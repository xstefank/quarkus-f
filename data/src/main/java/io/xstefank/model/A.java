package io.xstefank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * This is an entity class that is just here
 * to ensure that Hibernate gets activated.
 *
 * See https://github.com/quarkusio/quarkus/issues/7148 for future fix.
 */
@Entity
public class A {
    @Id
    public Long id;
}
