package Componente;

public class Nota {

    private final double vrNota;
    private final double porcentaje;
    private final double vrNotaPorcentaje;


    public Nota(double nota, double porcentaje) {
        this.vrNota = nota;
        this.porcentaje = porcentaje;
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


    @Override
    //Imprime la información de los atributos propios de un objeto de la clase
    public String toString() {
        String prcF = String.format("%.0f", porcentaje);//Se convierte y guarda el double en un string con formato
        String ntF = String.format("%.0f", vrNota);
        return "| Componente.Nota: " + ntF +
                "| Porcentaje: " + prcF + "| Componente.Nota Porcentaje: " + vrNotaPorcentaje + "\n";
    }

}
