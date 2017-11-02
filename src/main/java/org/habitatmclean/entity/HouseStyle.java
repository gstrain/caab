package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="house_style")
@AttributeOverride(name="id", column = @Column(name="style_id"))
public class HouseStyle extends GenericEntity implements Serializable {

    private String style;
    private String style_desc;

    public HouseStyle() { }

    public HouseStyle(Long id, String style, String style_desc) {
        this.id = id;
        this.style = style;
        this.style_desc = style_desc;
    }

    public HouseStyle(String style, String style_desc) {
        this.style = style;
        this.style_desc = style_desc;
    }

    @Column(name="style")
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Column(name="style_desc")
    public String getStyle_desc() {
        return style_desc;
    }

    public void setStyle_desc(String style_desc) {
        this.style_desc = style_desc;
    }
}
