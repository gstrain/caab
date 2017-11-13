package org.habitatmclean.table;

import com.google.gson.*;
import org.habitatmclean.dao.GenericDao;
import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.entity.Person;
import org.habitatmclean.entity.RelationType;
import org.habitatmclean.entity.Zone;
import org.habitatmclean.hibernate.HibernateAdapter;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public abstract class Table {
    private final String TABLE_BEGIN = "<table id='table' class='table table-hover'>\n";
    private final String TABLE_END = "\n</table>";
    private final String ADD_BUTTON = "<button id='addButton' type='button' class='btn btn-success btn-lg btn-add d-print-none'>Add</button>";
    private final String REPORT_BUTTON = "<button id='reportButton' type='button' class='btn btn-info` btn-lg btn-report d-print-none'>Generate Report From Table</button>";
    private static boolean flag;
    Modal modal;
    List<TableRow> rows;
    private TableRow headers;
    private final String[] HEADERS;

    // do not include default constructor so a table cannot be created without headers, or a modal //TODO potentially remove dependancy for modal and add boolean for editable, addable, etc.

    /**
     * @param HEADERS the headers of the table column to create
     * @param modal a modal object to add to the table
     */
    Table(String[] HEADERS, Modal modal, boolean flag) {
        rows = new ArrayList<Table.TableRow>();
        this.HEADERS = HEADERS;
        addHeaders();
        this.modal = modal;
        Table.flag = flag;
    }
    public List<String> returnModalFields(){
        return modal.returnFields();
    }

    /**
     * @param HEADERS the headers of the table column to create
     * @param modal a modal object to add to the table
     */
    Table(String[] HEADERS, Modal modal, boolean flag, SortedSet entities) {
        this(HEADERS,modal, flag);
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

    private void fill(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();

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

        if(id != 0) { // edit functionality
            System.out.println("Edit: " + id);
            recordEdit(request, id);
        }
        else {//add functionality
            System.out.println("Add");
            recordAdd(request);
        }
        //print all data retrieved for debugging and viability
        List<String> fields = this.returnModalFields();
        for(String field : fields){
            if(request.getParameter(field) != null)
                System.out.println(field + ": " + request.getParameter(field));
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

//        table.append(REPORT_BUTTON);
//        table.append(SEARCH_FORM_BEGIN);
//        for(int i=0; i< HEADERS.length; i++) {
//            table.append(SEARCH_BOX);
//        }
//        table.append(SEARCH_FORM_END);
//        table.append(ADD_BUTTON);
        table.append("<span style='font-size:30px;cursor:pointer' onclick='openNav()'>&#9776; open</span>");
        table.append("<div id='drawer' class='sidenav'> <!-- The drawer -->\n" +
                "        <a href='javascript:void(0)' class='closebtn' onclick='closeNav()'>&times;</a>\n" +
                "        <button id='addButton' type='button' class='btn btn-success btn-lg btn-add d-print-none'>Add</button>'>" +
                "        <button id='reportButton' type='button' class='btn btn-info` btn-lg btn-report d-print-none'>Generate Report</button>" +
                "        '<form class='filter-box form-inline d-print-none searchForm' role='search' autocomplete='on'> '\n" +
                "        + '<input type='search' class='form-control mr-sm-2' placeholder=''\n" +
                "        + ''>\\n' +\n" +
                "                '                        <span class='fa fa-search search-button'></span>\n' +\n" +
                "                '                        </input>\n' +\n" +
                "                '                        </form>\n'" +
                "        <a href='#'>Contact</a>\n" +
                "    </div>");
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

        final String EDIT_BUTTON = "<td><button id=\"editButton\" type=\"button\" class=\"btn btn-warning btn-sm btn-edit d-print-none\">Edit</button>";
        final String DELETE_BUTTON = "<button id=\"deleteButton\" type=\"button\" class=\"btn btn-danger btn-sm btn-delete d-print-none\">Delete</button>";
        final String LOG = "<button type=\"button\" class=\"btn btn-info btn-sm btn-log d-print-none\" href=\"pages/logs.html\">Logs</button></td>";


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
            boolean first = true;
            StringBuilder row = new StringBuilder();
            row.append(LINE_BEGIN);
            row.append(rowId);
            for(TableCell tableCell : tableCells) {
                row.append(tableCell);
            }
            row.append(EDIT_BUTTON);
            row.append(DELETE_BUTTON);
            if (flag) {
                row.append(LOG);
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

            static class HeaderCell extends TableCell {
//                private final String SEARCH_FORM_BEGIN = "<th><form class='filter-box form-inline d-print-none searchForm' role='search' autocomplete='on'>\n";
//                private final String SEARCH_BOX = "<input type='search' class='form-control mr-sm-2' placeholder='";
//                private final String SEARCH_FORM_END = "'>\n" +
//                        "                        <span class='fa fa-search search-button'></span>\n" +
//                        "                        </input>\n" +
//                        "                        </form>\n" +
//                        "                        </th>";
                private final String HEAD = "<th>";
                private final String END = "</th>";

                public HeaderCell(String value) {
                    super(value);
                }

                public String toString() {
                    StringBuilder header = new StringBuilder();
//                    header.append(SEARCH_FORM_BEGIN);
//                    header.append(SEARCH_BOX);
                    header.append(HEAD);
                    header.append(value);
                    header.append(END);
//                    header.append(SEARCH_FORM_END);
                    return header.toString();
                }
            }
        }
    }
}
