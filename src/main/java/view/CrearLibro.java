package view;

import Utils.CustomButton;
import dao.ColeccionesDAO;
import dao.LibroDAO;
import entity.Colecciones;
import entity.Libro;
import exceptions.DBException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class which object is a GUI that allows the user to create a book and add it to a collection
 */
public class CrearLibro extends JDialog {
    //Extras
    private VentanaPrincipal origen;

    //Constants
    private final Font FONT_STATS_NAME = new Font("SansSerif",Font.PLAIN,14);
    private final Font FONT_STATS_VALUE = new Font("SansSerif",Font.BOLD,22);
    private final Font FONT_TITLE = new  Font("SansSerif",Font.BOLD,24);

    private final Color COLOR_STATS_NAME = new Color(90, 90, 100);
    private final Color COLOR_STATS_VALUE = new Color(30, 30, 40);
    private final Color COLOR_COLLECTION_TITLE = new Color(200, 30, 100);

    private final Color STAT_CARDS_BG = new Color(253, 235, 240);
    private final Color STAT_CARDS_BORDER = new Color(240, 200, 215);

    private final Color DATA_BG = new Color(255, 255, 255);
    private final Color DATA_BORDER = new Color(139, 126, 174, 174);

    private final Color MAIN_BG = new Color(248, 238, 240);

    //JButtons
    private CustomButton btnCreate = new CustomButton("Create Book");
    private CustomButton btnCancel = new CustomButton("Cancel");

    //Jlabels
    private JLabel lbTitulo = new JLabel("Create Book",JLabel.CENTER);
    private JLabel lbNumVolumen = new JLabel("Volume Number: ",JLabel.CENTER);
    private JLabel lbEditorial = new JLabel("Editorial: ",JLabel.CENTER);
    private JLabel lbLenguaje = new JLabel("Language: ",JLabel.CENTER);
    private JLabel lbEstadoLibro = new JLabel("Book State: ",JLabel.CENTER);
    private JLabel lbColeccion = new JLabel("Collection: ",JLabel.CENTER);

    //JTextFields
    private JTextField tfNumVolumen = new JTextField(20);
    private JTextField tfEditorial = new JTextField(20);
    private JTextField tfLenguaje = new JTextField(20);

    //JComboBox
    private JComboBox<String> cbEstadoLibro = new JComboBox<String>(new String[]{"stopped", "finished", "onreading"});
    JComboBox<Colecciones> cbColeccion = new JComboBox<Colecciones>(ColeccionesDAO.listarColecciones().toArray(new Colecciones[ColeccionesDAO.listarColecciones().size()]));

    //JPanel
    private JPanel pTitulo = new JPanel(new GridLayout(1,1,10,10));
    private JPanel pNumeroVolumen = new JPanel(new GridBagLayout());
    private JPanel pEditorial = new JPanel(new GridBagLayout());
    private JPanel pLenguaje= new JPanel(new GridBagLayout());
    private JPanel pEstadoLibro = new JPanel(new GridBagLayout());
    private JPanel pColeccion = new JPanel(new GridBagLayout());
    private JPanel pBotones = new JPanel(new GridLayout(1,2,10,10));

    //GridBagConstraints
    private GridBagConstraints gbcData = new GridBagConstraints();


    /**
     * Constructor of the CrearLibro class
     * @param origen - Main GUI of the app
     * @throws DBException - Exception related to the DataBase
     */
    public CrearLibro(VentanaPrincipal origen) throws DBException {
        this.origen = origen;
        innit();
    }

