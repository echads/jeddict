/**
 * Copyright [2014] Gaurav Gupta
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
package org.netbeans.jpa.modeler.core.widget.flow.relation;

import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.anchor.AnchorShapeFactory;
import org.netbeans.modeler.anchorshape.IconAnchorShape;
import org.netbeans.modeler.specification.model.document.IModelerScene;
import org.netbeans.modeler.widget.edge.info.EdgeWidgetInfo;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Gaurav Gupta
 */
public abstract class MTORelationFlowWidget extends HierarchicalRelationFlowWidget {

    private static final AnchorShape SOURCE_ANCHOR_SHAPE = AnchorShapeFactory.createImageAnchorShape(ImageUtilities.loadImage("org/netbeans/jpa/modeler/resource/image/many-to-one.gif"), true);
    private static final AnchorShape TARGET_ANCHOR_SHAPE = new IconAnchorShape(ImageUtilities.loadImage("org/netbeans/jpa/modeler/resource/image/one-to-one-arrow.png"), true);

    public MTORelationFlowWidget(IModelerScene scene, EdgeWidgetInfo edge) {
        super(scene, edge);
        setSourceAnchorShape(SOURCE_ANCHOR_SHAPE);
        setTargetAnchorShape(TARGET_ANCHOR_SHAPE);
    }

}