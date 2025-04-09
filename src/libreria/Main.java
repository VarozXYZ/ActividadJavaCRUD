package libreria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/libreria";
        String user = "root";
        String password = "1234";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            GeneroDAO generoDAO = new GeneroDAO(conn);
            AutorDAO autorDAO = new AutorDAO(conn);
            LibroDAO libroDAO = new LibroDAO(conn);
            PrestamoDAO prestamoDAO = new PrestamoDAO(conn);

            int opc;
            do {
                System.out.println("\n=== MENÚ PRINCIPAL ===");
                System.out.println("1. Gestionar géneros");
                System.out.println("2. Gestionar autores");
                System.out.println("3. Gestionar libros");
                System.out.println("4. Gestionar préstamos");
                System.out.println("5. Consultar Libros con Autor y Género");
                System.out.println("6. Consultar préstamos con libros");
                System.out.println("0. Salir");
                System.out.print("Opción: ");
                opc = sc.nextInt();
                sc.nextLine();

                switch (opc) {
                    case 1 -> GeneroMenu.mostrarMenu(sc, generoDAO);
                    case 2 -> AutorMenu.mostrarMenu(sc, autorDAO);
                    case 3 -> LibroMenu.mostrarMenu(sc, libroDAO);
                    case 4 -> PrestamoMenu.mostrarMenu(sc, prestamoDAO);
                    case 5 -> mostrarConsultasLibrosAutorGenero(libroDAO, autorDAO, generoDAO);
                    case 6 -> mostrarPrestamosConLibros(prestamoDAO, libroDAO);
                    case 0 -> System.out.println("Saliendo del sistema...");
                    default -> System.out.println("Opción no válida.");
                }
            } while (opc != 0);

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }

        sc.close();
    }

    public static void mostrarConsultasLibrosAutorGenero(LibroDAO libroDAO, AutorDAO autorDAO, GeneroDAO generoDAO) {
        try {
            for (Libro libro : libroDAO.listarTodos()) {
                Autor autor = autorDAO.buscarPorId(libro.getIdAutor());
                Genero genero = generoDAO.buscarPorId(libro.getIdGenero());
                System.out.printf("%s | Autor: %s | Género: %s%n", libro.getTitulo(), autor.getNombreAutor(), genero.getNombreGenero());
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar libros: " + e.getMessage());
        }
    }

    public static void mostrarPrestamosConLibros(PrestamoDAO prestamoDAO, LibroDAO libroDAO) {
        try {
            for (Prestamo p : prestamoDAO.listarTodos()) {
                Libro libro = libroDAO.buscarPorId(p.getIdLibro());
                String estado = p.isDevuelto() ? "Devuelto" : "Pendiente";
                System.out.printf("Libro: %s | Fecha: %s | Estado: %s%n", libro.getTitulo(), p.getFechaPrestamo(), estado);
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar préstamos: " + e.getMessage());
        }
    }
}
