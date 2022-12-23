package com.test.dogapi.controller;

import com.test.dogapi.dto.GlobalResponseEntity;
import com.test.dogapi.dto.request.BreedCreateRequest;
import com.test.dogapi.dto.request.BreedUpdateRequest;
import com.test.dogapi.service.BreedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/breed")
@RequiredArgsConstructor
public class BreedController {

    private final BreedService breedService;

    @GetMapping(value = "/store")
    public ResponseEntity<GlobalResponseEntity> getAllBreed() {
        var result = breedService.storeNasabah();
        return GlobalResponseEntity.ok("oke", result);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<GlobalResponseEntity> createBreed(@RequestBody BreedCreateRequest breedRequest) {
        var result = breedService.create(breedRequest.getName());
        return GlobalResponseEntity.ok("oke", result);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<GlobalResponseEntity> getBreeds() {
        var result = breedService.getAll();
        return GlobalResponseEntity.ok("oke", result);
    }

    @GetMapping(value = "/detail")
    public ResponseEntity<GlobalResponseEntity> getBreedDetail(@RequestParam(name = "id") Long id) {
        var result = breedService.detail(id);
        return GlobalResponseEntity.ok("oke", result);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<GlobalResponseEntity> updateBreed(@RequestBody BreedUpdateRequest breedUpdateRequest) {
        var result = breedService.update(breedUpdateRequest);
        return GlobalResponseEntity.ok("oke", result);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<GlobalResponseEntity> deleteBreed(@RequestParam(value = "id") Long id) {
        breedService.delete(id);
        return GlobalResponseEntity.ok("oke");
    }

}
