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
 * Class which object is a GUI that allows the user to modify the data of a book
 */
public class EditarLibro extends JDialog {
    //Extras
    private VentanaPrincipal origen;

    //Constants
    private final Font FONT_STATS_NAME = new Font("SansSerif",Font.PLAIN,14);
    private final Font FONT_STATS_VALUE = new Font("SansSerif",Font.BOLD,22);
    private final Font FONT_TITLE = new  Font("Georgia",Font.BOLD,24);

    private final Color COLOR_STATS_NAME = new Color(90, 90, 100);
    private final Color COLOR_STATS_VALUE = new Color(30, 30, 40);
    private final Color COLOR_COLLECTION_TITLE = new Color(200, 30, 100);
    private final Color COLOR_COLLECTION_TITLE_BLOCK = new Color(115, 15, 55);

    private final Color STAT_CARDS_BG = new Color(253, 235, 240);
    private final Color STAT_CARDS_BORDER = new Color(240, 200, 215);

    private final Color DATA_BG = new Color(255, 255, 255);
    private final Color DATA_BORDER = new Color(139, 126, 174, 174);

    private final Color MAIN_BG = new Color(248, 238, 240);

    //JButtons
    private CustomButton btnCreate = new CustomButton("Save");
    private CustomButton btnCancel = new CustomButton("Cancel");

    //Jlabels
    private JLabel lbTitulo = new JLabel("Edit Book");
    private JLabel lbNumId = new JLabel("BOOK ID");
    private JLabel lbNumVolumen = new JLabel("VOLUME NUMBER");
    private JLabel lbEditorial = new JLabel("EDITORIAL");
    private JLabel lbLenguaje = new JLabel("LANGUAJE");
    private JLabel lbEstadoLibro = new JLabel("BOOK STATE");
    private JLabel lbColeccion = new JLabel("COLLECTION");

    //JTextFields
    private JTextField tfIdLibro = new JTextField(20);
    private JTextField tfNumVolumen = new JTextField(20);
    private JTextField tfEditorial = new JTextField(20);
    private JTextField tfLenguaje = new JTextField(20);

    //JComboBox
    private JComboBox<String> cbEstadoLibro = new JComboBox<String>(new String[]{"stopped", "finished", "onreading"});
    private JComboBox<Colecciones> cbColeccion = new JComboBox<Colecciones>(ColeccionesDAO.listarColecciones()
            .toArray(new Colecciones[ColeccionesDAO.listarColecciones().size()]));

    //JPanel
    private JPanel pTitulo = new JPanel(new GridLayout(1,1,10,10));
    private JPanel pIdLibro = new JPanel(new GridBagLayout());
    private JPanel pNumeroVolumen = new JPanel(new GridBagLayout());
    private JPanel pEditorial = new JPanel(new GridBagLayout());
    private JPanel pLenguaje= new JPanel(new GridBagLayout());
    private JPanel pEstadoLibro = new JPanel(new GridBagLayout());
    private JPanel pColeccion = new JPanel(new GridBagLayout());
    private JPanel pBotones = new JPanel(new GridLayout(1,2,10,10));

    //Constraints
    private GridBagConstraints gbcData = new GridBagConstraints();

    /**
     * Constructor of the class
     * @param origen - Main GUI of the app
     * @throws DBException - Error related to anything that has to do with the DB
     */
    public EditarLibro(VentanaPrincipal origen) throws DBException {
        super(origen,true);
        this.origen = origen;
        inicializar();
    }

