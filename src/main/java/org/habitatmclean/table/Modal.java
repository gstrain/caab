package org.habitatmclean.table;

import java.util.ArrayList;
import java.util.List;

public abstract class Modal {
    private String title;
    private String id = "<input type=\"hidden\" name=\"id\" value=\"\">";
    private String action = "<input type='hidden' name='action' value='write'>";
    protected List<Form> forms;

    Modal(String title){
        forms = new ArrayList<Form>();
        this.title = title;
        buildModal();
    }

    public abstract void buildModal();

    //public abstract void getResponse(); //access DB to fill modals

    public String toString(){
        String head = "<div class=\"modal fade\" id=\"record-modal\">\n" +
                "  <div class=\"modal-dialog\">\n" +
                "    <div class=\"modal-content\" action method=\"post\" novalidate>\n" +
                "      <div class=\"modal-header\">\n" +
                "        <h5 class=\"modal-title\">\n" +
                "           <span class=\"record-action\"></span>" + title +
                "        </h5>" +
                "        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n" +
                "          <span aria-hidden=\"true\">&times;</span>\n" +
                "        </button>\n" +
                "      </div>\n";
        String foot = " </div>\n" +
                "      <div class=\"modal-footer\">\n" +
                "        <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n" +
                "        <button type=\"submit\" class=\"btn btn-primary save\">Save changes</button>\n" +
                "        </form>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</div>";
        String content = "<div class=\"modal-body\"><form>\n";
        StringBuilder sb = new StringBuilder();

        sb.append(head);
        sb.append(content);
        for (Form form : forms) {
            sb.append(form.toString());
        }
        sb.append(id);
        sb.append(foot);
        return sb.toString();
    }
}
