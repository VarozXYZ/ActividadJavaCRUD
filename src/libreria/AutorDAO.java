package libreria;

import java.sql.*;
import java.util.*;

public class AutorDAO {

    private Connection conexion;

    public AutorDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertar(Autor autor) throws SQLException {
        String sql = "INSERT INTO autor (id_autor, nombre_autor, nacionalidad) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, autor.getIdAutor());
            stmt.setString(2, autor.getNombreAutor());
            stmt.setString(3, autor.getNacionalidad());
            stmt.executeUpdate();
        }
    }

    public void actualizar(Autor autor) throws SQLException {
        String sql = "UPDATE autor SET nombre_autor = ?, nacionalidad = ? WHERE id_autor = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, autor.getNombreAutor());
            stmt.setString(2, autor.getNacionalidad());
            stmt.setInt(3, autor.getIdAutor());
            stmt.executeUpdate();
        }
    }

    public void eliminar(int idAutor) throws SQLException {
        String sql = "DELETE FROM autor WHERE id_autor = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idAutor);
            stmt.executeUpdate();
        }
    }

    public Autor buscarPorId(int idAutor) throws SQLException {
        String sql = "SELECT * FROM autor WHERE id_autor = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idAutor);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Autor(rs.getInt("id_autor"), rs.getString("nombre_autor"), rs.getString("nacionalidad"));
            }
        }
        return null;
    }

    public List<Autor> listarTodos() throws SQLException {
        List<Autor> lista = new ArrayList<>();
        String sql = "SELECT * FROM autor";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Autor(rs.getInt("id_autor"), rs.getString("nombre_autor"), rs.getString("nacionalidad")));
            }
        }
        return lista;
    }
}
