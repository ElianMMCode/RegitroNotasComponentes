import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int opcion = -1;
        Map<String, Estudiante> estudiantes = new HashMap<>();

        while (opcion != 0) {
            System.out.println("===Registro Notas Estudiantes====");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Ver información de estudiante");
            System.out.println("0. Salir");
            opcion = strToInteger(kb);

            switch (opcion) {
                case 1:
                    opcionRegistrarEstudiante(kb, estudiantes);
                    break;
                case 2:
                    opcionListarEstudiantes(estudiantes);
                    break;
                case 3:
                    opcionVerInformacionEstudiantes(kb, estudiantes);
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
        System.out.print("Ingrese el nombre del estudiante: ");
        String nmEst = kb.nextLine();
        System.out.println("\nEstudiante registrado con exito!\n");

        Estudiante est = new Estudiante();
        est.registrarEstudiante(idEst, nmEst);
        estudiantes.put(est.getIdEstudiante(), est);

        System.out.println("===Registro Notas===");

        //Componente
        System.out.print("Componente: ");
        String nmComp = scannerString(kb);
        System.out.print("Profesor del componente: ");
        String prfComp = scannerString(kb);
        Componente comp = new Componente();
        comp.registrarInfoComponente(nmComp, prfComp);

        //Notas
        String salida;
        int i = 1;

        do {
            do {
                System.out.printf("Nota %s:%n", i);
                double nt = strToDouble(kb);
                System.out.printf("Porcentaje de la nota %s%n", i);
                double prcNt = strToDouble(kb);

                Nota nota = new Nota(nt, prcNt, comp);
                est.agregarNotas(i, nota);

                System.out.printf("Nota %s Registrada!%n", i);
                System.out.println("Desea ingresar otra nota (S/N)");
                salida = kb.nextLine();
                i++;
            } while (salida.equalsIgnoreCase("s"));

            if (porcentajeMxCn(est) == false) {
                System.out.println("Valores de porcentajes por encima de 100%");
                est.borrarNotas();
                i = 1;
            }
        } while (porcentajeMxCn(est) == false);

        System.out.println("¡El estudiante y sus notas fueron registradas exitosamente!");
    }

    private static void opcionListarEstudiantes(Map<String, Estudiante> estudiantes) {
        System.out.println("===Lista de Estudiantes===");
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados\n");
        } else {
            for (Estudiante e : estudiantes.values()) {
                System.out.print(e.toString());
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
                    System.out.println("<--");
                    break;
                default:
                    System.out.println("Opcion invalida. Intenta de nuevo");
            }
        }
    }

    private static void buscarPorId(Scanner kb, Map<String, Estudiante> estudiantes) {
        System.out.print("Ingrese el id del estudiante: ");
        String bsIdEst = kb.nextLine();
        Estudiante bsEst = estudiantes.get(bsIdEst);
        if (bsEst != null) {
            System.out.println("===Estudiante===");
            System.out.println(bsEst);
            System.out.println("===Notas Estudiante===");
            System.out.println(bsEst.getNotas());
            System.out.println("===Nota final===");
            Map<Integer, Nota> nts = bsEst.getNotas();
            nts.get(1);
            System.out.println(nts.get(1));
        } else {
            System.out.println("Id no asociado a un estudiante");
        }
    }

    private static void buscarPorNombre(Scanner kb, Map<String, Estudiante> estudiantes) {
        System.out.println("Ingrese el nombre del estudiante: ");
        String bsNmEst = kb.nextLine();
        for (Estudiante e : estudiantes.values())
            if (e.getNombreEstudiante().equalsIgnoreCase(bsNmEst)) {
                System.out.println(e);
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

    public static Double strToDouble(Scanner kb) {
        double d;
        try {
            d = Double.parseDouble(kb.nextLine());
        } catch (NumberFormatException e) {
            d = -1.0;
        }
        return d;
    }

    public static Integer strToInteger(Scanner kb) {
        int i;
        try {
            i = Integer.parseInt(kb.nextLine());
        } catch (NumberFormatException e) {
            i = -1;
        }
        return i;
    }

    public static String scannerString(Scanner kb) {
        String s;
        s = kb.nextLine();
        return s;
    }

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
