package com.greennest.backend.service;

import com.greennest.backend.entity.Plant;

import java.util.List;

public interface PlantService {

    Plant addPlant(Plant plant, Long categoryId);

    List<Plant> getAllPlants();

    Plant getPlantById(Long plantId);

    Plant updatePlant(Long plantId, Plant updatedPlant, Long categoryId);

    void deletePlant(Long plantId);

    List<Plant> searchPlantsByName(String name);

    List<Plant> getPlantsByCategory(Long categoryId);
}