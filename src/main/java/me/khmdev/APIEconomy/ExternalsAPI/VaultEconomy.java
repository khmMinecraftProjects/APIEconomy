package me.khmdev.APIEconomy.ExternalsAPI;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.economy.Economy;

public class VaultEconomy implements IEconomy {
	Economy economy = null;

	public VaultEconomy()  {

		RegisteredServiceProvider<Economy> eco = Bukkit.getServer().
				 getServicesManager()
				 .getRegistration(Economy.class);
		if(eco==null){return;}
		try {
			economy=eco.getService().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean setCash(String player, double Cash) {
		if(economy==null){return false;}
		if(economy.hasAccount(player)){
			economy.withdrawPlayer(player, economy.getBalance(player));
		}
		return economy.depositPlayer(player, Cash) != null;

	}

	@Override
	public boolean addCash(String player, double Cash) {
		if(economy==null){return false;}

		return economy.depositPlayer(player, Cash) != null;
	}

	@Override
	public boolean reduceCash(String player, double Cash) {
		if(economy==null){return false;}

		return economy.withdrawPlayer(player, Cash) != null;

	}

	@Override
	public double getCash(String player) {
		if(economy==null){return 0;}

		return economy.getBalance(player);

	}

}
