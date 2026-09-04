package view;

import Utils.CustomButton;
import dao.ColeccionesDAO;
import dao.LibroDAO;
import entity.Colecciones;
import entity.Libro;
import exceptions.DBException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class which object is a GUI that allows the user to delete a collection
 */
public class DeleteCollection extends JDialog {
    //Constants
    private final Font FONT_TITLE = new  Font("Georgia",Font.BOLD,24);
    private final Color COLOR_COLLECTION_TITLE = new Color(200, 30, 100);
    private final Color MAIN_BG = new Color(248, 238, 240);

    //Extras
    private VentanaPrincipal origin;

    //JLabel
    private JLabel lbWindowTittle = new JLabel("Delete Collection");
    private JLabel lbCollection = new JLabel("Collection");

    //JComboBox
    private JComboBox<Colecciones> cbCollections = new JComboBox<Colecciones>(ColeccionesDAO.listarColecciones().toArray(new Colecciones[ColeccionesDAO.listarColecciones().size()]));

    //JButtons
    private CustomButton btnDelete = new CustomButton("Delete");
    private CustomButton btnCancel = new CustomButton("Cancel");

    //JPanels
    private JPanel pSelection = new JPanel(new GridBagLayout());
    private JPanel pButtons = new JPanel(new GridBagLayout());

    //GridBakConstrains
    private GridBagConstraints gbcData = new GridBagConstraints();

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
        this.setSize(new Dimension(390,301));
        this.getContentPane().setBackground(MAIN_BG);
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
        lbWindowTittle.setForeground(COLOR_COLLECTION_TITLE);
        lbWindowTittle.setFont(FONT_TITLE);
        this.add(this.lbWindowTittle,gbcBase);

        //Collection
        gbcBase.gridy = 1;
        lbCollection.setFont(new Font("SansSerif",Font.PLAIN,15));
        this.pSelection.add(lbCollection,gbcData);
        gbcData.gridx = 1;
        cbCollections.setBorder(new EmptyBorder(1,1,1,1));
        cbCollections.setBackground(MAIN_BG);
        cbCollections.setForeground(Color.GRAY);
        cbCollections.setFont(new Font("SansSerif",Font.PLAIN,15));
        this.pSelection.add(this.cbCollections,gbcData);
        pSelection.setBackground(MAIN_BG);
        this.add(this.pSelection,gbcBase);

        //Buttons
        gbcBase.gridy = 2;
        gbcData.gridx = 0;
        btnDelete.setHorizontalAlignment(SwingConstants.CENTER);
        btnDelete.setBackground(COLOR_COLLECTION_TITLE);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setBorder(new LineBorder(new Color(168, 19, 81),4));
        this.pButtons.add(this.btnDelete,gbcData);
        gbcData.gridx = 1;
        btnCancel.setHorizontalAlignment(SwingConstants.CENTER);
        this.pButtons.add(this.btnCancel,gbcData);
        pButtons.setBackground(MAIN_BG);
        this.add(this.pButtons,gbcBase);

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
