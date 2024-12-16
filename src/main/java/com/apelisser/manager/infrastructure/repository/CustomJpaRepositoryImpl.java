package com.apelisser.manager.infrastructure.repository;

import com.apelisser.manager.domain.repository.CustomJpaRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;
import java.util.Optional;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>
        implements CustomJpaRepository<T, ID> {

    private final EntityManager manager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.manager = entityManager;
    }

    @Override
    public Optional<T> findFirst() {
        String jpql = "from " + getDomainClass().getName();

        T entity = manager.createQuery(jpql, getDomainClass())
            .setMaxResults(1)
            .getSingleResult();

        return Optional.ofNullable(entity);
    }

    @Override
    public void detach(T entity) {
        manager.detach(entity);
    }

    @Override
    public void detach(List<T> entities) {
        if (entities != null && !entities.isEmpty()) {
            entities.forEach(manager::detach);
        }
    }

}
