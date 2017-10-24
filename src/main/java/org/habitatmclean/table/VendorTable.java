package org.habitatmclean.table;

import org.habitatmclean.entity.Organization;
import org.habitatmclean.entity.RetrievableProperties;

import java.util.ArrayList;
import java.util.List;

public class VendorTable extends Table {
    public VendorTable() {
        super(new String[]{"name", "contact"}); // adjust this to determine table columns
    }
    @Override
    public void addRow(RetrievableProperties entity) {
        Organization organization = (Organization) entity;
        List<TableRow.Cell> cells = new ArrayList<TableRow.Cell>();
        cells.add(new TableRow.Cell(organization.getName()));
        cells.add(new TableRow.Cell(organization.getPerson().getFirst()));
        rows.add(new TableRow(cells));
    }
}
