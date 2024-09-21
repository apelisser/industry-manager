package com.apelisser.manager.api.v1.controller;

import com.apelisser.manager.api.v1.mapper.EquipmentInputDisassembler;
import com.apelisser.manager.api.v1.mapper.EquipmentModelAssembler;
import com.apelisser.manager.api.v1.mapper.EquipmentResumeModelAssembler;
import com.apelisser.manager.api.v1.model.EquipmentModel;
import com.apelisser.manager.api.v1.model.EquipmentResumeModel;
import com.apelisser.manager.api.v1.model.input.EquipmentInput;
import com.apelisser.manager.api.v1.model.input.EquipmentUpdateInput;
import com.apelisser.manager.domain.model.Equipment;
import com.apelisser.manager.domain.service.EquipmentRegistrationService;
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
@RequestMapping(value = "/api/v1/equipments",
    produces = MediaType.APPLICATION_JSON_VALUE)
public class EquipmentController {

    private final EquipmentInputDisassembler equipmentDisassembler;
    private final EquipmentModelAssembler equipmentAssembler;
    private final EquipmentResumeModelAssembler equipmentResumeAssembler;
    private final EquipmentRegistrationService equipmentService;

    public EquipmentController(EquipmentInputDisassembler equipmentDisassembler, EquipmentModelAssembler equipmentAssembler, EquipmentResumeModelAssembler equipmentResumeAssembler, EquipmentRegistrationService equipmentService) {
        this.equipmentDisassembler = equipmentDisassembler;
        this.equipmentAssembler = equipmentAssembler;
        this.equipmentResumeAssembler = equipmentResumeAssembler;
        this.equipmentService = equipmentService;
    }


    @GetMapping
    public List<EquipmentResumeModel> findAll() {
        List<Equipment> equipments = equipmentService.findAll();
        return equipmentResumeAssembler.toCollectionModel(equipments);
    }

    @GetMapping("/{equipmentId}")
    public EquipmentModel findById(@PathVariable Long equipmentId) {
        Equipment equipment = equipmentService.findById(equipmentId);
        return equipmentAssembler.toModel(equipment);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EquipmentModel add(@RequestBody EquipmentInput equipment) {
        Equipment domainEquipment = equipmentDisassembler.toDomainObject(equipment);
        Equipment savedEquipment = equipmentService.save(domainEquipment);
        return equipmentAssembler.toModel(savedEquipment);
    }

    @PutMapping("/{equipmentId}")
    public EquipmentModel update(@PathVariable Long equipmentId, @RequestBody EquipmentUpdateInput equipmentInput) {
        Equipment equipment = equipmentService.findById(equipmentId);
        equipmentDisassembler.copyToDomainObject(equipmentInput, equipment);
        Equipment updatedEquipment = equipmentService.save(equipment);
        return equipmentAssembler.toModel(updatedEquipment);
    }

    @DeleteMapping("/{equipmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long equipmentId) {
        equipmentService.delete(equipmentId);
    }

}
