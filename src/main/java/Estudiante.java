import java.util.Map;
import java.util.TreeMap;

public class Estudiante {

    private String idEstudiante;
    private String nombreEstudiante;
    private final Map<Integer, Nota> notas = new TreeMap<>();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("===Estudiante===\n").append("Id: ")
                .append(idEstudiante)
                .append("\nNombre: ")
                .append(nombreEstudiante)
                .append("\n===Notas===")
                .append("\nComponente: ")
                .append(notas.get(1).getComponente())
                .append("\nID | NOTA   | PORCENTAJE | NOTA PORCENTAJE |\n");

        notas.forEach((numeroNota, nota) -> {
            sb.append(numeroNota).append("    | ")
                    .append(String.format("%.2f ", nota.getVrNota()))
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
        for (Nota n : notas.values()) {
            sumNotas += n.getVrNotaPorcentaje();
        }
        setNotaFinal(sumNotas / cantNotas);
        return getNotaFinal();
    }

    public void borrarNotas() {
        notas.clear();
    }
}
