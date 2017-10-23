package org.habitatmclean.table;

public class HeadersNotDefinedException extends Exception {

    protected HeadersNotDefinedException() {
        super("Headers not yet defined for this table");
    }

    protected HeadersNotDefinedException(String message) {
        super(message);
    }
}
