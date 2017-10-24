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
        List<TableRow.Cell> cells = new ArrayList<TableRow.Cell>();
        cells.add(new TableRow.Cell(zone.getZone_info()));
        cells.add(new TableRow.Cell(zone.getZone_desc()));
        rows.add(new TableRow(cells));
    }
}
