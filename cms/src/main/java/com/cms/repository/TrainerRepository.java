package com.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cms.entity.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

}
