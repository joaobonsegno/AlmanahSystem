package main;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.bean.Comanda;
import model.bean.Produto;
import model.bean.Venda;
import model.dao.ComandaDAO;
import model.dao.ItemComandaDAO;
import model.dao.ProdutoDAO;

public class EncerrarComanda extends javax.swing.JFrame {
    public static boolean flagValor;
    public static ArrayList<Integer> listaIds;
    public static Venda venda;
    public static boolean flagVendaEncerrada;
    public static ArrayList<String> qtdRemovida;
    public static ArrayList<Produto> prodRemovido;
    
    public EncerrarComanda() {
        initComponents(); 
        this.setLocationRelativeTo(null);
        listaIds = new ArrayList<>();
        venda = new Venda(0.0);
        btnPagamento.setEnabled(false);
        txtNumeroComanda.requestFocus();
        this.formatarTabela();
        btnRemover.setEnabled(false);
        flagVendaEncerrada = false;
        qtdRemovida = new ArrayList<>();
        prodRemovido = new ArrayList<>();
    }
    
    public void adicionarPratoTabela(Double d, Comanda c){
        DefaultTableModel dtmComandas = (DefaultTableModel) jtItens.getModel();
        Object[] dados = {c.getId(), "Refeição Geral", "1", "R$ "+GerenciadorComandas.valorMonetario(d), "R$ "+GerenciadorComandas.valorMonetario(d)};
        dtmComandas.addRow(dados);
        
    }
    
    public void adicionarProdutoTabela(Produto p, String q, Comanda c){
        int qtd = Integer.parseInt(q);
        DefaultTableModel dtmComandas = (DefaultTableModel) jtItens.getModel();
        Object[] dados = {c.getId(), p.getNome(), q, "R$ "+GerenciadorComandas.valorMonetario(p.getPrecoComDesconto()), "R$ "+GerenciadorComandas.valorMonetario(p.getPrecoComDesconto()*qtd)};
        dtmComandas.addRow(dados);
    }
    
