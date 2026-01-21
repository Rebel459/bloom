package net.legacy.bloom.registry;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.legacy.bloom.config.BloomConfig;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.entity.npc.villager.VillagerTrades;

public class BloomVillagerTrades {

    public static void init() {
		if (BloomConfig.get.farming.cotton && BloomConfig.get.farming.tradable_yarn) {
			TradeOfferHelper.registerVillagerOffers(
				VillagerProfession.SHEPHERD,
				1,
				(trades, rebalanced) -> trades.add(
					new VillagerTrades.EmeraldForItems(
						BloomItems.YARN,
						20,
						8,
						2
					)
				)
			);
		}
    }
}
