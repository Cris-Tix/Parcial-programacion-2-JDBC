package parcialJava.model;

public class Duenio {
    private int idDuenio;
    private String nombre;
    private String celDuenio;

    public int getIdDuenio() {
        return idDuenio;
    }

    public void setIdDuenio(int idDuenio) {
        this.idDuenio = idDuenio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelDuenio() {
        return celDuenio;
    }

    public void setCelDuenio(String celDuenio) {
        this.celDuenio = celDuenio;
    }

    @Override
    public String toString() {
        return "Duenio{" +
                "idDuenio=" + idDuenio +
                ", nombre='" + nombre + '\'' +
                ", celDuenio='" + celDuenio + '\'' +
                '}';
    }
}