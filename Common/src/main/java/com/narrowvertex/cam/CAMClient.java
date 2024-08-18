package com.narrowvertex.cam;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.slf4j.Logger;

import java.util.List;

public class CAMClient {

    private static CAMClient instance;

    public static int topStandID;
    public static int bottomStandID;

    public static Logger LOGGER = LogUtils.getLogger();

    public CAMClient() {
        instance = this;
    }

    public void init() {
    }

    public static CAMClient getInstance() {
        return instance;
    }
}
