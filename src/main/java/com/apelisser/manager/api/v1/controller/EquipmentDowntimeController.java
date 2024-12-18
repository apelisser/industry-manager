package com.apelisser.manager.api.v1.controller;

import com.apelisser.manager.api.v1.mapper.EquipmentDowntimeInputDisassembler;
import com.apelisser.manager.api.v1.mapper.EquipmentDowntimeModelAssembler;
import com.apelisser.manager.api.v1.mapper.EventTimeInputDisassembler;
import com.apelisser.manager.api.v1.model.EquipmentDowntimeModel;
import com.apelisser.manager.api.v1.model.input.EquipmentDowntimeInput;
import com.apelisser.manager.api.v1.model.input.EventTimeInput;
import com.apelisser.manager.domain.model.EquipmentDowntime;
import com.apelisser.manager.domain.model.EventTime;
import com.apelisser.manager.domain.service.EquipmentDowntimeRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/equipments/downtimes",
    produces = MediaType.APPLICATION_JSON_VALUE)
public class EquipmentDowntimeController {

    private final EquipmentDowntimeRegistrationService downtimeService;
    private final EquipmentDowntimeModelAssembler downtimeAssembler;
    private final EquipmentDowntimeInputDisassembler downtimeDisassembler;
    private final EventTimeInputDisassembler eventTimeDisassembler;

    public EquipmentDowntimeController(EquipmentDowntimeRegistrationService downtimeService,
            EquipmentDowntimeModelAssembler downtimeAssembler, EquipmentDowntimeInputDisassembler downtimeDisassembler,
            EventTimeInputDisassembler eventTimeDisassembler) {
        this.downtimeService = downtimeService;
        this.downtimeDisassembler = downtimeDisassembler;
        this.downtimeAssembler = downtimeAssembler;
        this.eventTimeDisassembler = eventTimeDisassembler;
    }

    @GetMapping
    public List<EquipmentDowntimeModel> findAll() {
        List<EquipmentDowntime> equipmentDowntimes = downtimeService.findAll();
        return downtimeAssembler.toCollectionModel(equipmentDowntimes);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EquipmentDowntimeModel add(@RequestBody EquipmentDowntimeInput downtimeInput) {
        EquipmentDowntime domainObject = downtimeDisassembler.toDomainObject(downtimeInput);
        EquipmentDowntime savedDowntime = downtimeService.save(domainObject);
        return downtimeAssembler.toModel(savedDowntime);
    }

    @PostMapping(path = "/{equipmentDowntimeId}/related-events", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EquipmentDowntimeModel addRelatedEvent(@PathVariable Long equipmentDowntimeId,
            @RequestBody List<EventTimeInput> eventsTimeInput) {
        List<EventTime> eventsTime = eventTimeDisassembler.toCollectionDomain(eventsTimeInput);
        EquipmentDowntime updatedDowntime = downtimeService.addRelatedEventsTime(equipmentDowntimeId, eventsTime);
        return downtimeAssembler.toModel(updatedDowntime);
    }

    @DeleteMapping(path = "/{equipmentDowntimeId}/related-events/{eventTimeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRelatedEvent(@PathVariable Long equipmentDowntimeId,
            @PathVariable Long eventTimeId) {
        downtimeService.deleteRelatedEventTime(equipmentDowntimeId, eventTimeId);
    }

    @DeleteMapping(path = "/{equipmentDowntimeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long equipmentDowntimeId) {
        downtimeService.delete(equipmentDowntimeId);
    }

}
