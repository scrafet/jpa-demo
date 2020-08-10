package net.scrafet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scrafet.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
