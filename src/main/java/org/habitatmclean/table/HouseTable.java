package org.habitatmclean.table;

import org.habitatmclean.entity.House;
import org.habitatmclean.entity.RetrievableProperties;

import java.util.ArrayList;
import java.util.List;

public class HouseTable extends Table {
    public HouseTable() {
        super(new String[]{"address", "construction cost", "bedrooms", "bathrooms"}); // adjust this to determine table columns
    }
    @Override
    public void addRow(RetrievableProperties entity) {
        House house = (House) entity;
        List<TableRow.Cell> cells = new ArrayList<TableRow.Cell>();
        cells.add(new TableRow.Cell(house.getAddress().getNumber() + " " +
                                            house.getAddress().getStreet() + " " +
                                            house.getAddress().getCity() + " " +
                                            house.getAddress().getState() + " " +
                                            house.getAddress().getZipcode()));
        cells.add(new TableRow.Cell("" + house.getConstruction_cost()));
        cells.add(new TableRow.Cell("" + house.getBedrooms()));
        cells.add(new TableRow.Cell("" + house.getBathrooms()));
        rows.add(new TableRow(cells));
    }
}
