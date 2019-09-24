package main;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.bean.Cardapio;
import model.bean.Prato;
import model.bean.Produto;
import model.dao.PratoDAO;

public class GerenciadorCardapios extends javax.swing.JFrame {
    public static ArrayList<Prato> itensCardapio = new ArrayList<>();
    PratoDAO pDao;
    public static Produto prodSelecionado;
    
    public GerenciadorCardapios() {        
        initComponents();       
        this.setLocationRelativeTo(null);

        
    }

    public void formatarTabela(){
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();       
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        jtCardapios.setRowHeight(27);
        jtCardapios.getColumnModel().getColumn(0).setPreferredWidth(0); 
        jtCardapios.getColumnModel().getColumn(1).setPreferredWidth(400);
        
        jtCardapios.getColumnModel().getColumn(0).setMinWidth(0);
        jtCardapios.getColumnModel().getColumn(1).setMinWidth(400);
        
        jtCardapios.getColumnModel().getColumn(0).setMaxWidth(0);
        jtCardapios.getColumnModel().getColumn(1).setMaxWidth(400);
    }
    
    public void criarTabela(){
        limparTabela();
        ArrayList<Produto> ordenador = new ArrayList<>();
        
        for (Produto prod : listaProdutos){
            ordenador.add(prod);
        }
        //ordenador = listaProdutos.sort(listaProdutos);
        Collections.sort(ordenador);
        
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtCardapios.getModel();
        for (Produto p: ordenador){
            if (p.getPreco() == 0.0){
                dtmBebidas.addRow(
                    new Object[]{
                        p.getNome(),
                        "X",
                        p.getIdProduto()}
                );
            }else{
                String valor = GerenciadorComandas.valorMonetario(p.getPreco());
                dtmBebidas.addRow(
                    new Object[]{
                        p.getNome(),
                        valor,
                        p.getIdProduto()}
                );
            }
        }
    }
    
    public void criarTabelaNome(String nome){
        ArrayList<Produto> ordenador = new ArrayList<>();
        
        for (Produto prod : pDao.readForNome(nome)){
            ordenador.add(prod);
        }
        Collections.sort(ordenador);
        
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtCardapios.getModel();
        limparTabela();
        for (Produto p: ordenador){
            if (p.getPreco() == 0.0){
                dtmBebidas.addRow(
                    new Object[]{
                        p.getNome(),
                        "X",
                        p.getIdProduto()}
                );
            }else{
                String valor = GerenciadorComandas.valorMonetario(p.getPreco());
                dtmBebidas.addRow(
                    new Object[]{
                        p.getNome(),
                        valor,
                        p.getIdProduto()}
                );
            }
        }
    }
    
    public void criarTabelaCategoria(String nome){
        ArrayList<Produto> ordenador = new ArrayList<>();
        
        for (Produto prod : pDao.readForCategoria(nome)){
            ordenador.add(prod);
        }
        Collections.sort(ordenador);
        
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtCardapios.getModel();
        limparTabela();
        for (Produto p: ordenador){
            if (p.getPreco() == 0.0){
                dtmBebidas.addRow(
                    new Object[]{
                        p.getNome(),
                        "X",
                        p.getIdProduto()}
                );
            }else{
                String valor = GerenciadorComandas.valorMonetario(p.getPreco());
                dtmBebidas.addRow(
                    new Object[]{
                        p.getNome(),
                        valor,
                        p.getIdProduto()}
                );
            }
        }
    }
    
    public void limparTabela(){
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtCardapios.getModel();
        int i = dtmBebidas.getRowCount();
        
        for (int j = 0; j < i; j++){
            dtmBebidas.removeRow(0);
        }       
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringProdutos = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnLancador = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtCardapios = new javax.swing.JTable();
        btnRemover = new javax.swing.JButton();
        lblStringNomeProduto1 = new javax.swing.JLabel();
        calendarNasc = new com.toedter.calendar.JDateChooser();
        btnModificar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCriar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciador de Produtos");
        setMinimumSize(new java.awt.Dimension(757, 668));
        setResizable(false);

        btnStringProdutos.setBackground(new java.awt.Color(0, 102, 204));
        btnStringProdutos.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringProdutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringProdutos.setText("Gerenciador de Cardápios");

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        btnLancador.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        btnLancador.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\voltar (1).png")); // NOI18N
        btnLancador.setText("Menu");
        btnLancador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancadorActionPerformed(evt);
            }
        });

        jtCardapios.setBorder(new javax.swing.border.MatteBorder(null));
        jtCardapios.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jtCardapios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtCardapios);
        if (jtCardapios.getColumnModel().getColumnCount() > 0) {
            jtCardapios.getColumnModel().getColumn(0).setResizable(false);
            jtCardapios.getColumnModel().getColumn(1).setResizable(false);
        }

        btnRemover.setBackground(new java.awt.Color(204, 0, 0));
        btnRemover.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnRemover.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\delete (1).png")); // NOI18N
        btnRemover.setText("Remover Prato");
        btnRemover.setBorder(new javax.swing.border.MatteBorder(null));
        btnRemover.setBorderPainted(false);
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        lblStringNomeProduto1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto1.setText("Dia:");

        calendarNasc.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        btnModificar.setBackground(new java.awt.Color(204, 204, 0));
        btnModificar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\lapis.png")); // NOI18N
        btnModificar.setText("Modificar Pratos");
        btnModificar.setBorder(new javax.swing.border.MatteBorder(null));
        btnModificar.setBorderPainted(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnSalvar.setBackground(new java.awt.Color(0, 153, 0));
        btnSalvar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\confirm.png")); // NOI18N
        btnSalvar.setText("  Salvar");
        btnSalvar.setBorder(new javax.swing.border.MatteBorder(null));
        btnSalvar.setBorderPainted(false);

        btnCriar.setBackground(new java.awt.Color(0, 153, 0));
        btnCriar.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        btnCriar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\add (2) (1).png")); // NOI18N
        btnCriar.setText("Criar Cardápio");
        btnCriar.setBorder(new javax.swing.border.MatteBorder(null));
        btnCriar.setBorderPainted(false);
        btnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLancador)
                        .addGap(26, 26, 26)
                        .addComponent(btnStringProdutos))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblStringNomeProduto1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(calendarNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnStringProdutos))
                    .addComponent(btnLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStringNomeProduto1)
                    .addComponent(calendarNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemover))
                .addGap(18, 18, 18)
                .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addGap(4, 4, 4))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(534, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        new Menu().setVisible(true); 
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        try{
            Integer idSelecionado = (Integer)jtCardapios.getValueAt(jtCardapios.getSelectedRow(), 2); 
            
            for(Produto p:listaProdutos){
                if(p.getIdProduto().equals(idSelecionado)){
                    prodSelecionado = p;
                    InativarProduto inativar = new InativarProduto(new javax.swing.JFrame(), true);
                    inativar.setVisible(true); 
                    dispose();
                }
            }
           
        }catch(java.util.ConcurrentModificationException ex){
            System.out.println("Deu a exceção");
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarActionPerformed
        Cardapio cardapio = new Cardapio();
    }//GEN-LAST:event_btnCriarActionPerformed

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
            java.util.logging.Logger.getLogger(GerenciadorCardapios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciadorCardapios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciadorCardapios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciadorCardapios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new GerenciadorCardapios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCriar;
    private javax.swing.JButton btnLancador;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel btnStringProdutos;
    private com.toedter.calendar.JDateChooser calendarNasc;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtCardapios;
    private javax.swing.JLabel lblStringNomeProduto1;
    private javax.swing.Box.Filler linha1;
    // End of variables declaration//GEN-END:variables
}


