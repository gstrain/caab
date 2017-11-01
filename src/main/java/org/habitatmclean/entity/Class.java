package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="class")
public class Class implements Serializable, RetrievableProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long class_id;

    private String class_name;
    private String class_desc;

    public Class() { }

    public Class(Long class_id, String class_name, String class_desc) {
        this.class_id = class_id;
        this.class_name = class_name;
        this.class_desc = class_desc;
    }

    @Column(name="class_id")
    public Long getClass_id() {
        return class_id;
    }

    public void setClass_id(Long class_id) {
        this.class_id = class_id;
    }

    @Column(name="class_name")
    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    @Column(name="class_desc")
    public String getClass_desc() {
        return class_desc;
    }

    public void setClass_desc(String class_desc) {
        this.class_desc = class_desc;
    }

    @Override
    public String getValueByPropertyName(String property) {
        switch(property) {
            case "id":
                return "" + getClass_id();
            case "class_name":
                return "" + getClass_name();
            case "class_desc":
                return "" + getClass_desc();
            default:
                return "invalid property specifier";
        }
    }

}
