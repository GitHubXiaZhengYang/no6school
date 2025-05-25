package com.straight_ocean;
import com.straight_ocean.box.*;
import com.straight_ocean.items.*;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;



public class No6SchoolItems {
    public static final Item NO6_SCHOOL = register("no6_school",
            new Item(new FabricItemSettings()
                    .maxCount(1)
                    .fireproof()
            ));
    public static final Item FRAME = register("frame",
            new Frame(new FabricItemSettings()));
    public static final Item HIDE = register("hide",
            new Hide(new FabricItemSettings()));
    public static final Block BOX= register("box",
            new Box(AbstractBlock.Settings.copy(Blocks.CHEST)));
    public static final Item BOX_ITEM = register("box", new BlockItem(BOX, new Item.Settings()));

    public static final ItemGroup NO6_SCHOOL_GROUP = FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup.no6_school"))
            .icon(() -> new ItemStack(No6SchoolItems.NO6_SCHOOL))
            .entries((context, entries) -> {
                entries.add(No6SchoolItems.NO6_SCHOOL);
                entries.add(No6SchoolItems.FRAME);
                entries.add(No6SchoolItems.HIDE);
                entries.add(No6SchoolItems.BOX_ITEM);
            })
            .build();

    public static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, new Identifier(No6School.MOD_ID, name), item);

    }
    public static <T extends Block> T register(String name, T block) {
        return Registry.register(Registries.BLOCK, new Identifier(No6School.MOD_ID, name), block);
    }
    public static void init() {
        Registry.register(Registries.ITEM_GROUP, new Identifier(No6School.MOD_ID, "no6_school_group"), NO6_SCHOOL_GROUP);
    }
}
