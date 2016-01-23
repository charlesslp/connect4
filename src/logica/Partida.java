package logica;


import interfazGrafica.Observador;

import java.util.ArrayList;
import java.util.Stack;

import control.FactoriaTipoJuego;
import control.Jugador;
import exceptions.MovimientoInvalido;
import logica.Tablero;

public class Partida implements Observable{


	private ReglasJuego reglas;
	private Tablero tablero;
	private Ficha turno;
	private boolean terminada;
	private boolean ganador;
	private TableroSoloLectura tableroSoloLectura;
	private Stack <Movimiento> undoStack;
	
	// atributo para gestionar observadores
	private ArrayList<Observador> obs;
	

	/**
	 * Constructora de la partida
	 */
	public Partida(ReglasJuego reglas) {

		this.terminada = false;
		this.ganador = false;
		this.undoStack = new Stack <Movimiento>();
		this.obs = new ArrayList<Observador>();
		this.reglas = reglas;
		this.tablero = reglas.iniciaTablero();
		this.turno = reglas.jugadorInicial();
		this.tableroSoloLectura = this.tablero;
	}

	/**
	 * Deshace el ultimo movimiento realizado
	 * 
	 * @return true si se ha deshecho correctamente
	 */
	public void undo(){

		Movimiento mv;

		if (!this.undoStack.isEmpty()) {

			mv = this.undoStack.pop();
			mv.undo(this.tablero);
			this.turno = mv.getJugador();

			for (Observador o : obs) {
				o.onUndo(tableroSoloLectura, turno, !this.undoStack.isEmpty());
			}
			for (Observador o : this.obs) {
				o.onMovimientoStart(turno);
			}
		} else {
			for (Observador o : obs) {
				o.onUndoNotPossible();
			}
		}

	}

	/**
	 * resetea la partida entera
	 */
	public void reset(ReglasJuego reglas) {
		this.reglas = reglas;
		this.tablero = reglas.iniciaTablero();
		this.turno = reglas.jugadorInicial();
		this.terminada = false;
		this.tableroSoloLectura = this.tablero;
		this.undoStack = new Stack <Movimiento>();
		for(Observador o: obs){
			o.onReset(this.tableroSoloLectura, this.turno);
		}
		for (Observador o : this.obs) {
			o.onMovimientoStart(turno);
		}
	}
	
	
	
	/**
	 * Cambia el juego actual
	 * 
	 * @param reglas
	 *            reglas del siguiente juego
	 */
	public void cambiarJuego(ReglasJuego reglas) {
		this.reglas = reglas;
		this.tablero = reglas.iniciaTablero();
		this.turno = reglas.jugadorInicial();
		this.terminada = false;
		this.tableroSoloLectura = this.tablero;
		this.undoStack = new Stack<Movimiento>();
		for (Observador o : obs) {
			o.onCambioJuego(this.tableroSoloLectura, this.turno);
		}
		for (Observador o : this.obs) {
			o.onMovimientoStart(turno);
		}

	}

