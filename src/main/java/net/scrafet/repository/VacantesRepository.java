package net.scrafet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scrafet.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

}
