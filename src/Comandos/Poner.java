package Comandos;

import control.ControladorConsola;
import exceptions.DatosIncorrectos;
import exceptions.ErrorDeEjecucion;
import exceptions.MovimientoInvalido;

public class Poner implements Comandos{

	@Override
	public void execute(ControladorConsola control) throws ErrorDeEjecucion, MovimientoInvalido, DatosIncorrectos {
		control.poner();
	}

	@Override
	public Comandos parsea(String[] cadena) throws ErrorDeEjecucion{
		if (cadena[0].length() < 1)
			 throw new ErrorDeEjecucion("Introduzca algun dato");
		else if (cadena[0].equalsIgnoreCase("PONER"))
			return new Poner();
		else
			return null;
	}

	@Override
	public String textoAyuda() {
		return "PONER: utilizalo para poner la siguiente ficha.";
	}

}
