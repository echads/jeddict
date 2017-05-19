/**
 * Copyright [2017] Gaurav Gupta
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
package org.netbeans.jpa.modeler.core.widget;

import java.awt.event.MouseEvent;
import java.util.function.Supplier;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.jpa.modeler.spec.extend.JavaClass;
import org.netbeans.jpa.modeler.specification.model.util.JPAModelerUtil;
import org.netbeans.modeler.core.ModelerFile;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.filesystems.FileObject;
import static org.openide.util.NbBundle.getMessage;

/**
 *
 * @author jGauravGupta
 */
public class OpenSourceCodeAction extends WidgetAction.Adapter {

    private Supplier<FileObject> fileObjectSupplier;
    private JavaClass javaClass;
    private ModelerFile modelerFile;
    
    public OpenSourceCodeAction(Supplier<FileObject> fileObjectSupplier, JavaClass javaClass, ModelerFile modelerFile) {
        this.fileObjectSupplier = fileObjectSupplier;
        this.javaClass = javaClass;
        this.modelerFile = modelerFile;
    }
    
        @Override
        public WidgetAction.State mousePressed(Widget widget, WidgetAction.WidgetMouseEvent event) {
            if (event.getButton() == MouseEvent.BUTTON1 && event.getClickCount() == 2) {
                openSourceCode(true);
                return WidgetAction.State.CONSUMED;
            }
            return WidgetAction.State.REJECTED;
        }

        private void openSourceCode(boolean retryIfFileNotFound) {
            FileObject fileObject = fileObjectSupplier.get();
            if (fileObject == null || !fileObject.isValid()) {
                NotifyDescriptor.Confirmation msg = null;
                if (retryIfFileNotFound) {
                    msg = new NotifyDescriptor.Confirmation(getMessage(this.getClass(), "SRC_FILE_NOT_FOUND.text"),
                            getMessage(this.getClass(), "SRC_FILE_NOT_FOUND.title"), NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.QUESTION_MESSAGE);
                } else {
                    msg = new NotifyDescriptor.Confirmation(getMessage(this.getClass(), "SRC_FILE_NOT_FOUND.text"),
                            getMessage(this.getClass(), "SRC_FILE_NOT_FOUND_IN_CURRENT_PROECT.title"), NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.QUESTION_MESSAGE);
                }
                if (NotifyDescriptor.YES_OPTION.equals(DialogDisplayer.getDefault().notify(msg))) {
                    javaClass.setGenerateSourceCode(true);
                    JPAModelerUtil.generateSourceCode(modelerFile, () -> {
                        openSourceCode(false);
                    });
                }
            } else {
                org.netbeans.modules.openfile.OpenFile.open(fileObject, -1);
            }

        }

    }
