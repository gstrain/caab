package org.habitatmclean.table;

import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.entity.Zone;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ZoneTable extends Table {
    public ZoneTable() {
        super(new String[]{"zone_info", "zone_desc"}, new ZoneModal(), false); // adjust this to determine table columns
    }
    @Override
    public void addRow(GenericEntity entity) {
        Zone zone = (Zone) entity;
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        tableCells.add(new TableRow.TableCell(zone.getZone_info()));
        tableCells.add(new TableRow.TableCell(zone.getZone_desc()));
        TableRow tr = new TableRow(tableCells);
        tr.setRowId("" + entity.getId());
        rows.add(tr);
    }

    public void write(HttpServletRequest request){
        List<String> fields = this.returnModalFields();
        for(String field : fields){
            if(request.getParameter(field) != null)
                System.out.println(field + ": " + request.getParameter(field));
        }
    }

    static public class ZoneModal extends Modal{
        public ZoneModal(){
            super("Zone");
        }

        public void buildModal(){

        }
    }
}
