package org.habitatmclean.table;

import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.entity.Property;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PropertyTable<P> extends Table {
    public PropertyTable() {
        super(new String[]{"property_no", "owner", "property status"},new PropertyModal(), false); // adjust this to determine table columns
    }
    @Override
    public void addRow(GenericEntity entity) {
        Property property = (Property) entity;
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        tableCells.add(new TableRow.TableCell("" + property.getId()));
        tableCells.add(new TableRow.TableCell("" + property.getOwner().getId()));
        tableCells.add(new TableRow.TableCell(property.getProperty_status().getPstatus_desc()));
        TableRow tr = new TableRow(tableCells);
        tr.setRowId("" + entity.getId());
        rows.add(tr);
    }

    public void recordEdit(HttpServletRequest request, int id){
    }
    public void recordAdd(HttpServletRequest request){
    }

    static public class PropertyModal extends Modal {
        public PropertyModal(){
            super("Property");
        }

        public void buildModal(){

        }
    }
}
