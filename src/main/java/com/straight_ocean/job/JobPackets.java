package com.straight_ocean.job;

import com.straight_ocean.No6School;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

// CoinPackets.java 网络通信定义
public class JobPackets {
    // C2S 请求货币数据
    public static final Identifier C2S_REQUEST_JOB =
            new Identifier(No6School.MOD_ID, "c2s_request_job");

    // S2C 更新货币数据
    public static final Identifier S2C_UPDATE_JOB =
            new Identifier(No6School.MOD_ID, "s2c_update_job");

    // 发送S2C数据包
    public static void sendS2CCoinUpdate(ServerPlayerEntity player, String job) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(job);
        ServerPlayNetworking.send(player, S2C_UPDATE_JOB, buf);
    }

    // 注册客户端接收器
    public static void registerClientReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(
                S2C_UPDATE_JOB,
                (client, handler, buf, responseSender) -> {
                    String job = buf.readString();
                    JobClientData.setJob(job);
                }
        );
    }
}