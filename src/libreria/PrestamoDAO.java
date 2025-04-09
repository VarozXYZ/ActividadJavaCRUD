package libreria;

import java.sql.*;
import java.util.*;

public class PrestamoDAO {

    private Connection conexion;

    public PrestamoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertar(Prestamo prestamo) throws SQLException {
        String sql = "INSERT INTO prestamo (id_libro, fecha_prestamo, devuelto) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, prestamo.getIdLibro());
            stmt.setDate(2, prestamo.getFechaPrestamo());
            stmt.setBoolean(3, prestamo.isDevuelto());
            stmt.executeUpdate();
        }
    }

    public void actualizar(Prestamo prestamo) throws SQLException {
        String sql = "UPDATE prestamo SET fecha_prestamo = ?, devuelto = ? WHERE id_libro = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setDate(1, prestamo.getFechaPrestamo());
            stmt.setBoolean(2, prestamo.isDevuelto());
            stmt.setInt(3, prestamo.getIdLibro());
            stmt.executeUpdate();
        }
    }

    public void eliminar(int idLibro) throws SQLException {
        String sql = "DELETE FROM prestamo WHERE id_libro = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idLibro);
            stmt.executeUpdate();
        }
    }

    public Prestamo buscarPorIdLibro(int idLibro) throws SQLException {
        String sql = "SELECT * FROM prestamo WHERE id_libro = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idLibro);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Prestamo(
                    rs.getInt("id_libro"),
                    rs.getDate("fecha_prestamo"),
                    rs.getBoolean("devuelto")
                );
            }
        }
        return null;
    }

    public List<Prestamo> listarTodos() throws SQLException {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamo";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Prestamo(
                    rs.getInt("id_libro"),
                    rs.getDate("fecha_prestamo"),
                    rs.getBoolean("devuelto")
                ));
            }
        }
        return lista;
    }
}
