package Comandos;

import control.ControladorConsola;
import exceptions.ErrorDeEjecucion;



public class Ayuda implements Comandos {

	@Override
	public String textoAyuda() {
		return "AYUDA: Muestra la ayuda";
	}

	@Override
	public Comandos parsea(String[] cadena) throws ErrorDeEjecucion{
		if (cadena[0].length() < 1)
			 throw new ErrorDeEjecucion("Introduzca algun dato");
		else if (cadena[0].equalsIgnoreCase("AYUDA"))
			return new Ayuda();
		else
			return null;
	}

	@Override
	public void execute(ControladorConsola control) throws ErrorDeEjecucion {
		System.out.println(ParserAyudaComandos.AyudaComandos());
	}
}
