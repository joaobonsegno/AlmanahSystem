package main;

import ArrumarString.Monetarios;
import ArrumarString.SoNumeros;
import javax.swing.JOptionPane;
import model.bean.Caixa;
import model.bean.Carteira;
import model.bean.Cliente;
import model.bean.Log;
import model.dao.CaixaDAO;
import model.dao.CarteiraDAO;
import model.dao.ClienteDAO;
import model.dao.LogDAO;

public class InserirCliente extends javax.swing.JDialog {
    public static Cliente cliente;
    public static Carteira carteira;
    public static boolean flagCliente = false;
    
    public InserirCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnConfirmar); 
        btnConfirmar.setEnabled(false);
        if (GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado).getCliente() != null){
            cliente = GerenciadorComandas.comandasAbertas.get(GerenciadorComandas.indiceSelecionado).getCliente();            
            txtCpf.setText(cliente.getCpf());           
            lblNome.setText(cliente.getNome());
            try{
                lblDivida.setText("R$ "+GerenciadorComandas.valorMonetario(cliente.getSaldo()));
            }catch(java.lang.NullPointerException ex){
                lblDivida.setText("R$ 0,00");
            }
            btnConfirmar.setEnabled(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl1 = new javax.swing.JLabel();
        linha = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnCancelar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        lblInsiraSuprimento2 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        lblInsiraSuprimento = new javax.swing.JLabel();
        lblDivida = new javax.swing.JLabel();
        lblInsiraSuprimento3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Informar Cliente");
        setMaximumSize(new java.awt.Dimension(528, 266));
        setMinimumSize(new java.awt.Dimension(528, 266));
        setResizable(false);

        lbl1.setBackground(new java.awt.Color(0, 102, 204));
        lbl1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl1.setText("Informar Cliente");

        linha.setBackground(new java.awt.Color(0, 0, 0));
        linha.setOpaque(true);

        btnCancelar.setBackground(new java.awt.Color(255, 0, 51));
        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(new javax.swing.border.MatteBorder(null));
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnConfirmar.setBackground(new java.awt.Color(0, 153, 51));
        btnConfirmar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.setBorder(new javax.swing.border.MatteBorder(null));
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        lblNome.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblNome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblInsiraSuprimento2.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblInsiraSuprimento2.setText("Nome:");

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        txtCpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCpfKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCpfKeyTyped(evt);
            }
        });

        lblInsiraSuprimento.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblInsiraSuprimento.setText("CPF:");

        lblDivida.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblDivida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDivida.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblInsiraSuprimento3.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblInsiraSuprimento3.setText("Dívida atual:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblInsiraSuprimento2)
                    .addComponent(lblInsiraSuprimento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblInsiraSuprimento3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDivida, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblInsiraSuprimento)
                            .addComponent(lblInsiraSuprimento3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDivida, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblInsiraSuprimento2)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(lbl1)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(222, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        try{
            // Adicionando ao cliente a nova dívida
            Double saldo = cliente.getSaldo();
            saldo += FormaPagamento.valorCobrado;
            cliente.setSaldo(saldo);
            cliente.aumentarSaldoPendente(FormaPagamento.valorCobrado);
            ClienteDAO cDao = new ClienteDAO();
            cDao.updateSaldo(cliente);
            
            
            // Tornando flagCliente TRUE para a tela de FormasDePagamento chamar o método de nova FORMA
            flagCliente = true;
            
            // Instanciando a CARTEIRA - o LOG das rotinas de Cateira de clientes
            carteira = new Carteira(GerenciadorComandas.getDataAtualComHoraFormatoBr(), FormaPagamento.valorCobrado, cliente, Login.funcAtual);
            
            CarteiraDAO carteiraDao = new CarteiraDAO();
            carteiraDao.create(carteira);  
            carteira.setId(carteiraDao.readLast().getId());
            
            dispose();
        }catch(java.lang.NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Insira um valor válido");
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void txtCpfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfKeyTyped
        
    }//GEN-LAST:event_txtCpfKeyTyped

    public boolean isDigit(String c){
        try{
            Integer.parseInt(c);
        }catch(NumberFormatException ex){
            return false;
        }
        return true;
    }
    
    private void txtCpfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfKeyReleased
        String e = txtCpf.getText();
        Character c = e.charAt(13);
        String cString = c.toString();
        if (this.isDigit(cString)){
            ClienteDAO cDao = new ClienteDAO();
            cliente = cDao.readForCpfExato(e);
            if (cliente.getSaldo() != null){
                lblNome.setText(cliente.getNome());
                try{
                    lblDivida.setText("R$ "+GerenciadorComandas.valorMonetario(cliente.getSaldo()));
                }catch(java.lang.NullPointerException ex){
                    lblDivida.setText("R$ 0,00");
                }
                btnConfirmar.setEnabled(true);
            }else{
                lblNome.setText("CLIENTE NÃO ENCONTRADO");
                lblDivida.setText(" ");
            }
            
        }else{
            lblNome.setText("");
            lblDivida.setText("");
            btnConfirmar.setEnabled(false);
        }
    }//GEN-LAST:event_txtCpfKeyReleased

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InserirCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InserirCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InserirCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InserirCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InserirCliente dialog = new InserirCliente(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lblDivida;
    private javax.swing.JLabel lblInsiraSuprimento;
    private javax.swing.JLabel lblInsiraSuprimento2;
    private javax.swing.JLabel lblInsiraSuprimento3;
    private javax.swing.JLabel lblNome;
    private javax.swing.Box.Filler linha;
    private javax.swing.JFormattedTextField txtCpf;
    // End of variables declaration//GEN-END:variables
}
