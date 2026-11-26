package com.upiicsa.excepciones;

public class ArenaException extends RuntimeException {

    public ArenaException(String mensaje) {
        super(mensaje);
    }

    public static void validarNumeroPersonajes(int numPersonajes) {
        if (numPersonajes < 2) {
            throw new ArenaException("Usuarios Insuficientes");
        }
    }
}