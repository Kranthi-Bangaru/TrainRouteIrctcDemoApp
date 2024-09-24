package com.infyrail.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Route {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String source;
	    
	@Column(nullable = false)
	private String destination;
	

//	@JsonIgnore
//	@OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
//	@ElementCollection
//	private List<Integer> trainIds  = new ArrayList<>();
	
	
	
	public Route() {
		super();
	}

	public Route(Integer id, String source, String destination, List<Integer> trainIds) {
		super();
		this.id = id;
		this.source = source;
		this.destination = destination;
//		this.trainIds = trainIds;
	}


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

//	public List<Integer> getTrainIds() {
//		return trainIds;
//	}
//
//	public void setTrainIds(List<Integer> trainIds) {
//		this.trainIds = trainIds;
//	}

//	@Override
//	public String toString() {
//		return "Route [id=" + id + ", source=" + source + ", destination=" + destination + ", trainIds=" + trainIds
//				+ "]";
//	}

//	@Override
//	public int hashCode() {
//		return Objects.hash(destination, id, source, trainIds);
//	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Route other = (Route) obj;
//		return Objects.equals(destination, other.destination) && Objects.equals(id, other.id)
//				&& Objects.equals(source, other.source) && Objects.equals(trainIds, other.trainIds);
//	}
}
