package com.HackbrightOptum.capstone.repositories;

import com.HackbrightOptum.capstone.entities.Accommodations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodations, Long> {

}
