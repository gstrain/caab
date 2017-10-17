package org.habitatmclean.entity;

import javax.persistence.*;

@Entity
@Table(name="actor")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "actor_type",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("A")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actor_id;

    public Actor() { }

    public Actor(Long actor_id) {
        this.actor_id = actor_id;
    }

    @Column(name="actor_id")
    public Long getActor_id() {
        return actor_id;
    }

    public void setActor_id(Long actor_id) {
        this.actor_id = actor_id;
    }
}
