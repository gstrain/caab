package org.habitatmclean.table;

public class PeopleModal extends Modal{

    public PeopleModal(){
        super("Person");
    }

    public void buildModal(){
        //we JQuery now
        forms.add(Form.builder().setType("text").setName("firstName").setLabel("First Name").build());
        forms.add(Form.builder().setType("text").setName("middleName").setLabel("Middle Name").build());
        forms.add(Form.builder().setType("text").setName("lastName").setLabel("Last Name").build());
    }
}
