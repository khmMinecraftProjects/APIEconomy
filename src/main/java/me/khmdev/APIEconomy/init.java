package me.khmdev.APIEconomy;


import me.khmdev.APIBase.Almacenes.local.ConfigFile;
import me.khmdev.APIEconomy.Own.APIEconomy;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class init extends JavaPlugin{
	private APIEconomy apie;
	public void onEnable() {
		if (!hasPluging("APIBase")) {
			getLogger().severe(
					getName()
							+ " se desactivo debido ha que no encontro la API");
			setEnabled(false);
			return;
		}
		apie=new APIEconomy(this);configure();
	}public static boolean active = false;

	private void configure() {
		ConfigFile conf = new ConfigFile(getDataFolder(), "config");
		FileConfiguration section = conf.getConfig();

		if (section.isBoolean("Activado")) {
			active = section.getBoolean("Activado");
		}
		section.set("Activado", true);
		conf.saveConfig();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if (apie.onCommand(sender, cmd, label, args)) {
			return true;
		}

		
		return false;
	}


	private static boolean hasPluging(String s) {
		try {
			return Bukkit.getPluginManager().getPlugin(s).isEnabled();
		} catch (Exception e) {

		}
		return false;
	}
	public void onDisable(){
		apie.onDisable();
	}
}
