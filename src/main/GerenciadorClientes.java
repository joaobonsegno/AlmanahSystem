package main;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Cliente;
import model.dao.ClienteDAO;
import manual.Manual;

public class GerenciadorClientes extends javax.swing.JFrame {
    ClienteDAO cDao = new ClienteDAO();
    public static Cliente clienteSelecionado;

    public GerenciadorClientes() {
        initComponents();

        jtClientes.setRowHeight(24);
        this.setLocationRelativeTo(null);
        jtClientes.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtClientes.getColumnModel().getColumn(1).setPreferredWidth(470);
        jtClientes.getColumnModel().getColumn(2).setPreferredWidth(150);

        jtClientes.getColumnModel().getColumn(0).setMinWidth(0);
        jtClientes.getColumnModel().getColumn(1).setMinWidth(470);
        jtClientes.getColumnModel().getColumn(2).setMinWidth(150);

        jtClientes.getColumnModel().getColumn(0).setMaxWidth(0);
        jtClientes.getColumnModel().getColumn(1).setMaxWidth(470);
        jtClientes.getColumnModel().getColumn(2).setMaxWidth(150);
    }

    public void criarTabelaNome(String nome){
        limparTabela();
        ArrayList<Cliente> ordenador = new ArrayList<>();
        
        for (Cliente cliente : cDao.readForNome(nome)){
            ordenador.add(cliente);
        }
        Collections.sort(ordenador);
        
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtClientes.getModel();
        for (Cliente c: ordenador){
            dtmBebidas.addRow(
                new Object[]{
                    c.getId(),
                    c.getNome(),
                    c.getCpf()}
            );           
        }
    }
    
    public void limparTabela() {
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtClientes.getModel();
        int i = dtmBebidas.getRowCount();

        for (int j = 0; j < i; j++) {
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
        jtClientes = new javax.swing.JTable();
        btnAlterar = new javax.swing.JButton();
        btnInativar = new javax.swing.JButton();
        lblStringNomeProduto = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnLancador1 = new javax.swing.JButton();
        lblManual = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gerenciador de Clientes");
        setMaximumSize(new java.awt.Dimension(729, 588));
        setMinimumSize(new java.awt.Dimension(729, 588));
        setResizable(false);

        btnStringProdutos.setBackground(new java.awt.Color(0, 102, 204));
        btnStringProdutos.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringProdutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringProdutos.setText("Gerenciador de Clientes");

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
                "ID", "Nome", "CPF"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtClientes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtClientes);
        if (jtClientes.getColumnModel().getColumnCount() > 0) {
            jtClientes.getColumnModel().getColumn(0).setResizable(false);
            jtClientes.getColumnModel().getColumn(1).setResizable(false);
            jtClientes.getColumnModel().getColumn(2).setResizable(false);
        }

        btnAlterar.setBackground(new java.awt.Color(204, 204, 0));
        btnAlterar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnAlterar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\lapis.png")); // NOI18N
        btnAlterar.setText("   Alterar");
        btnAlterar.setBorder(new javax.swing.border.MatteBorder(null));
        btnAlterar.setBorderPainted(false);
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnInativar.setBackground(new java.awt.Color(204, 0, 0));
        btnInativar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnInativar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\delete (1).png")); // NOI18N
        btnInativar.setText("   Inativar");
        btnInativar.setBorder(new javax.swing.border.MatteBorder(null));
        btnInativar.setBorderPainted(false);
        btnInativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInativarActionPerformed(evt);
            }
        });

        lblStringNomeProduto.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto.setText("Nome do Cliente:");

        txtPesquisa.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        btnLancador1.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        btnLancador1.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\inativo true (1).png")); // NOI18N
        btnLancador1.setText("Inativos");
        btnLancador1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancador1ActionPerformed(evt);
            }
        });

        lblManual.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblManual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblManual.setText("?");
        lblManual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnLancador)
                .addGap(96, 96, 96)
                .addComponent(btnStringProdutos)
                .addGap(78, 78, 78)
                .addComponent(btnLancador1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(btnInativar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblManual)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblStringNomeProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha2, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLancador)
                    .addComponent(btnStringProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLancador1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStringNomeProduto))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnInativar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblManual)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(71, 71, 71)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(517, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(569, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        new Menu().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        //Menu.acaoEscolhida = 2;
        Integer idSelecionado = (Integer) jtClientes.getValueAt(jtClientes.getSelectedRow(), 0);

        for (Cliente c : cDao.read()) {
            if (c.getId().equals(idSelecionado)) {
                clienteSelecionado = c;
                new AlterarCliente().setVisible(true);
                dispose();
            }
        }

    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnInativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInativarActionPerformed
        try {
            if (jtClientes.getSelectedRowCount() > 1) {
                JOptionPane.showMessageDialog(null, "Selecione somente 1 (um) cliente");
            } else {
                int reply = JOptionPane.showConfirmDialog(null, "Deseja realmente inativar o cliente?", "Inativação de Cliente", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    Integer idSelecionado = (Integer) jtClientes.getValueAt(jtClientes.getSelectedRow(), 0);

                    for (Cliente c : cDao.read()) {
                        if (c.getId().equals(idSelecionado)) {
                            ClienteDAO cDao = new ClienteDAO();
                            cDao.setInativo(c.getId());
                            this.limparTabela();

                        }
                    }
                }
            }
        } catch (java.util.ConcurrentModificationException ex) {
            System.out.println("Deu a exceção");
        }
    }//GEN-LAST:event_btnInativarActionPerformed

    private void btnLancador1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancador1ActionPerformed
        InativosClientes novoPrato = new InativosClientes(new javax.swing.JFrame(), true);
        novoPrato.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLancador1ActionPerformed

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        String entrada = txtPesquisa.getText();
        if (entrada.length() >= 3) {
            this.criarTabelaNome(entrada);
        } else {
            this.limparTabela();
        }
    }//GEN-LAST:event_txtPesquisaKeyReleased

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GerenciadorClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciadorClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciadorClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciadorClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new GerenciadorClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnInativar;
    private javax.swing.JButton btnLancador;
    private javax.swing.JButton btnLancador1;
    private javax.swing.JLabel btnStringProdutos;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtClientes;
    private javax.swing.JLabel lblManual;
    private javax.swing.JLabel lblStringNomeProduto;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
