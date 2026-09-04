package view;

import Utils.CustomButton;
import dao.ColeccionesDAO;
import entity.Colecciones;
import exceptions.DBException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class which object is a GUI that allows the user to edit the info of a collection
 */
public class EditarColeccion extends JDialog {

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


    //Origen
    private VentanaPrincipal origen;

    //JLabels
    private JLabel lbTitulo = new JLabel("Edit Collection");
    private JLabel lbTituloColeccion = new JLabel("TITLE");
    private JLabel lbNombreAutor = new JLabel("AUTHOR");
    private JLabel lbTotalVolumenes = new JLabel("TOTAL VOLUMES");
    private JLabel lbTotalPoseidos = new JLabel("OWNED");
    private JLabel lbEstadoColeccion = new JLabel("COLLECTION STATE");
    private JLabel lbEstadoPublicacion = new JLabel("PUBLISHING STATE");

    //JButtons
    private CustomButton btnSave = new CustomButton("Save");
    private CustomButton btnCancel = new CustomButton("Cancel");

    //JTextFields
    private JTextField tfNombreAutor = new JTextField(20);
    private JTextField tfTotalVolumenes = new JTextField(20);
    private JTextField tfTotalPoseidos = new JTextField(20);

    //JComboBox
    private JComboBox<Colecciones> cbTituloColeccion = new JComboBox<Colecciones>(ColeccionesDAO.listarColecciones().toArray(new Colecciones[ColeccionesDAO.listarColecciones().size()]));
    private JComboBox<String> cbEstadoColeccion = new JComboBox<String>(new String[]{"stopped", "finished", "onreading"});
    private JComboBox<String> cbEstadoPublicacion = new JComboBox<String>(new String[]{"cancelled", "finished", "hiatus", "ongoing"});

    //JPanels
    private JPanel pTitulo = new JPanel(new GridLayout(1,1,10,10));
    private JPanel pTituloColeccion = new JPanel(new GridBagLayout());
    private JPanel pNombreAutor = new JPanel(new GridBagLayout());
    private JPanel pTotalVolumenes = new JPanel(new GridBagLayout());
    private JPanel pTotalPoseidos = new JPanel(new GridBagLayout());
    private JPanel pEstadoColeccion = new JPanel(new GridBagLayout());
    private JPanel pEstadoPublicacion = new JPanel(new GridBagLayout());
    private JPanel pBotonera = new JPanel(new GridBagLayout());


    /**
     * Constructor of the class
     *
     * @param origen - Main GUI of the app
     * @throws DBException - Error related to anything that has to do with the DB
     */
    public EditarColeccion(VentanaPrincipal origen)throws DBException{
        super(origen,true);
        this.origen = origen;
        inicializar();
    }

