package com.straight_ocean;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class Frame extends Item {
    public Frame(Settings settings) {
		super(settings
                .fireproof()
                .maxCount(1)
        );
    }
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.no6_school.frame.tooltip").formatted(Formatting.GOLD, Formatting.UNDERLINE));
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.playSound(SoundEvents.BLOCK_BELL_USE, 2.0F, 1.0F);
        if (!world.isClient()) {
            ServerWorld serverWorld = (ServerWorld) world;

            // 获取所有在线玩家
            List<ServerPlayerEntity> players = serverWorld.getPlayers();

            // 排除当前使用者
            players.removeIf(player -> player.getUuid().equals(user.getUuid()));

            if (!players.isEmpty()) {
                // 随机选择一名玩家
                Random random = new Random();
                PlayerEntity targetPlayer = players.get(random.nextInt(players.size()));

                // 给目标玩家添加10分钟的发光效果
                targetPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 6000, 0));
            }
        }

        // 减少物品耐久或其他逻辑
        ItemStack itemStack = user.getStackInHand(hand);
        itemStack.decrement(1);

        return TypedActionResult.success(itemStack);
    }
}
