package com.straight_ocean;
import com.straight_ocean.coin.*;
import com.straight_ocean.box.*;

import com.straight_ocean.job.JobClientEvents;
import com.straight_ocean.job.JobOverlay;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@Environment(EnvType.CLIENT)
	public class No6SchoolClient implements ClientModInitializer {
		@Override
		public void onInitializeClient() {
			HandledScreens.register(No6School.BOX_SCREEN_HANDLER, BoxScreen::new);
			ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
				CoinClientEvents.init();
			});
			ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
				JobClientEvents.init();
			});

			// 注册UI渲染事件
			HudRenderCallback.EVENT.register(CoinOverlay::render);
			HudRenderCallback.EVENT.register(JobOverlay::render);
	}
}
