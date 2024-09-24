package com.infyrail.dto;

import java.util.Objects;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TrainDTO {
	
	private Integer id;
	private String trainName;
	private String arrivalTime;
	private String departureTime;
	private Double fare;
	private Integer routeId;
	
	public TrainDTO() {
		super();
	}

	public TrainDTO(Integer id, String trainName, String arrivalTime, String departureTime, Double fare,
			Integer routeId) {
		super();
		this.id = id;
		this.trainName = trainName;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.fare = fare;
		this.routeId = routeId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public Double getFare() {
		return fare;
	}
	public void setFare(Double fare) {
		this.fare = fare;
	}
	public Integer getRouteId() {
		return routeId;
	}
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	@Override
	public String toString() {
		return "TrainDTO [id=" + id + ", trainName=" + trainName + ", arrivalTime=" + arrivalTime + ", departureTime="
				+ departureTime + ", fare=" + fare + ", routeId=" + routeId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(arrivalTime, departureTime, fare, id, routeId, trainName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrainDTO other = (TrainDTO) obj;
		return Objects.equals(arrivalTime, other.arrivalTime) && Objects.equals(departureTime, other.departureTime)
				&& Objects.equals(fare, other.fare) && Objects.equals(id, other.id)
				&& Objects.equals(routeId, other.routeId) && Objects.equals(trainName, other.trainName);
	}

}
