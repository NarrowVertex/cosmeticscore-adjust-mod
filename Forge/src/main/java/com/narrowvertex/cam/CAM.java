package com.narrowvertex.cam;

import com.narrowvertex.cam.input.KeyBindings;
import net.minecraftforge.fml.common.Mod;

@Mod(CAM.MODID)
public class CAM {
	public static final String MODID = "cos";

	private CAMClient client;
	private KeyBindings keyMapping;

	public CAM() {
		client = new CAMClient();
		client.init();

		keyMapping = new KeyBindings();
		keyMapping.register();
	}
}
