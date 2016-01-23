package interfazGrafica;

import java.util.Scanner;

import control.ControladorConsola;
import logica.Ficha;
import logica.TableroSoloLectura;
import exceptions.MovimientoInvalido;

public class VistaConsola implements Observador{
	
	private ControladorConsola c;
	
	public VistaConsola(ControladorConsola c){
		this.c = c;
	}
	
	/*
	 * dibuja el tablero
	 */
	public static void pintaTablero(TableroSoloLectura tab){
		System.out.println(tab.toString());
	}
	


	@Override
	public void onMovimientoStart(Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(TableroSoloLectura tab, Ficha turno) {
		System.out.println("Juegan " + enumeradoAString(turno));
		pintaTablero(tab);
	}

	@Override
	public void onPartidaTerminada(TableroSoloLectura tablero, Ficha ganador) {
		
		if(c.getPartida().getGanador())
			System.out.println("Han ganado las " + enumeradoAString(ganador) + ": ");
		else
			System.out.println("Partida terminada en tablas");
		
		pintaTablero(tablero);
	
	}

	// Metodo no necesario en este observador
	@Override
	public void onCambioJuego(TableroSoloLectura tab, Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUndoNotPossible() {
		System.err.println("Imposible deshacer");
		
	}

	@Override
	public void onUndo(TableroSoloLectura tablero, Ficha turno, boolean hayMas) {
		pintaTablero(tablero);
		System.out.println("Juegan " + enumeradoAString(turno));
		
	}

	@Override
	public void onMovimientoEnd(TableroSoloLectura tablero, Ficha jugador,
			Ficha turno) {
		pintaTablero(tablero);
		System.out.println("Juegan " + enumeradoAString(turno));
		
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		System.err.println(movimientoException.getMessage());
	}
	
	/**
	 * cambia de enumerado a String el turno de la partida
	 * 
	 * @return string del turno
	 */
	private String enumeradoAString(Ficha ficha) {

		String turno = "";

		switch (ficha){

		case BLANCA:
			turno = "blancas";
			break;
		case NEGRA:
			turno = "negras";
			break;
		case VACIA:
			;// no puede darse el caso, "vacia" no puede ser un turno
			break;
		}
		return turno;
	}

	/**
	 * Recibe el comando del usuario
	 * 
	 * @param in
	 * @return comando introducido
	 */
	public static String[] comando(Scanner in){
		
		String comando[] = new String[10];
		
		System.out.println("Que quieres hacer?: ");
		comando = in.nextLine().split(" ");
		return comando;
		
	}


}
