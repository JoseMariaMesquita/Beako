package view;

import dao.LibroDAO;
import entity.Libro;
import exceptions.DBException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteBook extends JFrame {

    //Extras
    private VentanaPrincipal origin;

    //JLabel
    private JLabel lbWindowTittle = new JLabel("Delete Book");
    private JLabel lbWindowTittle = new JLabel("Delete Book");

    //JComboBox
    private JComboBox cbBooks = new JComboBox(LibroDAO.listarLibros().toArray(new Libro[LibroDAO.listarLibros().size()]));

    //JButtons
    private JButton btnDelete = new JButton("Delete");
    private JButton btnCancel = new JButton("Cancel");

    //JPanels
    private JPanel pSelection = new JPanel(new GridLayout(1,2,10,10));
    private JPanel pButtons = new JPanel(new GridLayout(1,2,5,5));

    public DeleteBook(VentanaPrincipal origin) throws DBException {
        this.origin = origin;
        innit();
    }

    private void innit() throws DBException{
        this.setTitle("BeakoBeta: Delete Book");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(2,1,10,10));

        this.pSelection.add(this.lbWindowTittle);
        this.pSelection.add(this.cbBooks);
        this.add(this.pSelection);

        this.pButtons.add(btnDelete);
        this.pButtons.add(btnCancel);
        this.add(this.pButtons);

        this.setVisible(true);

        this.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    LibroDAO.eliminarLibro(LibroDAO.searchBookByVolume(Integer.parseInt(cbBooks.getSelectedItem().toString()),));
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
