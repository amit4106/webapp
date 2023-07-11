package com.cms.apis;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.entity.Trainer;
import com.cms.service.TrainerService;

@RestController
@RequestMapping(value = "/api/trainer")
public class TrainerController {

	@Autowired
	private TrainerService trainerService;
	
	@PostMapping(value = "/createTrainer")
	public ResponseEntity<Trainer> createTrainer(@Valid @RequestBody Trainer trainer){
		return ResponseEntity.ok(trainerService.createTrainer(trainer));
	}
	
	@GetMapping(value = "/findAllTrainer")
	public ResponseEntity<List<Trainer>> findAllTrainer(){
		return ResponseEntity.ok(trainerService.findAllTrainer());
	}
	
	@GetMapping(value = "/findTrainerById/{id}")
	public ResponseEntity<Trainer> findTrainerById(@PathVariable("id") Long trainerId){
		return ResponseEntity.ok(trainerService.findTrainerById(trainerId));
	}
	
	@PutMapping(value = "/updateTrainerById/{id}")
	public ResponseEntity<Trainer> updateTrainerById(@PathVariable("id") Long trainerId, @Valid @RequestBody Trainer trainer){
		return ResponseEntity.ok(trainerService.updateTrainerById(trainerId, trainer));
	}
	
	@DeleteMapping(value = "/deleteTrainerById/{id}")
	public ResponseEntity<String> deleteTrainerById(@PathVariable("id") Long trainerId){
		return ResponseEntity.ok(trainerService.deleteTrainerById(trainerId));
	}
}
