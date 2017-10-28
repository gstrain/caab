package org.habitatmclean.table;

import org.habitatmclean.entity.RetrievableProperties;

import java.util.ArrayList;
import java.util.List;

public abstract class Table {
    private final String TABLE_BEGIN = "<table class=\"table\" style=\"display:inline; margin:10px\">\n"; // TODO move inline display to CSS
    private final String TABLE_END = "\n</table>";
    private final String BUTTON = "<button type=\"button\" class=\"btn btn-success btn-lg btn-add\" data-toggle=\"modal\" data-target=\"#record-modal\">Add</button>";
    Modal modal;
    List<TableRow> rows;
    TableRow headers;
    private final String[] HEADERS;

    // do not include default constructor so a table cannot be created without headers

    Table(String[] HEADERS) {
        rows = new ArrayList<Table.TableRow>();
        this.HEADERS = HEADERS;
        addHeaders();
        buildModal();
    }

    /**
     * @param HEADERS the headers of the table column to create
     * @param entities a list of entities to add to the table, contents MUST implement RetrievableProperties
     */
    Table(String[] HEADERS, List entities) {
        this(HEADERS);
        addData(entities);
    }

    /**
     * adds an array of column names to be the column headers of the table
     */
    public void addHeaders()  {
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
     * builds this tables modal
     */
    public abstract void buildModal();

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
        table.append(TABLE_BEGIN);
        table.append(headers);
        for(TableRow row : rows) {
            table.append(row);
        }
        table.append(TABLE_END);
        table.append(BUTTON);
        table.append(modal.toString());
        return table.toString();
    }

    static class TableRow {
        final String LINE_BEGIN = "\t<tr>\n\t\t";
        final String LINE_END = "\n\t</tr>\n";
        final String EDIT_BUTTON = "<td><button type=\"button\" class=\"btn btn-warning btn-sm btn-edit\" style=\"margin:5px\" data-toggle=\"modal\" data-target=\"#record-modal\">Edit</button></td>"; // TODO move CSS to stylesheet
        final String DELETE_BUTTON = "<td><button type=\"button\" class=\"btn btn-danger btn-sm\" style=\"margin:5px\">Delete</button></td>";
        List<TableCell> tableCells = new ArrayList<TableCell>();

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

        void setTableCells(List<TableCell> tableCells) {
            this.tableCells = tableCells;
        }

        public String toString() {
            boolean first = true;
            StringBuilder row = new StringBuilder();
            row.append(LINE_BEGIN);
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
