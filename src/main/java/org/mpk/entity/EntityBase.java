package org.mpk.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

public abstract class EntityBase<R extends PanacheEntityBase, L extends Number> extends PanacheEntityBase {
    public static String hello = "hello from EntityBase :)";
}