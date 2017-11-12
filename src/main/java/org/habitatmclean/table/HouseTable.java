package org.habitatmclean.table;

import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.entity.House;

import java.util.ArrayList;
import java.util.List;

public class HouseTable extends Table {
    public HouseTable() {
        super(new String[]{"address", "construction cost", "bedrooms", "bathrooms"}, new HouseModal(), true); // adjust this to determine table columns
    }
    @Override
    public void addRow(GenericEntity entity) {
        House house = (House) entity;
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        tableCells.add(new TableRow.TableCell(house.getAddress().getNumber() + " " +
                                            house.getAddress().getStreet() + " " +
                                            house.getAddress().getCity() + " " +
                                            house.getAddress().getState() + " " +
                                            house.getAddress().getZipcode()));
        tableCells.add(new TableRow.TableCell("" + house.getConstruction_cost()));
        tableCells.add(new TableRow.TableCell("" + house.getBedrooms()));
        tableCells.add(new TableRow.TableCell("" + house.getBathrooms()));
        TableRow tr = new TableRow(tableCells);
        tr.setRowId("" + entity.getId());
        rows.add(tr);
    }

    static public class HouseModal extends Modal{
        public HouseModal(){
            super("House");
        }

        public void buildModal(){

        }
    }

}
