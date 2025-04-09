package libreria;

import java.sql.Date;

public class Prestamo {
    private int idLibro;
    private Date fechaPrestamo;
    private boolean devuelto;

    public Prestamo(int idLibro, Date fechaPrestamo, boolean devuelto) {
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.devuelto = devuelto;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }

    @Override
    public String toString() {
        return "Libro ID: " + idLibro + " - Prestado: " + fechaPrestamo + " - Devuelto: " + (devuelto ? "Sí" : "No");
    }
}
