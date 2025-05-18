package com.straight_ocean;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;


import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class No6School implements ModInitializer {
	public static final String MOD_ID = "no6-school";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final BlockEntityType<BoxEntity> BOX_BLOCK_ENTITY =
			Registry.register(
					Registries.BLOCK_ENTITY_TYPE,
					new Identifier(MOD_ID, "box_block_entity"),
					BlockEntityType.Builder.create(BoxEntity::new, No6SchoolItems.BOX).build(null)
			);

	public static final ScreenHandlerType<BoxScreenHandler> BOX_SCREEN_HANDLER =
			Registry.register(Registries.SCREEN_HANDLER, Identifier.of(MOD_ID, "box_block"),
					new ScreenHandlerType<>(BoxScreenHandler::new, FeatureSet.empty()));



	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		No6SchoolItems.init();


		LOGGER.info("Hello world!");
		LOGGER.info("I'm Straight_Ocean");
	}
}
