package com.sakshi.poc.conferencedemo.services;

import com.sakshi.poc.conferencedemo.models.Session;
import com.sakshi.poc.conferencedemo.repositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService implements ISessionService {

    private final SessionRepository sessionRepository;

    //Constructor Injection
    public SessionService(SessionRepository sessionRepository)
    {
        this.sessionRepository=sessionRepository;
    }

    @Override
    public Session getSessionById(Long id) throws Exception {
        if(id==null)
            throw new Exception("SessionId cannot be null.");

        Session session=sessionRepository.getReferenceById(id);
        if(session.getSessionId() == null)
            throw new Exception("Session does not exist");
        return  session;

    }

    @Override
    public List<Session> getSessions()
    {
        return sessionRepository.findAll();
    }

}
