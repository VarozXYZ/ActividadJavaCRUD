package libreria;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AutorMenu {

    public static void mostrarMenu(Scanner sc, AutorDAO autorDAO) {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE AUTORES ===");
            System.out.println("1. Listar autores");
            System.out.println("2. Agregar autor");
            System.out.println("3. Modificar autor");
            System.out.println("4. Eliminar autor");
            System.out.println("0. Volver al menú principal");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            try {
                switch (opcion) {
                    case 1 -> autorDAO.listarTodos().forEach(System.out::println);
                    case 2 -> {
                        System.out.print("ID: ");
                        int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Nacionalidad: ");
                        String nac = sc.nextLine();
                        autorDAO.insertar(new Autor(id, nombre, nac));
                        System.out.println("Autor agregado.");
                    }
                    case 3 -> {
                        System.out.print("ID a modificar: ");
                        int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Nuevo nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Nueva nacionalidad: ");
                        String nac = sc.nextLine();
                        autorDAO.actualizar(new Autor(id, nombre, nac));
                        System.out.println("Autor actualizado.");
                    }
                    case 4 -> {
                        System.out.print("ID a eliminar: ");
                        int id = sc.nextInt(); sc.nextLine();
                        autorDAO.eliminar(id);
                        System.out.println("Autor eliminado.");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }
}
