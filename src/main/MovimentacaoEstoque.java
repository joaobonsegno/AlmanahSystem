package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.bean.Log;
import model.bean.Produto;
import model.dao.LogDAO;
import model.dao.ProdutoDAO;

public class MovimentacaoEstoque extends javax.swing.JFrame {
    public static ArrayList<Produto> listaProdutos = new ArrayList<>();
    LogDAO logDao;
    public static Produto prodSelecionado;
    

     
    public MovimentacaoEstoque() {
       initComponents();       
       this.setLocationRelativeTo(null);
       this.formatarTabela();
       formatarCalendar();
       criarTabela();
    }
            
    public void formatarCalendar(){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date data;       
            data = sdf.parse(GerenciadorComandas.getDataAtualFormatoUSA());
            calendarData.setDate(data);
        } catch (ParseException ex) {
            System.out.println("Erro: "+ex);
        }
    }
    
    public void formatarTabela(){
        jtLogs.setRowHeight(26);
        jtLogs.getColumn("Data").setCellRenderer(centro);
        jtLogs.getColumn("ID").setCellRenderer(centro);
        jtLogs.getColumnModel().getColumn(0).setPreferredWidth(80); 
        jtLogs.getColumnModel().getColumn(1).setPreferredWidth(620);
        jtLogs.getColumnModel().getColumn(2).setPreferredWidth(220);

        jtLogs.getColumnModel().getColumn(0).setMinWidth(80);
        jtLogs.getColumnModel().getColumn(1).setMinWidth(620);
        jtLogs.getColumnModel().getColumn(2).setMinWidth(220);

        jtLogs.getColumnModel().getColumn(0).setMaxWidth(80);
        jtLogs.getColumnModel().getColumn(1).setMaxWidth(620);
        jtLogs.getColumnModel().getColumn(2).setMaxWidth(220);
    }
    
    public void criarTabela(){
        limparTabela();
        DefaultTableModel dtmLogs = (DefaultTableModel) jtLogs.getModel();
        logDao = new LogDAO();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = sdf.format(calendarData.getDate());
        
        for (Log l:logDao.read(dataFormatada)){
            if (l.getCategoria().equals("Estoque")){
                dtmLogs.addRow(
                    new Object[]{
                        l.getId(),
                        l.getDescricao(),
                        l.getData()}
                );
            }
        }
    }
    
    public void criarTabelaNome(String nome){
        DefaultTableModel dtmLogs = (DefaultTableModel) jtLogs.getModel();
        LogDAO lDao = new LogDAO();
        limparTabela();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = sdf.format(calendarData.getDate());
        for (Log l: lDao.readForNome(nome, dataFormatada)){
            if (l.getCategoria().equals("Estoque")){
                dtmLogs.addRow(
                    new Object[]{
                        l.getId(),
                        l.getDescricao(),
                        l.getData()}
                ); 
            }
        }
    }
    
    public void limparTabela(){
        DefaultTableModel dtmLogs = (DefaultTableModel) jtLogs.getModel();
        int i = dtmLogs.getRowCount();
        
        for (int j = 0; j < i; j++){
            dtmLogs.removeRow(0);
        }       
    }

    // MÉTODOS PARA ARRUMAR CÉLULAS DA TABELA
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

        btnStringProdutos = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        jScrollPane1 = new javax.swing.JScrollPane();
        jtLogs = new javax.swing.JTable();
        btnVoltar = new javax.swing.JButton();
        lblStringNomeProduto = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        linha2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        lblAtualizar = new javax.swing.JLabel();
        calendarData = new com.toedter.calendar.JDateChooser();
        lblStringNomeProduto1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Movimentação do Estoque");
        setMaximumSize(new java.awt.Dimension(1009, 497));
        setMinimumSize(new java.awt.Dimension(1009, 497));
        setResizable(false);

        btnStringProdutos.setBackground(new java.awt.Color(0, 102, 204));
        btnStringProdutos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        btnStringProdutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringProdutos.setText("Movimentação");

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        jtLogs.setBorder(new javax.swing.border.MatteBorder(null));
        jtLogs.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jtLogs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descrição", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtLogs);
        if (jtLogs.getColumnModel().getColumnCount() > 0) {
            jtLogs.getColumnModel().getColumn(0).setResizable(false);
            jtLogs.getColumnModel().getColumn(1).setResizable(false);
            jtLogs.getColumnModel().getColumn(2).setResizable(false);
        }

        btnVoltar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnVoltar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\voltar (1).png")); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.setBorder(new javax.swing.border.MatteBorder(null));
        btnVoltar.setBorderPainted(false);
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        lblStringNomeProduto.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto.setText("Pesquisa:");

        txtPesquisa.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });

        linha2.setBackground(new java.awt.Color(0, 0, 0));
        linha2.setOpaque(true);

        lblAtualizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAtualizar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\update (1) (1).png")); // NOI18N
        lblAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAtualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAtualizarMouseClicked(evt);
            }
        });

        calendarData.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        calendarData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                calendarDataFocusGained(evt);
            }
        });
        calendarData.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                calendarDataInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        lblStringNomeProduto1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto1.setText("Data:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblStringNomeProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(321, 321, 321)
                        .addComponent(btnStringProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(lblStringNomeProduto1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(calendarData, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAtualizar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha2, javax.swing.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStringProdutos)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStringNomeProduto1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                            .addComponent(calendarData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStringNomeProduto))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(493, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(540, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        dispose();
        new Menu().setVisible(true);
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        String e = txtPesquisa.getText();
        if (e.length()>= 3){
            criarTabelaNome(e);
        }else{
            criarTabela();
        }
    }//GEN-LAST:event_txtPesquisaKeyReleased

    private void lblAtualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAtualizarMouseClicked
        //calendarioDoDia();
        if (txtPesquisa.getText().equals("")){
            criarTabela();
        }else{
            criarTabelaNome(txtPesquisa.getText());
        }
        
    }//GEN-LAST:event_lblAtualizarMouseClicked

    private void calendarDataFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_calendarDataFocusGained
       
        jtLogs.clearSelection();
    }//GEN-LAST:event_calendarDataFocusGained

    private void calendarDataInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_calendarDataInputMethodTextChanged

    }//GEN-LAST:event_calendarDataInputMethodTextChanged

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
            java.util.logging.Logger.getLogger(MovimentacaoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MovimentacaoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MovimentacaoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MovimentacaoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new MovimentacaoEstoque().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnStringProdutos;
    private javax.swing.JButton btnVoltar;
    private com.toedter.calendar.JDateChooser calendarData;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtLogs;
    private javax.swing.JLabel lblAtualizar;
    private javax.swing.JLabel lblStringNomeProduto;
    private javax.swing.JLabel lblStringNomeProduto1;
    private javax.swing.Box.Filler linha1;
    private javax.swing.Box.Filler linha2;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}


