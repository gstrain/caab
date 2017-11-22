package org.habitatmclean.table;

import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.entity.Person;
import org.habitatmclean.entity.Property;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PropertyTable<P> extends Table {
    public PropertyTable() {
        super(new String[]{"property_no", "owner", "address", "property status"},new PropertyModal(), true, true); // adjust this to determine table columns
    }
    public void addRow(GenericEntity entity) {
        Property property = (Property) entity;
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        tableCells.add(new TableRow.TableCell("" + property.getId()));
        tableCells.add(new TableRow.TableCell("" + property.getPropertyAddress().toString()));
        tableCells.add(new TableRow.TableCell("" + property.getOwner().toString()));
        tableCells.add(new TableRow.TableCell(property.getProperty_status().getPstatus()));
        TableRow tr = new TableRow(tableCells);
        tr.setRowId("" + entity.getId());
        rows.add(tr);
    }

    public void recordEdit(HttpServletRequest request, int id){
    }
    public void recordAdd(HttpServletRequest request){
    }

    static class PropertyModal extends Modal {
        public PropertyModal(){
            super("Property");
        }

        public void buildModal(){
            forms.add(Form.builder().setType("text").setName("construction_cost").setLabel("Construction Cost").build());
            forms.add(Form.builder().setType("select").setName("zone").setLabel("Zone").setFromTable("Property","this").build());

        }
    }
}
