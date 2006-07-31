package org.javageek.katalogo;

public class KatalogoException extends RuntimeException{
    public KatalogoException() {
    }

    public KatalogoException(final Throwable cause) {
        super(cause);
    }

    public KatalogoException(final String message) {
        super(message);
    }

    public KatalogoException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
