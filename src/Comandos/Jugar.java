package Comandos;

import control.ControladorConsola;
import control.FactoriaC4;
import control.FactoriaComplica;
import control.FactoriaGravity;
import control.FactoriaReversi;
import control.FactoriaTipoJuego;
import exceptions.ErrorDeEjecucion;

public class Jugar implements Comandos {

	private FactoriaTipoJuego factoria;
	private boolean reiniciar = false;

	public Jugar(FactoriaTipoJuego factoria, boolean reinicia) {
		this.factoria = factoria;
		this.reiniciar = reinicia;
	}

	public Jugar() {
	}

	@Override
	public void execute(ControladorConsola control) throws ErrorDeEjecucion {
		if (reiniciar)
			control.reset(control.getFactoria());
		else
			control.reset(this.factoria);
	}

	@Override
	public Comandos parsea(String[] cadena) throws ErrorDeEjecucion {
		int x, y;
		if (cadena[0].length() < 1)
			throw new ErrorDeEjecucion("Introduzca algun dato");
		else if (cadena[0].equalsIgnoreCase("JUGAR")) {
			if (cadena[1].equalsIgnoreCase("C4")) {
				factoria = new FactoriaC4();
			} else if (cadena[1].equalsIgnoreCase("CO")) {
				factoria = new FactoriaComplica();
			} else if (cadena[1].equalsIgnoreCase("RV")) {
				factoria = new FactoriaReversi();
			} else if (cadena[1].equalsIgnoreCase("GR")) {
				if (cadena.length > 2) {
					x = Integer.parseInt(cadena[2]);
					y = Integer.parseInt(cadena[3]);
				} else {
					x = 10;
					y = 10;
				}
				factoria = new FactoriaGravity(x, y);
			} else
				throw new ErrorDeEjecucion("Error, juego " + cadena[1]
						+ " no encontrado");
			return new Jugar(factoria, false);

		} else if (cadena[0].equalsIgnoreCase("REINICIAR")) {
			return new Jugar(factoria, true);
		}
		return null;

	}

	@Override
	public String textoAyuda() {
		return "JUGAR [c4|co|gr|rv] [tamX tamY]: cambia el tipo de juego. "
				+ "\nREINICIAR: Reinicia la partida";
	}

}
