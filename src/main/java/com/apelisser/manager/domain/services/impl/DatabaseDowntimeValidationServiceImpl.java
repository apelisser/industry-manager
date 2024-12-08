package com.apelisser.manager.domain.services.impl;

import com.apelisser.manager.domain.entities.EquipmentDowntime;
import com.apelisser.manager.domain.exceptions.OverlapException;
import com.apelisser.manager.domain.services.DatabaseDowntimeValidationService;
import com.apelisser.manager.domain.services.EquipmentDowntimeRegistrationService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseDowntimeValidationServiceImpl implements DatabaseDowntimeValidationService {

    private static final String OVERLAP_EVENT_MESSAGE_TEMPLATE = "Overlap detected in registered event: " +
        "[Event ID: %d, Start: %s, End: %s] overlaps with [Event ID: %d, Start: %s, End: %s].";

    private final EquipmentDowntimeRegistrationService downtimeService;

    public DatabaseDowntimeValidationServiceImpl(@Lazy EquipmentDowntimeRegistrationService downtimeService) {
        this.downtimeService = downtimeService;
    }

    @Override
    public void validate(EquipmentDowntime equipmentDowntime) {
        findOverlap(equipmentDowntime).ifPresent(downtimeFound ->
            throwOverlapException(downtimeFound, equipmentDowntime));
    }

    private Optional<EquipmentDowntime> findOverlap(EquipmentDowntime equipmentDowntime) {
        return downtimeService.findFirstOverlap(equipmentDowntime);
    }

    private void throwOverlapException(EquipmentDowntime downtime, EquipmentDowntime newDowntime) {
        String message = String.format(OVERLAP_EVENT_MESSAGE_TEMPLATE,
            newDowntime.getEvent().getId(),
            newDowntime.getStartTime(),
            newDowntime.getEndTime(),
            downtime.getEvent().getId(),
            downtime.getStartTime(),
            downtime.getEndTime());
        throw new OverlapException(message);
    }

}
