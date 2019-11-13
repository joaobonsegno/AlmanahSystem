package main;

import ArrumarString.Monetarios;
import ArrumarString.SoNumeros;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import model.bean.AgendaDespesa;
import model.dao.LogDAO;
import model.bean.Despesa;
import manual.Manual;
import model.dao.AgendaDespesaDAO;

public class CriarDespesa extends javax.swing.JDialog {
    public static boolean flagNovaDespesa;
    private AgendaDespesa despesa;
    
    public CriarDespesa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        txtValor.setDocument(new Monetarios(7,2));
        txtRepeticao.setDocument(new SoNumeros());
        getRootPane().setDefaultButton(btnConfirmar);
        LogDAO logDao = new LogDAO();
        txtDescricao.setWrapStyleWord(true);
        flagNovaDespesa = false;
        txtRepeticao.setVisible(false);
        lblVezes.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo1 = new javax.swing.ButtonGroup();
        txtStringSuprimento = new javax.swing.JLabel();
        linha = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        lblInsiraSuprimento = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        lblInsiraSuprimento1 = new javax.swing.JLabel();
        lblInsiraSuprimento4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        lblStringDia = new javax.swing.JLabel();
        calendar = new com.toedter.calendar.JDateChooser();
        lblStringDia1 = new javax.swing.JLabel();
        radioMensal = new javax.swing.JRadioButton();
        radioSemanal = new javax.swing.JRadioButton();
        radioOutro = new javax.swing.JRadioButton();
        txtRepeticao = new javax.swing.JTextField();
        lblVezes = new javax.swing.JLabel();
        linha1 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Criar Agendamento de Despesa");
        setMinimumSize(new java.awt.Dimension(498, 457));
        setResizable(false);

        txtStringSuprimento.setBackground(new java.awt.Color(0, 102, 204));
        txtStringSuprimento.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        txtStringSuprimento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtStringSuprimento.setText("Criar Despesa");

        linha.setBackground(new java.awt.Color(0, 0, 0));
        linha.setOpaque(true);

        lblInsiraSuprimento.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblInsiraSuprimento.setText("*Valor da despesa:");

        txtValor.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

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

        lblInsiraSuprimento1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblInsiraSuprimento1.setText("R$");

        lblInsiraSuprimento4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblInsiraSuprimento4.setText("Descrição:");

        txtDescricao.setColumns(20);
        txtDescricao.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        txtDescricao.setLineWrap(true);
        txtDescricao.setRows(3);
        txtDescricao.setTabSize(1);
        jScrollPane2.setViewportView(txtDescricao);

        lblStringDia.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblStringDia.setText("*Data:");

        calendar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        lblStringDia1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblStringDia1.setText("*Repetições:");

