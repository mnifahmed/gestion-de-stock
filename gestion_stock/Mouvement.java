package gestion_stock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;

public class Mouvement extends JFrame {

    JLabel lb1, lb2, lb3, lb4, lb6, lb7;
    JTextField jf4, jf2, jf5, jf6;
    JRadioButton jr1, jr2;
    JButton bt1, bt2, bt3, bt4, bt5, bt6;
    JComboBox<String> jcb, jcb2;
    JTable tb;
    JScrollPane sp;
    Statement st;
    ResultSet rst;
    Connexion conn = new Connexion();

    public Mouvement() {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Mouvement");
        this.setSize(800, 450);
        this.setLocationRelativeTo(null);

        JPanel jp = new JPanel(new BorderLayout());
        jp.setBackground(new Color(173, 216, 230));
        add(jp);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(173, 216, 230));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp.add(headerPanel, BorderLayout.NORTH);

        lb1 = new JLabel("Interface de gestion des mouvements");
        lb1.setFont(new Font("Arial", Font.BOLD, 15));
        headerPanel.add(lb1);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.setBackground(new Color(173, 216, 230));
        jp.add(formPanel, BorderLayout.CENTER);

        // id
        lb2 = new JLabel("Identifiant");
        lb2.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(lb2);
        jf2 = new JTextField();
        jf2.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(jf2);

        // codeprod
        lb3 = new JLabel("Code Produit");
        lb3.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(lb3);
        jcb2 = new JComboBox<String>(new String[]{"PEP", "SAV", "SPG", "NES", "DOR"});
        jcb2.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(jcb2);

        // quantite
        lb4 = new JLabel("Quantite");
        lb4.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(lb4);
        jf4 = new JTextField();
        jf4.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(jf4);

        // date
        lb7 = new JLabel("Date");
        lb7.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(lb7);
        jcb = new JComboBox<String>();
        jcb.addItem("01-04-2023");
        jcb.addItem("02-04-2023");
        jcb.addItem("03-04-2023");
        jcb.addItem("04-04-2023");
        jcb.addItem("05-04-2023");
        jcb.addItem("06-04-2023");
        jcb.addItem("07-04-2023");
        jcb.addItem("08-04-2023");
        jcb.addItem("09-04-2023");
        jcb.addItem("10-04-2023");
        jcb.addItem("11-04-2023");
        jcb.addItem("12-04-2023");
        jcb.addItem("13-04-2023");
        jcb.addItem("14-04-2023");
        jcb.addItem("15-04-2023");
        jcb.addItem("16-04-2023");
        jcb.addItem("17-04-2023");
        jcb.addItem("18-04-2023");
        jcb.addItem("19-04-2023");
        jcb.addItem("20-04-2023");
        jcb.addItem("21-04-2023");
        jcb.addItem("22-04-2023");
        jcb.addItem("23-04-2023");
        jcb.addItem("24-04-2023");
        jcb.addItem("25-04-2023");
        jcb.addItem("26-04-2023");
        jcb.addItem("27-04-2023");
        jcb.addItem("28-04-2023");
        jcb.addItem("29-04-2023");
        jcb.addItem("30-04-2023");
        jcb.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(jcb);

        // nature
        lb6 = new JLabel("Nature");
        lb6.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(lb6);

        // bouton radio
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.setBackground(new Color(173, 216, 230));
        jr1 = new JRadioButton("Depot");
        jr1.setBackground(new Color(173, 216, 230));
        radioPanel.add(jr1);
        jr2 = new JRadioButton("Retrait");
        jr2.setBackground(new Color(173, 216, 230));
        radioPanel.add(jr2);
        ButtonGroup bg = new ButtonGroup();
        bg.add(jr1);
        bg.add(jr2);
        formPanel.add(radioPanel);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(173, 216, 230));
        jp.add(bottomPanel, BorderLayout.SOUTH);

        // recherche
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(new Color(173, 216, 230));
        jf5 = new JTextField("Identifiant");
        jf5.setFont(new Font("Arial", Font.BOLD, 12));
        jf5.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jf5.getText().equals("Identifiant")) {
                    jf5.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jf5.getText().isEmpty()) {
                    jf5.setText("Identifiant");
                }
            }
        });
        searchPanel.add(jf5);
        bt1 = new JButton("Rechercher");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//recherche
                if (e.getSource() == bt1) {
                    String a;
                    a = jf5.getText();
                    String qr = "select * from mouvmt where idmv='" + a + "' ";
                    try {
                        st = conn.connexion().createStatement();
                        rst = st.executeQuery(qr);
                        if (rst.next()) {
                            jf2.setText(rst.getString("idmv"));
                            jcb2.setSelectedItem(rst.getString("codeprd"));
                            jf4.setText(rst.getString("quantite"));
                            jcb.setSelectedItem(rst.getString("date"));
                            if (rst.getString("nature").equals("Depot"))
                                jr1.setSelected(true);
                            else
                                jr2.setSelected(true);
                        } else
                            JOptionPane.showMessageDialog(null, "identifiant introuvable!", null, JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException ex) {
                    	
                    }
                }
            }
        });
        searchPanel.add(bt1);
        bottomPanel.add(searchPanel);

        // bouton
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(new Color(173, 216, 230));
        // insertion
        bt2 = new JButton("Inserer");
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//insertion
                if (e.getSource() == bt2) {
                    String a, b, c, d, f;
                    a = jf2.getText();
                    b = jcb2.getSelectedItem().toString();
                    c = jf4.getText();
                    d = jcb.getSelectedItem().toString();
                    if (jr1.isSelected())
                        f = jr1.getText();
                    else
                        f = jr2.getText();
                    String qr = "insert into mouvmt values('" + a + "','" + b + "','" + c + "','" + f + "','" + d + "') ";
                    try {
                        st = conn.connexion().createStatement();
                        if (JOptionPane.showConfirmDialog(null, "Voulez-Vous ajouter ce mouvement?", null, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                            st.executeUpdate(qr);
                            JOptionPane.showMessageDialog(null, "Insertion effectuee!");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Insertion impossible!", null, JOptionPane.ERROR_MESSAGE);
                    }
                    dispose();
                    Mouvement fges = new Mouvement();
                    fges.setVisible(true);
                }
            }
        });
        buttonPanel.add(bt2);
        // modification
        bt4 = new JButton("Modifier");
