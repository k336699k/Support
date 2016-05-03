package ita.support.utils.exceptions;

/**
 * 
 * @author Konstantin Shaplyko
 * @version 1.0
 *
 */
public class UnexpectedErrorException extends FtException {

	private static final long serialVersionUID = 1L;

	public UnexpectedErrorException() {
		super(500, "-9999", "Unexpected error exception!", "see log for more information!");
	}
}
