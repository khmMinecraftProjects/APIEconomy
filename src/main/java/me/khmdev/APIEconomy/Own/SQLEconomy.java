package me.khmdev.APIEconomy.Own;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.khmdev.APIBase.Almacenes.ConstantesAlmacen.typeVar;
import me.khmdev.APIBase.Almacenes.SQLPlayerData;
import me.khmdev.APIBase.Almacenes.varSQL;

public class SQLEconomy {

	public static void updateMoney(String pl, double cash) {

		if (!SQLPlayerData.existUser(pl)) {
			SQLPlayerData.crearUser(pl);
		}
		SQLPlayerData.setVar(pl, "cash", cash);

	}

	public static double getMoney(String pl) {

		if (!SQLPlayerData.existUser(pl)) {
			SQLPlayerData.crearUser(pl);
		}
		try {
			ResultSet player = SQLPlayerData.getPlayer(pl);
			double c = 0;

			if (player != null && player.next()) {
				c = player.getDouble("cash");
			}
			return c;
		} catch (SQLException e) {
			return 0;
		}

	}

	public static void init() {
		if (!SQLPlayerData.existVar("cash")) {
			SQLPlayerData.addVars(new varSQL("cash", typeVar.Double));
		}

	}
}
