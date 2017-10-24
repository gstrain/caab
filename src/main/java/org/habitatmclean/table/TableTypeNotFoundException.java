package org.habitatmclean.table;

public class TableTypeNotFoundException extends Exception {
    public TableTypeNotFoundException() {
        super("requested table type does not exist");
    }

    public TableTypeNotFoundException(String message) {
        super(message);
    }
}
