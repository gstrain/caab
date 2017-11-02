package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="house_style")
public class HouseStyle implements Serializable, RetrievableProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long style_id;

    private String style;
    private String style_desc;

    public HouseStyle() { }

    public HouseStyle(Long style_id, String style, String style_desc) {
        this.style_id = style_id;
        this.style = style;
        this.style_desc = style_desc;
    }

    public HouseStyle(String style, String style_desc) {
        this.style = style;
        this.style_desc = style_desc;
    }

    @Column(name="style_id")
    public Long getStyle_id() {
        return style_id;
    }

    public void setStyle_id(Long style_id) {
        this.style_id = style_id;
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

    @Override
    public String getValueByPropertyName(String property) {
        switch(property) {
            case "id":
                return "" + getStyle_id();
            case "style":
                return "" + getStyle();
            case "style_desc":
                return "" + getStyle_desc();
            default:
                return "invalid property specifier";
        }
    }
}
