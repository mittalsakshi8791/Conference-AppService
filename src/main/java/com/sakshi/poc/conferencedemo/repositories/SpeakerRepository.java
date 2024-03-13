package com.sakshi.poc.conferencedemo.repositories;

import com.sakshi.poc.conferencedemo.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker,Long> {
}
