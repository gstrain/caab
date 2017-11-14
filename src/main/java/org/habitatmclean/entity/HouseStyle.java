package org.habitatmclean.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.io.Serializable;
import java.util.SortedSet;

@Entity
@Table(name="house_style")
@AttributeOverride(name="id", column = @Column(name="style_id"))
public class HouseStyle extends GenericEntity implements Serializable {

    private String style;
    private String style_desc;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "house_style")
    @SortNatural
    @Fetch(FetchMode.SUBSELECT)
    private SortedSet<House> houses;

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

    public SortedSet<House> getHouses() {
        return houses;
    }

    public void setHouses(SortedSet<House> houses) {
        this.houses = houses;
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
            case "style_id":
            case "id":
                return "" + getId();
            case "style":
                return "" + getStyle();
            case "style_desc":
                return "" + getStyle_desc();
            default:
                return "invalid property specifier";
        }
    }
}
