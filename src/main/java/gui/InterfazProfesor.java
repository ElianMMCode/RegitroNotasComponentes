
package gui;

import Usuarios.Estudiante;
import Usuarios.Profesor;

import javax.swing.*;
import java.util.Map;

public class InterfazProfesor {

    public static void mostrarMenuProfesor(Map<String, Profesor> profesores) {
        int opcion = -2;
        while (opcion != 0 && opcion != -1) {
            var op = JOptionPane.showInputDialog(null,
                    """
                            1. Registro Profesor
                            
                            2. Iniciar Sesión
                            
                            0. Volver""", "Usuario", JOptionPane.PLAIN_MESSAGE);
            opcion = InterfazOpciones.strToInteger(op);
            switch (opcion) {
                case 1 -> InterfazOpciones.opcionRegistrarProfesor(profesores);
                case 2 -> validarProfesor(profesores);
                case 0,-1 -> {}
                default -> InterfazOpciones.opcionInvalida();
            }
        }
    }

    private static void validarProfesor(Map<String, Profesor> profesores) {
        var idPrf = JOptionPane.showInputDialog(null, "Ingrese su  Id: ");
        if (profesores.containsKey(idPrf)) {
            String pssPrf = JOptionPane.showInputDialog(null, "Ingrese su password: ");
            Profesor p = profesores.get(idPrf);
            if (p.password().equals(pssPrf)) {
                mostrarMenuComponentes(p);
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
            }
        } else {
            JOptionPane.showMessageDialog(null, "ID no asociado a un profesor");
        }
    }

    public static void mostrarMenuEstudiantes(Map<String, Estudiante> estudiantes) {
        int opcion = -2;
        while (opcion != 0 && opcion != -1) {
            String op = JOptionPane.showInputDialog(null,
                    """
                            1. Registrar estudiante
                            
                            2. Listar estudiantes
                            
                            3. Ver información de estudiante
                            
                            0. Volver""", "Estudiantes", JOptionPane.PLAIN_MESSAGE);
            opcion = InterfazOpciones.strToInteger(op);
            switch (opcion) {
                case 1 -> InterfazOpciones.opcionRegistrarEstudiante(estudiantes);
                case 2 -> InterfazOpciones.opcionListarEstudiantes(estudiantes);
                case 3 -> InterfazOpciones.opcionVerInformacionEstudiantes(estudiantes);
                case 0, -1 -> {}
                default -> InterfazOpciones.opcionInvalida();
            }
        }
    }

    private static void mostrarMenuComponentes(Profesor p) {
        int opcion = -2;
        while (opcion != 0 &&  opcion != -1) {
            String op = JOptionPane.showInputDialog(null,
                    """
                            1. Registro Componente
                            
                            2. Listar componentes
                            
                            3. Buscar Componente
                            
                            4. Registrar Estudiantes a Componente
                            
                            0. Volver""", "Profesor", JOptionPane.PLAIN_MESSAGE);
            opcion = InterfazOpciones.strToInteger(op);
            switch (opcion) {
                case 1 -> InterfazOpciones.opcionRegistroComponente(p);
                case 2 -> InterfazOpciones.opcionListarComponentes(p);
                case 3 -> InterfazOpciones.opcionBuscarComponente(p);
                case 4 -> InterfazOpciones.opcionRegistrarEstComp(p);
                case 0 , -1 -> {}
                default -> InterfazOpciones.opcionInvalida();
            }
        }
    }
}
