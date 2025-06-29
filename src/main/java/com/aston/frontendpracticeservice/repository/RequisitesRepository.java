package com.aston.frontendpracticeservice.repository;

import com.aston.frontendpracticeservice.domain.entity.Requisites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RequisitesRepository extends JpaRepository<Requisites, UUID> {
}
