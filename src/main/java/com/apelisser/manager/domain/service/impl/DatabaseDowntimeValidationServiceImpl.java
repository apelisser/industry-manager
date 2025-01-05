package com.apelisser.manager.domain.service.impl;

import com.apelisser.manager.domain.model.EquipmentDowntime;
import com.apelisser.manager.domain.exception.EventOverlapException;
import com.apelisser.manager.domain.service.DatabaseDowntimeValidationService;
import com.apelisser.manager.domain.service.EquipmentDowntimeRegistrationService;
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
        throw new EventOverlapException(message);
    }

}
