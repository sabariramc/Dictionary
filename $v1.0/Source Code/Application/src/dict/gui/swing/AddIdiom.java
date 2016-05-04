/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dict.gui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dict.obj.DictionaryInterface;

/**
 *
 * @author Sabari
 */
public class AddIdiom extends javax.swing.JFrame {

    /**
     * Creates new form AddIdiom
     */
    
    AddWord parent;
    DictionaryInterface di;
    
    public AddIdiom(AddWord parent, DictionaryInterface di) {
        initComponents();
        this.parent = parent;
        this.di = di;
        submit.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae){
                        setActionSubmit(ae);
                    }
                }
        );
        
        cancel.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae){
                        setActionCancel(ae);
                    }
                }
        );
        setVisible(true);
    }

    
    private void setActionSubmit(ActionEvent ae){
        String form = formField.getText();
        String meaning = meaningArea.getText();
        String example = exampleArea.getText();
        int psCodeIndex = psCodes.getSelectedIndex();
        if(form.length() == 0){
            warnings.setText("Idiom form cannot be empty");
        }
        else if(meaning.length() == 0){
            warnings.setText("Meaning cannot be empty");
        }
        else if(example.length() == 0){
            warnings.setText(" Example cannot be empty");
        }
        else{
            try{       
                di.addIdiom(DictionaryInterface.getPartOfSpeechCode(psCodeIndex), form, meaning, example);
                parent.updateIdiomList(form);
                setVisible(false);
                parent.setVisible(true);
                dispose();
            }
            catch(Exception e){
                warnings.setText(e.getMessage());
                e.printStackTrace();
            }
        }
        
    }
    
    private void setActionCancel(ActionEvent ae){
        setVisible(false);
        parent.setVisible(true);
        dispose();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        formField = new javax.swing.JTextField();
        psCodes = new javax.swing.JComboBox(DictionaryInterface.getPartOfSpeechCode());
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        meaningArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        exampleArea = new javax.swing.JTextArea();
        submit = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        warnings = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Idiom");
        setResizable(false);

        jLabel1.setText("Idiom");

        jLabel2.setText("Meaning");

        meaningArea.setColumns(20);
        meaningArea.setRows(5);
        jScrollPane1.setViewportView(meaningArea);

        jLabel3.setText("Example");

        exampleArea.setColumns(20);
        exampleArea.setRows(5);
        jScrollPane2.setViewportView(exampleArea);

        submit.setText("Submit");

        cancel.setText("Cancel");

        warnings.setForeground(new java.awt.Color(240, 0, 0));
        warnings.setText("  ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(submit))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(warnings)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(psCodes, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(formField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(formField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(psCodes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submit)
                    .addComponent(cancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warnings)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JTextArea exampleArea;
    private javax.swing.JTextField formField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea meaningArea;
    private javax.swing.JComboBox psCodes;
    private javax.swing.JButton submit;
    private javax.swing.JLabel warnings;
    // End of variables declaration//GEN-END:variables
}