    public void removerItem(){
        Venda v = EncerrarComanda.venda;
        // Verifica a quantidade de linhas selecionadas. USUÁRIO SÓ PODE REMOVER 1 LINHA (ITEM) POR VEZ
        if (jtItens.getSelectedRowCount() > 1){
            JOptionPane.showMessageDialog(null, "Selecione somente 1 (um) item por vez para remover");
        }else{           
            DefaultTableModel dtmBebidas = (DefaultTableModel) jtItens.getModel();
            Integer i = jtItens.getSelectedRow();
            
            // Pega da tabela a coluna VALOR do item selecionado, para verificar se o valor pendente da comanda é MENOR do que o valor do item sendo removido
            String valorBruto = (String)dtmBebidas.getValueAt(i, 4);
            valorBruto = valorBruto.replace(",", ".");
            valorBruto = valorBruto.replace("R$", "");
            valorBruto = valorBruto.replace(" ", "");
            Double valorItem = Double.parseDouble(valorBruto);
            if (v.getTotalPendente() < valorItem){
                JOptionPane.showMessageDialog(null, "Não foi possível remover o item, pois:\n- O valor pendente da venda é menor do que o valor do item que está sendo removido");
            }else{               
                // Caso o valor seja MAIOR, o processo de remoção pode prosseguir
                Integer qtdPratos = v.getPratos().size(); //2
                if (i != -1){  
                    dtmBebidas.removeRow(i);
                    if (i > qtdPratos-1){ // I (índice selecionado) é maior do que a quantidade de PRATOS na venda - 1
                        //Remover PRODUTO
                        Integer j = i;
                        j -= qtdPratos;
                        qtdRemovida.add(v.getQnt().get(j));
                        prodRemovido.add(v.getItens().get(j));
                        v.removerItem(j);
                    }else{
                        // Remover PRATO
                        v.removerPrato(i);
                    }
                }
                lblValorTotal.setText("R$ "+GerenciadorComandas.valorMonetario(v.getTotal()));
                if (v.getTotal() == 0.0){
                    btnPagamento.setEnabled(false);
                }
                btnRemover.setEnabled(false);
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
        jtItens.getColumn("ID Comanda").setCellRenderer(centro);
        jtItens.getColumn("Qtd").setCellRenderer(centro);
        jtItens.getColumn("Unitário").setCellRenderer(centro);
        jtItens.getColumn("Total").setCellRenderer(centro);
        jtItens.getColumnModel().getColumn(0).setPreferredWidth(80); //ID
        jtItens.getColumnModel().getColumn(1).setPreferredWidth(450); //NOME
        jtItens.getColumnModel().getColumn(2).setPreferredWidth(80); // QTD
        jtItens.getColumnModel().getColumn(3).setPreferredWidth(120); // UNITARIO
        jtItens.getColumnModel().getColumn(4).setPreferredWidth(140); // TOTAL
        
        jtItens.getColumnModel().getColumn(0).setMinWidth(80); //ID
        jtItens.getColumnModel().getColumn(1).setMinWidth(450); //NOME
        jtItens.getColumnModel().getColumn(2).setMinWidth(80); // QTD
        jtItens.getColumnModel().getColumn(3).setMinWidth(120); // UNITARIO
        jtItens.getColumnModel().getColumn(4).setMinWidth(140); // TOTAL
        
        jtItens.getColumnModel().getColumn(0).setMaxWidth(80); //ID
        jtItens.getColumnModel().getColumn(1).setMaxWidth(450); //NOME
        jtItens.getColumnModel().getColumn(2).setMaxWidth(80); // QTD
        jtItens.getColumnModel().getColumn(3).setMaxWidth(120); // UNITARIO
        jtItens.getColumnModel().getColumn(4).setMaxWidth(140); // TOTAL
    }

    //MÉTODOS PARA ARRUMAR CÉLULAS DA TABELA
    DefaultTableCellRenderer centro = new DefaultTableCellRenderer() {
        public void setValue(Object value) {
            setHorizontalAlignment(JLabel.CENTER);
            super.setValue(value);
        }
    };

    DefaultTableCellRenderer direita = new DefaultTableCellRenderer() {
        public void setValue(Object value) {
            setHorizontalAlignment(JLabel.RIGHT);
            super.setValue(value);
        }
    };
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringGerenciador = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnAssociarCliente = new javax.swing.JButton();
        lblNome = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtNumeroComanda = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtItens = new javax.swing.JTable();
        btnRemover = new javax.swing.JButton();
        lblValorTotal = new javax.swing.JLabel();
        lblStringValorTotal = new javax.swing.JLabel();
        btnPagamento = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Encerramento de Comanda");
        setMaximumSize(new java.awt.Dimension(892, 637));
        setMinimumSize(new java.awt.Dimension(892, 637));
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

        btnAssociarCliente.setBackground(new java.awt.Color(204, 204, 0));
        btnAssociarCliente.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnAssociarCliente.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\link (1).png")); // NOI18N
        btnAssociarCliente.setText(" Associar Cliente");
        btnAssociarCliente.setBorder(new javax.swing.border.MatteBorder(null));
        btnAssociarCliente.setBorderPainted(false);
        btnAssociarCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnAssociarClienteFocusGained(evt);
            }
        });
        btnAssociarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssociarClienteActionPerformed(evt);
            }
        });

        lblNome.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblNome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumeroComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16)
                .addComponent(btnAssociarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnAssociarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jtItens.setBorder(new javax.swing.border.MatteBorder(null));
        jtItens.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jtItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Comanda", "Nome", "Qtd", "Unitário", "Total"
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

        lblValorTotal.setFont(new java.awt.Font("Century Gothic", 1, 29)); // NOI18N
        lblValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValorTotal.setText("R$ 0,00");
        lblValorTotal.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        lblStringValorTotal.setFont(new java.awt.Font("Century Gothic", 1, 25)); // NOI18N
        lblStringValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStringValorTotal.setText("Valor  Total");

        btnPagamento.setBackground(new java.awt.Color(0, 153, 0));
        btnPagamento.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnPagamento.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\continuar.png")); // NOI18N
        btnPagamento.setText("  Pagamento");
        btnPagamento.setBorder(new javax.swing.border.MatteBorder(null));
        btnPagamento.setBorderPainted(false);
        btnPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblStringValorTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(lblValorTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStringValorTotal))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(btnPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203)
                .addComponent(btnStringGerenciador)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha2, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStringGerenciador)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(67, 67, 67)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(569, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(623, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagamentoActionPerformed
        FormaPagamento forma = new FormaPagamento(new javax.swing.JFrame(), true);
        forma.setVisible(true);        
    }//GEN-LAST:event_btnPagamentoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (venda.getTotal() != 0.0){
            int reply = JOptionPane.showConfirmDialog(null, "Deseja realmente retonar ao Gerenciador de Comandas?\n(Retornar irá fazer com que todos os dados "
                                                      + "da venda atual sejam perdidos)", "Retornar ao Gerenciador de Comandas?", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                new GerenciadorComandas().setVisible(true);
                dispose();
            } 
        }else{
            new GerenciadorComandas().setVisible(true);
            dispose();
        }
               
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        removerItem();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void jtItensFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtItensFocusGained
        btnRemover.setEnabled(true);
    }//GEN-LAST:event_jtItensFocusGained

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        if (venda.getCliente() != null){
            lblNome.setText(venda.getCliente().getNome());
        }      
        btnRemover.setEnabled(false);
        jtItens.clearSelection();     
        if (flagVendaEncerrada){
            new GerenciadorComandas().setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private void btnAssociarClienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAssociarClienteFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAssociarClienteFocusGained

    private void btnAssociarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssociarClienteActionPerformed
        AssociarCliente forma = new AssociarCliente(new javax.swing.JFrame(), true);
        forma.setVisible(true);
    }//GEN-LAST:event_btnAssociarClienteActionPerformed

    private void txtNumeroComandaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroComandaFocusGained
        jtItens.clearSelection();
    }//GEN-LAST:event_txtNumeroComandaFocusGained

    private void txtNumeroComandaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroComandaKeyReleased
        if (evt.getKeyCode() == 10){
           ComandaDAO comDao = new ComandaDAO();
            int cod = comDao.codComanda(txtNumeroComanda.getText());
            if (cod == 0){
                if (!txtNumeroComanda.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Código de comanda inválido");
            }else{
                txtNumeroComanda.setText("");
                boolean flagComandaAberta = false;
                boolean flagComandaAdicionada = false;
                for (Integer i : listaIds){
                    if (i == cod){
                        flagComandaAdicionada = true;
                        flagComandaAberta = true;
                        break;
                    }
                }
                if (!flagComandaAdicionada){
                    for (Integer i : GerenciadorComandas.idsAbertos){
                        if (i == cod){                       
                            ItemComandaDAO itemDao = new ItemComandaDAO();
                            flagComandaAberta = true;
                            Comanda c = comDao.readForId(cod);
                            itemDao.read(c);
                            for (Double d : c.getPratos()){
                                venda.setPrato(d);
                                this.adicionarPratoTabela(d, c);
                            }
                            for (int j = 0; j < c.getItens().size(); j++){
                                venda.setItem(c.getItens().get(j), c.getQnt().get(j));
                                this.adicionarProdutoTabela(c.getItens().get(j), c.getQnt().get(j), c);
                            }
                            btnPagamento.setEnabled(true);
                            lblValorTotal.setText("R$ "+GerenciadorComandas.valorMonetario(venda.getTotal()));
                            listaIds.add(i);
                            break;
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Comanda "+cod+" já foi adicionada à venda");
                }
                
                if (!flagComandaAberta){
                    JOptionPane.showMessageDialog(null, "A comanda "+cod+" já está fechada");
                }
            }
        }
    }//GEN-LAST:event_txtNumeroComandaKeyReleased

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
    private javax.swing.JButton btnAssociarCliente;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnPagamento;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel btnStringGerenciador;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtItens;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblStringValorTotal;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    private javax.swing.JTextField txtNumeroComanda;
    // End of variables declaration//GEN-END:variables
}


