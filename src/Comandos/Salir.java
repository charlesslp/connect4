package Comandos;

import control.ControladorConsola;
import exceptions.ErrorDeEjecucion;

public class Salir implements Comandos{
	


	@Override
	public void execute(ControladorConsola control) throws ErrorDeEjecucion {
		System.out.println("Has salido del juego");
		System.exit(0);		
	}

	@Override
	public Comandos parsea(String[] cadena) throws ErrorDeEjecucion{
		if (cadena[0].length() < 1)
			 throw new ErrorDeEjecucion("Introduzca algun dato");
		else if (cadena[0].equalsIgnoreCase("SALIR"))
			return new Salir();
		else
			return null;
	}

	@Override
	public String textoAyuda() {
		return "SALIR: termina la aplicacion.";
	}

}
