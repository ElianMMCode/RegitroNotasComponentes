package Usuarios;

import Componente.Componente;

import java.util.ArrayList;

public class Profesor extends Usuario {

    private final String pswdProfesor;
    private final ArrayList<Componente> componentes = new ArrayList<>();


    public Profesor(String id, String nombre, String pswdProfesor) {
        super(id,nombre);
        this.pswdProfesor = pswdProfesor;
    }

    public String password (){
        return pswdProfesor;
    }

    public ArrayList<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(Componente c) {
        componentes.add(c);
    }

    @Override
    public String toString() {
        return "Profesor: "+nombre+"\n";
    }
}
