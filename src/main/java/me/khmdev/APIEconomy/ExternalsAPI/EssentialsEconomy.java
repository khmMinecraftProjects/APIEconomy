package me.khmdev.APIEconomy.ExternalsAPI;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;

public class EssentialsEconomy implements IEconomy{

	@SuppressWarnings("deprecation")
	@Override
	public boolean setCash(String player, double Cash) {
		if(Economy.playerExists(player)){
			return false;
		}
		try {
			Economy.setMoney(player, Cash);
		} catch (NoLoanPermittedException | UserDoesNotExistException e) {
			e.printStackTrace();
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean addCash(String player, double Cash) {
		if(Economy.playerExists(player)){
			return false;
		}
		try {
			Economy.add(player, Cash);
		} catch (NoLoanPermittedException | UserDoesNotExistException e) {
			e.printStackTrace();
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean reduceCash(String player, double Cash) {
		if(Economy.playerExists(player)){
			return false;
		}
		try {
			Economy.subtract(player, Cash);
		} catch (NoLoanPermittedException | UserDoesNotExistException e) {
			e.printStackTrace();
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public double getCash(String player) {
		if(Economy.playerExists(player)){
			return 0;
		}
		try {
			return Economy.getMoney(player);
		} catch ( UserDoesNotExistException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}
