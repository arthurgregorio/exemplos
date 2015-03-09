package filemover;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import javax.swing.*;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0
 * @since 1.0, 14/01/2015
 */
public class PathChooserView extends JFrame {

    private String filesBaseName;

    private Path sourceFolder;
    private Path targetFolder;

    /**
     *
     */
    public PathChooserView() {
        initComponents();
    }

    /**
     *
     */
    private void moveFiles() {

        if (this.sourceFolder == null || this.targetFolder == null) {
            JOptionPane.showMessageDialog(null, "Selecione os diretórios", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.filesBaseName = this.sourceFolder.getFileName().toString();

        final Path pathToCopy = Paths.get(this.targetFolder.toString()
                + File.separator + this.filesBaseName + "_moved");

        try {
            Files.createDirectories(pathToCopy);
            Files.walkFileTree(this.sourceFolder, 
                    new FileCopier(this.sourceFolder, pathToCopy));
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    /**
     * Mostra o file chooser para selecionar as pastas
     */
    private File showChooser() {

        final JFileChooser jFileChooser = new JFileChooser();

        jFileChooser.setCurrentDirectory(new File("."));
        
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.showOpenDialog(this);

        return jFileChooser.getSelectedFile();
    }

    /**
     *
     */
    public class FileCopier extends SimpleFileVisitor<Path> {

        private int counter;
        
        private final Path source;
        private final Path target;

        /**
         *
         * @param source
         * @param target
         */
        public FileCopier(Path source, Path target) {
            this.source = source;
            this.target = target;
        }

        /**
         * 
         * @param dir
         * @param attrs
         * @return
         * @throws IOException 
         */
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            jTextArea1.append("Entrando no diretorio: " + dir.getFileName().toString());
            jTextArea1.append("\n");
            return FileVisitResult.CONTINUE;
        }

        /**
         * 
         * @param file
         * @param attrs
         * @return
         * @throws IOException 
         */
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            
            jTextArea1.append("  Copiando: " + file.getFileName().toString());
            jTextArea1.append("\n");
            
            this.counter++;
            
            this.copyPath(file);
            
            return FileVisitResult.CONTINUE;
        }

        /**
         * 
         * @param input
         * @throws IOException 
         */
        private void copyPath(Path input) throws IOException {
            
            final String realFileName = input.getFileName().toString();
            final StringBuilder fileName = new StringBuilder();
            
            fileName.append(this.source.getFileName().toString());
            fileName.append("-");
            fileName.append(String.valueOf(this.counter));
            fileName.append(".");
            fileName.append(realFileName.substring(realFileName.lastIndexOf('.') + 1));
            
            Path targetFile = Paths.get(this.target.toString() + File.separator 
                    + fileName.toString());
            
            if (!Files.exists(targetFile)) {
                Files.createFile(targetFile);
            }

            Files.copy(input, targetFile, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mover Arquivos");

        jTextField1.setEditable(false);

        jLabel1.setText("Pasta Selecionada:");

        jButton1.setText("Selecionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);

        jLabel2.setText("Pasta de Destino:");

        jButton2.setText("Selecionar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Mover Arquivos");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton4.setText("Limpar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     * @param evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.sourceFolder = Paths.get(this.showChooser().getPath());
        this.jTextField1.setText(this.sourceFolder == null ? null : this.sourceFolder.toString());
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     *
     * @param evt
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.targetFolder = Paths.get(this.showChooser().getPath());
        this.jTextField2.setText(this.targetFolder == null ? null : this.targetFolder.toString());
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     *
     * @param evt
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.moveFiles();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     *
     * @param evt
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.sourceFolder = null;
        this.targetFolder = null;

        this.jTextArea1.setText(null);
        this.jTextField1.setText(null);
        this.jTextField2.setText(null);
    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
