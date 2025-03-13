import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

// Interfaz genérica Repositorio
interface Repositorio<T> {
    void agregar(T item);
    void eliminar(T item);
    List<T> obtenerTodos();
    List<T> buscar(String criterio);
}

// Clase Libro
class Libro {
    private String titulo;
    private String autor;
    private String isbn;
    private boolean prestado;

    public Libro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.prestado = false;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public boolean isPrestado() { return prestado; }
    
    public void prestar() { this.prestado = true; }
    public void devolver() { this.prestado = false; }

    @Override
    public String toString() {
        return "Libro: " + titulo + " | Autor: " + autor + " | ISBN: " + isbn + " | Prestado: " + prestado;
    }
}

// Implementación de Repositorio para libros
@Component
class LibroRepositorio implements Repositorio<Libro> {
    private List<Libro> catalogo = new ArrayList<>();

    @Override
    public void agregar(Libro libro) { catalogo.add(libro); }
    
    @Override
    public void eliminar(Libro libro) { catalogo.remove(libro); }
    
    @Override
    public List<Libro> obtenerTodos() { return new ArrayList<>(catalogo); }
    
    @Override
    public List<Libro> buscar(String criterio) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : catalogo) {
            if (libro.getTitulo().toLowerCase().contains(criterio.toLowerCase()) ||
                libro.getAutor().toLowerCase().contains(criterio.toLowerCase())) {
                resultados.add(libro);
            }
        }
        return resultados;
    }
}

// Implementación de Repositorio para periódicos
@Component
class PeriodicoRepositorio implements Repositorio<String> {
    private List<String> periodicos = new ArrayList<>();

    @Override
    public void agregar(String periodico) { periodicos.add(periodico); }
    
    @Override
    public void eliminar(String periodico) { periodicos.remove(periodico); }
    
    @Override
    public List<String> obtenerTodos() { return new ArrayList<>(periodicos); }
    
    @Override
    public List<String> buscar(String criterio) {
        List<String> resultados = new ArrayList<>();
        for (String periodico : periodicos) {
            if (periodico.toLowerCase().contains(criterio.toLowerCase())) {
                resultados.add(periodico);
            }
        }
        return resultados;
    }
}

// Implementación de Repositorio para computadoras
@Component
class ComputadorRepositorio implements Repositorio<String> {
    private List<String> computadores = new ArrayList<>();

    @Override
    public void agregar(String computador) { computadores.add(computador); }
    
    @Override
    public void eliminar(String computador) { computadores.remove(computador); }
    
    @Override
    public List<String> obtenerTodos() { return new ArrayList<>(computadores); }
    
    @Override
    public List<String> buscar(String criterio) {
        List<String> resultados = new ArrayList<>();
        for (String computador : computadores) {
            if (computador.toLowerCase().contains(criterio.toLowerCase())) {
                resultados.add(computador);
            }
        }
        return resultados;
    }
}

// Clase principal para pruebas
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        LibroRepositorio libroRepo = context.getBean(LibroRepositorio.class);
        PeriodicoRepositorio periodicoRepo = context.getBean(PeriodicoRepositorio.class);
        ComputadorRepositorio computadorRepo = context.getBean(ComputadorRepositorio.class);
        
        // Agregar elementos
        libroRepo.agregar(new Libro("Harry Potter La Piedra Filosofal", "J. K. Rowling", "1349"));
        libroRepo.agregar(new Libro("Grandes esperanzas", "Charles Dickens", "88457"));
        libroRepo.agregar(new Libro("Cien Años de Soledad", "Gabriel García Márquez", "54321"));
        
        periodicoRepo.agregar("El Tiempo");
        periodicoRepo.agregar("El Espectador");
        periodicoRepo.agregar("Quiubo");
        
        computadorRepo.agregar("Dell XPS 15");
        computadorRepo.agregar("MacBook Pro");
        computadorRepo.agregar("Lenovo ThinkPad");
        
        // Mostrar catálogos
        System.out.println("\nLibros en la biblioteca:");
        libroRepo.obtenerTodos().forEach(System.out::println);
        
        System.out.println("\nPeriódicos disponibles:");
        periodicoRepo.obtenerTodos().forEach(System.out::println);
        
        System.out.println("\nComputadores disponibles:");
        computadorRepo.obtenerTodos().forEach(System.out::println);
        
        // Buscar y eliminar
        String criterio = "Prin";
        List<Libro> librosEncontrados = libroRepo.buscar(criterio);
        
        if (!librosEncontrados.isEmpty()) {
            System.out.println("\nEliminando libro: " + librosEncontrados.get(0).getTitulo());
            libroRepo.eliminar(librosEncontrados.get(0));
        }
        
        // Mostrar catálogo actualizado
        System.out.println("\nLibros actualizados en la biblioteca:");
        libroRepo.obtenerTodos().forEach(System.out::println);
    }
}

// Configuración de Spring
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.biblioteca")
class AppConfig {
}
