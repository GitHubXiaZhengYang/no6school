package com.straight_ocean.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.straight_ocean.coin.CoinSystem;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Objects;


public class Command {
    public static void init() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("coin")
                .then(CommandManager.literal("add")
                    .then(CommandManager.argument("player", EntityArgumentType.player())
                        .then(CommandManager.argument("value", IntegerArgumentType.integer())
                            .executes(context -> {
                                ServerPlayerEntity player = EntityArgumentType.getPlayer(context, "player");
                                int cache = Objects.requireNonNull(player).getDataTracker().get(CoinSystem.COIN);
                                Objects.requireNonNull(player).getDataTracker().set(CoinSystem.COIN, cache + IntegerArgumentType.getInteger(context, "value"));
                                return 1;
                            })
                        )
                    )
                )
                .then(CommandManager.literal("remove")
                    .then(CommandManager.argument("player", EntityArgumentType.player())
                        .then(CommandManager.argument("value", IntegerArgumentType.integer(1))
                            .executes(context -> {
                                ServerPlayerEntity player = EntityArgumentType.getPlayer(context, "player");
                                int cache = Objects.requireNonNull(player).getDataTracker().get(CoinSystem.COIN);
                                Objects.requireNonNull(player).getDataTracker().set(CoinSystem.COIN, cache - IntegerArgumentType.getInteger(context, "value"));
                                return 1;
                            })
                        )
                    )
                )
                .then(CommandManager.literal("set")
                    .then(CommandManager.argument("value", IntegerArgumentType.integer(1))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                            .executes(context -> {
                                ServerPlayerEntity player = EntityArgumentType.getPlayer(context, "player");
                                Objects.requireNonNull(player).getDataTracker().set(CoinSystem.COIN, IntegerArgumentType.getInteger(context, "value"));
                                return 1;
                            })
                        )
                    )
                )
            );
        });
    }
}
