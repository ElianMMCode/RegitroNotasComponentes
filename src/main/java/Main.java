import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        //Se inicia la opcion en -1 para en caso de no leer nada salte a la opcion default
        int opcion = -1;
        //Map donde se guardaran los estudiantes
        Map<String, Estudiante> estudiantes = new HashMap<>();//Se crea HashMap para que los estudiantes se guarden el orden de insercion

        //Con este while se permite que después de salir de una opcion se repita el switch hasta escoger la opcion 0
        while (opcion != 0) {
            System.out.println("===Registro Notas Estudiantes====");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Ver información de estudiante");
            System.out.println("0. Salir");
            opcion = strToInteger(kb);

            switch (opcion) {
                case 1:
                    opcionRegistrarEstudiante(kb, estudiantes);//Registro de la info del estudiante, sus notas y el componente asociado a estas
                    break;
                case 2:
                    opcionListarEstudiantes(estudiantes);//Lista de todos los estudiantes registrados
                    break;
                case 3:
                    opcionVerInformacionEstudiantes(kb, estudiantes);//Ver la información detallada de uno de los estudiantes registrados
                    break;
                case 0:
                    opcionSalir();
                    break;
                default:
                    opcionOpcionInvalida();
            }
        }
        kb.close();
    }

    private static void opcionRegistrarEstudiante(Scanner kb, Map<String, Estudiante> estudiantes) {
        System.out.println("===Registro Estudiante===");
        System.out.print("Ingrese el id del estudiante: ");
        String idEst = kb.nextLine();
        if (!estudiantes.containsKey(idEst)) {//Sé verífica que al registrar el ID no exista en otro estudiante
            System.out.print("Ingrese el nombre del estudiante: ");
            String nmEst = kb.nextLine();
            System.out.println("Estudiante registrado con exito!\n");

            Estudiante est = new Estudiante();//Se crea el estudiante
            est.registrarEstudiante(idEst, nmEst);//Se inserta su informacion
            estudiantes.put(est.getIdEstudiante(), est);//Se identifica y se guarda en el Map

            System.out.print("===Registro Notas===\n");
            //Componente
            System.out.print("Componente: ");
            String nmComp = scannerString(kb);
            System.out.print("Profesor del componente: ");
            String prfComp = scannerString(kb);
            Componente comp = new Componente();//Se crea el componente
            comp.registrarInfoComponente(nmComp, prfComp);//Se registra la informacion del componente

            //Notas
            String salida;//Opcion para dejar de guardar notas
            int i = 1;//Id para las notas
            do {
                do {
                    System.out.printf("Nota %s: ", i);
                    double nt = strToDouble(kb);//convierte String en double o un numero negativo en caso de error
                    if (nt > 0) {
                        System.out.printf("Porcentaje de la nota %s: ", i);
                        double prcNt = strToDouble(kb);
                        if (prcNt > 0) {
                            Nota nota = new Nota(nt, prcNt, comp);
                            est.agregarNotas(i, nota);
                            System.out.printf("Nota %s Registrada!\n", i);
                            i++;
                        } else {
                            System.out.println("Valor ingresado invalido");
                        }
                    } else {
                        System.out.println("Valor ingresado incorrecto");
                    }
                    System.out.println("Desea ingresar otra nota (S/N)");
                    salida = kb.nextLine();
                } while (salida.equalsIgnoreCase("s"));
                //Sé verífica que la suma de los porcentajes de las notas ingresadas no superen el 100%
                if (porcentajeMxCn(est) == false) {//Solo permite guardar cuando los porcentajes de las notas no superan el 100%
                    System.out.println("Valores de porcentajes por encima de 100%");
                    est.borrarNotas();//Si las notas superan el 100% se borran todas la notas ingresadas
                    i = 1;//Se reinicia el ID para volver a registrar las notas
                }
            } while (porcentajeMxCn(est) == false);//El registro de las notas solo permite salir cuando no se supera el 100%
        } else {
            System.out.println("\nEstudiante con ID ("+idEst+") ya registrado\n");
        }
    }

    private static void opcionListarEstudiantes(Map<String, Estudiante> estudiantes) {
        System.out.println("===Lista de Estudiantes===");
        if (estudiantes.isEmpty()) {//Sé verífica que exista registro del algún estudiante
            System.out.println("No hay estudiantes registrados\n");
        } else {
            System.out.println("ID  | NOMBRE   |");
            System.out.println("---------------------");
            for (Estudiante e : estudiantes.values()) {
                System.out.print(e.infoEstudiante());//Imprime todos los estudiantes registrados
                System.out.println("\n---------------------");
            }
        }
    }

    private static void opcionVerInformacionEstudiantes(Scanner kb, Map<String, Estudiante> estudiantes) {
        int opcion2 = -1;
        while (opcion2 != 0) {
            System.out.println("===Informacion Estudiantes===");
            System.out.println("1. Buscar estudiante por id");
            System.out.println("2. Buscar estudiante por nombre");
            System.out.println("0. Volver");
            opcion2 = strToInteger(kb);
            switch (opcion2) {
                case 1:
                    buscarPorId(kb, estudiantes);
                    break;
                case 2:
                    buscarPorNombre(kb, estudiantes);
                    break;
                case 0:
                    // volver
                    System.out.println("<--");//Opcion para volver al menu de inicio
                    break;
                default:
                    System.out.println("Opcion invalida. Intenta de nuevo");
            }
        }
    }

    private static void buscarPorId(Scanner kb, Map<String, Estudiante> estudiantes) {
        System.out.print("Ingrese el id del estudiante: ");
        String bsIdEst = kb.nextLine();
        if (estudiantes.containsKey(bsIdEst)) {//Se busca por ID al estudiante, si no encuentra devuelve false
            Estudiante bsEst = estudiantes.get(bsIdEst);//Sí lo encuentra sus atributos sé asociando a un objeto
            System.out.println(bsEst.toString());       //para luego imprimirlos
        } else {
            System.out.println("Id no asociado a un estudiante");
        }
    }

    private static void buscarPorNombre(Scanner kb, Map<String, Estudiante> estudiantes) {
        System.out.println("Ingrese el nombre del estudiante: ");
        String bsNmEst = kb.nextLine();
        for (Estudiante e : estudiantes.values())//Se recorre el map buscando un estudiante que coincida el nombre
            if (e.getNombreEstudiante().equalsIgnoreCase(bsNmEst)) { //ignorando mayúsculas y minuscules
                System.out.println(e); //Al encntrar una coincidencia imprime
            } else {
                System.out.println("No existe un estudiante con ese nombre");
            }
    }

    private static void opcionSalir() {
        System.out.println("====FIN DEL PROGRAMA====");
    }

    private static void opcionOpcionInvalida() {
        System.out.println("Opcion invalida. Intenta de nuevo");
    }

    //Lee un string y retorna un dooble - controla errores por lectura
    public static Double strToDouble(Scanner kb) {
        double d;
        try {
            d = Double.parseDouble(kb.nextLine());
        } catch (NumberFormatException e) {//Se controla posible error de Scanner
            d = -1.0;//Si detecta error devuelve un negativo
        }
        return d;
    }

    //Lee un string y devuelve un integer-controla errores por lectura
    public static Integer strToInteger(Scanner kb) {
        int i;
        try {
            i = Integer.parseInt(kb.nextLine());
        } catch (NumberFormatException e) {
            i = -1;
        }
        return i;
    }

    //Scaneo de strings
    public static String scannerString(Scanner kb) {
        String s;
        s = kb.nextLine();
        return s;
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
