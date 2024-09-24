package com.infyrail.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infyrail.entity.Route;

@Repository
public interface RouteRepo extends JpaRepository<Route, Integer>{
	
//	@Query("SELECT r FROM Route r WHERE r.source = :source")
	List<Route> findRouteBySource(@Param("source") String source);
	
	@Query("SELECT r FROM Route r WHERE r.destination = :destination")
	List<Route> findRouteByDestination(@Param("destination") String destination);
	
	
	@Query("select r from Route r where r.source = ?1 and r.destination = ?2")
	Route getBySrcAndDest(String source, String destination);
}
