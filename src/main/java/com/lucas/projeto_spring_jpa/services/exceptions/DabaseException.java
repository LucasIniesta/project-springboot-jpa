package com.lucas.projeto_spring_jpa.services.exceptions;

public class DabaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DabaseException(String message) {
        super(message);
    }
}