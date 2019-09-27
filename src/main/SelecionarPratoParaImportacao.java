package main;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.DefaultTableModel;
import model.bean.Cardapio;
import model.bean.CategoriaPrato;
import model.bean.Prato;
import model.dao.CardapioDAO;
import model.dao.CategoriaPratoDAO;
import model.dao.ItemCardapioDAO;
import model.dao.PratoDAO;

public class SelecionarPratoParaImportacao extends javax.swing.JDialog {    
    public ArrayList<Prato> listaPratos;
    public PratoDAO pDao = new PratoDAO();
    public static boolean flagModificacao = false;
    String entradas = "";
    
    public SelecionarPratoParaImportacao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);       
        getRootPane().setDefaultButton(btnAdicionar);
        listaPratos = new ArrayList<>();
        
        listaPratos.removeAll(listaPratos);
        for (Prato p: pDao.read()){
            listaPratos.add(p);
        }
        btnAdicionar.setEnabled(false);
        formatarTabela(); 
        criarTabela();
    }   
    
    public void criarTabela(){
        ArrayList<Prato> ordenador = new ArrayList<>();
        CardapioDAO cDao = new CardapioDAO();
        ItemCardapioDAO item = new ItemCardapioDAO();
        Cardapio c = cDao.readForData(ImportarCardapio.dataSelecionada);
        item.readForCardapio(c);
        // Percorre a lista de TODOS os Pratos da mesma CATEGORIA
        for (Prato prato : c.getPratos()){
            boolean flagExistente = false;
            if (GerenciadorCardapios.cardapio.getPratos().size() > 0){
                // Percorre a lista de Pratos do Cardápio, para verificar se este Prato desta Categoria já está adicionado
                for (Prato pratoExistente:GerenciadorCardapios.cardapio.getPratos()){
                    if (pratoExistente.getId() == prato.getId()){
                        flagExistente = true;
                        break;
                    }
                }
                
                if (!flagExistente){
                    ordenador.add(prato);
                }
            }else{
                ordenador.add(prato);
            } 
        }
        Collections.sort(ordenador);
        
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtPratos.getModel();
        limparTabela();
        for (Prato prato: ordenador){
            dtmBebidas.addRow(
                new Object[]{
                    prato.getId(),
                    prato.getNome()}
            );
        }
    }

    /*public void setarSelecionado(){
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtPratos.getModel();
        int quantidadeDeProdutos = dtmBebidas.getRowCount();
        
        for (int i = 0; i < quantidadeDeProdutos; i++){
            Integer id = (Integer)dtmBebidas.getValueAt(i, 1);
            for (Prato p : pratosSelecionados){
                if (p.getId() == id){
                    //jtProdutos.setRowSelectionInterval(i, i);
                    jtPratos.addRowSelectionInterval(i, i);
                }
            }
        }
    }*/
    
    public void limparTabela(){
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtPratos.getModel();
        int i = dtmBebidas.getRowCount();
        
        for (int j = 0; j < i; j++){
            dtmBebidas.removeRow(0);
        }       
    }

    public void formatarTabela(){        
        jtPratos.setRowHeight(26);
        jtPratos.getColumnModel().getColumn(0).setPreferredWidth(0); 
        jtPratos.getColumnModel().getColumn(1).setPreferredWidth(530);
        
        jtPratos.getColumnModel().getColumn(0).setMinWidth(0);
        jtPratos.getColumnModel().getColumn(1).setMinWidth(530);
        
        jtPratos.getColumnModel().getColumn(0).setMaxWidth(0);
        jtPratos.getColumnModel().getColumn(1).setMaxWidth(530);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtStringSuprimento = new javax.swing.JLabel();
        linha = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnCancelar = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtPratos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Suprimento do Caixa");
        setMaximumSize(new java.awt.Dimension(517, 378));
        setMinimumSize(new java.awt.Dimension(517, 378));
        setResizable(false);

        txtStringSuprimento.setBackground(new java.awt.Color(0, 102, 204));
        txtStringSuprimento.setFont(new java.awt.Font("Century Gothic", 0, 19)); // NOI18N
        txtStringSuprimento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtStringSuprimento.setText("Seleção de Pratos");

        linha.setBackground(new java.awt.Color(0, 0, 0));
        linha.setOpaque(true);

        btnCancelar.setBackground(new java.awt.Color(255, 0, 51));
        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCancelar.setText("Voltar");
        btnCancelar.setBorder(new javax.swing.border.MatteBorder(null));
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAdicionar.setBackground(new java.awt.Color(0, 153, 51));
        btnAdicionar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnAdicionar.setText("Adicionar");
        btnAdicionar.setBorder(new javax.swing.border.MatteBorder(null));
        btnAdicionar.setBorderPainted(false);
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        jtPratos.setBorder(new javax.swing.border.MatteBorder(null));
        jtPratos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jtPratos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtPratos.getTableHeader().setReorderingAllowed(false);
        jtPratos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtPratosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtPratosFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(jtPratos);
        if (jtPratos.getColumnModel().getColumnCount() > 0) {
            jtPratos.getColumnModel().getColumn(0).setResizable(false);
            jtPratos.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtStringSuprimento)
                .addGap(173, 173, 173))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtStringSuprimento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(334, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        ImportarCardapio novoPrato = new ImportarCardapio(new javax.swing.JFrame(), true);
        novoPrato.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        //materiasSelecionadas.removeAll(materiasSelecionadas);
        ItemCardapioDAO itemCDao = new ItemCardapioDAO();
        int quantidadeDeSelecionados = jtPratos.getSelectedRows().length;

        for (int i = 0; i < quantidadeDeSelecionados; i++){
            Integer idSelecionado = (Integer)jtPratos.getValueAt(jtPratos.getSelectedRows()[i], 0);
            for (Prato p:listaPratos){
                if (p.getId() == idSelecionado){
                    GerenciadorCardapios.cardapio.setPrato(p);
                    itemCDao.create(GerenciadorCardapios.cardapio, p);
                }
            }
        }
        dispose();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void jtPratosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtPratosFocusGained
        btnAdicionar.setEnabled(true);
    }//GEN-LAST:event_jtPratosFocusGained

    private void jtPratosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtPratosFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jtPratosFocusLost

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
            java.util.logging.Logger.getLogger(SelecionarPratoParaImportacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SelecionarPratoParaImportacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SelecionarPratoParaImportacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SelecionarPratoParaImportacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SelecionarPratoParaImportacao dialog = new SelecionarPratoParaImportacao(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtPratos;
    private javax.swing.Box.Filler linha;
    private javax.swing.JLabel txtStringSuprimento;
    // End of variables declaration//GEN-END:variables
}
