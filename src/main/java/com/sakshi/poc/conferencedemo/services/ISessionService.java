package com.sakshi.poc.conferencedemo.services;

import com.sakshi.poc.conferencedemo.models.Session;

import java.util.List;

public interface ISessionService {
    Session getSessionById(Long id) throws Exception;
    List<Session> getSessions();
}
