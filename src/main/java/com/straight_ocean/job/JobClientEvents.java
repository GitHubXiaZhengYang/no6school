package com.straight_ocean.job;


import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;

// CoinClientEvents.java 客户端事件处理
public class JobClientEvents {
    private static int updateCounter;

    public static void init() {
        // 注册网络接收器
        JobPackets.registerClientReceiver();

        // 每秒请求更新
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player != null && updateCounter++ % 20 == 0) {
                ClientPlayNetworking.send(JobPackets.C2S_REQUEST_JOB, PacketByteBufs.create());
            }
        });
    }
}
