package main;

import ArrumarString.SoNumeros;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import model.bean.Log;
import model.bean.Produto;
import model.dao.ItemComandaDAO;
import model.dao.LogDAO;
import model.dao.ProdutoDAO;

public class NovaSobremesa extends javax.swing.JFrame {
    ArrayList<Produto> listaProdutos = new ArrayList<>();
    
    public void criarTabela(){
        ArrayList<Produto> ordenador = new ArrayList<>();
        
        ProdutoDAO pDao = new ProdutoDAO();
        
        for (Produto prod : pDao.read()){
            ordenador.add(prod);
        }
        ordenador = GerenciadorComandas.ordenarListasProduto(ordenador);
        
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtSobremesas.getModel();
        for (Produto p: ordenador){
            if(p.getCategoria().getNome().equals("Sobremesa")){
                String valor = GerenciadorComandas.valorMonetario(p.getPreco());
                
                dtmBebidas.addRow(
                    new Object[]{
                        p.getIdProduto(),
                        p.getQtdEstoque(),
                        valor,
                        p.getNome()}
                );
            }
        } 
    }
    
    public void criarTabelaNome(String nome){
        ArrayList<Produto> ordenador = new ArrayList<>();
        
        ProdutoDAO pDao = new ProdutoDAO();
        for (Produto prod : pDao.readForNome(nome)){
            ordenador.add(prod);
        }
        ordenador = GerenciadorComandas.ordenarListasProduto(ordenador);
        
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtSobremesas.getModel();
        limparTabela();
        for (Produto p: ordenador){
            if(p.getCategoria().getNome().equals("Sobremesa")){
                String valor = GerenciadorComandas.valorMonetario(p.getPreco());
                dtmBebidas.addRow(
                    new Object[]{
                        p.getIdProduto(),
                        p.getQtdEstoque(),
                        valor,
                        p.getNome()}
                );
            }
        }
    }
    
    public void limparTabela(){
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtSobremesas.getModel();
        int i = dtmBebidas.getRowCount();
        
        for (int j = 0; j < i; j++){
            dtmBebidas.removeRow(0);
        }
    }
    
