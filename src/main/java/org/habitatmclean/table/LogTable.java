package org.habitatmclean.table;
import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.entity.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Log Table class, extends Table functionality
 */
/*
public class LogTable extends Table {
    public LogTable() {
        super(new String[]{"Reason", "Date", "Notes", "Status"}, new LogModal(), false);
    }

    /**
     * Extends Table addRow. Builds table rows from the log object
     * @param entity the entity to add data from
     */
/*
    @Override
    public void addRow(GenericEntity entity) {
        Log log = (Log) entity;
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();

        tableCells.add(new TableRow.TableCell("" + log.getId()));
        /*TODO get the ids for contact and property from the servlet */
/*
        tableCells.add(new TableRow.TableCell(log.getReason()));
        tableCells.add(new TableRow.TableCell(log.getDate().toString()));
        tableCells.add(new TableRow.TableCell(log.getNotes()));
        tableCells.add(new TableRow.TableCell(log.getStatus()));

        TableRow tr = new TableRow(tableCells);
        tr.setRowId("" + entity.getId());
        rows.add(tr);
    }

    static class LogModal extends Modal {

        public LogModal(){
            super("Log");
        }

        @Override
        public void buildModal() {
            forms.add(Form.builder().setType("text").setName("reason").setLabel("Reason").build());
            forms.add(Form.builder().setType("text").setName("date").setLabel("Date").build());
            forms.add(Form.builder().setType("text").setName("notes").setLabel("Notes").build());
            forms.add(Form.builder().setType("text").setName("status").setLabel("Status").build());
        }
    }
}
*/