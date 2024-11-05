package com.apelisser.manager.api.v1.controller;

import com.apelisser.manager.api.v1.mapper.EventInputDisassembler;
import com.apelisser.manager.api.v1.mapper.EventModelAssembler;
import com.apelisser.manager.api.v1.mapper.EventResumeModelAssembler;
import com.apelisser.manager.api.v1.model.EventModel;
import com.apelisser.manager.api.v1.model.EventResumeModel;
import com.apelisser.manager.api.v1.model.input.EventChildrenInput;
import com.apelisser.manager.api.v1.model.input.EventRootInput;
import com.apelisser.manager.api.v1.model.input.EventUpdateInput;
import com.apelisser.manager.domain.entities.Event;
import com.apelisser.manager.domain.services.EventRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
    path = "/api/v1/events",
    produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {

    private final EventRegistrationService eventService;
    private final EventResumeModelAssembler eventResumeAssembler;
    private final EventModelAssembler eventAssembler;
    private final EventInputDisassembler eventDisassembler;

    public EventController(EventRegistrationService eventService, EventResumeModelAssembler eventResumeAssembler,
            EventModelAssembler eventAssembler, EventInputDisassembler eventDisassembler) {
        this.eventService = eventService;
        this.eventResumeAssembler = eventResumeAssembler;
        this.eventAssembler = eventAssembler;
        this.eventDisassembler = eventDisassembler;
    }

    @GetMapping("/roots")
    public List<EventResumeModel> findAllRoots() {
        List<Event> events = eventService.findAllRoots();
        return eventResumeAssembler.toCollectionModel(events);
    }

    @GetMapping("/{eventId}/children")
    public List<EventResumeModel> findChildren(@PathVariable Long eventId) {
        List<Event> children = eventService.findAllChildren(eventId);
        return eventResumeAssembler.toCollectionModel(children);
    }

    @GetMapping("/{eventId}")
    public EventModel findById(@PathVariable Long eventId) {
        Event event = eventService.findById(eventId);
        return eventAssembler.toModel(event);
    }

    @PostMapping(path = "/roots", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EventModel addRoot(@RequestBody EventRootInput eventInput) {
        Event domainEvent = eventDisassembler.toDomainObject(eventInput);
        Event savedEvent = eventService.save(domainEvent);
        return eventAssembler.toModel(savedEvent);
    }

    @PostMapping(path = "/{eventId}/children", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EventModel addChildren(@PathVariable Long eventId, @RequestBody EventChildrenInput eventInput) {
        Event domainEvent = eventDisassembler.toDomainObject(eventInput);
        Event event = eventService.findById(eventId);
        domainEvent.setParent(event);
        domainEvent.setCompany(event.getCompany());
        Event savedEvent = eventService.save(domainEvent);
        return eventAssembler.toModel(savedEvent);
    }

    @PutMapping("/{eventId}")
    public EventModel update(@PathVariable Long eventId, @RequestBody EventUpdateInput eventInput) {
        Event event = eventService.findById(eventId);
        eventDisassembler.copyToDomainObject(eventInput, event);
        Event updatedEvent = eventService.save(event);
        return eventAssembler.toModel(updatedEvent);
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long eventId) {
        eventService.delete(eventId);
    }

    @PatchMapping("/{eventId}/inactivation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivate(@PathVariable Long eventId) {
        eventService.inactivate(eventId);
    }

    @PatchMapping("/{eventId}/activation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long eventId) {
        eventService.activate(eventId);
    }

}
