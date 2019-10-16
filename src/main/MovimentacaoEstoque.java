package main;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.bean.Log;
import model.bean.Produto;
import model.dao.LogDAO;
import model.dao.ProdutoDAO;

public class MovimentacaoEstoque extends javax.swing.JFrame {
    public static ArrayList<Produto> listaProdutos = new ArrayList<>();
    LogDAO logDao;
    public static Produto prodSelecionado;
    

     
    public MovimentacaoEstoque() {
       initComponents();       
       getRootPane().setDefaultButton(btnBuscar);
       this.setLocationRelativeTo(null);
       this.formatarTabela();
       criarTabela();
    }
    
    public void formatarTabela(){
        jtLogs.setRowHeight(26);
       jtLogs.getColumnModel().getColumn(0).setPreferredWidth(80); 
       jtLogs.getColumnModel().getColumn(1).setPreferredWidth(620);
       jtLogs.getColumnModel().getColumn(2).setPreferredWidth(220);
        
       jtLogs.getColumnModel().getColumn(0).setMinWidth(80);
       jtLogs.getColumnModel().getColumn(1).setMinWidth(620);
       jtLogs.getColumnModel().getColumn(2).setMinWidth(220);
        
       jtLogs.getColumnModel().getColumn(0).setMaxWidth(80);
       jtLogs.getColumnModel().getColumn(1).setMaxWidth(620);
       jtLogs.getColumnModel().getColumn(2).setMaxWidth(220);
    }
    
    public void criarTabela(){
        DefaultTableModel dtmLogs = (DefaultTableModel) jtLogs.getModel();
        logDao = new LogDAO();
        for (Log l:logDao.read()){
            if (l.getCategoria().equals("Estoque")){
                dtmLogs.addRow(
                    new Object[]{
                        l.getId(),
                        l.getDescricao(),
                        l.getData()}
                );
            }
        }
    }
    
    public void criarTabelaNome(String nome){
        DefaultTableModel dtmLogs = (DefaultTableModel) jtLogs.getModel();
        LogDAO lDao = new LogDAO();
        limparTabela();
        for (Log l: lDao.readForNome(nome)){
            if (l.getCategoria().equals("Estoque")){
                dtmLogs.addRow(
                    new Object[]{
                        l.getId(),
                        l.getDescricao(),
                        l.getData()}
                ); 
            }
        }
    }
    
    public void limparTabela(){
        DefaultTableModel dtmLogs = (DefaultTableModel) jtLogs.getModel();
        int i = dtmLogs.getRowCount();
        
        for (int j = 0; j < i; j++){
            dtmLogs.removeRow(0);
        }       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringProdutos = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        jScrollPane1 = new javax.swing.JScrollPane();
        jtLogs = new javax.swing.JTable();
        btnVoltar = new javax.swing.JButton();
        lblStringNomeProduto = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Movimentação do Estoque");
        setMinimumSize(new java.awt.Dimension(1009, 495));
        setResizable(false);

        btnStringProdutos.setBackground(new java.awt.Color(0, 102, 204));
        btnStringProdutos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        btnStringProdutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringProdutos.setText("Movimentação");

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        jtLogs.setBorder(new javax.swing.border.MatteBorder(null));
        jtLogs.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jtLogs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descrição", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtLogs);
        if (jtLogs.getColumnModel().getColumnCount() > 0) {
            jtLogs.getColumnModel().getColumn(0).setResizable(false);
            jtLogs.getColumnModel().getColumn(1).setResizable(false);
            jtLogs.getColumnModel().getColumn(2).setResizable(false);
        }

        btnVoltar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnVoltar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\voltar (1).png")); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.setBorder(new javax.swing.border.MatteBorder(null));
        btnVoltar.setBorderPainted(false);
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        lblStringNomeProduto.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto.setText("Pesquisa:");

        txtPesquisa.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N

        btnBuscar.setBackground(new java.awt.Color(0, 153, 204));
        btnBuscar.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\pesquisar (1).png")); // NOI18N
        btnBuscar.setText(" Buscar");
        btnBuscar.setBorder(new javax.swing.border.MatteBorder(null));
        btnBuscar.setBorderPainted(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(309, 309, 309)
                        .addComponent(btnStringProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblStringNomeProduto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStringProdutos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblStringNomeProduto)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(434, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        dispose();
        new Menu().setVisible(true);
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        criarTabelaNome(txtPesquisa.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

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
                if ("WebLaF".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MovimentacaoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MovimentacaoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MovimentacaoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MovimentacaoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MovimentacaoEstoque().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel btnStringProdutos;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtLogs;
    private javax.swing.JLabel lblStringNomeProduto;
    private javax.swing.Box.Filler linha1;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}


