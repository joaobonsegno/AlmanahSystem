package main;

import ArrumarString.SoNumeros;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.DefaultTableModel;
import model.bean.Cliente;
import model.bean.Produto;
import model.dao.ClienteDAO;
import model.dao.ProdutoDAO;

public class GerenciadorCarteira extends javax.swing.JFrame {
    public static int idSelecionado;
    
    public GerenciadorCarteira() {
        initComponents();
        this.setLocationRelativeTo(null);
        btnPagamento.setEnabled(false);
        btnDetalhar.setEnabled(false);
        this.formatarTabela();
        this.criarTabela();
    }

    public void criarTabela() {
        this.limparTabela();
        ArrayList<Cliente> ordenador = new ArrayList<>();
        ClienteDAO cDao = new ClienteDAO();

        for (Cliente prod : cDao.readForSaldo()) {
            ordenador.add(prod);
        }
        Collections.sort(ordenador);

        DefaultTableModel dtmClientes = (DefaultTableModel) jtClientes.getModel();

        for (Cliente c : ordenador) {
            dtmClientes.addRow(
                    new Object[]{
                        c.getNome(),
                        c.getCpf(),
                        "R$ " + GerenciadorComandas.valorMonetario(c.getSaldo()),
                        c.getId()}
            );
        }
    }

    public void criarTabelaCpf(String cpf) {
        this.limparTabela();
        ArrayList<Cliente> ordenador = new ArrayList<>();
        ClienteDAO cDao = new ClienteDAO();
        for (Cliente cliente : cDao.readForCpf(cpf)){
            ordenador.add(cliente);
        }
        Collections.sort(ordenador);

        DefaultTableModel dtmClientes = (DefaultTableModel) jtClientes.getModel();
        limparTabela();
        for (Cliente c : ordenador) {
            dtmClientes.addRow(
                new Object[]{
                    c.getNome(),
                    c.getCpf(),
                    "R$ " + GerenciadorComandas.valorMonetario(c.getSaldo()),
                    c.getId()}
            );
        }
    }

    public void limparTabela() {
        DefaultTableModel dtmProdutos = (DefaultTableModel) jtClientes.getModel();
        int i = dtmProdutos.getRowCount();

        for (int j = 0; j < i; j++) {
            dtmProdutos.removeRow(0);
        }
    }

