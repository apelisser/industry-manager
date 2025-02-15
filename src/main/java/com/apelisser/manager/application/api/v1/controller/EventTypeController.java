package com.apelisser.manager.application.api.v1.controller;

import com.apelisser.manager.application.api.v1.mapper.EventTypeInputDisassembler;
import com.apelisser.manager.application.api.v1.mapper.EventTypeModelAssembler;
import com.apelisser.manager.application.api.v1.mapper.EventTypeResumeModelAssembler;
import com.apelisser.manager.application.api.v1.model.EventTypeModel;
import com.apelisser.manager.application.api.v1.model.EventTypeResumeModel;
import com.apelisser.manager.application.api.v1.model.input.EventTypeInput;
import com.apelisser.manager.application.api.v1.model.input.EventTypeUpdateInput;
import com.apelisser.manager.domain.model.EventType;
import com.apelisser.manager.domain.service.EventTypeRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/event-types",
    produces = MediaType.APPLICATION_JSON_VALUE)
public class EventTypeController {

    private final EventTypeInputDisassembler eventTypeDisassembler;
    private final EventTypeModelAssembler eventTypeAssembler;
    private final EventTypeResumeModelAssembler eventTypeResumeAssembler;
    private final EventTypeRegistrationService eventTypeService;

    public EventTypeController(EventTypeInputDisassembler eventTypeDisassembler,
            EventTypeModelAssembler eventTypeAssembler, EventTypeResumeModelAssembler eventTypeResumeAssembler,
            EventTypeRegistrationService eventTypeService) {
        this.eventTypeDisassembler = eventTypeDisassembler;
        this.eventTypeAssembler = eventTypeAssembler;
        this.eventTypeResumeAssembler = eventTypeResumeAssembler;
        this.eventTypeService = eventTypeService;
    }

    @GetMapping
    public List<EventTypeResumeModel> findAll() {
        List<EventType> eventTypes = eventTypeService.findAll();
        return eventTypeResumeAssembler.toCollectionModel(eventTypes);
    }

    @GetMapping("/{eventTypeId}")
    public EventTypeModel findById(@PathVariable String eventTypeId) {
        EventType eventType = eventTypeService.findById(eventTypeId);
        return eventTypeAssembler.toModel(eventType);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EventTypeModel add(@RequestBody EventTypeInput eventType) {
        EventType domainEventType = eventTypeDisassembler.toDomainObject(eventType);
        EventType savedEventType = eventTypeService.save(domainEventType);
        return eventTypeAssembler.toModel(savedEventType);
    }

    @PutMapping("/{eventTypeId}")
    public EventTypeModel update(@PathVariable String eventTypeId, @RequestBody EventTypeUpdateInput eventTypeInput) {
        EventType eventType = eventTypeService.findById(eventTypeId);
        eventTypeDisassembler.copyToDomainObject(eventTypeInput, eventType);
        EventType updatedEventType = eventTypeService.save(eventType);
        return eventTypeAssembler.toModel(updatedEventType);
    }

    @DeleteMapping("/{eventTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String eventTypeId) {
        eventTypeService.delete(eventTypeId);
    }

}
