package org.habitatmclean.table;

import org.habitatmclean.entity.RetrievableProperties;
import org.habitatmclean.entity.Zone;

import java.util.ArrayList;
import java.util.List;

public class ZoneTable extends Table {
    public ZoneTable() {
        super(new String[]{"zone_info", "zone_desc"}); // adjust this to determine table columns
    }
    @Override
    public void addRow(RetrievableProperties entity) {
        Zone zone = (Zone) entity;
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        tableCells.add(new TableRow.TableCell(zone.getZone_info()));
        tableCells.add(new TableRow.TableCell(zone.getZone_desc()));
        TableRow tr = new TableRow(tableCells);
        tr.setRowId("" + ((Zone) entity).getZone_id());
        rows.add(tr);
    }

    @Override
    public void buildModal(){
        modal = new Modal ("Zone");
    }
}
