package com.infyrail.service;

import java.util.List;

import com.infyrail.dto.RouteDTO;
import com.infyrail.dto.TrainDTO;
import com.infyrail.exception.RouteException;

public interface RouteService {
	
	public List<RouteDTO> getAllRoutes() throws RouteException;
	public RouteDTO createRoute(RouteDTO routeDTO) throws RouteException;
	public RouteDTO getRouteIdDetails(Integer routeId) throws RouteException;
	public RouteDTO updateRoute(Integer routeId, RouteDTO routeDTO) throws RouteException;
	public RouteDTO updateTrainInRoute(Integer routeId, Integer trainId, TrainDTO traindto) throws RouteException;
	public void deleteRoute(Integer routeId, Integer trainId) throws RouteException;
	public List<TrainDTO> trainsBySrcAndDest(String source, String destination);
    
}
