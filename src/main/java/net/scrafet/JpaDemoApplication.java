package net.scrafet;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.scrafet.model.Categoria;
import net.scrafet.repository.CategoriasRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriasRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		existeId();
	}
	
	/**
	 * Metodo count - interfaz CrudRepository
	 */
	private void conteo() {
		long count = repo.count();
		System.out.println("Total Categorias : " + count);
	}
	
	/**
	 * Metodo ExistById - interfaz CrudRepository
	 */
	private void existeId() {
		boolean existe = repo.existsById(5);
		System.out.println("La categoria existe : " + existe);
	}
	
	
	/**
	 *  Metodo Save(update) - interfaz CrudRepository
	 */
	private void modificar() {
Optional<Categoria> optional = repo.findById(2);
		
		if (optional.isPresent()) {
			Categoria catTmp = optional.get();
			catTmp.setNombre("Ingenieria de Software");
			catTmp.setDescripcion("Desarrollo de Sistemas");
			repo.save(catTmp);
			System.out.println(optional.get());
		}
	    else
			System.out.println("Categoria no encontrada");
		
	}
	
	/**
	 * Metodo findAll - interfaz CrudRepository
	 */
	private void buscarTodos() {
		Iterable<Categoria>  categorias = repo.findAll();
		for (Categoria cat : categorias) {
			System.out.println(cat);
		}
	}
	
	/**
	 * Metodo findById - interfaz CrudRepository
	 */
	private void buscarPorId() {
		Optional<Categoria> optional = repo.findById(6);
		
		if (optional.isPresent()) 
			System.out.println(optional.get());
	    else
			System.out.println("Categoria no encontrada");
		
	}
	
	/**
	 * Metodo findAllById - interfaz CrudRepository
	 */
	private void encontrarPorIds() {
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(4);
		ids.add(10);
		Iterable<Categoria> categorias = repo.findAllById(ids);
		for (Categoria cat : categorias) {
			System.out.println(cat);
		}
	}
	
	/**
	 *  Metodo Save - interfaz CrudRepository
	 */
	private void guardar() {
		Categoria cat = new Categoria();
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajos relacionados con Finanzas y Contabilidad");
		repo.save(cat);
		System.out.println(cat);
	}
	
	/**
	 * Metodo SaveAll - interfaz CrudRepository
	 */
	private void guardarTodas() {
		//List<Categorias> categorias = getListaCategorias();
		//repo.saveAll(categorias);
	}
	
	/**
	 *  Metodo Delete - interfaz CrudRepository
	 */
	private void Eliminar() {
		int idCategoria=5;
		repo.deleteById(idCategoria);
		System.out.println("Registro eliminado");
	}
	
	/**
	 * Metodo DeleteAll- interfaz CrudRepository
	 */
	private void eliminarTodos() {
		repo.deleteAll();
	}

}
