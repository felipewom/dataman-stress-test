/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.dataman;

import java.awt.BorderLayout;
import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//com.summit.dataman//StressTest//EN",
autostore = false)
@TopComponent.Description(preferredID = "StressTestTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "com.summit.dataman.StressTestTopComponent")
@ActionReference(path = "Menu/Cognex"
  , position = 10)
@TopComponent.OpenActionRegistration(displayName = "#CTL_StressTestAction",
preferredID = "StressTestTopComponent")
@Messages({
    "CTL_StressTestAction=StressTest",
    "CTL_StressTestTopComponent=StressTest Window",
    "HINT_StressTestTopComponent=This is a StressTest window"
})
public final class StressTestTopComponent extends TopComponent {

    ScheduledExecutorService executor;
    
    XYSeriesCollection dataSet;
    
    XYSeries currentSeries;
    AtomicInteger currentCount;
    int tryCount = 0;
    JFreeChart chartComponent;
    public StressTestTopComponent() {
        initComponents();
        setName(Bundle.CTL_StressTestTopComponent());
        setToolTipText(Bundle.HINT_StressTestTopComponent());
        dataSet = new XYSeriesCollection();
        chartComponent = ChartFactory.createXYLineChart("Tries vs Request Time", "Attempt", "Operation Time (ms)", dataSet, PlotOrientation.VERTICAL, true, true, false);
        chartParentPanel.add(new ChartPanel(chartComponent),BorderLayout.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        hostTextField = new javax.swing.JTextField();
        portSpinner = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        delaySpinner = new javax.swing.JSpinner();
        stopButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        triggerTextField = new javax.swing.JTextField();
        connectionCheckBox = new javax.swing.JCheckBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        chartParentPanel = new javax.swing.JPanel();
        clearGraphButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        timeoutSpinner = new javax.swing.JSpinner();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(StressTestTopComponent.class, "StressTestTopComponent.jLabel1.text")); // NOI18N

        hostTextField.setText(org.openide.util.NbBundle.getMessage(StressTestTopComponent.class, "StressTestTopComponent.hostTextField.text")); // NOI18N

        portSpinner.setModel(new javax.swing.SpinnerNumberModel(23, 1, 65535, 1));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(StressTestTopComponent.class, "StressTestTopComponent.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(startButton, org.openide.util.NbBundle.getMessage(StressTestTopComponent.class, "StressTestTopComponent.startButton.text")); // NOI18N
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(StressTestTopComponent.class, "StressTestTopComponent.jLabel3.text")); // NOI18N

        delaySpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1000), Integer.valueOf(10), null, Integer.valueOf(1)));

        org.openide.awt.Mnemonics.setLocalizedText(stopButton, org.openide.util.NbBundle.getMessage(StressTestTopComponent.class, "StressTestTopComponent.stopButton.text")); // NOI18N
        stopButton.setEnabled(false);
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(StressTestTopComponent.class, "StressTestTopComponent.jLabel4.text")); // NOI18N

        triggerTextField.setText(org.openide.util.NbBundle.getMessage(StressTestTopComponent.class, "StressTestTopComponent.triggerTextField.text")); // NOI18N

        connectionCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(connectionCheckBox, org.openide.util.NbBundle.getMessage(StressTestTopComponent.class, "StressTestTopComponent.connectionCheckBox.text")); // NOI18N
        connectionCheckBox.setEnabled(false);

        chartParentPanel.setLayout(new java.awt.BorderLayout());

        org.openide.awt.Mnemonics.setLocalizedText(clearGraphButton, org.openide.util.NbBundle.getMessage(StressTestTopComponent.class, "StressTestTopComponent.clearGraphButton.text")); // NOI18N
        clearGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearGraphButtonActionPerformed(evt);
            }
        });
        chartParentPanel.add(clearGraphButton, java.awt.BorderLayout.PAGE_END);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(StressTestTopComponent.class, "StressTestTopComponent.chartParentPanel.TabConstraints.tabTitle"), chartParentPanel); // NOI18N

        logTextArea.setColumns(20);
        logTextArea.setRows(5);
        jScrollPane1.setViewportView(logTextArea);

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(StressTestTopComponent.class, "StressTestTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(StressTestTopComponent.class, "StressTestTopComponent.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(StressTestTopComponent.class, "StressTestTopComponent.jLabel5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(StressTestTopComponent.class, "StressTestTopComponent.jLabel6.text")); // NOI18N

        timeoutSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(portSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(delaySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(startButton)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(triggerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(connectionCheckBox))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(stopButton)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timeoutSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(hostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(delaySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(triggerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectionCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(timeoutSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton)
                    .addComponent(stopButton)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(StressTestTopComponent.class, "StressTestTopComponent.jTabbedPane1.AccessibleContext.accessibleName")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        tryCount++;
        hostTextField.setEnabled(false);
        portSpinner.setEnabled(false);
        //connectionCheckBox.setEnabled(false);
        delaySpinner.setEnabled(false);
        triggerTextField.setEnabled(false);
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        timeoutSpinner.setEnabled(false);
        currentSeries = new XYSeries("Delay-" + delaySpinner.getValue());
        dataSet.addSeries(currentSeries);
        clearGraphButton.setEnabled(false);
        currentCount = new AtomicInteger(0);
        executor = Executors.newScheduledThreadPool(1);
        dataSet.removeSeries(dataSet.getSeries("Delay-" + delaySpinner.getValue()));
        executor.scheduleWithFixedDelay(new SocketThread(hostTextField.getText(), (Integer)portSpinner.getValue(), triggerTextField.getText()), 0l, (Integer)delaySpinner.getValue(), TimeUnit.MILLISECONDS);
    }//GEN-LAST:event_startButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        hostTextField.setEnabled(true);
        portSpinner.setEnabled(true);
        //.setEnabled(true);
        delaySpinner.setEnabled(true);
        triggerTextField.setEnabled(true);
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        timeoutSpinner.setEnabled(true);
        clearGraphButton.setEnabled(true);
        executor.shutdown();
    }//GEN-LAST:event_stopButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        logTextArea.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void clearGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearGraphButtonActionPerformed
        dataSet.removeAllSeries();
    }//GEN-LAST:event_clearGraphButtonActionPerformed

    private class SocketThread implements Runnable {

        private String host;
        private int port;
        private String trigger;

        public SocketThread(String host, int port, String trigger) {
            this.host = host;
            this.port = port;
            this.trigger = trigger;
        }

        @Override
        public void run() {
            try {
                long startTime = System.currentTimeMillis();
                Socket s = new Socket(host, port);

                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

                out.write(trigger + "\r\n");
                logTextArea.append("Out:\t" + trigger + "\r\n");
                out.flush();

                String lineIn = "";
                try {
                    lineIn = in.readLine();
                } catch (SocketTimeoutException ste) {
                    lineIn = "SOCKET TIMED OUT!!!";
                }
                long endTime = System.currentTimeMillis();
                final long totalTime = endTime - startTime;
                final int count = currentCount.incrementAndGet();
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        currentSeries.add(count, totalTime);
                    }
                });
                logTextArea.append("In:\t" + lineIn +"\r\n");
                s.close();

            } catch (UnknownHostException ex) {
                Exceptions.printStackTrace(ex);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartParentPanel;
    private javax.swing.JButton clearGraphButton;
    private javax.swing.JCheckBox connectionCheckBox;
    private javax.swing.JSpinner delaySpinner;
    private javax.swing.JTextField hostTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea logTextArea;
    private javax.swing.JSpinner portSpinner;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stopButton;
    private javax.swing.JSpinner timeoutSpinner;
    private javax.swing.JTextField triggerTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}
