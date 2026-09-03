package view;

import Utils.CustomButton;
import dao.ColeccionesDAO;
import dao.LibroDAO;
import entity.Colecciones;
import entity.Libro;
import exceptions.DBException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class which object is a GUI that allows the user to create collections
 */
public class CrearColeccion extends JDialog {
    //Extras
    private VentanaPrincipal origen;

    //Constants
    private final Font FONT_STATS_NAME = new Font("SansSerif",Font.PLAIN,14);
    private final Font FONT_STATS_VALUE = new Font("SansSerif",Font.BOLD,22);
    private final Font FONT_TITLE = new  Font("Georgia",Font.BOLD,24);

    private final Color COLOR_STATS_NAME = new Color(90, 90, 100);
    private final Color COLOR_STATS_VALUE = new Color(30, 30, 40);
    private final Color COLOR_COLLECTION_TITLE = new Color(200, 30, 100);

    private final Color STAT_CARDS_BG = new Color(253, 235, 240);
    private final Color STAT_CARDS_BORDER = new Color(240, 200, 215);

    private final Color DATA_BG = new Color(255, 255, 255);
    private final Color DATA_BORDER = new Color(139, 126, 174, 174);

    private final Color MAIN_BG = new Color(248, 238, 240);

    //JButtons
    private CustomButton btnCreate = new CustomButton("Create Collection");
    private CustomButton btnCancel = new CustomButton("Cancel");

    //Jlabels
    private JLabel lbTituloPestaña = new JLabel("Create Collection");
    private JLabel lbTitulo = new JLabel("TITLE");
    private JLabel lbAutor = new JLabel("AUTHOR");
    private JLabel lbTotalVolumen = new JLabel("MAX VOLUMES");
    private JLabel lbEstadoCol = new JLabel("COLLECTION STATUS");
    private JLabel lbEstadoPubli = new JLabel("PUBLISHING STATUS");

    //JTextFields
    private JTextField tfTitulo = new JTextField(20);
    private JTextField tfAutor = new JTextField(20);
    private JTextField tfTotalVolumen = new JTextField(20);

    //JComboBox
    private JComboBox<String> cbEstadoCol = new JComboBox<String>(new String[]{"stopped", "finished", "onreading"});
    private JComboBox<String> cbEstadoPubli = new JComboBox<String>(new String[]{"cancelado", "terminado", "hiatus", "ongoing"});

    //JPanel
    private JPanel pTituloPestaña = new JPanel(new GridLayout(1,1,10,10));
    private JPanel pTitulo = new JPanel(new GridBagLayout());
    private JPanel pAutor = new JPanel(new GridBagLayout());
    private JPanel pTotalVolumen= new JPanel(new GridBagLayout());
    private JPanel pEstadoCol = new JPanel(new GridBagLayout());
    private JPanel pEstadoPubli = new JPanel(new GridBagLayout());
    private JPanel pBotones = new JPanel(new GridLayout(1,2,10,10));

    //GridBagConstraints Data
    GridBagConstraints gbcData = new GridBagConstraints();


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
        this.setSize(350,740);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(MAIN_BG);
        this.setLocationRelativeTo(null);

        GridBagConstraints gbcBase = new GridBagConstraints();
        gbcBase.insets = new Insets(7,10,7,10);
        gbcBase.gridx = 0;
        gbcBase.gridy = 0;
        gbcBase.weightx = 0.0;
        gbcBase.weightx = 0.0;
        gbcBase.fill = GridBagConstraints.HORIZONTAL;
        gbcBase.anchor = GridBagConstraints.NORTHWEST;

        //GridBagConstraints Data
        gbcData.insets = new Insets(5,12,5,12);
        gbcData.gridx = 0;
        gbcData.gridy = 0;
        gbcData.weightx = 0.0;
        gbcData.weighty = 0.0;
        gbcData.fill = GridBagConstraints.HORIZONTAL;
        gbcData.anchor = GridBagConstraints.NORTHWEST;

        //Titulo
        lbTituloPestaña.setFont(FONT_TITLE);
        lbTituloPestaña.setForeground(COLOR_COLLECTION_TITLE);
        this.pTituloPestaña.add(lbTituloPestaña);
        pTituloPestaña.setBackground(MAIN_BG);
        this.add(pTituloPestaña,gbcBase);

