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
 * Class which object is a GUI that allows the user to modify the data of a book
 */
public class EditarLibro extends JFrame {
    //Extras
    private VentanaPrincipal origen;

    //JButtons
    private JButton btnCreate = new JButton("Save");
    private JButton btnCancel = new JButton("Cancel");

    //Jlabels
    private JLabel lbTitulo = new JLabel("Editar Libro",JLabel.CENTER);
    private JLabel lbNumId = new JLabel("Id Libro",JLabel.CENTER);
    private JLabel lbNumVolumen = new JLabel("Numero Volumen: ",JLabel.CENTER);
    private JLabel lbEditorial = new JLabel("Editorial: ",JLabel.CENTER);
    private JLabel lbLenguaje = new JLabel("Lenguaje: ",JLabel.CENTER);
    private JLabel lbEstadoLibro = new JLabel("Estado Libro: ",JLabel.CENTER);
    private JLabel lbColeccion = new JLabel("Coleccion: ",JLabel.CENTER);

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
    private JPanel pIdLibro = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pNumeroVolumen = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pEditorial = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pLenguaje= new JPanel(new GridLayout(1,2,10,10));
    private JPanel pEstadoLibro = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pColeccion = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pBotones = new JPanel(new GridLayout(1,2,10,10));

    /**
     * Constructor of the class
     * @param origen - Main GUI of the app
     * @throws DBException - Error related to anything that has to do with the DB
     */
    public EditarLibro(VentanaPrincipal origen) throws DBException {
        this.origen = origen;
        inicializar();
    }

    /**
     * Method that initialices the Edit GUI
     */
    private void inicializar(){
        this.setTitle("BeakoBeta: Editar Libro");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(8,1,5,20));
        this.setLocationRelativeTo(null);

        //Titulo
        lbTitulo.setFont(new Font("arial",Font.BOLD,20));
        this.pTitulo.add(lbTitulo);
        this.add(pTitulo);

        //Id Libro
        this.pIdLibro.add(lbNumId);
        this.pIdLibro.add(tfIdLibro);
        this.add(pIdLibro);

        //Numero Volumenes
        this.pNumeroVolumen.add(lbNumVolumen);
        tfNumVolumen.setEditable(false);
        this.pNumeroVolumen.add(tfNumVolumen);
        this.add(pNumeroVolumen);

        //Editorial
        this.pEditorial.add(lbEditorial);
        tfEditorial.setEditable(false);
        this.pEditorial.add(tfEditorial);
        this.add(pEditorial);

        //Lenguaje
        this.pLenguaje.add(lbLenguaje);
        tfLenguaje.setEditable(false);
        this.pLenguaje.add(tfLenguaje);
        this.add(pLenguaje);

        //Estado Libro
        this.pEstadoLibro.add(lbEstadoLibro);
        cbEstadoLibro.setEditable(false);
        this.pEstadoLibro.add(cbEstadoLibro);
        this.add(pEstadoLibro);

        //Coleccion
        this.pColeccion.add(lbColeccion);
        cbColeccion.setEditable(false);
        this.pColeccion.add(cbColeccion);
        this.add(pColeccion);

        //Botones
        btnCreate.setEnabled(false);
        this.pBotones.add(btnCreate);
        this.pBotones.add(btnCancel);
        this.add(pBotones);

        this.setSize(521,556);
        this.setResizable(false);
        this.setVisible(true);

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
    }

}
