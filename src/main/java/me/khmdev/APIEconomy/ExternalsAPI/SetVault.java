package me.khmdev.APIEconomy.ExternalsAPI;
import java.util.List;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
public class SetVault implements Economy{

	public EconomyResponse bankBalance(String arg0) {
		return null;
	}

	public EconomyResponse bankDeposit(String arg0, double arg1) {
		return null;
	}

	public EconomyResponse bankHas(String arg0, double arg1) {
		return null;
	}

	public EconomyResponse bankWithdraw(String arg0, double arg1) {
		return null;
	}

	public EconomyResponse createBank(String arg0, String arg1) {
		return null;
	}

	public boolean createPlayerAccount(String arg0) {
		return false;
	}

	public boolean createPlayerAccount(String arg0, String arg1) {
		return false;
	}

	public String currencyNamePlural() {
		return null;
	}

	public String currencyNameSingular() {
		return null;
	}

	public EconomyResponse deleteBank(String arg0) {
		return null;
	}

	public EconomyResponse depositPlayer(String arg0, double arg1) {
		return null;
	}

	public EconomyResponse depositPlayer(String arg0, String arg1, double arg2) {
		return null;
	}

	public String format(double arg0) {
		return null;
	}

	public int fractionalDigits() {
		return 0;
	}

	public double getBalance(String arg0) {
		return 0;
	}

	public double getBalance(String arg0, String arg1) {
		return 0;
	}

	public List<String> getBanks() {
		return null;
	}

	public String getName() {
		return null;
	}

	public boolean has(String arg0, double arg1) {
		return false;
	}

	public boolean has(String arg0, String arg1, double arg2) {
		return false;
	}

	public boolean hasAccount(String arg0) {
		return false;
	}

	public boolean hasAccount(String arg0, String arg1) {
		return false;
	}

	public boolean hasBankSupport() {
		return false;
	}

	public EconomyResponse isBankMember(String arg0, String arg1) {
		return null;
	}

	public EconomyResponse isBankOwner(String arg0, String arg1) {
		return null;
	}

	public boolean isEnabled() {
		return false;
	}

	public EconomyResponse withdrawPlayer(String arg0, double arg1) {
		return null;
	}

	public EconomyResponse withdrawPlayer(String arg0, String arg1, double arg2) {
		return null;
	}

}
