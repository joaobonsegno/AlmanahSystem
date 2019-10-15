package main;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.DefaultTableModel;
import model.bean.Cardapio;
import model.bean.CategoriaPrato;
import model.bean.Comanda;
import model.bean.Forma;
import model.bean.Prato;
import model.bean.Produto;
import model.dao.CardapioDAO;
import model.dao.CategoriaPratoDAO;
import model.dao.ComandaDAO;
import model.dao.FormaDAO;
import model.dao.ItemCardapioDAO;
import model.dao.ItemComandaDAO;
import model.dao.PratoDAO;

public class UnirComandas extends javax.swing.JDialog {    
    public ArrayList<Comanda> listaComandas;
    public PratoDAO pDao = new PratoDAO();
    public static boolean flagModificacao = false;
    String entradas = "";
    
    public UnirComandas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);       
        getRootPane().setDefaultButton(btnConfirmar);
        listaComandas = new ArrayList<>();
        btnConfirmar.setEnabled(false);
        formatarTabela(); 
        for (Comanda c : GerenciadorComandas.comandasAbertas){
            if (c.getId() != GerenciadorComandas.idSelecionado){
                listaComandas.add(c);
            }
        }
        criarTabela();        
    }   
    
    public void criarTabela(){       
        DefaultTableModel dtmComandas = (DefaultTableModel) jtComandas.getModel();
        for (Comanda c : listaComandas){
            dtmComandas.addRow(
                new Object[]{
                    c.getId(),
                    GerenciadorComandas.valorMonetario(c.getValor())}
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
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtComandas.getModel();
        int i = dtmBebidas.getRowCount();
        
        for (int j = 0; j < i; j++){
            dtmBebidas.removeRow(0);
        }       
    }

    public void formatarTabela(){        
        jtComandas.setRowHeight(26);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtStringSuprimento = new javax.swing.JLabel();
        linha = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtComandas = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Unir Comandas");
        setMaximumSize(new java.awt.Dimension(399, 378));
        setMinimumSize(new java.awt.Dimension(399, 378));
        setResizable(false);

        txtStringSuprimento.setBackground(new java.awt.Color(0, 102, 204));
        txtStringSuprimento.setFont(new java.awt.Font("Century Gothic", 0, 19)); // NOI18N
        txtStringSuprimento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtStringSuprimento.setText("Unir Comandas");

        linha.setBackground(new java.awt.Color(0, 0, 0));
        linha.setOpaque(true);

        jtComandas.setBorder(new javax.swing.border.MatteBorder(null));
        jtComandas.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jtComandas.setModel(new javax.swing.table.DefaultTableModel(
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
        jtComandas.getTableHeader().setReorderingAllowed(false);
        jtComandas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtComandasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtComandasFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(jtComandas);
        if (jtComandas.getColumnModel().getColumnCount() > 0) {
            jtComandas.getColumnModel().getColumn(0).setResizable(false);
            jtComandas.getColumnModel().getColumn(1).setResizable(false);
        }

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

        btnConfirmar.setBackground(new java.awt.Color(0, 153, 51));
        btnConfirmar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.setBorder(new javax.swing.border.MatteBorder(null));
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(txtStringSuprimento)))
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtStringSuprimento)
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(334, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        ItemComandaDAO itemCDao = new ItemComandaDAO();
        FormaDAO formaDao = new FormaDAO();
        Comanda comandaUnificada = GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado);
        
        int quantidadeDeSelecionados = jtComandas.getSelectedRows().length;
        ArrayList<Integer> idsSelecionados = new ArrayList<>();
        
        // Faz um laço que percorre todos os itens selecionados pelo usuário
        for (int i = 0; i < quantidadeDeSelecionados; i++){
            // A variável idSelecionado assume, de um em um, o ID das comandas selecionadas pelo usuário
            Integer idSelecionado = (Integer)jtComandas.getValueAt(jtComandas.getSelectedRows()[i], 0);
            idsSelecionados.add(idSelecionado);
            for (Comanda c:listaComandas){
                
                // Estrutura WHEN, para achar quando o ID selecionado pelo usuário é igual ao ID da comanda
                if (c.getId() == idSelecionado){
                    ArrayList<Integer> indicesASeremRemovidos = new ArrayList<>();
                    Integer contador = 0;
                    
                    // Percorre a lista de PRATOS da comanda selecionada, para colocá-los na comanda unificada
                    for (Double d : c.getPratos()){
                        comandaUnificada.setPratos(d);
                        itemCDao.create(comandaUnificada, d);
                        indicesASeremRemovidos.add(contador);                        
                        contador += 1;
                    }
                    
                    // Remove todos os pratos que estiverem nos índices do laço 
                    for (Integer in : indicesASeremRemovidos){
                        c.removerPrato(0);
                    }
//------------------------------REMOÇÃO DE PRODUTOS-------------------------------------
                    indicesASeremRemovidos.removeAll(indicesASeremRemovidos);
                    contador = 0;
                    // Percorre a lista de PRODUTOS da comanda selecionada, para colocá-los na comanda unificada
                    for (Produto p : c.getItens()){
                        if (comandaUnificada.setItensComVerificacao(p, c.getQnt().get(contador))){
                            itemCDao.update(comandaUnificada, p, Integer.parseInt(comandaUnificada.getQnt().get(comandaUnificada.getQnt().size()-1)));
                        }else{
                            itemCDao.create(comandaUnificada, p, Integer.parseInt(comandaUnificada.getQnt().get(comandaUnificada.getQnt().size()-1)));
                        }                       
                        indicesASeremRemovidos.add(contador);                        
                        contador += 1;
                    }
                    
                    for (Integer in : indicesASeremRemovidos){
                        c.removerItemSemAddEstoque(0);
                    }
//------------------------------UNINDO FORMAS DE PAGAMENTO-------------------------------------                
                    for (Forma f : formaDao.readForComanda(c)){
                        Forma forma = new Forma();
                        forma = f.clonarComanda(forma, comandaUnificada);
                        comandaUnificada.setForma(forma);
                        formaDao.updateComanda(forma);
                    }
                }                
            }
        }
        
        int indice = 0;
        ArrayList<Integer> indices = new ArrayList<>();
        ComandaDAO comandaDao = new ComandaDAO();
        for (Comanda c : GerenciadorComandas.comandasAbertas){
            for (Integer id : idsSelecionados){
                if (c.getId() == id){
                    indices.add(indice);
                    c.setStatus(0);
                    comandaDao.update(c);
                }
            }
            indice += 1;
        }
        
        int contador = 0;
        for (int rem : indices){            
            GerenciadorComandas.comandasAbertas.remove(rem-contador);
            contador += 1;
        }
        
        dispose();
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void jtComandasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtComandasFocusGained
        btnConfirmar.setEnabled(true);
    }//GEN-LAST:event_jtComandasFocusGained

    private void jtComandasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtComandasFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jtComandasFocusLost

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
            java.util.logging.Logger.getLogger(UnirComandas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UnirComandas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UnirComandas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UnirComandas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                UnirComandas dialog = new UnirComandas(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtComandas;
    private javax.swing.Box.Filler linha;
    private javax.swing.JLabel txtStringSuprimento;
    // End of variables declaration//GEN-END:variables
}
