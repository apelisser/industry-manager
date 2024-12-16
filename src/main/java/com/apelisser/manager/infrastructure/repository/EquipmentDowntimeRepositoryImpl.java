package com.apelisser.manager.infrastructure.repository;

import com.apelisser.manager.domain.entity.EquipmentDowntime;
import com.apelisser.manager.domain.repository.EquipmentDowntimeRepositoryQueries;
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

        List<EquipmentDowntime> resultList = query.getResultList();
        return resultList.isEmpty() ?
            Optional.empty() :
            Optional.ofNullable(resultList.getFirst());
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
        List<Predicate> predicates = new ArrayList<>();

        // Define predicates for overlapping downtimes based on start and end times
        Predicate startTimeIsBeforeNewEndTime = builder.lessThan(root.get("startTime"), equipmentDowntime.getEndTime());
        Predicate endTimeIsAfterNewStartTime = builder.greaterThan(root.get("endTime"), equipmentDowntime.getStartTime());

        // Add predicate to ensure the equipment matches
        Predicate isSameEquipment = builder.equal(root.get("equipment"), equipmentDowntime.getEquipment());
        predicates.add(isSameEquipment);

        // Exclude the current record if it is an update
        if (isAnUpdateRecord(equipmentDowntime)) {
            Predicate notId = builder.notEqual(root.get("id"), equipmentDowntime.getId());
            predicates.add(notId);
        }

        // Check for overlapping times depending on the presence of start and end times
        if (startAndEndTimeExists(equipmentDowntime)) {
            // Create predicates to check if existing events overlap with the new event times
            Predicate startTimeIsGreaterOrEqualNewStartTime = builder.greaterThanOrEqualTo(root.get("startTime"), equipmentDowntime.getStartTime());
            Predicate checkStartAndEndOverlaps = builder.and(startTimeIsBeforeNewEndTime, endTimeIsAfterNewStartTime);
            Predicate startTimeBetweenNewEventTime = builder.and(startTimeIsGreaterOrEqualNewStartTime, startTimeIsBeforeNewEndTime);
            Predicate finalOrPredicate = builder.or(checkStartAndEndOverlaps, startTimeBetweenNewEventTime);

            predicates.add(finalOrPredicate);
        } else if (onlyStartTimeExists(equipmentDowntime)) {
            // Create predicates to check if new start time overlaps with existing events
            Predicate startTimesAreTheSame = builder.equal(root.get("startTime"), equipmentDowntime.getStartTime());
            Predicate startTimeIsBeforeNewStartTime = builder.lessThan(root.get("startTime"), equipmentDowntime.getStartTime());
            Predicate newStartTimeBetweenEventTime = builder.and(endTimeIsAfterNewStartTime, startTimeIsBeforeNewStartTime);
            Predicate finalOrPredicate = builder.or(newStartTimeBetweenEventTime, startTimesAreTheSame);

            predicates.add(finalOrPredicate);
        }

        // Apply the predicates to the criteria query
        criteria.where(predicates.toArray(new Predicate[0]));

        // Specify ordering of results by start time
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
