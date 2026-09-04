package view;

import Utils.CustomButton;
import dao.ColeccionesDAO;
import dao.LibroDAO;
import entity.Colecciones;
import entity.Libro;
import exceptions.DBException;
import exceptions.NoBookException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Class which object is a GUI that allows the user to delete a book from a collection
 */
public class DeleteBook extends JDialog {

    //Extras
    private VentanaPrincipal origin;

    //Constants
    private final Font FONT_TITLE = new  Font("Georgia",Font.BOLD,24);
    private final Color COLOR_COLLECTION_TITLE = new Color(200, 30, 100);
    private final Color MAIN_BG = new Color(248, 238, 240);

    //JLabel
    private JLabel lbWindowTittle = new JLabel("Delete Book",JLabel.CENTER);
    private  JLabel lbCollections = new JLabel("Collection: ");
    private JLabel lbBookId = new JLabel("Book: ");


    //JComboBox
    private JComboBox cbCollections;
    private JComboBox cbBooks;

    //JButtons
    private CustomButton btnDelete = new CustomButton("Delete");
    private CustomButton btnCancel = new CustomButton("Cancel");

    //JPanels
    private JPanel pTittle = new JPanel(new GridLayout(1,1,10,10));
    private JPanel pSelectionCollection = new JPanel(new GridBagLayout());
    private JPanel pSelectionBook = new JPanel(new GridBagLayout());
    private JPanel pButtons = new JPanel(new GridBagLayout());

    //GridBakConstrains
    private GridBagConstraints gbcData = new GridBagConstraints();

    /**
     * Constructor of the Class
     * @param origin - Main GUI
     * @throws DBException - Exception related to the DataBase
     */
    public DeleteBook(VentanaPrincipal origin) throws DBException, NoBookException {
        this.origin = origin;
        innit();
    }

    /**
     * Method that initializes the GUI
     * @throws DBException
     */
    private void innit() throws DBException, NoBookException{
        //JFrameConfiguration
        this.setTitle("BeakoBeta: Delete Book");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(this.origin);
        this.getContentPane().setBackground(MAIN_BG);
        this.setSize(new Dimension(390,301));
        this.setLayout(new GridBagLayout());
        this.setResizable(false);

        GridBagConstraints gbcBase = new GridBagConstraints();
        gbcBase.insets = new Insets(7,10,7,10);
        gbcBase.gridx = 0;
        gbcBase.gridy = 0;
        gbcBase.weightx = 1.1;
        gbcBase.weighty = 1.1;
        gbcBase.fill = GridBagConstraints.HORIZONTAL;

        //GridBagLayout Data
        gbcData.insets = new Insets(5,5,5,5);
        gbcData.gridx = 0;
        gbcData.gridy = 0;
        gbcData.weightx = 1.1;
        gbcData.weighty = 1.1;
        gbcData.fill = GridBagConstraints.HORIZONTAL;

        //Tittle
        lbWindowTittle.setFont(FONT_TITLE);
        lbWindowTittle.setForeground(COLOR_COLLECTION_TITLE);
        this.pTittle.add(lbWindowTittle);
        pTittle.setBackground(MAIN_BG);
        this.add(this.pTittle,gbcBase);

        //Collection
        gbcBase.gridy = 1;
        List<Colecciones> collectionsList = ColeccionesDAO.listarColecciones();
        if(collectionsList.isEmpty()) {
            throw new NoBookException("No Books found inside the DataBase");
        }else {
            cbCollections = new JComboBox(collectionsList.toArray());
            cbCollections.setForeground(Color.GRAY);
            cbCollections.setBackground(MAIN_BG);
            cbCollections.setBorder(new EmptyBorder(1,1,1,1));
            cbCollections.setFont(new Font("SansSerif",Font.PLAIN,15));
            this.pSelectionCollection.add(this.lbCollections,gbcData);
            gbcData.gridx = 1;
            this.pSelectionCollection.add(this.cbCollections,gbcData);
            pSelectionCollection.setBackground(MAIN_BG);
            this.add(pSelectionCollection,gbcBase);
        }

        //Books
        gbcBase.gridy = 2;
        Colecciones collection = (Colecciones) cbCollections.getSelectedItem();
        if(collection != null){
            this.cbBooks = new JComboBox(LibroDAO.listBooksByCollection(collection.getIdCollection())
                    .toArray(new Libro[LibroDAO.listBooksByCollection(collection.getIdCollection()).size()]));
        }
        gbcData.gridx = 0;
        if(cbBooks != null) {
            cbBooks.setForeground(Color.GRAY);
            cbBooks.setBackground(MAIN_BG);
            cbBooks.setBorder(new EmptyBorder(1, 1, 1, 1));
            cbBooks.setFont(new Font("SansSerif",Font.PLAIN,15));
            this.pSelectionBook.add(this.lbBookId, gbcData);
            gbcData.gridx = 1;
            this.pSelectionBook.add(this.cbBooks, gbcData);
            pSelectionBook.setBackground(MAIN_BG);
            this.add(pSelectionBook, gbcBase);
        }

        //Buttons
        gbcBase.gridy = 3;
        gbcData.gridx = 0;
        btnDelete.setHorizontalAlignment(SwingConstants.CENTER);
        btnDelete.setBorder(new LineBorder(new Color(168, 19, 81),4));
        btnDelete.setBackground(COLOR_COLLECTION_TITLE);
        btnDelete.setForeground(Color.WHITE);
        this.pButtons.add(this.btnDelete,gbcData);

        gbcData.gridx = 1;
        btnCancel.setHorizontalAlignment(SwingConstants.CENTER);
        btnCancel.setBorder(new LineBorder(new Color(204, 159, 173),4));
        btnCancel.setBackground(Color.WHITE);
        this.pButtons.add(this.btnCancel,gbcData);
        pButtons.setBackground(MAIN_BG);
        this.add(pButtons,gbcBase);

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

        this.setVisible(true);

    }
}
