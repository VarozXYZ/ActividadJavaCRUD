package libreria;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class GeneroMenu {

    public static void mostrarMenu(Scanner sc, GeneroDAO generoDAO) {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE GÉNEROS ===");
            System.out.println("1. Listar géneros");
            System.out.println("2. Agregar género");
            System.out.println("3. Modificar género");
            System.out.println("4. Eliminar género");
            System.out.println("0. Volver al menú principal");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            try {
                switch (opcion) {
                    case 1 -> {
                        List<Genero> generos = generoDAO.listarTodos();
                        generos.forEach(System.out::println);
                    }
                    case 2 -> {
                        System.out.print("ID: ");
                        int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        generoDAO.insertar(new Genero(id, nombre));
                        System.out.println("Género agregado.");
                    }
                    case 3 -> {
                        System.out.print("ID del género a modificar: ");
                        int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Nuevo nombre: ");
                        String nombre = sc.nextLine();
                        generoDAO.actualizar(new Genero(id, nombre));
                        System.out.println("Género actualizado.");
                    }
                    case 4 -> {
                        System.out.print("ID del género a eliminar: ");
                        int id = sc.nextInt(); sc.nextLine();
                        generoDAO.eliminar(id);
                        System.out.println("Género eliminado.");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }
}
