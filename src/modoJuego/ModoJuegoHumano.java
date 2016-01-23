package modoJuego;

import control.ControladorGUI;

public class ModoJuegoHumano implements ModoJuego{

	private ControladorGUI c;
	private int fila;
	private int columna;
	
	public ModoJuegoHumano(ControladorGUI c){
		this.c = c;
	}
	
	@Override
	public void comenzar() {		
	}

	@Override
	public void terminar() {
		
	}

	@Override
	public void deshacerPulsado() {
		
	}
	
	public void tableroPulsado(int f,int col){
		this.fila = f;
		this.columna = col;
		c.poner(fila,columna);
	}

}
