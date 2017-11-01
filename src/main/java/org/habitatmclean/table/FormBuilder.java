package org.habitatmclean.table;

public class FormBuilder {
    private Form form;

    public FormBuilder(){
        form = new Form();
    }

    //TODO some sort of catch so that a form will not be built without type and name
    public Form build(){
        return form;
    }

    public FormBuilder setType(String type){
        form.setType(type);
        return this;
    }

    public FormBuilder setName(String name){
        form.setName(name);
        return this;
    }

    public FormBuilder setLabel(String label){
        form.setLabel(label);
        return this;
    }

    public FormBuilder setExtraText(String extraText){
        form.setExtraText(extraText);
        return this;
    }

    public FormBuilder setRequired(Boolean required){
        form.setRequired(required);
        return this;
    }

    public FormBuilder setMaxLength(int maxLength){
        form.setMaxLength(maxLength);
        return this;
    }
}
