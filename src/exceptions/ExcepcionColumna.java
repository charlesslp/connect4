package exceptions;

public class ExcepcionColumna extends MovimientoInvalido{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcepcionColumna(int columna){
		super("La columna debe estar entre 1 y " + columna);
	}
	
}
