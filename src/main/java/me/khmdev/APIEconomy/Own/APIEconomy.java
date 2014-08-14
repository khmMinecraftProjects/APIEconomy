package me.khmdev.APIEconomy.Own;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.khmdev.APIBase.API;
import me.khmdev.APIBase.Almacenes.Almacen;
import me.khmdev.APIBase.Almacenes.Central;
import me.khmdev.APIBase.Almacenes.ConfigFile;
import me.khmdev.APIBase.Almacenes.Datos;
import me.khmdev.APIBase.Auxiliar.runKill;
import me.khmdev.APIEconomy.CommandEconomy;
import me.khmdev.APIEconomy.ExternalsAPI.BOSEEconomy;
import me.khmdev.APIEconomy.ExternalsAPI.CraftEconomyEconomy;
import me.khmdev.APIEconomy.ExternalsAPI.EssentialsEconomy;
import me.khmdev.APIEconomy.ExternalsAPI.IEconomy;
import me.khmdev.APIEconomy.ExternalsAPI.VaultEconomy;
import me.khmdev.APIEconomy.Own.PlayerUpdate.apli;

public class APIEconomy implements Datos, Listener {
	private static IEconomy eco;
	private static boolean sql = false;
	private static Central central;

	public APIEconomy(JavaPlugin init) {
		ConfigFile conf = new ConfigFile(init.getDataFolder(), "Economy");
		FileConfiguration section = conf.getConfig();
		String n = "";
		if (section.isString("Estandar")) {
			n = section.getString("Estandar");
		}
		initEconomy(init, n);

		sql = API.getInstance().getSql().isEnable();

		if (section.isBoolean("SQL")) {
			sql = sql && section.getBoolean("SQL");
		}
		if (sql) {
			init.getLogger().info("APIE usara SQL");
			SQLEconomy.init();
			SqlEnable(init);
		} else {
			init.getLogger().info("APIE no usara SQL");
		}
	}

	@EventHandler
	public void logeIn(PlayerJoinEvent e) {
		if (sql) {
			String n = e.getPlayer().getName();
			double d = SQLEconomy.getMoney(n);
			eco.setCash(n, d);
		}
	}

	private void SqlEnable(JavaPlugin plug) {

		Bukkit.getServer().getPluginManager().registerEvents(this, plug);

		RetardedSQL run = new RetardedSQL();
		int idd = Bukkit.getScheduler().scheduleSyncRepeatingTask(plug, run,
				0L, 20L);
		run.setId(idd);

		runKill rune = new runKill() {
			Player[] l = Bukkit.getServer().getOnlinePlayers();
			int i = 0;

			@Override
			public void run() {
				if (l.length <= i) {
					kill();
				} else {
					String n = l[i].getName();
					double d = SQLEconomy.getMoney(n);
					eco.setCash(n, d);
				}
			}
		};
		int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(plug, rune,
				0L, 20L);
		rune.setId(id);

	}

	private void initEconomy(JavaPlugin ap, String n) {

		switch (n) {
		case "VaultEconomy":
			if (hasPlugin("net.milkbowl.vault.economy.Economy")) {
				eco = new VaultEconomy();
				ap.getLogger().info("APIEconomy usara Vault");
				return;
			}
			break;
		case "EssentialsEconomy":
			if (hasPlugin("com.earth2me.essentials.api.Economy")) {
				eco = new EssentialsEconomy();
				ap.getLogger().info("APIEconomy usara Essentials");
				return;
			}
			break;
		case "BOSEEconomy":
			if (hasPlugin("cosine.boseconomy.BOSEconomy")) {
				eco = new BOSEEconomy();
				ap.getLogger().info("APIEconomy usara BOSEconomy");
				return;
			}
			break;
		case "CraftEconomy":
			if (hasPlugin("com.greatmancode.craftconomy3.Common")) {
				eco = new CraftEconomyEconomy();
				ap.getLogger().info("APIEconomy usara CraftEconomy");
				return;
			}
			break;
		case "OwnEconomy":
			eco = new OwnEconomy(ap);
			API.getInstance().getCentral().insertar(this);
			ap.getLogger().info("APIEconomy usara su propia API");
			return;

		default:
			break;
		}

		if (hasPlugin("net.milkbowl.vault.economy.Economy")) {
			eco = new VaultEconomy();
			ap.getLogger().info("APIEconomy usara Vault");

		} else if (hasPlugin("com.earth2me.essentials.api.Economy")) {
			eco = new EssentialsEconomy();
			ap.getLogger().info("APIEconomy usara Essentials");

		} else if (hasPlugin("cosine.boseconomy.BOSEconomy")) {
			eco = new BOSEEconomy();
			ap.getLogger().info("APIEconomy usara BOSEconomy");

		} else if (hasPlugin("com.greatmancode.craftconomy3.Common")) {
			eco = new CraftEconomyEconomy();
			ap.getLogger().info("APIEconomy usara CraftEconomy");
		} else {
			eco = new OwnEconomy(ap);
			central = new Central(ap);
			central.insertar(this);
			ap.getLogger().info("APIEconomy usara su propia API");
		}
	}

	private static boolean hasPlugin(String s) {
		try {
			Class.forName(s);
			return true;
		} catch (Exception e) {

		}
		return false;
	}

	public static boolean setCash(String player, double Cash) {
		if (eco == null) {
			return false;
		}
		// SQLEconomy.updateMoney(player,Cash);
		if (sql) {
			RetardedSQL.add(player, Cash);
		}

		return eco.setCash(player, Cash);
	}

	public static boolean addCash(String player, double Cash) {
		if (eco == null) {
			return false;
		}
		// SQLEconomy.updateMoney(player,getCash(player)+Cash);
		if (sql) {
			RetardedSQL.add(player, Cash, apli.added);
		}

		return eco.addCash(player, Cash);
	}

	public static boolean reduceCash(String player, double Cash) {
		if (eco == null) {
			return false;
		}
		// SQLEconomy.updateMoney(player,getCash(player)-Cash);
		if (sql) {
			RetardedSQL.add(player, Cash, apli.reduce);
		}

		return eco.reduceCash(player, Cash);
	}

	public static double getCash(String player) {
		if (eco == null) {
			return 0;
		}

		return eco.getCash(player);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		return CommandEconomy.onCommand(sender, cmd, label, args);
	}

	@Override
	public void cargar(Almacen nbt) {
		if (eco instanceof OwnEconomy) {
			Almacen alm = nbt.getAlmacen("Economy");
			OwnEconomy c = (OwnEconomy) eco;
			c.cargar(alm);
			nbt.setAlmacen("Economy", alm);
		}
	}

	@Override
	public void guardar(Almacen nbt) {
		if (eco instanceof OwnEconomy) {
			Almacen alm = nbt.getAlmacen("Economy");
			OwnEconomy c = (OwnEconomy) eco;
			c.guardar(alm);
			nbt.setAlmacen("Economy", alm);
		}
	}

	public void onDisable() {
		if (central != null) {
			central.guardar();
		}
	}

}
