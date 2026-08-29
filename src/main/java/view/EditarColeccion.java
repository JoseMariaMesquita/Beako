package view;

import dao.ColeccionesDAO;
import entity.Colecciones;
import exceptions.DBException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class which object is a GUI that allows the user to edit the info of a collection
 */
public class EditarColeccion extends JDialog {

    //Origen
    private VentanaPrincipal origen;

    //JLabels
    private JLabel lbTitulo = new JLabel("Editar Libro", JLabel.CENTER);
    private JLabel lbTituloColeccion = new JLabel("Titulo: ", JLabel.CENTER);
    private JLabel lbNombreAutor = new JLabel("Autor: ", JLabel.CENTER);
    private JLabel lbTotalVolumenes = new JLabel("Total Volumen: ", JLabel.CENTER);
    private JLabel lbTotalPoseidos = new JLabel("Total Poseidos: ", JLabel.CENTER);
    private JLabel lbEstadoColeccion = new JLabel("Estado Coleccion: ", JLabel.CENTER);
    private JLabel lbEstadoPublicacion = new JLabel("Estado Publicacion: ", JLabel.CENTER);

    //JButtons
    private JButton btnSave = new JButton("Save");
    private JButton btnCancel = new JButton("Cancel");

    //JTextFields
    private JTextField tfNombreAutor = new JTextField(20);
    private JTextField tfTotalVolumenes = new JTextField(20);
    private JTextField tfTotalPoseidos = new JTextField(20);

    //JComboBox
    private JComboBox<Colecciones> cbTituloColeccion = new JComboBox<Colecciones>(ColeccionesDAO.listarColecciones().toArray(new Colecciones[ColeccionesDAO.listarColecciones().size()]));
    private JComboBox<String> cbEstadoColeccion = new JComboBox<String>(new String[]{"stopped", "finished", "onreading"});
    private JComboBox<String> cbEstadoPublicacion = new JComboBox<String>(new String[]{"cancelado", "terminado", "hiatus", "ongoing"});

    //JPanels
    private JPanel pTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel pTituloColeccion = new JPanel(new GridLayout(1, 2, 10, 10));
    private JPanel pNombreAutor = new JPanel(new GridLayout(1, 2, 10, 10));
    private JPanel pTotalVolumenes = new JPanel(new GridLayout(1, 2, 10, 10));
    private JPanel pTotalPoseidos = new JPanel(new GridLayout(1, 2, 10, 10));
    private JPanel pEstadoColeccion = new JPanel(new GridLayout(1, 2, 10, 10));
    private JPanel pEstadoPublicacion = new JPanel(new GridLayout(1, 2, 10, 10));
    private JPanel pBotonera = new JPanel(new GridLayout(1, 2, 10, 10));


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
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(8, 1, 10, 10));
        this.setLocationRelativeTo(null);

        //Titulo
        lbTitulo.setFont(new Font("arial", Font.BOLD, 20));
        this.pTitulo.add(lbTitulo);
        this.add(pTitulo);

        //Titulo Coleccion
        this.pTituloColeccion.add(this.lbTituloColeccion);
        this.pTituloColeccion.add(this.cbTituloColeccion);
        this.add(pTituloColeccion);

        //Nombre Autor
        this.tfNombreAutor.setEditable(false);
        this.pNombreAutor.add(this.lbNombreAutor);
        this.pNombreAutor.add(this.tfNombreAutor);
        this.add(this.pNombreAutor);

        //Total Volumenes
        this.tfTotalVolumenes.setEditable(false);
        this.pTotalVolumenes.add(this.lbTotalVolumenes);
        this.pTotalVolumenes.add(this.tfTotalVolumenes);
        this.add(pTotalVolumenes);

        //Total Poseidos
        this.tfTotalPoseidos.setEditable(false);
        this.pTotalPoseidos.add(this.lbTotalPoseidos);
        this.pTotalPoseidos.add(this.tfTotalPoseidos);
        this.add(pTotalPoseidos);

        //Estado Coleccion
        this.cbEstadoColeccion.setEditable(false);
        this.pEstadoColeccion.add(this.lbEstadoColeccion);
        this.pEstadoColeccion.add(this.cbEstadoColeccion);
        this.add(pEstadoColeccion);

        //Estado Publicacion
        this.cbEstadoPublicacion.setEditable(false);
        this.pEstadoPublicacion.add(this.lbEstadoPublicacion);
        this.pEstadoPublicacion.add(this.cbEstadoPublicacion);
        this.add(this.pEstadoPublicacion);

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
