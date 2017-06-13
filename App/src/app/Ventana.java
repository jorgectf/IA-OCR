package app;

import com.asprise.ocr.Ocr;
import java.awt.Desktop;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ventana extends javax.swing.JFrame {

    public Ventana() {
        initComponents();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        jFileChooser1.setFileFilter((javax.swing.filechooser.FileFilter) filtro);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OCR Amaury Ortega y Ramiro Verbel");

        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("OCR Amaury Ortega y Ramiro Verbel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(124, 124, 124))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        String accion = evt.getActionCommand();
        if (accion.equals(JFileChooser.APPROVE_SELECTION)) {
            //Aceptar
            //Archivo
            File archivo = jFileChooser1.getSelectedFile();
            String ruta = archivo.getAbsolutePath();
            //Salida
            String[] opciones = new String[]{"TEXTO PLANO", "PDF", "RTF"};
            int respuesta;
            respuesta = JOptionPane.showOptionDialog(rootPane, "Desea un pdf o un rtf?", "Formato de salida",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
            //OCR
            Ocr.setUp();
            Ocr ocr = new Ocr();
            ocr.startEngine("spa", Ocr.SPEED_FAST);
            switch (respuesta) {
                case 0:
                    String texto = ocr.recognize(new File[]{new File(ruta)}, Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PLAINTEXT);
                    JOptionPane.showMessageDialog(rootPane, texto, "Resultado", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1:
                    ocr.recognize(new File[]{new File(ruta)}, Ocr.RECOGNIZE_TYPE_ALL,
                            Ocr.OUTPUT_FORMAT_PDF,
                            Ocr.PROP_PDF_OUTPUT_FILE, "resultado.pdf",
                            Ocr.PROP_PDF_OUTPUT_TEXT_VISIBLE, true);
                    //Abrir automaticamente PDF si es soportado
                    if (Desktop.isDesktopSupported()) {
                        try {
                            File myFile = new File("resultado.pdf");
                            Desktop.getDesktop().open(myFile);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(rootPane, "No se encontró un programa para abrir PDF", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                case 2:
                    ocr.recognize(new File[]{new File(ruta)},
                            Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_RTF,
                            "PROP_RTF_OUTPUT_FILE=resultado.rtf");
                    //Abrir automaticamente RTF si es soportado
                    if (Desktop.isDesktopSupported()) {
                        try {
                            File myFile = new File("resultado.rtf");
                            Desktop.getDesktop().open(myFile);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(rootPane, "No se encontró un programa para abrir RTF", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
            }
            ocr.stopEngine();
        } else if (accion.equals(JFileChooser.CANCEL_SELECTION)) {
            System.exit(0);
        }
    }//GEN-LAST:event_jFileChooser1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
