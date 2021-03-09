package com.car.factory.carfactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car.factory.carfactory.model.CarHood;

@Repository
public interface CarHoodRepository extends JpaRepository<CarHood, Long> {

}
