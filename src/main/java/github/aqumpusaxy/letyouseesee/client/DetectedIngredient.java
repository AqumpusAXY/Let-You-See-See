package github.aqumpusaxy.letyouseesee.client;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public record DetectedIngredient(ResourceLocation id, String type, Component info, Optional<ItemStack> itemStack) {}
