package com.sakshi.poc.conferencedemo.controllers;

import com.sakshi.poc.conferencedemo.models.Session;
import com.sakshi.poc.conferencedemo.repositories.SessionRepository;
import com.sakshi.poc.conferencedemo.services.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {

    private final SessionService sessionService;
    private static final Logger logger= LoggerFactory.getLogger("SessionsController.class");
    public SessionsController(SessionService sessionService)
    {
        this.sessionService=sessionService;
    }

    //Property Injection. Preferable is constructor Injection
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> getSessions()
    {
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session getSessionById(@PathVariable Long id) throws Exception {
        logger.info("Getting speaker for Id:{} ",id);
        return sessionService.getSessionById(id);
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

//    @ExceptionHandler(EntityNotFoundException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String handleException()
//    {
//        return "oops something went wrong" ;
//    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String handleAllException(HttpServletRequest request,Exception e)
//    {
//        String method=request.getMethod();
//        return "oops something went wrong in method:"+method +"with exception:"+e.getMessage();
//    }
}
