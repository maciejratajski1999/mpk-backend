package org.mpk.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import org.mpk.entity.EntityBase;

public class BaseRepository<T extends EntityBase> implements PanacheRepository {
}
