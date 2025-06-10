package Usuarios;

public class Usuario {

    protected String id;
    protected String nombre;


    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

}
