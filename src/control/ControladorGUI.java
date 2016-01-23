package control;


import logica.Partida;

public class ControladorGUI {
	
	private FactoriaTipoJuego factoria;
	private Partida partida;
	
	public ControladorGUI(FactoriaTipoJuego factoria,Partida partida){
		this.partida = partida;
		setFactoria(factoria);
	}
	

	
	/**
	 * situa una ficha en el tablero
	 * 
	 */
	public void poner(int fila, int columna) {

		this.partida.ejecutaMovimiento(this.factoria.creaMovimiento(fila, columna, this.partida.getTurno()));

	}

	/**
	 * Coloca una ficha en el tablero de forma aleatoria
	 */
	public void ponerAleatorio(){
		
		this.partida.getMovAutomatico(factoria);
				
		this.partida.ejecutaMovimiento(partida.getMovAutomatico(factoria));
		if(partida.tablas()){
			partida.setTerminada();
		}
		
	}
	
	/**
	 * Resetea el controlador 
	 * @param factoria
	 */
	public void reset(FactoriaTipoJuego factoria){
		this.setFactoria(factoria);
		this.partida.reset(factoria.creaReglas());
	}
	
	/**
	 * Cambia de juego
	 * @param factoria
	 */
	public void cambiarJuego(FactoriaTipoJuego factoria){
		this.setFactoria(factoria);
		this.partida.cambiarJuego(factoria.creaReglas());
	}
	
	/**
	 * Deshace el movimiento
	 */
	public void undo(){
		this.partida.undo();
	}	

	public Partida getPartida() {
		
		return this.partida;
	}

	public FactoriaTipoJuego getFactoria() {
		return factoria;
	}

	public void setFactoria(FactoriaTipoJuego factoria) {
		this.factoria = factoria;
	}

}
