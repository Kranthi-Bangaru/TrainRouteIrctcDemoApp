package com.infyrail.repo;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infyrail.entity.Train;

@Repository
public interface TrainRepo extends JpaRepository<Train, Integer>{
	
	@Query("SELECT t FROM Train t WHERE t.routeId = ?1")
	List<Train> findByRouteId(Integer routeId);

}