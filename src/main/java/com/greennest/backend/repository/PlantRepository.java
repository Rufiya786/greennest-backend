package com.greennest.backend.repository;

import com.greennest.backend.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {

    List<Plant> findByCategory_CategoryId(Long categoryId);

    List<Plant> findByNameContainingIgnoreCase(String name);
}