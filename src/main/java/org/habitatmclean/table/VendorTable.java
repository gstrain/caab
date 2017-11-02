package org.habitatmclean.table;

import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.entity.Organization;

import java.util.ArrayList;
import java.util.List;

public class VendorTable extends Table {
    public VendorTable() {
        super(new String[]{"name", "contact"}, new VendorModal()); // adjust this to determine table columns
    }
    @Override
    public void addRow(GenericEntity entity) {
        Organization organization = (Organization) entity;
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        tableCells.add(new TableRow.TableCell(organization.getName()));
        tableCells.add(new TableRow.TableCell(organization.getPerson().getFirst()));
        TableRow tr = new TableRow(tableCells);
        tr.setRowId( "" + entity.getId());
        rows.add(tr);
    }

    static public class VendorModal extends Modal{
        public VendorModal(){
            super("Vendor");
        }

        public void buildModal(){

        }
    }
}
