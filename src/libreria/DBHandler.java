package libreria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBHandler {
	private static final String URL = "jdbc:mysql://localhost:3306/libreria";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";
	
	public static Connection startConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			JOptionPane.showMessageDialog(null, "Conectado correctamente a la base de datos. Bienvenido al programa de gestión de base de datos de Librería");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return conn;
	}
	
	public static String getText(Connection conn, String columna, String tabla, String nombreId, int id) {
	    String texto = "";
	    try {
	        String sql = "SELECT " + columna + " FROM " + tabla + " WHERE " + nombreId + " = ?";
	        PreparedStatement stt = conn.prepareStatement(sql);
	        stt.setInt(1, id);
	        ResultSet rs = stt.executeQuery();
	        if (rs.next()) {
	            texto = rs.getString(1);
	        }
	        rs.close();
	        stt.close();
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, e.getMessage());
	    }
	    return texto;
	}
}
