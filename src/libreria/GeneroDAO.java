package libreria;

import java.sql.*;
import java.util.*;

public class GeneroDAO {

    private Connection conexion;

    public GeneroDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertar(Genero genero) throws SQLException {
        String sql = "INSERT INTO genero (id_genero, nombre_genero) VALUES (?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, genero.getIdGenero());
            stmt.setString(2, genero.getNombreGenero());
            stmt.executeUpdate();
        }
    }

    public void actualizar(Genero genero) throws SQLException {
        String sql = "UPDATE genero SET nombre_genero = ? WHERE id_genero = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, genero.getNombreGenero());
            stmt.setInt(2, genero.getIdGenero());
            stmt.executeUpdate();
        }
    }

    public void eliminar(int idGenero) throws SQLException {
        String sql = "DELETE FROM genero WHERE id_genero = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idGenero);
            stmt.executeUpdate();
        }
    }

    public Genero buscarPorId(int idGenero) throws SQLException {
        String sql = "SELECT * FROM genero WHERE id_genero = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idGenero);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Genero(rs.getInt("id_genero"), rs.getString("nombre_genero"));
            }
        }
        return null;
    }

    public List<Genero> listarTodos() throws SQLException {
        List<Genero> lista = new ArrayList<>();
        String sql = "SELECT * FROM genero";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Genero(rs.getInt("id_genero"), rs.getString("nombre_genero")));
            }
        }
        return lista;
    }
}
