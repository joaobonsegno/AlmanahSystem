package main;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Cargo;
import model.bean.Funcionario;
import model.dao.CargoDAO;
import model.dao.FuncionarioDAO;
import manual.Manual;

public class GerenciadorFuncionario extends javax.swing.JFrame {

    FuncionarioDAO fDao = new FuncionarioDAO();
    public static Funcionario funcSelecionado;

    public GerenciadorFuncionario() {
        initComponents();
        formatarTabela();
        this.setLocationRelativeTo(null);  
        btnAlterar.setEnabled(false);
        btnInativar.setEnabled(false);
        criarTabela();
        criarComboBox();
    }

    public void criarTabela() {
        limparTabela();
        ArrayList<Funcionario> ordenador = new ArrayList<>();

        for (Funcionario func : fDao.read()) {
            ordenador.add(func);
        }
        //ordenador = listaProdutos.sort(listaProdutos);
        Collections.sort(ordenador);

        DefaultTableModel dtmBebidas = (DefaultTableModel) jtFuncionarios.getModel();
        for (Funcionario f : ordenador) {
            dtmBebidas.addRow(
                    new Object[]{
                        f.getIdFuncionario(),
                        f.getNome(),
                        f.getCargo().getNome(),
                        f.getCpf()}
            );
        }
    }

    public void criarTabelaNome(String nome) {
        ArrayList<Funcionario> ordenador = new ArrayList<>();

        for (Funcionario func : fDao.readForNome(nome)) {
            ordenador.add(func);
        }
        Collections.sort(ordenador);

        DefaultTableModel dtmBebidas = (DefaultTableModel) jtFuncionarios.getModel();
        limparTabela();
        for (Funcionario f : ordenador) {
            dtmBebidas.addRow(
                    new Object[]{
                        f.getIdFuncionario(),
                        f.getNome(),
                        f.getCargo().getNome(),
                        f.getCpf()}
            );
        }
    }

    public void criarTabelaCargo(String nome) {
        limparTabela();
        ArrayList<Funcionario> ordenador = new ArrayList<>();

        for (Funcionario func : fDao.readForCargo(nome)) {
            ordenador.add(func);
        }
        //ordenador = listaProdutos.sort(listaProdutos);
        Collections.sort(ordenador);

        DefaultTableModel dtmBebidas = (DefaultTableModel) jtFuncionarios.getModel();
        for (Funcionario f : ordenador) {
            dtmBebidas.addRow(
                    new Object[]{
                        f.getIdFuncionario(),
                        f.getNome(),
                        f.getCargo().getNome(),
                        f.getCpf()}
            );
        }
    }

    public void limparTabela() {
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtFuncionarios.getModel();
        int i = dtmBebidas.getRowCount();

        for (int j = 0; j < i; j++) {
            dtmBebidas.removeRow(0);
        }
    }

    public void criarComboBox() {
        cbCargos.removeAllItems();
        cbCargos.addItem("");
        CargoDAO cargoDao = new CargoDAO();
        for (Cargo c : cargoDao.read()) {
            cbCargos.addItem(c.getNome());
        }
    }

    public void formatarTabela(){
        jtFuncionarios.setRowHeight(25);
        jtFuncionarios.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtFuncionarios.getColumnModel().getColumn(1).setPreferredWidth(350);
        jtFuncionarios.getColumnModel().getColumn(2).setPreferredWidth(150);
        jtFuncionarios.getColumnModel().getColumn(3).setPreferredWidth(150);

        jtFuncionarios.getColumnModel().getColumn(0).setMinWidth(0);
        jtFuncionarios.getColumnModel().getColumn(1).setMinWidth(350);
        jtFuncionarios.getColumnModel().getColumn(2).setMinWidth(150);
        jtFuncionarios.getColumnModel().getColumn(3).setMinWidth(150);

        jtFuncionarios.getColumnModel().getColumn(0).setMaxWidth(0);
        jtFuncionarios.getColumnModel().getColumn(1).setMaxWidth(350);
        jtFuncionarios.getColumnModel().getColumn(2).setMaxWidth(150);
        jtFuncionarios.getColumnModel().getColumn(3).setMaxWidth(150);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringProdutos = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnLancador = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtFuncionarios = new javax.swing.JTable();
        btnAlterar = new javax.swing.JButton();
        btnInativar = new javax.swing.JButton();
        lblStringNomeProduto = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        lblStringNomeProduto1 = new javax.swing.JLabel();
        cbCargos = new javax.swing.JComboBox<>();
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnLancador1 = new javax.swing.JButton();
        lblManual = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gerenciador de Funcionários");
        setMaximumSize(new java.awt.Dimension(757, 639));
        setMinimumSize(new java.awt.Dimension(757, 639));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnStringProdutos.setBackground(new java.awt.Color(0, 102, 204));
        btnStringProdutos.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringProdutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringProdutos.setText("Gerenciador de Funcionários");
        getContentPane().add(btnStringProdutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);
        getContentPane().add(linha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 75, 757, -1));

