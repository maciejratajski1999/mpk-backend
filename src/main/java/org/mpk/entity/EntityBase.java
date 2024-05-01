package org.mpk.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import java.util.Map;

public abstract class EntityBase extends PanacheEntityBase {
    public static String hello = "hello from EntityBase :)";

    public abstract void populateFromGTFS(Map<String, String> entry);
}
