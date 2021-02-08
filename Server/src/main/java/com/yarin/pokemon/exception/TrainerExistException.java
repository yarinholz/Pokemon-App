package com.yarin.pokemon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Trainer name already in use")
public class TrainerExistException extends RuntimeException {

}
