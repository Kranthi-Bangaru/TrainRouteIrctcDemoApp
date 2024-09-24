package com.infyrail.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Train {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String trainName;
    
    @Column(nullable = false)
    private String arrivalTime;
    
    @Column(nullable = false)
    private String departureTime;
    
    @Column(nullable = false)
    private Double fare;
    
    @Column(name = "route_id")
    private Integer routeId;

	public Train() {
		super();
	}

	public Train(Integer id, String trainName, String arrivalTime, String departureTime, Double fare) {
		super();
		this.id = id;
		this.trainName = trainName;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.fare = fare;
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
		return "Train [id=" + id + ", trainName=" + trainName + ", arrivalTime=" + arrivalTime + ", departureTime="
				+ departureTime + ", fare=" + fare + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(arrivalTime, departureTime, fare, id, trainName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Train other = (Train) obj;
		return Objects.equals(arrivalTime, other.arrivalTime) && Objects.equals(departureTime, other.departureTime)
				&& Objects.equals(fare, other.fare) && Objects.equals(id, other.id)
				&& Objects.equals(trainName, other.trainName);
	}	
	
}
