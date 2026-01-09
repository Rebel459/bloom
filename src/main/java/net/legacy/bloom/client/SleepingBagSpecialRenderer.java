package net.legacy.bloom.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BedRenderer;
import net.minecraft.client.renderer.special.BedSpecialRenderer;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Blocks;
import org.joml.Vector3fc;
import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class SleepingBagSpecialRenderer implements NoDataSpecialModelRenderer {
	private final SleepingBagRenderer renderer;
	private final Material material;

	public SleepingBagSpecialRenderer(SleepingBagRenderer renderer, Material material) {
		this.renderer = renderer;
		this.material = material;
	}

	@Override
	public void submit(ItemDisplayContext itemDisplayContext, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int j, boolean bl, int k) {
		this.renderer.submitSpecial(poseStack, submitNodeCollector, i, j, this.material, k);
	}

	@Override
	public void getExtents(Consumer<Vector3fc> consumer) {
		this.renderer.getExtents(consumer);
	}

	@Environment(EnvType.CLIENT)
	public record Unbaked(Identifier texture) implements SpecialModelRenderer.Unbaked {
		public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(Identifier.CODEC.fieldOf("texture").forGetter(SleepingBagSpecialRenderer.Unbaked::texture))
				.apply(instance, SleepingBagSpecialRenderer.Unbaked::new)
		);

		public Unbaked(DyeColor dyeColor) {
			this(Sheets.colorToResourceMaterial(dyeColor));
		}

		@Override
		public MapCodec<SleepingBagSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		@Override
		public SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext bakingContext) {
			return new SleepingBagSpecialRenderer(new SleepingBagRenderer(bakingContext), SleepingBagSheets.SLEEPING_BAG_MAPPER.apply(this.texture));
		}
	}
}
