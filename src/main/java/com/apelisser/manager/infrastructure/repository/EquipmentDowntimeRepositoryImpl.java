package com.apelisser.manager.infrastructure.repository;

import com.apelisser.manager.domain.entities.EquipmentDowntime;
import com.apelisser.manager.domain.repositories.EquipmentDowntimeRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EquipmentDowntimeRepositoryImpl implements EquipmentDowntimeRepositoryQueries {

    private final EntityManager manager;

    public EquipmentDowntimeRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Optional<EquipmentDowntime> findFirstOverlap(EquipmentDowntime equipmentDowntime) {
        CriteriaQuery<EquipmentDowntime> criteria = createCriteria(equipmentDowntime);

        TypedQuery<EquipmentDowntime> query = manager.createQuery(criteria);
        query.setMaxResults(1);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<EquipmentDowntime> findAllOverlaps(EquipmentDowntime equipmentDowntime) {
        CriteriaQuery<EquipmentDowntime> criteria = createCriteria(equipmentDowntime);

        TypedQuery<EquipmentDowntime> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    private CriteriaQuery<EquipmentDowntime> createCriteria(EquipmentDowntime equipmentDowntime) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<EquipmentDowntime> criteria = builder.createQuery(EquipmentDowntime.class);
        Root<EquipmentDowntime> root = criteria.from(EquipmentDowntime.class);

        Predicate startTimeIsBeforeNewEndTime = builder.lessThan(root.get("startTime"), equipmentDowntime.getEndTime());
        Predicate endTimeIsAfterNewStartTime = builder.greaterThan(root.get("endTime"), equipmentDowntime.getStartTime());

        List<Predicate> predicates = new ArrayList<>();

        Predicate isSameEquipment = builder.equal(root.get("equipment"), equipmentDowntime.getEquipment());
        predicates.add(isSameEquipment);

        if (isAnUpdateRecord(equipmentDowntime)) {
            // OK :: se for update: desconsiderar a si proprio na consulta
            Predicate notId = builder.notEqual(root.get("id"), equipmentDowntime.getId());
            predicates.add(notId);
        }

        if (startAndEndTimeExists(equipmentDowntime)) {
            // OK :: se existir inicio e fim no registro a ser salvo
            Predicate startTimeIsGreaterOrEqualNewStartTime = builder.greaterThanOrEqualTo(root.get("startTime"), equipmentDowntime.getStartTime());

            Predicate checkStartAndEndOverlaps = builder.and(startTimeIsBeforeNewEndTime, endTimeIsAfterNewStartTime);
            Predicate startTimeBetweenNewEventTime = builder.and(startTimeIsGreaterOrEqualNewStartTime, startTimeIsBeforeNewEndTime);
            Predicate finalOrPredicate = builder.or(checkStartAndEndOverlaps, startTimeBetweenNewEventTime);
            predicates.add(finalOrPredicate);
        } else if (onlyStartTimeExists(equipmentDowntime)) {
            // OK :: se existir apenas o inicio no registro a ser salvo
            Predicate startTimeEquals = builder.equal(root.get("startTime"), equipmentDowntime.getStartTime());

            Predicate startTimeIsBeforeNewStartTime = builder.lessThan(root.get("startTime"), equipmentDowntime.getStartTime());
            Predicate newStartTimeBetweenEventTime = builder.and(endTimeIsAfterNewStartTime, startTimeIsBeforeNewStartTime);

            Predicate finalOrPredicate = builder.or(newStartTimeBetweenEventTime, startTimeEquals);
            predicates.add(finalOrPredicate);
        }

        criteria.where(predicates.toArray(new Predicate[0]));
        criteria.orderBy(builder.asc(root.get("startTime")));

        return criteria;
    }

    private boolean isAnUpdateRecord(EquipmentDowntime equipmentDowntime) {
        return equipmentDowntime.getId() != null;
    }

    private boolean startAndEndTimeExists(EquipmentDowntime equipmentDowntime) {
        return equipmentDowntime.getStartTime() != null && equipmentDowntime.getEndTime() != null;
    }

    private boolean onlyStartTimeExists(EquipmentDowntime equipmentDowntime) {
        return equipmentDowntime.getStartTime() != null;
    }

}
