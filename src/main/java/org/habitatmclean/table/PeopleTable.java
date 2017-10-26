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
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        tableCells.add(new TableRow.TableCell(person.getFirst()));
        tableCells.add(new TableRow.TableCell(person.getMiddle()));
        tableCells.add(new TableRow.TableCell(person.getLast()));
        rows.add(new TableRow(tableCells));
    }
}
