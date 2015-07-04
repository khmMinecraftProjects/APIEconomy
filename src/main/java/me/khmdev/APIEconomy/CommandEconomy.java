package me.khmdev.APIEconomy;

import me.khmdev.APIEconomy.Own.APIEconomy;
import me.khmdev.APIEconomy.lang.Lang;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandEconomy {
	private static String help() {
		return Lang.get("CommandEconomy.help");
	}

	private static double getDouble(String s, double d) {
		try {
			return Double.valueOf(s);
		} catch (Exception e) {
			return d;
		}
	}

	public static boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] args) {
		String pl = sender.getName();

		if (cmd.getName().equalsIgnoreCase("coins")) {
			if (sender.getName() == "CONSOLE") {
				return true;
			}
			sender.sendMessage(Lang.get("CommandEconomy.get")
					.replace("%cash%", APIEconomy.getCash(pl) + "")
					.replace("%UM%", ConstantesEconomy.UM+""));
			return true;
		}
		if (!cmd.getName().equalsIgnoreCase("apie")) {
			return false;
		}
		if (args.length == 0) {
			sender.sendMessage(help());
			return true;
		}

		if (args[0].equals("get")) {
			if (args.length == 1) {
				if (sender.getName() == "CONSOLE") {
					sender.sendMessage(help());
					return true;
				}
				sender.sendMessage(Lang.get("CommandEconomy.get")
						.replace("%cash%", APIEconomy.getCash(pl) + "")
						.replace("%UM%", ConstantesEconomy.UM+""));
				return true;
			}

		}
		if (args[0].equals("get")) {
			if (args.length < 2) {
				sender.sendMessage(Lang.get("CommandEconomy.NoValues"));
				return true;
			}
			Player pl2 = Bukkit.getPlayer(args[1]);
			if (pl2 == null) {
				sender.sendMessage(Lang.get("CommandEconomy.WrongValues"));
				return true;
			}
			sender.sendMessage(Lang.get("CommandEconomy.getOther")
					.replace("%pl%", pl2.getName())
					.replace("%cash%", APIEconomy.getCash(pl2.getName())  + "")
					.replace("%UM%", ConstantesEconomy.UM+""));

			return true;
		}

		if (args[0].equals("set")) {
			if (args.length < 3) {
				sender.sendMessage("No se ha introducido valores");
				return true;
			}
			Player pl2 = Bukkit.getPlayer(args[1]);

			double cash = getDouble(args[2], -1);
			if (cash < 0 || pl2 == null) {
				sender.sendMessage("Valor incorrecto");
				return true;
			}
			if (APIEconomy.setCash(args[1], cash)) {
				sender.sendMessage(pl2.getName() + " tiene " + cash + "$");
			} else {
				sender.sendMessage("APIE no disponible");
			}
			return true;
		}
		if (args[0].equals("add")) {
			if (args.length < 3) {
				sender.sendMessage("No se ha introducido valores");
				return true;
			}
			Player pl2 = Bukkit.getPlayer(args[1]);
			double cash = getDouble(args[2], -1);
			if (cash < 0 || pl2 == null) {
				sender.sendMessage("Valor incorrecto");
				return true;
			}
			if (APIEconomy.addCash(args[1], cash)) {

				sender.sendMessage(pl2.getName() + " se le ha añadido " + cash
						+ "$");
			} else {
				sender.sendMessage("APIE no disponible");
			}
			return true;
		}
		if (args[0].equals("reducir")) {
			if (args.length < 3) {
				sender.sendMessage("No se ha introducido valores");
				return true;
			}
			Player pl2 = Bukkit.getPlayer(args[1]);
			double cash = getDouble(args[2], -1);
			if (cash < 0 || pl2 == null) {
				sender.sendMessage("Valor incorrecto");
				return true;
			}
			if (APIEconomy.reduceCash(args[1], cash)) {
				sender.sendMessage(pl2.getName() + " se le ha quitado " + cash
						+ "$");
			} else {
				sender.sendMessage("APIE no disponible");
			}
			return true;
		}

		sender.sendMessage(help());
		return true;
	}
}
