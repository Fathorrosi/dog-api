package com.test.dogapi.repository;

import com.test.dogapi.entity.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Long> {
    boolean existsByName(String name);

}
