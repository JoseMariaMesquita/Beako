package view;

import dao.ColeccionesDAO;
import dao.LibroDAO;
import entity.Colecciones;
import entity.Libro;
import exceptions.DBException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

/**
 * Class which object is a GUI that portraits then info of the selected collection
 */
public class CollectionContents extends JDialog {
    //Constants
    private final Font FONT_STATS_NAME = new Font("SansSerif",Font.PLAIN,14);
    private final Font FONT_STATS_VALUE = new Font("SansSerif",Font.BOLD,22);
    private final Font FONT_TITLE = new  Font("SansSerif",Font.PLAIN,24);

    private final Color COLOR_STATS_NAME = new Color(90, 90, 100);
    private final Color COLOR_STATS_VALUE = new Color(30, 30, 40);
    private final Color COLOR_COLLECTION_TITLE = new Color(200, 30, 100);

    private final Color STAT_CARDS_BG = new Color(253, 235, 240);
    private final Color STAT_CARDS_BORDER = new Color(240, 200, 215);

    private final Color DATA_BG = new Color(255, 255, 255);
    private final Color DATA_BORDER = new Color(139, 126, 174, 174);

    private final Color MAIN_BG = new Color(248, 238, 240);

    //Extras
    private VentanaPrincipal origen;

    //Parametros
    private int collectionId;
    private String tittleString;
    private String author;
    private String counterTotalVolumes;
    private String counterOwned;
    private String counterCollectionStatus;
    private String counterPublicationStatus;

    //JLabels - Text
    private JLabel lbCollectionTittle = new JLabel();
    private JLabel lbTotalVolumes = new JLabel("Total Volumes",JLabel.LEFT);
    private JLabel lbOwned = new JLabel("Owned",JLabel.LEFT);
    private JLabel lbCollectionStatus = new JLabel("Collection Status",JLabel.LEFT);
    private JLabel lbPublicationStatus = new JLabel("Publication Status",JLabel.LEFT);

    //JLabel - Counters
    private JLabel lbCounterTotalVolumes = new JLabel();
    private JLabel lbCounterOwned = new JLabel();
    private JLabel lbCounterCollectionStatus = new JLabel();
    private JLabel lbCounterPublicationStatus = new JLabel();

    //JPanels
    private JPanel pData = new JPanel(new GridBagLayout());
    private JPanel pCounters = new JPanel(new GridLayout(1,4,10,10));
    private JPanel pVolumeCounter = new JPanel(new GridBagLayout());
    private JPanel pOwnedCounter = new JPanel(new GridBagLayout());
    private JPanel pCollectionCounter = new JPanel(new GridBagLayout());
    private JPanel pPublicationCounter = new JPanel(new GridBagLayout());

    //Panel - Data GridBagConstraint
    private GridBagConstraints gbcData = new GridBagConstraints();

    //Panel - Counter Grid Bag Constrains
    private GridBagConstraints gbcCounter = new GridBagConstraints();

    /**
     * Constructor of the CollectionContents GUI
     * @param collectionId - Id of the collection
     * @param tittleString - Tittle of the collection
     * @param author - Author of the collection
     * @param counterTotalVolumes - Total volumes of the collection
     * @param counterOwned - Owned books of the collection
     * @param counterCollectionStatus - State of the collection regarding the user
     * @param counterPublicationStatus - State of the collection regarding publishing
     * @throws DBException - Exception related to the DataBase
     */
    public CollectionContents(VentanaPrincipal origen,int collectionId, String tittleString, String author, String counterTotalVolumes, String counterOwned, String counterCollectionStatus, String counterPublicationStatus) throws DBException{
        super(origen,true);
        this.origen = origen;
        this.collectionId = collectionId;
        this.tittleString = tittleString;
        this.author = author;
        this.counterTotalVolumes = counterTotalVolumes;
        this.counterOwned = counterOwned;
        this.counterCollectionStatus = counterCollectionStatus;
        this.counterPublicationStatus = counterPublicationStatus;
        innit(this.tittleString, this.author,this.counterTotalVolumes,this.counterOwned,this.counterCollectionStatus,this.counterPublicationStatus);
    }

