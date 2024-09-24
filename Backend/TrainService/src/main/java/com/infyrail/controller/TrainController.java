package com.infyrail.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.infyrail.dto.TrainDTO;
import com.infyrail.exception.TrainException;
import com.infyrail.service.TrainService;

@RestController
@RequestMapping("/trainservice")
@CrossOrigin(origins = "http://localhost:3000")
public class TrainController {
	
	@Autowired
	private TrainService trainService;
    
    @GetMapping("/trains/byRoute/{routeId}") 	// working
    public List<TrainDTO> getTrainsByRouteId(@PathVariable Integer routeId) throws TrainException{
        return trainService.getTrainsByRouteId(routeId);
    }
	
	@PostMapping("/addTrain") // partially working
	public ResponseEntity<String> createTrain(@Valid @RequestBody TrainDTO trainDTO) throws TrainException {
		try {
			Integer createTrainId = trainService.createTrain(trainDTO);
			String successMessage = "Train created successfully with ID: " + createTrainId;
			return new ResponseEntity<String>(successMessage, HttpStatus.OK);
		} catch(TrainException  e) {
			String errorMessage = "Error creating train: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/trains/{trainId}")
	ResponseEntity<String> updateTrainDetails(@PathVariable Integer trainId, @RequestBody TrainDTO trainDTO) throws TrainException {
		try {
            TrainDTO updatedTrainDTO = trainService.updateTrainDetails(trainId, trainDTO);
            String successMessage = "Train details updated successfully: " + updatedTrainDTO.getId();
            return new ResponseEntity<>(successMessage, HttpStatus.OK);
        } catch (TrainException e) {
            String errorMessage = "Error updating train details: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
	}
	
	@DeleteMapping("/trains/{trainId}")		// working
	public ResponseEntity<String> deleteTrain(@PathVariable Integer routeId, @PathVariable Integer trainId) throws TrainException {
		try {
            String response = trainService.deleteTrain(routeId, trainId);
            String successMessage = "Train deleted successfully: " + response;
            return new ResponseEntity<>(successMessage, HttpStatus.OK);
        } catch (TrainException e) {
            String errorMessage = "Error deleting train: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
	}
	
	@GetMapping("/{routeId}")
	public ResponseEntity<Object> getByRoutId(@PathVariable Integer routeId) throws TrainException {
		try {
			System.out.print("*****************"+routeId);
			List<TrainDTO> byRouteId = trainService.findByRouteId(routeId);
			return new ResponseEntity<Object>(byRouteId, HttpStatus.OK);
		} catch(TrainException  e) {
			return ResponseEntity.status(500).body(null);
		}
	}
	
//	@GetMapping("/{trainId}")
//    public ResponseEntity<TrainDTO> getTrainById(@PathVariable Integer trainId) throws TrainException {
//        TrainDTO train = trainService.getTrainById(trainId);
//        if (train == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(train);
//    }
	
}
