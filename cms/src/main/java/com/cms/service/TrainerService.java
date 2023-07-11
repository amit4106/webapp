package com.cms.service;

import java.util.List;

import javax.validation.Valid;

import com.cms.entity.Trainer;

public interface TrainerService {

	Trainer createTrainer(@Valid Trainer trainer);

	List<Trainer> findAllTrainer();

	Trainer findTrainerById(Long trainerId);

	Trainer updateTrainerById(Long trainerId, @Valid Trainer trainer);

	String deleteTrainerById(Long trainerId);

}
