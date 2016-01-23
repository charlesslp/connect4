package control;

import logica.Ficha;
import logica.Movimiento;
import logica.Partida;
import interfazGrafica.VistaConsola;

import java.util.Scanner;

import Comandos.Comandos;
import Comandos.ParserAyudaComandos;
import exceptions.DatosIncorrectos;
import exceptions.ErrorDeEjecucion;
import exceptions.MovimientoInvalido;

public class ControladorConsola {

	private Partida partida;
	private Scanner in;
	private FactoriaTipoJuego factoria;	
	private Jugador jugador1;
	private Jugador jugador2;

	/**
	 * Constructora para la clase Controlador
	 * 
	 * @param factoria
	 * @param partida
	 * @param in
	 */
	public ControladorConsola(FactoriaTipoJuego factoria, Partida partida, Scanner in) {
		this.partida = partida;
		this.in = in;
		this.setFactoria(factoria);
		this.jugador1 = factoria.creaJugadorHumano(this.in);
		this.jugador2 = factoria.creaJugadorHumano(this.in);
	}
	
	/**
	 * Ejecuta el Juego
	 */
	public void run() {

		String []comando = null;
		
		reset(factoria);
		
		// mientras la partida no termine
		while (!partida.partidaTerminada()) {
						
			// se seguira escribiendo el menu mientras el usuario escriba mal
			// los comandos
			boolean correcto = false;
			
			do {
				try{
					comando = VistaConsola.comando(this.in);
					accionMenu(comando);
					correcto = true;
				}
				catch (DatosIncorrectos e) {
					System.err.println(e.getMessage());
				}
				catch(MovimientoInvalido e){
					System.err.println(e.getMessage());
				}
				catch(ErrorDeEjecucion e){
					System.err.println(e.getMessage()+"\n");
				} 

			} while (!correcto);


		}
	}
	
	/**
	 * Ejecuta la opcion que ha introducido el usuario
	 * 
	 * @param comando
	 *            Lo que el usuario introdujo
	 * @return False si el usuario no introdujo un comando adecuado
	 * @throws DatosIncorrectos
	 */
	private void accionMenu(String[] comando) throws MovimientoInvalido, ErrorDeEjecucion, DatosIncorrectos {
		
		Comandos command = ParserAyudaComandos.parsea(comando);
		
		if(command != null)
			command.execute(this);
		else
			throw new ErrorDeEjecucion("datos incorrectos");
	}

	
	/**
	 * Crea un movimiento
	 * 
	 * @return movimiento
	 * @throws MovimientoInvalido
	 * @throws DatosIncorrectos
	 */
	private Movimiento crearMov() throws MovimientoInvalido, DatosIncorrectos {

		if(this.partida.getTurno()==Ficha.BLANCA)
			return this.jugador1.getMovimiento(this.getFactoria(), this.partida.getTablero(),Ficha.BLANCA);
		
		else
			return this.jugador2.getMovimiento(this.getFactoria(), this.partida.getTablero(),Ficha.NEGRA);
			
		
	}

	
	/**
	 * Crea un movimiento y devuelve errores en caso de fallo
	 * 
	 * @throws MovimientoInvalido
	 * @throws DatosIncorrectos
	 */
	public void poner() throws MovimientoInvalido, DatosIncorrectos{

		Movimiento movimiento = crearMov();
		
		this.partida.ejecutaMovimiento(movimiento);

	}
	

	/**
	 * Resetea el controlador 
	 * @param factoria
	 */
	public void reset(FactoriaTipoJuego factoria){
		this.setFactoria(factoria);
		this.partida.reset(factoria.creaReglas());
		this.jugador1 = factoria.creaJugadorHumano(this.in);
		this.jugador2 = factoria.creaJugadorHumano(this.in);
	}
	
	/**
	 * Cambia de jugador humano a automatico y viceversa
	 * 
	 * @param color
	 *            ficha actual
	 * @param modo
	 *            aleatorio o humano
	 */
	public void cambiarModoJugador(Ficha color, String modo) {
		if (color == Ficha.BLANCA && modo == "aleatorio" )
			this.jugador1 = this.getFactoria().creaJugadorAleatorio();
		else if(color == Ficha.NEGRA && modo == "aleatorio" )
			this.jugador2 = this.getFactoria().creaJugadorAleatorio();
		else if(color == Ficha.BLANCA && modo == "humano" )
			this.jugador1 = this.getFactoria().creaJugadorHumano(this.in);
		else if(color == Ficha.NEGRA && modo == "humano" )
			this.jugador2 = this.getFactoria().creaJugadorHumano(this.in);

		
	}
	
	/**
	 * Deshace el movimiento
	 */
	public void undo(){
		this.partida.undo();
	}

	/**
	 * Metodo de consulta de la factoria
	 * 
	 * @return factoria
	 */
	public FactoriaTipoJuego getFactoria() {
		return factoria;
	}


	/**
	 * Cambia el atributo factoria
	 * @param factoria
	 */
	public void setFactoria(FactoriaTipoJuego factoria) {
		this.factoria = factoria;
	}

	public Partida getPartida() {
		return this.partida;
	}
}
