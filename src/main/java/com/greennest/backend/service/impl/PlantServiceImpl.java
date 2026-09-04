package com.greennest.backend.service.impl;

import com.greennest.backend.entity.Category;
import com.greennest.backend.entity.Plant;
import com.greennest.backend.exception.ResourceNotFoundException;
import com.greennest.backend.repository.CategoryRepository;
import com.greennest.backend.repository.PlantRepository;
import com.greennest.backend.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public PlantServiceImpl(PlantRepository plantRepository, CategoryRepository categoryRepository) {
        this.plantRepository = plantRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Plant addPlant(Plant plant, Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

        plant.setCategory(category);

        return plantRepository.save(plant);
    }

    @Override
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    @Override
    public Plant getPlantById(Long plantId) {
        return plantRepository.findById(plantId)
                .orElseThrow(() -> new ResourceNotFoundException("Plant not found with id: " + plantId));
    }

    @Override
    public Plant updatePlant(Long plantId, Plant updatedPlant, Long categoryId) {

        Plant existingPlant = plantRepository.findById(plantId)
                .orElseThrow(() -> new ResourceNotFoundException("Plant not found with id: " + plantId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

        existingPlant.setName(updatedPlant.getName());
        existingPlant.setDescription(updatedPlant.getDescription());
        existingPlant.setPrice(updatedPlant.getPrice());
        existingPlant.setStockQuantity(updatedPlant.getStockQuantity());
        existingPlant.setImageUrl(updatedPlant.getImageUrl());
        existingPlant.setCategory(category);

        return plantRepository.save(existingPlant);
    }

    @Override
    public void deletePlant(Long plantId) {

        if (!plantRepository.existsById(plantId)) {
            throw new ResourceNotFoundException("Plant not found with id: " + plantId);
        }

        plantRepository.deleteById(plantId);
    }

    @Override
    public List<Plant> searchPlantsByName(String name) {
        return plantRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Plant> getPlantsByCategory(Long categoryId) {
        return plantRepository.findByCategory_CategoryId(categoryId);
    }
}