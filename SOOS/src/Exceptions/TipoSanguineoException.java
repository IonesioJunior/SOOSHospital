package Exceptions;

public class TipoSanguineoException extends ControllerException {

	private static final long serialVersionUID = 1L;
	
	public TipoSanguineoException() {
		super("Tipo sanguineo invalido.");
	}
	
}
