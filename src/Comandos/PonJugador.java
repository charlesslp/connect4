package Comandos;

import logica.Ficha;
import control.ControladorConsola;
import exceptions.ErrorDeEjecucion;

public class PonJugador implements Comandos{

	
	private Ficha color;
	 private String tipoJugador;;
	
	public PonJugador(Ficha color,String modo){
		this.color = color;
		this.tipoJugador = modo;
	}
	
	public PonJugador() {
	}

	@Override
	public void execute(ControladorConsola control) throws ErrorDeEjecucion {
		control.cambiarModoJugador(this.color, this.tipoJugador);
	}

	@Override
	public Comandos parsea(String[] cadena) throws ErrorDeEjecucion{
		
		
		if (cadena[0].length() < 1)
			 throw new ErrorDeEjecucion("Introduzca algun dato");
		else if(cadena[0].equalsIgnoreCase("JUGADOR")) {

			if (cadena[1].equalsIgnoreCase("BLANCAS")){
				this.color = Ficha.BLANCA;
			}
			else if (cadena[1].equalsIgnoreCase("NEGRAS")){
				this.color = Ficha.NEGRA;
			}
			else throw new ErrorDeEjecucion("Elige un color valido");
				
			if(cadena[2].equalsIgnoreCase("ALEATORIO"))
				this.tipoJugador = "aleatorio";
			else if(cadena[2].equalsIgnoreCase("HUMANO"))
				this.tipoJugador = "humano";
			else throw new ErrorDeEjecucion("Introduce un modo de jugador valido");
			
			return new PonJugador(this.color,this.tipoJugador);
		}
			
		else
			return null;
	}

	@Override
	public String textoAyuda() {
		return "JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador.";
	}

}
