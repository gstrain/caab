package org.habitatmclean.table;

import org.habitatmclean.entity.GenericEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public abstract class Table {
    private final String TABLE_BEGIN = "<table id='table' class='table table-hover'>\n";
    private final String TABLE_END = "\n</table>";
    private final String ADD_BUTTON = "<button id='addButton' type='button' class='btn btn-success btn-lg btn-add d-print-none'>Add</button>";
    private final String REPORT_BUTTON = "<button id='reportButton' type='button' class='btn btn-info` btn-lg d-print-none'>Generate Report</button>";
    private static boolean logFlag;
    private static boolean individualReportFlag;
    Modal modal;
    List<TableRow> rows;
    private TableRow headers;
    private final String[] HEADERS;

    // do not include default constructor so a table cannot be created without headers, or a modal //TODO potentially remove dependancy for modal and add boolean for editable, addable, etc.

    /**
     * @param HEADERS the headers of the table column to create
     * @param modal a modal object to add to the table
     * @param logFlag whether the table should have a log button
     * @param individualReportFlag whether this table allows generating reports for individual entries
     */
    Table(String[] HEADERS, Modal modal, boolean logFlag, boolean individualReportFlag) {
        rows = new ArrayList<Table.TableRow>();
        this.HEADERS = HEADERS;
        addHeaders();
        this.modal = modal;
        logFlag = logFlag;
        Table.logFlag = logFlag;
        Table.individualReportFlag = individualReportFlag;
    }
    public List<String> returnModalFields(){
        return modal.returnFields();
    }

    /**
     * creates a fully built table with data already in it
     *
     * @param HEADERS the headers of the table column to create
     * @param modal a modal object to add to the table
     * @param logFlag whether the table should have a log button
     * @param individualReportFlag whether this table allows generating reports for individual entries

     */
    Table(String[] HEADERS, Modal modal, boolean logFlag, boolean individualReportFlag, SortedSet entities) {
        this(HEADERS,modal, logFlag, individualReportFlag);
        this.addData(entities);
    }

    /**
     * adds an array of column names to be the column headers of the table
     */
    private void addHeaders()  {
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        for(String header : HEADERS) {
            tableCells.add(new TableRow.TableCell.HeaderCell(header));
        }
        this.headers = new TableRow.TableHeaders(tableCells);
    }

    /**
     * adds a row to a table
     * @param entity the entity to add data from
     */
    public abstract void addRow(GenericEntity entity);

    public void write(HttpServletRequest request){
        int id;
        //try catch works like an if. If there is an id, it will do the try, else the catch.
        try{id = Integer.parseInt(request.getParameter("id"));}
        catch(Exception e){ id = 0;}


        //print all data retrieved for debugging and viability
        List<String> fields = this.returnModalFields();
        for(String field : fields){
            if(request.getParameter(field) != null)
                System.out.println(field + ": " + request.getParameter(field));
            else
                System.out.println(field + ": " + "not retrieved");
        }

        if(id != 0) { // edit functionality
            System.out.println("Edit: " + id);
            recordEdit(request, id);
        }
        else {//add functionality
            System.out.println("Add");
            recordAdd(request);
        }
    }

    public abstract void recordEdit(HttpServletRequest request, int id);
    public abstract void recordAdd(HttpServletRequest request);

    /**
     * adds the entire list of entities as rows to the table
     * @param entities a list of entities to add to the table, contents MUST implement GenericEntity
     */
    public void addData(SortedSet<GenericEntity> entities) {
        for(GenericEntity entity : entities) {
            addRow(entity);
        }
    }

    public String toString() {
        StringBuilder table = new StringBuilder();

//        table.append("<span id='open' style='font-size:30px;cursor:pointer' onclick='openNav()'>&#9776; open</span>");
        table.append("<span id='open' style='font-size:30px;cursor:pointer' onclick='openNav()'><i class='fa fa-chevron-right' aria-hidden='true'></i></span>");
        table.append("<div id='drawer' class='sidenav'> <!-- The drawer -->" +
                "        <a href='javascript:void(0)' class='closebtn' onclick='closeNav()'>&times;</a>");
        table.append(ADD_BUTTON);
//        for (int i = 0; i < HEADERS.length; i++) {
            table.append("<form class='filter-box form-inline d-print-none searchForm' role='search' autocomplete='on'>\n" +
            "<input type='search' id='search' onkeyup='searchTable();' class='form-control mr-sm-2' data-table='order-table' placeholder='Search Table'>\n </input> \n </form>\n");
//        }
        table.append(REPORT_BUTTON + "</div>");
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

        final String EDIT_BUTTON = "<td><button id='editButton' type='button' class='btn btn-warning btn-sm btn-edit d-print-none'>Edit</button>";
        final String DELETE_BUTTON = "<button id='deleteButton' type='button' class='btn btn-danger btn-sm btn-delete d-print-none'>Delete</button>";
        final String LOG = "<a href='logs.html' class='btn btn-info btn-sm btn-log d-print-none'>Logs</a>";
        final String REPORT = "<button type='button' class='btn btn-info btn-sm btn-report d-print-none'>Report</button></td>";   // watch location of <td> and </td> tags

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
            this.rowId += "'primary" + rowId + "'>\n\t\t";
        }

        public String toString() {
            StringBuilder row = new StringBuilder();
            row.append(LINE_BEGIN);
            row.append(rowId);
            for(TableCell tableCell : tableCells) {
                row.append(tableCell);
            }
            row.append(EDIT_BUTTON);
            row.append(DELETE_BUTTON);
            if (logFlag) {
                row.append(LOG);
            }
            if (individualReportFlag) {
                row.append(REPORT);
            }
            row.append(LINE_END);
            return row.toString();
        }

        static class TableHeaders extends TableRow {
            private final String HEADER_BEGIN = "\t<thead><tr>\n\t\t";
            private final String HEADER_END = "</tr></thead>";

            TableHeaders(List<TableCell> tableCells) {
                super(tableCells);
            }

            @Override
            public String toString() {
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
            final String CELL_BEGIN = "<td>"; // if these change, make sure to also change the .toArray() method
            final String CELL_END = "</td>";  // in TableRow
            String value;

            TableCell(String value) {
                 setValue(value);
            }

            String getValue() {
                return value;
            }

            void setValue(String value) {
                if(value == null || value.equals("null"))
                    this.value = "<div class='text-muted'>value not set</div>";
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

            static class HeaderCell extends TableCell {
                private final String HEAD = "<th onclick=\"sort(\"";
                private final String END = "\")></th>";

                public HeaderCell(String value) {
                    super(value);
                }

                public String toString() {
                    StringBuilder header = new StringBuilder();
                    header.append(HEAD);
                    for (int i = 0; i < tableCells.size(); )
                    header.append(value);
                    header.append(END);
                    return header.toString();
                }
            }
        }
    }
}
