package interfazGrafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import control.ControladorGUI;
import control.FactoriaC4;
import control.FactoriaComplica;
import control.FactoriaGravity;
import control.FactoriaReversi;
import control.FactoriaTipoJuego;
import logica.Ficha;
import logica.TableroSoloLectura;
import modoJuego.ModoJuego;
import modoJuego.ModoJuegoAutomatico;
import modoJuego.ModoJuegoHumano;
import modoJuego.TipoTurno;
import exceptions.MovimientoInvalido;


public class VistaGUI extends JFrame implements Observador{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String[] JUEGOS = {"CONECTA 4","GRAVITY","COMPLICA","REVERSI"};
	
	private Container root;
	private ControladorGUI c;
	private ModoJuego modoBlancas;
	private ModoJuego modoNegras;
	
	// paneles de la ventana
	private JPanel panelIzqdo;
	private JPanel pIArriba;
	private JPanel pIATablero;
	private JPanel pIALabel;
	private JPanel pIAbajo;
	private JPanel panelDcho;
	private JPanel pDAPartida;
	private JPanel pDAJugadores;
	private JPanel pDAJuego;
	private JPanel pDAbajo;
	private JPanel panelDimensiones;
	
	// botones
	private Casilla botones[][];
	private JButton botonAleatorio;
	private JButton deshacer;
	private JButton reiniciar;
	private JButton salir;
	private JButton cambiar;
	private JButton todosAHumano;
	private JButton todosAutomatico;
	
	// componentes extra
	private Label labelInfoUsuario;
	private JComboBox<String> tipoJuego;
	private JComboBox<TipoTurno> jugadorBlancas;
	private JComboBox<TipoTurno> jugadorNegras;
	private JLabel filas;
	private JLabel columnas;
	private JTextField filasArea;
	private JTextField columnasArea;
	
	
	// imagenes
	private ImageIcon imagenSalir;
	private ImageIcon imagenDeshacer;
	private ImageIcon imagenReiniciar;
	private ImageIcon imagenAleatorio;
	private ImageIcon imagenCambiar;
	
