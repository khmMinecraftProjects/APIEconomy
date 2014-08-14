package me.khmdev.APIEconomy.Own;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import me.khmdev.APIBase.Almacenes.Almacen;
import me.khmdev.APIBase.Almacenes.Datos;
import me.khmdev.APIEconomy.ExternalsAPI.IEconomy;

import org.bukkit.plugin.java.JavaPlugin;


public class OwnEconomy implements IEconomy,Datos{
	HashMap<String, Double> acounts=new HashMap<>();
	

	
	public OwnEconomy(JavaPlugin plug){
		

	}
	public void OnlySetCash(String player, double Cash) {
		acounts.put(player, Cash);
	}
	@Override
	public boolean setCash(String player, double Cash) {
		if(acounts.containsKey(player)){
			acounts.put(player, Cash);

			return true;
		}
		acounts.put(player, Cash);

		return false;
	}

	@Override
	public boolean addCash(String player, double Cash) {
		if(acounts.containsKey(player)){
			acounts.put(player, acounts.get(player)+Cash);

			return true;
		}
		acounts.put(player, Cash);

		return false;
	}

	@Override
	public boolean reduceCash(String player, double Cash) {
		if(acounts.containsKey(player)){
			acounts.put(player, acounts.get(player)-Cash);

			return true;
		}
		acounts.put(player, -Cash);

		return false;

	}

	@Override
	public double getCash(String player) {
		if(acounts.containsKey(player)){
			return acounts.get(player);
		}
		acounts.put(player, 0d);

		return acounts.get(player);
	}

	@Override
	public void cargar(Almacen nbt) {
		Iterator<String> it=nbt.getKeys().iterator();
		while(it.hasNext()){
			String s=it.next();
			double cash=nbt.getDouble(s);
			acounts.put(s, cash);
		}
		
		
	}

	@Override
	public void guardar(Almacen nbt) {
		Iterator<Entry<String, Double>> it=acounts.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, Double> s=it.next();
			nbt.setDouble(s.getKey(), s.getValue());
		}		
	}

	
}
