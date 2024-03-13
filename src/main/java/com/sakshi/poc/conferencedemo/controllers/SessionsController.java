package com.sakshi.poc.conferencedemo.controllers;

import com.sakshi.poc.conferencedemo.models.Session;
import com.sakshi.poc.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> getSessions()
    {
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session getSessionById(@PathVariable Long id)
    {
        return sessionRepository.getReferenceById(id);
    }

    @PostMapping
    public Session createSession(@RequestBody final Session session)
    {
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void deleteSession(@PathVariable Long id)
    {
         sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public Session updateSession(@PathVariable Long id,@RequestBody final Session session)
    {
        if (id == null || session == null) {
            // Handle null input error
            return null;
        }
        Session existingSession= sessionRepository.getReferenceById(id);
        if (existingSession == null) {
            // Handle not found error
            return null;
        }
        System.out.println(existingSession);
        //BeanUtils.copyProperties(session,existingSession,"session_id");
        existingSession.setSessionName(session.getSessionName());
        existingSession.setSessionDescription(session.getSessionDescription());
        return sessionRepository.saveAndFlush(existingSession);
    }
}