        //Numero Volumenes
        gbcBase.gridy = 1;
        gbcBase.weightx = 1.0;
        gbcBase.weighty = 1.0;
        gbcData.weightx = 1.0;
        gbcData.weighty = 1.0;
        this.pTitulo.add(lbTitulo,gbcData);
        gbcData.gridy = 1;
        tfTitulo.setBorder(new EmptyBorder(1,1,1,1));
        tfTitulo.setFont(new Font("SansSerif",Font.PLAIN,15));
        tfTitulo.setBackground(MAIN_BG);
        tfTitulo.setForeground(Color.GRAY);
        this.pTitulo.add(tfTitulo,gbcData);
        pTitulo.setBackground(MAIN_BG);
        this.add(pTitulo,gbcBase);

        //Editorial
        gbcBase.gridy = 2;
        gbcData.gridy = 0;
        this.pAutor.add(lbAutor,gbcData);
        gbcData.gridy = 1;
        tfAutor.setBackground(MAIN_BG);
        tfAutor.setForeground(Color.GRAY);
        tfAutor.setFont(new Font("SansSerif",Font.PLAIN,15));
        tfAutor.setBorder(new EmptyBorder(1,1,1,1));
        this.pAutor.add(tfAutor,gbcData);
        pAutor.setBackground(MAIN_BG);
        this.add(pAutor,gbcBase);

        //Lenguaje
        gbcBase.gridy = 3;
        gbcData.gridy = 0;
        this.pTotalVolumen.add(lbTotalVolumen,gbcData);
        gbcData.gridy = 1;
        tfTotalVolumen.setBackground(MAIN_BG);
        tfTotalVolumen.setForeground(Color.GRAY);
        tfTotalVolumen.setFont(new Font("SansSerif",Font.PLAIN,15));
        tfTotalVolumen.setBorder(new EmptyBorder(1,1,1,1));
        this.pTotalVolumen.add(tfTotalVolumen,gbcData);
        pTotalVolumen.setBackground(MAIN_BG);
        this.add(pTotalVolumen,gbcBase);

        //Estado Libro
        gbcBase.gridy = 4;
        gbcData.gridy = 0;
        this.pEstadoCol.add(lbEstadoCol,gbcData);
        gbcData.gridy = 1;
        cbEstadoCol.setBackground(MAIN_BG);
        cbEstadoCol.setForeground(Color.GRAY);
        cbEstadoCol.setFont(new Font("SansSerif",Font.PLAIN,15));
        cbEstadoCol.setBorder(new EmptyBorder(1,1,1,1));
        this.pEstadoCol.add(cbEstadoCol,gbcData);
        pEstadoCol.setBackground(MAIN_BG);
        this.add(pEstadoCol,gbcBase);

        //Coleccion
        gbcBase.gridy = 5;
        gbcData.gridy = 0;
        this.pEstadoPubli.add(lbEstadoPubli,gbcData);
        gbcData.gridy = 1;
        cbEstadoPubli.setBackground(MAIN_BG);
        cbEstadoPubli.setForeground(Color.GRAY);
        cbEstadoPubli.setFont(new Font("SansSerif",Font.PLAIN,15));
        cbEstadoPubli.setBorder(new EmptyBorder(1,1,1,1));
        this.pEstadoPubli.add(cbEstadoPubli,gbcData);
        pEstadoPubli.setBackground(MAIN_BG);
        this.add(pEstadoPubli,gbcBase);

        //Botones
        gbcBase.gridy = 6;
        gbcData.gridy = 0;
        btnCreate.setHorizontalAlignment(SwingConstants.CENTER);
        btnCreate.setBackground(COLOR_COLLECTION_TITLE);
        btnCreate.setForeground(Color.WHITE);
        btnCreate.setBorder(new LineBorder(new Color(168, 19, 81),4));
        this.pBotones.add(btnCreate,gbcData);

        gbcData.gridy = 1;
        btnCancel.setHorizontalAlignment(SwingConstants.CENTER);
        btnCancel.setBorder(new LineBorder(new Color(204, 159, 173),4));
        this.pBotones.add(btnCancel,gbcData);
        this.add(pBotones,gbcBase);

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
