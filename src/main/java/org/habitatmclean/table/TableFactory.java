package org.habitatmclean.table;

import org.habitatmclean.entity.Property;

public class TableFactory {
    public static Table getTable(String tableType) throws TableTypeNotFoundException {
        switch(tableType) {
            case "property":
                return new PropertyTable<Property>();
            case "house":
                return new HouseTable();
            case "family":
                return new FamilyTable();
            case "organization":
                return new OrganizationTable();
            case "person":
                return new PersonTable();
            case "zone":
                return new ZoneTable();
            case "log":
                return new LogTable();
            default:
                throw new TableTypeNotFoundException();
        }
    }
}