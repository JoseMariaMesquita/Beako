package view;

import dao.ColeccionesDAO;
import dao.LibroDAO;
import entity.Libro;
import exceptions.DBException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteCollection extends JFrame {

    //Extras
    private VentanaPrincipal origin;

    //JLabel
    private JLabel lbWindowTittle = new JLabel("Delete Book");

    //JComboBox
    private JComboBox cbCollections = new JComboBox(ColeccionesDAO.listarNombreColecciones().toArray(new String[ColeccionesDAO.listarNombreColecciones().size()]));

    //JButtons
    private JButton btnDelete = new JButton("Delete");
    private JButton btnCancel = new JButton("Cancel");

    //JPanels
    private JPanel pSelection = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pButtons = new JPanel(new GridLayout(1,2,5,5));

    public DeleteCollection(VentanaPrincipal origin) throws DBException {
        this.origin = origin;
        innit();
    }

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


        this.setVisible(true);

        this.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    ColeccionesDAO.eliminarColeccion(ColeccionesDAO.obtenerId(cbCollections.getSelectedItem().toString()));
                } catch (DBException e) {
                    JOptionPane.showMessageDialog(origin,"BeakoBeta: Error", e.getMessage(),JOptionPane.ERROR_MESSAGE);
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
