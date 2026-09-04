package com.greennest.backend.controller;

import com.greennest.backend.entity.Plant;
import com.greennest.backend.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plants")
public class PlantController {

    private final PlantService plantService;

    @Autowired
    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @PostMapping
    public ResponseEntity<Plant> addPlant(@RequestBody Plant plant, @RequestParam Long categoryId) {
        Plant saved = plantService.addPlant(plant, categoryId);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Plant>> getAllPlants() {
        List<Plant> plants = plantService.getAllPlants();
        return ResponseEntity.ok(plants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable("id") Long id) {
        Plant plant = plantService.getPlantById(id);
        return ResponseEntity.ok(plant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable("id") Long id,
                                              @RequestBody Plant updatedPlant,
                                              @RequestParam Long categoryId) {
        Plant plant = plantService.updatePlant(id, updatedPlant, categoryId);
        return ResponseEntity.ok(plant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlant(@PathVariable("id") Long id) {
        plantService.deletePlant(id);
        return ResponseEntity.ok("Plant deleted successfully with id: " + id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Plant>> searchPlants(@RequestParam String name) {
        List<Plant> plants = plantService.searchPlantsByName(name);
        return ResponseEntity.ok(plants);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Plant>> getPlantsByCategory(@PathVariable Long categoryId) {
        List<Plant> plants = plantService.getPlantsByCategory(categoryId);
        return ResponseEntity.ok(plants);
    }
}