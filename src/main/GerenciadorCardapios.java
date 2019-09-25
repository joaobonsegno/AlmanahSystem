package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.bean.Cardapio;
import model.bean.Prato;
import model.bean.Produto;
import model.dao.CardapioDAO;
import model.dao.ItemCardapioDAO;
import model.dao.PratoDAO;

public class GerenciadorCardapios extends javax.swing.JFrame {
    public static Cardapio cardapio;
    PratoDAO pDao;
    CardapioDAO cDao = new CardapioDAO();
    ItemCardapioDAO itemCDao = new ItemCardapioDAO();
    
    public GerenciadorCardapios() {        
        initComponents();       
        this.setLocationRelativeTo(null);
        this.formatarTabela();
        this.formatarCalendar();
       // lblImportar.setText("<html><u>[Importar Cardápio]</u></html>");
        btnRemover.setEnabled(false);
        this.calendarioDoDia();              
    }

    public void calendarioDoDia(){
        ItemCardapioDAO itemCardapioDao = new ItemCardapioDAO();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = sdf.format(calendarData.getDate());
        cardapio = cDao.readForData(dataFormatada);
        try{
            if (cardapio.getId()>=1){              
                btnCriar.setEnabled(false);
                btnAdicionar.setEnabled(true);
                itemCardapioDao.readForCardapio(cardapio);
                lblImportar.setVisible(true);
                this.criarTabela();
            }else{
                btnCriar.setEnabled(true);
                btnAdicionar.setEnabled(false);
                jtPratos.setEnabled(false);
                lblImportar.setVisible(false);
                limparTabela();
            }        
        }catch(java.lang.NullPointerException ex){
            System.out.println("Entrei na ex");           
        }
    }
    
