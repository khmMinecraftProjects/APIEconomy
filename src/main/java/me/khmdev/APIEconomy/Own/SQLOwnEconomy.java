package me.khmdev.APIEconomy.Own;

import me.khmdev.APIEconomy.ExternalsAPI.IEconomy;


public class SQLOwnEconomy implements IEconomy{

	public SQLOwnEconomy() {
	}

	@Override
	public boolean setCash(String player, double Cash) {
		SQLEconomy.updateMoney(player, Cash);
		return true;
	}

	@Override
	public boolean addCash(String player, double Cash) {
		SQLEconomy.updateMoney(player, SQLEconomy.getMoney(player)+Cash);
		return true;
	}

	@Override
	public boolean reduceCash(String player, double Cash) {
		SQLEconomy.updateMoney(player, SQLEconomy.getMoney(player)-Cash);
		return true;
	}

	@Override
	public double getCash(String player) {
		return SQLEconomy.getMoney(player);
	}

}
