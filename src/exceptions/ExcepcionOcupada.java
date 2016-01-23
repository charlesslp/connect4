package exceptions;

public class ExcepcionOcupada extends MovimientoInvalido{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcepcionOcupada() {
		super("Esta posicion esta ocupada");
	}

}
