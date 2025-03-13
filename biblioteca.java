import java.util.ArrayList;
import java.util.List;

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

// Clase Usuario
class Usuario {
    private String nombre;
    private String id;
    private List<Libro> librosPrestados;

    public Usuario(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.librosPrestados = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public String getId() { return id; }
    public List<Libro> getLibrosPrestados() { return librosPrestados; }
    
    public void prestarLibro(Libro libro) {
        if (!libro.isPrestado()) {
            libro.prestar();
            librosPrestados.add(libro);
            System.out.println(nombre + " ha tomado prestado el libro: " + libro.getTitulo());
        } else {
            System.out.println("El libro ya está prestado.");
        }
    }

    public void devolverLibro(Libro libro) {
        if (librosPrestados.contains(libro)) {
            libro.devolver();
            librosPrestados.remove(libro);
            System.out.println(nombre + " ha devuelto el libro: " + libro.getTitulo());
        } else {
            System.out.println("El usuario no tiene este libro prestado.");
        }
    }
}

// Clase ServicioBiblioteca
class ServicioBiblioteca {
    private List<Libro> catalogo;

    public ServicioBiblioteca() {
        this.catalogo = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) { catalogo.add(libro); }

    public void mostrarCatalogo() {
        for (Libro libro : catalogo) {
            System.out.println(libro);
        }
    }

    public List<Libro> buscarLibros(String criterio) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : catalogo) {
            if (libro.getTitulo().toLowerCase().contains(criterio.toLowerCase()) ||
                libro.getAutor().toLowerCase().contains(criterio.toLowerCase())) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    public void eliminarLibro(Libro libro) {
        catalogo.remove(libro);
        System.out.println("Libro eliminado: " + libro.getTitulo());
    }
}

// Clase principal para pruebas
public class Main {
    public static void main(String[] args) {
        ServicioBiblioteca biblioteca = new ServicioBiblioteca();
        
        // Agregar al menos 3 libros
        biblioteca.agregarLibro(new Libro("1984", "George Orwell", "12345"));
        biblioteca.agregarLibro(new Libro("El Principito", "Antoine de Saint-Exupéry", "67890"));
        biblioteca.agregarLibro(new Libro("Cien Años de Soledad", "Gabriel García Márquez", "54321"));
        
        // Imprimir catálogo actual
        System.out.println("\nCatálogo actual:");
        biblioteca.mostrarCatalogo();
        
        // Buscar libros con un criterio parcial
        String criterio = "Prin";
        List<Libro> resultados = biblioteca.buscarLibros(criterio);
        
        System.out.println("\nResultados de búsqueda para '" + criterio + "':");
        for (Libro libro : resultados) {
            System.out.println(libro);
        }
        
        // Eliminar el primer resultado de la búsqueda
        if (!resultados.isEmpty()) {
            biblioteca.eliminarLibro(resultados.get(0));
        }
        
        // Imprimir catálogo actualizado
        System.out.println("\nCatálogo actualizado:");
        biblioteca.mostrarCatalogo();
    }
}
