package com.exercise.phoneapplication.repository;

import com.exercise.phoneapplication.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneRepository extends JpaRepository<Phone, UUID> { }