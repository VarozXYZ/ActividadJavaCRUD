package libreria;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LibroMenu {

    public static void mostrarMenu(Scanner sc, LibroDAO libroDAO) {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE LIBROS ===");
            System.out.println("1. Listar libros");
            System.out.println("2. Agregar libro");
            System.out.println("3. Modificar libro");
            System.out.println("4. Eliminar libro");
            System.out.println("0. Volver al menú principal");
            System.out.print("Opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            try {
                switch (opcion) {
                    case 1 -> libroDAO.listarTodos().forEach(System.out::println);
                    case 2 -> {
                        System.out.print("ID: ");
                        int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();
                        System.out.print("Año publicación: ");
                        int anio = sc.nextInt(); sc.nextLine();
                        System.out.print("ID Autor: ");
                        int idAutor = sc.nextInt(); sc.nextLine();
                        System.out.print("ID Género: ");
                        int idGenero = sc.nextInt(); sc.nextLine();
                        libroDAO.insertar(new Libro(id, titulo, anio, idAutor, idGenero));
                        System.out.println("Libro agregado.");
                    }
                    case 3 -> {
                        System.out.print("ID del libro a modificar: ");
                        int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Nuevo título: ");
                        String titulo = sc.nextLine();
                        System.out.print("Nuevo año: ");
                        int anio = sc.nextInt(); sc.nextLine();
                        System.out.print("Nuevo ID Autor: ");
                        int idAutor = sc.nextInt(); sc.nextLine();
                        System.out.print("Nuevo ID Género: ");
                        int idGenero = sc.nextInt(); sc.nextLine();
                        libroDAO.actualizar(new Libro(id, titulo, anio, idAutor, idGenero));
                        System.out.println("Libro actualizado.");
                    }
                    case 4 -> {
                        System.out.print("ID del libro a eliminar: ");
                        int id = sc.nextInt(); sc.nextLine();
                        libroDAO.eliminar(id);
                        System.out.println("Libro eliminado.");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }
}
