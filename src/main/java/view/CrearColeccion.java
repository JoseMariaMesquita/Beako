package view;

import dao.ColeccionesDAO;
import dao.LibroDAO;
import entity.Colecciones;
import entity.Libro;
import exceptions.DBException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class which object is a GUI that allows the user to create collections
 */
public class CrearColeccion extends JDialog {
    //Extras
    private VentanaPrincipal origen;

    //JButtons
    private JButton btnCreate = new JButton("Create Collection");
    private JButton btnCancel = new JButton("Cancel");

    //Jlabels
    private JLabel lbTituloPestaña = new JLabel("Create Colection");
    private JLabel lbTitulo = new JLabel("Tittle: ",JLabel.CENTER);
    private JLabel lbAutor = new JLabel("Author: ",JLabel.CENTER);
    private JLabel lbTotalVolumen = new JLabel("Max Volumes: ",JLabel.CENTER);
    private JLabel lbEstadoCol = new JLabel("Collection status: ",JLabel.CENTER);
    private JLabel lbEstadoPubli = new JLabel("Publishing status: ",JLabel.CENTER);

    //JTextFields
    private JTextField tfTitulo = new JTextField(20);
    private JTextField tfAutor = new JTextField(20);
    private JTextField tfTotalVolumen = new JTextField(20);

    //JComboBox
    private JComboBox<String> cbEstadoCol = new JComboBox<String>(new String[]{"stopped", "finished", "onreading"});
    private JComboBox<String> cbEstadoPubli = new JComboBox<String>(new String[]{"cancelado", "terminado", "hiatus", "ongoing"});

    //JPanel
    private JPanel pTituloPestaña = new JPanel(new GridLayout(1,1,10,10));
    private JPanel pTitulo = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pAutor = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pTotalVolumen= new JPanel(new GridLayout(1,2,10,10));
    private JPanel pEstadoCol = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pEstadoPubli = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pBotones = new JPanel(new GridLayout(1,2,10,10));

    /**
     * Constructor of the class
     * @param origen - Main GUI of the app
     * @throws DBException - Exception related to the DataBase
     */
    public CrearColeccion(VentanaPrincipal origen) throws DBException {
        super(origen,true);
        this.origen = origen;
        innit();
    }

    /**
     * Method that initializes the Creation GUI
     */
    private void innit(){
        this.setTitle("BeakoBeta: Crear Libro");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(7,1,5,20));
        this.setLocationRelativeTo(null);

        //Titulo
        lbTituloPestaña.setFont(new Font("arial",Font.BOLD,20));
        this.pTituloPestaña.add(lbTituloPestaña);
        this.add(pTituloPestaña);

        //Numero Volumenes
        this.pTitulo.add(lbTitulo);
        this.pTitulo.add(tfTitulo);
        this.add(pTitulo);

        //Editorial
        this.pAutor.add(lbAutor);
        this.pAutor.add(tfAutor);
        this.add(pAutor);

        //Lenguaje
        this.pTotalVolumen.add(lbTotalVolumen);
        this.pTotalVolumen.add(tfTotalVolumen);
        this.add(pTotalVolumen);

        //Estado Libro
        this.pEstadoCol.add(lbEstadoCol);
        this.pEstadoCol.add(cbEstadoCol);
        this.add(pEstadoCol);

        //Coleccion
        this.pEstadoPubli.add(lbEstadoPubli);
        this.pEstadoPubli.add(cbEstadoPubli);
        this.add(pEstadoPubli);

        //Botones
        this.pBotones.add(btnCreate);
        this.pBotones.add(btnCancel);
        this.add(pBotones);

        this.setSize(521,556);
        this.setResizable(false);

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    ColeccionesDAO.insertarColeccion(new Colecciones(tfTitulo.getText(),tfAutor.getText(),Integer.parseInt(tfTotalVolumen.getText()),0,cbEstadoCol.getSelectedItem().toString(),cbEstadoPubli.getSelectedItem().toString()));
                    dispose();
                } catch (DBException e) {
                    JOptionPane.showMessageDialog(CrearColeccion.this,"Message: " + e.getMessage(),"BeakoBeta: Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CrearColeccion.this.dispose();
            }
        });


        this.setVisible(true);



    }
}
