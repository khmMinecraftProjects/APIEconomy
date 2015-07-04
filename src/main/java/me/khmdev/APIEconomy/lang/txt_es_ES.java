package me.khmdev.APIEconomy.lang;

import java.util.*;

public class txt_es_ES extends ListResourceBundle {
	public Object[][] getContents() {
		return contenido;
	}

	private Object[][] contenido = {
			{
					"CommandEconomy.help",
					"/apie <Command>\n" + "Commands:     get\n"
							+ "              get (Player)\n"
							+ "              add (Player) (dinero)\n"
							+ "              set (Player) (dinero)\n"
							+ "              reduce (Player) (dinero)\n" },
			{ "CommandEconomy.get", "Tienes %cash%%UM%" },
			{ "CommandEconomy.getOther", "%pl% tiene %cash%%UM%" },
			{ "CommandEconomy.NoValues", "No se ha introducido valores" },
			{ "CommandEconomy.WrongValues", "Valor incorrecto" } };
}