package org.habitatmclean.table;

import java.util.ArrayList;
import java.util.List;

public abstract class Modal {
    private String title;
    private String id = "<input type='hidden' name='item-id' value=''>";
    protected List<Form> forms;

    protected Modal(String title){
        forms = new ArrayList<Form>();
        this.title = title;
        buildModal();
    }

    public abstract void buildModal();

    //public abstract void getResponse(); //access DB to fill modals

    public List<String> returnFields() {
        List<String> fields = new ArrayList<String>();
        for(Form i : forms){
            fields.add(i.getName());
        }
        return fields;
    }

    public String toString(){
        String head = "<div class='modal fade' id='record-modal'>\n" +
                "  <div class='modal-dialog'>\n" +
                "    <form class='modal-content' action='/dbservlet' method='post'>\n" +
                "      <div class='modal-header'>\n" +
                "        <h5 class='modal-title'>\n" +
                "           <span class='record-action'></span>" + title +
                "        </h5>" +
                "        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>\n" +
                "          <span aria-hidden='true'>&times;</span>\n" +
                "        </button>\n" +
                "      </div>\n";
        String foot = " </div>\n" +
                "      <div class='modal-footer'>\n" +
                "        <button type='button' class='btn btn-secondary' data-dismiss='modal'>Close</button>\n" +
                "        <button type='submit' class='btn btn-primary save' id='modal-submit'>Save changes</button>\n" +
                "           " + id +
                "        </form>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</div>";
        String content = "<div class='modal-body'><form>\n";
        StringBuilder sb = new StringBuilder();

        sb.append(head);
        sb.append(content);
        for (Form form : forms) {
            sb.append(form.toString());
        }
        sb.append(foot);
        return sb.toString();
    }
}
