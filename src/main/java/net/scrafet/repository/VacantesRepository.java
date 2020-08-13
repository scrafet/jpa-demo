package net.scrafet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scrafet.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

	/**
	 * 
	 * @param estatus
	 * @return Busqueda por estatus
	 */
	List<Vacante> findByEstatus(String estatus);
	
	List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);
	
	List<Vacante> findBySalarioBetweenOrderBySalarioDesc(double s1, double s2);
	
	List<Vacante> findByEstatusIn(String[] estatus);

}
