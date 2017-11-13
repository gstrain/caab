package org.habitatmclean.table;

import org.habitatmclean.hibernate.functions;

public class Form {
    private String type; //the type of form group, text, select //TODO email, date, maybe checkbox and radio?
    private String name; //name of form as well as label
    private String label;
    private String extraText;
    private boolean required = true;
    private int maxLength = 0;
    //for select types only:
    private String[] options;
    boolean labelAsValue = true;

    public Form(){
    }

    //use a form builder to build Form. high cohesion from keeping the builder inside the class and no where else
    public static FormBuilder builder(){
        return new FormBuilder();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getExtraText() {
        return extraText;
    }

    public void setExtraText(String extraText) {
        this.extraText = extraText;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public String toString() {
        if (functions.checkfor(name, type)) {
            StringBuilder html = new StringBuilder();
            html.append("<div class='form-group'>\n");

            if(functions.checkfor(label)){
                html.append("<label for='"+name+"'>");
                html.append(label);
                html.append("</label>\n");
            }

            if(type.equals("text")) {
                html.append("<input type='" + type + "' ");
                html.append("class='form-control' ");
                html.append("id='" + name + "' ");
                html.append("name='" + name + "' ");
                if (required)
                    html.append("required ");
                html.append("value ");
                if (maxLength > 0)
                    html.append("maxlength='" + maxLength + "' ");
                html.append(">\n");

                if (functions.checkfor(extraText))
                    html.append("<small id='" + name + "-extra" + "' class='form-text text-muted'>" + extraText + "</small>");
            }
            else if(type.equals("select")){
                html.append("select is not ready yet");
            }
            html.append("</div>\n");
            return html.toString();
        }
        else return "not ready yet! name and type required!";
    }
}
