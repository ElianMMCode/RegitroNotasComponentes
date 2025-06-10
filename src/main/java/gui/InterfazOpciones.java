
package gui;

import Componente.*;
import Usuarios.*;

import javax.swing.*;
import java.util.Map;

public class InterfazOpciones {

    public static void opcionRegistrarProfesor(Map<String, Profesor> profesores) {
        String idPrf = JOptionPane.showInputDialog(null,
                "Ingrese su Id: ","Registro Profesor", JOptionPane.PLAIN_MESSAGE);
        if (!profesores.containsKey(idPrf)) {
            String nmPrf = JOptionPane.showInputDialog("Ingrese su nombre: ");
            String pssPrf = JOptionPane.showInputDialog("Ingrese su password: ");
            Profesor prf = new Profesor(idPrf, nmPrf, pssPrf);
            profesores.put(prf.getId(), prf);
        } else {
            JOptionPane.showMessageDialog(null, "Profesor con ID (" + idPrf + ") ya registrado");
        }
    }

    public static void opcionRegistroComponente(Profesor p) {
        String nmComp = JOptionPane.showInputDialog(null,
                "Ingrese el nombre del componente: ","Registro Componente", JOptionPane.PLAIN_MESSAGE);
        boolean existe = false;
        for (Componente c : p.getComponentes()) {
            if (c.getNombreComponente().equalsIgnoreCase(nmComp)) {
                existe = true;
                break;
            }
        }

        if (!existe) {
            Componente nuevo = new Componente();
            nuevo.registrarInfoComponente(nmComp);
            p.setComponentes(nuevo);
            JOptionPane.showMessageDialog(null, "Componente guardado");
        } else {
            JOptionPane.showMessageDialog(null, "El componente ya está registrado");
        }
    }

    public static void opcionListarComponentes(Profesor p) {
        StringBuilder sb = new StringBuilder();
        for (Componente c : p.getComponentes()) {
            sb.append(c).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista Componentes", JOptionPane.PLAIN_MESSAGE);
    }

    public static void opcionBuscarComponente(Profesor p) {
        String nmComp = JOptionPane.showInputDialog(null,
                "Ingrese el nombre del componente:", "Búsqueda Componente", JOptionPane.PLAIN_MESSAGE);
        boolean encontrado = false;
        for (Componente c : p.getComponentes()) {
            if (c.getNombreComponente().equalsIgnoreCase(nmComp)) {
                StringBuilder sb = new StringBuilder();
                for (Estudiante e : c.getListEstudiantes().values()){
                    sb.append(e);
                }
                String textoJustificado = sb.toString();
                JOptionPane.showMessageDialog(null, c+textoJustificado, "Componente", JOptionPane.PLAIN_MESSAGE);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "Componente no encontrado");
        }
    }

    public static void opcionRegistrarEstComp(Profesor p) {
        String comp = JOptionPane.showInputDialog(null,
                "Ingrese el nombre del componente", "Componente Registro Estudiantes", JOptionPane.PLAIN_MESSAGE);
        boolean encontrado = false;
        for (Componente c : p.getComponentes()) {
            if (c.getNombreComponente().equalsIgnoreCase(comp)) {
                InterfazProfesor.mostrarMenuEstudiantes(c.getListEstudiantes());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "Componente no encontrado");
        }
    }

    public static void opcionRegistrarEstudiante(Map<String, Estudiante> estudiantes) {
        String idEst = JOptionPane.showInputDialog(null,
                "Ingrese el id del estudiante:", "Registro Estudiantes", JOptionPane.PLAIN_MESSAGE);
        if (!estudiantes.containsKey(idEst)) {
            String nmEst = JOptionPane.showInputDialog("Ingrese el nombre del estudiante:");
            JOptionPane.showMessageDialog(null, "Estudiante registrado con éxito!");

            Estudiante est = new Estudiante();
            est.registrarEstudiante(idEst, nmEst);
            estudiantes.put(est.getIdEstudiante(), est);

            int salida;
            int i = 1;
            do {
                do {
                    double nt = strToDouble(JOptionPane.showInputDialog("Nota " + i));
                    if (nt > 0) {
                        double prcNt = strToDouble(JOptionPane.showInputDialog("Porcentaje de la nota " + i));
                        if (prcNt > 0) {
                            est.agregarNotas(i, new Nota(nt, prcNt));
                            JOptionPane.showMessageDialog(null, "Nota Registrada!");
                            i++;
                        } else {
                            JOptionPane.showMessageDialog(null, "Porcentaje inválido");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nota inválida");
                    }
                    salida = JOptionPane.showConfirmDialog(null, "¿Desea ingresar otra nota?");
                } while (salida == 0);

                if (porcentajeValido(est)) {
                    JOptionPane.showMessageDialog(null, "Porcentajes exceden 100%. Se borrarán las notas.");
                    est.borrarNotas();
                    i = 1;
                }
            } while (porcentajeValido(est));
        } else {
            JOptionPane.showMessageDialog(null, "Estudiante con ID (" + idEst + ") ya registrado");
        }
    }

    public static void opcionListarEstudiantes(Map<String, Estudiante> estudiantes) {
        if (estudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay estudiantes registrados");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Estudiante e : estudiantes.values()) {
            sb.append(e.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Lista Estudiantes", JOptionPane.PLAIN_MESSAGE);
    }

    public static void opcionVerInformacionEstudiantes(Map<String, Estudiante> estudiantes) {
        int opcion = -2;
        while (opcion != 0 && opcion != -1) {
            opcion = strToInteger(JOptionPane.showInputDialog(null, """
                            1. Buscar estudiante por ID
                            2. Buscar estudiante por nombre
                            0. Volver"""));
            switch (opcion) {
                case 1 -> buscarPorId(estudiantes);
                case 2 -> buscarPorNombre(estudiantes);
                case 0, -1 -> {}
                default -> opcionInvalida();
            }
        }
    }

    private static void buscarPorId(Map<String, Estudiante> estudiantes) {
        String bsIdEst = JOptionPane.showInputDialog("Ingrese el ID del estudiante:");
        Estudiante bsEst = estudiantes.get(bsIdEst);
        if (bsEst != null) {
            JOptionPane.showMessageDialog(null, bsEst.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Estudiante no encontrado");
        }
    }

    private static void buscarPorNombre(Map<String, Estudiante> estudiantes) {
        String bsNmEst = JOptionPane.showInputDialog("Ingrese el nombre del estudiante:");
        boolean encontrado = false;
        for (Estudiante e : estudiantes.values()) {
            if (e.getNombreEstudiante().equalsIgnoreCase(bsNmEst)) {
                JOptionPane.showMessageDialog(null, e.toString());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "No se encontró estudiante con ese nombre");
        }
    }

    public static void opcionInvalida() {
        JOptionPane.showMessageDialog(null, "Opción inválida", "Error", JOptionPane.PLAIN_MESSAGE);
    }

    public static double strToDouble(String string) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return -1.0;
        }
    }

    public static int strToInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static boolean porcentajeValido(Estudiante e) {
        Map<Integer, Nota> notas = e.getNotas();
        double sumPrct = 0.0;
        boolean limPrct = false;
        for (Nota n : notas.values()) {
            sumPrct += n.getPorcentaje();
        }
        if (sumPrct <= 100) {
            limPrct = true;
        }
        return !limPrct;
    }
}
