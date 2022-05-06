package edu.hm.contact.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for HTTP 400 Bad Request.
 *
 * @author Simon Hirner
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(BadRequestException.class);

    public BadRequestException(String message) {
        super(message);
        logger.error(this.getMessage());
    }

}
