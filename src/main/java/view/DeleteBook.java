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
 * Class which object is a GUI that allows the user to delete a book from a collection
 */
public class DeleteBook extends JFrame {

    //Extras
    private VentanaPrincipal origin;


    //JLabel
    private JLabel lbWindowTittle = new JLabel("Delete Book",JLabel.CENTER);
    private  JLabel lbCollections = new JLabel("Collection: ");
    private JLabel lbBookId = new JLabel("Book: ");


    //JComboBox
    private JComboBox cbCollections = new JComboBox(ColeccionesDAO.listarColecciones()
            .toArray(new Colecciones[ColeccionesDAO.listarColecciones().size()]));
    private JComboBox cbBooks;

    //JButtons
    private JButton btnDelete = new JButton("Delete");
    private JButton btnCancel = new JButton("Cancel");

    //JPanels
    private JPanel pTittle = new JPanel(new GridLayout(1,1,10,10));
    private JPanel pSelectionCollection = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pSelectionBook = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pButtons = new JPanel(new GridLayout(1,2,5,5));

    /**
     * Constructor of the Class
     * @param origin - Main GUI
     * @throws DBException - Exception related to the DataBase
     */
    public DeleteBook(VentanaPrincipal origin) throws DBException {
        this.origin = origin;
        innit();
    }

    /**
     * Method that initializes the GUI
     * @throws DBException
     */
    private void innit() throws DBException{
        //JFrameConfiguration
        this.setTitle("BeakoBeta: Delete Book");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(4,1,10,10));

        //Tittle
        this.pTittle.add(lbWindowTittle);
        this.add(this.pTittle);

        //Collection
        this.pSelectionCollection.add(this.lbCollections);
        this.pSelectionCollection.add(this.cbCollections);
        this.add(pSelectionCollection);

        //Books
        Colecciones collection = (Colecciones) cbCollections.getSelectedItem();
        if(collection != null){
            this.cbBooks = new JComboBox(LibroDAO.listBooksByCollection(collection.getIdCollection())
                    .toArray(new Libro[LibroDAO.listBooksByCollection(collection.getIdCollection()).size()]));
        }
        this.pSelectionBook.add(this.lbBookId);
        this.pSelectionBook.add(this.cbBooks);
        this.add(pSelectionBook);

        //Buttons
        this.pButtons.add(this.btnDelete);
        this.pButtons.add(this.btnCancel);
        this.add(pButtons);


        this.setVisible(true);

        this.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Libro book = (Libro) cbBooks.getSelectedItem();

                    if(book != null) {
                        Colecciones collection = ColeccionesDAO.obtenerColeccion(book.getColeccion());
                        LibroDAO.eliminarLibro(book.getIdBook());
                        if(collection.getTotalPoseidos() > 0) {
                            collection.setTotalPoseidos(collection.getTotalPoseidos() - 1);
                            ColeccionesDAO.editarColeccion(collection.getNombre(),collection.getAutor()
                                    ,collection.getTotalVolumenes(),collection.getTotalPoseidos()
                                    ,collection.getEstadoColeccion(),collection.getEstadoublicacion()
                                    ,collection.getIdCollection());
                        }
                    }
                    dispose();
                } catch (DBException e) {
                    JOptionPane.showMessageDialog(origin,"Message: " + e.getMessage(),"BeakoBeta: Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

    }
}