	public VistaGUI(ControladorGUI c) {
		super("Juegos familia 4");
		this.c = c;
		panelIzqdo = new JPanel();
		pIArriba = new JPanel();
		pIATablero = new JPanel();
		pIALabel = new JPanel();
		pIAbajo = new JPanel();
		panelDcho = new JPanel();
		pDAPartida = new JPanel();
		pDAJugadores = new JPanel();
		pDAJuego = new JPanel();
		pDAbajo = new JPanel();
		panelDimensiones = new JPanel();
		
		modoBlancas = new ModoJuegoHumano(c);
		modoNegras = new ModoJuegoHumano(c);
		
		
		root = this.getContentPane();
		root.setLayout(new GridLayout(1, 2));
		root.add(panelIzquierdo());
		root.add(panelDerecho());
		implementListeners();
		
		
		this.setSize(850, 580);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	
	
	
	private JPanel panelIzquierdo() {

		panelIzqdo.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0; 
		constraints.gridy = 0; 
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 40;
		constraints.weightx = 1.0;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill = GridBagConstraints.BOTH;

		panelIzqdo.add(pIzqArriba(c.getPartida().getTableroSoloLectura(),c.getPartida().getTurno()),constraints);
		
		constraints.gridx = 0; 
		constraints.gridy = 1; 
		constraints.gridwidth = 2;
		constraints.gridheight = 2;
		constraints.weighty = 1.0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(30,110,30,110);


		panelIzqdo.add(pIzqAbajo(),constraints);

		return panelIzqdo;
	}
	
	
	

	private JPanel pIzqArriba(TableroSoloLectura tablero, Ficha turno) {

		pIArriba.setLayout(new BorderLayout());
		pIArriba.add(pIArribaTablero(tablero),BorderLayout.CENTER);
		pIArriba.add(pIArribaLabel(turno),BorderLayout.SOUTH);

		pIArriba.setBorder(BorderFactory.createRaisedBevelBorder());		
		return pIArriba;
	}
	
	
	

	private JPanel pIArribaTablero(TableroSoloLectura t) {

		pIATablero.setLayout(new GridLayout(t.getFilas(), t.getColumnas()));
		pIATablero.setBorder(BorderFactory.createEtchedBorder());
		
		
		this.botones = new Casilla[t.getFilas()][ t.getColumnas()];
		Casilla boton = null;

		for (int i = 0; i < t.getFilas(); i++) {
			for (int j = 0; j <  t.getColumnas(); j++) {
				switch (t.getFicha(i, j)) {
				case VACIA: {
					boton = new Casilla(i,j,Ficha.VACIA);
					boton.setBackground(Color.orange);
					break;
				}
				case NEGRA: {
					boton = new Casilla(i,j,Ficha.NEGRA);
					boton.setBackground(Color.BLACK);
					break;
				}

				case BLANCA: {
					boton = new Casilla(i,j,Ficha.BLANCA);
					boton.setBackground(Color.WHITE);
					break;
				}

				}
				pIATablero.add(boton);
				botones[i][j] = boton;
			}
		}

		return pIATablero;
	}
	
	
	

	private JPanel pIArribaLabel(Ficha turno) {
				
		this.labelInfoUsuario = new Label("Juegan " + enumeradoAString(turno));
		//Aumenta el tamano de la fuente
		this.labelInfoUsuario.setFont(new Font("sherif", Font.PLAIN, 28));
		pIALabel.add(labelInfoUsuario);

		return pIALabel;

	}
	
	

	private JPanel pIzqAbajo() {
		
		this.botonAleatorio = new JButton("Poner aleatorio");
		java.net.URL url_aleatorio = null;
		url_aleatorio = VistaGUI.class.getResource("imagenes/aleatorio.png");
		if(url_aleatorio!=null)
			this.imagenAleatorio = new ImageIcon(url_aleatorio);
		this.botonAleatorio.setIcon(imagenAleatorio);
		
		pIAbajo.setLayout(new BorderLayout());
		pIAbajo.add(botonAleatorio,BorderLayout.CENTER);
		

		return pIAbajo;
	}
	
	
	

	private JPanel panelDerecho() {
		
		
		panelDcho.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 0.025;
		constraints.weightx = 2.0;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill = GridBagConstraints.BOTH;

		
		constraints.insets = new Insets(30,50,20,50);

		panelDcho.add(pDchaAPartida(),constraints);
		
		constraints.gridx = 0; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 0.025;
		constraints.weightx = 2.0;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill = GridBagConstraints.BOTH;

		
		constraints.insets = new Insets(15,30,15,30);

		panelDcho.add(pDAJugadores(),constraints);
		
		constraints.insets = new Insets(0,15,30,10);
		constraints.gridx = 0; 
		constraints.gridy = 2; 
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 2;
		constraints.weightx = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.BOTH;


		panelDcho.add(pDchaAJuego(),constraints);
		
		
		constraints.gridx = 0; 
		constraints.gridy = 3; 
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 0;
		constraints.weightx = 2.0;
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(10,110,30,110);
		
		panelDcho.add(pDchaAbajo(),constraints);
		
		
		return panelDcho;
	}


	private JPanel pDchaAPartida() {
		
		pDAPartida.setBorder(BorderFactory.createTitledBorder("Partida"));
	    
		pDAPartida.setLayout(new GridLayout(1,2));
		this.deshacer = new JButton("Deshacer");
		java.net.URL url_deshacer = null;
		url_deshacer = VistaGUI.class.getResource("imagenes/undo.png");
		if(url_deshacer!=null)
			this.imagenDeshacer = new ImageIcon(url_deshacer);
		this.deshacer.setIcon(imagenDeshacer);
		
		deshacer.setEnabled(false);
		pDAPartida.add(deshacer);
		
		
		this.reiniciar = new JButton("Reiniciar");
		java.net.URL url_reiniciar = null;
		url_reiniciar = VistaGUI.class.getResource("imagenes/reiniciar.png");
		if(url_reiniciar!=null)
			this.imagenReiniciar = new ImageIcon(url_reiniciar);
		this.reiniciar.setIcon(imagenReiniciar);
		pDAPartida.add(reiniciar);
		
		return pDAPartida;
	}




	private JPanel pDchaAJuego() {
		
		pDAJuego.setLayout(new GridBagLayout());
		pDAJuego.setBorder(BorderFactory.createTitledBorder("Cambio de juego"));
		
		this.tipoJuego = new JComboBox<String>();
		for (int i = 0; i < JUEGOS.length; i++)
			tipoJuego.addItem(JUEGOS[i]);
		filas = new JLabel("       Filas: ");
		columnas = new JLabel("    Columnas: ");
		filasArea = new JTextField(1);
		columnasArea = new JTextField(1);
		
		panelDimensiones.setLayout(new GridLayout(1,5));
		panelDimensiones.add(filas);
		panelDimensiones.add(filasArea);
		panelDimensiones.add(columnas);
		panelDimensiones.add(columnasArea);
		panelDimensiones.add(new Panel());//panelVacio
		panelDimensiones.setVisible(false);
		
		this.cambiar = new JButton("Cambiar");
		java.net.URL url_cambiar = null;
		url_cambiar = VistaGUI.class.getResource("imagenes/cambiar.png");
		if(url_cambiar!=null)
			this.imagenCambiar = new ImageIcon(url_cambiar);
		this.cambiar.setIcon(imagenCambiar);
		
		
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 0;
		constraints.weightx = 1;
		constraints.insets = new Insets(0,0,3,0);
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill = GridBagConstraints.BOTH;
		pDAJuego.add(tipoJuego,constraints);

		constraints.gridx = 0; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.insets = new Insets(10,0,10,0);
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.BOTH;
		pDAJuego.add(panelDimensiones,constraints);

		constraints.gridx = 0; 
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 0;
		constraints.weightx = 1;
		constraints.insets = new Insets(3,0,0,0);
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.fill = GridBagConstraints.NONE;
		pDAJuego.add(cambiar,constraints);
				
		return pDAJuego;
	}

	private JPanel pDAJugadores() {

		JLabel jBlancas = new JLabel("jugador de blancas"), jNegras = new JLabel(
				"jugador de negras");

		pDAJugadores.setLayout(new GridLayout(3, 2));

		pDAJugadores.setBorder(BorderFactory
				.createTitledBorder("Gestion de jugadores"));

		this.jugadorBlancas = new JComboBox<TipoTurno>();
		this.jugadorBlancas.addItem(TipoTurno.HUMANO);
		this.jugadorBlancas.addItem(TipoTurno.AUTOMATICO);
		this.jugadorNegras = new JComboBox<TipoTurno>();
		this.jugadorNegras.addItem(TipoTurno.HUMANO);
		this.jugadorNegras.addItem(TipoTurno.AUTOMATICO);

		pDAJugadores.add(jBlancas);
		pDAJugadores.add(this.jugadorBlancas);
		pDAJugadores.add(jNegras);
		pDAJugadores.add(this.jugadorNegras);
		todosAHumano = new JButton("Ambos a humano");
		pDAJugadores.add(todosAHumano);
		todosAutomatico = new JButton("Ambos automatico");
		pDAJugadores.add(todosAutomatico);

		return pDAJugadores;
	}

	private JPanel pDchaAbajo() {
		
		pDAbajo.setLayout(new BorderLayout());
		java.net.URL url_salir = null;
		url_salir = VistaGUI.class.getResource("imagenes/salir.png");
		if(url_salir!=null)
			this.imagenSalir = new ImageIcon(url_salir);
		this.salir = new JButton("Salir");
		this.salir.setIcon(imagenSalir);
		
		pDAbajo.add(salir,BorderLayout.CENTER);
		
		return pDAbajo;
	}


	//_____________________________________________________________________________________________________________
	

	public void onMovimientoStart(Ficha turno) {
		
		if(turno == Ficha.BLANCA && jugadorBlancas.getSelectedIndex() == 1)
			bloquearTablero();
		else if (turno == Ficha.NEGRA && jugadorNegras.getSelectedIndex() == 1)
			bloquearTablero();
		
		if(turno == Ficha.BLANCA)
			modoBlancas.comenzar();
		else
			modoNegras.comenzar();

	}
	
	@Override
	public void onReset(final TableroSoloLectura tab, final Ficha turno) {
		
		modoBlancas.terminar();
		modoNegras.terminar();
	
		desbloquearTablero();

		jugadorNegras.setSelectedIndex(0);
		jugadorBlancas.setSelectedIndex(0);
		labelInfoUsuario.setText("Juegan " + enumeradoAString(turno));
		actualizarBotones(tab);
		botonAleatorio.setEnabled(true);
		deshacer.setEnabled(false);

		// escrito para evitar errores
		tipoJuego.revalidate();


	}



	@Override
	public void onPartidaTerminada(TableroSoloLectura tablero, Ficha ganador) {

		modoBlancas.terminar();
		modoNegras.terminar();
		
		String mensaje = "";

		if (ganador == Ficha.VACIA)
			mensaje = "Partida terminada en tablas";
		else
			mensaje = "Ganan " + enumeradoAString(ganador) + "!";

		this.labelInfoUsuario.setText(mensaje);
		// informa al usuario
		JOptionPane.showMessageDialog(null, mensaje,"Fin de la partida",JOptionPane.INFORMATION_MESSAGE);		
		this.botonAleatorio.setEnabled(false);
		this.deshacer.setEnabled(false);
		
	}


	@Override
	public void onCambioJuego(TableroSoloLectura tab, Ficha turno) {

		modoBlancas.terminar();
		modoNegras.terminar();

		
		
		this.pIArriba.remove(pIATablero);
		
		pIATablero = new JPanel(); //Crea un nuevo panel
		
		this.pIArriba.add(pIArribaTablero(tab));
		
		this.labelInfoUsuario.setText("Juegan " + enumeradoAString(turno));
		actualizarBotones(tab);
		implementListenersBotones();
		this.botonAleatorio.setEnabled(true);
		this.deshacer.setEnabled(false);
		
		jugadorNegras.setSelectedIndex(0);
		jugadorBlancas.setSelectedIndex(0);
		// escrito para evitar errores
		this.tipoJuego.revalidate();

		}

	
	@Override
	public void onUndoNotPossible() {

			
	}


	@Override
	public void onUndo(TableroSoloLectura tablero, Ficha turno, boolean hayMas) {
		
		modoBlancas.terminar();
		modoNegras.terminar();
	
		this.labelInfoUsuario.setText("Juegan " + enumeradoAString(turno));
		actualizarBotones(tablero);
		
		// escrito para evitar errores
		this.tipoJuego.revalidate();

		if(!hayMas) // si no quedan mas movimientos en la pila, inhabilita el boton deshacer
			this.deshacer.setEnabled(false);
		else{
			if(turno == Ficha.BLANCA && jugadorBlancas.getSelectedIndex() == 0)
				this.deshacer.setEnabled(true);
			else if (turno == Ficha.NEGRA && jugadorNegras.getSelectedIndex() == 0)
					this.deshacer.setEnabled(true);
		}
		
		/*
		 * Hemos pensado que lo más logico cuando un jugador humano pulsa el deshacer es
		 * que, en el caso de que esté jugando contra un jugador automatico, no solo deshaga
		 * el movimiento del automático sino tambien el del propio jugador.
		 * 
		 * Esto se debe a que al haber implementado un modo automático es inteligente, cada vez que el humano
		 * pulse deshacer, el jugador automático volvera a poner exactamente en el mismo sitio
		 * con lo cual el boton deshacer pierde toda su funcionalidad y se convierte en un boton inutil.
		 */
		
		if(turno == Ficha.BLANCA)
			modoBlancas.deshacerPulsado();
		else
			modoNegras.deshacerPulsado();
		
	}




	@Override
	public void onMovimientoEnd(final TableroSoloLectura tablero, Ficha jugador, final Ficha turno) {
		SwingUtilities.invokeLater(new Runnable(){
				
				public void run(){

					if(!c.getPartida().getTerminada()) //si la partida ha terminado no actualizamos el Label
						labelInfoUsuario.setText("Juegan " + enumeradoAString(turno));
					actualizarBotones(tablero);
					
					if(turno == Ficha.BLANCA && jugadorBlancas.getSelectedIndex() == 0)
						desbloquearTablero();
					else if(turno == Ficha.NEGRA && jugadorNegras.getSelectedIndex() == 0)
						desbloquearTablero();
					// escrito para evitar errores
					tipoJuego.revalidate();
				}
			});
	}




	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		JOptionPane.showMessageDialog(null, movimientoException.getMessage(),"Movimiento invalido",JOptionPane.WARNING_MESSAGE);
	}
	
	
	
	
	// LISTENERS
	//_____________________________________________________________________________________________________________
	
