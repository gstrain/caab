package org.habitatmclean.table;

import org.habitatmclean.entity.RetrievableProperties;

import java.util.ArrayList;
import java.util.List;

public abstract class Table {
    private final String SEARCH = "<div id=\"left\">\n" + "<form class=\"navbar-form navbar-left\" role=\"search\" autocomplete=\"on\">\n" + "<div class=\"form-group\">\n" + "<input type=\"text\" class=\"form-control\" placeholder=\"Search\">\n" + "<span id=\"search-button\" class=\"glyphicon glyphicon-search\"></span>\n" + "</div>\n" + "</form>\n" + "</div>";
    private final String TABLE_BEGIN = "<table class=\"table\">\n";
    private final String TABLE_END = "\n</table>";
    private final String ADD_BUTTON = "<button id=\"addButton\" type=\"button\" class=\"btn btn-success btn-lg button-add\" data-toggle=\"modal\" data-target=\"#record-modal\">Add</button>";
    Modal modal;
    List<TableRow> rows;
    private TableRow headers;
    private final String[] HEADERS;

    // do not include default constructor so a table cannot be created without headers, or a modal //TODO potentially remove dependancy for modal and add boolean for editable, addable, etc.

    /**
     * @param HEADERS the headers of the table column to create
     * @param modal a modal object to add to the table
     */
    Table(String[] HEADERS, Modal modal) {
        rows = new ArrayList<Table.TableRow>();
        this.HEADERS = HEADERS;
        addHeaders();
        this.modal = modal;
    }

    /**
     * @param HEADERS the headers of the table column to create
     * @param modal a modal object to add to the table
     */
    Table(String[] HEADERS, Modal modal, List entities) {
        this(HEADERS,modal);
        addData(entities);
    }

    /**
     * adds an array of column names to be the column headers of the table
     */
    private void addHeaders()  {
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        for(String header : HEADERS) {
            tableCells.add(new TableRow.TableCell(header));
        }
        this.headers = new TableRow.TableHeaders(tableCells);
    }

    /**
     * adds a row to a table
     * @param entity the entity to add data from
     */
    public abstract void addRow(RetrievableProperties entity);

    /**
     * adds the entire list of entities as rows to the table
     * @param entities a list of entities to add to the table, contents MUST implement RetrievableProperties
     */
    public void addData(List<RetrievableProperties> entities) {
        for(RetrievableProperties entity : entities) {
            addRow(entity);
        }
    }

    public String toString() {
        StringBuilder table = new StringBuilder();
        for (int i = 0; i < HEADERS.length; i++) {
            table.append(SEARCH);
        }
        table.append(ADD_BUTTON);
        table.append(TABLE_BEGIN);
        table.append(headers);
        for(TableRow row : rows) {
            table.append(row);
        }
        table.append(TABLE_END);
        table.append(modal.toString());
        return table.toString();
    }

    static class TableRow {
        final String LINE_BEGIN = "\t<tr ";
        final String LINE_END = "\n\t</tr>\n";
        final String EDIT_BUTTON = "<td><button id=\"editButton\" type=\"button\" class=\"btn btn-warning btn-sm button-edit\" data-toggle=\"modal\" data-target=\"#record-modal\">Edit</button>";
        final String DELETE_BUTTON = "<button id=\"deleteButton\" type=\"button\" class=\"btn btn-danger btn-sm button-delete\">Delete</button></td>";
        List<TableCell> tableCells = new ArrayList<TableCell>();
        private String rowId = "id=";

        String[] toArray() {
            String[] data = new String[tableCells.size()];
            for(int i = 0; i < data.length; i++) {
                data[i] = tableCells.get(i).toString().replace("<td>", "");
                data[i] = data[i].replace("</td>", "");
            }
            return data;
        }

        TableRow(List<TableCell> tableCells){
            this.tableCells = tableCells;
        }

        List<TableCell> getTableCells() {
            return tableCells;
        }

        /* make sure this is used in implementations of the Table addRow() method */
        void setTableCells(List<TableCell> tableCells) {
            this.tableCells = tableCells;
        }

        public void setRowId(String rowId) {
            this.rowId += "\"primary" + rowId + "\">\n\t\t";
        }

        public String toString() {
            boolean first = true;
            StringBuilder row = new StringBuilder();
            row.append(LINE_BEGIN);
            row.append(rowId);
            for(TableCell tableCell : tableCells) {
                row.append(tableCell);
            }
            row.append(EDIT_BUTTON);
            row.append(DELETE_BUTTON);
            row.append(LINE_END);
            return row.toString();
        }

        static class TableHeaders extends TableRow {
            private final String HEADER_BEGIN = "\t<thead>\n\t\t";
            private final String HEADER_END = "</thead>";
            TableHeaders(List<TableCell> tableCells) {
                super(tableCells);
            }

            @Override
            public String toString() {
                boolean first = true;
                StringBuilder row = new StringBuilder();
                row.append(HEADER_BEGIN);
                for(TableCell tableCell : tableCells) {
                    row.append(tableCell);
                }
                row.append(HEADER_END);
                return row.toString();
            }
        }

        static class TableCell {
            private final String CELL_BEGIN = "<td>"; // if these change, make sure to also change the .toArray() method
            private final String CELL_END = "</td>";  // in TableRow
            private String value;

            TableCell(String value) {
                 setValue(value);
            }

            String getValue() {
                return value;
            }

            void setValue(String value) {
                if(value == null || value.equals("null"))
                    this.value = "";
                else
                    this.value = value;
            }

            public String toString() {
                StringBuilder cell = new StringBuilder();
                cell.append(CELL_BEGIN);
                cell.append(value);
                cell.append(CELL_END);
                return cell.toString();
            }
        }
    }
}
