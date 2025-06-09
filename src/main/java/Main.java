import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;

public class Main {

    //Correciones
    //Definir al docente como usuario y sus respectivas funcionalidades
    //Un rol estudiante que solo pueda mirar sus notas
    //Definir una clase interface para ser invocada por el main

    public static void main(String[] args) {
        //Se inicia la opcion en -1 para en caso de no leer nada salte a la opcion default
        int opcion = -1;
        //Map donde se guardaran los estudiantes
        Map<String, Estudiante> estudiantes = new HashMap<>();//Se crea HashMap para que los estudiantes se guarden el orden de insercion

        //Con este while se permite que después de salir de una opcion se repita el switch hasta escoger la opcion 0
        while (opcion != 0) {


            String op = JOptionPane.showInputDialog(null,
                    """
                            1. Registrar estudiante\
                            
                            2. Listar estudiantes\
                            P
                            3. Ver información de estudiante\
                            
                            0. Salir""","Registro Notas Estudiantes", JOptionPane.PLAIN_MESSAGE);
            opcion = strToInteger(op);
            switch (opcion) {
                case 1:
                    opcionRegistrarEstudiante(estudiantes);//Registro de la info del estudiante, sus notas y el componente asociado a estas
                    break;
                case 2:
                    opcionListarEstudiantes(estudiantes);//Lista de todos los estudiantes registrados
                    break;
                case 3:
                    opcionVerInformacionEstudiantes(estudiantes);//Ver la información detallada de uno de los estudiantes registrados
                    break;
                case 0:
                    opcionSalir();
                    break;
                default:
                    opcionOpcionInvalida();
            }
        }
    }

