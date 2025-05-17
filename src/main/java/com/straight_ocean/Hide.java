package com.straight_ocean;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Hide extends Item {
    public Hide(Settings settings) {
        super(settings
                .fireproof()
                .maxCount(1)
        );
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.no6_school.hide.tooltip").formatted(Formatting.GOLD, Formatting.UNDERLINE));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.playSound(SoundEvents.BLOCK_BELL_USE, 2.0F, 1.0F);
        if (!world.isClient) {
            // 存储玩家原来的游戏模式
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) user;
            GameMode originalMode = serverPlayer.interactionManager.getGameMode();


            // 更改玩家为旁观者模式
            serverPlayer.interactionManager.changeGameMode(GameMode.SPECTATOR);

            // 播放音效提示
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);

            // 设置一个延迟任务，在30秒后恢复原游戏模式
            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            serverPlayer.interactionManager.changeGameMode(originalMode);
                        }
                    },
                    30000 // 延迟30秒执行
            );
        }
        ItemStack itemStack = user.getStackInHand(hand);
        itemStack.decrement(1);

        return TypedActionResult.success(itemStack);
    }
}