    /**
     * Method that initializes the creation GUI
     */
    private void innit() throws DBException{
        this.setTitle("BeakoBeta: Crear Libro");
        this.setSize(350,740);
        this.setPreferredSize(new Dimension(350,740));
        this.setMaximumSize(new Dimension(350,740));
        this.getContentPane().setBackground(MAIN_BG);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setLocationRelativeTo(null);

        GridBagConstraints gbcBase = new GridBagConstraints();
        gbcBase.insets = new Insets(7,10,7,10);
        gbcBase.gridx = 0;
        gbcBase.gridy = 0;
        gbcBase.weightx = 0.0;
        gbcBase.weighty = 0.0;
        gbcBase.anchor = GridBagConstraints.NORTH;
        gbcBase.fill = GridBagConstraints.HORIZONTAL;

        //Data GridBagConstraints
        gbcData.insets = new Insets(5,12,5,12);
        gbcData.gridx = 0;
        gbcData.gridy = 0;
        gbcData.weightx = 0.0;
        gbcData.weighty = 0.0;
        gbcData.anchor = GridBagConstraints.NORTHWEST;


        //Titulo
        lbTitulo.setForeground(COLOR_COLLECTION_TITLE);
        lbTitulo.setFont(FONT_TITLE);
        this.pTitulo.add(lbTitulo);
        this.pTitulo.setBackground(MAIN_BG);
        this.add(pTitulo,gbcBase);

        //Numero Volumenes
        gbcBase.gridy = 1;
        gbcBase.weightx = 1.0;
        gbcBase.weighty = 1.0;
        gbcData.weightx = 1.0;
        gbcData.weighty = 1.0;
        this.pNumeroVolumen.add(lbNumVolumen,gbcData);
        gbcData.gridy = 1;
        gbcData.fill = GridBagConstraints.HORIZONTAL;
        tfNumVolumen.setFont(new Font("SansSerif",Font.PLAIN,18));
        tfNumVolumen.setBorder(new LineBorder(new Color(204, 159, 173),2));
        this.pNumeroVolumen.add(tfNumVolumen,gbcData);
        this.pNumeroVolumen.setBackground(MAIN_BG);
        this.add(pNumeroVolumen,gbcBase);

        //Editorial
        gbcBase.gridy = 2;
        gbcData.gridy = 0;
        gbcData.fill = GridBagConstraints.CENTER;
        this.pEditorial.add(lbEditorial,gbcData);
        gbcData.gridy = 1;
        gbcData.fill = GridBagConstraints.HORIZONTAL;
        tfEditorial.setFont(new Font("SansSerif",Font.PLAIN,18));
        tfEditorial.setBorder(new LineBorder(new Color(204, 159, 173),2));
        this.pEditorial.add(tfEditorial,gbcData);
        this.pEditorial.setBackground(MAIN_BG);
        this.add(pEditorial,gbcBase);

        //Lenguaje
        gbcBase.gridy = 3;
        gbcData.gridy = 0;
        gbcData.fill = GridBagConstraints.CENTER;
        this.pLenguaje.add(lbLenguaje,gbcData);
        gbcData.gridy = 1;
        gbcData.fill = GridBagConstraints.HORIZONTAL;
        tfLenguaje.setFont(new Font("SansSerif",Font.PLAIN,18));
        tfLenguaje.setBorder(new LineBorder(new Color(204, 159, 173),2));
        this.pLenguaje.add(tfLenguaje,gbcData);
        this.pLenguaje.setBackground(MAIN_BG);
        this.add(pLenguaje,gbcBase);

        //Estado Libro
        gbcBase.gridy = 4;
        gbcData.gridy = 0;
        gbcData.fill = GridBagConstraints.CENTER;
        this.pEstadoLibro.add(lbEstadoLibro,gbcData);
        gbcData.gridy = 1;
        gbcData.fill = GridBagConstraints.HORIZONTAL;
        cbEstadoLibro.setFont(new Font("SansSerif",Font.PLAIN,18));
        cbEstadoLibro.setBorder(new LineBorder(new Color(204, 159, 173),2));
        this.pEstadoLibro.add(cbEstadoLibro,gbcData);
        this.pEstadoLibro.setBackground(MAIN_BG);
        this.add(pEstadoLibro,gbcBase);

        //Coleccion
        gbcBase.gridy = 5;
        gbcData.gridy = 0;
        gbcData.fill = GridBagConstraints.CENTER;
        this.pColeccion.add(lbColeccion,gbcData);
        gbcData.gridy = 1;
        gbcData.fill = GridBagConstraints.HORIZONTAL;
        cbColeccion.setFont(new Font("SansSerif",Font.PLAIN,18));
        cbColeccion.setBorder(new LineBorder(new Color(204, 159, 173),2));
        this.pColeccion.add(cbColeccion,gbcData);
        this.pColeccion.setBackground(MAIN_BG);
        this.add(pColeccion,gbcBase);

        //Botones
        gbcBase.gridy = 6;
        btnCreate.setHorizontalAlignment(SwingConstants.CENTER);
        btnCreate.setBackground(COLOR_COLLECTION_TITLE);
        btnCreate.setBorder(new LineBorder(new Color(168, 19, 81),4));
        btnCreate.setForeground(Color.white);

        btnCancel.setHorizontalAlignment(SwingConstants.CENTER);
        btnCancel.setBackground(STAT_CARDS_BORDER);
        btnCancel.setBorder(new LineBorder(new Color(204, 159, 173),4));

        this.pBotones.add(btnCreate);
        this.pBotones.add(btnCancel);
        this.add(pBotones,gbcBase);

        this.setSize(521,556);
        this.setResizable(false);
        this.setVisible(true);

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Colecciones colection = (Colecciones) cbColeccion.getSelectedItem();
                    Libro l = new Libro(Integer.parseInt(tfNumVolumen.getText()),tfEditorial.getText(),tfLenguaje.getText(),cbEstadoLibro.getSelectedItem().toString(),colection.getIdCollection());
                    LibroDAO.insertarLibro(l);
                    colection.setTotalPoseidos(colection.getTotalPoseidos()+1);
                    ColeccionesDAO.editarColeccion(colection.getNombre(),colection.getAutor(),colection.getTotalVolumenes(),colection.getTotalPoseidos(),colection.getEstadoColeccion(),colection.getEstadoublicacion(),colection.getIdCollection());
                    dispose();
                } catch (DBException e) {
                    JOptionPane.showMessageDialog(CrearLibro.this,"Message: " + e.getMessage(),"BeakoBeta: Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CrearLibro.this.dispose();
            }
        });
    }





}
