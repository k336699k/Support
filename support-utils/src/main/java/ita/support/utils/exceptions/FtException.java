package ita.support.utils.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Konstantin Shaplyko
 * @version 1.0
 *
 */
@JsonIgnoreProperties({ "stackTrace", "cause", "suppressed", "localizedMessage" })
public class FtException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int status;
	private String internalCode;
	private String message;
	private String moreInfo;
	//
	public static final String FT_ERRORS_BASE_URL = "http:\\\\support.com\\errors\\";

	// ---------------------constructors

	public FtException(int status, String internalCode, String message, String moreInfo) {
		super();
		this.status = status;
		this.internalCode = internalCode;
		this.message = message;
		this.moreInfo = moreInfo;
	}

	public FtException(int status, String internalCode, String message) {
		super(message);
		this.status = status;
		this.internalCode = internalCode;
		this.message = message;
	}

	public FtException(int status, String message) {
		super(message);
		this.status = status;
		this.message = message;
	}

	public FtException(String message, FtException couseEx) {
		this(couseEx.status, couseEx.internalCode, message, couseEx.moreInfo);
	}

	public FtException(String message, Exception couseEx) {
		super(couseEx);
		this.status = 500;
		this.message = "unexpected error!";
		this.moreInfo = couseEx.getMessage();
	}

	public FtException(Exception couseEx) {
		super(couseEx);
		this.status = 500;
		this.message = couseEx.getMessage();
		this.moreInfo = couseEx.getMessage();
	}

	public FtException(String message) {
		super(message);
		this.message = message;
	}

	public FtException() {
		super();
	}

	// ---------------------getters
	@JsonProperty("status")
	public int getStatus() {
		return status;
	}

	@JsonProperty("internalCode")
	public String getInternalCode() {
		return internalCode;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	@JsonProperty("moreInfo")
	public String getMoreInfo() {
		return moreInfo;
	}

	// ---------------------setters
	public void setStatus(int status) {
		this.status = status;
	}

	public void setInternalCode(String internalCode) {
		this.internalCode = internalCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}
}
