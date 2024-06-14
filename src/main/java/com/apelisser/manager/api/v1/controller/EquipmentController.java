package com.apelisser.manager.api.v1.controller;

import com.apelisser.manager.api.v1.model.EquipmentModel;
import com.apelisser.manager.api.v1.model.input.EquipmentInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/equipments", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
public class EquipmentController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipmentModel newEquipment(@RequestBody EquipmentInput equipment) {
        // TODO

        System.out.println(equipment);
        
        return new EquipmentModel();
    }

}
