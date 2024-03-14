package com.sakshi.poc.conferencedemo.repositories;

import com.sakshi.poc.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends JpaRepository<Session,Long> {
}
