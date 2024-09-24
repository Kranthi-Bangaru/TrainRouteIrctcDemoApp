package com.infyrail.dto;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class RouteDTO {
	
	private Integer id;
	private String source;
	private String destination;
//	private List<Integer> trainIds;
	private List<TrainDTO> trains;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}

	public List<TrainDTO> getTrains() {
		return trains;
	}

	public void setTrains(List<TrainDTO> trains) {
		this.trains = trains;
	}

	@Override
	public String toString() {
		return "RouteDTO [id=" + id + ", source=" + source + ", destination=" + destination + ", trains=" + trains
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(destination, id, source, trains);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RouteDTO other = (RouteDTO) obj;
		return Objects.equals(destination, other.destination) && Objects.equals(id, other.id)
				&& Objects.equals(source, other.source) && Objects.equals(trains, other.trains);
	}
	
	
}
