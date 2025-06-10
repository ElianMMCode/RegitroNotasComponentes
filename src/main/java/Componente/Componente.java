package Componente;

import Usuarios.Estudiante;

import java.util.Map;
import java.util.TreeMap;

public class Componente {

    private String nombreComponente;
    private int totalEstudiantes;
    private final Map<String, Estudiante> listEstudiantes = new TreeMap<>();

    public Componente() {
        totalEstudiantes = 0;
    }

    public void registrarInfoComponente(String nombreComponente){
        this.totalEstudiantes = listEstudiantes.size();
        this.nombreComponente = nombreComponente;
    }

    public String getNombreComponente() {
        return nombreComponente;
    }

    public Map<String, Estudiante> getListEstudiantes(){
        return listEstudiantes;
    }

    @Override
    public String toString (){
        return "Componente: "+nombreComponente.toUpperCase()+" \n"+
                "Total Estudiantes: "+totalEstudiantes+"\n";
    }

}

