package com.straight_ocean.job;

import com.straight_ocean.No6School;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

// CoinSystem.java 完整实现
public class JobSystem {
    public static final Identifier JOB_ID = new Identifier(No6School.MOD_ID, "job");
    public static final TrackedData<String> JOB = DataTracker.registerData(
            PlayerEntity.class,
            TrackedDataHandlerRegistry.STRING
    );

    public static void init() {
        // 注册网络事件
        registerNetworkHandlers();

        // 监听玩家加入事件
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            PlayerEntity player = handler.getPlayer();
            if (!player.getDataTracker().containsKey(JOB)) {
                player.getDataTracker().startTracking(JOB, JobList.STUDENT); // 初始资金
            }
        });
    }

    private static void registerNetworkHandlers() {
        // 服务器端处理C2S请求
        ServerPlayNetworking.registerGlobalReceiver(
                JobPackets.C2S_REQUEST_JOB,
                (server, player, handler, buf, responseSender) -> {
                    String job = player.getDataTracker().get(JOB);
                    JobPackets.sendS2CCoinUpdate(player, job);
                }
        );
    }
}
