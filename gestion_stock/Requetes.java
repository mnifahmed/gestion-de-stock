package gestion_stock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;

public class Requetes extends JFrame {
	
	ResultSet rst;
    Statement st;
    Connexion conn = new Connexion();
    JLabel lab1, lab2, lab3, lab4;
    JButton bt1, bt2, bt3, bt4, bt5;
    JTable tb, tb2, tb3, tb4;
    JScrollPane sp, sp2, sp3, sp4;
    JComboBox<String> jcb1, jcb2, jcb3, jcb4;

    public Requetes() {
    	
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Requetes");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(173, 216, 230));
        add(mainPanel);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 4));
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 5));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 2));
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        centerPanel.add(leftPanel);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        centerPanel.add(rightPanel);

        // creation des composants
        lab1 = new JLabel("Restes en Stock");
        lab2 = new JLabel("Historique des depots et des retraits");
        lab3 = new JLabel("Montant total des produits en stock:");
        lab4 = new JLabel("Montant");
        lab4.setForeground(Color.red);
        lab4.setFont(new Font("Arial", Font.BOLD, 15));

        topPanel.add(lab1);
        topPanel.add(new JPanel());
        topPanel.add(new JPanel());
        topPanel.add(lab2);
        topPanel.add(new JPanel());
        topPanel.add(new JPanel());
        topPanel.add(new JPanel());
        topPanel.add(new JPanel());

        jcb1 = new JComboBox<String>();
        jcb1.addItem("Depot");
        jcb1.addItem("Retrait");

        jcb2 = new JComboBox<String>();
        jcb2.addItem("Depot");
        jcb2.addItem("Retrait");

        jcb4 = new JComboBox<String>();
        jcb4.addItem("Pepsi");
        jcb4.addItem("Savon");
        jcb4.addItem("Spaghetti");
        jcb4.addItem("Nescafe");
        jcb4.addItem("Doritos");

        jcb3 = new JComboBox<String>();
        jcb3.addItem("01-04-2023");
        jcb3.addItem("02-04-2023");
        jcb3.addItem("03-04-2023");
        jcb3.addItem("04-04-2023");
        jcb3.addItem("05-04-2023");
        jcb3.addItem("06-04-2023");
        jcb3.addItem("07-04-2023");
        jcb3.addItem("08-04-2023");
        jcb3.addItem("09-04-2023");
        jcb3.addItem("10-04-2023");
        jcb3.addItem("11-04-2023");
        jcb3.addItem("12-04-2023");
        jcb3.addItem("13-04-2023");
        jcb3.addItem("14-04-2023");
        jcb3.addItem("15-04-2023");
        jcb3.addItem("16-04-2023");
        jcb3.addItem("17-04-2023");
        jcb3.addItem("18-04-2023");
        jcb3.addItem("19-04-2023");
        jcb3.addItem("20-04-2023");
        jcb3.addItem("21-04-2023");
        jcb3.addItem("22-04-2023");
        jcb3.addItem("23-04-2023");
        jcb3.addItem("24-04-2023");
        jcb3.addItem("25-04-2023");
        jcb3.addItem("26-04-2023");
        jcb3.addItem("27-04-2023");
        jcb3.addItem("28-04-2023");
        jcb3.addItem("29-04-2023");
        jcb3.addItem("30-04-2023");
        jcb3.setFont(new Font("Arial", Font.BOLD, 12));

        bt1 = new JButton("OK");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (e.getSource() == bt1) {
                    DefaultTableModel df2 = new DefaultTableModel();
                    df2.addColumn("Code du produit");
                    df2.addColumn("Quantite");
                    df2.addColumn("Date");
                    tb2.setModel(df2);
                    String a;
                    a = jcb1.getSelectedItem().toString();
                    String qrr = "select codeprd,quantite,date from mouvmt where nature='" + a + "' order by codeprd";
                    try {
                        st = conn.connexion().createStatement();
                        rst = st.executeQuery(qrr);
                        while (rst.next()) {
                            df2.addRow(new Object[] {
                                rst.getString("codeprd"), rst.getString("quantite"), rst.getString("date")
                            });
                        }
                    } catch (SQLException ex) {

                    }
                }
            }
        });
        bt2 = new JButton("OK");
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (e.getSource() == bt2) {
                    DefaultTableModel df3 = new DefaultTableModel();
                    df3.addColumn("Code du produit");
                    df3.addColumn("Quantite");
                    df3.addColumn("Date");
                    tb3.setModel(df3);
                    String a, b;
                    a = jcb2.getSelectedItem().toString();
                    b = jcb3.getSelectedItem().toString();
                    String qrr = "select codeprd,quantite,date from mouvmt where nature='" + a + "' and date='" + b + "' order by codeprd";
                    try {
                        st = conn.connexion().createStatement();
                        rst = st.executeQuery(qrr);
                        while (rst.next()) {
                            df3.addRow(new Object[] {
                                rst.getString("codeprd"), rst.getString("quantite"), rst.getString("date")
                            });
                        }
                    } catch (SQLException ex) {

                    }
                }
            }
        });
        bt3 = new JButton("OK");
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (e.getSource() == bt3) {
                    DefaultTableModel df4 = new DefaultTableModel();
                    df4.addColumn("Code du produit");
                    df4.addColumn("Nom du produit");
                    df4.addColumn("Prix unitaire");
                    df4.addColumn("Quantite en stock ");
                    df4.addColumn("Montant Total");
                    tb4.setModel(df4);
                    String a;
                    a = jcb4.getSelectedItem().toString();
                    String qr = "select codeprod,nomprod,prix,sum(qte) as stock,sum(qte)*prix as montant from vue1 where nomprod='" + a + "' " +
                        " group by codeprod";
                    try {
                        st = conn.connexion().createStatement();
                        rst = st.executeQuery(qr);
                        if (rst.next()) {
                            df4.addRow(new Object[] {
                                rst.getString("codeprod"),
                                    rst.getString("nomprod"),
                                    rst.getString("prix"),
                                    rst.getString("stock"),
                                    rst.getString("montant")
                            });
                        } else
                            JOptionPane.showMessageDialog(null, "Introuvable!", null, JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException ex) {

                    }
                }
            }
        });
        bt4 = new JButton("Mouvement");
        bt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (e.getSource() == bt4) {
                    dispose();
                    Mouvement m = new Mouvement();
                    m.setVisible(true);
                }
            }
        });
        bt5 = new JButton("Produit");
        bt5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (e.getSource() == bt5) {
                    dispose();
                    Produit fm = new Produit();
                    fm.setVisible(true);
                }
            }
        });

        bottomPanel.add(new JPanel());
        bottomPanel.add(jcb4);
        bottomPanel.add(bt3);
        bottomPanel.add(bt4);
        bottomPanel.add(bt5);
        bottomPanel.add(new JPanel());
        bottomPanel.add(new JPanel());
        bottomPanel.add(jcb1);
        bottomPanel.add(bt1);
        bottomPanel.add(jcb2);
        bottomPanel.add(jcb3);
        bottomPanel.add(bt2);
        

        tb = new JTable();
        sp = new JScrollPane(tb);
        leftPanel.add(sp, BorderLayout.CENTER);
        JPanel labPanel = new JPanel();
        labPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        labPanel.add(lab3);
        labPanel.add(lab4);
        leftPanel.add(labPanel, BorderLayout.SOUTH);

        tb2 = new JTable();
        sp2 = new JScrollPane(tb2);
        rightPanel.add(sp2, BorderLayout.NORTH);

        tb3 = new JTable();
        sp3 = new JScrollPane(tb3);
        rightPanel.add(sp3, BorderLayout.CENTER);

        tb4 = new JTable();
        sp4 = new JScrollPane(tb4);
        leftPanel.add(sp4, BorderLayout.NORTH);
        DefaultTableModel df = new DefaultTableModel();
        df.addColumn("Code du produit");
        df.addColumn("Nom du produit");
        df.addColumn("Prix unitaire");
        df.addColumn("Quantite en stock ");
        df.addColumn("Montant Total");
        tb.setModel(df);
        
        DefaultTableModel df2 = new DefaultTableModel();
        df2.addColumn("Code du produit");
        df2.addColumn("Quantite");
        df2.addColumn("Date");
        tb2.setModel(df2);

        DefaultTableModel df3 = new DefaultTableModel();
        df3.addColumn("Code du produit");
        df3.addColumn("Quantite");
        df3.addColumn("Date");
        tb3.setModel(df3);

        DefaultTableModel df4 = new DefaultTableModel();
        df4.addColumn("Code du produit");
        df4.addColumn("Nom du produit");
        df4.addColumn("Prix unitaire");
        df4.addColumn("Quantite en stock ");
        df4.addColumn("Montant Total");
        tb4.setModel(df4);
        String qr = "select codeprod,nomprod,prix,sum(qte) as stock,sum(qte)*prix as montant from vue1 group by codeprod";
        try {
            st = conn.connexion().createStatement();
            rst = st.executeQuery(qr);
            while (rst.next()) {
                df.addRow(new Object[] {
                    rst.getString("codeprod"),
                        rst.getString("nomprod"),
                        rst.getString("prix"),
                        rst.getString("stock"),
                        rst.getString("montant")
                });
            }
        } catch (SQLException ex) {
        	
        }
        
        String qery = "select sum(prix*qte) as total from vue1";
        try {
            st = conn.connexion().createStatement();
            rst = st.executeQuery(qery);
            if (rst.next()) {
                lab4.setText(rst.getString("total"));
            }
        } catch (SQLException ex) {

        }
    }
}