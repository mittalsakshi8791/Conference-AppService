package com.sakshi.poc.conferencedemo.services;

import com.sakshi.poc.conferencedemo.models.Session;
import com.sakshi.poc.conferencedemo.repositories.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    //Constructor Injection
    public SessionService(SessionRepository sessionRepository)
    {
        this.sessionRepository=sessionRepository;
    }

    public Session getSessionById(Long id) throws Exception {
        if(id==null)
            throw new Exception("SessionId cannot be null.");

        Session session=sessionRepository.getReferenceById(id);
        if(session.getSessionId() == null)
            throw new Exception("Session does not exist");
        return  session;

    }

}