    /**
     * Method that initialices the Edit GUI
     */
    private void inicializar(){
        this.setTitle("BeakoBeta: Editar Libro");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(MAIN_BG);
        this.setLocationRelativeTo(this.origen);
        this.setResizable(false);

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
        lbTitulo.setFont(FONT_TITLE);
        lbTitulo.setForeground(COLOR_COLLECTION_TITLE);
        this.pTitulo.add(lbTitulo);
        this.pTitulo.setBackground(MAIN_BG);
        this.add(pTitulo,gbcBase);

        //Id Libro
        gbcBase.weightx = 1.0;
        gbcBase.weighty = 1.0;
        gbcData.weightx = 1.0;
        gbcData.weighty = 1.0;
        this.pIdLibro.add(lbNumId,gbcData);
        gbcData.gridy = 1;
        gbcData.fill = GridBagConstraints.HORIZONTAL;
        tfIdLibro.setFont(new Font("SansSerif",Font.PLAIN,15));
        tfIdLibro.setBorder(new EmptyBorder(1,1,1,1));
        tfIdLibro.setForeground(Color.GRAY);
        tfIdLibro.setBackground(MAIN_BG);
        this.pIdLibro.add(tfIdLibro,gbcData);
        gbcBase.gridy = 1;
        pIdLibro.setBackground(MAIN_BG);
        this.add(pIdLibro,gbcBase);

        //Numero Volumenes
        gbcData.gridy = 0;
        gbcBase.weightx = 1.0;
        gbcBase.weighty = 1.0;
        gbcData.weightx = 1.0;
        gbcData.weighty = 1.0;
        gbcData.fill = GridBagConstraints.CENTER;
        this.pNumeroVolumen.add(lbNumVolumen,gbcData);
        gbcData.gridy = 1;
        gbcData.fill = GridBagConstraints.HORIZONTAL;
        tfNumVolumen.setEditable(false);
        tfNumVolumen.setFont(new Font("SansSerif",Font.PLAIN,15));
        tfNumVolumen.setBorder(new EmptyBorder(1,1,1,1));
        tfNumVolumen.setForeground(Color.GRAY);
        tfNumVolumen.setBackground(MAIN_BG);
        this.pNumeroVolumen.add(tfNumVolumen,gbcData);
        gbcBase.gridy = 2;
        this.pNumeroVolumen.setBackground(MAIN_BG);
        this.add(pNumeroVolumen,gbcBase);

        //Editorial
        gbcData.gridy = 0;
        this.pEditorial.add(lbEditorial,gbcData);
        tfEditorial.setEditable(false);
        gbcData.gridy = 1;
        tfEditorial.setFont(new Font("SansSerif",Font.PLAIN,15));
        tfEditorial.setBorder(new EmptyBorder(1,1,1,1));
        tfEditorial.setForeground(Color.GRAY);
        tfEditorial.setBackground(MAIN_BG);
        this.pEditorial.add(tfEditorial,gbcData);
        gbcBase.gridy = 3;
        pEditorial.setBackground(MAIN_BG);
        this.add(pEditorial,gbcBase);

        //Lenguaje
        gbcData.gridy = 0;
        this.pLenguaje.add(lbLenguaje,gbcData);
        tfLenguaje.setEditable(false);
        gbcData.gridy = 1;
        tfLenguaje.setFont(new Font("SansSerif",Font.PLAIN,15));
        tfLenguaje.setBorder(new EmptyBorder(1,1,1,1));
        tfLenguaje.setForeground(Color.GRAY);
        tfLenguaje.setBackground(MAIN_BG);
        this.pLenguaje.add(tfLenguaje,gbcData);
        gbcBase.gridy = 4;
        pLenguaje.setBackground(MAIN_BG);
        this.add(pLenguaje,gbcBase);

        //Estado Libro
        gbcData.gridy = 0;
        this.pEstadoLibro.add(lbEstadoLibro,gbcData);
        cbEstadoLibro.setEditable(false);
        gbcData.gridy = 1;
        cbEstadoLibro.setFont(new Font("SansSerif",Font.PLAIN,15));
        cbEstadoLibro.setForeground(Color.GRAY);
        cbEstadoLibro.setBorder(new EmptyBorder(1,1,1,1));
        cbEstadoLibro.setBackground(MAIN_BG);
        this.pEstadoLibro.add(cbEstadoLibro,gbcData);
        gbcBase.gridy = 5;
        pEstadoLibro.setBackground(MAIN_BG);
        this.add(pEstadoLibro,gbcBase);

        //Coleccion
        gbcData.gridy = 0;
        this.pColeccion.add(lbColeccion,gbcData);
        cbColeccion.setEditable(false);
        gbcData.gridy = 1;
        cbColeccion.setFont(new Font("SansSerif",Font.PLAIN,15));
        cbColeccion.setForeground(Color.GRAY);
        cbColeccion.setBorder(new EmptyBorder(1,1,1,1));
        cbColeccion.setBackground(MAIN_BG);
        this.pColeccion.add(cbColeccion,gbcData);
        gbcBase.gridy = 6;
        pColeccion.setBackground(MAIN_BG);
        this.add(pColeccion,gbcBase);

        //Botones
        gbcData.gridy = 0;
        btnCreate.setEnabled(false);
        btnCreate.setHorizontalAlignment(SwingConstants.CENTER);
        btnCreate.setBackground(COLOR_COLLECTION_TITLE_BLOCK);
        btnCreate.setBorder(new LineBorder(new Color(168, 19, 81),4));
        btnCreate.setForeground(Color.WHITE);
        this.pBotones.add(btnCreate,gbcData);

        gbcData.gridy = 1;
        btnCancel.setHorizontalAlignment(SwingConstants.CENTER);
        btnCancel.setBackground(STAT_CARDS_BORDER);
        btnCancel.setBorder(new LineBorder(new Color(204, 159, 173),4));
        this.pBotones.add(btnCancel,gbcData);

        gbcBase.gridy = 7;
        pBotones.setBackground(MAIN_BG);
        this.add(pBotones,gbcBase);

        this.setSize(521,556);
        this.setResizable(false);

        tfIdLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Libro l = LibroDAO.buscarLibro(Integer.parseInt(tfIdLibro.getText()));
                    if(l != null){
                        tfNumVolumen.setText(Integer.toString(l.getNumeroVolumen()));
                        tfEditorial.setText(l.getEditorial());
                        tfLenguaje.setText(l.getLenguaje());
                        cbEstadoLibro.setSelectedItem(l.getEstadoLibro());
                        cbColeccion.setSelectedItem(Integer.toString(l.getColeccion()));

                        btnCreate.setBackground(COLOR_COLLECTION_TITLE);
                        tfNumVolumen.setEditable(true);
                        tfEditorial.setEditable(true);
                        tfLenguaje.setEditable(true);
                        cbEstadoLibro.setEditable(true);
                        cbColeccion.setEditable(true);
                        btnCreate.setEnabled(true);

                        btnCreate.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                try {
                                    Colecciones coleccion = (Colecciones) cbColeccion.getSelectedItem();
                                    LibroDAO.editarLibros(Integer.parseInt(tfIdLibro.getText()), Integer.parseInt(tfNumVolumen.getText())
                                            ,tfEditorial.getText(),tfLenguaje.getText(),cbEstadoLibro.getSelectedItem().toString()
                                            ,coleccion.getIdCollection());
                                    dispose();
                                } catch (DBException e) {
                                    JOptionPane.showMessageDialog(EditarLibro.this,"Message: " + e.getMessage()
                                            ,"BeakoBeta: Error",JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        });

                    }
                } catch (DBException e) {
                    JOptionPane.showMessageDialog(EditarLibro.this,"Message: " + e.getMessage()
                            ,"BeakoBeta: Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EditarLibro.this.dispose();
            }
        });

        this.setVisible(true);
    }

}
