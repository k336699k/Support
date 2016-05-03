package ita.support.utils.exceptions;

public class NoEntityFoundException extends FtException {

	private static final long serialVersionUID = 1L;

	public NoEntityFoundException() {
		super(404, "-9999", "No entity found!", "see log for more information!");
	}

	public NoEntityFoundException(String message) {
		super(404, message);
	}
}
