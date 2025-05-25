package com.straight_ocean.rubbish;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class Hud implements HudRenderCallback {
    // 原版血量纹理
    private static final Identifier HEALTH_TEXTURE = new Identifier("minecraft", "textures/gui/icons.png");

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;

        // 移除hudHidden判断以确保强制显示
        if (player == null) return;

        int x = 10;  // 左边距
        int y = 10;  // 上边距

        // 获取血量数据
        int health = (int) Math.ceil(player.getHealth());
        int maxHealth = (int) Math.ceil(player.getMaxHealth());

        // 计算心形数量
        int hearts = maxHealth / 2;
        // 处理奇数血量
        boolean hasHalfHeart = maxHealth % 2 != 0;

        // 血量渲染逻辑
        for (int i = 0; i < hearts + (hasHalfHeart ? 1 : 0); i++) {
            // 纹理坐标计算
            int u = 16;
            int v = 0;

            // 当前位置是否应显示半血
            boolean isHalf = i * 2 + 1 > health;

//            // 难度模式纹理偏移
//            if (hardcore && i < 10) {
//                u += 9;  // 硬核模式红心X轴偏移
//            }

            // 血量状态纹理偏移


            // 绘制心形图标
            drawContext.drawTexture(HEALTH_TEXTURE, x + i * 8, y, u, v, 9, 9);
            if (health > 0) {
                if (isHalf) {
                    drawContext.drawTexture(HEALTH_TEXTURE, x + i * 8, y, u + 45, v, 9, 9);
                } else {
                    drawContext.drawTexture(HEALTH_TEXTURE, x + i * 8, y, u + 36, v, 9, 9);
                }
            }


            // 更新剩余血量
            health -= 2;
        }
    }
}
