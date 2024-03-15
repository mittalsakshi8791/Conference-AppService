package com.sakshi.poc.conferencedemo.controllers;

import com.sakshi.poc.conferencedemo.models.Session;
import com.sakshi.poc.conferencedemo.models.Speaker;
import com.sakshi.poc.conferencedemo.repositories.SpeakerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
//import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/speaker")
public class SpeakersController {

    //springboot default framework is Logback.
    private static final Logger logger1= LoggerFactory.getLogger("SpeakersController.class");

    //this is using JVM in built logging framework
    //private static final Logger logger1= Logger.getLogger("SpeakersController.class");

    //this is using Log4j2 framework for logging
    //private static final Logger logger= LogManager.getLogManager().getLogger("SpeakersController.class");
    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> getSpeakers()
    {
        logger1.info("Getting all the speakers .");
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{Id}")
    public Speaker getSpeakerById(@PathVariable Long id)
    {

        logger1.info("Getting speaker for Id:{} ",id);
        return speakerRepository.getReferenceById(id);
    }

    @PostMapping
    public Speaker createSession(@RequestBody final Speaker speaker)
    {
        return speakerRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void deleteSpeaker(@PathVariable Long id)
    {
        logger1.info("Deleting the speaker");
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public Speaker updateSession(@PathVariable Long id,@RequestBody final Speaker speaker)
    {
        Speaker existingSpeaker= speakerRepository.getReferenceById(id);
        BeanUtils.copyProperties(speaker,existingSpeaker,"speaker_id");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }
}
