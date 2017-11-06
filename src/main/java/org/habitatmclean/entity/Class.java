package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="class")
@AttributeOverride(name="id", column = @Column(name="class_id"))
public class Class extends GenericEntity implements Serializable {

    private String class_name;
    private String class_desc;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "classType")
    private List<Family> families;

    public Class() { }

    public Class(Long id, String class_name, String class_desc) {
        this.id = id;
        this.class_name = class_name;
        this.class_desc = class_desc;
    }

    public List<Family> getFamilies() {
        return families;
    }

    public void setFamilies(List<Family> families) {
        this.families = families;
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

}
