package com.apelisser.manager.application.api.v1.controller;

import com.apelisser.manager.application.api.v1.mapper.PositionInputDisassembler;
import com.apelisser.manager.application.api.v1.mapper.PositionModelAssembler;
import com.apelisser.manager.application.api.v1.mapper.PositionResumeModelAssembler;
import com.apelisser.manager.application.api.v1.model.PositionModel;
import com.apelisser.manager.application.api.v1.model.PositionResumeModel;
import com.apelisser.manager.application.api.v1.model.input.PositionInput;
import com.apelisser.manager.domain.model.Position;
import com.apelisser.manager.domain.service.PositionRegistrationService;
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
@RequestMapping(
    path = "/api/v1/positions",
    produces = MediaType.APPLICATION_JSON_VALUE)
public class PositionController {

    private final PositionInputDisassembler positionDisassembler;
    private final PositionModelAssembler positionAssembler;
    private final PositionResumeModelAssembler positionResumeAssembler;
    private final PositionRegistrationService positionService;

    public PositionController(PositionInputDisassembler positionDisassembler, PositionModelAssembler positionAssembler,
        PositionResumeModelAssembler positionResumeAssembler, PositionRegistrationService positionService) {
        this.positionDisassembler = positionDisassembler;
        this.positionAssembler = positionAssembler;
        this.positionResumeAssembler = positionResumeAssembler;
        this.positionService = positionService;
    }

    @GetMapping
    public List<PositionResumeModel> findAll() {
        List<Position> positions = positionService.findAll();
        return positionResumeAssembler.toCollectionModel(positions);
    }

    @GetMapping("/{positionId}")
    public PositionModel findById(@PathVariable Long positionId) {
        Position position = positionService.findById(positionId);
        return positionAssembler.toModel(position);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PositionModel add(@RequestBody PositionInput positionInput) {
        Position domainPosition = positionDisassembler.toDomainObject(positionInput);
        Position savedPosition = positionService.save(domainPosition);
        return positionAssembler.toModel(savedPosition);
    }

    @PutMapping("/{positionId}")
    public PositionModel update(@PathVariable Long positionId, @RequestBody PositionInput positionInput) {
        Position position = positionService.findById(positionId);
        positionDisassembler.copyToDomainObject(positionInput, position);
        Position updatedPosition = positionService.save(position);
        return positionAssembler.toModel(updatedPosition);
    }

    @DeleteMapping("/{positionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long positionId) {
        positionService.delete(positionId);
    }

}