        btnLancador.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        btnLancador.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\voltar (1).png")); // NOI18N
        btnLancador.setText("Menu");
        btnLancador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancadorActionPerformed(evt);
            }
        });
        getContentPane().add(btnLancador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 24, -1, 40));

        jtFuncionarios.setBorder(new javax.swing.border.MatteBorder(null));
        jtFuncionarios.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jtFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Cargo", "CPF"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtFuncionarios.getTableHeader().setReorderingAllowed(false);
        jtFuncionarios.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtFuncionariosFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jtFuncionarios);
        if (jtFuncionarios.getColumnModel().getColumnCount() > 0) {
            jtFuncionarios.getColumnModel().getColumn(0).setResizable(false);
            jtFuncionarios.getColumnModel().getColumn(1).setResizable(false);
            jtFuncionarios.getColumnModel().getColumn(2).setResizable(false);
            jtFuncionarios.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 213, 627, 344));

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
        getContentPane().add(btnAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 570, 170, 53));

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
        getContentPane().add(btnInativar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 570, 170, 53));

        lblStringNomeProduto.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto.setText("Nome do Funcionário:");
        getContentPane().add(lblStringNomeProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 101, 218, -1));

        txtPesquisa.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
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
        getContentPane().add(txtPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(257, 94, 327, 42));

        lblStringNomeProduto1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto1.setText("Exibir Somente:");
        getContentPane().add(lblStringNomeProduto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 154, -1, -1));

        cbCargos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cbCargos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCargos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCargosItemStateChanged(evt);
            }
        });
        cbCargos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbCargosFocusGained(evt);
            }
        });
        cbCargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCargosActionPerformed(evt);
            }
        });
        getContentPane().add(cbCargos, new org.netbeans.lib.awtextra.AbsoluteConstraints(257, 147, 161, 41));

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);
        getContentPane().add(linha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 11, 757, -1));

        btnLancador1.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        btnLancador1.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\inativo true (1).png")); // NOI18N
        btnLancador1.setText("Inativos");
        btnLancador1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnLancador1FocusGained(evt);
            }
        });
        btnLancador1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancador1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnLancador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 25, -1, -1));

        lblManual.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lblManual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblManual.setText("?");
        lblManual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(lblManual, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 610, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLancadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancadorActionPerformed
        new Menu().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLancadorActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        //Menu.acaoEscolhida = 2;
        Integer idSelecionado = (Integer) jtFuncionarios.getValueAt(jtFuncionarios.getSelectedRow(), 0);

        for (Funcionario f : fDao.read()) {
            if (f.getIdFuncionario().equals(idSelecionado)) {
                funcSelecionado = f;
                new AlterarFuncionario().setVisible(true);
                dispose();
            }
        }

    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnInativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInativarActionPerformed
        try {
            if (jtFuncionarios.getSelectedRowCount() > 1) {
                JOptionPane.showMessageDialog(null, "Selecione somente 1 (um) funcionário");
            } else {
                int reply = JOptionPane.showConfirmDialog(null, "Deseja realmente inativar o funcionário?", "Inativação de Funcionário", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    Integer idSelecionado = (Integer) jtFuncionarios.getValueAt(jtFuncionarios.getSelectedRow(), 0);

                    for (Funcionario f : fDao.read()) {
                        if (f.getIdFuncionario() == idSelecionado) {
                            fDao.setInativo(f.getIdFuncionario());
                            this.criarTabela();
                            btnAlterar.setEnabled(false);
                            btnInativar.setEnabled(false);
                            jtFuncionarios.clearSelection();
                        }
                    }
                }
            }

        } catch (java.util.ConcurrentModificationException ex) {
            System.out.println("Deu a exceção");
        } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Selecione o funcionário que deseja inativar");
        }
    }//GEN-LAST:event_btnInativarActionPerformed

    private void cbCargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCargosActionPerformed

    }//GEN-LAST:event_cbCargosActionPerformed

    private void cbCargosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCargosItemStateChanged
        String escolhido = (String) cbCargos.getSelectedItem();
        txtPesquisa.setText("");
        try {
            if (!escolhido.equals("")) {
                criarTabelaCargo(escolhido);
            } else {
                criarTabela();
            }
        } catch (java.lang.NullPointerException ex) {

        }
    }//GEN-LAST:event_cbCargosItemStateChanged

    private void btnLancador1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancador1ActionPerformed
        InativosFuncionarios novoPrato = new InativosFuncionarios(new javax.swing.JFrame(), true);
        novoPrato.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLancador1ActionPerformed

    private void jtFuncionariosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtFuncionariosFocusGained
        btnAlterar.setEnabled(true);
        btnInativar.setEnabled(true);
    }//GEN-LAST:event_jtFuncionariosFocusGained

    private void txtPesquisaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesquisaFocusGained
        cbCargos.setSelectedIndex(0);
        btnAlterar.setEnabled(false);
        btnInativar.setEnabled(false);
        jtFuncionarios.clearSelection();
    }//GEN-LAST:event_txtPesquisaFocusGained

    private void cbCargosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbCargosFocusGained
        btnAlterar.setEnabled(false);
        btnInativar.setEnabled(false);
        jtFuncionarios.clearSelection();
    }//GEN-LAST:event_cbCargosFocusGained

    private void btnLancador1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnLancador1FocusGained
        btnAlterar.setEnabled(false);
        btnInativar.setEnabled(false);
        jtFuncionarios.clearSelection();
    }//GEN-LAST:event_btnLancador1FocusGained

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        String entrada = txtPesquisa.getText();
        if (entrada.length() >= 3) {
            this.criarTabelaNome(entrada);
        } else {
            this.criarTabela();
        }
    }//GEN-LAST:event_txtPesquisaKeyReleased

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
            java.util.logging.Logger.getLogger(GerenciadorFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciadorFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciadorFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciadorFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciadorFuncionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnInativar;
    private javax.swing.JButton btnLancador;
    private javax.swing.JButton btnLancador1;
    private javax.swing.JLabel btnStringProdutos;
    private javax.swing.JComboBox<String> cbCargos;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtFuncionarios;
    private javax.swing.JLabel lblManual;
    private javax.swing.JLabel lblStringNomeProduto;
    private javax.swing.JLabel lblStringNomeProduto1;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
