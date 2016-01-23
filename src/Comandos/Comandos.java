package Comandos;

import control.ControladorConsola;
import exceptions.DatosIncorrectos;
import exceptions.ErrorDeEjecucion;
import exceptions.MovimientoInvalido;

public interface Comandos {

	/**
	 * Ejecuta el comando
	 * 
	 * @param control
	 * @throws ErrorDeEjecucion
	 * @throws MovimientoInvalido
	 * @throws DatosIncorrectos
	 */
	void execute(ControladorConsola control) throws ErrorDeEjecucion,
			MovimientoInvalido, DatosIncorrectos;

	/**
	 * Parsea la cadena de caracteres para descubrir que busca el usuario
	 * 
	 * @param cadena
	 * @return comando buscado o null si no se encuentra
	 * @throws ErrorDeEjecucion
	 */
	Comandos parsea(String[] cadena) throws ErrorDeEjecucion;

	/*
	 * Breve descripcion del comando
	 */
	String textoAyuda();
}
