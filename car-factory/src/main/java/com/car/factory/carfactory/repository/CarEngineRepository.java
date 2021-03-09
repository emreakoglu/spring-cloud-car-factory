package com.car.factory.carfactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car.factory.carfactory.model.CarEngine;


@Repository
public interface CarEngineRepository extends JpaRepository<CarEngine, Long> {

}
