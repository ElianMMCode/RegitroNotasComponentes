public class Componente {

    private String nombreComponente;
    public String profesor;


    public Componente (){}

    public void registrarInfoComponente(String nombreComponente, String profesor){
        this.nombreComponente = nombreComponente;
       this.profesor = profesor;
    }

    @Override
    public String toString (){
        return nombreComponente +
               "\nProfesor: " + profesor;
    }

}

