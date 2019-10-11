package main;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.bean.Comanda;
import model.bean.Produto;
import model.bean.PromocaoUm;
import model.dao.ProdutoDAO;
import model.dao.PromocaoUmDAO;

public class EncerrarComanda extends javax.swing.JFrame {
    ArrayList<Produto> listaProdutos = new ArrayList<>();
    public static Comanda comandaSelecionada;
    public static boolean flagValor;
    ProdutoDAO pDao;
    
    public void criarTabela(){
        Comanda comanda = new Comanda();
        comanda = comandaSelecionada.clonarComanda(comanda);
        PromocaoUmDAO promoDao = new PromocaoUmDAO();
        PromocaoUm promocaoUm = promoDao.read();
        
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtItens.getModel();
        
        ArrayList<String> qnt = new ArrayList<>();
        for(String s:comanda.getQnt()){
            qnt.add(s);
        }
        int i = 0;
        
        for (Produto p: comanda.getItens()){

                String valor = GerenciadorComandas.valorMonetario(p.getPrecoComDesconto());
                String valor2 = GerenciadorComandas.valorMonetario(p.getPrecoComDesconto()*Integer.parseInt(qnt.get(i)));
                dtmBebidas.addRow(
                        new Object[]{
                            p.getIdProduto(),
                            qnt.get(i),
                            p.getNome(),
                            valor,
                            valor2}
                    );
                i+=1;
                      
        }
        
        for (Double d: comanda.getPratos()){
            String valor = GerenciadorComandas.valorMonetario(d);
            String valor2 = GerenciadorComandas.valorMonetario(d);
            dtmBebidas.addRow(
                    new Object[]{
                        "X",
                        "1",
                        "Refeição Geral",
                        valor,
                        valor2}
                );
        }
    }
    
    public Integer pegarItemSelecionado(){
        Integer i = jtItens.getSelectedRow();
        return i;
    }
    
    public void removerItem(){
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtItens.getModel();
        Integer i = jtItens.getSelectedRow();
        Integer qtdPratos = comandaSelecionada.getPratos().size();
        Integer qtdProdutos = comandaSelecionada.getItens().size();
        if (i != -1){  
            dtmBebidas.removeRow(i);
            if (i > qtdProdutos-1){
                Integer j = i;
                j -= qtdProdutos;
                comandaSelecionada.removerPrato(j);
            }else{
                comandaSelecionada.removerItem(i);
            }
        }
        lblValorTotal.setText("R$ "+GerenciadorComandas.valorMonetario(EncerrarComanda.comandaSelecionada.getValor()));
        for(Comanda c:GerenciadorComandas.comandasAbertas){
            if(comandaSelecionada.getId() == c.getId()){
                c.setValor(comandaSelecionada.getValor());
                c.setValorPendente(comandaSelecionada.getValorPendente());
            }
        }
        if (EncerrarComanda.comandaSelecionada.getValor() == 0){
            new GerenciadorComandas().setVisible(true);
            dispose();
        } 
    }
    
    public void limparTabela(){
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtItens.getModel();
        int i = dtmBebidas.getRowCount();
        
        for (int j = 0; j < i; j++){
            dtmBebidas.removeRow(0);
        }       
    }
    
