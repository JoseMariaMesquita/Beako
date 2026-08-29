package view;

import Utils.CustomButton;
import Utils.ImageResizer;
import dao.ColeccionesDAO;
import entity.Colecciones;
import exceptions.DBException;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Class which object is a GUI that is the main GUI of the program
 */
public class VentanaPrincipal extends JFrame {
    //Fuentes
    private final Font FONT_LABELS = new Font("SansSerif", Font.BOLD, 20);

    //Colores
    private final Color CONTROL_PANEL_BG = new Color(253, 235, 240);
    private final Color CONTROL_BORDER = new Color(241, 162, 193);
    private final Color MAIN_BG = new Color(248, 238, 240);

    //JLabels
    private JLabel lbControlPanel = new JLabel("Panel de Control", JLabel.LEFT);
    private JLabel lbCollectionOverview = new JLabel("Overview de Colecciones", JLabel.LEFT);

    //Icons
    private final ImageIcon imgBeako = new ImageIcon("src\\main\\resources\\icons\\beako.png");
    private final ImageIcon imgCreateBook = new ImageIcon("src\\main\\resources\\icons\\createBook.png");
    private final ImageIcon imgEditBook = new ImageIcon("src\\main\\resources\\icons\\editBook.png");
    private final ImageIcon imgDeleteBook = new ImageIcon("src\\main\\resources\\icons\\deleteBook.png");
    private final ImageIcon imgCreateCollection = new ImageIcon("src\\main\\resources\\icons\\createCollection.png");
    private final ImageIcon imgEditCollection = new ImageIcon("src\\main\\resources\\icons\\editCollection.png");
    private final ImageIcon imgDeleteCollection = new ImageIcon("src\\main\\resources\\icons\\deleteCollection.png");

    //JButton
    private CustomButton btnCreateBook = new CustomButton("Crear Libro", ImageResizer.resizeImages(imgCreateBook));
    private CustomButton btnEditBook = new CustomButton("Editar Libro", ImageResizer.resizeImages(imgEditBook));
    private CustomButton btnDeleteBook = new CustomButton("Eliminar Libro", ImageResizer.resizeImages(imgDeleteBook));

    private CustomButton btnCreateCollection = new CustomButton("Crear Coleccion", ImageResizer.resizeImages(imgCreateCollection));
    private CustomButton btnEditCollection = new CustomButton("Editar Coleccion", ImageResizer.resizeImages(imgEditCollection));
    private CustomButton btnDeleteCollection = new CustomButton("Eliminar Colleccion", ImageResizer.resizeImages(imgDeleteCollection));

    //JPanels
    private JPanel pControlPanel = new JPanel(new GridBagLayout());
    private JPanel pCollectionOverview = new JPanel(new GridBagLayout());
    private JPanel pEmptyPanel = new JPanel();

    //JSeparator
    private JSeparator separator = new JSeparator();

    //GridBagConstrains
    private GridBagConstraints gbcControlPanel = new GridBagConstraints();
    private GridBagConstraints gbcCollectionOverview = new GridBagConstraints();

    /**
     * Constructor of the classs
     *
     * @throws DBException - Exception related to the Database
     */
    public VentanaPrincipal() throws DBException {
        inicializar();
    }

