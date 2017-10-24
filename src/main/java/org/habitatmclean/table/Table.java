package org.habitatmclean.table;

import org.habitatmclean.entity.RetrievableProperties;

import java.util.ArrayList;
import java.util.List;

public abstract class Table {
    protected final String TABLE_BEGIN = "<table>";
    protected final String TABLE_END = "</table>";
    protected List<TableRow> rows;
    protected TableRow headers;
    protected final String[] HEADERS;

    // do not include default constructor so a table cannot be created without headers

    Table(String[] HEADERS) {
        rows = new ArrayList<Table.TableRow>();
        this.HEADERS = HEADERS;
        addHeaders();
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
        List<TableRow.Cell> cells = new ArrayList<TableRow.Cell>();
        for(String header : HEADERS) {
            cells.add(new TableRow.Cell(header));
        }
        this.headers = new TableRow(cells);
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
        table.append(TABLE_BEGIN);
        table.append(headers);
        for(TableRow row : rows) {
            table.append(row);
        }
        table.append(TABLE_END);
        return table.toString();
    }

    static class TableRow {
        final String LINE_BEGIN = "<tr>";
        final String LINE_END = "</tr>";
        List<Cell> cells = new ArrayList<Cell>();

        public String[] toArray() {
            String[] data = new String[cells.size()];
            for(int i = 0; i < data.length; i++) {
                data[i] = cells.get(i).toString().replace("<td>", "");
                data[i] = data[i].replace("</td>", "");
            }
            return data;
        }

        public TableRow(List<Cell> cells){
            this.cells = cells;
        }

        public List<Cell> getCells() {
            return cells;
        }

        public void setCells(List<Cell> cells) {
            this.cells = cells;
        }

        public String toString() {
            StringBuilder row = new StringBuilder();
            row.append(LINE_BEGIN);
            for(Cell cell : cells) {
                row.append(cell);
            }
            row.append(LINE_END);
            return row.toString();
        }

        static class Cell {
            private final String CELL_BEGIN = "<td>"; // if these change, make sure to also change the .toArray() method
            private final String CELL_END = "</td>";  // in TableRow
            private String value;

            public Cell(String value) {
                 setValue(value);
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
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
