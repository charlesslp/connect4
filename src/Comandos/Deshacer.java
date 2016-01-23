package Comandos;

import control.ControladorConsola;
import exceptions.ErrorDeEjecucion;

public class Deshacer implements Comandos{

	@Override
	public void execute(ControladorConsola control) throws ErrorDeEjecucion {
		control.undo();
	}

	

	@Override
	public String textoAyuda() {
		return "DESHACER: deshace el ultimo movimiento hecho en la partida.";
	}

	@Override
	public Comandos parsea(String[] cadena) throws ErrorDeEjecucion{
		if  (cadena[0].length() < 1)
			 throw new ErrorDeEjecucion("Introduzca algun dato");
		else if (cadena[0].equalsIgnoreCase("DESHACER"))
			return new Deshacer();
		else
			return null;
	}
	

}
