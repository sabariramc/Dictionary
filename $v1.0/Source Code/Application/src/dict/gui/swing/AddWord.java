/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dict.gui.swing;

import dict.obj.DictionaryInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 *
 * @author Sabari
 */
public class AddWord extends javax.swing.JFrame {

    /**
     * Creates new form AddWord
     */
    
    SearchWord parent;
    private DictionaryInterface di;
    Vector<String> contexts;
    Vector<String> idioms;
    Vector<String> phrasalVerbs;
    private int cCount;
    
    public AddWord(SearchWord parent, DictionaryInterface di) {
        initComponents();
        contexts = new Vector<String>();
        idioms = new Vector<String>();
        phrasalVerbs = new Vector<String>();
        cCount = 0;
        this.parent = parent;
        this.di = di;
        
        addWindowListener(
                new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent we){
                        parent.setVisible(true);
                    }
                }
        );
        
        setWord.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae){
                    setActionSetWord(ae);
                }
            }
        );
        
        addContext.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae){
                        setActionAddContext(ae);
                    }
                }
        );
        
        addIdiom.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae){
                        setActionAddIdiom(ae);
                    }
                }
        );
        
        addPhrasalVerb.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae){
                        setActionAddPhrasalVerb(ae);
                    }
                }
        );
        
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

    private void setActionSetWord(ActionEvent ae){
        
        warnings.setText(" ");
        try{
            String word = wordField.getText();
            String syllable = syllableField.getText();
            if(word.length() == 0){
                warnings.setText("Word cannot be empty");
            }

            else if(syllable.length() == 0){
                warnings.setText("Syllable cannot be empty");
            }
            else if(di.isDuplicate(word)){
                warnings.setText("Word already exist");
            }
            else{
                di.createWord(word, syllable);
                wordField.setEnabled(false);
                syllableField.setEnabled(false);
                setWord.setEnabled(false);
                contextList.setEnabled(true);
                addContext.setEnabled(true);
                idiomList.setEnabled(true);
                addIdiom.setEnabled(true);
                phrasalList.setEnabled(true);
                addPhrasalVerb.setEnabled(true);
                submit.setEnabled(true);
                warnings.setText("  ");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void setActionAddContext(ActionEvent ae){
        
        warnings.setText(" ");
        setVisible(false);
        new AddContext(this,di);
    }
    
    private void setActionAddIdiom(ActionEvent ae){
        
        warnings.setText(" ");
        setVisible(false);
        new AddIdiom(this,di);
    }
    
    private void setActionAddPhrasalVerb(ActionEvent ae){
        
        warnings.setText(" ");
        setVisible(false);
        new AddPhrasalVerb(this,di);
    }
    
    private void setActionSubmit(ActionEvent ae){
        
        if(cCount == 0){
            warnings.setText("Context cannot be empty");            
        }
        else{
            try{
                di.submitWordToDictionary();
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
    
    void updateContextList(String context){
        cCount++;
        contexts.add(context);
        contextList.setListData(contexts);
    }
    
    void updateIdiomList(String idiom){
        idioms.add(idiom);
        idiomList.setListData(idioms);
    }
    
    void updatePhrasalVerbList(String phrasalVerb){
        phrasalVerbs.add(phrasalVerb);
        phrasalList.setListData(phrasalVerbs);
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
        wordField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        syllableField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        contextList = new javax.swing.JList<String>();
        addContext = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        addIdiom = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        idiomList = new javax.swing.JList<String>();
        jLabel5 = new javax.swing.JLabel();
        addPhrasalVerb = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        phrasalList = new javax.swing.JList<String>();
        submit = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        setWord = new javax.swing.JButton();
        warnings = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Word");
        setResizable(false);

        jLabel1.setText("Word");

        jLabel2.setText("Syllable");

        jLabel3.setText("Contexts");

        contextList.setEnabled(false);
        jScrollPane1.setViewportView(contextList);

        addContext.setText("Add Context");
        addContext.setEnabled(false);

        jLabel4.setText("Idioms");

        addIdiom.setText("Add Idiom");
        addIdiom.setEnabled(false);

        idiomList.setEnabled(false);
        jScrollPane2.setViewportView(idiomList);

        jLabel5.setText("Phrasal Verbs");

        addPhrasalVerb.setText("Add Phrasal verbs");
        addPhrasalVerb.setEnabled(false);

        phrasalList.setEnabled(false);
        jScrollPane3.setViewportView(phrasalList);

        submit.setText("Submit");
        submit.setEnabled(false);

        cancel.setText("Cancel");

        setWord.setText("Set Word");

        warnings.setForeground(new java.awt.Color(250, 0, 0));
        warnings.setText("  ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(setWord)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addComponent(cancel)
                                .addGap(18, 18, 18)
                                .addComponent(submit))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addPhrasalVerb))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addIdiom, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addContext, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(wordField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(syllableField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(warnings)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(wordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(syllableField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setWord)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(addContext))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(addIdiom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(addPhrasalVerb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submit)
                    .addComponent(cancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warnings)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addContext;
    private javax.swing.JButton addIdiom;
    private javax.swing.JButton addPhrasalVerb;
    private javax.swing.JButton cancel;
    private javax.swing.JList<String> contextList;
    private javax.swing.JList<String> idiomList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> phrasalList;
    private javax.swing.JButton setWord;
    private javax.swing.JButton submit;
    private javax.swing.JTextField syllableField;
    private javax.swing.JLabel warnings;
    private javax.swing.JTextField wordField;
    // End of variables declaration//GEN-END:variables
}
