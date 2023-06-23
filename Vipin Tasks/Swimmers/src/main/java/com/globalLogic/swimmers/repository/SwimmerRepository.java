package com.globalLogic.swimmers.repository;

import com.globalLogic.swimmers.entity.Swimmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwimmerRepository extends JpaRepository<Swimmer, Integer> {
}
