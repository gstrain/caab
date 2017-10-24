package org.habitatmclean.table;

import org.habitatmclean.entity.Person;
import org.habitatmclean.entity.RetrievableProperties;

import java.util.ArrayList;
import java.util.List;

public class PeopleTable extends Table {
    public PeopleTable() {
        super(new String[]{"first", "middle", "last"}); // adjust this to determine table columns
    }
    @Override
    public void addRow(RetrievableProperties entity) {
        Person person = (Person) entity;
        List<TableRow.Cell> cells = new ArrayList<TableRow.Cell>();
        cells.add(new TableRow.Cell(person.getFirst()));
        cells.add(new TableRow.Cell(person.getMiddle()));
        cells.add(new TableRow.Cell(person.getLast()));
        rows.add(new TableRow(cells));
    }
}
