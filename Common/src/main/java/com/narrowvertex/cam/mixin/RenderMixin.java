package com.narrowvertex.cam.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.narrowvertex.cam.CAMClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ArmorStand;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(LevelRenderer.class)
public class RenderMixin {

    @Unique
    public final double topStandDistance = 3.6;

    @Unique
    public final double bottomStandDistance = 1.35;

    @Shadow
    @Final
    private EntityRenderDispatcher entityRenderDispatcher;
    /*
    partialTick:
      timer.partialTick: Minecraft private Timer
      pausePartialTick: Minecraft private float
     */
    @Inject(method="renderEntity(Lnet/minecraft/world/entity/Entity;DDDFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;)V", at=@At("HEAD"))
    private void renderEntityMixin(Entity entity, double camX, double camY, double camZ, float partialTick, PoseStack poseStack, MultiBufferSource source, CallbackInfo callbackInfo) {
        if(entity instanceof ArmorStand armorStand) {
            int entityID = entity.getId();

            if(CAMClient.topStandID == entityID || CAMClient.bottomStandID == entityID) {
                Minecraft mc = Minecraft.getInstance();

                if(mc.player == null)
                    return;

                double height = 0;
                if(CAMClient.topStandID == entityID)
                    height = topStandDistance;
                else
                    height = bottomStandDistance;

                armorStand.setYRot(mc.player.yBodyRot);
                armorStand.setPos(mc.player.getX(), mc.player.getY() + height, mc.player.getZ());
            }
        }
    }
}
