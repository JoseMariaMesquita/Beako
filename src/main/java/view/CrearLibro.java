package view;

import dao.ColeccionesDAO;
import dao.LibroDAO;
import entity.Libro;
import exceptions.DBException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearLibro extends JFrame {
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
    private JComboBox<String> cbColeccion = new JComboBox<String>(ColeccionesDAO.listarNombreColecciones().toArray(new String[ColeccionesDAO.listarNombreColecciones().size()]));

    //JPanel
    private JPanel pTitulo = new JPanel(new GridLayout(1,1,10,10));
    private JPanel pNumeroVolumen = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pEditorial = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pLenguaje= new JPanel(new GridLayout(1,2,10,10));
    private JPanel pEstadoLibro = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pColeccion = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pBotones = new JPanel(new GridLayout(1,2,10,10));

    /**
     * Test Main class
     */
    public static void main(){
        try {
            CrearLibro c = new CrearLibro();
        } catch (DBException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test Object Constructor
     * @throws DBException
     */
    public CrearLibro() throws DBException {
        inicializar();
    }

    /**
     * Contructor of the CrearLibro class
     * @param origen - Main GUI of the app
     * @throws DBException -Exception to anything related to the DB
     */
    public CrearLibro(VentanaPrincipal origen) throws DBException {
        this.origen = origen;
        inicializar();
    }

    /**
     * Method that initialices the creation GUI
     */
    private void inicializar(){
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
                    Libro l = new Libro(Integer.parseInt(tfNumVolumen.getText()),tfEditorial.getText(),tfLenguaje.getText(),cbEstadoLibro.getSelectedItem().toString(),ColeccionesDAO.obtenerId(cbColeccion.getSelectedItem().toString()));
                    LibroDAO.insertarLibro(l);
                    ColeccionesDAO.incrementOwnedBooks(l.getColeccion());
                } catch (DBException e) {
                    JOptionPane.showMessageDialog(CrearLibro.this,"Error al ejecutar orden sql","Error:Beako Beta",JOptionPane.ERROR_MESSAGE);
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
