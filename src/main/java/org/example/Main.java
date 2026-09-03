package org.example;

import config.ConfigDB;
import dao.ColeccionesDAO;
import dao.LibroDAO;
import entity.Libro;
import exceptions.DBException;
import view.VentanaPrincipal;

import javax.swing.*;


public class Main {
    static void main() {
        //ToDo 2: Make it all one same language for better understanding of the code and to improve the GUI visually
        //ToDo 5: Improve the CRUD GUIs
        //ToDo 6: Do some bug fixing and bug searching
        try {
            VentanaPrincipal vp = new VentanaPrincipal();
        } catch (DBException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"BeakoBeta: Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
