package org.example;

import dao.ColeccionesDAO;
import dao.LibroDAO;
import entity.Libro;
import exceptions.DBException;
import view.VentanaPrincipal;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        //ToDo: Add The JavaDocs to all the classes
        //ToDo 2: Make it allone same language for better understanding of the code and to improve the GUI visually
        //Todo 3: Fix editarColeccion, it can desync the count of the owned books
        //ToDo 4: Add remove from owned for when deleting a book from the collection
        //ToDo 5: Improve the CRUD GUIs
        //ToDo 6: Fix the problem that allows deleteBook to remove everyBook with the same Volume Number
        //ToDo 7: Add the collection Id and the book id and fix all shenanigans that adding these will cause
        try {
            VentanaPrincipal vp = new VentanaPrincipal();
        } catch (DBException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"BeakoBeta: Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
