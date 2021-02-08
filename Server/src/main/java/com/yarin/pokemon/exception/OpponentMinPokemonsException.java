package com.yarin.pokemon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Opponent doesn't have enough Pokemons to fight")
public class OpponentMinPokemonsException extends RuntimeException {
}
