package com.infyrail.controller;

import com.infyrail.dto.RouteDTO;
import com.infyrail.dto.TrainDTO;
import com.infyrail.service.RouteService;
import com.infyrail.exception.RouteException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/infyrail/routes")
@CrossOrigin(origins = "http://localhost:3000")
public class RouteController {

    @Autowired
    private RouteService routeService;
    
    @Autowired
    private Environment environment;
    
    @GetMapping("/allroutes")
    public ResponseEntity<List<RouteDTO>> getAllRoutes() throws RouteException {
        List<RouteDTO> routes = routeService.getAllRoutes();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @PostMapping		// not working
    public ResponseEntity<RouteDTO> createRoute(@RequestBody RouteDTO routeDTO) throws RouteException {
        RouteDTO createdRoute = routeService.createRoute(routeDTO);
        return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
    }

    @GetMapping("/{routeId}")		// working
    public ResponseEntity<RouteDTO> getRouteById(@PathVariable Integer routeId) throws RouteException {
        try {
            RouteDTO routeDTO = routeService.getRouteIdDetails(routeId);
            return new ResponseEntity<>(routeDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{routeId}") 		// working
    public ResponseEntity<RouteDTO> updateRoute(@PathVariable Integer routeId, @RequestBody RouteDTO routeDTO) throws RouteException {
        try {
            RouteDTO updatedRoute = routeService.updateRoute(routeId, routeDTO);
            return new ResponseEntity<>(updatedRoute, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{routeId}/trains/{trainId}")		// working
    public ResponseEntity<RouteDTO> updateTrainInRoute(@PathVariable Integer routeId, @PathVariable Integer trainId, @RequestBody TrainDTO trainDTO) throws RouteException {
        try {
            RouteDTO updatedRoute = routeService.updateTrainInRoute(routeId, trainId, trainDTO);
            return new ResponseEntity<>(updatedRoute, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{routeId}/trains/{trainId}")  // working
    public ResponseEntity<Void> deleteTrainFromRoute(@PathVariable Integer routeId, @PathVariable Integer trainId) throws RouteException {
        try {
            routeService.deleteRoute(routeId, trainId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/trainsBySrcAndDest")			// working
    public ResponseEntity<Object> trainsBySrcAndDest(@RequestParam String source, @RequestParam String destination) throws RouteException {
        try {
        	List<TrainDTO> trains = routeService.trainsBySrcAndDest(source, destination);
            return new ResponseEntity<Object>(trains,HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