    /**
     * Initialization method of the UI
     * @param tittleString - Tittle of the collection
     * @param author - Author of the collection
     * @param counterTotalVolumes - Total volumes of the collection
     * @param counterOwned - Owned books of the collection
     * @param counterCollectionStatus - State of the collection regarding the user
     * @param counterPublicationStatus - State of the collection regarding publishing
     * @throws DBException - Exception related to the DataBase
     */
    public void innit(String tittleString, String author, String counterTotalVolumes, String counterOwned, String counterCollectionStatus, String counterPublicationStatus) throws DBException {
        this.setTitle("Collection: " + tittleString + " - " + author);
        this.setSize(918,468);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        this.setResizable(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;
        gbc.gridy  = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        //Counters
        gbcCounter.insets = new Insets(4,4,4,4);
        gbcCounter.gridx = 0;
        gbcCounter.gridy  = 0;


        //Counter - Total Volumes
        this.lbTotalVolumes.setFont(FONT_STATS_NAME);
        this.lbTotalVolumes.setForeground(COLOR_STATS_NAME);
        this.pVolumeCounter.add(this.lbTotalVolumes,gbcData);

        gbcCounter.gridx = 0;
        gbcCounter.gridy = 1;
        this.lbCounterTotalVolumes.setText(counterTotalVolumes);
        this.lbCounterTotalVolumes.setFont(FONT_STATS_VALUE);
        this.lbCounterTotalVolumes.setForeground(COLOR_STATS_VALUE);
        this.pVolumeCounter.add(lbCounterTotalVolumes,gbcCounter);

        //Counter - Owned
        gbcCounter.gridx = 0;
        gbcCounter.gridy = 0;
        this.lbOwned.setFont(FONT_STATS_NAME);
        this.lbOwned.setForeground(COLOR_STATS_NAME);
        this.pOwnedCounter.add(this.lbOwned,gbcCounter);

        gbcCounter.gridx = 0;
        gbcCounter.gridy = 1;
        this.lbCounterOwned.setText(counterOwned);
        this.lbCounterOwned.setFont(FONT_STATS_VALUE);
        this.lbCounterOwned.setForeground(COLOR_STATS_VALUE);
        this.pOwnedCounter.add(lbCounterOwned,gbcCounter);

        //Counter - Collection Status
        gbcCounter.gridx = 0;
        gbcCounter.gridy = 0;
        this.lbCollectionStatus.setFont(FONT_STATS_NAME);
        this.lbCollectionStatus.setForeground(COLOR_STATS_NAME);
        this.pCollectionCounter.add(this.lbCollectionStatus,gbcCounter);

        gbcCounter.gridx = 0;
        gbcCounter.gridy = 1;
        this.lbCounterCollectionStatus.setText(counterCollectionStatus);
        this.lbCounterCollectionStatus.setFont(FONT_STATS_VALUE);
        this.lbCounterCollectionStatus.setForeground(COLOR_STATS_VALUE);
        this.pCollectionCounter.add(this.lbCounterCollectionStatus,gbcCounter);

        //Counter - Publication Status
        gbcCounter.gridx = 0;
        gbcCounter.gridy = 0;
        this.lbPublicationStatus.setFont(FONT_STATS_NAME);
        this.lbPublicationStatus.setForeground(COLOR_STATS_NAME);
        this.pPublicationCounter.add(lbPublicationStatus,gbcCounter);

        gbcCounter.gridx = 0;
        gbcCounter.gridy = 1;
        this.lbCounterPublicationStatus.setText(counterPublicationStatus);
        this.lbCounterPublicationStatus.setFont(FONT_STATS_VALUE);
        this.lbCounterPublicationStatus.setForeground(COLOR_STATS_VALUE);
        this.pPublicationCounter.add(this.lbCounterPublicationStatus,gbcCounter);

        //Counters Panel
        this.pCounters.setBackground(DATA_BG);
        this.pVolumeCounter.setBorder(new LineBorder(STAT_CARDS_BORDER,1));
        this.pVolumeCounter.setBackground(STAT_CARDS_BG);
        this.pCounters.add(pVolumeCounter);

        this.pOwnedCounter.setBorder(new LineBorder(STAT_CARDS_BORDER,1));
        this.pOwnedCounter.setBackground(STAT_CARDS_BG);
        this.pCounters.add(this.pOwnedCounter);

        this.pCollectionCounter.setBorder(new LineBorder(STAT_CARDS_BORDER,1));
        this.pCollectionCounter.setBackground(STAT_CARDS_BG);
        this.pCounters.add(pCollectionCounter);

        this.pPublicationCounter.setBorder(new LineBorder(STAT_CARDS_BORDER,1));
        this.pPublicationCounter.setBackground(STAT_CARDS_BG);
        this.pCounters.add(this.pPublicationCounter);



        //Data Panel
        gbcData.insets = new Insets(7,7,7,7);
        gbcData.gridx = 0;
        gbcData.gridy  = 0;
        gbcData.weightx = 1.0;
        gbcData.weighty = 1.0;
        gbcData.fill = GridBagConstraints.BOTH;

        this.lbCollectionTittle.setText(tittleString);
        this.lbCollectionTittle.setFont(FONT_TITLE);
        this.lbCollectionTittle.setForeground(COLOR_COLLECTION_TITLE);
        this.pData.add(this.lbCollectionTittle, gbcData);

        gbcData.gridy  = 1;
        this.pData.add(this.pCounters, gbcData);

        this.pData.setBorder(new LineBorder(DATA_BORDER,2));
        this.pData.setBackground(DATA_BG);

        //Main Frame
        JScrollPane scrollPane = new JScrollPane(bookTable(this.collectionId));
        this.getContentPane().setBackground(MAIN_BG);
        this.add(pData,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(scrollPane,gbc);


        this.setVisible(true);

    }

    /**
     * Method that creates a table of the books that are from the collection
     * @param id - Identification of the collection
     * @return tblBooks - JTable with every data of the booksthat belong to this collection
     * @throws DBException - Exception related to the DataBase
     */
    public JTable bookTable(int id) throws DBException {

        String[] column = {"ID","Vol #","Editorial","Language","Status","Collection"};
        DefaultTableModel model = new DefaultTableModel(column,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Libro> books = LibroDAO.listBooksByCollection(id);
        for(Libro b : books){

            model.addRow(new Object[]{
                    b.getIdBook(),
                    b.getNumeroVolumen(),
                    b.getEditorial(),
                    b.getLenguaje(),
                    b.getEstadoLibro(),
                    b.getColeccion()
            });
        }

        JTable tblBooks = new JTable();
        JTableHeader header = tblBooks.getTableHeader();
        header.setBackground(new Color(246, 212, 224));
        tblBooks.setModel(model);
        tblBooks.setCellSelectionEnabled(false);
        tblBooks.setRowSelectionAllowed(true);
        tblBooks.setFont(new Font("SansSerif",Font.PLAIN,14));


        return tblBooks;

    }
}
