package ita.support.dba;

import org.hibernate.HibernateException;

import ita.support.utils.exceptions.EErrorMessages;
import ita.support.utils.exceptions.FtException;

public class DaoFtException extends FtException {
	private static final long serialVersionUID = 1L;

	public DaoFtException(String internalCode, String message) {
		super(500, internalCode, message, FT_ERRORS_BASE_URL + internalCode);
	}

	public DaoFtException(HibernateException hex, EErrorMessages errCode, Object... params) {
		super(hex);
		String msg = String.format(errCode.message, params);
		this.setStatus(500);
		this.setInternalCode(errCode.code);
		this.setMessage(msg);
		this.setMoreInfo(FT_ERRORS_BASE_URL + errCode.code);
	}

}
