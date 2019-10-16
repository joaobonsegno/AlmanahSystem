package main;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.bean.Log;
import model.bean.Produto;
import model.bean.PromocaoUm;
import model.dao.ItemComandaDAO;
import model.dao.LogDAO;
import model.dao.ProdutoDAO;
import model.dao.PromocaoUmDAO;

public class NovaBebida extends javax.swing.JFrame {
    ArrayList<Produto> listaProdutos = new ArrayList<>();
    
    public NovaBebida() {
        initComponents();
        formatarTabela();
        String comString = Integer.toString(GerenciadorComandas.idSelecionado);
        lblComanda.setText(comString);
        SpinnerNumberModel nm = new SpinnerNumberModel(1, 1, 50, 1);
        SpinnerQtd.setModel(nm);
        ProdutoDAO pDao = new ProdutoDAO();
        for (Produto p: pDao.read()){
            if(p.getCategoria().getNome().equals("Bebida")|p.getCategoria().getNome().equals("Suco")){
                listaProdutos.add(p);
            }
        }
        criarTabela();
        this.setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnConfirmar);
        /*JFormattedTextField tf = ((JSpinner.DefaultEditor) SpinnerQtd.getEditor()).getTextField();
        tf.setEditable(false);
        SpinnerQtd.setModel(nm);*/
    }
    
    public void formatarTabela(){
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();       
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        
        jtBebidas.setRowHeight(26);
        jtBebidas.getColumnModel().getColumn(0).setPreferredWidth(0); 
        jtBebidas.getColumnModel().getColumn(1).setPreferredWidth(530);
        jtBebidas.getColumnModel().getColumn(2).setPreferredWidth(60);
        jtBebidas.getColumnModel().getColumn(3).setPreferredWidth(110);
        
        jtBebidas.getColumnModel().getColumn(0).setMinWidth(0);
        jtBebidas.getColumnModel().getColumn(1).setMinWidth(530);
        jtBebidas.getColumnModel().getColumn(2).setMinWidth(60);
        jtBebidas.getColumnModel().getColumn(3).setMinWidth(110);
        
        jtBebidas.getColumnModel().getColumn(0).setMaxWidth(0);
        jtBebidas.getColumnModel().getColumn(1).setMaxWidth(530);
        jtBebidas.getColumnModel().getColumn(2).setMaxWidth(60);
        jtBebidas.getColumnModel().getColumn(3).setMaxWidth(110);
   
        jtBebidas.getColumnModel().getColumn(2).setCellRenderer(centro);
        jtBebidas.getColumnModel().getColumn(3).setCellRenderer(direita);
    }
    
    public void limparTabela(){
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtBebidas.getModel();
        int i = dtmBebidas.getRowCount();
        
        for (int j = 0; j < i; j++){
            dtmBebidas.removeRow(0);
        }
    }
    
    public void criarTabela(){        
        ProdutoDAO pDao = new ProdutoDAO();
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtBebidas.getModel();
        for (Produto p: pDao.read()){
            p.getCategoria().getId();
            if(p.getCategoria().getNome().equals("Bebida")){
                String valor = GerenciadorComandas.valorMonetario(p.getPreco());
                
                dtmBebidas.addRow(
                    new Object[]{
                        p.getIdProduto(),
                        p.getNome(),
                        p.getQtdEstoque(),                       
                        valor}
                );
            }
        }

        PromocaoUmDAO promoDao = new PromocaoUmDAO();
        PromocaoUm promocaoUm = promoDao.read();
        for (Produto p: pDao.read()){
            if (Menu.flagDia){
                p.getCategoria().getId();
                if(p.getCategoria().getNome().equals("Suco")){
                    String valor = GerenciadorComandas.valorMonetario(p.getPrecoComDesconto());

                    dtmBebidas.addRow(
                        new Object[]{
                            p.getIdProduto(),
                            p.getNome()+" (PROMOÇÃO)",
                            p.getQtdEstoque(),
                            valor}
                    );
                }
            }else{
                p.getCategoria().getId();
                if(p.getCategoria().getNome().equals("Suco")){
                    String valor = GerenciadorComandas.valorMonetario(p.getPreco());

                    dtmBebidas.addRow(
                        new Object[]{
                            p.getIdProduto(),
                            p.getNome(),
                            p.getQtdEstoque(),
                            valor}
                    );
                }
            }
        }
    }
    
    public void criarTabelaNome(String nome){
        limparTabela();
        ProdutoDAO pDao = new ProdutoDAO();
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtBebidas.getModel();
        for (Produto p: pDao.readForNome(txtPesquisa.getText())){
            p.getCategoria().getId();
            if(p.getCategoria().getNome().equals("Bebida")){
                String valor = GerenciadorComandas.valorMonetario(p.getPreco());
                
                dtmBebidas.addRow(
                    new Object[]{
                        p.getIdProduto(),
                        p.getNome(),
                        p.getQtdEstoque(),                       
                        valor}
                );
            }
        }

        PromocaoUmDAO promoDao = new PromocaoUmDAO();
        PromocaoUm promocaoUm = promoDao.read();
        for (Produto p: pDao.readForNome(txtPesquisa.getText())){
            if (Menu.flagDia){
                p.getCategoria().getId();
                if(p.getCategoria().getNome().equals("Suco")){
                    String valor = GerenciadorComandas.valorMonetario(p.getPrecoComDesconto());

                    dtmBebidas.addRow(
                        new Object[]{
                            p.getIdProduto(),
                            p.getNome()+" (PROMOÇÃO)",
                            p.getQtdEstoque(),
                            valor}
                    );
                }
            }else{
                p.getCategoria().getId();
                if(p.getCategoria().getNome().equals("Suco")){
                    String valor = GerenciadorComandas.valorMonetario(p.getPreco());

                    dtmBebidas.addRow(
                        new Object[]{
                            p.getIdProduto(),
                            p.getNome(),
                            p.getQtdEstoque(),
                            valor}
                    );
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringGerenciador = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        jScrollPane1 = new javax.swing.JScrollPane();
        jtBebidas = new javax.swing.JTable();
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
        setMaximumSize(new java.awt.Dimension(770, 754));
        setMinimumSize(new java.awt.Dimension(770, 754));
        setResizable(false);

        btnStringGerenciador.setBackground(new java.awt.Color(0, 102, 204));
        btnStringGerenciador.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringGerenciador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringGerenciador.setText("Nova Bebida");

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        jtBebidas.setBorder(new javax.swing.border.MatteBorder(null));
        jtBebidas.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jtBebidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Qtd", "Preço (R$)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtBebidas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtBebidasFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jtBebidas);
        if (jtBebidas.getColumnModel().getColumnCount() > 0) {
            jtBebidas.getColumnModel().getColumn(0).setResizable(false);
            jtBebidas.getColumnModel().getColumn(1).setResizable(false);
            jtBebidas.getColumnModel().getColumn(2).setResizable(false);
            jtBebidas.getColumnModel().getColumn(3).setResizable(false);
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
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblStringComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblStringNomeProduto)
                                .addGap(6, 6, 6)
                                .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 175, Short.MAX_VALUE)))
                .addGap(35, 35, 35))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnStringGerenciador, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(lblStringCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SpinnerQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(197, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha2, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnStringGerenciador))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStringComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(694, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(740, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    public int getIndiceProduto(Integer idSelecionado){
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
        idSelecionado = (Integer)jtBebidas.getValueAt(jtBebidas.getSelectedRow(), 0); 
        boolean flag = true;
        Integer qtdInt = (Integer)SpinnerQtd.getValue();
        String qtd = Integer.toString(qtdInt);
        for(Produto prod:listaProdutos){
            if(prod.getIdProduto().equals(idSelecionado)){
                if(!prod.getQtdEstoque().equals("X")){
                    if(Integer.parseInt(prod.getQtdEstoque()) < qtdInt){
                        JOptionPane.showMessageDialog(null, "Quantidade do produto em estoque é insuficiente.");
                    }else{
                        int indiceDoProduto = getIndiceProduto(idSelecionado);
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
                        logDao.create(l);
                    }
                }else{
                    int indiceDoProduto = getIndiceProduto(idSelecionado);
                    if (indiceDoProduto != -1){
                        Integer quantidade = Integer.parseInt(GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1).getQntEspecifica(indiceDoProduto));
                        quantidade += qtdInt;
                        qtd = Integer.toString(quantidade);
                        GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1).setItensComVerificacao(prod, qtd);
                        item.update(GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1), prod, quantidade);
                        flag = false;
                    }else{
                        GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1).setItensComVerificacao(prod, qtd);
                        item.create(GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado-1), prod, qtdInt);
                        flag = false;
                    }
                }
            }
        }
        if(!flag){
            //new GerenciadorComandas().setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
//        new GerenciadorComandas().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        criarTabelaNome(txtPesquisa.getText());
        jtBebidas.clearSelection();
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

    private void jtBebidasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtBebidasFocusGained

    }//GEN-LAST:event_jtBebidasFocusGained

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
            java.util.logging.Logger.getLogger(NovaBebida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NovaBebida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NovaBebida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NovaBebida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NovaBebida().setVisible(true);
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
    private static javax.swing.JTable jtBebidas;
    public static javax.swing.JLabel lblComanda;
    private javax.swing.JLabel lblStringCodigo1;
    public static javax.swing.JLabel lblStringComanda;
    private javax.swing.JLabel lblStringNomeProduto;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}


