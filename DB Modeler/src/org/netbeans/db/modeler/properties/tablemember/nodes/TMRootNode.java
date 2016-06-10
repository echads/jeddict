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
package org.netbeans.db.modeler.properties.tablemember.nodes;

import org.netbeans.db.modeler.core.widget.table.TableWidget;
import org.netbeans.db.modeler.spec.DBTable;
import org.netbeans.jpa.modeler.navigator.nodes.RootNode;
import org.netbeans.jpa.modeler.navigator.nodes.CheckableAttributeNode;
import org.netbeans.jpa.modeler.navigator.nodes.TreeChildFactory;
import org.netbeans.db.modeler.properties.tablemember.TableMembers;

public class TMRootNode extends RootNode<TableMembers> {


    private final TableWidget<? extends DBTable> tableWidget;

    public TMRootNode(TableWidget<? extends DBTable> tableWidget, TableMembers tableMembers, TreeChildFactory childFactory, CheckableAttributeNode checkableNode) {
        super(tableMembers, childFactory, checkableNode);
        this.tableWidget = tableWidget;

   }

    public TableWidget<? extends DBTable> getRootWidget() {
        return tableWidget;
    }

    @Override
    public void init() {
        DBTable table = tableWidget.getBaseElementSpec();
        setDisplayName(table.getName());
        setShortDescription(table.getName());
        setIconBaseWithExtension(tableWidget.getIconPath());
    }

}
