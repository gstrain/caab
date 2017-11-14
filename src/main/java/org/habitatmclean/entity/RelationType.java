package org.habitatmclean.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.io.Serializable;
import java.util.SortedSet;

@Entity
@Table(name="relation_type")
@AttributeOverride(name="id", column = @Column(name="relation_id"))
public class RelationType extends GenericEntity implements Serializable {

    private String relation_name;
    private String relation_desc;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "relationType")
    @SortNatural
    @Fetch(FetchMode.SUBSELECT)
    private SortedSet<Actor> actors;

    public RelationType() { }

    public RelationType(Long id, String relation_name, String relation_desc) {
        this.id = id;
        this.relation_name = relation_name;
        this.relation_desc = relation_desc;
    }

    public RelationType(String relation_name, String relation_desc) {
        this.relation_name = relation_name;
        this.relation_desc = relation_desc;
    }

    public SortedSet<Actor> getActors() {
        return actors;
    }

    public void setActors(SortedSet<Actor> actors) {
        this.actors = actors;
    }

    @Column(name="relation_name")
    public String getRelation_name() {
        return relation_name;
    }

    public void setRelation_name(String relation_name) {
        this.relation_name = relation_name;
    }

    @Column(name="relation_desc")
    public String getRelation_desc() {
        return relation_desc;
    }

    public void setRelation_desc(String relation_desc) {
        this.relation_desc = relation_desc;
    }

    @Override
    public String getValueByPropertyName(String property) {
        switch(property) {
            case "relation_id":
            case "id":
                return "" + getId();
            case "relation_name":
                return "" + getRelation_name();
            case "relation_desc":
                return "" + getRelation_desc();
            default:
                return "invalid property specifier";
        }
    }
}
