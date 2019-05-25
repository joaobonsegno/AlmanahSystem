package main;

import ArrumarString.SoNumeros;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import static javax.swing.GroupLayout.Alignment.CENTER;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import static main.GerenciadorProdutos.listaProdutos;
import static main.NovaBebida.lblComanda;
import model.bean.Comanda;
import model.bean.Log;
import model.bean.Produto;
import model.dao.ItemComandaDAO;
import model.dao.LogDAO;
import model.dao.ProdutoDAO;

public class NovaSobremesa extends javax.swing.JFrame {
    ArrayList<Produto> listaProdutos = new ArrayList<>();
    ProdutoDAO pDao;
    
    public void criarTabela(){
        ArrayList<Produto> ordenador = new ArrayList<>();
        
        for (Produto prod : listaProdutos){
            ordenador.add(prod);
        }
        ordenador = GerenciadorComandas.ordenarListasProduto(ordenador);
        
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtSobremesas.getModel();
        for (Produto p: ordenador){
            p.getCategoria().getId();
            if(p.getCategoria().getId() == 2){
                String valor = GerenciadorComandas.valorMonetario(p.getPreco());
                dtmBebidas.addRow(
                    new Object[]{
                        p.getIdSistema(),
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
                        p.getIdSistema(),
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
        txtIdBebida.setDocument(new SoNumeros());
        
        lblErro.setVisible(false);
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
        this.setLocationRelativeTo(null);
        pDao = new ProdutoDAO();
        
        for (Produto p: pDao.read()){
            if(p.getCategoria().getNome().equals("Sobremesa")){
                listaProdutos.add(p);
            } 
        }
        criarTabela();
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
        lblStringSelecione = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblStringCodigo = new javax.swing.JLabel();
        txtIdBebida = new javax.swing.JTextField();
        SpinnerQtd = new javax.swing.JSpinner();
        lblStringQuantidade = new javax.swing.JLabel();
        lblErro = new javax.swing.JLabel();
        lblStringNomeProduto = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(1, 1), new java.awt.Dimension(1, 1), new java.awt.Dimension(1, 1));
        lblStringComanda = new javax.swing.JLabel();
        lblComanda = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Nova Sobremesa");
        setMinimumSize(new java.awt.Dimension(1135, 700));
        setResizable(false);

        btnStringGerenciador.setBackground(new java.awt.Color(0, 102, 204));
        btnStringGerenciador.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        btnStringGerenciador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringGerenciador.setText("Nova Sobremesa");

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        jtSobremesas.setBorder(new javax.swing.border.MatteBorder(null));
        jtSobremesas.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
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
        jScrollPane1.setViewportView(jtSobremesas);
        if (jtSobremesas.getColumnModel().getColumnCount() > 0) {
            jtSobremesas.getColumnModel().getColumn(0).setResizable(false);
            jtSobremesas.getColumnModel().getColumn(1).setResizable(false);
            jtSobremesas.getColumnModel().getColumn(2).setResizable(false);
            jtSobremesas.getColumnModel().getColumn(3).setResizable(false);
        }

        lblStringSelecione.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        lblStringSelecione.setText("Lista de Sobremesas");

        btnConfirmar.setBackground(new java.awt.Color(0, 153, 0));
        btnConfirmar.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
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
        btnCancelar.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\cancel.png")); // NOI18N
        btnCancelar.setText("   Cancelar");
        btnCancelar.setBorder(new javax.swing.border.MatteBorder(null));
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblStringCodigo.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        lblStringCodigo.setText("Código da Sobremesa:");

        txtIdBebida.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtIdBebida.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIdBebidaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIdBebidaFocusLost(evt);
            }
        });
        txtIdBebida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdBebidaActionPerformed(evt);
            }
        });

        SpinnerQtd.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
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

        lblStringQuantidade.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        lblStringQuantidade.setText("Quantidade:");

        lblErro.setFont(new java.awt.Font("Comic Sans MS", 0, 25)); // NOI18N
        lblErro.setForeground(new java.awt.Color(255, 0, 0));
        lblErro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblErro.setText("ID Inválido");

        lblStringNomeProduto.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        lblStringNomeProduto.setText("Nome:");

        txtPesquisa.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N

        btnOk.setBackground(new java.awt.Color(0, 153, 204));
        btnOk.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
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

        lblStringComanda.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        lblStringComanda.setForeground(new java.awt.Color(255, 0, 0));
        lblStringComanda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStringComanda.setText("Comanda");

        lblComanda.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        lblComanda.setForeground(new java.awt.Color(255, 0, 0));
        lblComanda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComanda.setText("X");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblStringSelecione)
                                .addGap(103, 103, 103)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77)
                                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 41, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblStringQuantidade)
                                            .addComponent(lblStringCodigo))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtIdBebida, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(SpinnerQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(146, 146, 146))
                                    .addComponent(lblErro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblStringNomeProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStringComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(274, 274, 274)
                .addComponent(btnStringGerenciador, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1135, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha2, javax.swing.GroupLayout.DEFAULT_SIZE, 1135, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(583, 583, 583)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(551, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStringGerenciador))
                .addGap(30, 30, 30)
                .addComponent(lblStringSelecione)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblStringNomeProduto)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStringCodigo)
                            .addComponent(txtIdBebida, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStringQuantidade)
                            .addComponent(SpinnerQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblErro)
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(128, 128, 128))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(67, 67, 67)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(628, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(684, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(92, 92, 92)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(28, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        LogDAO logDao = new LogDAO();
        ItemComandaDAO item = new ItemComandaDAO();
        String id1 = txtIdBebida.getText();
        Integer idSelecionado = Integer.parseInt(id1);
        boolean flag = true;
        Integer qtdInt = (Integer)SpinnerQtd.getValue();
        String qtd = Integer.toString(qtdInt);
        System.out.println("Quantidade adicionada: "+qtdInt);
        for(Produto prod:listaProdutos){
            if(prod.getIdSistema().equals(idSelecionado)){
                if(!prod.getQtdEstoque().equals("X")){
                    if(Integer.parseInt(prod.getQtdEstoque()) < qtdInt){
                        lblErro.setText("Quantidade em estoque insuficiente");
                        lblErro.setVisible(true);
                    }else{
                        Log l = new Log();
                        GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1).setItens(prod, qtd);
                        GerenciadorEstoque.retirarEstoque(prod, qtdInt);
                        item.create(GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1), prod, qtdInt);
                        l.setCategoria("Estoque");
                        l.setData(l.dataAtual());
                        l.setDescricao("João retirou "+qtd+" de \""+prod.getNome()+"\" do estoque");
                        logDao.create(l);
                        flag = false;
                    }
                }else{
                    GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1).setItens(prod, qtd);
                    item.create(GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1), prod, qtdInt);
                    flag = false;
                }
            }
        }
        if(flag){
            lblErro.setVisible(true);
        }else{
            new GerenciadorComandas().setVisible(true);
            dispose();
        }
        
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        new GerenciadorComandas().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtIdBebidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdBebidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdBebidaActionPerformed

    private void txtIdBebidaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdBebidaFocusGained
        lblErro.setText("ID Inválido");
        lblErro.setVisible(false);
    }//GEN-LAST:event_txtIdBebidaFocusGained

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

    private void txtIdBebidaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdBebidaFocusLost

    }//GEN-LAST:event_txtIdBebidaFocusLost

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
    public static javax.swing.JLabel lblErro;
    private javax.swing.JLabel lblStringCodigo;
    public static javax.swing.JLabel lblStringComanda;
    private javax.swing.JLabel lblStringNomeProduto;
    private javax.swing.JLabel lblStringQuantidade;
    private javax.swing.JLabel lblStringSelecione;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    private javax.swing.JTextField txtIdBebida;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}


