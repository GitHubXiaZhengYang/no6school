package com.straight_ocean.job;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

// CoinOverlay.java 货币显示组件
// CoinOverlay.java - 增加千分位格式化
public class JobOverlay {
    public static void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && client.world != null) {
            String job = JobClientData.getJob();

            // 使用格式化方法添加千分位分隔符
            String text = "职业: " + job;

            context.drawText(
                    client.textRenderer,
                    text,
                    10, 20,
                    0xFFFFD700, // 金色
                    true
            );
        }
    }
}
