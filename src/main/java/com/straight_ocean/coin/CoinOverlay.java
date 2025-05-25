package com.straight_ocean.coin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

// CoinOverlay.java 货币显示组件
// CoinOverlay.java - 增加千分位格式化
public class CoinOverlay {
    public static void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && client.world != null) {
            int coin = CoinClientData.getCoin();

            // 使用格式化方法添加千分位分隔符
            String text = "金币: " + formatWithCommas(coin);

            context.drawText(
                client.textRenderer,
                text,
                10, 10,
                0xFFFFD700, // 金色
                true
            );
        }
    }

    /**
     * 手动实现千分位格式化（兼容所有环境）
     * @param number 需要格式化的数字
     * @return 带千分位分隔符的字符串
     */
    private static String formatWithCommas(int number) {
        String numStr = Integer.toString(number);
        int len = numStr.length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            if (i > 0 && (len - i) % 3 == 0) {
                sb.append(',');
            }
            sb.append(numStr.charAt(i));
        }

        return sb.toString();
    }
}
