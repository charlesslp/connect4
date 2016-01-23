package exceptions;

public class ExcepcionLlena extends MovimientoInvalido{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcepcionLlena(int columna) {
		super("La columna " + columna + " esta llena");
	}

}
