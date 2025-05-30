
public class Nota {

    private final double vrNota;
    private final double porcentaje;
    private final double vrNotaPorcentaje;
    private final Componente componente;//Se guarda la información del componente asociado a la nota


    public Nota(double nota, double porcentaje, Componente componente) {
        this.vrNota = nota;
        this.porcentaje = porcentaje;
        this.componente = componente;
        this.vrNotaPorcentaje = nota * (porcentaje / 100);//Se convierte la nota a su porcentaje al momento de crear la
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
    //Imprime la información de los atributos propios de un objeto de la clase
    public String toString() {
        String prcF = String.format("%.0f", porcentaje);//Se convierte y guarda el double en un string con formato
        String ntF = String.format("%.0f", vrNota);
        return "| Nota: " + ntF +
                "| Porcentaje: " + prcF + "| Nota Porcentaje: " + vrNotaPorcentaje + "\n";
    }

}
