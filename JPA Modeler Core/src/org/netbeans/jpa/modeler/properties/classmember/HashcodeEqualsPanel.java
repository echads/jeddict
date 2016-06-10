/**
 * Copyright [2016] Gaurav Gupta
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.netbeans.jpa.modeler.properties.classmember;

import org.netbeans.jpa.modeler.core.widget.PersistenceClassWidget;
import org.netbeans.jpa.modeler.spec.ManagedClass;
import org.netbeans.jpa.modeler.spec.extend.JavaClass;
import org.netbeans.modeler.properties.embedded.GenericEmbeddedEditor;

public class HashcodeEqualsPanel extends GenericEmbeddedEditor<JavaClass> {


    private JavaClass javaClass;
    private final PersistenceClassWidget<? extends ManagedClass> persistenceClassWidget;

    public HashcodeEqualsPanel(PersistenceClassWidget<? extends ManagedClass> persistenceClassWidget) {
        this.persistenceClassWidget = persistenceClassWidget;
    }

    @Override
    public void init() {
        initComponents();
        ((ClassMemberPanel)equalsMethodPanel).init();
        ((ClassMemberPanel)hashcodeMethodPanel).init();
    }

    
    @Override
    public void setValue(JavaClass javaClass) {
        this.javaClass=javaClass;
        ((ClassMemberPanel)equalsMethodPanel).setPersistenceClassWidget(persistenceClassWidget);
        ((ClassMemberPanel)hashcodeMethodPanel).setPersistenceClassWidget(persistenceClassWidget);

        ((ClassMemberPanel)equalsMethodPanel).setValue(javaClass.getEqualsMethod());
        ((ClassMemberPanel)hashcodeMethodPanel).setValue(javaClass.getHashCodeMethod());
    }
    
    @Override
    public JavaClass getValue() {
        ((ClassMemberPanel)equalsMethodPanel).getValue();
        ((ClassMemberPanel)hashcodeMethodPanel).getValue();
        return javaClass;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane2 = new javax.swing.JSplitPane();
        jSplitPane = new javax.swing.JSplitPane();
        hashcodeMethodPanel = new ClassMemberPanel(org.openide.util.NbBundle.getMessage(ClassMemberPanel.class, "LBL_hashcode_select"));
        equalsMethodPanel = new ClassMemberPanel(org.openide.util.NbBundle.getMessage(ClassMemberPanel.class, "LBL_equals_select"));

        jSplitPane2.setToolTipText(org.openide.util.NbBundle.getMessage(HashcodeEqualsPanel.class, "HashcodeEqualsPanel.jSplitPane2.toolTipText")); // NOI18N

        jSplitPane.setDividerSize(10);
        jSplitPane.setResizeWeight(0.5d);

        javax.swing.GroupLayout hashcodeMethodPanelLayout = new javax.swing.GroupLayout(hashcodeMethodPanel);
        hashcodeMethodPanel.setLayout(hashcodeMethodPanelLayout);
        hashcodeMethodPanelLayout.setHorizontalGroup(
            hashcodeMethodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        hashcodeMethodPanelLayout.setVerticalGroup(
            hashcodeMethodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jSplitPane.setLeftComponent(hashcodeMethodPanel);

        javax.swing.GroupLayout equalsMethodPanelLayout = new javax.swing.GroupLayout(equalsMethodPanel);
        equalsMethodPanel.setLayout(equalsMethodPanelLayout);
        equalsMethodPanelLayout.setHorizontalGroup(
            equalsMethodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        equalsMethodPanelLayout.setVerticalGroup(
            equalsMethodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 492, Short.MAX_VALUE)
        );

        jSplitPane.setRightComponent(equalsMethodPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel equalsMethodPanel;
    private javax.swing.JPanel hashcodeMethodPanel;
    private javax.swing.JSplitPane jSplitPane;
    private javax.swing.JSplitPane jSplitPane2;
    // End of variables declaration//GEN-END:variables


}