    public NovaSobremesa() {
        initComponents();
        jtSobremesas.setRowHeight(22);
        jtSobremesas.getColumnModel().getColumn(0).setPreferredWidth(80); 
        jtSobremesas.getColumnModel().getColumn(1).setPreferredWidth(80);
        jtSobremesas.getColumnModel().getColumn(2).setPreferredWidth(80);
        jtSobremesas.getColumnModel().getColumn(3).setPreferredWidth(300);
        
        jtSobremesas.getColumnModel().getColumn(0).setMinWidth(80);
        jtSobremesas.getColumnModel().getColumn(1).setMinWidth(80);
        jtSobremesas.getColumnModel().getColumn(2).setMinWidth(80);
        jtSobremesas.getColumnModel().getColumn(3).setMinWidth(300);
        
        jtSobremesas.getColumnModel().getColumn(0).setMaxWidth(80);
        jtSobremesas.getColumnModel().getColumn(1).setMaxWidth(80);
        jtSobremesas.getColumnModel().getColumn(2).setMaxWidth(80);
        jtSobremesas.getColumnModel().getColumn(3).setMaxWidth(300);
        String comString = Integer.toString(GerenciadorComandas.idSelecionado);
        lblComanda.setText(comString);
        SpinnerNumberModel nm = new SpinnerNumberModel(1, 1, 50, 1);
        SpinnerQtd.setModel(nm);
        ProdutoDAO pDao = new ProdutoDAO();
        
        for (Produto p: pDao.read()){
            listaProdutos.add(p);
        }
        criarTabela();
        this.setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnConfirmar);
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
        jtSobremesas = new javax.swing.JTable();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblStringComanda = new javax.swing.JLabel();
        SpinnerQtd = new javax.swing.JSpinner();
        lblStringCodigo1 = new javax.swing.JLabel();
        lblStringNomeProduto = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(1, 1), new java.awt.Dimension(1, 1), new java.awt.Dimension(1, 1));
        lblComanda = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Nova Bebida");
        setMinimumSize(new java.awt.Dimension(537, 752));
        setResizable(false);

        btnStringGerenciador.setBackground(new java.awt.Color(0, 102, 204));
        btnStringGerenciador.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringGerenciador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringGerenciador.setText("Nova Bebida");

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        jtSobremesas.setBorder(new javax.swing.border.MatteBorder(null));
        jtSobremesas.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jtSobremesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Qtd", "Preço", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtSobremesas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtSobremesasFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jtSobremesas);
        if (jtSobremesas.getColumnModel().getColumnCount() > 0) {
            jtSobremesas.getColumnModel().getColumn(0).setResizable(false);
            jtSobremesas.getColumnModel().getColumn(1).setResizable(false);
            jtSobremesas.getColumnModel().getColumn(2).setResizable(false);
            jtSobremesas.getColumnModel().getColumn(3).setResizable(false);
        }

        btnConfirmar.setBackground(new java.awt.Color(0, 153, 0));
        btnConfirmar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnConfirmar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\confirm.png")); // NOI18N
        btnConfirmar.setText("  Confirmar");
        btnConfirmar.setBorder(new javax.swing.border.MatteBorder(null));
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(204, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\cancel.png")); // NOI18N
        btnCancelar.setText("   Cancelar");
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

        SpinnerQtd.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        SpinnerQtd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                SpinnerQtdFocusLost(evt);
            }
        });
        SpinnerQtd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SpinnerQtdKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SpinnerQtdKeyTyped(evt);
            }
        });

        lblStringCodigo1.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lblStringCodigo1.setText("Quantidade:");

        lblStringNomeProduto.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto.setText("Nome:");

        txtPesquisa.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N

        btnOk.setBackground(new java.awt.Color(0, 153, 204));
        btnOk.setFont(new java.awt.Font("Century Gothic", 0, 19)); // NOI18N
        btnOk.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\pesquisar (1).png")); // NOI18N
        btnOk.setText(" Buscar");
        btnOk.setBorder(new javax.swing.border.MatteBorder(null));
        btnOk.setBorderPainted(false);
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        filler1.setBackground(new java.awt.Color(0, 0, 0));
        filler1.setOpaque(true);

        lblComanda.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lblComanda.setForeground(new java.awt.Color(255, 0, 0));
        lblComanda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComanda.setText("X");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lblStringNomeProduto)
                        .addGap(6, 6, 6)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 41, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(lblStringCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SpinnerQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblStringComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnStringGerenciador, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 1, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 1, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(btnStringGerenciador))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblStringComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringNomeProduto)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SpinnerQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringCodigo1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(692, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(740, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public int produtoExiste(Integer idSelecionado){
        boolean flag = true;
        int contador = -1;
        for (Produto p:GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1).getItens()){
            if (p.getIdProduto() == idSelecionado){
                contador += 1;
                flag = false;
                break;
            }
            contador += 1;
        }
        if (flag){
            contador = -1;
        }
        return contador;
    }
    
    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        LogDAO logDao = new LogDAO();
        ItemComandaDAO item = new ItemComandaDAO();
        String id1;
        Integer idSelecionado;
        idSelecionado = (Integer)jtSobremesas.getValueAt(jtSobremesas.getSelectedRow(), 0); 
        boolean flag = true;
        Integer qtdInt = (Integer)SpinnerQtd.getValue();
        String qtd = Integer.toString(qtdInt);
        for(Produto prod:listaProdutos){
            if(prod.getIdProduto().equals(idSelecionado)){
                if(!prod.getQtdEstoque().equals("X")){
                    if(Integer.parseInt(prod.getQtdEstoque()) < qtdInt){
                        JOptionPane.showMessageDialog(null, "Quantidade do produto em estoque é insuficiente.");
                    }else{
                        int indiceDoProduto = produtoExiste(idSelecionado);
                        if (indiceDoProduto != -1){
                            Integer quantidade = Integer.parseInt(GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1).getQntEspecifica(indiceDoProduto));
                            quantidade += qtdInt;
                            qtd = Integer.toString(quantidade);
                            GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1).removerProduto(indiceDoProduto);
                            GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1).setItens(prod, qtd);
                            GerenciadorEstoque.retirarEstoque(prod, qtdInt);
                            item.update(GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1), prod, quantidade);
                            flag = false;
                        }else{               
                            GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1).setItens(prod, qtd);
                            GerenciadorEstoque.retirarEstoque(prod, qtdInt);
                            item.create(GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1), prod, qtdInt);
                            flag = false;
                        }
                        Log l = new Log();
                        l.setCategoria("Estoque");
                        l.setData(l.dataAtual());
                        l.setDescricao("João retirou "+qtd+" de \""+prod.getNome()+"\" do estoque");
                        l.setSaldo(0.0);
                        l.setStatus(0);
                        l.setTipo("");
                        l.setValor(0.0);
                        logDao.create(l);
                    }
                }else{
                    int indiceDoProduto = produtoExiste(idSelecionado);
                    if (indiceDoProduto != -1){
                        Integer quantidade = Integer.parseInt(GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1).getQntEspecifica(indiceDoProduto));
                        quantidade += qtdInt;
                        qtd = Integer.toString(quantidade);
                        GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1).removerProduto(indiceDoProduto);
                        GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1).setItens(prod, qtd);
                        item.update(GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1), prod, quantidade);
                        flag = false;
                    }else{
                        GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1).setItens(prod, qtd);
                        item.create(GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1), prod, qtdInt);
                        flag = false;
                    }
                }
            }
        }
        if(!flag){
            new GerenciadorComandas().setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        new GerenciadorComandas().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        criarTabelaNome(txtPesquisa.getText());
    }//GEN-LAST:event_btnOkActionPerformed

    private void SpinnerQtdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SpinnerQtdKeyTyped
        /*char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
            evt.consume();
        }*/
    }//GEN-LAST:event_SpinnerQtdKeyTyped

    private void SpinnerQtdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SpinnerQtdKeyPressed
 
    }//GEN-LAST:event_SpinnerQtdKeyPressed

    private void SpinnerQtdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SpinnerQtdFocusLost

    }//GEN-LAST:event_SpinnerQtdFocusLost

    private void jtSobremesasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtSobremesasFocusGained

    }//GEN-LAST:event_jtSobremesasFocusGained

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
            java.util.logging.Logger.getLogger(NovaSobremesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NovaSobremesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NovaSobremesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NovaSobremesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new NovaSobremesa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner SpinnerQtd;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel btnStringGerenciador;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtSobremesas;
    public static javax.swing.JLabel lblComanda;
    private javax.swing.JLabel lblStringCodigo1;
    public static javax.swing.JLabel lblStringComanda;
    private javax.swing.JLabel lblStringNomeProduto;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}