    /**
     * Method that initializes the GUI
     *
     * @throws DBException - Exception related to the Database
     */
    public void inicializar() throws DBException {
        this.setTitle("BeakoBeta");
        this.setSize(980, 884);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(MAIN_BG);
        this.setIconImage(ImageResizer.resizeImages(imgBeako).getImage());

        //Frame GridBagConstrain
        GridBagConstraints gbcFrame = new GridBagConstraints();
        gbcFrame.insets = new Insets(2, 2, 2, 2);
        gbcFrame.gridx = 0;
        gbcFrame.gridy = 0;
        gbcFrame.weightx = 0.0;
        gbcFrame.weighty = 0.0;
        gbcFrame.fill = GridBagConstraints.BOTH;


        //ControlPanel
        gbcControlPanel.insets = new Insets(7, 7, 7, 7);
        gbcControlPanel.gridx = 0;
        gbcControlPanel.gridy = 0;
        gbcControlPanel.weightx = 0.0;
        gbcControlPanel.weighty = 0.0;
        gbcControlPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcControlPanel.anchor = GridBagConstraints.NORTHWEST;

        this.lbControlPanel.setFont(FONT_LABELS);
        this.pControlPanel.add(this.lbControlPanel, gbcControlPanel);

        gbcControlPanel.gridx = 0;
        gbcControlPanel.gridy = 1;
        this.pControlPanel.add(this.btnCreateBook, gbcControlPanel);

        gbcControlPanel.gridx = 0;
        gbcControlPanel.gridy = 2;
        this.pControlPanel.add(this.btnEditBook, gbcControlPanel);

        gbcControlPanel.gridx = 0;
        gbcControlPanel.gridy = 3;
        this.pControlPanel.add(this.btnDeleteBook, gbcControlPanel);

        gbcControlPanel.gridx = 0;
        gbcControlPanel.gridy = 4;
        this.pControlPanel.add(separator, gbcControlPanel);

        gbcControlPanel.gridx = 0;
        gbcControlPanel.gridy = 5;
        this.pControlPanel.add(this.btnCreateCollection, gbcControlPanel);

        gbcControlPanel.gridx = 0;
        gbcControlPanel.gridy = 6;
        this.pControlPanel.add(this.btnEditCollection, gbcControlPanel);

        gbcControlPanel.gridx = 0;
        gbcControlPanel.gridy = 7;
        this.pControlPanel.add(this.btnDeleteCollection, gbcControlPanel);

        gbcControlPanel.gridx = 0;
        gbcControlPanel.gridy = 8;
        gbcControlPanel.weightx = 1.0;
        gbcControlPanel.weighty = 1.0;
        gbcControlPanel.fill = GridBagConstraints.BOTH;
        this.pEmptyPanel.setPreferredSize(new Dimension(178, 700));
        this.pEmptyPanel.setBackground(CONTROL_PANEL_BG);
        this.pControlPanel.add(this.pEmptyPanel, gbcControlPanel);

        this.pControlPanel.setPreferredSize(new Dimension(250, 884));
        this.pControlPanel.setMaximumSize(new Dimension(250, 884));
        this.pControlPanel.setBackground(CONTROL_PANEL_BG);
        this.pControlPanel.setBorder(new LineBorder(CONTROL_BORDER, 2));
        this.add(pControlPanel, gbcFrame);

        //Collection Overview
        gbcFrame.gridx = 1;
        gbcFrame.weightx = 1.0;
        gbcFrame.weighty = 1.0;

        gbcCollectionOverview.insets = new Insets(1, 7, 7, 7);
        gbcCollectionOverview.gridx = 0;
        gbcCollectionOverview.gridy = 0;
        gbcCollectionOverview.weightx = 1.0;
        gbcCollectionOverview.weighty = 0.0;
        gbcCollectionOverview.fill = GridBagConstraints.HORIZONTAL;
        gbcCollectionOverview.anchor = GridBagConstraints.NORTHWEST;

        JTable tblCollection = this.collectionTable();
        refreshTableData(tblCollection);
        JScrollPane scrollPane = new JScrollPane(tblCollection);
        this.lbCollectionOverview.setFont(FONT_LABELS);
        this.pCollectionOverview.add(this.lbCollectionOverview, gbcCollectionOverview);


        gbcCollectionOverview.gridy = 1;
        gbcCollectionOverview.weightx = 1.0;
        gbcCollectionOverview.weighty = 1.0;
        gbcCollectionOverview.fill = GridBagConstraints.BOTH;
        this.pCollectionOverview.add(scrollPane, gbcCollectionOverview);
        this.pCollectionOverview.setBackground(MAIN_BG);
        this.add(pCollectionOverview, gbcFrame);


        this.setVisible(true);

        tblCollection.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                JTable table = (JTable) e.getSource();
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);
                if (e.getClickCount() >= 2 && table.getSelectedRowCount() != -1 && row != -1) {
                    try {
                        int collectionId = Integer.parseInt(tblCollection.getValueAt(tblCollection.getSelectedRow(), 0).toString());
                        Colecciones col = ColeccionesDAO.obtenerColeccion(collectionId);
                        CollectionContents cont = new CollectionContents(VentanaPrincipal.this,collectionId, col.getNombre(), col.getAutor(), Integer.toString(col.getTotalVolumenes()), Integer.toString(col.getTotalPoseidos()), col.getEstadoColeccion(), col.getEstadoublicacion());

                    } catch (DBException ex) {
                        JOptionPane.showMessageDialog(VentanaPrincipal.this, "Message: " + ex.getMessage(), "BeakoBeta: Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        this.btnCreateBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    CrearLibro add = new CrearLibro(VentanaPrincipal.this);
                } catch (DBException e) {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, "Message: " + e.getMessage(), "BeakoBeta: Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.btnEditBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    EditarLibro edit = new EditarLibro(VentanaPrincipal.this);
                } catch (DBException e) {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, "Message: " + e.getMessage(), "BeakoBeta: Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.btnDeleteBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    DeleteBook dB = new DeleteBook(VentanaPrincipal.this);
                } catch (DBException e) {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, "Message: " + e.getMessage(), "BeakoBeta: Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.btnCreateCollection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    CrearColeccion cC = new CrearColeccion(VentanaPrincipal.this);
                    refreshTableData(tblCollection);
                } catch (DBException e) {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, "Message: " + e.getMessage(), "BeakoBeta: Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.btnEditCollection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    EditarColeccion eL = new EditarColeccion(VentanaPrincipal.this);
                    refreshTableData(tblCollection);
                } catch (DBException e) {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, "Message: " + e.getMessage(), "BeakoBeta: Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.btnDeleteCollection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    DeleteCollection dC = new DeleteCollection(VentanaPrincipal.this);
                    refreshTableData(tblCollection);
                } catch (DBException e) {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, "Message: " + e.getMessage(), "BeakoBeta: Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    /**
     * Method that creates a table containing all the collections
     *
     * @return tblCollections - JTable containing all the collection
     * @throws DBException - Exception related to the Database
     */
    public JTable collectionTable() throws DBException {
        String[] column = {"ID", "Title", "Autor", "Total de Volumes", "Owned", "Collection Status", "Publication Status"};

        DefaultTableModel model = new DefaultTableModel(column, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tblCollections = new JTable();
        tblCollections.setModel(model);
        tblCollections.setCellSelectionEnabled(false);
        tblCollections.setRowSelectionAllowed(true);

        JTableHeader header = tblCollections.getTableHeader();
        header.setBackground(new Color(246, 212, 224));

        return tblCollections;
    }

    /**
     * Refresh the table model to reload into memory its data
     * @param table - the table
     * @throws DBException
     */
    private void refreshTableData(JTable table) throws DBException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        String[] column = {"ID", "Title", "Autor", "Total de Volumes", "Owned", "Collection Status", "Publication Status"};
        List<Colecciones> listadoDeColecciones = ColeccionesDAO.listarColecciones();
        for (Colecciones c : listadoDeColecciones) {

            model.addRow(new Object[]{
                    c.getIdCollection(),
                    c.getNombre(),
                    c.getAutor(),
                    c.getTotalVolumenes(),
                    c.getTotalPoseidos(),
                    c.getEstadoColeccion(),
                    c.getEstadoublicacion(),
            });


        }


    }


}
