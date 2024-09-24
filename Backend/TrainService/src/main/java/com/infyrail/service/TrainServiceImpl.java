package com.infyrail.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infyrail.dto.TrainDTO;
import com.infyrail.entity.Train;
import com.infyrail.exception.TrainException;
import com.infyrail.repo.TrainRepo;

@Service
public class TrainServiceImpl implements TrainService {
	
	@Autowired
	private TrainRepo trainRepo;

	@Override
	public Integer createTrain(TrainDTO trainDto) throws TrainException {
		Train createTrain = new Train();
		createTrain.setTrainName(trainDto.getTrainName());
		createTrain.setDepartureTime(trainDto.getDepartureTime());
		createTrain.setArrivalTime(trainDto.getArrivalTime());
		createTrain.setFare(trainDto.getFare());
//		createTrain.setRouteId(trainDto.getRouteId());	
		Train saveTrain = trainRepo.save(createTrain);
//		train = trainRepository.save(train);
		
		trainDto.setId(saveTrain.getId());
		return trainDto.getId();
	}
	
	@Override
    public TrainDTO getTrainById(Integer trainId) {
        Optional<Train> optionalTrain = trainRepo.findById(trainId);
        if (optionalTrain.isPresent()) {
            Train train = optionalTrain.get();
            return convertToDTO(train);
        } else {
            return null;
        }
    }

	@Override
	public List<TrainDTO> getTrainsByRouteId(Integer routeId) throws TrainException {
		List<Train> findTrainRouteId = trainRepo.findByRouteId(routeId);
		return findTrainRouteId.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	@Override
	public String updateFare(Integer trainId, Double fare) throws TrainException {
		Optional<Train> fareIds = trainRepo.findById(trainId);
		if(fareIds.isPresent()) {
			Train train = fareIds.get();
			train.setFare(fare);
			trainRepo.save(train);
			return "Fare Updated Successfully" + trainId;
		} else {
			return "Train not found";
		}
	}
	
	
	@Override
	public TrainDTO updateTrainDetails(Integer trainId, TrainDTO trainDTO) throws TrainException {
		
		Optional<Train> optionalTrain = trainRepo.findById(trainId);
		if (optionalTrain.isPresent()) {
            Train train = optionalTrain.get();
            train.setTrainName(trainDTO.getTrainName());
            train.setArrivalTime(trainDTO.getArrivalTime());
            train.setDepartureTime(trainDTO.getDepartureTime());
            train.setFare(trainDTO.getFare());
            
            Train updatedTrain = trainRepo.save(train);
            return convertToDTO(updatedTrain);
        } else {
            throw new TrainException("Train not found with ID: " + trainId);
        }
	}

	@Override
	public String  deleteTrain(Integer routeId, Integer trainId) throws TrainException {
		Train deleteTrainId = trainRepo.findById(trainId).orElseThrow(() -> new RuntimeException("Train not found"));
//		if (!deleteTrainId.getRouteId().equals(routeId)) {
//            throw new RuntimeException("Train does not belong to the specified route");
//        }
		trainRepo.delete(deleteTrainId);
        return "Train deleted successfully";
	}

	@Override
	public List<TrainDTO> getTrainsBySourceAndDestination(String source, String Destination) throws TrainException {
//		Route route = routeRepo.findRoutesBySourceAndDestination(source, Destination);
//		List<Train> trainsBySourceAndDestination = trainRepo.findTrainsBySourceAndDestination(source, Destination);
//		return trainsBySourceAndDestination.stream().map(this::convertToDTO).collect(Collectors.toList());
		return null;
	}
	
	private TrainDTO convertToDTO(Train train) {
		TrainDTO convertTainDto = new TrainDTO();
		convertTainDto.setId(train.getId());
		convertTainDto.setTrainName(train.getTrainName());
		convertTainDto.setArrivalTime(train.getArrivalTime());
		convertTainDto.setDepartureTime(train.getDepartureTime());
		convertTainDto.setFare(train.getFare());
		convertTainDto.setRouteId(train.getRouteId());
		return convertTainDto;
	}

	@Override
	public List<TrainDTO> findByRouteId(Integer routeId) throws TrainException {
		if(routeId != null) {
			List<Train> trains = trainRepo.findByRouteId(routeId);
			List<TrainDTO> trainsdto = new ArrayList<TrainDTO>();
//			for(Object obj:trains) {
////				TrainDTO trainDTO = new TrainDTO();
//				TrainDTO trainDTO = convertToDTO(obj);
////				BeanUtils.copyProperties(obj, trainsdto);
//				trainsdto.add(trainDTO);
//			}
			for(int i=0;i<trains.size();i++) {
				TrainDTO trainDTO = convertToDTO(trains.get(i));
				trainsdto.add(trainDTO);
			}
			
			return trainsdto;
		}
		throw new TrainException("RouteId shouldn't be null");
	}


}
