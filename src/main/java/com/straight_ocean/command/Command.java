package com.straight_ocean.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.straight_ocean.coin.CoinSystem;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Objects;


public class Command {
    public static void init() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("add_coin")
                    .then(CommandManager.argument("value", IntegerArgumentType.integer(1))
                                    .executes(context -> {
                                        ServerCommandSource source = context.getSource();
                                        int cache = Objects.requireNonNull(source.getPlayer()).getDataTracker().get(CoinSystem.COIN);
                                        Objects.requireNonNull(source.getPlayer()).getDataTracker().set(CoinSystem.COIN, cache + IntegerArgumentType.getInteger(context, "value"));
                                        return 1;
                                    })
                    )
            );
        });
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("remove_coin")
                    .then(CommandManager.argument("value", IntegerArgumentType.integer(1))
                            .executes(context -> {
                                ServerCommandSource source = context.getSource();
                                int cache = Objects.requireNonNull(source.getPlayer()).getDataTracker().get(CoinSystem.COIN);
                                Objects.requireNonNull(source.getPlayer()).getDataTracker().set(CoinSystem.COIN, cache - IntegerArgumentType.getInteger(context, "value"));
                                return 1;
                            })
                    )
            );
        });
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("set_coin")
                    .then(CommandManager.argument("value", IntegerArgumentType.integer(1))
                            .executes(context -> {
                                ServerCommandSource source = context.getSource();
                                Objects.requireNonNull(source.getPlayer()).getDataTracker().set(CoinSystem.COIN, IntegerArgumentType.getInteger(context, "value"));
                                return 1;
                            })
                    )
            );
        });
    }
}
