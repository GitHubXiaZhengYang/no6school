package com.straight_ocean;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class DataBox extends SimpleFabricLootTableProvider {
    public DataBox(FabricDataOutput output) {
        super(output, LootContextTypes.CHEST);
    }
    public static final Identifier BOX_TABLE = new Identifier(No6School.MOD_ID, "box_table");

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> lootTableBiConsumer) {
        lootTableBiConsumer.accept(BOX_TABLE, LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(ItemEntry.builder(Items.DIAMOND)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                        .with(ItemEntry.builder(Items.DIAMOND_SWORD)))
        );
    }
}