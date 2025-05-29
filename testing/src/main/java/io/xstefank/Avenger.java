package io.xstefank;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Avenger extends PanacheEntity {

    public String name;
    public String civilname;
    public boolean snapped;
}
