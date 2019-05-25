package main;

import java.awt.Color;
import java.util.ArrayList;
import model.bean.Comanda;
import model.bean.Produto;
import model.bean.Venda;
import model.dao.ComandaDAO;
import model.dao.ItemComandaDAO;
import model.dao.ItemVendaDAO;
import model.dao.VendaDAO;

public class FormaPagamento extends javax.swing.JDialog {
    Integer formaPag;
    public static String formaPagamento;
    public static Double valorRecebido;
    
    public void mudarCor(){
        if(formaPag == 1){
            btnDinheiro.setBackground(Color.GREEN);
            btnDebito.setBackground(null);
            btnCredito.setBackground(null);
            btnVoucher.setBackground(null);
        }else if(formaPag == 2){
            btnDinheiro.setBackground(null);
            btnDebito.setBackground(Color.GREEN);
            btnCredito.setBackground(null);
            btnVoucher.setBackground(null);
        }else if(formaPag == 3){
            btnDinheiro.setBackground(null);
            btnDebito.setBackground(null);
            btnCredito.setBackground(Color.GREEN);
            btnVoucher.setBackground(null);
        }else{
            btnDinheiro.setBackground(null);
            btnDebito.setBackground(null);
            btnCredito.setBackground(null);
            btnVoucher.setBackground(Color.GREEN);
        }
    }
    
