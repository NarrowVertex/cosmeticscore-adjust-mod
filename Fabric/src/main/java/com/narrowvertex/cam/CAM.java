package com.narrowvertex.cam;

import com.narrowvertex.cam.input.KeyBindings;
import net.fabricmc.api.ModInitializer;

public class CAM implements ModInitializer {

    public static final String MODID = "cos";

    private CAMClient client;
    private KeyBindings keyBindings;

    @Override
    public void onInitialize() {
        client = new CAMClient();
        keyBindings = new KeyBindings();

        client.init();
        keyBindings.register();
    }
}
