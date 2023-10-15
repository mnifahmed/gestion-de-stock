package gestion_stock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;

public class Produit extends JFrame {

	JLabel lb1, lb2, lb3, lb4;
    JTextField jf4, jf5;
    JRadioButton jr1, jr2;
    JButton bt1, bt2, bt3, bt4, bt5, bt6;
    JComboBox<String> jc1, jc2;
    JTable tb;
    JScrollPane sp;
    Statement st;
    ResultSet rst;
    Connexion conn = new Connexion();


    public Produit() {

    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Produit");
        this.setSize(800, 400);
        this.setLocationRelativeTo(null);

        JPanel jp = new JPanel(new BorderLayout(10, 10));
        jp.setBackground(new Color(173, 216, 230));
        add(jp);

        // North panel
        JPanel northPanel = new JPanel();
        jp.add(northPanel, BorderLayout.NORTH);

        lb1 = new JLabel("Interface de gestion des produits");
        lb1.setFont(new Font("Arial", Font.BOLD, 15));
        northPanel.add(lb1);

        // Center panel
        JPanel centerPanel = new JPanel(new GridLayout(4, 2));
        jp.add(centerPanel, BorderLayout.CENTER);

        // codeprod
        lb2 = new JLabel("Code Produit");
        lb2.setFont(new Font("Arial", Font.BOLD, 12));
        centerPanel.add(lb2);

        jc1 = new JComboBox<String>();
        jc1.addItem("PEP");
        jc1.addItem("SAV");
        jc1.addItem("SPG");
        jc1.addItem("NES");
        jc1.addItem("DOR");
        jc1.setFont(new Font("Arial", Font.BOLD, 12));
        centerPanel.add(jc1);

        // nomprod
        lb3 = new JLabel("Nom Produit");
        lb3.setFont(new Font("Arial", Font.BOLD, 12));
        centerPanel.add(lb3);

        jc2 = new JComboBox<String>();
        jc2.addItem("Pepsi");
        jc2.addItem("Savon");
        jc2.addItem("Spaghetti");
        jc2.addItem("Nescafe");
        jc2.addItem("Doritos");
        jc2.setFont(new Font("Arial", Font.BOLD, 12));
        centerPanel.add(jc2);

        // prix
        lb4 = new JLabel("Prix unitaire");
        lb4.setFont(new Font("Arial", Font.BOLD, 12));
        centerPanel.add(lb4);

        jf4 = new JTextField();
        jf4.setFont(new Font("Arial", Font.BOLD, 12));
        centerPanel.add(jf4);

        // recherche
        jf5 = new JTextField();
        jf5.setText("Code Produit");
        jf5.setFont(new Font("Arial", Font.BOLD, 12));
        jf5.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jf5.getText().equals("Code Produit")) {
                	jf5.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jf5.getText().isEmpty()) {
                	jf5.setText("Code Produit");
                }
            }
        });
        centerPanel.add(jf5);

        bt1 = new JButton("Rechercher");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//recherche
                if (e.getSource() == bt1) {
                    String a;
                    a = jf5.getText();
                    String qr = "select * from produit where codeprod='" + a + "'";
                    try {
                        st = conn.connexion().createStatement();
                        rst = st.executeQuery(qr);
                        if (rst.next()) {
                            jc1.setSelectedItem(rst.getString("codeprod"));
                            jc2.setSelectedItem(rst.getString("nomprod"));
                            jf4.setText(rst.getString("prix"));
                        } else
                            JOptionPane.showMessageDialog(null, "Code produit introuvable!", null, JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException ex) {

                    }
                }
            }
        });
        centerPanel.add(bt1);

        // South panel
        JPanel southPanel = new JPanel(new GridLayout(2, 2));
        jp.add(southPanel, BorderLayout.SOUTH);

        // insertion
        bt2 = new JButton("Inserer");
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//insertion
                if (e.getSource() == bt2) {
                    String a, b, c;
                    a = jc1.getSelectedItem().toString();
                    b = jc2.getSelectedItem().toString();
                    c = jf4.getText();
                    String qr = "insert into produit values('" + a + "','" + b + "','" + c + "')";
                    try {
                        st = conn.connexion().createStatement();
                        if (JOptionPane.showConfirmDialog(null, "Voulez-Vous ajouter ce produit?", null, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                            st.executeUpdate(qr);
                            JOptionPane.showMessageDialog(null, "Insertion effectuee!");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Insertion impossible!");
                    }
                    dispose();
                    Produit fges = new Produit();
                    fges.setVisible(true);
                }
            }
        });
        southPanel.add(bt2);

        // suppression
        bt3 = new JButton("Supprimer");
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//suppression
                if (e.getSource() == bt3) {
                    String a;
                    a = jc1.getSelectedItem().toString();
                    String qr = "delete from produit where codeprod='" + a + "'";
                    try {
                        st = conn.connexion().createStatement();
                        if (JOptionPane.showConfirmDialog(null, "Voulez-Vous supprimer ce produit?", null, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                            st.executeUpdate(qr);
                            JOptionPane.showMessageDialog(null, "Suppression effectuee!");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Suppression impossible!");
                    }
                    dispose();
                    Produit fges = new Produit();
                    fges.setVisible(true);
                }
            }
        });
        southPanel.add(bt3);

        // modification
        bt4 = new JButton("Modifier");
        bt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//modification
                if (e.getSource() == bt4) {
                    String a, b, c;
                    a = jc1.getSelectedItem().toString();
                    b = jc2.getSelectedItem().toString();
                    c = jf4.getText();
                    String qr = "update produit set nomprod='" + b + "',prix='" + c + "' where codeprod='" + a + "'";
                    try {
                        st = conn.connexion().createStatement();
                        if (JOptionPane.showConfirmDialog(null, "Voulez-Vous modifier ce produit?", null, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                            st.executeUpdate(qr);
                            JOptionPane.showMessageDialog(null, "Modification effectuee!");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Modification impossible!");
                    }
                    dispose();
                    Produit fges = new Produit();
                    fges.setVisible(true);
                }
            }
        });
        southPanel.add(bt4);

        // mouvement
        bt5 = new JButton("Mouvement");
        bt5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//bt5 mvmt
                if (e.getSource() == bt5) {
                    dispose();
                    Mouvement fg = new Mouvement();
                    fg.setVisible(true);
                }
            }
        });
        southPanel.add(bt5);

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
        jp.add(bt6, BorderLayout.EAST);

        // Table
        DefaultTableModel df = new DefaultTableModel();
        init();
        df.addColumn("Code Produit");
        df.addColumn("Nom Produit");
        df.addColumn("Prix unitaire");
        tb.setModel(df);
        jp.add(sp, BorderLayout.WEST);

        String qry2 = "select * from produit";
        try {
            st = conn.connexion().createStatement();
            rst = st.executeQuery(qry2);
            while (rst.next()) {
                df.addRow(new Object[] {
                    rst.getString("codeprod"), rst.getString("nomprod"), rst.getString("prix")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void init() {
        tb = new JTable();
        sp = new JScrollPane(tb);
        sp.setViewportView(tb);
    }
}