package com.apelisser.manager.api.v1.controller;

import java.util.List;

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

import com.apelisser.manager.api.v1.mapper.StateInputDisassembler;
import com.apelisser.manager.api.v1.mapper.StateModelAssembler;
import com.apelisser.manager.api.v1.model.StateModel;
import com.apelisser.manager.api.v1.model.input.StateInput;
import com.apelisser.manager.domain.entities.State;
import com.apelisser.manager.domain.services.StateRegistrationService;

@RestController
@RequestMapping(
    path = "/api/v1/states",
    produces = MediaType.APPLICATION_JSON_VALUE)
public class StateController {

    private final StateInputDisassembler stateDisassembler;
    private final StateModelAssembler stateAssembler;
    private final StateRegistrationService stateService;

    public StateController(StateInputDisassembler stateDisassembler, StateModelAssembler stateAssembler,
        StateRegistrationService stateService) {
        this.stateDisassembler = stateDisassembler;
        this.stateAssembler = stateAssembler;
        this.stateService = stateService;
    }

    @GetMapping
    public List<StateModel> findAll() {
        List<State> states = stateService.findAll();
        return stateAssembler.toCollectionModel(states);
    }

    @GetMapping("/{stateId}")
    public StateModel findById(@PathVariable Long stateId) {
        State state = stateService.findById(stateId);
        return stateAssembler.toModel(state);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public StateModel add(@RequestBody StateInput stateInput) {
        State domainState = stateDisassembler.toDomainObject(stateInput);
        State savedState = stateService.save(domainState);
        return stateAssembler.toModel(savedState);
    }

    @PutMapping("/{stateId}")
    public StateModel update(@PathVariable Long stateId, @RequestBody StateInput stateInput) {
        State state = stateService.findById(stateId);
        stateDisassembler.copyToDomainObject(stateInput, state);
        State updatedState = stateService.save(state);
        return stateAssembler.toModel(updatedState);
    }

    @DeleteMapping("/{stateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long stateId) {
        stateService.delete(stateId);
    }

}
