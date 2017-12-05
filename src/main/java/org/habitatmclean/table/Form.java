package org.habitatmclean.table;

import org.habitatmclean.dao.GenericDao;
import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.hibernate.Functions;
import org.habitatmclean.hibernate.HibernateAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;

public class Form {
    private String type; //the type of form group: text, tel, email, date. //TODO SELECT
    private String name; //name of form. If you want it to load form table, it MUST match the name of the entity value it connects to
    private String label; // label for form control
    private String extraText;
    private boolean required = true;
    private int maxLength = 0;
    private boolean fromTable = false;
    private String fillTable;
    private String parent;
    private boolean hasParent;
    private String selectLabel;
    private String path;
    private boolean hasPath;
    private String selectValue = "id";
    //for select types only:
    private Map<String,String> options = new HashMap<>();
    boolean labelAsValue = false; //label and value of select will be the same. false by default

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

    public void setParent(String parent) {this.parent = parent; this.hasParent=true; }

    public void setPath(String path) {this.path = path; hasPath = true; }
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

    // name it according to class names
    public void setFromTable(String table, String label, String value){
        fillTable = table;
        fromTable = true;
        selectLabel = label;
        selectValue = value;
    }

    public void addOption(String label, String value){
        options.put(label,value);
    }

    //when using label as value
    public void addOption(String label){
        options.put(label,label);
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
        if (Functions.checkfor(name, type)) {
            StringBuilder html = new StringBuilder();
            html.append("<div class='form-group'>\n");

            if(Functions.checkfor(label)){
                html.append("<label for='"+name+"'>");
                html.append(label);
                html.append("</label>\n");
            }

            if(!type.equals("select")) {
                html.append("<input type='" + type + "' ");
                html.append("class='form-control' ");
                html.append("id='" + name + "' ");
                html.append("name='" + name + "' " + ( hasParent? "data-value-parent='" + parent+"'":"") + path());
                if (required)
                    html.append("required ");
                html.append("value ");
                if (maxLength > 0)
                    html.append("maxlength='" + maxLength + "' ");
                html.append(">\n");

                if (Functions.checkfor(extraText))
                    html.append("<small id='" + name + "-extra" + "' class='form-text text-muted'>" + extraText + "</small>");
            }
            else if(type.equals("select")){
                html.append("<select class='form-control' id='" + name + "' name='"+name+"'"+ (fromTable ? "data-value-drop='" + selectValue+"'":"") +(required ? " required " : "") + path() +">\n");
                html.append("<option selected value disabled> Choose a "+label.toLowerCase()+"</option>\n");
                if(fromTable)
                    fillTable();
                if(!options.isEmpty()) {
                    for (Map.Entry<String, String> entry : options.entrySet()) {
                        String key = entry.getKey();
                        String value;
                        if(labelAsValue)
                            value = key;
                        else
                            value = entry.getValue();
                        html.append("<option value='"+value+"'>"+key+"</option>");
                    }
                }
                html.append("</select>");
            }
            html.append("</div>\n");
            return html.toString();
        }
        else return "not ready yet! name and type required!";
    }

    private void fillTable(){
        //SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //sessionFactory.getCurrentSession().beginTransaction();

        GenericDao dao = HibernateAdapter.getBoByEntityName(fillTable);
        SortedSet<GenericEntity> records = dao.findAll();
        for(GenericEntity record : records) {
            String label = record.getValueByPropertyName(selectLabel);
            String value = record.getValueByPropertyName(selectValue);
            addOption(label,value);
        }
        // we are already in a transaction.
        //sessionFactory.getCurrentSession().getTransaction().commit();
    }

    public String path(){
        return (hasPath ? " data-value-path='"+path+"'" : "");
    }
}