    public EncerrarComanda() {
        initComponents();
        //btnRemover.setEnabled(false);
        jtItens.setRowHeight(40);
        jtItens.getColumnModel().getColumn(0).setPreferredWidth(80); //ID
        jtItens.getColumnModel().getColumn(1).setPreferredWidth(80); //QTD
        jtItens.getColumnModel().getColumn(2).setPreferredWidth(412); // NOME
        jtItens.getColumnModel().getColumn(3).setPreferredWidth(100); // UNITARIO
        jtItens.getColumnModel().getColumn(4).setPreferredWidth(120); // TOTAL
        
        jtItens.getColumnModel().getColumn(0).setMinWidth(80); //ID
        jtItens.getColumnModel().getColumn(1).setMinWidth(80); //QTD
        jtItens.getColumnModel().getColumn(2).setMinWidth(412); // NOME
        jtItens.getColumnModel().getColumn(3).setMinWidth(100); // UNITARIO
        jtItens.getColumnModel().getColumn(4).setMinWidth(120);
        
        jtItens.getColumnModel().getColumn(0).setMaxWidth(80); //ID
        jtItens.getColumnModel().getColumn(1).setMaxWidth(80); //QTD
        jtItens.getColumnModel().getColumn(2).setMaxWidth(412); // NOME
        jtItens.getColumnModel().getColumn(3).setMaxWidth(100); // UNITARIO
        jtItens.getColumnModel().getColumn(4).setMaxWidth(120);
      
        String comString = Integer.toString(GerenciadorComandas.idSelecionado);
        comandaSelecionada = new Comanda();
        for(Comanda c:GerenciadorComandas.comandasAbertas){
            if(c.getId() == GerenciadorComandas.idSelecionado){
                comandaSelecionada = c.clonarComanda(comandaSelecionada);
            }
        }
        lblComanda.setText(comString);
        lblValorTotal.setText("R$ "+GerenciadorComandas.valorMonetario(EncerrarComanda.comandaSelecionada.getValor()));

        this.setLocationRelativeTo(null);
        pDao = new ProdutoDAO();
        
        for (Produto p: pDao.read()){
            listaProdutos.add(p);
        }
        criarTabela();
        getRootPane().setDefaultButton(btnConfirmar);
        btnRemover.setEnabled(false);
        /*JFormattedTextField tf = ((JSpinner.DefaultEditor) SpinnerQtd.getEditor()).getTextField();
        tf.setEditable(false);
        SpinnerQtd.setModel(nm);*/
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringGerenciador = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        jScrollPane1 = new javax.swing.JScrollPane();
        jtItens = new javax.swing.JTable();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblStringComanda = new javax.swing.JLabel();
        lblComanda = new javax.swing.JLabel();
        lblStringValorTotal = new javax.swing.JLabel();
        lblValorTotal = new javax.swing.JLabel();
        btnRemover = new javax.swing.JButton();
        btnRemover1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Encerramento de Comanda");
        setMinimumSize(new java.awt.Dimension(873, 593));
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        btnStringGerenciador.setBackground(new java.awt.Color(0, 102, 204));
        btnStringGerenciador.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringGerenciador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringGerenciador.setText("Encerrar Comanda");

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        jtItens.setBorder(new javax.swing.border.MatteBorder(null));
        jtItens.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jtItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Qtd", "Nome", "Unitário", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtItens.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtItensFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jtItens);
        if (jtItens.getColumnModel().getColumnCount() > 0) {
            jtItens.getColumnModel().getColumn(0).setResizable(false);
            jtItens.getColumnModel().getColumn(1).setResizable(false);
            jtItens.getColumnModel().getColumn(2).setResizable(false);
            jtItens.getColumnModel().getColumn(3).setResizable(false);
            jtItens.getColumnModel().getColumn(4).setResizable(false);
        }

        btnConfirmar.setBackground(new java.awt.Color(0, 153, 0));
        btnConfirmar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnConfirmar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\continuar.png")); // NOI18N
        btnConfirmar.setText("  Continuar");
        btnConfirmar.setBorder(new javax.swing.border.MatteBorder(null));
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(204, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\voltar (2).png")); // NOI18N
        btnCancelar.setText("  Voltar");
        btnCancelar.setBorder(new javax.swing.border.MatteBorder(null));
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblStringComanda.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lblStringComanda.setForeground(new java.awt.Color(255, 0, 0));
        lblStringComanda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStringComanda.setText("Comanda");

        lblComanda.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lblComanda.setForeground(new java.awt.Color(255, 0, 0));
        lblComanda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComanda.setText("X");

        lblStringValorTotal.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblStringValorTotal.setText("Valor Total");

        lblValorTotal.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lblValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValorTotal.setText("R$ 0,00");
        lblValorTotal.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        btnRemover.setBackground(new java.awt.Color(153, 153, 0));
        btnRemover.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnRemover.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\delete.png")); // NOI18N
        btnRemover.setText("  Remover");
        btnRemover.setBorder(new javax.swing.border.MatteBorder(null));
        btnRemover.setBorderPainted(false);
        btnRemover.setEnabled(false);
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnRemover1.setBackground(new java.awt.Color(0, 153, 204));
        btnRemover1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnRemover1.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\turno.png")); // NOI18N
        btnRemover1.setText("  Unir Comandas");
        btnRemover1.setBorder(new javax.swing.border.MatteBorder(null));
        btnRemover1.setBorderPainted(false);
        btnRemover1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnRemover1FocusGained(evt);
            }
        });
        btnRemover1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemover1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblStringComanda)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(107, 107, 107)
                            .addComponent(btnStringGerenciador, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnRemover1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(133, 133, 133)
                                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblStringValorTotal)
                                        .addComponent(lblValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 798, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 1, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStringGerenciador))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblStringValorTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRemover1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(67, 67, 67)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(575, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(631, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        
        FormaPagamento forma = new FormaPagamento(new javax.swing.JFrame(), true);
        forma.setVisible(true); 
        dispose();
        
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        new GerenciadorComandas().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        removerItem();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnRemover1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemover1ActionPerformed
        UnirComandas forma = new UnirComandas(new javax.swing.JFrame(), true);
        forma.setVisible(true); 
    }//GEN-LAST:event_btnRemover1ActionPerformed

    private void jtItensFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtItensFocusGained
        btnRemover.setEnabled(true);
    }//GEN-LAST:event_jtItensFocusGained

    private void btnRemover1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnRemover1FocusGained
        btnRemover.setEnabled(false);
        jtItens.clearSelection();
    }//GEN-LAST:event_btnRemover1FocusGained

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        btnRemover.setEnabled(false);
        jtItens.clearSelection();
        
        this.limparTabela();
        this.criarTabela();
        int contador = 0;
        for (Comanda c : GerenciadorComandas.comandasAbertas){
            if (c.getId() == GerenciadorComandas.idSelecionado){
                GerenciadorComandas.indiceSelecionado = contador;
            }
            contador += 1;
        }
        lblValorTotal.setText("R$ "+GerenciadorComandas.valorMonetario(GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado).getValor()));
    }//GEN-LAST:event_formWindowGainedFocus

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
            java.util.logging.Logger.getLogger(EncerrarComanda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EncerrarComanda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EncerrarComanda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EncerrarComanda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new EncerrarComanda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnRemover1;
    private javax.swing.JLabel btnStringGerenciador;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtItens;
    public static javax.swing.JLabel lblComanda;
    public static javax.swing.JLabel lblStringComanda;
    private javax.swing.JLabel lblStringValorTotal;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    // End of variables declaration//GEN-END:variables
}