    public FormaPagamento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        String comString = Integer.toString(GerenciadorComandas.idSelecionado);
        //lblComanda.setText(comString);
        btnConfirmar.setEnabled(false);
        /*String preco = Double.toString(EncerrarComanda.comandaSelecionada.getValor());
        preco = GerenciadorComandas.tornarCompativel(preco);
        preco = GerenciadorComandas.arredondarValor(preco);
        EncerrarComanda.comandaSelecionada.setValor(Double.parseDouble(preco));*/
        lblValorTotal.setText("R$ "+GerenciadorComandas.valorMonetario(EncerrarComanda.comandaSelecionada.getValor()));
        if (EncerrarComanda.flagValor == false){
            lblRecebido.setText("R$ 0,00");
        }else{
            String valor = GerenciadorComandas.valorMonetario(valorRecebido);
            lblRecebido.setText("R$ "+ valor);
            formaPag = 1;
            formaPagamento = "Dinheiro";
            mudarCor();
            btnConfirmar.setEnabled(true);
            Double troco = EncerrarComanda.comandaSelecionada.getValor() - valorRecebido;
            troco = Math.abs(troco);
            String trocoString = GerenciadorComandas.valorMonetario(troco);
            lblTroco.setText("R$ "+trocoString);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo1 = new javax.swing.ButtonGroup();
        lblStringNovoPrato = new javax.swing.JLabel();
        linha = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        btnDebito = new javax.swing.JButton();
        btnDinheiro = new javax.swing.JButton();
        btnVoucher = new javax.swing.JButton();
        btnCredito = new javax.swing.JButton();
        lblStringValorTotal = new javax.swing.JLabel();
        lblStringTroco = new javax.swing.JLabel();
        lblStringRecebido = new javax.swing.JLabel();
        lblStringDesconto = new javax.swing.JLabel();
        lblDesconto = new javax.swing.JLabel();
        lblValorTotal = new javax.swing.JLabel();
        lblRecebido = new javax.swing.JLabel();
        lblTroco = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Forma de Pagamento");
        setMaximumSize(new java.awt.Dimension(600, 630));
        setMinimumSize(new java.awt.Dimension(600, 630));
        setPreferredSize(new java.awt.Dimension(600, 630));
        setResizable(false);

        lblStringNovoPrato.setBackground(new java.awt.Color(0, 102, 204));
        lblStringNovoPrato.setFont(new java.awt.Font("Comic Sans MS", 0, 22)); // NOI18N
        lblStringNovoPrato.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStringNovoPrato.setText("Forma de Pagamento");

        linha.setBackground(new java.awt.Color(0, 0, 0));
        linha.setOpaque(true);

        btnDebito.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnDebito.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\debito.png")); // NOI18N
        btnDebito.setText("   Débito");
        btnDebito.setBorder(new javax.swing.border.MatteBorder(null));
        btnDebito.setBorderPainted(false);
        btnDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDebitoActionPerformed(evt);
            }
        });

        btnDinheiro.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnDinheiro.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\money.png")); // NOI18N
        btnDinheiro.setText("  Dinheiro");
        btnDinheiro.setBorder(new javax.swing.border.MatteBorder(null));
        btnDinheiro.setBorderPainted(false);
        btnDinheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDinheiroActionPerformed(evt);
            }
        });

        btnVoucher.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnVoucher.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\voucher.png")); // NOI18N
        btnVoucher.setText("  Voucher");
        btnVoucher.setBorder(new javax.swing.border.MatteBorder(null));
        btnVoucher.setBorderPainted(false);
        btnVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoucherActionPerformed(evt);
            }
        });

        btnCredito.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnCredito.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\credito.png")); // NOI18N
        btnCredito.setText("  Crédito");
        btnCredito.setBorder(new javax.swing.border.MatteBorder(null));
        btnCredito.setBorderPainted(false);
        btnCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreditoActionPerformed(evt);
            }
        });

        lblStringValorTotal.setFont(new java.awt.Font("Comic Sans MS", 0, 22)); // NOI18N
        lblStringValorTotal.setText("Valor Total");

        lblStringTroco.setFont(new java.awt.Font("Comic Sans MS", 0, 22)); // NOI18N
        lblStringTroco.setText("Troco");

        lblStringRecebido.setFont(new java.awt.Font("Comic Sans MS", 0, 22)); // NOI18N
        lblStringRecebido.setText("Recebido");

        lblStringDesconto.setFont(new java.awt.Font("Comic Sans MS", 0, 22)); // NOI18N
        lblStringDesconto.setText("Desconto");

        lblDesconto.setFont(new java.awt.Font("Comic Sans MS", 0, 22)); // NOI18N
        lblDesconto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDesconto.setText("R$ 0,00");
        lblDesconto.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        lblValorTotal.setFont(new java.awt.Font("Comic Sans MS", 0, 22)); // NOI18N
        lblValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValorTotal.setText("R$ 0,00");
        lblValorTotal.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        lblRecebido.setFont(new java.awt.Font("Comic Sans MS", 0, 22)); // NOI18N
        lblRecebido.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRecebido.setText("R$ 0,00");
        lblRecebido.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        lblTroco.setFont(new java.awt.Font("Comic Sans MS", 0, 22)); // NOI18N
        lblTroco.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTroco.setText("R$ 0,00");
        lblTroco.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        btnVoltar.setBackground(new java.awt.Color(255, 51, 51));
        btnVoltar.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        btnVoltar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\cancel.png")); // NOI18N
        btnVoltar.setText("  Cancelar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        btnConfirmar.setBackground(new java.awt.Color(51, 153, 0));
        btnConfirmar.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        btnConfirmar.setIcon(new javax.swing.ImageIcon("C:\\Projetos Netbeans\\AlmanahSystem\\images\\negocio (1).png")); // NOI18N
        btnConfirmar.setText("  Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(lblStringNovoPrato))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTroco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblStringRecebido, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblRecebido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblStringValorTotal, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDesconto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblValorTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addComponent(lblStringDesconto)))
                    .addComponent(lblStringTroco, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(29, 29, 29))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 6, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblStringNovoPrato, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(lblStringDesconto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(lblStringValorTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(lblStringRecebido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(lblStringTroco))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(lblTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConfirmar))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(573, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDebitoActionPerformed
        EncerrarComanda.flagValor = false;
        btnConfirmar.setEnabled(true);
        formaPag = 2;
        formaPagamento = "Debito";
        lblRecebido.setText("R$ "+GerenciadorComandas.valorMonetario(EncerrarComanda.comandaSelecionada.getValor()));
        lblTroco.setText("R$ 0,00");
        mudarCor();
    }//GEN-LAST:event_btnDebitoActionPerformed

    private void btnDinheiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDinheiroActionPerformed
        dispose();
        FormaDinheiro forma = new FormaDinheiro(new javax.swing.JFrame(), true);
        forma.setVisible(true); 
        btnConfirmar.setEnabled(true);
        formaPagamento = "Dinheiro";
        lblRecebido.setText("R$ 0,00");
    }//GEN-LAST:event_btnDinheiroActionPerformed

    private void btnVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoucherActionPerformed
        EncerrarComanda.flagValor = false;
        btnConfirmar.setEnabled(true);
        formaPag = 4;
        lblRecebido.setText("R$ "+GerenciadorComandas.valorMonetario(EncerrarComanda.comandaSelecionada.getValor()));
        formaPagamento = "Voucher";
        lblTroco.setText("R$ 0,00");
        mudarCor();
    }//GEN-LAST:event_btnVoucherActionPerformed

    private void btnCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreditoActionPerformed
        EncerrarComanda.flagValor = false;
        btnConfirmar.setEnabled(true);
        formaPag = 3;
        lblRecebido.setText("R$ "+GerenciadorComandas.valorMonetario(EncerrarComanda.comandaSelecionada.getValor()));
        formaPagamento = "Credito";
        lblTroco.setText("R$ 0,00");
        mudarCor();
    }//GEN-LAST:event_btnCreditoActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        EncerrarComanda.flagValor = false;
        dispose();
        new EncerrarComanda().setVisible(true);
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        VendaDAO vendaDao = new VendaDAO();
        ComandaDAO comandaDao = new ComandaDAO();
        ItemComandaDAO iDao = new ItemComandaDAO();
        ArrayList<Produto> itens = new ArrayList<>();
        ArrayList<String> qtd = new ArrayList<>();
        Venda venda = new Venda();
        String data = venda.dataAtual();
        EncerrarComanda.flagValor = false;
        for(Comanda c:GerenciadorComandas.comandasAbertas){
            if(EncerrarComanda.comandaSelecionada.getId() == c.getId()){
                c.setValor(EncerrarComanda.comandaSelecionada.getValor());
            }
        }
        for(Comanda c:GerenciadorComandas.comandasAbertas){
            if(GerenciadorComandas.idSelecionado == c.getId()){
                venda.setAtributos(data, formaPagamento, c.getValor(), Login.turnoAtual);
                vendaDao.create(venda);
                int i = vendaDao.read().size()-1;
                int j = 0;
                for(Venda v:vendaDao.read()){
                    if(j==i){
                        venda.setIdBanco(v.getIdBanco());
                    }
                    j+=1;
                }
                for(Produto p:c.getItens()){
                    itens.add(p);
                }
                for(String quantidade:c.getQnt()){
                    qtd.add(quantidade);
                }
                for(int k=0; k<itens.size(); k++){
                    ItemVendaDAO itemDao = new ItemVendaDAO();
                    itemDao.create(itens.get(k), Integer.parseInt(qtd.get(k)), venda);
                }
                for(Double p:c.getPratos()){
                    ItemVendaDAO itemDao = new ItemVendaDAO();
                    itemDao.create(p, venda);
                }
                c.setStatus(0);
                comandaDao.update(c);
            }
        }
        GerenciadorComandas.comandasAbertas.removeAll(GerenciadorComandas.comandasAbertas);
        for (Comanda c:comandaDao.read()){
            if(c.getStatus() == 1){
                GerenciadorComandas.comandasAbertas.add(c);
                iDao.read(c);
            }
        }
        dispose();
        new GerenciadorComandas().setVisible(true);
    }//GEN-LAST:event_btnConfirmarActionPerformed

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
            java.util.logging.Logger.getLogger(FormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormaPagamento dialog = new FormaPagamento(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnCredito;
    private javax.swing.JButton btnDebito;
    private javax.swing.JButton btnDinheiro;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton btnVoucher;
    private javax.swing.ButtonGroup grupo1;
    private javax.swing.JLabel lblDesconto;
    private javax.swing.JLabel lblRecebido;
    private javax.swing.JLabel lblStringDesconto;
    private javax.swing.JLabel lblStringNovoPrato;
    private javax.swing.JLabel lblStringRecebido;
    private javax.swing.JLabel lblStringTroco;
    private javax.swing.JLabel lblStringValorTotal;
    private javax.swing.JLabel lblTroco;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.Box.Filler linha;
    // End of variables declaration//GEN-END:variables
}
