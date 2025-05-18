package com.straight_ocean;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@Environment(EnvType.CLIENT)
	public class No6SchoolClient implements ClientModInitializer {
		@Override
		public void onInitializeClient() {
			HandledScreens.register(No6School.BOX_SCREEN_HANDLER, BoxScreen::new);
	}
}
