package com.straight_ocean.coin;

// CoinClientData.java 客户端本地存储
public class CoinClientData {
    private static int coin;

    public static int getCoin() {
        return coin;
    }

    public static void setCoin(int coin) {
        CoinClientData.coin = coin;
    }
}

