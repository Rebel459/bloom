package net.rebel459.bloom.registry;

import net.rebel459.bloom.config.BloomConfig;

public class BloomVillagerTrades {

    public static void init() {
		if (BloomConfig.get.farming.cotton && BloomConfig.get.farming.tradable_yarn) {
/*			TradeOfferHelper.registerVillagerOffers(
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
			);*/
		}
    }
}