bt4 = new JButton("Modifier");
        
        bt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//modification
                if (e.getSource() == bt4) {
                    String a, b, c, d, f;
                    a = jf2.getText();
                    b = jcb2.getSelectedItem().toString();
                    c = jf4.getText();
                    d = jcb.getSelectedItem().toString();
                    if (jr1.isSelected())
                        f = jr1.getText();
                    else
                        f = jr2.getText();
                    String qr = "update mouvmt set codeprd='" + b + "',quantite='" + c + "',nature='" + f + "',date='" + d + "'" +
                        "where idmv='" + a + "' ";
                    try {
                        st = conn.connexion().createStatement();
                        if (JOptionPane.showConfirmDialog(null, "Voulez-Vous modifier ce mouvement?", null, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                            st.executeUpdate(qr);
                            JOptionPane.showMessageDialog(null, "Modification effectuee!");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Modification impossible!", null, JOptionPane.ERROR_MESSAGE);
                    }
                    dispose();
                    Mouvement fges = new Mouvement();
                    fges.setVisible(true);
                }
            }
        });
        buttonPanel.add(bt4);
        // suppression
        bt3 = new JButton("Supprimer");
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//suppression
                if (e.getSource() == bt3) {
                    String a;
                    a = jf2.getText();
                    String qr = "delete from mouvmt where idmv='" + a + "' ";
                    try {
                        st = conn.connexion().createStatement();
                        if (JOptionPane.showConfirmDialog(null, "Voulez-Vous supprimer ce mouvement?", null, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                            st.executeUpdate(qr);
                            JOptionPane.showMessageDialog(null, "Suppression effectuee!");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Suppression impossible!");
                    }
                    dispose();
                    Mouvement fges = new Mouvement();
                    fges.setVisible(true);
                }
            }
        });
        buttonPanel.add(bt3);
        // mouvement
        bt5 = new JButton("Produit");
        bt5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//bt5 produit
                if (e.getSource() == bt5) {
                    dispose();
                    Produit fg = new Produit();
                    fg.setVisible(true);
                }
            }
        });
        buttonPanel.add(bt5);
        bottomPanel.add(buttonPanel);
        // requetes
        bt6 = new JButton("Requetes");
        bt6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//bt6 reqt
                if (e.getSource() == bt6) {
                    dispose();
                    Requetes fg = new Requetes();
                    fg.setVisible(true);
                }
            }
        });
        buttonPanel.add(bt6);

        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(173, 216, 230));
        jp.add(tablePanel, BorderLayout.EAST);

        DefaultTableModel df = new DefaultTableModel();
        init();
        df.addColumn("Identifiant");
        df.addColumn("Code Produit");
        df.addColumn("Quantite");
        df.addColumn("Nature");
        df.addColumn("Date");
        tb.setModel(df);
        tablePanel.add(sp, BorderLayout.CENTER);

        String qry2 = "select * from mouvmt";
        try {
            st = conn.connexion().createStatement();
            rst = st.executeQuery(qry2);
            while (rst.next()) {
                df.addRow(new Object[] {
                    rst.getString("idmv"), rst.getString("codeprd"), rst.getString("quantite"),
                        rst.getString("nature"), rst.getString("date")
                });
            }
        } catch (SQLException ex) {

        }
    }

    public void init() {
        tb = new JTable();
        sp = new JScrollPane(tb);
    }

}