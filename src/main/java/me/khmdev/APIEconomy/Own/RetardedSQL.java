package me.khmdev.APIEconomy.Own;

import java.util.LinkedList;
import java.util.Queue;

import me.khmdev.APIBase.Auxiliar.runKill;
import me.khmdev.APIEconomy.Own.PlayerUpdate.apli;

public class RetardedSQL extends runKill{
	private static Queue<PlayerUpdate> list=new LinkedList<>();
	public static void add(String p,double f,apli a){
		list.add(new PlayerUpdate(p, f,a));
	}
	public static void add(String p,double f){
		list.add(new PlayerUpdate(p, f,apli.set));
	}
	public static void add(PlayerUpdate p){
		list.add(p);
	}
	@Override
	public void run() {
		if(list.size()==0){return;}
		PlayerUpdate up=list.poll();
		switch (up.getAplic()) {
		case reduce:
			double d=SQLEconomy.getMoney(up.getPl());
			SQLEconomy.updateMoney(up.getPl(), d-up.getM());
			break;
		case added:
			double d2=SQLEconomy.getMoney(up.getPl());
			SQLEconomy.updateMoney(up.getPl(), d2+up.getM());
			break;		
		case set:
			SQLEconomy.updateMoney(up.getPl(), up.getM());
			break;
		default:
			break;
		}
	}

}
