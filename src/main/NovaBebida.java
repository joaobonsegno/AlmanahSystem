package main;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import static main.GerenciadorComandas.idsAbertos;
import model.bean.Comanda;
import model.bean.Log;
import model.bean.Produto;
import model.bean.PromocaoUm;
import model.dao.ComandaDAO;
import model.dao.ItemComandaDAO;
import model.dao.LogDAO;
import model.dao.ProdutoDAO;
import model.dao.PromocaoUmDAO;

public class NovaBebida extends javax.swing.JFrame {
    ArrayList<Produto> listaProdutos = new ArrayList<>();
    
    public NovaBebida() {
        initComponents();
        formatarTabela();
        SpinnerNumberModel nm = new SpinnerNumberModel(1, 1, 50, 1);
        SpinnerQtd.setModel(nm);
        criarTabela();
        this.setLocationRelativeTo(null);
        txtNumeroComanda.requestFocus();
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
        limparTabela();
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
        SpinnerQtd = new javax.swing.JSpinner();
        lblStringCodigo1 = new javax.swing.JLabel();
        lblStringNomeProduto = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        btnLancador = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtNumeroComanda = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Nova Bebida");
        setMaximumSize(new java.awt.Dimension(770, 670));
        setMinimumSize(new java.awt.Dimension(770, 670));
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SpinnerQtdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SpinnerQtdKeyTyped(evt);
            }
        });

        lblStringCodigo1.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lblStringCodigo1.setText("Quantidade:");

        lblStringNomeProduto.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto.setText("Nome:");

        txtPesquisa.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        txtPesquisa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPesquisaFocusGained(evt);
            }
        });
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });

        btnLancador.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        btnLancador.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\voltar (1).png")); // NOI18N
        btnLancador.setText("Voltar");
        btnLancador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancadorActionPerformed(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        txtNumeroComanda.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNumeroComanda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumeroComandaFocusGained(evt);
            }
        });
        txtNumeroComanda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroComandaKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Código:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumeroComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(btnLancador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                .addComponent(btnStringGerenciador, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(291, 291, 291))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblStringCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SpinnerQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblStringNomeProduto)
                                .addGap(6, 6, 6)
                                .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(35, 35, 35))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha2, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStringGerenciador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringNomeProduto)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SpinnerQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblStringCodigo1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(610, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(658, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    public int getIndiceProduto(Integer idSelecionado, Comanda c){
        boolean flag = true;
        int contador = -1;
        for (Produto p:c.getItens()){
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
        txtNumeroComanda.requestFocus();
    }//GEN-LAST:event_jtBebidasFocusGained

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed

        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void SpinnerQtdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SpinnerQtdKeyReleased
        if (evt.getKeyCode() == 10)
            txtNumeroComanda.requestFocus();
    }//GEN-LAST:event_SpinnerQtdKeyReleased

    private void txtNumeroComandaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroComandaFocusGained

    }//GEN-LAST:event_txtNumeroComandaFocusGained

    private void txtNumeroComandaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroComandaKeyReleased
        ProdutoDAO pDao = new ProdutoDAO();
        if (evt.getKeyCode() == 10){
            ComandaDAO comDao = new ComandaDAO();
            int cod = comDao.codComanda(txtNumeroComanda.getText());
            if (cod == 0){
                if (!txtNumeroComanda.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Código de comanda inválido");
            }else{
                txtNumeroComanda.setText("");
                boolean flagComandaAberta = false;
                for (Integer i : idsAbertos){
                    if (i == cod){
                        flagComandaAberta = true;
                        break;
                    }
                }
                if (!flagComandaAberta){
                    GerenciadorComandas.novaComanda(cod);
                }
                try{
                    boolean muitosSelecionados = false;
                    boolean flag = true;
                    if (jtBebidas.getSelectedRowCount() > 1){
                        JOptionPane.showMessageDialog(null, "Selecione somente uma bebida");
                        muitosSelecionados = true;
                    }
                    if (!muitosSelecionados){
                        
                        int indice = GerenciadorComandas.retornaIndice(cod);
                        
                        LogDAO logDao = new LogDAO();
                        ItemComandaDAO item = new ItemComandaDAO();
                        String id1;
                        Integer idSelecionado;
                        idSelecionado = (Integer)jtBebidas.getValueAt(jtBebidas.getSelectedRow(), 0);                     
                        Integer qtdInt = (Integer)SpinnerQtd.getValue();
                        String qtd = Integer.toString(qtdInt);
                        for(Produto prod:pDao.read()){
                            if(prod.getIdProduto().equals(idSelecionado)){
                                if(!prod.getQtdEstoque().equals("X")){
                                    if(Integer.parseInt(prod.getQtdEstoque()) < qtdInt){
                                        JOptionPane.showMessageDialog(null, "Quantidade do produto em estoque é insuficiente.");
                                    }else{
                                        int indiceDoProduto = getIndiceProduto(idSelecionado,GerenciadorComandas.comandasAbertas.get(indice));
                                        if (indiceDoProduto != -1){
                                            Integer quantidade = Integer.parseInt(GerenciadorComandas.comandasAbertas.get(indice).getQntEspecifica(indiceDoProduto));
                                            quantidade += qtdInt;
                                            qtd = Integer.toString(quantidade);
                                            GerenciadorComandas.comandasAbertas.get(indice).removerProduto(indiceDoProduto);
                                            GerenciadorComandas.comandasAbertas.get(indice).setItens(prod, qtd);
                                            GerenciadorEstoque.retirarEstoque(prod, qtdInt);
                                            item.update(GerenciadorComandas.comandasAbertas.get(indice), prod, quantidade);
                                            flag = false;
                                        }else{               
                                            GerenciadorComandas.comandasAbertas.get(indice).setItens(prod, qtd);
                                            GerenciadorEstoque.retirarEstoque(prod, qtdInt);
                                            item.create(GerenciadorComandas.comandasAbertas.get(indice), prod, qtdInt);
                                            flag = false;
                                        }
                                        Log l = new Log();
                                        l.setCategoria("Estoque");
                                        l.setData(l.dataAtual());
                                        l.setDescricao("João retirou "+qtd+" de \""+prod.getNome()+"\" do estoque");
                                        logDao.create(l);
                                    }
                                }else{
                                    int indiceDoProduto = getIndiceProduto(idSelecionado,GerenciadorComandas.comandasAbertas.get(indice));
                                    if (indiceDoProduto != -1){
                                        Integer quantidade = Integer.parseInt(GerenciadorComandas.comandasAbertas.get(indice).getQntEspecifica(indiceDoProduto));
                                        quantidade += qtdInt;
                                        qtd = Integer.toString(quantidade);
                                        GerenciadorComandas.comandasAbertas.get(indice).setItensComVerificacao(prod, qtd);
                                        item.update(GerenciadorComandas.comandasAbertas.get(indice), prod, quantidade);
                                        flag = false;
                                    }else{
                                        GerenciadorComandas.comandasAbertas.get(indice).setItensComVerificacao(prod, qtd);
                                        item.create(GerenciadorComandas.comandasAbertas.get(indice), prod, qtdInt);
                                        flag = false;
                                    }
                                }
                            }
                        }
                    }
                    if(!flag){
                        dispose();
                    }
                }catch(java.lang.ArrayIndexOutOfBoundsException ex){
                    JOptionPane.showMessageDialog(null, "Selecione a bebida");
                }
            }
        }
    }//GEN-LAST:event_txtNumeroComandaKeyReleased

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        jtBebidas.clearSelection();
        String entrada = txtPesquisa.getText();
        if (entrada.length() >= 3){
            criarTabelaNome(txtPesquisa.getText());
        }else{
            criarTabela();
        }    
    }//GEN-LAST:event_txtPesquisaKeyReleased

    private void txtPesquisaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesquisaFocusGained
        jtBebidas.clearSelection();
    }//GEN-LAST:event_txtPesquisaFocusGained

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
    private javax.swing.JButton btnLancador;
    private javax.swing.JLabel btnStringGerenciador;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtBebidas;
    private javax.swing.JLabel lblStringCodigo1;
    private javax.swing.JLabel lblStringNomeProduto;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    private javax.swing.JTextField txtNumeroComanda;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}


