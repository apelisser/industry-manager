package com.apelisser.manager.api.v1.controller;

import com.apelisser.manager.api.v1.mapper.EventReasonInputDisassembler;
import com.apelisser.manager.api.v1.mapper.EventReasonModelAssembler;
import com.apelisser.manager.api.v1.mapper.EventReasonResumeModelAssembler;
import com.apelisser.manager.api.v1.model.EventReasonModel;
import com.apelisser.manager.api.v1.model.EventReasonResumeModel;
import com.apelisser.manager.api.v1.model.input.EventReasonInput;
import com.apelisser.manager.domain.model.EventReason;
import com.apelisser.manager.domain.service.EventReasonRegistrationService;
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
@RequestMapping(value = "/api/v1/event-reasons",
    produces = MediaType.APPLICATION_JSON_VALUE)
public class EventReasonController {

    private final EventReasonInputDisassembler eventReasonDisassembler;
    private final EventReasonModelAssembler eventReasonAssembler;
    private final EventReasonResumeModelAssembler eventReasonResumeAssembler;
    private final EventReasonRegistrationService eventReasonService;

    public EventReasonController(EventReasonInputDisassembler eventReasonDisassembler,
            EventReasonModelAssembler eventReasonAssembler, EventReasonResumeModelAssembler eventReasonResumeAssembler,
            EventReasonRegistrationService eventReasonService) {
        this.eventReasonDisassembler = eventReasonDisassembler;
        this.eventReasonAssembler = eventReasonAssembler;
        this.eventReasonResumeAssembler = eventReasonResumeAssembler;
        this.eventReasonService = eventReasonService;
    }

    @GetMapping
    public List<EventReasonResumeModel> findAll() {
        List<EventReason> eventReasons = eventReasonService.findAll();
        return eventReasonResumeAssembler.toCollectionModel(eventReasons);
    }

    @GetMapping("/{eventReasonId}")
    public EventReasonModel findById(@PathVariable Long eventReasonId) {
        EventReason eventReason = eventReasonService.findById(eventReasonId);
        return eventReasonAssembler.toModel(eventReason);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EventReasonModel add(@RequestBody EventReasonInput eventReasonInput) {
        EventReason domainEventReason = eventReasonDisassembler.toDomainObject(eventReasonInput);
        EventReason savedEventReason = eventReasonService.save(domainEventReason);
        return eventReasonAssembler.toModel(savedEventReason);
    }

    @DeleteMapping("/{eventReasonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long eventReasonId) {
        eventReasonService.delete(eventReasonId);
    }

}
