package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.EventReason;

import java.util.List;

public interface EventReasonRegistrationService {

    EventReason save(EventReason eventReason);

    void delete(Long eventReasonId);

    EventReason findById(Long eventReasonId);

    List<EventReason> findAll();

}
