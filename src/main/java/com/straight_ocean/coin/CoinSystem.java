package com.straight_ocean.coin;

import com.straight_ocean.No6School;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

// CoinSystem.java 完整实现
public class CoinSystem {
    public static final Identifier COIN_ID = new Identifier(No6School.MOD_ID, "coin");
    public static final TrackedData<Integer> COIN = DataTracker.registerData(
        PlayerEntity.class,
        TrackedDataHandlerRegistry.INTEGER
    );

    public static void init() {
        // 注册网络事件
        registerNetworkHandlers();

        // 监听玩家加入事件
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            PlayerEntity player = handler.getPlayer();
            if (!player.getDataTracker().containsKey(COIN)) {
                player.getDataTracker().startTracking(COIN, 0); // 初始资金
            }
        });
    }

    private static void registerNetworkHandlers() {
        // 服务器端处理C2S请求
        ServerPlayNetworking.registerGlobalReceiver(
            CoinPackets.C2S_REQUEST_COIN,
            (server, player, handler, buf, responseSender) -> {
                int currentCoin = player.getDataTracker().get(COIN);
                CoinPackets.sendS2CCoinUpdate(player, currentCoin);
            }
        );
    }
}
