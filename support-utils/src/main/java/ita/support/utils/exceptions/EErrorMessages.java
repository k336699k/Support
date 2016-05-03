package ita.support.utils.exceptions;

/**
 * 
 * @author Konstantin Shaplyko
 * @version 1.0
 *
 */
public enum EErrorMessages {

	// 0001 - DBA
	// 1001 - utils (and all common errors)
	// 2001 - security
	// 3001 - inquiries module
	// 4001 - etc
	// ---------------------------------
	// -------------------DBA messages
		DBA_0001("0001", "Cannot get object by id=%s"),
		DBA_0002("0002", "Cannot get list of %s"),
		DBA_0003("0003", "Cannot create object %s"),
		DBA_0004("0004", "Cannot update object %s"),
		DBA_0005("0005", "Cannot delete object %s"),
		DBA_0006("0006", "Cannot create hql: %s");
	// -------------------

	public final String code;
	public final String message;

	private EErrorMessages(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String toString() {
		return code + " : " + message;
	}

}
