import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Configuration
@ComponentScan(basePackages = "com.biblioteca")
public class AppConfig {
}

@Component
public class ServicioBiblioteca {
    private List<Libro> catalogo;

    public ServicioBiblioteca() {
        this.catalogo = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) { catalogo.add(libro); }

    public void mostrarCatalogo() {
        catalogo.forEach(System.out::println);
    }
}

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ServicioBiblioteca biblioteca = context.getBean(ServicioBiblioteca.class);
        
        biblioteca.agregarLibro(new Libro("Harry Potter La Piedra Filosofal", "J. K. Rowling", "1349"));
        biblioteca.agregarLibro(new Libro("Grandes esperanzas", "Charles Dickens", "88457"));
        biblioteca.agregarLibro(new Libro("Cien Años de Soledad", "Gabriel García Márquez", "34356"));
        
        System.out.println("\nCatálogo actual:");
        biblioteca.mostrarCatalogo();
    }
}
