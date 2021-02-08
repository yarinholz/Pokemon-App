package com.yarin.pokemon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Pokemon Not found")
public class PokemonNotFoundException extends RuntimeException {
}
