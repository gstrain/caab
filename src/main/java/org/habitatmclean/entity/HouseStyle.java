package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="house_style")
public class HouseStyle implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long style_id;

    private String style;

    public HouseStyle() { }

    public HouseStyle(Long style_id, String style) {
        this.style_id = style_id;
        this.style = style;
    }

    public HouseStyle(String style) {
        this.style = style;
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
}
