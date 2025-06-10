package Usuarios;

import Componente.Nota;

import java.util.Map;
import java.util.TreeMap;

public class Estudiante {

    private String idEstudiante;
    private String nombreEstudiante;
    private final Map<Integer, Nota> notas = new TreeMap<>();//Map para guardar las notas
    private double notaFinal;
    private double cantNotas;

    public Estudiante() {
    }

    public void registrarEstudiante(String id, String nombre) {
        this.idEstudiante = id;
        this.nombreEstudiante = nombre;
    }

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public Map<Integer, Nota> getNotas() {
        return notas;
    }

    public void agregarNotas(int id, Nota nota) {
        notas.put(id, nota);
        cantNotas++;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    //Se sobreescribe esta clase para dar formato propio a la impression de un objeto de tipo Usuarios.Estudiante
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();//Permite ir editando cadenas de texto
        sb.append("===Estudiante===\n").append("Id: ")
                .append(idEstudiante)//Con él .append se agregan fragmentos de texto a la impresion
                .append("\nNombre: ")
                .append(nombreEstudiante)
                .append("\n===Notas===")
                .append("\nID | NOTA   | PORCENTAJE | NOTA PORCENTAJE |\n");

        notas.forEach((numeroNota, nota) -> {//se recorre el map para dar formato a cada nota
            sb.append(numeroNota).append("    | ")
                    .append(String.format("%.2f ", nota.getVrNota()))//se cambia el formato de los doubles
                    .append("    | ")
                    .append(String.format("%.0f%%", nota.getPorcentaje()))
                    .append("    | ")
                    .append(String.format("%.2f", nota.getVrNotaPorcentaje()))
                    .append("    | \n");

        });
        sb.append("-------------------\n")
                .append("NOTA FINAL: ")
                .append(String.format("%.2f", calcularNotaFinal()))
                .append("\nTotal Notas: ")
                .append(String.format("%.0f", getCantNotas()))
                .append("\n");
        return sb.toString();
    }

    public double getCantNotas() {
        return cantNotas;
    }


    public double calcularNotaFinal() {
        double sumNotas = 0;
        //Se recorre el map sumando las notas
        for (Nota n : notas.values()) {
            sumNotas += n.getVrNotaPorcentaje();
        }
        setNotaFinal(sumNotas);
        return getNotaFinal();
    }

    public void borrarNotas() {
        notas.clear();//limpia el map de notas del estudiante
    }
}
