package net.scrafet;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import net.scrafet.model.Categoria;
import net.scrafet.model.Vacante;
import net.scrafet.repository.CategoriasRepository;
import net.scrafet.repository.VacantesRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriasRepository repoCategorias;
	
	
	@Autowired
	private VacantesRepository repoVacantes;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		buscarVacantes();
	}
	
	/**
	 * Metodo count - interfaz CrudRepository
	 */
	private void conteo() {
		long count = repoCategorias.count();
		System.out.println("Total Categorias : " + count);
	}
	
	/**
	 * Metodo ExistById - interfaz CrudRepository
	 */
	private void existeId() {
		boolean existe = repoCategorias.existsById(5);
		System.out.println("La categoria existe : " + existe);
	}
	
	
	/**
	 *  Metodo Save(update) - interfaz CrudRepository
	 */
	private void modificar() {
Optional<Categoria> optional = repoCategorias.findById(2);
		
		if (optional.isPresent()) {
			Categoria catTmp = optional.get();
			catTmp.setNombre("Ingenieria de Software");
			catTmp.setDescripcion("Desarrollo de Sistemas");
			repoCategorias.save(catTmp);
			System.out.println(optional.get());
		}
	    else
			System.out.println("Categoria no encontrada");
		
	}
	
	/**
	 * Metodo findAll - interfaz CrudRepository
	 */
	private void buscarTodos() {
		Iterable<Categoria>  categorias = repoCategorias.findAll();
		for (Categoria cat : categorias) {
			System.out.println(cat);
		}
		
	}
	/**
	 * Metodo findAll - interfaz JpaRepository
	 */
	private void buscarTodosJpa() {
		List<Categoria> categorias=repoCategorias.findAll();
		for (Categoria c : categorias) {
			System.out.println(c.getId() + " " + c.getNombre()  );
		}
	}
	
	/**
	 * Metodo findById - interfaz CrudRepository
	 */
	private void buscarPorId() {
		Optional<Categoria> optional = repoCategorias.findById(6);
		
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
		Iterable<Categoria> categorias = repoCategorias.findAllById(ids);
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
		repoCategorias.save(cat);
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
		repoCategorias.deleteById(idCategoria);
		System.out.println("Registro eliminado");
	}
	
	/**
	 * Metodo DeleteAll- interfaz CrudRepository
	 */
	private void eliminarTodos() {
		repoCategorias.deleteAll();
	}
	
	/**
	 * Metodo deleteallInBatch- interfaz JpaRepository
	 */
	private void borrarTodoEnBloque() {
		repoCategorias.deleteAllInBatch();
	}
	/**
	 * Metodo findAll [ordenados por campos ] -  interfaz JpaRepository
	 */
	
	private void buscarTodosOrdenados() {
		List<Categoria> categorias = repoCategorias.findAll(Sort.by("nombre").descending());
		for (Categoria c : categorias) {
			System.out.println(c.getId() + " " + c.getNombre());
		}
	}
	
	/**
	 * Metodo findAll [con paginacion] -  interfaz JpaRepository
	 */
	private void buscarTodosPaginacion() {
		Page<Categoria> page =  repoCategorias.findAll(PageRequest.of(1, 5));
		System.out.println("Total Registros : " + page.getTotalElements());
		System.out.println("Total Paginas : " + page.getTotalPages());
		for (Categoria c : page.getContent()) {
			System.out.println(c.getId() + " " +  c.getNombre());
			
		}
	}
	
	/**
	 * Metodo findAll [con paginacion y ordenados] -  interfaz JpaRepository
	 */
	private void buscarTodosPaginacionOrdenados() {
		Page<Categoria> page =  repoCategorias.findAll(PageRequest.of(1, 5, Sort.by("nombre").descending()));
		System.out.println("Total Registros : " + page.getTotalElements());
		System.out.println("Total Paginas : " + page.getTotalPages());
		for (Categoria c : page.getContent()) {
			System.out.println(c.getId() + " " +  c.getNombre());			
		}	
	}
	
	private void buscarVacantes() {
		List<Vacante> lista=repoVacantes.findAll();
		for (Vacante v : lista) {
			System.out.println(v.getId() + " " + v.getNombre() + " => " + v.getCategoria().getNombre());
			
		}
	}

}
