package net.scrafet.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import net.scrafet.model.Categoria;

//public interface CategoriasRepository extends CrudRepository<Categoria, Integer> {
public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {

}
