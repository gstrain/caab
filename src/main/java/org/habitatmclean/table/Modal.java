package org.habitatmclean.table;

public class Modal {
    private String head;
    private String foot = " </div>\n" +
            "      <div class=\"modal-footer\">\n" +
            "        <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n" +
            "        <button type=\"submit\" class=\"btn btn-primary save\">Save changes</button>\n" +
            "        </form>\n" +
            "      </div>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</div>";
    private String content = "<div class=\"modal-body\"><form>";
    Modal(String title){
        head = "<div class=\"modal fade\" id=\"record-modal\">\n" +
                "  <div class=\"modal-dialog\">\n" +
                "    <div class=\"modal-content\" action method=\"post\" novalidate>\n" +
                "      <div class=\"modal-header\">\n" +
                "        <h5 class=\"modal-title\">\n" +
                "           <span class=\"record-action\"></span>" + title +
                "        </h5>" +
                "        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n" +
                "          <span aria-hidden=\"true\">&times;</span>\n" +
                "        </button>\n" +
                "      </div>";
    }

    public String toString(){
        return head + content + foot;
    }
}
