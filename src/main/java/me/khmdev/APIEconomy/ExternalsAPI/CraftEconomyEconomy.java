package me.khmdev.APIEconomy.ExternalsAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.greatmancode.craftconomy3.Common;
import com.greatmancode.craftconomy3.account.Account;
import com.greatmancode.craftconomy3.account.AccountManager;

public class CraftEconomyEconomy implements IEconomy{
	AccountManager economy = null;

	public CraftEconomyEconomy() {

		Common eco = (Common)Bukkit.getServer().
				getPluginManager().getPlugin("Craftconomy3");
		economy=eco.getAccountManager();
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean setCash(String player, double Cash) {
		if(economy.getAccount(player)==null){
			return false;
		}
		Account acount=economy.getAccount(player);
		Player pl=Bukkit.getPlayer(player);
		String w=Bukkit.getWorlds().get(0).getName();
		if(pl!=null){
			w=pl.getWorld().getName();
		}
		return acount.set(Cash, w,player)==Cash;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean addCash(String player, double Cash) {
		if(economy.getAccount(player)==null){
			return false;
		}
		Account acount=economy.getAccount(player);
		Player pl=Bukkit.getPlayer(player);
		String w=Bukkit.getWorlds().get(0).getName();
		if(pl!=null){
			w=pl.getWorld().getName();
		}
		return acount.deposit(Cash, w,player)==Cash;
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean reduceCash(String player, double Cash) {
		if(economy.getAccount(player)==null){
			return false;
		}
		Account acount=economy.getAccount(player);
		Player pl=Bukkit.getPlayer(player);
		String w=Bukkit.getWorlds().get(0).getName();
		if(pl!=null){
			w=pl.getWorld().getName();
		}
		return acount.withdraw(Cash, w,player)==Cash;
	}

	@Override
	public double getCash(String player) {
		if(economy.getAccount(player)==null){
			return 0;
		}
		Account acount=economy.getAccount(player);
		@SuppressWarnings("deprecation")
		Player pl=Bukkit.getPlayer(player);
		String w=Bukkit.getWorlds().get(0).getName();
		if(pl!=null){
			w=pl.getWorld().getName();
		}
		return acount.getBalance(player, w);
	}

}
