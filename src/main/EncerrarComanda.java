package main;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Comanda;
import model.bean.Produto;
import model.bean.PromocaoUm;
import model.dao.ProdutoDAO;
import model.dao.PromocaoUmDAO;

public class EncerrarComanda extends javax.swing.JFrame {
    public static boolean flagValor;
 
    public EncerrarComanda() {
        initComponents(); 
        this.setLocationRelativeTo(null);
        
        
        //Seta as informações da tela
        String comString = Integer.toString(GerenciadorComandas.idSelecionado);
        lblComanda.setText(comString);
        lblValorTotal.setText("R$ "+GerenciadorComandas.valorMonetario(GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado).getValor()));

        //Cria a tabela
        this.formatarTabela();
        this.criarTabela();
        
        //Seta a formatação da tela
        if (GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado).getCliente() != null){
            lblNome.setVisible(true);
            lblNome.setText(GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado).getCliente().getNome());
        }else{
            lblNome.setVisible(false);
        }
        btnRemover.setEnabled(false);
        

    }
    
    public void criarTabela(){

        Comanda comanda = GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado);
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
                            p.getNome(),
                            qnt.get(i),                           
                            "R$  "+valor,
                            "R$  "+valor2}
                    );
                i+=1;
                      
        }
        
        for (Double d: comanda.getPratos()){
            String valor = GerenciadorComandas.valorMonetario(d);
            String valor2 = GerenciadorComandas.valorMonetario(d);
            dtmBebidas.addRow(
                    new Object[]{
                        "X",
                        "Refeição Geral",
                        "1",                        
                        "R$  "+valor,
                        "R$  "+valor2}
                );
        }
    }
    
    public void removerItem(){
        Comanda comanda = GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado);
        
        // Verifica a quantidade de linhas selecionadas. USUÁRIO SÓ PODE REMOVER 1 LINHA (ITEM) POR VEZ
        if (jtItens.getSelectedRowCount() > 1){
            JOptionPane.showMessageDialog(null, "Selecione somente 1 (um) item por vez para remover");
        }else{           
            DefaultTableModel dtmBebidas = (DefaultTableModel) jtItens.getModel();
            Integer i = jtItens.getSelectedRow();
            
            // Pega da tabela a coluna VALOR do item selecionado, para verificar se o valor pendente da comanda é MENOR do que o valor do item sendo removido
            Double valorItem = (Double)dtmBebidas.getValueAt(i, 4);
            if (comanda.getValorPendente() < valorItem){
                JOptionPane.showMessageDialog(null, "Não foi possível remover o item, pois:\n- O valor pendente da comanda é menor do que o valor do item que está sendo removido");
            }else{
                
                // Caso o valor seja MAIOR, o processo de remoção pode prosseguir
                Integer qtdProdutos = comanda.getItens().size();
                if (i != -1){  
                    dtmBebidas.removeRow(i);
                    if (i > qtdProdutos-1){
                        Integer j = i;
                        j -= qtdProdutos;
                        comanda.removerPrato(j);
                    }else{
                        comanda.removerItem(i);
                    }
                }
                lblValorTotal.setText("R$ "+GerenciadorComandas.valorMonetario(comanda.getValor()));
                for(Comanda c:GerenciadorComandas.comandasAbertas){
                    if(comanda.getId() == c.getId()){
                        c.setValor(comanda.getValor());
                        c.setValorPendente(comanda.getValorPendente());
                    }
                }
                if (comanda.getValor() == 0){
                    new GerenciadorComandas().setVisible(true);
                    dispose();
                }
            }
             
        }      
    }
    
    public void limparTabela(){
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtItens.getModel();
        int i = dtmBebidas.getRowCount();
        
        for (int j = 0; j < i; j++){
            dtmBebidas.removeRow(0);
        }       
    }
    
    public void formatarTabela(){
        jtItens.setRowHeight(40);
        jtItens.getColumnModel().getColumn(0).setPreferredWidth(80); //ID
        jtItens.getColumnModel().getColumn(1).setPreferredWidth(420); //NOME
        jtItens.getColumnModel().getColumn(2).setPreferredWidth(80); // QTD
        jtItens.getColumnModel().getColumn(3).setPreferredWidth(120); // UNITARIO
        jtItens.getColumnModel().getColumn(4).setPreferredWidth(140); // TOTAL
        
        jtItens.getColumnModel().getColumn(0).setMinWidth(80); //ID
        jtItens.getColumnModel().getColumn(1).setMinWidth(420); //NOME
        jtItens.getColumnModel().getColumn(2).setMinWidth(80); // QTD
        jtItens.getColumnModel().getColumn(3).setMinWidth(120); // UNITARIO
        jtItens.getColumnModel().getColumn(4).setMinWidth(140); // TOTAL
        
        jtItens.getColumnModel().getColumn(0).setMaxWidth(80); //ID
        jtItens.getColumnModel().getColumn(1).setMaxWidth(420); //NOME
        jtItens.getColumnModel().getColumn(2).setMaxWidth(80); // QTD
        jtItens.getColumnModel().getColumn(3).setMaxWidth(120); // UNITARIO
        jtItens.getColumnModel().getColumn(4).setMaxWidth(140); // TOTAL
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringGerenciador = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnCancelar = new javax.swing.JButton();
        lblStringComanda = new javax.swing.JLabel();
        lblComanda = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnUnirComandas1 = new javax.swing.JButton();
        btnUnirComandas = new javax.swing.JButton();
        lblNome = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtItens = new javax.swing.JTable();
        btnRemover = new javax.swing.JButton();
        lblValorTotal = new javax.swing.JLabel();
        lblStringValorTotal = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Encerramento de Comanda");
        setMaximumSize(new java.awt.Dimension(874, 634));
        setMinimumSize(new java.awt.Dimension(874, 634));
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

        btnUnirComandas1.setBackground(new java.awt.Color(204, 204, 0));
        btnUnirComandas1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnUnirComandas1.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\link (1).png")); // NOI18N
        btnUnirComandas1.setText(" Associar Cliente");
        btnUnirComandas1.setBorder(new javax.swing.border.MatteBorder(null));
        btnUnirComandas1.setBorderPainted(false);
        btnUnirComandas1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnUnirComandas1FocusGained(evt);
            }
        });
        btnUnirComandas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnirComandas1ActionPerformed(evt);
            }
        });

        btnUnirComandas.setBackground(new java.awt.Color(0, 153, 204));
        btnUnirComandas.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnUnirComandas.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\turno.png")); // NOI18N
        btnUnirComandas.setText("  Unir Comandas");
        btnUnirComandas.setBorder(new javax.swing.border.MatteBorder(null));
        btnUnirComandas.setBorderPainted(false);
        btnUnirComandas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnUnirComandasFocusGained(evt);
            }
        });
        btnUnirComandas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnirComandasActionPerformed(evt);
            }
        });

        lblNome.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblNome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnUnirComandas, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUnirComandas1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUnirComandas1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUnirComandas, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jtItens.setBorder(new javax.swing.border.MatteBorder(null));
        jtItens.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jtItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Qtd", "Unitário", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtItens.getTableHeader().setReorderingAllowed(false);
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

        btnRemover.setBackground(new java.awt.Color(204, 0, 51));
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

        lblValorTotal.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        lblValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValorTotal.setText("R$ 0,00");
        lblValorTotal.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        lblStringValorTotal.setFont(new java.awt.Font("Century Gothic", 0, 25)); // NOI18N
        lblStringValorTotal.setText("Valor Total");

        btnConfirmar.setBackground(new java.awt.Color(0, 153, 0));
        btnConfirmar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnConfirmar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\continuar.png")); // NOI18N
        btnConfirmar.setText("  Pagamento");
        btnConfirmar.setBorder(new javax.swing.border.MatteBorder(null));
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblStringValorTotal)
                        .addGap(220, 220, 220)))
                .addGap(4, 4, 4))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 848, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblStringValorTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147)
                .addComponent(btnStringGerenciador, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(lblStringComanda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStringGerenciador)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(67, 67, 67)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(566, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(620, Short.MAX_VALUE)))
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

    private void btnUnirComandasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnirComandasActionPerformed
        UnirComandas forma = new UnirComandas(new javax.swing.JFrame(), true);
        forma.setVisible(true); 
    }//GEN-LAST:event_btnUnirComandasActionPerformed

    private void jtItensFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtItensFocusGained
        btnRemover.setEnabled(true);
    }//GEN-LAST:event_jtItensFocusGained

    private void btnUnirComandasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnUnirComandasFocusGained
        btnRemover.setEnabled(false);
        jtItens.clearSelection();
    }//GEN-LAST:event_btnUnirComandasFocusGained

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        if (GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado).getCliente() != null){
            lblNome.setVisible(true);
            lblNome.setText(GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado).getCliente().getNome());
        }else{
            lblNome.setVisible(false);
        }
        
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

    private void btnUnirComandas1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnUnirComandas1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUnirComandas1FocusGained

    private void btnUnirComandas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnirComandas1ActionPerformed
        AssociarCliente forma = new AssociarCliente(new javax.swing.JFrame(), true);
        forma.setVisible(true);
    }//GEN-LAST:event_btnUnirComandas1ActionPerformed

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
    private javax.swing.JLabel btnStringGerenciador;
    private javax.swing.JButton btnUnirComandas;
    private javax.swing.JButton btnUnirComandas1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtItens;
    public static javax.swing.JLabel lblComanda;
    private javax.swing.JLabel lblNome;
    public static javax.swing.JLabel lblStringComanda;
    private javax.swing.JLabel lblStringValorTotal;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    // End of variables declaration//GEN-END:variables
}


