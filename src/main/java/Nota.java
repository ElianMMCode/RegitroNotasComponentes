
public class Nota {

    private final double vrNota;
    private final double porcentaje;
    private final double vrNotaPorcentaje;
    private final Componente componente;


    public Nota(double nota, double porcentaje, Componente componente) {
        this.vrNota = nota;
        this.porcentaje = porcentaje;
        this.componente = componente;
        this.vrNotaPorcentaje = nota * (porcentaje / 100);
    }

    public double getVrNota() {
        return vrNota;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public double getVrNotaPorcentaje() {
        return vrNotaPorcentaje;
    }

    public Componente getComponente() {
        return componente;
    }

    @Override
    public String toString() {
        String prcF = String.format("%.0f", porcentaje);
        String ntF = String.format("%.0f", vrNota);
        return "| Nota: " + ntF +
                "| Porcentaje: " + prcF + "| Nota Porcentaje: " + vrNotaPorcentaje + "\n";
    }

}
