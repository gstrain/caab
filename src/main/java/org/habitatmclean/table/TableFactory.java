package org.habitatmclean.table;

import org.habitatmclean.entity.Property;

public class TableFactory {
    public static Table getTable(String tableType) throws TableTypeNotFoundException {
        switch(tableType) {
            case "property":
                return new PropertyTable<Property>();
            case "house":
                return new HouseTable();
            case "vendor":
                return new VendorTable();
            case "person":
                return new PeopleTable();
            case "zone":
                return new ZoneTable();
            default:
                throw new TableTypeNotFoundException(); // TODO maybe get rid of this
        }
    }
}
