package me.khmdev.APIEconomy.ExternalsAPI;

public interface IEconomy {
	
	public boolean setCash(String player,double Cash);
	
	public boolean addCash(String player,double Cash);
	
	public boolean reduceCash(String player,double Cash);
	
	public double getCash(String player);
}
