package ita.support.ws.exceptions;

import org.springframework.http.HttpStatus;

import ita.support.utils.exceptions.EErrorMessages;
import ita.support.utils.exceptions.FtException;

public class BadRequestException extends FtException {

	private static final long serialVersionUID = 1L;

	public BadRequestException(String internalCode, String message) {
		super(HttpStatus.BAD_REQUEST.value(), internalCode, message);
	}

	public BadRequestException(EErrorMessages errCode, Object... params) {
		String msg = String.format(errCode.message, params);
		this.setStatus(500);
		this.setInternalCode(errCode.code);
		this.setMessage(msg);
		this.setMoreInfo(FT_ERRORS_BASE_URL + errCode.code);
	}
}
