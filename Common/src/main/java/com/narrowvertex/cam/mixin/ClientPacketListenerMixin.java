package com.narrowvertex.cam.mixin;

import com.mojang.datafixers.util.Pair;
import com.narrowvertex.cam.CAMClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Set;

@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin {

    @Unique
    public final double epsilon = 0.1;

    @Unique
    public final double topStandDistance = 12.96;

    @Unique
    public final double bottomStandDistance = 1.8225;

    @Shadow
    private ClientLevel level;

    @Inject(method = "handleSetEquipment(Lnet/minecraft/network/protocol/game/ClientboundSetEquipmentPacket;)V", at = {@At("TAIL")})
    private void handleSetEquipmentMixin(ClientboundSetEquipmentPacket clientboundSetEquipmentPacket, CallbackInfo info) {
        Entity entity = this.level.getEntity(clientboundSetEquipmentPacket.getEntity());

        if(entity instanceof ArmorStand armorStand) {
            Minecraft mc = Minecraft.getInstance();

            double distance = armorStand.position().distanceToSqr(mc.player.position());
            if(distance > 13)
                return;

            // entity.setInvisible(false);

            int entityID = entity.getId();
            System.out.println("test with: " + entityID);
            if(Math.abs(distance - topStandDistance) < epsilon)
                CAMClient.topStandID = entityID;
            else if(Math.abs(distance - bottomStandDistance) < epsilon)
                CAMClient.bottomStandID = entityID;

            /*
            StringBuilder armorItems = new StringBuilder();
            for(ItemStack itemStack : armorStand.getArmorSlots()) {
                String itemName = itemStack.getDisplayName().getString();
                armorItems.append(itemName).append(", ");
            }
            mc.player.displayClientMessage(Component.literal(armorItems.toString()), false);

            StringBuilder handItems = new StringBuilder();
            for(ItemStack itemStack : armorStand.getHandSlots()) {
                String itemName = itemStack.getDisplayName().getString();
                handItems.append(itemName).append(", ");
            }
            mc.player.displayClientMessage(Component.literal(handItems.toString()), false);

            String message = String.format("entityID: %d, distance: %f", entityID, distance);
            mc.player.displayClientMessage(Component.literal(message), false);
             */
        }
    }
}