package com.helmes.sectors.exception;

public class SectorIdDoesNotExistException extends RuntimeException {
    public SectorIdDoesNotExistException(String message) {
        super(message);
    }
}