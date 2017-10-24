package org.habitatmclean.table;

import org.habitatmclean.entity.Property;
import org.habitatmclean.entity.RetrievableProperties;

import java.util.ArrayList;
import java.util.List;

public class PropertyTable<P> extends Table {
    public PropertyTable() {
        super(new String[]{"property_no", "owner", "property status"}); // adjust this to determine table columns
    }
    @Override
    public void addRow(RetrievableProperties entity) {
        Property property = (Property) entity;
        List<TableRow.Cell> cells = new ArrayList<TableRow.Cell>();
        cells.add(new TableRow.Cell("" + property.getProperty_no()));
        cells.add(new TableRow.Cell("" + property.getOwner().getActor_id()));
        cells.add(new TableRow.Cell(property.getProperty_status().getPstatus_desc()));
        rows.add(new TableRow(cells));
    }
}
