package main;

import ArrumarString.SoNumeros;
import javax.swing.JOptionPane;
import model.bean.PromocaoUm;
import model.dao.LogDAO;
import model.dao.PromocaoUmDAO;

public class Promocao1 extends javax.swing.JDialog {
    public static boolean flagSuprimento;
    PromocaoUm p;
    public static Integer contador;
    
    public Promocao1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        PromocaoUmDAO pDao = new PromocaoUmDAO();
        p = pDao.read();
        montarBoxes();
        btnConfirmar.setEnabled(false);
        //getRootPane().setDefaultButton(btnConfirmar);
        LogDAO logDao = new LogDAO();
        //String teste = "<html><body><br>teste</body></html>";   
        
        contador = 0;
    }
    
    public void montarBoxes(){
        if (p.getSeg() == 1){
            cbSeg.setSelected(true);
        }else{
            cbSeg.setSelected(false);
        }
        if (p.getTer() == 1){
            cbTer.setSelected(true);
        }else{
            cbTer.setSelected(false);
        }
        if (p.getQua() == 1){
            cbQua.setSelected(true);
        }else{
            cbQua.setSelected(false);
        }
        if (p.getQui() == 1){
            cbQui.setSelected(true);
        }else{
            cbQui.setSelected(false);
        }
        if (p.getSex() == 1){
            cbSex.setSelected(true);
        }else{
            cbSex.setSelected(false);
        }
        if (p.getSab() == 1){
            cbSab.setSelected(true);
        }else{
            cbSab.setSelected(false);
        }
        if (p.getDom() == 1){
            cbDom.setSelected(true);
        }else{
            cbDom.setSelected(false);
        }
        if (p.getStatus() == 1){
            jrAtivo.setSelected(true);
        }else{
            jrInativo.setSelected(true);
        }
        setTextos();
    }
    
    public void setTextos(){
        txtEntradaPorcentagem.setDocument(new SoNumeros());
        txtEntradaPorcentagem.setText(Double.toString(p.getPorcentagem()*10)); 
        lblDescricao.setText(p.getDescricao());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo1 = new javax.swing.ButtonGroup();
        lblStringPromocao1 = new javax.swing.JLabel();
        linha = new javax.swing.Box.Filler(new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 1), new java.awt.Dimension(2, 32767));
        lblStringPorcentagem = new javax.swing.JLabel();
        txtEntradaPorcentagem = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        lblInsiraSuprimento1 = new javax.swing.JLabel();
        lblStringDia = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cbSeg = new javax.swing.JCheckBox();
        cbTer = new javax.swing.JCheckBox();
        cbQua = new javax.swing.JCheckBox();
        cbQui = new javax.swing.JCheckBox();
        cbSex = new javax.swing.JCheckBox();
        cbSab = new javax.swing.JCheckBox();
        cbDom = new javax.swing.JCheckBox();
        lblStringDescricao = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblDescricao = new javax.swing.JLabel();
        lblStringDescricao1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jrAtivo = new javax.swing.JRadioButton();
        jrInativo = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Suprimento do Caixa");
        setMaximumSize(new java.awt.Dimension(562, 503));
        setMinimumSize(new java.awt.Dimension(562, 503));
        setResizable(false);

        lblStringPromocao1.setBackground(new java.awt.Color(0, 102, 204));
        lblStringPromocao1.setFont(new java.awt.Font("Century Gothic", 1, 19)); // NOI18N
        lblStringPromocao1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStringPromocao1.setText("Promoção 01");

        linha.setBackground(new java.awt.Color(0, 0, 0));
        linha.setOpaque(true);

        lblStringPorcentagem.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lblStringPorcentagem.setText("Porcentagem do desconto:");

        txtEntradaPorcentagem.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtEntradaPorcentagem.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEntradaPorcentagem.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        txtEntradaPorcentagem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEntradaPorcentagemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEntradaPorcentagemKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEntradaPorcentagemKeyTyped(evt);
            }
        });

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

        lblInsiraSuprimento1.setFont(new java.awt.Font("Century Gothic", 0, 19)); // NOI18N
        lblInsiraSuprimento1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInsiraSuprimento1.setText("%");

        lblStringDia.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lblStringDia.setText("Dias da semana:");

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        cbSeg.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        cbSeg.setText("Seg");
        cbSeg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbSegMouseClicked(evt);
            }
        });
        cbSeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSegActionPerformed(evt);
            }
        });

        cbTer.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        cbTer.setText("Ter");
        cbTer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbTerMouseClicked(evt);
            }
        });
        cbTer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTerActionPerformed(evt);
            }
        });

        cbQua.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        cbQua.setText("Qua");
        cbQua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbQuaMouseClicked(evt);
            }
        });
        cbQua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbQuaActionPerformed(evt);
            }
        });

        cbQui.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        cbQui.setText("Qui");
        cbQui.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbQuiMouseClicked(evt);
            }
        });
        cbQui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbQuiActionPerformed(evt);
            }
        });

        cbSex.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        cbSex.setText("Sex");
        cbSex.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbSexMouseClicked(evt);
            }
        });
        cbSex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSexActionPerformed(evt);
            }
        });

        cbSab.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        cbSab.setText("Sab");
        cbSab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbSabMouseClicked(evt);
            }
        });
        cbSab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSabActionPerformed(evt);
            }
        });

        cbDom.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        cbDom.setText("Dom");
        cbDom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbDomMouseClicked(evt);
            }
        });
        cbDom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbQua)
                    .addComponent(cbQui)
                    .addComponent(cbTer)
                    .addComponent(cbSeg)
                    .addComponent(cbSab)
                    .addComponent(cbSex))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbDom)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(cbSeg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbQua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbQui)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbSex)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbSab)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbDom))
        );

        lblStringDescricao.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lblStringDescricao.setText("Descrição:");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setMaximumSize(new java.awt.Dimension(306, 224));
        jPanel2.setMinimumSize(new java.awt.Dimension(306, 224));

        lblDescricao.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lblDescricao.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblDescricao.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        lblStringDescricao1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lblStringDescricao1.setText("Status:");

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        grupo1.add(jrAtivo);
        jrAtivo.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jrAtivo.setText("Ativo");
        jrAtivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrAtivoActionPerformed(evt);
            }
        });

        grupo1.add(jrInativo);
        jrInativo.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jrInativo.setText("Inativo");
        jrInativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrInativoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jrAtivo)
                .addGap(18, 18, 18)
                .addComponent(jrInativo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrAtivo)
                    .addComponent(jrInativo))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(linha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStringDescricao1)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStringDescricao)
                            .addComponent(lblStringPorcentagem)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblStringDia)
                        .addGap(170, 170, 170)
                        .addComponent(txtEntradaPorcentagem, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblInsiraSuprimento1)))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblStringPromocao1)
                .addGap(217, 217, 217))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblStringPromocao1)
                .addGap(18, 18, 18)
                .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStringDia)
                    .addComponent(lblStringPorcentagem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEntradaPorcentagem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblInsiraSuprimento1))
                        .addGap(25, 25, 25)
                        .addComponent(lblStringDescricao)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(lblStringDescricao1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        flagSuprimento = false;
        new Promocoes().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    public void setDias(PromocaoUm p){
        //Verifica de a segunda-feira está selecionada
        if (cbSeg.isSelected()){
            p.setSeg(1);
        }else{
            p.setSeg(0);
        }

        //Verifica de a terça-feira está selecionada
        if (cbTer.isSelected()){
            p.setTer(1);
        }else{
            p.setTer(0);
        }

        //Verifica de a quarta-feira está selecionada
        if (cbQua.isSelected()){
            p.setQua(1);
        }else{
            p.setQua(0);
        }

        //Verifica de a quinta-feira está selecionada
        if (cbQui.isSelected()){
            p.setQui(1);
        }else{
            p.setQui(0);
        }

        //Verifica de a sexta-feira está selecionada
        if (cbSex.isSelected()){
            p.setSex(1);
        }else{
            p.setSex(0);
        }

        //Verifica de a sábado está selecionada
        if (cbSab.isSelected()){
            p.setSab(1);
        }else{
            p.setSab(0);
        }

        //Verifica de a domingo está selecionada
        if (cbDom.isSelected()){
            p.setDom(1);
        }else{
            p.setDom(0);
        }
        
        setDescricao(p);
        btnConfirmar.setEnabled(true);
    }
    
    public void setDescricao(PromocaoUm p){
        lblDescricao.setText("");
        String descricao = "<html><body>Sucos custam "+txtEntradaPorcentagem.getText()+"% menos nos dias:<br>";
        if (p.getSeg() == 1){
            descricao += "  - Segunda-feira<br>";
        }
        if (p.getTer() == 1){
            descricao += "  - Terça-feira<br>";
        }
        if (p.getQua() == 1){
            descricao += "  - Quarta-feira<br>";
        }
        if (p.getQui() == 1){
            descricao += "  - Quinta-feira<br>";
        }
        if (p.getSex() == 1){
            descricao += "  - Sexta-feira<br>";
        }
        if (p.getSab() == 1){
            descricao += "  - Sábado<br>";
        }
        if (p.getDom() == 1){
            descricao += "  - Domingo<br>";
        }
        descricao += "</body></html>";
        lblDescricao.setText(descricao);
        p.setDescricao(descricao);
    }
    
    public void setStatus(PromocaoUm p){
        if (jrAtivo.isSelected()){
            p.setStatus(1);
        }else{
            p.setStatus(2);
        }
    }
    
    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        
        Double porcentagem;
        boolean flagPorcentagemErrada = false;
        
        if (Integer.parseInt(txtEntradaPorcentagem.getText()) > 100 | Integer.parseInt(txtEntradaPorcentagem.getText()) == 0){
            flagPorcentagemErrada = true;
        }
        
        if (flagPorcentagemErrada){
            JOptionPane.showMessageDialog(null, "Porcentagem inválida: insira um valor entre 1 e 100%");
        }else{
            PromocaoUmDAO pDao = new PromocaoUmDAO();
            porcentagem = Double.parseDouble(txtEntradaPorcentagem.getText())/100;
            p.setPorcentagem(porcentagem);
            p.setId(1);
            setStatus(p);
            pDao.update(p);
            JOptionPane.showMessageDialog(null, "Promoção atualizada com sucesso!");
            new Promocoes().setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void cbSegMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbSegMouseClicked
        setDias(p);
    }//GEN-LAST:event_cbSegMouseClicked

    private void cbSegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSegActionPerformed
        setDias(p);
    }//GEN-LAST:event_cbSegActionPerformed

    private void cbTerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbTerMouseClicked
        setDias(p);
    }//GEN-LAST:event_cbTerMouseClicked

    private void cbTerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTerActionPerformed
        setDias(p);
    }//GEN-LAST:event_cbTerActionPerformed

    private void cbQuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbQuaMouseClicked
        setDias(p);
    }//GEN-LAST:event_cbQuaMouseClicked

    private void cbQuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbQuaActionPerformed
        setDias(p);
    }//GEN-LAST:event_cbQuaActionPerformed

    private void cbQuiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbQuiMouseClicked
        setDias(p);
    }//GEN-LAST:event_cbQuiMouseClicked

    private void cbQuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbQuiActionPerformed
        setDias(p);
    }//GEN-LAST:event_cbQuiActionPerformed

    private void cbSexMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbSexMouseClicked
        setDias(p);
    }//GEN-LAST:event_cbSexMouseClicked

    private void cbSexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSexActionPerformed
        setDias(p);
    }//GEN-LAST:event_cbSexActionPerformed

    private void cbSabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbSabMouseClicked
        setDias(p);
    }//GEN-LAST:event_cbSabMouseClicked

    private void cbSabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSabActionPerformed
        setDias(p);
    }//GEN-LAST:event_cbSabActionPerformed

    private void cbDomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbDomMouseClicked
        setDias(p);
    }//GEN-LAST:event_cbDomMouseClicked

    private void cbDomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDomActionPerformed
        setDias(p);
    }//GEN-LAST:event_cbDomActionPerformed

    private void txtEntradaPorcentagemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntradaPorcentagemKeyTyped
          
    }//GEN-LAST:event_txtEntradaPorcentagemKeyTyped

    private void jrAtivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrAtivoActionPerformed
        btnConfirmar.setEnabled(true);
    }//GEN-LAST:event_jrAtivoActionPerformed

    private void jrInativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrInativoActionPerformed
        btnConfirmar.setEnabled(true);
    }//GEN-LAST:event_jrInativoActionPerformed

    private void txtEntradaPorcentagemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntradaPorcentagemKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEntradaPorcentagemKeyPressed

    private void txtEntradaPorcentagemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntradaPorcentagemKeyReleased
        setDias(p);
    }//GEN-LAST:event_txtEntradaPorcentagemKeyReleased

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
            java.util.logging.Logger.getLogger(Promocao1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Promocao1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Promocao1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Promocao1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Promocao1 dialog = new Promocao1(new javax.swing.JFrame(), true);
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
    private javax.swing.JCheckBox cbDom;
    private javax.swing.JCheckBox cbQua;
    private javax.swing.JCheckBox cbQui;
    private javax.swing.JCheckBox cbSab;
    private javax.swing.JCheckBox cbSeg;
    private javax.swing.JCheckBox cbSex;
    private javax.swing.JCheckBox cbTer;
    private javax.swing.ButtonGroup grupo1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jrAtivo;
    private javax.swing.JRadioButton jrInativo;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblInsiraSuprimento1;
    private javax.swing.JLabel lblStringDescricao;
    private javax.swing.JLabel lblStringDescricao1;
    private javax.swing.JLabel lblStringDia;
    private javax.swing.JLabel lblStringPorcentagem;
    private javax.swing.JLabel lblStringPromocao1;
    private javax.swing.Box.Filler linha;
    private javax.swing.JTextField txtEntradaPorcentagem;
    // End of variables declaration//GEN-END:variables
}
