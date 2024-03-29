package me.khmdev.APIEconomy.Own;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.khmdev.APIBase.Almacenes.ConstantesAlmacen.typeVar;
import me.khmdev.APIBase.Almacenes.sql.Consulta;
import me.khmdev.APIBase.Almacenes.sql.varSQL;
import me.khmdev.APIBase.Almacenes.sql.player.SQLPlayerData;

public class SQLEconomy {
	private static final String celdaCash="cash";
	public static void updateMoney(String pl, double cash) {

		if (!SQLPlayerData.existUser(pl)) {
			SQLPlayerData.crearUser(pl);
		}
		SQLPlayerData.setVar(pl, celdaCash, cash);

	}

	public static double getMoney(String pl) {

		if (!SQLPlayerData.existUser(pl)) {
			SQLPlayerData.crearUser(pl);
		}
		Consulta cc = SQLPlayerData.getPlayer(pl);
		if(cc==null){return 0;}
		try {
			ResultSet player=cc.getR();
			double c = 0;

			if (player != null && player.next()) {
				c = player.getDouble(celdaCash);
			}
			return c;
		} catch (SQLException e) {
			return 0;
		}finally{
			cc.close();
		}

	}

	public static void init() {
		if (!SQLPlayerData.existVar(celdaCash)) {
			SQLPlayerData.addVars(new varSQL(celdaCash, typeVar.Double));
		}

	}
}
