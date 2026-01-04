package net.legacy.bloom.util;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.legacy.bloom.mixin.worldgen.BiomeAccessor;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.ChatFormatting;

public class ClimateCommand {

	public static void init() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(Commands.literal("climate")
				.executes(ctx -> climateCommand(ctx, ctx.getSource().getPlayerOrException()))
			);
		});
	}

	private static int climateCommand(CommandContext<CommandSourceStack> ctx, ServerPlayer player) throws CommandSyntaxException {
		CommandSourceStack source = ctx.getSource();
		ServerLevel world = player.level();
		BlockPos pos = player.blockPosition();
		int seaLevel = world.getSeaLevel();

		Biome biome = world.getBiome(pos).value();

		float baseTemp = biome.getBaseTemperature();
		float adjustedTemp = biome.getTemperature(pos, seaLevel);

		Biome.ClimateSettings climate = ((BiomeAccessor) (Object) biome).getClimate();
		float downfall = climate.downfall();

		ResourceKey<Biome> biomeKey = world.getBiome(pos).unwrapKey().get();

		source.sendSuccess(() -> Component.literal("")
			.append(Component.literal("Climate at " + pos + "\n")
				.withStyle(ChatFormatting.WHITE))
			.append(Component.literal("  Biome: " + biomeKey.identifier() + "\n")
				.withStyle(ChatFormatting.GRAY))
			.append(Component.literal(String.format("  Local Temperature: %.3f\n", adjustedTemp))
				.withStyle(ChatFormatting.AQUA))
			.append(Component.literal(String.format("  Biome Temperature: %.3f\n", baseTemp))
				.withStyle(ChatFormatting.GREEN))
			.append(Component.literal(String.format("  Biome Downfall: %.3f", downfall))
				.withStyle(ChatFormatting.LIGHT_PURPLE)), false);
		return 1;
	}
}
