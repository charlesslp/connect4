package modoJuego;

import control.ControladorGUI;

public class ModoJuegoAutomatico implements ModoJuego{
	
	private ControladorGUI c;
	private Thread th;
	public ModoJuegoAutomatico(ControladorGUI c){
		this.c = c;
	}

	/*
	 * Metodo que crea una hebra y la empieza con el .start()
	 * 
	 * La hebra espera 2 segundos antes de ejecutar el ponerAleatorio()
	 */
	public synchronized void comenzar() {
		
			this.th = new Thread(new Thread() {
				public void run() {
					try {
						Thread.sleep(2000);
						c.ponerAleatorio();


					} catch (InterruptedException e) {}
				}
			});

			this.th.start();

	}

	/*
	 * Al darse un error de funcionalidad al hacerse solo un interrupt(), lo
	 * revisamos con dos profesores al mismo tiempo que nos recomendaron que se
	 * debe hacer un join() y esperar a que acabe la hebra antes de continuar
	 */
	@Override
	public synchronized void terminar() {
		if (this.th != null) {

			this.th.interrupt();

			try {
				this.th.join();

			} catch (InterruptedException e) {
			}
		}
	}

	/*
	 * Este deshacer es llamado en el caso de que el jugador humano pules
	 * deshacer y esté jugando contra un jugador automático. La lógica de este
	 * propósito se explica con mas detalle en la funcion onUndo() de la clase
	 * VistaGUI
	 */
	@Override
	public void deshacerPulsado() {
		c.getPartida().undo();
	}

	
	public void tableroPulsado(int f, int c) {
	}

}
