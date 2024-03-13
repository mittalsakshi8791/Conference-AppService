package com.sakshi.poc.conferencedemo.controllers;

import com.sakshi.poc.conferencedemo.models.Session;
import com.sakshi.poc.conferencedemo.models.Speaker;
import com.sakshi.poc.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speaker")
public class SpeakersController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> getSpeakers()
    {
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{Id}")
    public Speaker getSpeakerById(@PathVariable Long id)
    {
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
