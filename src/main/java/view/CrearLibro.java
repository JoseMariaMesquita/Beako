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
 * Class which object is a GUI that allows the user to create a book and add it to a collection
 */
public class CrearLibro extends JDialog {
    //Extras
    private VentanaPrincipal origen;

    //JButtons
    private JButton btnCreate = new JButton("Create Book");
    private JButton btnCancel = new JButton("Cancel");

    //Jlabels
    private JLabel lbTitulo = new JLabel("Crear Libro");
    private JLabel lbNumVolumen = new JLabel("Numero Volumen: ",JLabel.CENTER);
    private JLabel lbEditorial = new JLabel("Editorial: ",JLabel.CENTER);
    private JLabel lbLenguaje = new JLabel("Lenguaje: ",JLabel.CENTER);
    private JLabel lbEstadoLibro = new JLabel("Estado Libro: ",JLabel.CENTER);
    private JLabel lbColeccion = new JLabel("Coleccion: ",JLabel.CENTER);

    //JTextFields
    private JTextField tfNumVolumen = new JTextField(20);
    private JTextField tfEditorial = new JTextField(20);
    private JTextField tfLenguaje = new JTextField(20);

    //JComboBox
    private JComboBox<String> cbEstadoLibro = new JComboBox<String>(new String[]{"stopped", "finished", "onreading"});
    JComboBox<Colecciones> cbColeccion = new JComboBox<Colecciones>(ColeccionesDAO.listarColecciones().toArray(new Colecciones[ColeccionesDAO.listarColecciones().size()]));

    //JPanel
    private JPanel pTitulo = new JPanel(new GridLayout(1,1,10,10));
    private JPanel pNumeroVolumen = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pEditorial = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pLenguaje= new JPanel(new GridLayout(1,2,10,10));
    private JPanel pEstadoLibro = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pColeccion = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pBotones = new JPanel(new GridLayout(1,2,10,10));

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
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(7,1,5,20));
        this.setLocationRelativeTo(null);

        //Titulo
        lbTitulo.setFont(new Font("arial",Font.BOLD,20));
        this.pTitulo.add(lbTitulo);
        this.add(pTitulo);

        //Numero Volumenes
        this.pNumeroVolumen.add(lbNumVolumen);
        this.pNumeroVolumen.add(tfNumVolumen);
        this.add(pNumeroVolumen);

        //Editorial
        this.pEditorial.add(lbEditorial);
        this.pEditorial.add(tfEditorial);
        this.add(pEditorial);

        //Lenguaje
        this.pLenguaje.add(lbLenguaje);
        this.pLenguaje.add(tfLenguaje);
        this.add(pLenguaje);

        //Estado Libro
        this.pEstadoLibro.add(lbEstadoLibro);
        this.pEstadoLibro.add(cbEstadoLibro);
        this.add(pEstadoLibro);

        //Coleccion
        this.pColeccion.add(lbColeccion);
        this.pColeccion.add(cbColeccion);
        this.add(pColeccion);

        //Botones
        this.pBotones.add(btnCreate);
        this.pBotones.add(btnCancel);
        this.add(pBotones);

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
