
package gui;

import Componente.*;
import Usuarios.*;

import javax.swing.*;
import java.util.Map;

public class InterfazEstudiante {

    public static void mostrarMenuEstudiante(Map<String, Profesor> profesores) {
        var idEst = JOptionPane.showInputDialog(null, "Ingrese su id");

        for (Profesor p : profesores.values()) {
            for (Componente c : p.getComponentes()) {
                for (Estudiante e : c.getListEstudiantes().values()) {
                    if (e.getIdEstudiante().equalsIgnoreCase(idEst)) {
                        JOptionPane.showMessageDialog(null, p+c.toString()+e);

                    }
                }
            }
        }


    }
}