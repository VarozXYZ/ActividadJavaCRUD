package libreria;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PrestamoMenu {

    public static void mostrarMenu(Scanner sc, PrestamoDAO prestamoDAO) {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE PRÉSTAMOS ===");
            System.out.println("1. Listar préstamos");
            System.out.println("2. Registrar préstamo");
            System.out.println("3. Marcar como devuelto");
            System.out.println("4. Eliminar préstamo");
            System.out.println("0. Volver al menú principal");
            System.out.print("Opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            try {
                switch (opcion) {
                    case 1 -> prestamoDAO.listarTodos().forEach(System.out::println);
                    case 2 -> {
                        System.out.print("ID Libro: ");
                        int idLibro = sc.nextInt(); sc.nextLine();
                        System.out.print("Fecha (YYYY-MM-DD): ");
                        Date fecha = Date.valueOf(sc.nextLine());
                        prestamoDAO.insertar(new Prestamo(idLibro, fecha, false));
                        System.out.println("Préstamo registrado.");
                    }
                    case 3 -> {
                        System.out.print("ID Libro: ");
                        int idLibro = sc.nextInt(); sc.nextLine();
                        Prestamo p = prestamoDAO.buscarPorIdLibro(idLibro);
                        if (p != null) {
                            p.setDevuelto(true);
                            prestamoDAO.actualizar(p);
                            System.out.println("Préstamo actualizado como devuelto.");
                        } else {
                            System.out.println("Préstamo no encontrado.");
                        }
                    }
                    case 4 -> {
                        System.out.print("ID Libro: ");
                        int idLibro = sc.nextInt(); sc.nextLine();
                        prestamoDAO.eliminar(idLibro);
                        System.out.println("Préstamo eliminado.");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }
}
