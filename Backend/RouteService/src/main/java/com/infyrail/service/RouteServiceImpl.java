package com.infyrail.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.infyrail.config.RestTemplateConfig;
import com.infyrail.dto.RouteDTO;
import com.infyrail.dto.TrainDTO;
import com.infyrail.entity.Route;
import com.infyrail.exception.RouteException;
import com.infyrail.repo.RouteRepo;

@Service
public class RouteServiceImpl implements RouteService {
	
	@Autowired
	private RouteRepo routeRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String TRAIN_SERVICE_URL = "http://localhost:8081/trainservice";

	@Override
	public RouteDTO createRoute(RouteDTO routeDTO) throws RouteException {
		Route route = new Route();
		route.setSource(routeDTO.getSource());
        route.setDestination(routeDTO.getDestination());
//        route.setTrainIds(routeDTO.getTrainIds());
        Route savedRoute = routeRepo.save(route);
        
        RouteDTO savedRouteDTO = new RouteDTO();
        savedRouteDTO.setId(savedRoute.getId());
        savedRouteDTO.setSource(savedRoute.getSource());
        savedRouteDTO.setDestination(savedRoute.getDestination());
//        savedRouteDTO.setTrainIds(savedRoute.getTrainIds());
		return savedRouteDTO;
	}

	@Override
	public RouteDTO getRouteIdDetails(Integer routeId) throws RouteException {
		Optional<Route> routeDetails = routeRepo.findById(routeId);
	    if (routeDetails.isPresent()) {
	    	Route route = routeDetails.get();
	    	String trainServiceUrl = "http://localhost:8081/trainservice/trains/byRoute/" + routeId;
	        ResponseEntity<TrainDTO[]> responseEntity = restTemplate.getForEntity(trainServiceUrl, TrainDTO[].class);
	        TrainDTO[] trainArray = responseEntity.getBody();
	        List<TrainDTO> trainDTOList = Arrays.asList(trainArray);
	        RouteDTO routeDTO = new RouteDTO();
	        routeDTO.setId(route.getId());
	        routeDTO.setSource(route.getSource());
	        routeDTO.setDestination(route.getDestination());
	        routeDTO.setTrains(trainDTOList);
	        return routeDTO;
	    } else {
	    	throw new RouteException("Route not found with id " + routeId);
	    }
	}
	

	@Override
	public RouteDTO updateRoute(Integer routeId, RouteDTO routeDTO) throws RouteException {
		Optional<Route> routeDetails = routeRepo.findById(routeId);

        if (routeDetails.isPresent()) {
            Route route = routeDetails.get();
            route.setSource(routeDTO.getSource());
            route.setDestination(routeDTO.getDestination());
//            route.setTrainIds(routeDTO.getTrainIds());

            Route updatedRoute = routeRepo.save(route);
            
            RouteDTO updatedRouteDTO = new RouteDTO();
            updatedRouteDTO.setId(updatedRoute.getId());
            updatedRouteDTO.setSource(updatedRoute.getSource());
            updatedRouteDTO.setDestination(updatedRoute.getDestination());
//            updatedRouteDTO.setTrainIds(updatedRoute.getTrainIds());
            
            return updatedRouteDTO;
        } else {
            throw new RouteException("Route not found with ID: " + routeId);
        }
	}
	
	@Override
	public RouteDTO updateTrainInRoute(Integer routeId, Integer trainId, TrainDTO traindto) throws RouteException {
		Optional<Route> optionalRoute = routeRepo.findById(routeId);
		if (optionalRoute.isPresent()) {
			Route route = optionalRoute.get();
			String trainServiceUrl = "http://localhost:8081/trainservice/trains/" + trainId;
			
			ResponseEntity<TrainDTO> responseEntity = restTemplate.getForEntity(trainServiceUrl, TrainDTO.class);
			TrainDTO existingTrainDTO = responseEntity.getBody();
			if (existingTrainDTO == null || !existingTrainDTO.getRouteId().equals(routeId)) {
	            throw new RouteException("Train does not belong to the specified route or train not found");
	        }
			restTemplate.put(trainServiceUrl, traindto);
			
			ResponseEntity<TrainDTO> updatedResponseEntity = restTemplate.getForEntity(trainServiceUrl, TrainDTO.class);
	        TrainDTO updatedTrainDTO = updatedResponseEntity.getBody();
	        
	        if (!updatedTrainDTO.getRouteId().equals(routeId)) {
	            throw new RouteException("Updated train does not belong to the specified route");
	        }

	        routeRepo.save(route);
	        RouteDTO routeDTO = new RouteDTO();
	        routeDTO.setId(route.getId());
	        routeDTO.setSource(route.getSource());
	        routeDTO.setDestination(route.getDestination());
	        
	        ResponseEntity<TrainDTO[]> trainsResponseEntity = restTemplate.getForEntity(TRAIN_SERVICE_URL + "/trains/byRoute/" + routeId, TrainDTO[].class);
	        List<TrainDTO> trainsForRoute = Arrays.asList(trainsResponseEntity.getBody());
	        routeDTO.setTrains(trainsForRoute);

	        return routeDTO;
	        
		} else {
	        throw new RouteException("Route not found with ID: " + routeId);
	    }
	}

	@Override
	public void deleteRoute(Integer routeId, Integer trainId) throws RouteException {
		Optional<Route> routeDetails = routeRepo.findById(routeId);
        if (routeDetails.isPresent()) {
            Route route = routeDetails.get();
            routeRepo.delete(route);
        } else {
            throw new RouteException("Route not found with ID: " + routeId);
        }
	}
	
	private RouteDTO convertToDTO(Route route) {
		RouteDTO routeDTO = new RouteDTO();
        routeDTO.setId(route.getId());
        routeDTO.setSource(route.getSource());
        routeDTO.setDestination(route.getDestination());
        return routeDTO;
    }

	@Override
	public List<TrainDTO> trainsBySrcAndDest(String source, String destination) {
		Route route = routeRepo.getBySrcAndDest(source, destination);
		List<TrainDTO> obj = (List<TrainDTO>) restTemplate.getForEntity("http://localhost:8081/trainservice/" + route.getId(), Object.class).getBody();
		return obj;
	}

	@Override
	public List<RouteDTO> getAllRoutes() throws RouteException {
		List<Route> routes = routeRepo.findAll();
        if (routes.isEmpty()) {
            throw new RouteException("No routes found");
        }

        List<RouteDTO> routeDTOs = routes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return routeDTOs;
	}

}