	/**
	 * Realiza cierto movimiento en la partida
	 * 
	 * @param mv
	 *            movimiento
	 */
	public void ejecutaMovimiento(Movimiento mv) {
	
		Ficha ganador;
		try {
			if (this.terminada)
				throw new MovimientoInvalido("Partida ya terminada.");
			if (mv.getJugador() != this.getTurno())
				throw new MovimientoInvalido("Turno incorrecto.");

			mv.ejecutaMovimiento(this.tablero);

			for (Observador o : this.obs) {
				o.onMovimientoEnd(tableroSoloLectura, turno,
						this.reglas.siguienteTurno(this.turno,this.tableroSoloLectura));
			}


			ganador = this.reglas.hayGanador(mv.getFila(), mv.getColumna(),
					mv.getJugador(), this.tablero);

			if (ganador != Ficha.VACIA) {// si hay ganador
				setGanador();
				setTerminada();
				this.turno = ganador;
				for (Observador o : this.obs) {
					o.onPartidaTerminada(this.tableroSoloLectura, this.turno);
				}
			} else {
				this.turno = this.reglas.siguienteTurno(this.turno,this.tableroSoloLectura);
				this.undoStack.add(mv);

				if (tablas()) {
					setTerminada();
					for (Observador o : this.obs) {
						o.onPartidaTerminada(this.tableroSoloLectura, Ficha.VACIA);
					}
				}
				
				/*
				 * En el caso de que en el Reversi, ninguno de los dos jugadores pueda mover
				 * (aunque el tablero no esté completo) la funcion siguienteTurno() devolvera una Ficha.VACIA
				 * y debemos acabar el juego y decir quien de los dos ha ganado
				 */
				if(this.turno == Ficha.VACIA){
					
					//El metodo ganadorImposibleMover() cuenta las fichas de cada jugador y devuelve quien
					//tiene mas fichas, queden o no casillas vacias
					ganador = this.reglas.ganadorImposibleMover(this.tableroSoloLectura);
					
					setGanador();
					setTerminada();
					this.turno = ganador;
					for (Observador o : this.obs) {
						o.onPartidaTerminada(this.tableroSoloLectura, this.turno);
					}
				}
				
			}
			if(!this.terminada){
				for (Observador o : this.obs) {
					o.onMovimientoStart(turno);
				}
			}

		} catch (MovimientoInvalido e) {
			for (Observador o : this.obs) {
				o.onMovimientoIncorrecto(e);
			}
		}
	}

	/**
	 * A partir del tablero comprueba si hay tablas
	 * 
	 * @return true si hay tablas
	 */
	public boolean tablas() {
		return this.reglas.tablas(this.tablero);
	}

	@Override
	public void addObservador(Observador o) {
		if(!obs.contains(o)){
			obs.add(o);
		}
		
	}
	
	@Override
	public void removeObservador(Observador o){
		obs.remove(o);
	}
	
	

	/**
	 * Metodo de consulta del turno de la partida
	 * 
	 * @return Turno
	 */
	public Ficha getTurno() {
		return this.turno;
	}

	/**
	 * Metodo de consulta del tablero de la partida
	 * 
	 * @return Tablero
	 */
	public Tablero getTablero() {
		return this.tablero;
	}

	/**
	 * Metodo de consulta del fin de la partida
	 * 
	 * @return devuelve el booleano terminada
	 */
	public boolean partidaTerminada() {
		return this.terminada;
	}

	/**
	 * Metodo de consulta del ganador
	 * 
	 * @return ganador
	 */
	public boolean getGanador() {
		return this.ganador;
	}

	/**
	 * Metodo de consulta de las reglas
	 * 
	 * @return reglas
	 */
	public ReglasJuego getReglas() {
		return this.reglas;
	}

	/**
	 * Metodo de consulta de tablero () {completo
	 * 
	 * @return si esta lleno el tablero
	 */
	public boolean getCompleto() {
		return tablero.completo();
	}

	/**
	 * Metodo de consulta de terminada
	 * 
	 * @return si la partida ha terminado
	 */
	public boolean getTerminada(){
		return this.terminada;
	}

	/**
	 * Pone el parametro de partida terminada a true
	 */
	public void setTerminada() {
		this.terminada = true;
	}

	/**
	 * Coloca a true el parametro ganador
	 */
	private void setGanador() {
		this.ganador = true;
	}


	
	public TableroSoloLectura getTableroSoloLectura(){
		return this.tableroSoloLectura;
	}
	
	public Movimiento getMovAutomatico(FactoriaTipoJuego factoria){
		
		Movimiento mv;
		Jugador jugador = factoria.creaJugadorAleatorio();
		jugador.obtenFilaColumna(this.tablero, this.turno);
		mv = factoria.creaMovimiento(jugador.fila, jugador.columna, this.turno);
		
		return mv;
	}
	

}