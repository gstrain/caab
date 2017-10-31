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
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        tableCells.add(new TableRow.TableCell("" + property.getProperty_no()));
        tableCells.add(new TableRow.TableCell("" + property.getOwner().getActor_id()));
        tableCells.add(new TableRow.TableCell(property.getProperty_status().getPstatus_desc()));
        TableRow tr = new TableRow(tableCells);
        tr.setRowId("" + ((Property) entity).getProperty_no());
        rows.add(tr);
    }

    @Override
    public void buildModal(){
        modal = new Modal ("Property");
    }
}
