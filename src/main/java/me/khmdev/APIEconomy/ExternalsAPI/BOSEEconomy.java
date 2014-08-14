package me.khmdev.APIEconomy.ExternalsAPI;

import org.bukkit.Bukkit;

import cosine.boseconomy.BOSEconomy;

public class BOSEEconomy implements IEconomy {
	BOSEconomy economy = null;

	public BOSEEconomy() {

		economy = (BOSEconomy) Bukkit.getServer().getPluginManager()
				.getPlugin("BOSEconomy");
	}

	@Override
	public boolean setCash(String player, double Cash) {
		return economy.setPlayerMoney(player, Cash, false);
	}

	@Override
	public boolean addCash(String player, double Cash) {
		return economy.addPlayerMoney(player, Cash, false);
	}

	@Override
	public boolean reduceCash(String player, double Cash) {
		return economy.addPlayerMoney(player, -Cash, false);

	}

	@Override
	public double getCash(String player) {
		return economy.getPlayerMoneyDouble(player);

	}

}
