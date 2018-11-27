package com.boot;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author ania.pawelczyk
 * @since 27.11.2018.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidWebappStateException extends Exception {
  public InvalidWebappStateException(String message) {
    super(message);
  }
}
