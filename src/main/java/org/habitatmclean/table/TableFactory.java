package org.habitatmclean.table;

import org.habitatmclean.entity.Property;

public class TableFactory {
    public static Table getTable(String tableType) throws TableTypeNotFoundException {
        switch(tableType) {
            case "Property":
                return new PropertyTable<Property>();
            case "House":
                return new HouseTable();
            case "Vendor":
                return new VendorTable();
            case "Person":
                return new PersonTable();
            case "Zone":
                return new ZoneTable();
            default:
                throw new TableTypeNotFoundException(); // TODO maybe get rid of this
        }
    }
}