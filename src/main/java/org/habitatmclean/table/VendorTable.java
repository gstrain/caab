package org.habitatmclean.table;

import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.entity.Organization;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class VendorTable extends Table {
    public VendorTable() {
        super(new String[]{"name", "contact"}, new VendorModal(), false); // adjust this to determine table columns
    }
    public void addRow(GenericEntity entity) {
        Organization organization = (Organization) entity;
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        tableCells.add(new TableRow.TableCell(organization.getName()));
        tableCells.add(new TableRow.TableCell(organization.getOrganizationContact().getFirst()));
        TableRow tr = new TableRow(tableCells);
        tr.setRowId( "" + entity.getId());
        rows.add(tr);
    }

    public void recordEdit(HttpServletRequest request, int id){
    }
    public void recordAdd(HttpServletRequest request){
    }

    static class VendorModal extends Modal {
        public VendorModal(){
            super("Vendor");
        }

        public void buildModal(){

        }
    }
}