        grupo1.add(radioMensal);
        radioMensal.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        radioMensal.setText("Mensal");
        radioMensal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                radioMensalFocusGained(evt);
            }
        });

        grupo1.add(radioSemanal);
        radioSemanal.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        radioSemanal.setText("Semanal");
        radioSemanal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                radioSemanalFocusGained(evt);
            }
        });

        grupo1.add(radioOutro);
        radioOutro.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        radioOutro.setText("Parcela");
        radioOutro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                radioOutroFocusGained(evt);
            }
        });

        txtRepeticao.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        txtRepeticao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblVezes.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        lblVezes.setText("vezes");

        linha1.setBackground(new java.awt.Color(0, 0, 0));
        linha1.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblInsiraSuprimento)
                    .addComponent(lblStringDia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblInsiraSuprimento1))
                    .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(155, Short.MAX_VALUE))
            .addComponent(linha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblStringDia1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioSemanal)
                            .addComponent(radioMensal)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(radioOutro)
                                .addGap(24, 24, 24)
                                .addComponent(txtRepeticao, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblVezes)))
                        .addGap(125, 125, 125))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblInsiraSuprimento4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtStringSuprimento)
                        .addGap(188, 188, 188))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(linha1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(txtStringSuprimento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInsiraSuprimento1)
                    .addComponent(lblInsiraSuprimento))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblStringDia)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioSemanal)
                    .addComponent(lblStringDia1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioMensal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioOutro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRepeticao)
                            .addComponent(lblVezes, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInsiraSuprimento4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(443, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        boolean flagValorOuRepeticao = false;
        try{     
            boolean flagParcelas = false;
            if (txtRepeticao.getText().equals("0")){
                flagParcelas = true;
                JOptionPane.showMessageDialog(null, "A quantidade de parcelas deve ser maior do que zero");
            }
            
            if (!flagParcelas){
                //Instancia os objetos de manipulação de datas
                SimpleDateFormat formatoBr = new SimpleDateFormat("dd/MM/yyyy");
                Calendar atual = new GregorianCalendar();
                Calendar calendario = Calendar.getInstance();
                Date dataSelecionada, dataAtual;
                boolean dataSelecionadaMenor = false;

                //Instancia a data atual e a data selecionada, para compará-las
                String data = formatoBr.format(calendar.getDate());
                dataSelecionada = formatoBr.parse(data);
                dataAtual = formatoBr.parse(GerenciadorComandas.getDataAtualSemHoraFormatoBr());

                if (dataSelecionada.compareTo(dataAtual) == -1){ // DATA SELECIONADA é menor do que a DATA ATUAL
                    dataSelecionadaMenor = true;              
                    JOptionPane.showMessageDialog(null, "A data selecionada deve ser maior ou igual à data atual");
                }

                if (!dataSelecionadaMenor){
                    //Seta os atributos da agenda
                    AgendaDespesaDAO dDao = new AgendaDespesaDAO();
                    String valor = txtValor.getText();
                    valor = valor.replace("." , "");
                    valor = valor.replace("," , ".");
                    despesa = new AgendaDespesa();
                    despesa.setData(data);
                    despesa.setDescricao(txtDescricao.getText());
                    despesa.setValor(Double.parseDouble(valor));
                    //despesa.setStatus(1);
                    if (radioSemanal.isSelected()){
                        despesa.setFrequencia("Semanal");
                    }else if (radioMensal.isSelected()){
                        despesa.setFrequencia("Mensal");
                    }else{                   
                        despesa.setFrequencia("Parcela");
                        flagValorOuRepeticao = true;
                        despesa.setQtdVezes(Integer.parseInt(txtRepeticao.getText()));
                    }
                    dDao.create(despesa);                      
                    flagNovaDespesa = true;
                    dispose(); 
                }   
            }
        }catch(java.lang.NumberFormatException ex){
            if (flagValorOuRepeticao)
                JOptionPane.showMessageDialog(null, "Insira a quantidade de vezes que a despesa deverá ser cobrada");
            else
                JOptionPane.showMessageDialog(null, "Insira o valor da despesa");
        }catch(java.lang.NullPointerException ex){
            JOptionPane.showMessageDialog(null, "Informe a data da despesa");
        }catch(ParseException ex){
            System.err.println("Erro: "+ex);
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void radioSemanalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_radioSemanalFocusGained
        txtRepeticao.setVisible(false);
        lblVezes.setVisible(false);
                
    }//GEN-LAST:event_radioSemanalFocusGained

    private void radioMensalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_radioMensalFocusGained
        txtRepeticao.setVisible(false);
        lblVezes.setVisible(false);
    }//GEN-LAST:event_radioMensalFocusGained

    private void radioOutroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_radioOutroFocusGained
        txtRepeticao.setVisible(true);
        lblVezes.setVisible(true);
    }//GEN-LAST:event_radioOutroFocusGained

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
            java.util.logging.Logger.getLogger(Despesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Despesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Despesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Despesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                CriarDespesa dialog = new CriarDespesa(new javax.swing.JFrame(), true);
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
    private com.toedter.calendar.JDateChooser calendar;
    private javax.swing.ButtonGroup grupo1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblInsiraSuprimento;
    private javax.swing.JLabel lblInsiraSuprimento1;
    private javax.swing.JLabel lblInsiraSuprimento4;
    private javax.swing.JLabel lblStringDia;
    private javax.swing.JLabel lblStringDia1;
    private javax.swing.JLabel lblVezes;
    private javax.swing.Box.Filler linha;
    private javax.swing.Box.Filler linha1;
    private javax.swing.JRadioButton radioMensal;
    private javax.swing.JRadioButton radioOutro;
    private javax.swing.JRadioButton radioSemanal;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtRepeticao;
    private javax.swing.JLabel txtStringSuprimento;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
