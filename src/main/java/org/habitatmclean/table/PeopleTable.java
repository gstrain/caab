package org.habitatmclean.table;

import org.habitatmclean.entity.Person;
import org.habitatmclean.entity.RetrievableProperties;

import java.util.ArrayList;
import java.util.List;

public class PeopleTable extends Table {
    public PeopleTable() {
        super(new String[]{"first", "middle", "last"}, new PeopleModal()); // adjust this to determine table columns
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

    static class PeopleModal extends Modal{

        public PeopleModal(){
            super("Person");
        }

        public void buildModal(){
            //we JQuery now
            forms.add(Form.builder().setType("text").setName("firstName").setLabel("First Name").build());
            forms.add(Form.builder().setType("text").setName("middleName").setLabel("Middle Name").setRequired(false).build());
            forms.add(Form.builder().setType("text").setName("lastName").setLabel("Last Name").build());
        }
    }
}



