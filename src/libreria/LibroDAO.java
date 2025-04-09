package libreria;

import java.sql.*;
import java.util.*;

public class LibroDAO {

    private Connection conexion;

    public LibroDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertar(Libro libro) throws SQLException {
        String sql = "INSERT INTO libro (id_libro, titulo, anio_publicacion, id_autor, id_genero) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, libro.getIdLibro());
            stmt.setString(2, libro.getTitulo());
            stmt.setInt(3, libro.getAnioPublicacion());
            stmt.setInt(4, libro.getIdAutor());
            stmt.setInt(5, libro.getIdGenero());
            stmt.executeUpdate();
        }
    }

    public void actualizar(Libro libro) throws SQLException {
        String sql = "UPDATE libro SET titulo = ?, anio_publicacion = ?, id_autor = ?, id_genero = ? WHERE id_libro = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setInt(2, libro.getAnioPublicacion());
            stmt.setInt(3, libro.getIdAutor());
            stmt.setInt(4, libro.getIdGenero());
            stmt.setInt(5, libro.getIdLibro());
            stmt.executeUpdate();
        }
    }

    public void eliminar(int idLibro) throws SQLException {
        String sql = "DELETE FROM libro WHERE id_libro = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idLibro);
            stmt.executeUpdate();
        }
    }

    public Libro buscarPorId(int idLibro) throws SQLException {
        String sql = "SELECT * FROM libro WHERE id_libro = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idLibro);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Libro(
                    rs.getInt("id_libro"),
                    rs.getString("titulo"),
                    rs.getInt("anio_publicacion"),
                    rs.getInt("id_autor"),
                    rs.getInt("id_genero")
                );
            }
        }
        return null;
    }

    public List<Libro> listarTodos() throws SQLException {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libro";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Libro(
                    rs.getInt("id_libro"),
                    rs.getString("titulo"),
                    rs.getInt("anio_publicacion"),
                    rs.getInt("id_autor"),
                    rs.getInt("id_genero")
                ));
            }
        }
        return lista;
    }
}