    public void formatarCalendar(){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date data;       
            data = sdf.parse(GerenciadorComandas.getDataAtual());
            calendarData.setDate(data);
        } catch (ParseException ex) {
            System.out.println("Erro: "+ex);
        }
    }
    
    public void formatarTabela(){
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();       
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        jtPratos.setRowHeight(27);
        jtPratos.getColumnModel().getColumn(0).setPreferredWidth(0); 
        jtPratos.getColumnModel().getColumn(1).setPreferredWidth(550);
        
        jtPratos.getColumnModel().getColumn(0).setMinWidth(0);
        jtPratos.getColumnModel().getColumn(1).setMinWidth(550);
        
        jtPratos.getColumnModel().getColumn(0).setMaxWidth(0);
        jtPratos.getColumnModel().getColumn(1).setMaxWidth(550);
    }
    
    public void criarTabela(){
        ArrayList<Prato> ordenador = new ArrayList<>();
        
        for (Prato prato : cardapio.getPratos()){
            ordenador.add(prato);
        }
        Collections.sort(ordenador);
        
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtPratos.getModel();
        limparTabela();
        for (Prato p: ordenador){
            dtmBebidas.addRow(
                new Object[]{
                    p.getId(),
                    p.getNome()}
            );           
        }
    }
    
    public void limparTabela(){
        DefaultTableModel dtmBebidas = (DefaultTableModel) jtPratos.getModel();
        int i = dtmBebidas.getRowCount();
        
        for (int j = 0; j < i; j++){
            dtmBebidas.removeRow(0);
        }       
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStringGerenciadorCardapios = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnMenu = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnAdicionar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtPratos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblAtualizar = new javax.swing.JLabel();
        lblStringNomeProduto1 = new javax.swing.JLabel();
        calendarData = new com.toedter.calendar.JDateChooser();
        btnCriar = new javax.swing.JButton();
        lblImportar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciador de Produtos");
        setMaximumSize(new java.awt.Dimension(545, 543));
        setMinimumSize(new java.awt.Dimension(545, 543));
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        btnStringGerenciadorCardapios.setBackground(new java.awt.Color(0, 102, 204));
        btnStringGerenciadorCardapios.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        btnStringGerenciadorCardapios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStringGerenciadorCardapios.setText("Gerenciador de Cardápios");

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        btnMenu.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        btnMenu.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\voltar (1).png")); // NOI18N
        btnMenu.setText("Menu");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        btnAdicionar.setBackground(new java.awt.Color(204, 204, 0));
        btnAdicionar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnAdicionar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\lapis1.png")); // NOI18N
        btnAdicionar.setText("Adicionar Pratos");
        btnAdicionar.setBorder(new javax.swing.border.MatteBorder(null));
        btnAdicionar.setBorderPainted(false);
        btnAdicionar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnAdicionarFocusGained(evt);
            }
        });
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnRemover.setBackground(new java.awt.Color(204, 0, 0));
        btnRemover.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnRemover.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\delete (2).png")); // NOI18N
        btnRemover.setText("Remover Prato");
        btnRemover.setBorder(new javax.swing.border.MatteBorder(null));
        btnRemover.setBorderPainted(false);
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        jtPratos.setBorder(new javax.swing.border.MatteBorder(null));
        jtPratos.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jtPratos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtPratos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtPratosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtPratosFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(jtPratos);
        if (jtPratos.getColumnModel().getColumnCount() > 0) {
            jtPratos.getColumnModel().getColumn(0).setResizable(false);
            jtPratos.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblAtualizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAtualizar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\update (1) (1).png")); // NOI18N
        lblAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAtualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAtualizarMouseClicked(evt);
            }
        });

        lblStringNomeProduto1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblStringNomeProduto1.setText("Dia:");

        calendarData.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        calendarData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                calendarDataFocusGained(evt);
            }
        });
        calendarData.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                calendarDataInputMethodTextChanged(evt);
            }
        });

        btnCriar.setBackground(new java.awt.Color(51, 204, 0));
        btnCriar.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        btnCriar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\add (2) (1).png")); // NOI18N
        btnCriar.setText("Criar Cardápio");
        btnCriar.setBorder(new javax.swing.border.MatteBorder(null));
        btnCriar.setBorderPainted(false);
        btnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarActionPerformed(evt);
            }
        });

        lblImportar.setFont(new java.awt.Font("Century Gothic", 1, 17)); // NOI18N
        lblImportar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImportar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\import (2).png")); // NOI18N
        lblImportar.setText(" Importar");
        lblImportar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStringNomeProduto1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calendarData, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAtualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btnCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImportar)
                .addGap(48, 48, 48))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImportar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblStringNomeProduto1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAtualizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(calendarData, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnMenu)
                        .addGap(26, 26, 26)
                        .addComponent(btnStringGerenciadorCardapios))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 2, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnStringGerenciadorCardapios))
                    .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(482, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        new Menu().setVisible(true); 
        dispose();
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
      //  try{
            if (jtPratos.getSelectedRowCount() > 1){
                JOptionPane.showMessageDialog(null, "Selecione somente 1 (um) prato por vez para remover");
            }else{
                pDao = new PratoDAO();                
                Integer idSelecionado = (Integer)jtPratos.getValueAt(jtPratos.getSelectedRow(), 0);             
                for(Prato p:pDao.read()){
                    if(p.getId() == idSelecionado){    
                        itemCDao.deleteItemCardapio(cardapio, p.getId());
                        cardapio.removerPrato(p);                       
                        break;
                    }
                }
                criarTabela();
            }           
        /*}catch(java.util.ConcurrentModificationException ex){
            System.err.println("Deu a exceção");
        }*/
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        AdicionarPrato novoPrato = new AdicionarPrato(new javax.swing.JFrame(), true);
        novoPrato.setVisible(true);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarActionPerformed
        // Pega a data do Calendar
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = sdf.format(calendarData.getDate());
        
        //Formatar a entrada do JCalendar
        cardapio = new Cardapio();      
        cardapio.setData(dataFormatada);
        btnAdicionar.setEnabled(true);
        btnCriar.setEnabled(false);
        jtPratos.setEnabled(true);
        cDao.create(cardapio);
        cardapio.setId(cDao.readForData(cardapio.getData()).getId());
    }//GEN-LAST:event_btnCriarActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // Faz as modificações necessárias na interface
        try{
            criarTabela();   
        }catch(java.lang.NullPointerException ex){}
    }//GEN-LAST:event_formWindowGainedFocus

    private void jtPratosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtPratosFocusGained
        btnRemover.setEnabled(true);
        //jtPratos.clearSelection();
    }//GEN-LAST:event_jtPratosFocusGained

    private void jtPratosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtPratosFocusLost
        
    }//GEN-LAST:event_jtPratosFocusLost

    private void btnAdicionarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAdicionarFocusGained
        btnRemover.setEnabled(false);
        jtPratos.clearSelection();
    }//GEN-LAST:event_btnAdicionarFocusGained

    private void calendarDataFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_calendarDataFocusGained
        btnRemover.setEnabled(false);
        jtPratos.clearSelection();
    }//GEN-LAST:event_calendarDataFocusGained

    private void calendarDataInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_calendarDataInputMethodTextChanged
        
    }//GEN-LAST:event_calendarDataInputMethodTextChanged

    private void lblAtualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAtualizarMouseClicked
        calendarioDoDia();
        btnRemover.setEnabled(false);
    }//GEN-LAST:event_lblAtualizarMouseClicked

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
            java.util.logging.Logger.getLogger(GerenciadorCardapios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciadorCardapios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciadorCardapios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciadorCardapios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new GerenciadorCardapios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnCriar;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel btnStringGerenciadorCardapios;
    private com.toedter.calendar.JDateChooser calendarData;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jtPratos;
    private javax.swing.JLabel lblAtualizar;
    private javax.swing.JLabel lblImportar;
    private javax.swing.JLabel lblStringNomeProduto1;
    private javax.swing.Box.Filler linha1;
    // End of variables declaration//GEN-END:variables
}


