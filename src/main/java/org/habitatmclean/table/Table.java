package org.habitatmclean.table;

import org.habitatmclean.entity.RetrievableProperties;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private final String TABLE_BEGIN = "<table>";
    private final String TABLE_END = "</table>";
    private List<TableRow> rows;
    private TableRow headers;

    public Table() {
        rows = new ArrayList<Table.TableRow>();
    }

    /**
     * @param headers the headers of the table column to create
     */
    public Table(String[] headers) {
        rows = new ArrayList<Table.TableRow>();
        try {
            addHeaders(headers);
        } catch (HeadersAlreadyDefinedException e) {
            e.printStackTrace(); // this will never happen
        }
    }

    /**
     * @param headers the headers of the table column to create
     * @param entities a list of entities to add to the table, contents MUST implement RetrievableProperties
     */
    public Table(String[] headers, List entities) {
        this(headers);
        try {
            addData(entities);
        } catch (HeadersNotDefinedException e) {
            e.printStackTrace(); // this will never happen
        }
    }

    /**
     * adds an array of column names to be the column headers of the table
     * @param headers the headers for the columns to go in the table
     * @throws HeadersAlreadyDefinedException if the headers for this table are already set
     */
    public void addHeaders(String[] headers) throws HeadersAlreadyDefinedException {
        if(this.headers != null) throw new HeadersAlreadyDefinedException();
        List<TableRow.Cell> cells = new ArrayList<TableRow.Cell>();
        for(String header : headers) {
            cells.add(new TableRow.Cell(header));
        }
        this.headers = new TableRow(cells);
    }

    /**
     * adds a row to a table
     * @param entity the entity to add data from
     * @throws HeadersNotDefinedException if the column headers for the table were not already defined
     */
    public void addRow(RetrievableProperties entity) throws HeadersNotDefinedException {
        if(headers == null) throw new HeadersNotDefinedException();
        List<TableRow.Cell> cells = new ArrayList<TableRow.Cell>();
        for(String property : headers.toArray()) {
            String value = entity.getValueByPropertyName(property);
            cells.add(new TableRow.Cell(value));
        }
        rows.add(new TableRow(cells));
    }

    /**
     * adds the entire list of entities as rows to the table
     * @param entities a list of entities to add to the table, contents MUST implement RetrievableProperties
     * @throws HeadersNotDefinedException if the column headers for the table were not already defined
     */
    public void addData(List<RetrievableProperties> entities) throws HeadersNotDefinedException{
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

    private static class TableRow {
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

        private static class Cell {
            private final String CELL_BEGIN = "<td>";
            private final String CELL_END = "</td>";
            private String value;

            public Cell(String value) {
                if(!value.equals("null"))
                    this.value = value;
                else
                    this.value = "";
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
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
