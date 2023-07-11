package com.cms.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.Trainer;
import com.cms.exception.TrainerNotFoundException;
import com.cms.repository.TrainerRepository;
import com.cms.service.TrainerService;

@Service
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	private TrainerRepository trainerRepository;

	@Override
	public Trainer createTrainer(@Valid Trainer trainer) {
		return trainerRepository.save(trainer);
	}

	@Override
	public List<Trainer> findAllTrainer() {
		return trainerRepository.findAll();
	}

	@Override
	public Trainer findTrainerById(Long trainerId) throws TrainerNotFoundException{
		Trainer response=trainerRepository.findById(trainerId)
				.orElseThrow(()->new TrainerNotFoundException("Trainer not  found for this id :"+trainerId));
		return response;
	}

	@Override
	public Trainer updateTrainerById(Long trainerId, @Valid Trainer trainer) {
		Trainer response=trainerRepository.findById(trainerId)
				.orElseThrow(()->new TrainerNotFoundException("Trainer not  found for this id :"+trainerId));
		response.setFirstName(trainer.getFirstName());
		response.setLastName(trainer.getLastName());
		response.setEmail(trainer.getEmail());
		final Trainer saveTrainer = trainerRepository.save(response);
		return saveTrainer;
	}

	@Override
	public String deleteTrainerById(Long trainerId) {
		Trainer response=trainerRepository.findById(trainerId)
				.orElseThrow(()->new TrainerNotFoundException("Trainer not  found for this id :"+trainerId));
		trainerRepository.delete(response);
		return ("Trainer has been deleted for the Id :"+trainerId);
	}
}