    private static void opcionRegistrarEstudiante(Map<String, Estudiante> estudiantes) {
        String idEst = JOptionPane.showInputDialog(null,
                "Ingrese el id del estudiante: ","Registro Estudiantes", JOptionPane.PLAIN_MESSAGE);
        if (!estudiantes.containsKey(idEst)) {//Sé verífica que al registrar el ID no exista en otro estudiante
            String nmEst = JOptionPane.showInputDialog("Ingrese el nombre del estudiante: ");
            JOptionPane.showMessageDialog(null, "Estudiante registrado con exito!");
            Estudiante est = new Estudiante();//Se crea el estudiante
            est.registrarEstudiante(idEst, nmEst);//Se inserta su informacion
            estudiantes.put(est.getIdEstudiante(), est);//Se identifica y se guarda en el Map


            String nmComp = JOptionPane.showInputDialog("Registro Componente");
            String prfComp = JOptionPane.showInputDialog("Profesor del componente");
            Componente comp = new Componente();//Se crea el componente
            comp.registrarInfoComponente(nmComp, prfComp);//Se registra la informacion del componente

            //Notas
            int salida;//Opcion para dejar de guardar notas
            int i = 1;//Id para las notas
            do {
                do {
                    double nt = strToDouble(JOptionPane.showInputDialog("Nota"+ i));//convierte String en double o un numero negativo en caso de error
                    if (nt > 0) {
                        double prcNt = strToDouble(JOptionPane.showInputDialog("Porcentaje de la nota "+ i));
                        if (prcNt > 0) {
                            Nota nota = new Nota(nt, prcNt, comp);
                            est.agregarNotas(i, nota);
                            JOptionPane.showMessageDialog(null, "Nota Registrada!", " ", JOptionPane.ERROR_MESSAGE);
                            i++;
                        } else {
                            JOptionPane.showMessageDialog(null, "Valor ingesado invalido", "Exito!", JOptionPane.PLAIN_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Valor ingresado incorrecto", "Error!", JOptionPane.PLAIN_MESSAGE);
                    }
                    salida = JOptionPane.showConfirmDialog(null, "Desea ingresar otra nota", "Error!", JOptionPane.YES_NO_OPTION);
                } while (salida == 0);
                if (porcentajeMxCn(est) == false) {//Solo permite guardar cuando los porcentajes de las notas no superan el 100%
                    JOptionPane.showMessageDialog(null, "Valores de porcentajes por encima de 100%", "Error!", JOptionPane.PLAIN_MESSAGE);
                    est.borrarNotas();//Si las notas superan el 100% se borran todas la notas ingresadas
                    i = 1;//Se reinicia el ID para volver a registrar las notas
                }
            } while (porcentajeMxCn(est) == false);//El registro de las notas solo permite salir cuando no se supera el 100%
        } else {
            JOptionPane.showMessageDialog(null, "Estudiante con ID (" + idEst + ") ya registrado");
        }
    }

    private static void opcionListarEstudiantes(Map<String, Estudiante> estudiantes) {
        if (estudiantes.isEmpty()) {//Sé verífica que exista registro del algún estudiante
            JOptionPane.showMessageDialog(null, "No hay estudiantes registrados", "Lista de Estudiantes", JOptionPane.PLAIN_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder();
            for (Estudiante e : estudiantes.values()) {
                sb.append(e.toString());
            }
            String textoJustificado = sb.toString();
            JOptionPane.showMessageDialog(null,
                    "ID  | NOMBRE   |\n" +
                            "-----------------------\n" +
                            textoJustificado, "Lista estudiantes", JOptionPane.PLAIN_MESSAGE);

        }
    }

    private static void opcionVerInformacionEstudiantes(Map<String, Estudiante> estudiantes) {
        int opcion2 = -1;
        while (opcion2 != 0) {
            opcion2 = strToInteger(JOptionPane.showInputDialog(null, """
                            1. Buscar estudiante por id\
                            
                            2. Buscar estudiante por nombre\
                            
                            0. Volver""",
                    "Informacion Estudiantes"));
            switch (opcion2) {
                case 1:
                    buscarPorId(estudiantes);
                    break;
                case 2:
                    buscarPorNombre(estudiantes);
                    break;
                case 0:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion invalida. Intenta de nuevo", "Error!", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private static void buscarPorId(Map<String, Estudiante> estudiantes) {
        String bsIdEst = JOptionPane.showInputDialog(null,"Ingrese el id del estudiante: ");
        if (estudiantes.containsKey(bsIdEst)) {//Se busca por ID al estudiante, si no encuentra devuelve false
            Estudiante bsEst = estudiantes.get(bsIdEst);//Sí lo encuentra sus atributos sé asociando a un objeto
            JOptionPane.showMessageDialog(null,bsEst.toString(), "Info estudiante", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,"Id no asociado a un estudiante","Error!", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private static void buscarPorNombre(Map<String, Estudiante> estudiantes) {
        String bsNmEst = JOptionPane.showInputDialog("Ingrese el nombre del estudiante: ");
        for (Estudiante e : estudiantes.values())//Se recorre el map buscando un estudiante que coincida el nombre
            if (e.getNombreEstudiante().equalsIgnoreCase(bsNmEst)) { //ignorando mayúsculas y minuscules
                JOptionPane.showMessageDialog(null,e.toString(), "Info estudiante", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No existe un estudiante con ese nombre", "Error!", JOptionPane.PLAIN_MESSAGE);
            }
    }

    private static void opcionSalir() {
        JOptionPane.showMessageDialog(null, "Saliendo");
    }

    private static void opcionOpcionInvalida() {
        JOptionPane.showMessageDialog(null, "Opcion invalida",  "Error!", JOptionPane.PLAIN_MESSAGE);
    }

    //Lee un string y retorna un dooble - controla errores por lectura
    public static Double strToDouble(String string) {
        double d;
        try {
            d = Double.parseDouble(string);
        } catch (NumberFormatException e) {//Se controla posible error de Scanner
            d = -1.0;//Si detecta error devuelve un negativo
        }
        return d;
    }

    //Lee un string y devuelve un integer-controla errores por lectura
    public static Integer strToInteger(String string) {
        int i;
        try {
            i = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            i = -1;
        }
        return i;
    }

    //Verifica que la suma de porcentajes de las notas no pase el 100%
    public static Boolean porcentajeMxCn(Estudiante e) {
        Map<Integer, Nota> notas = e.getNotas();
        double sumPrct = 0.0;
        boolean limPrct = false;
        for (Nota n : notas.values()) {
            sumPrct += n.getPorcentaje();
        }
        if (sumPrct <= 100) {
            limPrct = true;
        }
        return limPrct;
    }
}
