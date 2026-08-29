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
 * Class which object is a GUI that allows the user to delete a collection
 */
public class DeleteCollection extends JDialog {

    //Extras
    private VentanaPrincipal origin;

    //JLabel
    private JLabel lbWindowTittle = new JLabel("Delete Book");

    //JComboBox
    private JComboBox<Colecciones> cbCollections = new JComboBox<Colecciones>(ColeccionesDAO.listarColecciones().toArray(new Colecciones[ColeccionesDAO.listarColecciones().size()]));

    //JButtons
    private JButton btnDelete = new JButton("Delete");
    private JButton btnCancel = new JButton("Cancel");

    //JPanels
    private JPanel pSelection = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pButtons = new JPanel(new GridLayout(1,2,5,5));

    /**
     * Constructor of the Class
     * @param origin - Main GUI
     * @throws DBException - Exception related to the DataBase
     */
    public DeleteCollection(VentanaPrincipal origin) throws DBException {
        super(origin,true);
        this.origin = origin;
        innit();
    }

    /**
     * Method that initializes the GUI
     */
    private void innit(){
        this.setTitle("BeakoBeta: Delete Collection");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(2,1,10,10));

        this.pSelection.add(this.lbWindowTittle);
        this.pSelection.add(this.cbCollections);
        this.add(this.pSelection);

        this.pButtons.add(this.btnDelete);
        this.pButtons.add(this.btnCancel);
        this.add(this.pButtons);

        this.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Colecciones collection = (Colecciones) cbCollections.getSelectedItem();
                    if(collection != null) {
                        ColeccionesDAO.eliminarColeccion(collection.getIdCollection());
                        dispose();
                    }
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
