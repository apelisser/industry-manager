package com.apelisser.manager.domain.exception;

import com.apelisser.manager.domain.model.EquipmentDowntime;

import java.io.Serial;

public class EquipmentDowntimeOverlapException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4328235942302990766L;

    private final EquipmentDowntime equipmentDowntime;
    private final EquipmentDowntime overlappingEquipmentDowntime;

    public EquipmentDowntimeOverlapException(EquipmentDowntime equipmentDowntime, EquipmentDowntime overlappingEquipmentDowntime, String message) {
        super(message);
        this.equipmentDowntime = equipmentDowntime;
        this.overlappingEquipmentDowntime = overlappingEquipmentDowntime;
    }

    public EquipmentDowntimeOverlapException(EquipmentDowntime equipmentDowntime, EquipmentDowntime overlappingEquipmentDowntime, String message, Throwable cause) {
        super(message, cause);
        this.equipmentDowntime = equipmentDowntime;
        this.overlappingEquipmentDowntime = overlappingEquipmentDowntime;
    }

    public EquipmentDowntime getEquipmentDowntime() {
        return equipmentDowntime;
    }

    public EquipmentDowntime getOverlappingEquipmentDowntime() {
        return overlappingEquipmentDowntime;
    }

}
