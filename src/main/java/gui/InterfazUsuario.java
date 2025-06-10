
package gui;

import Usuarios.Profesor;

import javax.swing.*;
import java.util.Map;

public class InterfazUsuario {

    public void mostrarMenuUsuario() {
        var opcion = -1;
        Map<String, Profesor> profesores = new java.util.HashMap<>();

        while (opcion != 0) {
            String op = JOptionPane.showInputDialog(null,
                    """
                            1. Profesor
                            
                            2. Estudiante
                            
                            0. Salir""", "Usuario", JOptionPane.PLAIN_MESSAGE);
            opcion = InterfazOpciones.strToInteger(op);
            switch (opcion) {
                case 1 -> InterfazProfesor.mostrarMenuProfesor(profesores);
                case 2 -> InterfazEstudiante.mostrarMenuEstudiante(profesores);
                case 0 -> {
                }
                default -> InterfazOpciones.opcionInvalida();
            }
        }
    }
}