	/*
	 * Implementa todos los listeners de la ventana
	 */
	private void implementListeners(){
		this.salir.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showOptionDialog(null, "Estas seguro de que quieres salir?", "Confirmacion de salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if(n == 0)
					System.exit(0);
			}
			
		});
		
		this.botonAleatorio.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				c.ponerAleatorio();

			}
			
		});
		
		
		this.tipoJuego.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// solo seran visibles si el gravity es la opcion seleccionada del comboBox
				if(tipoJuego.getSelectedIndex()==1)
					panelDimensiones.setVisible(true);
				else
					panelDimensiones.setVisible(false);
				// escrito para evitar errores
				panelDimensiones.revalidate();
			}
			
		});

		
		this.deshacer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				c.getPartida().undo();
				
			}
			
		});


		this.reiniciar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				c.getPartida().reset(c.getFactoria().creaReglas());

			}
			
		});

		// Se ejecuta cuando cambia el Item seleccionado en el comboBox del jugador de blancas
		this.jugadorBlancas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (!c.getPartida().getTerminada()) { //Si la partida ya ha terminado, no se debe llamar a ninguno de los siguientes metodos
					if (jugadorBlancas.getSelectedIndex() == 0) {
						modoBlancas = new ModoJuegoHumano(c);
						if (c.getPartida().getTurno() == Ficha.BLANCA)
							desbloquearTablero();
					} else {
						modoBlancas = new ModoJuegoAutomatico(c);
						if (c.getPartida().getTurno() == Ficha.BLANCA) {
							bloquearTablero();
							
							modoBlancas.comenzar(); // En cuanto se seleccione el modo automatico
													// (en el caso de que el turno sea de las blancas) se ejecuta
													// el metodo de poner
						}
					}
				}
				
			}
		
		});
		

		// Se ejecuta cuando cambia el Item seleccionado en el comboBox del jugador de negras
		this.jugadorNegras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!c.getPartida().getTerminada()) {
					if (jugadorNegras.getSelectedIndex() == 0) {
						modoNegras = new ModoJuegoHumano(c);
						if (c.getPartida().getTurno() == Ficha.NEGRA)
							desbloquearTablero();
					} else {
						modoNegras = new ModoJuegoAutomatico(c);
						if (c.getPartida().getTurno() == Ficha.NEGRA) {
							bloquearTablero();

							modoNegras.comenzar();  // En cuanto se seleccione el modo automatico
													// (en el caso de que el turno sea de las negras) se ejecuta
													// el metodo de poner
						}
					}
				}

			}

		});
		
		//Cambiar los dos jugadores a Jugadores Humanos a la vez
		this.todosAHumano.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				jugadorNegras.setSelectedIndex(0);	
				jugadorBlancas.setSelectedIndex(0);
				
			}
		
		});
		
		//Cambiar los dos jugadores a Jugadores Automáticos a la vez
		this.todosAutomatico.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				jugadorNegras.setSelectedIndex(1);	
				jugadorBlancas.setSelectedIndex(1);
				
			}
		
		});

		this.cambiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int i, j; // Nuevas dimensiones del tablero (Gravity)
				boolean todoCorrecto = true;
				FactoriaTipoJuego factoriaNueva = null;

				String juego = (String) tipoJuego.getSelectedItem(); // selecciona
																		// el
																		// item
																		// del
																		// comboBox

				switch (juego) {
				case "CONECTA 4": // inicia un juego conecta 4
					factoriaNueva = new FactoriaC4();
					break;
				case "GRAVITY": // inicia un juego gravity
					try {
						i = Integer.parseInt(filasArea.getText()); // obtiene
																	// los
																	// numeros
																	// que
																	// introduce
																	// el
																	// usuario
						j = Integer.parseInt(columnasArea.getText());
						if (i > 0 && j > 0) // solo son validos si son positivos
							if (i > 20 || j > 20) { // si hace un tablero
													// demasiado grande (evita
													// que colapse el juego)
								int n = JOptionPane
										.showOptionDialog(
												null,
												"Valores superiores a 20. \nDesea usar el tablero por defecto?",
												"Valores no reconocidos",
												JOptionPane.YES_NO_OPTION,
												JOptionPane.QUESTION_MESSAGE,
												null, null, null);
								if (n == 0) { // el usuario elige usar el
												// tablero por defecto
									filasArea.setText("10");
									columnasArea.setText("10");
									factoriaNueva = new FactoriaGravity(10, 10);
								}
							} else
								factoriaNueva = new FactoriaGravity(i, j); // crea
																			// un
																			// juego
																			// gravity
						else {
							JOptionPane.showMessageDialog(null,
									"Introduzca valores positivos",
									"Valores erroneos",
									JOptionPane.WARNING_MESSAGE);
							todoCorrecto = false;
						}
					} catch (NumberFormatException exc) { // si no introduce
															// nada o algo que
								// no sea un Int
						int n = JOptionPane
								.showOptionDialog(
										null,
										"No se han reconocido los valores. \nDesea usar el tablero por defecto?",
										"Valores no reconocidos",
										JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE, null,
										null, null);
						if (n == 0) { // el usuario elige usar el tablero por
										// defecto
							filasArea.setText("10");
							columnasArea.setText("10");
							factoriaNueva = new FactoriaGravity(10, 10);
						} else
							todoCorrecto = false;
					}
					break;
				case "COMPLICA": // inicia un juego complica
					factoriaNueva = new FactoriaComplica();
					break;
				case "REVERSI":// inicia un juego reversi
					factoriaNueva = new FactoriaReversi();
					break;
				default: // en caso de error
					todoCorrecto = false;
					break;
				}

				if (todoCorrecto) // si todo ha ido correctamente
					c.cambiarJuego(factoriaNueva); // cambia de juego

			}

		});

		implementListenersBotones();
	}
	
	/*
	 * Implementa los listeners de los botones del tablero
	 */
	private void implementListenersBotones(){
		for (int i = 0; i < c.getPartida().getTableroSoloLectura().getFilas(); i++) {
			for (int j = 0; j < c.getPartida().getTableroSoloLectura().getColumnas(); j++) {
				
				this.botones[i][j].addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						
						Casilla casilla = (Casilla) e.getSource();
						
						if(c.getPartida().getTurno() == Ficha.BLANCA)
							modoBlancas.tableroPulsado(casilla.getFila(),casilla.getColumna());
						else
							modoNegras.tableroPulsado(casilla.getFila(),casilla.getColumna());
						
					}
				});
				
			}
		}
	}
	
	
	
	
	//EXTRAS
	//_____________________________________________________________________________________________________________
	
	/**
	 * Actualiza el color de los botones del tablero
	 * 
	 * @param tablero
	 *            Tablero de solo lectura
	 */
	private void actualizarBotones(TableroSoloLectura tablero){
		
		for (int i = 0; i < tablero.getFilas(); i++) {
			for (int j = 0; j < tablero.getColumnas(); j++) {
				
				switch (tablero.getFicha(i, j)) {
				case VACIA: {
					botones[i][j].setBackground(Color.orange);
					break;
				}
				case NEGRA: {
					botones[i][j].setBackground(Color.BLACK);
					break;
				}

				case BLANCA: {
					botones[i][j].setBackground(Color.WHITE);
					break;
				}

				}
				
			}
		}
	}
	
	/**
	 * cambia de enumerado a String el turno de la partida
	 * 
	 * @return string del turno
	 */
	private String enumeradoAString(Ficha ficha) {

		String turno = "";

		switch (ficha) {

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
	
	// Llamado cuando el jugador es Automático
	private void bloquearTablero() {
		for (int i = 0; i < c.getPartida().getTableroSoloLectura().getFilas(); i++) {
			for (int j = 0; j < c.getPartida().getTableroSoloLectura().getColumnas(); j++) {
				botones[i][j].setEnabled(false);
			}
		}
		this.botonAleatorio.setEnabled(false);
		this.deshacer.setEnabled(false);
	}
	
	// Llamado cuando el jugador es Humano
	private void desbloquearTablero() {
		for (int i = 0; i < c.getPartida().getTableroSoloLectura().getFilas(); i++) {
			for (int j = 0; j < c.getPartida().getTableroSoloLectura().getColumnas(); j++) {
				botones[i][j].setEnabled(true);
			}
		}
		this.botonAleatorio.setEnabled(true);
		this.deshacer.setEnabled(true);
	}

	
	
	
	
	
	
	
}
