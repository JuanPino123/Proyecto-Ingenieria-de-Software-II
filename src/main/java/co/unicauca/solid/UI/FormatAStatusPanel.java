package co.unicauca.solid.UI;

import co.unicauca.domain.DegreeWorkStatus;
import co.unicauca.domain.FormatA;
import co.unicauca.solid.domain.access.IFormatARepository;
import co.unicauca.solid.domain.access.Factory;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel simple para mostrar solo el estado del Formato A
 */
public class FormatAStatusPanel extends javax.swing.JPanel {

    private IFormatARepository formatARepository;

    public FormatAStatusPanel() {
        this.formatARepository = Factory.getInstance().getFormatARepository("default");
        initComponents();
        loadFormatAStatus();
    }

    private void loadFormatAStatus() {
        try {
            // Obtener el primer formato A (simplificado)
            List<FormatA> formatos = formatARepository.getAll();
            FormatA formatA = formatos.isEmpty() ? null : formatos.get(0);
            
            if (formatA == null) {
                showNoFormatA();
            } else {
                showFormatAStatus(formatA);
            }
        } catch (Exception ex) {
            lblStatus.setText("Error al cargar estado");
            lblStatus.setForeground(Color.RED);
        }
    }

    private void showNoFormatA() {
        lblStatus.setText("NO REGISTRADO");
        lblStatus.setForeground(Color.RED);
        lblObservations.setText("No has enviado el Formato A");
    }

    private void showFormatAStatus(FormatA formatA) {
        if (formatA.getStatus() != null) {
            lblStatus.setText(formatA.getStatus().toString());
            setStatusColor(formatA.getStatus());
        } else {
            lblStatus.setText("PENDIENTE");
            lblStatus.setForeground(Color.ORANGE);
        }
        
        if (formatA.getObservations() != null && !formatA.getObservations().isEmpty()) {
            lblObservations.setText(formatA.getObservations());
        } else {
            lblObservations.setText("Sin observaciones");
        }
    }

    private void setStatusColor(DegreeWorkStatus status) {
        switch (status) {
            case APROBADO:
                lblStatus.setForeground(new Color(0, 128, 0)); // Verde
                break;
            case RECHAZADO:
                lblStatus.setForeground(Color.RED);
                break;
            case PENDIENTE:
                lblStatus.setForeground(new Color(255, 165, 0)); // Naranja
                break;
            
            default:
                lblStatus.setForeground(Color.BLACK);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblObservations = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(500, 300));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Estado:");

        lblStatus.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblStatus.setText("Cargando...");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Observaciones:");

        lblObservations.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblObservations.setText("Cargando...");

        btnRefresh.setText("Actualizar");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnBack.setText("Volver al Menú");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblStatus))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(lblObservations, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRefresh)
                        .addGap(18, 18, 18)
                        .addComponent(btnBack)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblStatus))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblObservations))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefresh)
                    .addComponent(btnBack))
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {                                         
        loadFormatAStatus();
    }                                        

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {                                        
        Controller.OpenPanel(new Selector());
    }                                       

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblObservations;
    private javax.swing.JLabel lblStatus;
    // End of variables declaration                   
}