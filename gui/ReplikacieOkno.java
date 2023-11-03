/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import OSPABA.ISimDelegate;
import javax.swing.table.DefaultTableModel;
import simulation.MySimulation;

/**
 *
 * @author Jožko
 */
public class ReplikacieOkno extends javax.swing.JFrame {

    /**
     * Creates new form ReplikacieOkno
     */
    public ReplikacieOkno() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pocetRec = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pocetKad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        pocetKoz = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pocetRep = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabulkaVysledkov = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        pocetParkovacichRadov = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Rec:");

        jLabel2.setText("Kad:");

        jLabel3.setText("Koz:");

        jLabel4.setText("Rep:");

        tabulkaVysledkov.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Vyťaženie recepčných [%]", "0", "0"},
                {"Vyťaženie kaderníčok [%]", "0", "0"},
                {"Vyťaženie kozmetíčok [%]", "0", "0"},
                {"Čas v systéme", "0", "0"},
                {"Čas v rade - recepčné objednávka", "0", "0"},
                {"Čas v rade - recepčné platba", "0", "0"},
                {"Čas v rade - kaderníčky", "0", "0"},
                {"Čas v rade - kozmetičky", "0", "0"},
                {"Dĺžka radu - recepčné objednávka", "0", "0"},
                {"Dĺžka radu - recepčné platba", "0", "0"},
                {"Dĺžka radu - kaderníčky", "0", "0"},
                {"Dĺžka radu - kozmetičky", "0", "0"},
                {"Čas obsluhy - recepčné", "0", "0"},
                {"Čas obsluhy - kaderníčky", "0", "0"},
                {"Čas obsluhy - kozmetičky", "0", "0"},
                {"Otvorené naviac", "0", null},
                {"Počet vyhodených z radu recepčné", "0", null},
                {"Spokojnosť", "0", null},
                {"Úspešnosť", "0", null}
            },
            new String [] {
                "Štatistika", "Hodnota", "Hodnota bez"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabulkaVysledkov);

        jButton1.setText("Štart");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Menu");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Park:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pocetRec, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pocetKad, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pocetKoz, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pocetParkovacichRadov, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pocetRep, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(pocetRec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(pocetKad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(pocetKoz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(pocetRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jLabel5)
                    .addComponent(pocetParkovacichRadov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        MySimulation salon;                  
        salon = new MySimulation(Integer.parseInt(pocetRec.getText()), Integer.parseInt(pocetKad.getText()), Integer.parseInt(pocetKoz.getText()), Integer.parseInt(pocetParkovacichRadov.getText()));
        salon.simulate(Integer.parseInt(pocetRep.getText()));
        
        DefaultTableModel modelVysl = (DefaultTableModel) tabulkaVysledkov.getModel();
        
        // aktualizacia tabulky priemernymi vyslednymi hodnotami z replikacii
        modelVysl.setValueAt(String.format("%.2f", salon.priemerneVytazenieRecepcneCELE().dajPriemer()), 0, 1);
        modelVysl.setValueAt(String.format("%.2f", salon.priemerneVytazenieKadernickyCELE().dajPriemer()), 1, 1);
        modelVysl.setValueAt(String.format("%.2f", salon.priemerneVytazenieKozmetickyCELE().dajPriemer()), 2, 1);       
        modelVysl.setValueAt("< " + salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernyCasZakaznikaVSysteme().dajLavostrannyInterval90()) + " ; " + salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernyCasZakaznikaVSysteme().dajPriemer()) + " ; " + salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernyCasZakaznikaVSysteme().dajPravostrannyInterval90()) + " >", 3, 1);
        modelVysl.setValueAt(salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernyCasRadRecepcneObjednavkaCELE().dajPriemer()), 4, 1);
        modelVysl.setValueAt(salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernyCasRadRecepcnePlatbaCELE().dajPriemer()), 5, 1);
        modelVysl.setValueAt(salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernyCasRadKadernickyCELE().dajPriemer()), 6, 1);
        modelVysl.setValueAt(salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernyCasRadKozmetickyCELE().dajPriemer()), 7, 1);       
        modelVysl.setValueAt(String.format("%.2f", salon.priemernaDlzkaRaduRecepcneObjednavka().dajPriemer()), 8, 1);
        modelVysl.setValueAt(String.format("%.2f", salon.priemernaDlzkaRaduRecepcnePlatba().dajPriemer()), 9, 1);
        modelVysl.setValueAt(String.format("%.2f", salon.priemernaDlzkaRaduKadernicky().dajPriemer()), 10, 1);
        modelVysl.setValueAt(String.format("%.2f", salon.priemernaDlzkaRaduKozmeticky().dajPriemer()), 11, 1);
        modelVysl.setValueAt(salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernaDobaObsluhyRecepcne().dajPriemer()), 12, 1);
        modelVysl.setValueAt(salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernaDobaObsluhyKadernicky().dajPriemer()), 13, 1);
        modelVysl.setValueAt(salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernaDobaObsluhyKozmeticky().dajPriemer()), 14, 1);
        modelVysl.setValueAt(salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernaDobaOtvoreniaNaviac().dajPriemer()), 15, 1);
        modelVysl.setValueAt(String.format("%.2f", salon.priemernyPocetVyhodenychLudi().dajPriemer()), 16, 1);
        modelVysl.setValueAt(String.format("%.2f", salon.priemernaSpokojnost().dajPriemer()), 17, 1);
        modelVysl.setValueAt(String.format("%.2f", salon.priemernaUspesnostParkovania().dajPriemer()), 18, 1);
                
        // aktualizacia tabulky priemernymi vyslednymi hodnotami z replikacii - statistiky do 17:00
        modelVysl.setValueAt(String.format("%.2f", salon.priemerneVytazenieRecepcne17().dajPriemer()), 0, 2);
        modelVysl.setValueAt(String.format("%.2f", salon.priemerneVytazenieKadernicky17().dajPriemer()), 1, 2);
        modelVysl.setValueAt(String.format("%.2f", salon.priemerneVytazenieKozmeticky17().dajPriemer()), 2, 2);
        modelVysl.setValueAt("< " + salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernyCasZakaznikaVSysteme17().dajLavostrannyInterval90()) + " ; " + salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernyCasZakaznikaVSysteme17().dajPriemer()) + " ; " + salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernyCasZakaznikaVSysteme17().dajPravostrannyInterval90()) + " >", 3, 2);
        modelVysl.setValueAt(salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernyCasRadRecepcneObjednavka17().dajPriemer()), 4, 2);
        modelVysl.setValueAt(salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernyCasRadRecepcnePlatba17().dajPriemer()), 5, 2);
        modelVysl.setValueAt(salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernyCasRadKadernicky17().dajPriemer()), 6, 2);
        modelVysl.setValueAt(salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernyCasRadKozmeticky17().dajPriemer()), 7, 2);
        modelVysl.setValueAt(String.format("%.2f", salon.priemernaDlzkaRaduRecepcneObjednavka17().dajPriemer()), 8, 2);
        modelVysl.setValueAt(String.format("%.2f", salon.priemernaDlzkaRaduRecepcnePlatba17().dajPriemer()), 9, 2);
        modelVysl.setValueAt(String.format("%.2f", salon.priemernaDlzkaRaduKadernicky17().dajPriemer()), 10, 2);
        modelVysl.setValueAt(String.format("%.2f", salon.priemernaDlzkaRaduKozmeticky17().dajPriemer()), 11, 2);
        modelVysl.setValueAt(salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernaDobaObsluhyRecepcne17().dajPriemer()), 12, 2);
        modelVysl.setValueAt(salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernaDobaObsluhyKadernicky17().dajPriemer()), 13, 2);
        modelVysl.setValueAt(salon.agentSalonu().dajFormatovanySimulacnyCas(salon.priemernaDobaObsluhyKozmeticky17().dajPriemer()), 14, 2);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new Menu().setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReplikacieOkno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReplikacieOkno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReplikacieOkno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReplikacieOkno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReplikacieOkno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField pocetKad;
    private javax.swing.JTextField pocetKoz;
    private javax.swing.JTextField pocetParkovacichRadov;
    private javax.swing.JTextField pocetRec;
    private javax.swing.JTextField pocetRep;
    private javax.swing.JTable tabulkaVysledkov;
    // End of variables declaration//GEN-END:variables
}