    /**
     * Method that initializes the Edit GUI
     */
    public void inicializar(){
        this.setTitle("BaekoBeta: Editar Coleccion");
        this.setSize(350,740);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(MAIN_BG);
        this.setLayout(new GridBagLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        GridBagConstraints gbcBase = new GridBagConstraints();
        gbcBase.insets = new Insets(7,10,7,10);
        gbcBase.gridx = 0;
        gbcBase.gridy = 0;
        gbcBase.weightx = 0.0;
        gbcBase.weighty = 0.0;
        gbcBase.anchor = GridBagConstraints.NORTHWEST;
        gbcBase.fill = GridBagConstraints.HORIZONTAL;

        //GridBagConstraints Data
        GridBagConstraints gbcData = new GridBagConstraints();
        gbcData.insets = new Insets(5,12,5,12);
        gbcData.gridx = 0;
        gbcData.gridy = 0;
        gbcData.weightx = 0.0;
        gbcData.weighty = 0.0;
        gbcData.fill = GridBagConstraints.HORIZONTAL;
        gbcData.anchor = GridBagConstraints.NORTHWEST;

        //Titulo
        lbTitulo.setFont(FONT_TITLE);
        lbTitulo.setForeground(COLOR_COLLECTION_TITLE);
        this.pTitulo.add(lbTitulo);
        pTitulo.setBackground(MAIN_BG);
        this.add(pTitulo,gbcBase);

        //Titulo Coleccion
        gbcBase.gridy = 1;
        gbcBase.weightx = 1.0;
        gbcBase.weighty = 1.0;
        gbcData.weightx = 1.0;
        gbcData.weighty = 1.0;
        this.pTituloColeccion.add(this.lbTituloColeccion,gbcData);
        gbcData.gridy = 1;
        cbTituloColeccion.setBackground(MAIN_BG);
        cbTituloColeccion.setForeground(Color.GRAY);
        cbTituloColeccion.setFont(new Font("SansSerif",Font.PLAIN,15));
        cbTituloColeccion.setBorder(new EmptyBorder(1,1,1,1));
        this.pTituloColeccion.add(this.cbTituloColeccion,gbcData);
        pTituloColeccion.setBackground(MAIN_BG);
        this.add(pTituloColeccion,gbcBase);

        //Nombre Autor
        gbcBase.gridy = 2;
        gbcData.gridy = 0;
        this.tfNombreAutor.setEditable(false);
        this.pNombreAutor.add(this.lbNombreAutor,gbcData);
        gbcData.gridy = 1;
        tfNombreAutor.setBorder(new EmptyBorder(1,1,1,1));
        tfNombreAutor.setBackground(MAIN_BG);
        tfNombreAutor.setForeground(Color.GRAY);
        tfNombreAutor.setFont(new Font("SansSerif",Font.PLAIN,15));
        this.pNombreAutor.add(this.tfNombreAutor,gbcData);
        pNombreAutor.setBackground(MAIN_BG);
        this.add(this.pNombreAutor,gbcBase);

        //Total Volumenes
        gbcBase.gridy = 3;
        gbcData.gridy = 0;
        this.tfTotalVolumenes.setEditable(false);
        this.pTotalVolumenes.add(this.lbTotalVolumenes,gbcData);
        gbcData.gridy = 1;
        tfTotalVolumenes.setBorder(new EmptyBorder(1,1,1,1));
        tfTotalVolumenes.setBackground(MAIN_BG);
        tfTotalVolumenes.setForeground(Color.GRAY);
        tfTotalVolumenes.setFont(new Font("SansSerif",Font.PLAIN,15));
        this.pTotalVolumenes.add(this.tfTotalVolumenes,gbcData);
        pTotalVolumenes.setBackground(MAIN_BG);
        this.add(pTotalVolumenes,gbcBase);

        //Total Poseidos
        gbcBase.gridy = 4;
        gbcData.gridy = 0;
        this.tfTotalPoseidos.setEditable(false);
        this.pTotalPoseidos.add(this.lbTotalPoseidos,gbcData);
        gbcData.gridy = 1;
        tfTotalPoseidos.setBorder(new EmptyBorder(1,1,1,1));
        tfTotalPoseidos.setBackground(MAIN_BG);
        tfTotalPoseidos.setForeground(Color.GRAY);
        tfTotalPoseidos.setFont(new Font("SansSerif",Font.PLAIN,15));
        this.pTotalPoseidos.add(this.tfTotalPoseidos,gbcData);
        pTotalPoseidos.setBackground(MAIN_BG);
        this.add(pTotalPoseidos,gbcBase);

        //Estado Coleccion
        gbcBase.gridy = 5;
        gbcData.gridy = 0;
        this.cbEstadoColeccion.setEditable(false);
        this.pEstadoColeccion.add(this.lbEstadoColeccion,gbcData);
        gbcData.gridy = 1;
        cbEstadoColeccion.setBorder(new EmptyBorder(1,1,1,1));
        cbEstadoColeccion.setBackground(MAIN_BG);
        cbEstadoColeccion.setForeground(Color.GRAY);
        cbEstadoColeccion.setFont(new Font("SansSerif",Font.PLAIN,15));
        this.pEstadoColeccion.add(this.cbEstadoColeccion,gbcData);
        pEstadoColeccion.setBackground(MAIN_BG);
        this.add(pEstadoColeccion,gbcBase);

        //Estado Publicacion
        gbcBase.gridy = 6;
        gbcData.gridy = 0;
        this.cbEstadoPublicacion.setEditable(false);
        this.pEstadoPublicacion.add(this.lbEstadoPublicacion,gbcData);
        gbcData.gridy = 1;
        cbEstadoPublicacion.setBorder(new EmptyBorder(1,1,1,1));
        cbEstadoPublicacion.setBackground(MAIN_BG);
        cbEstadoPublicacion.setForeground(Color.GRAY);
        cbEstadoPublicacion.setFont(new Font("SansSerif",Font.PLAIN,15));
        this.pEstadoPublicacion.add(this.cbEstadoPublicacion,gbcData);
        pEstadoPublicacion.setBackground(MAIN_BG);
        this.add(this.pEstadoPublicacion,gbcBase);

        //Buttons
        gbcBase.gridy = 7;
        gbcData.gridx = 0;
        btnSave.setEnabled(false);
        btnSave.setHorizontalAlignment(SwingConstants.CENTER);
        btnSave.setBackground(COLOR_COLLECTION_TITLE_BLOCK);
        btnSave.setBorder(new LineBorder(new Color(168, 19, 81),4));
        btnSave.setForeground(Color.WHITE);
        this.pBotonera.add(this.btnSave,gbcData);
        btnCancel.setHorizontalAlignment(SwingConstants.CENTER);
        gbcData.gridx = 1;
        this.pBotonera.add(this.btnCancel,gbcData);
        pBotonera.setBackground(MAIN_BG);
        this.add(pBotonera,gbcBase);

        cbTituloColeccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Colecciones coleccion = (Colecciones) cbTituloColeccion.getSelectedItem();
                if (coleccion != null) {
                    tfNombreAutor.setText(coleccion.getAutor());
                    tfTotalVolumenes.setText(Integer.toString(coleccion.getTotalVolumenes()));
                    tfTotalPoseidos.setText(Integer.toString(coleccion.getTotalPoseidos()));
                    cbEstadoColeccion.setSelectedItem(coleccion.getEstadoColeccion());
                    cbEstadoPublicacion.setSelectedItem(coleccion.getEstadoublicacion());

                    btnSave.setEnabled(true);
                    btnSave.setBackground(COLOR_COLLECTION_TITLE);
                    btnSave.setForeground(Color.WHITE);
                    tfTotalPoseidos.setEditable(false);
                    tfTotalVolumenes.setEditable(true);
                    cbEstadoColeccion.setEditable(true);
                    cbEstadoPublicacion.setEditable(true);
                }


            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Colecciones collection = (Colecciones) cbTituloColeccion.getSelectedItem();
                    if(cbTituloColeccion != null && cbEstadoColeccion != null && collection != null) {
                        ColeccionesDAO.editarColeccion(cbTituloColeccion.getSelectedItem().toString(), tfNombreAutor.getText(), Integer.parseInt(tfTotalVolumenes.getText()), Integer.parseInt(tfTotalPoseidos.getText()), cbEstadoColeccion.getSelectedItem().toString(), cbEstadoPublicacion.getSelectedItem().toString(), collection.getIdCollection());
                        dispose();
                    }
                } catch (DBException e) {
                    JOptionPane.showMessageDialog(origen,"Message: " + e.getMessage(),"BeakoBeta: Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        this.setVisible(true);

    }
}
