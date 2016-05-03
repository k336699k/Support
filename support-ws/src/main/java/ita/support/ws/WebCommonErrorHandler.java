package ita.support.ws;

import ita.support.utils.exceptions.FtException;
import ita.support.utils.exceptions.UnexpectedErrorException;
import ita.support.ws.exceptions.BadRequestException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(value = { "ita.support.ws" })
public class WebCommonErrorHandler extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(WebCommonErrorHandler.class.getName());

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseEntity<UnexpectedErrorException> handleAnyException(Exception e) {
		log.error("Handle unexpected exception ", e);
		return new ResponseEntity<>(new UnexpectedErrorException(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseBody
	@ExceptionHandler(FtException.class)
	public ResponseEntity<FtException> handleFtException(FtException e) {
		log.error("FtException ", e);
		return new ResponseEntity<>(e, HttpStatus.valueOf(e.getStatus()));
	}

	@ResponseBody
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BadRequestException> handleBadRequestException(BadRequestException e) {
		log.error("BadRequestException ", e);
		return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
	}
}