    public void formatarTabela() {
        jtClientes.setRowHeight(27);
        jtClientes.getColumnModel().getColumn(0).setPreferredWidth(400);
        jtClientes.getColumnModel().getColumn(1).setPreferredWidth(160);
        jtClientes.getColumnModel().getColumn(2).setPreferredWidth(120);
        jtClientes.getColumnModel().getColumn(3).setPreferredWidth(0);
        
        jtClientes.getColumnModel().getColumn(0).setMinWidth(400);
        jtClientes.getColumnModel().getColumn(1).setMinWidth(160);
        jtClientes.getColumnModel().getColumn(2).setMinWidth(120);
        jtClientes.getColumnModel().getColumn(3).setMinWidth(0);

        jtClientes.getColumnModel().getColumn(0).setMaxWidth(400);
        jtClientes.getColumnModel().getColumn(1).setMaxWidth(160);
        jtClientes.getColumnModel().getColumn(2).setMaxWidth(120);
        jtClientes.getColumnModel().getColumn(3).setMaxWidth(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringProdutos = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnLancador = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtClientes = new javax.swing.JTable();
        btnPagamento = new javax.swing.JButton();
        lblStringNomeProduto = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        btnDetalhar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gerenciador de Estoque");
        setMaximumSize(new java.awt.Dimension(734, 558));
        setMinimumSize(new java.awt.Dimension(734, 558));
        setResizable(false);

        btnStringProdutos.setBackground(new java.awt.Color(0, 102, 204));
        btnStringProdutos.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringProdutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringProdutos.setText("Gerenciador de Carteiras");

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

        jtClientes.setBorder(new javax.swing.border.MatteBorder(null));
        jtClientes.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jtClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "CPF", "Valor a pagar", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtClientes.getTableHeader().setReorderingAllowed(false);
        jtClientes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtClientesFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jtClientes);
        if (jtClientes.getColumnModel().getColumnCount() > 0) {
            jtClientes.getColumnModel().getColumn(0).setResizable(false);
            jtClientes.getColumnModel().getColumn(1).setResizable(false);
            jtClientes.getColumnModel().getColumn(2).setResizable(false);
            jtClientes.getColumnModel().getColumn(3).setResizable(false);
        }

        btnPagamento.setBackground(new java.awt.Color(0, 153, 0));
        btnPagamento.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnPagamento.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\payment (2).png")); // NOI18N
        btnPagamento.setText("  Pagamento");
        btnPagamento.setBorder(new javax.swing.border.MatteBorder(null));
        btnPagamento.setBorderPainted(false);
        btnPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagamentoActionPerformed(evt);
            }
        });

        lblStringNomeProduto.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto.setText("CPF do Cliente:");

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        txtCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCpfFocusGained(evt);
            }
        });
        txtCpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCpfKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCpfKeyTyped(evt);
            }
        });

        btnDetalhar.setBackground(new java.awt.Color(204, 204, 0));
        btnDetalhar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnDetalhar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\info (1).png")); // NOI18N
        btnDetalhar.setText("   Detalhar");
        btnDetalhar.setBorder(new javax.swing.border.MatteBorder(null));
        btnDetalhar.setBorderPainted(false);
        btnDetalhar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(lblStringNomeProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLancador)
                        .addGap(81, 81, 81)
                        .addComponent(btnStringProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnDetalhar, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(btnPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLancador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStringProdutos))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringNomeProduto)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDetalhar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(63, 63, 63)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(494, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        new Menu().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void btnPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagamentoActionPerformed
        idSelecionado = (Integer) jtClientes.getValueAt(jtClientes.getSelectedRow(), 3);
        FormaPagamentoCarteira forma = new FormaPagamentoCarteira(new javax.swing.JFrame(), true);
        forma.setVisible(true);
        dispose();

    }//GEN-LAST:event_btnPagamentoActionPerformed

    public boolean isDigit(String c) {
        try {
            Integer.parseInt(c);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private void txtCpfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfKeyReleased
        String e = txtCpf.getText();
        Character c = e.charAt(2);
        String cString = c.toString();
        if (this.isDigit(cString)){
            this.criarTabelaCpf(e);
        } else {
            this.criarTabela();
        }
    }//GEN-LAST:event_txtCpfKeyReleased

    private void txtCpfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfKeyTyped

    }//GEN-LAST:event_txtCpfKeyTyped

    private void jtClientesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtClientesFocusGained
        btnPagamento.setEnabled(true);
        btnDetalhar.setEnabled(true);
    }//GEN-LAST:event_jtClientesFocusGained

    private void txtCpfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCpfFocusGained
        btnPagamento.setEnabled(false);
        btnDetalhar.setEnabled(true);
    }//GEN-LAST:event_txtCpfFocusGained

    private void btnDetalharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalharActionPerformed
        idSelecionado = (Integer) jtClientes.getValueAt(jtClientes.getSelectedRow(), 3);
        Detalhar d = new Detalhar(new javax.swing.JFrame(), true);
        d.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnDetalharActionPerformed

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
            java.util.logging.Logger.getLogger(GerenciadorCarteira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciadorCarteira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciadorCarteira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciadorCarteira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciadorCarteira().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetalhar;
    private javax.swing.JButton btnLancador;
    private javax.swing.JButton btnPagamento;
    private javax.swing.JLabel btnStringProdutos;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtClientes;
    private javax.swing.JLabel lblStringNomeProduto;
    private javax.swing.Box.Filler linha1;
    private javax.swing.JFormattedTextField txtCpf;
    // End of variables declaration//GEN-END:variables
}
