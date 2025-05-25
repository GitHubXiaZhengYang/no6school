package com.straight_ocean.box;

import com.straight_ocean.No6School;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;


public class BoxEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);

    public BoxEntity(BlockPos pos, BlockState state) {
        super(No6School.BOX_BLOCK_ENTITY, pos, state);
    }



    // 从 ImplementedInventory 接口

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;

    }

    // 这些方法来自 NamedScreenHandlerFactory 接口
    // createMenu 会创建 ScreenHandler 自身
    // getDisplayName 会提供名称，名称通常显示在顶部

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        // 因为我们的类实现 Inventory，所以将*这个*提供给 ScreenHandler
        // 一开始只有服务器拥有物品栏，然后在 ScreenHandler 中同步给客户端
        return new BoxScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
        // 对于 1.19 之前的版本，请使用：
        // return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    // 以下两个方法，旧版本请移除参数 `registryLookup`。
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.inventory);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
    }
}
