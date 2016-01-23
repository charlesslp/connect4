package main;



import java.util.Scanner;

import org.apache.commons.cli.*;

import logica.Partida;
import control.ControladorConsola;
import control.ControladorGUI;
import control.FactoriaC4;
import control.FactoriaComplica;
import control.FactoriaGravity;
import control.FactoriaReversi;
import control.FactoriaTipoJuego;
import exceptions.ErrorDeEjecucion;
import interfazGrafica.VistaConsola;
import interfazGrafica.VistaGUI;

public class Main {
	
	private static FactoriaTipoJuego factoria;
	private static Scanner in = new Scanner(System.in);
	private static boolean open_in_window;
	
	public static void main(String[] args){
		
		VistaGUI vistaGUI;
		open_in_window = false; // por defecto el programa corre por consola
		factoria = new FactoriaC4();// factoría por defecto
		
		parseArguments(args);
		
		Partida partida = new Partida(factoria.creaReglas());
		
		if(open_in_window){
			
			ControladorGUI c = new ControladorGUI(factoria,partida);
			

			vistaGUI = new VistaGUI(c);
			partida.addObservador(vistaGUI);
		}
		else{
			
			
			ControladorConsola c = new ControladorConsola(factoria, partida, in);
			c.getPartida().addObservador(new VistaConsola(c));
			c.run();
		}
	}
	

	@SuppressWarnings("static-access")
	public static void parseArguments(String[] args) {
		
		Options commandLineOptions = new Options();
		CommandLineParser parser = new PosixParser();

		Option help = OptionBuilder.withArgName("help")
				.withDescription("muestra esta ayuda")// detalles
				.withLongOpt("help").create('h'); // nombre del argumento
		commandLineOptions.addOption(help);

		Option game = OptionBuilder.withArgName("game").hasArg(true)
				.withDescription("Tipo de juego (c4,co,gr,rv)") // detalles
				.withLongOpt("game").create('g'); // nombre del argumento
		commandLineOptions.addOption(game);
		
		Option columnas = OptionBuilder.withArgName("columnas").hasArg(true)
				.withDescription("Numero de columnas del tablero (solo para Gravity). Por defecto, 10.") // detalles
				.withLongOpt("tamX").create('x'); // nombre del argumento
		commandLineOptions.addOption(columnas);

		Option filas = OptionBuilder.withArgName("filas").hasArg(true)
				.withDescription("Numero de filas del tablero (solo para Gravity). Por defecto, 10.") // detalles
				.withLongOpt("tamY").create('y'); // nombre del argumento
		commandLineOptions.addOption(filas);
		
		Option ui = OptionBuilder.withArgName("ui").hasArg(true)
				.withDescription("Tipo de interfaz (console, window). Por defecto, console.") // detalles
				.withLongOpt("ui").create('u');
		commandLineOptions.addOption(ui);

		
		try {
			CommandLine line = parser.parse(commandLineOptions, args);

			if (line.hasOption("help")) {
				HelpFormatter hf = new HelpFormatter();
				hf.printHelp("ant", commandLineOptions);
				
				System.exit(0);
				
			} else if (line.hasOption("game")) {
				
				String juego = line.getOptionValue("game");
						
				if(juego.equalsIgnoreCase("gr")){
					
					int x = Integer.parseInt(line.getOptionValue("tamX","10")), 
						y = Integer.parseInt(line.getOptionValue("tamY", "10"));
					factoria = new FactoriaGravity(y, x);

				} else if (juego.equalsIgnoreCase("c4")) {
					factoria = new FactoriaC4();
				} else if (juego.equalsIgnoreCase("co")) {
					factoria = new FactoriaComplica();
				}else if(juego.equalsIgnoreCase("rv")){
					factoria = new FactoriaReversi();
				}
				else throw new ErrorDeEjecucion("Error, no se ha podido encontrar el juego " + juego);
			}
			if(line.hasOption("ui")){
				String tipo = line.getOptionValue("ui","console");
				if(tipo.equalsIgnoreCase("window"))
					open_in_window = true;
				
			}
			
		} catch (ParseException exp) {
			System.err.println("Ha habido un error. Razon: " + exp.getMessage());
		} catch (ErrorDeEjecucion e) {
			System.err.println(e.getMessage());
			System.exit(0);
		} 
	}
}
