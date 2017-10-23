package org.habitatmclean.table;

public class HeadersAlreadyDefinedException extends Exception {

    public HeadersAlreadyDefinedException() {
        super("Headers already defined for this table");
    }

    public HeadersAlreadyDefinedException(String message) {
        super(message);
    }
}
