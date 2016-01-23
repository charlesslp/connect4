package Comandos;


public class ParserAyudaComandos {
	
	private static Comandos[] comandos = { new Salir(), new Deshacer(),
			new Jugar(), new Poner(), new PonJugador(), new Ayuda()};

	static public String AyudaComandos() {
		String s = "";
		s += "\n Los comandos disponibles son: \n";
		
		for(int i = 0;i<6;i++)
			s += "\n" + comandos[i].textoAyuda();
		return s + "\n";
		
	}

	static public Comandos parsea(String[] cadenas) {
		Comandos comando = null;
		try{
		int i = 0;
			while(i < 6 && comando == null){
				comando = comandos[i].parsea(cadenas);
				i++;
			}
		return comando;
		} catch(ArrayIndexOutOfBoundsException e){
			System.err.println("Comando incorrecto");
			return new Ayuda();			
		}
	}
}
