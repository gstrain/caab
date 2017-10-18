package org.habitatmclean.entity;

import javax.management.relation.Relation;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="actor")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(
        name = "actor_type",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("A")
public class Actor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actor_id;

    @ManyToOne(optional = true)
    @JoinColumn(name="relation_id")
    private RelationType relationType;

    public Actor() { }

    public Actor(Long actor_id) {
        this.actor_id = actor_id;
    }

    public Actor(Long actor_id, RelationType relationType) {
        this.actor_id = actor_id;
        this.relationType = relationType;
    }

    @Column(name="actor_id")
    public Long getActor_id() {
        return actor_id;
    }

    public void setActor_id(Long actor_id) {
        this.actor_id = actor_id;
    }

    public RelationType getRelationType() {
        return relationType;
    }

    public void setRelationType(RelationType relationType) {
        this.relationType = relationType;
    }
}
