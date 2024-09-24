package com.infyrail.service;

import java.util.List;
import com.infyrail.dto.TrainDTO;
import com.infyrail.exception.TrainException;

public interface TrainService {
	
	public TrainDTO getTrainById(Integer trainId) throws TrainException;
	public Integer createTrain(TrainDTO trainDTO) throws TrainException;     // done
	public String updateFare(Integer trainId, Double fare) throws TrainException;    // done
	public TrainDTO updateTrainDetails(Integer trainId, TrainDTO trainDTO) throws TrainException;    // done
	public String deleteTrain(Integer routeId, Integer trainId) throws TrainException;     // done
	
	public List<TrainDTO> getTrainsBySourceAndDestination(String source, String destination) throws TrainException;
	public List<TrainDTO>getTrainsByRouteId(Integer routeId) throws TrainException;
	public List<TrainDTO> findByRouteId(Integer routeId) throws TrainException;
	
}
