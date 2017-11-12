package org.habitatmclean.table;

import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonTable extends Table {
    public PersonTable() {
        super(new String[]{"first", "middle", "last"}, new PersonModal()); // adjust this to determine table columns
    }
    @Override
    public void addRow(GenericEntity entity) {
        Person person = (Person) entity;
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        tableCells.add(new TableRow.TableCell(person.getFirst()));
        tableCells.add(new TableRow.TableCell(person.getMiddle()));
        tableCells.add(new TableRow.TableCell(person.getLast()));
        TableRow tr = new TableRow(tableCells);
        tr.setRowId("" + entity.getId());
        rows.add(tr);
    }

    static class PersonModal extends Modal{

        public PersonModal(){
            super("Person");
        }
        public void buildModal(){
            //we JQuery now
            forms.add(Form.builder().setType("text").setName("firstName").setLabel("First Name").build());
            forms.add(Form.builder().setType("text").setName("middleName").setLabel("Middle Name").setRequired(false).build());
            forms.add(Form.builder().setType("text").setName("lastName").setLabel("Last Name").build());
            forms.add(Form.builder().setType("text").setName("address").setLabel("Address").build());
            forms.add(Form.builder().setType("text").setName("state").setLabel("State").build());
            forms.add(Form.builder().setType("text").setName("zip").setLabel("Zip").build());
            forms.add(Form.builder().setType("text").setName("home-phone").setLabel("Phone Number").build());
        }
    }
}



