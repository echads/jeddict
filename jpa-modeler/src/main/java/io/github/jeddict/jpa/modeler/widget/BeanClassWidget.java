/**
 * Copyright 2013-2018 the original author or authors from the Jeddict project (https://jeddict.github.io/).
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
package io.github.jeddict.jpa.modeler.widget;

import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.netbeans.api.visual.widget.Widget;
import static io.github.jeddict.jpa.modeler.Constant.BEAN_ATTRIBUTE;
import static io.github.jeddict.jpa.modeler.Constant.BEAN_COLLECTION_ATTRIBUTE;
import static io.github.jeddict.jpa.modeler.Constant.TRANSIENT_ATTRIBUTE;
import io.github.jeddict.jpa.modeler.widget.attribute.AttributeWidget;
import io.github.jeddict.jpa.modeler.widget.attribute.association.AssociationAttributeWidget;
import io.github.jeddict.jpa.modeler.widget.attribute.association.MTMAssociationAttributeWidget;
import io.github.jeddict.jpa.modeler.widget.attribute.association.MTOAssociationAttributeWidget;
import io.github.jeddict.jpa.modeler.widget.attribute.association.OTMAssociationAttributeWidget;
import io.github.jeddict.jpa.modeler.widget.attribute.association.OTOAssociationAttributeWidget;
import io.github.jeddict.jpa.modeler.widget.attribute.bean.BeanAttributeWidget;
import io.github.jeddict.jpa.modeler.widget.attribute.bean.BeanCollectionAttributeWidget;
import io.github.jeddict.jpa.modeler.widget.attribute.bean.BeanTransientAttributeWidget;
import io.github.jeddict.jpa.spec.Transient;
import io.github.jeddict.jpa.spec.extend.Attribute;
import io.github.jeddict.jpa.spec.bean.BeanAttribute;
import io.github.jeddict.jpa.spec.bean.BeanAttributes;
import io.github.jeddict.jpa.spec.bean.BeanClass;
import io.github.jeddict.jpa.spec.bean.BeanCollectionAttribute;
import io.github.jeddict.jpa.spec.bean.ManyToManyAssociation;
import io.github.jeddict.jpa.spec.bean.ManyToOneAssociation;
import io.github.jeddict.jpa.spec.bean.OneToManyAssociation;
import io.github.jeddict.jpa.spec.bean.OneToOneAssociation;
import io.github.jeddict.jpa.modeler.initializer.JPAModelerScene;
import io.github.jeddict.jpa.modeler.initializer.JPAModelerUtil;
import io.github.jeddict.jpa.modeler.widget.flow.association.AssociationFlowWidget;
import org.apache.commons.lang.StringUtils;
import org.netbeans.modeler.config.palette.SubCategoryNodeConfig;
import static org.netbeans.modeler.core.NBModelerUtil.getAutoGeneratedStringId;
import org.netbeans.modeler.widget.node.info.NodeWidgetInfo;

public class BeanClassWidget extends JavaClassWidget<BeanClass> {

    private final List<BeanAttributeWidget> beanAttributeWidgets = new ArrayList<>();
    private final List<BeanCollectionAttributeWidget> beanCollectionAttributeWidgets = new ArrayList<>();
    private final List<BeanTransientAttributeWidget> beanTransientAttributeWidgets = new ArrayList<>();
    private final List<OTOAssociationAttributeWidget> oneToOneAssociationAttributeWidgets = new ArrayList<>();
    private final List<OTMAssociationAttributeWidget> oneToManyAssociationAttributeWidgets = new ArrayList<>();
    private final List<MTOAssociationAttributeWidget> manyToOneAssociationAttributeWidgets = new ArrayList<>();
    private final List<MTMAssociationAttributeWidget> manyToManyAssociationAttributeWidgets = new ArrayList<>();
    private final List<AssociationFlowWidget> inverseSideAssociationFlowWidgets = new ArrayList<>();
    private final List<AssociationFlowWidget> unidirectionalAssociationFlowWidget = new ArrayList<>();

    public BeanClassWidget(JPAModelerScene scene, NodeWidgetInfo nodeWidgetInfo) {
        super(scene, nodeWidgetInfo);
        this.addPropertyChangeListener("abstract", (oldValue, value) -> setImage(getIcon()));
    }

    @Override
    public void init() {
        super.init();
        BeanClass beanClass = this.getBaseElementSpec();
        if (beanClass.getAttributes().getAllAttribute().isEmpty() && this.getModelerScene().getModelerFile().isLoaded()) {
            addBeanAttribute("attribute");
        }

        if (beanClass.getClazz() == null || beanClass.getClazz().isEmpty()) {
            beanClass.setClazz(this.getModelerScene().getNextClassName("Class_"));
        }

        setName(beanClass.getClazz());
        setLabel(beanClass.getClazz());
        this.setImage(getIcon());
        validateName(null, this.getName());
    }

    @Override
    public String getIconPath() {
        if (this.getBaseElementSpec().getAbstract()) {
            return JPAModelerUtil.ABSTRACT_JAVA_CLASS_ICON_PATH;
        } else {
            return JPAModelerUtil.JAVA_CLASS_ICON_PATH;
        }
    }

    @Override
    public Image getIcon() {
        if (this.getBaseElementSpec().getAbstract()) {
            return JPAModelerUtil.ABSTRACT_JAVA_CLASS_ICON;
        } else {
            return JPAModelerUtil.JAVA_CLASS_ICON;
        }
    }

    @Override
    public void deleteAttribute(AttributeWidget attributeWidget) {
        BeanClass beanClass = this.getBaseElementSpec();
        BeanAttributes attributes = beanClass.getAttributes();
        if (attributeWidget instanceof BeanAttributeWidget) {
            beanAttributeWidgets.remove((BeanAttributeWidget) attributeWidget);
            attributes.removeBasic(((BeanAttributeWidget) attributeWidget).getBaseElementSpec());
        } else if (attributeWidget instanceof BeanCollectionAttributeWidget) {
            beanCollectionAttributeWidgets.remove((BeanCollectionAttributeWidget) attributeWidget);
            attributes.removeElementCollection(((BeanCollectionAttributeWidget) attributeWidget).getBaseElementSpec());
        } else if (attributeWidget instanceof BeanTransientAttributeWidget) {
            beanTransientAttributeWidgets.remove((BeanTransientAttributeWidget) attributeWidget);
            attributes.removeTransient(((BeanTransientAttributeWidget) attributeWidget).getBaseElementSpec());
        } else if (attributeWidget instanceof AssociationAttributeWidget) {
            if (attributeWidget instanceof OTOAssociationAttributeWidget) {
                OTOAssociationAttributeWidget otoAssociationAttributeWidget = (OTOAssociationAttributeWidget) attributeWidget;
                otoAssociationAttributeWidget.setLocked(true);
                otoAssociationAttributeWidget.getOneToOneAssociationFlowWidget().remove();
                otoAssociationAttributeWidget.setLocked(false);
                oneToOneAssociationAttributeWidgets.remove((OTOAssociationAttributeWidget) attributeWidget);
                attributes.removeOneToOne(((OTOAssociationAttributeWidget) attributeWidget).getBaseElementSpec());
            } else if (attributeWidget instanceof OTMAssociationAttributeWidget) {
                OTMAssociationAttributeWidget otmAssociationAttributeWidget = (OTMAssociationAttributeWidget) attributeWidget;
                otmAssociationAttributeWidget.setLocked(true);
                otmAssociationAttributeWidget.getHierarchicalAssociationFlowWidget().remove();
                otmAssociationAttributeWidget.setLocked(false);
                oneToManyAssociationAttributeWidgets.remove((OTMAssociationAttributeWidget) attributeWidget);
                attributes.removeOneToMany(((OTMAssociationAttributeWidget) attributeWidget).getBaseElementSpec());
            } else if (attributeWidget instanceof MTOAssociationAttributeWidget) {
                MTOAssociationAttributeWidget mtoAssociationAttributeWidget = (MTOAssociationAttributeWidget) attributeWidget;
                mtoAssociationAttributeWidget.setLocked(true);
                mtoAssociationAttributeWidget.getManyToOneAssociationFlowWidget().remove();
                mtoAssociationAttributeWidget.setLocked(false);
                manyToOneAssociationAttributeWidgets.remove((MTOAssociationAttributeWidget) attributeWidget);
                attributes.removeManyToOne(((MTOAssociationAttributeWidget) attributeWidget).getBaseElementSpec());
            } else if (attributeWidget instanceof MTMAssociationAttributeWidget) {
                MTMAssociationAttributeWidget mtmAssociationAttributeWidget = (MTMAssociationAttributeWidget) attributeWidget;
                mtmAssociationAttributeWidget.setLocked(true);
                mtmAssociationAttributeWidget.getManyToManyAssociationFlowWidget().remove();
                mtmAssociationAttributeWidget.setLocked(false);
                manyToManyAssociationAttributeWidgets.remove((MTMAssociationAttributeWidget) attributeWidget);
                attributes.removeManyToMany(((MTMAssociationAttributeWidget) attributeWidget).getBaseElementSpec());
            }
        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        sortAttributes();
        scanDuplicateAttributes(attributeWidget.getBaseElementSpec().getName(), null);
        beanClass.updateArtifact((Attribute) attributeWidget.getBaseElementSpec());
    }

    @Override
    public InheritanceStateType getInheritanceState() {
        return getInheritanceState(false);
    }

    @Override
    public InheritanceStateType getInheritanceState(boolean includeAllClass) {
        return InheritanceStateType.NONE;
    }

    @Override
    public List<AttributeWidget<? extends Attribute>> getAllAttributeWidgets(boolean includeParentClassAttibute) {
        List<AttributeWidget<? extends Attribute>> attributeWidgets = new ArrayList<>();
        JavaClassWidget superClassWidget = this.getSuperclassWidget(); //super class will get other attribute from its own super class
        if (includeParentClassAttibute && superClassWidget instanceof BeanClassWidget) {
            attributeWidgets.addAll(((BeanClassWidget) superClassWidget).getAllAttributeWidgets(includeParentClassAttibute));
        }
        attributeWidgets.addAll(beanAttributeWidgets);
        attributeWidgets.addAll(beanCollectionAttributeWidgets);
        attributeWidgets.addAll(beanTransientAttributeWidgets);
        attributeWidgets.addAll(oneToOneAssociationAttributeWidgets);
        attributeWidgets.addAll(oneToManyAssociationAttributeWidgets);
        attributeWidgets.addAll(manyToOneAssociationAttributeWidgets);
        attributeWidgets.addAll(manyToManyAssociationAttributeWidgets);
        return attributeWidgets;
    }

    public List<AssociationAttributeWidget> getAllAssociationAttributeWidgets() {
        return getAllAssociationAttributeWidgets(false);
    }

    public List<AssociationAttributeWidget> getAllAssociationAttributeWidgets(boolean includeParentClassAttribute) {
        List<AssociationAttributeWidget> attributeWidgets = new ArrayList<>();
        JavaClassWidget classWidget = this.getSuperclassWidget(); //super class will get other attribute from its own super class
        if (includeParentClassAttribute && classWidget instanceof BeanClassWidget) {
            attributeWidgets.addAll(((BeanClassWidget) classWidget).getAllAssociationAttributeWidgets(includeParentClassAttribute));
        }
        attributeWidgets.addAll(oneToOneAssociationAttributeWidgets);
        attributeWidgets.addAll(oneToManyAssociationAttributeWidgets);
        attributeWidgets.addAll(manyToOneAssociationAttributeWidgets);
        attributeWidgets.addAll(manyToManyAssociationAttributeWidgets);
        return attributeWidgets;
    }

    public AssociationAttributeWidget findAssociationAttributeWidget(String id, Class<? extends AssociationAttributeWidget>... associationAttributeWidgetClasses) {
        for (Class<? extends AssociationAttributeWidget> associationAttributeWidgetClass : associationAttributeWidgetClasses) {
            if (associationAttributeWidgetClass == OTOAssociationAttributeWidget.class) {
                for (OTOAssociationAttributeWidget oneToOneAssociationAttributeWidget : oneToOneAssociationAttributeWidgets) {
                    if (oneToOneAssociationAttributeWidget.getId().equals(id)) {
                        return oneToOneAssociationAttributeWidget;
                    }
                }
            } else if (associationAttributeWidgetClass == OTMAssociationAttributeWidget.class) {
                for (OTMAssociationAttributeWidget oneToManyAssociationAttributeWidget : oneToManyAssociationAttributeWidgets) {
                    if (oneToManyAssociationAttributeWidget.getId().equals(id)) {
                        return oneToManyAssociationAttributeWidget;
                    }
                }
            } else if (associationAttributeWidgetClass == MTOAssociationAttributeWidget.class) {
                for (MTOAssociationAttributeWidget manyToOneAssociationAttributeWidget : manyToOneAssociationAttributeWidgets) {
                    if (manyToOneAssociationAttributeWidget.getId().equals(id)) {
                        return manyToOneAssociationAttributeWidget;
                    }
                }
            } else if (associationAttributeWidgetClass == MTMAssociationAttributeWidget.class) {
                for (MTMAssociationAttributeWidget manyToManyAssociationAttributeWidget : manyToManyAssociationAttributeWidgets) {
                    if (manyToManyAssociationAttributeWidget.getId().equals(id)) {
                        return manyToManyAssociationAttributeWidget;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void setName(String name) {
        String previousName = this.name;
        if (StringUtils.isNotBlank(name)) {
            this.name = filterName(name);
            if (this.getModelerScene().getModelerFile().isLoaded()) {
                getBaseElementSpec().setClazz(this.name);
//                refractorReference(previousName, this.name);
            }
            validateName(previousName, this.getName());
        }

    }
    
    @Override
    public void createPinWidget(SubCategoryNodeConfig subCategoryInfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BeanClass createBaseElementSpec() {
        return new BeanClass();
    }

    /**
     * @return the beanAttributeWidgets
     */
    public List<BeanAttributeWidget> getBeanAttributeWidgets() {
        return beanAttributeWidgets;
    }

    /**
     * @return the beanCollectionAttributeWidgets
     */
    public List<BeanCollectionAttributeWidget> getBeanCollectionAttributeWidgets() {
        return beanCollectionAttributeWidgets;
    }

    /**
     * @return the beanTransientAttributeWidget
     */
    public List<BeanTransientAttributeWidget> getBeanTransientAttributeWidgets() {
        return beanTransientAttributeWidgets;
    }

    /**
     * @return the oneToOneAssociationAttributeWidgets
     */
    public List<OTOAssociationAttributeWidget> getOneToOneAssociationAttributeWidgets() {
        return oneToOneAssociationAttributeWidgets;
    }

    /**
     * @return the oneToManyAssociationAttributeWidgets
     */
    public List<OTMAssociationAttributeWidget> getOneToManyAssociationAttributeWidgets() {
        return oneToManyAssociationAttributeWidgets;
    }

    /**
     * @return the manyToOneAssociationAttributeWidgets
     */
    public List<MTOAssociationAttributeWidget> getManyToOneAssociationAttributeWidgets() {
        return manyToOneAssociationAttributeWidgets;
    }

    /**
     * @return the manyToManyAssociationAttributeWidgets
     */
    public List<MTMAssociationAttributeWidget> getManyToManyAssociationAttributeWidgets() {
        return manyToManyAssociationAttributeWidgets;
    }

    public List<AssociationFlowWidget> getUnidirectionalAssociationFlowWidget() {
        return unidirectionalAssociationFlowWidget;
    }

    public boolean addUnidirectionalAssociationFlowWidget(AssociationFlowWidget associationFlowWidget) {
        return unidirectionalAssociationFlowWidget.add(associationFlowWidget);
    }

    public boolean removeUnidirectionalAssociationFlowWidget(AssociationFlowWidget associationFlowWidget) {
        return unidirectionalAssociationFlowWidget.remove(associationFlowWidget);
    }

    public void clearUnidirectionalAssociationFlowWidget() {
        unidirectionalAssociationFlowWidget.clear();
    }

    public List<AssociationFlowWidget> getInverseSideAssociationFlowWidgets() {
        return inverseSideAssociationFlowWidgets;
    }

    public void addInverseSideAssociationFlowWidget(AssociationFlowWidget associationFlowWidget) {
        inverseSideAssociationFlowWidgets.add(associationFlowWidget);
    }

    public void removeInverseSideAssociationFlowWidget(AssociationFlowWidget associationFlowWidget) {
        inverseSideAssociationFlowWidgets.remove(associationFlowWidget);
    }

    public BeanAttributeWidget addBeanAttribute(String name) {
        return addBeanAttribute(name, null);
    }

    public BeanAttributeWidget addBeanAttribute(String name, BeanAttribute attribute) {
        BeanClass javaClass = this.getBaseElementSpec();

        if (attribute == null) {
            attribute = new BeanAttribute();
            attribute.setId(getAutoGeneratedStringId());
            attribute.setAttributeType(String.class.getName());
            attribute.setName(name);
            javaClass.getAttributes().addBasic(attribute);
        }
        BeanAttributeWidget attributeWidget = createPinWidget(attribute, BeanAttributeWidget.class, 
                widgetInfo -> new BeanAttributeWidget(this.getModelerScene(), this, widgetInfo));
        getBeanAttributeWidgets().add(attributeWidget);
        sortAttributes();
        scanDuplicateAttributes(null, attribute.getName());
        return attributeWidget;
    }

    public BeanCollectionAttributeWidget addBeanCollectionAttribute(String name) {
        return addBeanCollectionAttribute(name, null);
    }

    public BeanCollectionAttributeWidget addBeanCollectionAttribute(String name, BeanCollectionAttribute collectionAttribute) {
        BeanClass javaClass = this.getBaseElementSpec();

        if (collectionAttribute == null) {
            collectionAttribute = new BeanCollectionAttribute();
            collectionAttribute.setId(getAutoGeneratedStringId());
            collectionAttribute.setAttributeType(String.class.getName());
            collectionAttribute.setName(name);
            javaClass.getAttributes().addElementCollection(collectionAttribute);
        }
        BeanCollectionAttributeWidget attributeWidget = createPinWidget(collectionAttribute, BeanCollectionAttributeWidget.class, 
                widgetInfo -> new BeanCollectionAttributeWidget(this.getModelerScene(), this, widgetInfo));
        getBeanCollectionAttributeWidgets().add(attributeWidget);
        sortAttributes();
        scanDuplicateAttributes(null, collectionAttribute.getName());
        return attributeWidget;
    }

    public BeanTransientAttributeWidget addBeanTransientAttribute(String name) {
        return addBeanTransientAttribute(name, null);
    }

    public BeanTransientAttributeWidget addBeanTransientAttribute(String name, Transient _transient) {
        BeanClass javaClass = this.getBaseElementSpec();

        if (_transient == null) {
            _transient = new Transient();
            _transient.setId(getAutoGeneratedStringId());
            _transient.setAttributeType(String.class.getName());
            _transient.setName(name);
            javaClass.getAttributes().addTransient(_transient);
        }
        BeanTransientAttributeWidget attributeWidget = createPinWidget(_transient, BeanTransientAttributeWidget.class, 
                widgetInfo -> new BeanTransientAttributeWidget(this.getModelerScene(), this, widgetInfo));
        getBeanTransientAttributeWidgets().add(attributeWidget);
        sortAttributes();
        scanDuplicateAttributes(null, _transient.getName());
        return attributeWidget;
    }

    public OTOAssociationAttributeWidget addOneToOneAssociationAttribute(String name) {
        return addOneToOneAssociationAttribute(name, null);
    }

    public OTOAssociationAttributeWidget addOneToOneAssociationAttribute(String name, OneToOneAssociation oneToOne) {
        BeanClass javaClass = this.getBaseElementSpec();
        if (oneToOne == null) {
            oneToOne = new OneToOneAssociation();
            oneToOne.setId(getAutoGeneratedStringId());
            oneToOne.setName(name);
            javaClass.getAttributes().addAssociationAttribute(oneToOne);
        }
        OTOAssociationAttributeWidget attributeWidget = createPinWidget(oneToOne, OTOAssociationAttributeWidget.class, 
                widgetInfo -> new OTOAssociationAttributeWidget(this.getModelerScene(), this, widgetInfo));
        oneToOneAssociationAttributeWidgets.add(attributeWidget);
        return attributeWidget;
    }

    public OTMAssociationAttributeWidget addOneToManyAssociationAttribute(String name) {
        return addOneToManyAssociationAttribute(name, null);
    }

    public OTMAssociationAttributeWidget addOneToManyAssociationAttribute(String name, OneToManyAssociation oneToMany) {
        BeanClass javaClass = this.getBaseElementSpec();
        if (oneToMany == null) {
            oneToMany = new OneToManyAssociation();
            oneToMany.setId(getAutoGeneratedStringId());
            oneToMany.setName(name);
            oneToMany.setCollectionType(List.class.getName());
            oneToMany.setCollectionImplType(ArrayList.class.getName());
            javaClass.getAttributes().addAssociationAttribute(oneToMany);
        }
        OTMAssociationAttributeWidget attributeWidget = createPinWidget(oneToMany, OTMAssociationAttributeWidget.class, 
                widgetInfo -> new OTMAssociationAttributeWidget(this.getModelerScene(), this, widgetInfo));
        getOneToManyAssociationAttributeWidgets().add(attributeWidget);
        sortAttributes();
        scanDuplicateAttributes(null, oneToMany.getName());
        return attributeWidget;
    }

    public MTOAssociationAttributeWidget addManyToOneAssociationAttribute(String name) {
        return addManyToOneAssociationAttribute(name, null);
    }

    public MTOAssociationAttributeWidget addManyToOneAssociationAttribute(String name, ManyToOneAssociation manyToOne) {
        BeanClass javaClass = this.getBaseElementSpec();
        if (manyToOne == null) {
            manyToOne = new ManyToOneAssociation();
            manyToOne.setId(getAutoGeneratedStringId());
            manyToOne.setName(name);
            javaClass.getAttributes().addAssociationAttribute(manyToOne);
        }
        MTOAssociationAttributeWidget attributeWidget = createPinWidget(manyToOne, MTOAssociationAttributeWidget.class, 
                widgetInfo -> new MTOAssociationAttributeWidget(this.getModelerScene(), this, widgetInfo));
        getManyToOneAssociationAttributeWidgets().add(attributeWidget);
        return attributeWidget;
    }

    public MTMAssociationAttributeWidget addManyToManyAssociationAttribute(String name) {
        return addManyToManyAssociationAttribute(name, null);
    }

    public MTMAssociationAttributeWidget addManyToManyAssociationAttribute(String name, ManyToManyAssociation manyToMany) {
        BeanClass javaClass = this.getBaseElementSpec();
        if (manyToMany == null) {
            manyToMany = new ManyToManyAssociation();
            manyToMany.setId(getAutoGeneratedStringId());
            manyToMany.setName(name);
            manyToMany.setCollectionType(List.class.getName());
            manyToMany.setCollectionImplType(ArrayList.class.getName());
            javaClass.getAttributes().addAssociationAttribute(manyToMany);
        }
        MTMAssociationAttributeWidget attributeWidget = createPinWidget(manyToMany, MTMAssociationAttributeWidget.class, 
                widgetInfo -> new MTMAssociationAttributeWidget(this.getModelerScene(), this, widgetInfo));
        getManyToManyAssociationAttributeWidgets().add(attributeWidget);
        sortAttributes();
        scanDuplicateAttributes(null, manyToMany.getName());
        return attributeWidget;
    }

    @Override
    public void createPinWidget(String docId) {
        if (null != docId) {
            switch (docId) {
                case BEAN_ATTRIBUTE:
                    this.addBeanAttribute(getNextAttributeName()).edit();
                    break;
                case BEAN_COLLECTION_ATTRIBUTE:
                    this.addBeanCollectionAttribute(getNextAttributeName(null, true)).edit();
                    break;
                case TRANSIENT_ATTRIBUTE:
                    this.addBeanTransientAttribute(getNextAttributeName()).edit();
                    break;
                default:
                    throw new UnsupportedOperationException("docId not supported yet : " + docId); //To change body of generated methods, choose Tools | Templates.        }
            }
        }
    }

    @Override
    public Map<String, List<Widget>> getAttributeCategories() {
        Map<String, List<Widget>> categories = new LinkedHashMap<>();
        if (!getBeanAttributeWidgets().isEmpty()) {
            categories.put("Basic", new LinkedList<>(beanAttributeWidgets));
        }
        
        if (!getBeanCollectionAttributeWidgets().isEmpty()) {
            categories.put("Collection", new LinkedList<>(beanCollectionAttributeWidgets));
        }
        
        List<Widget> associationAttributeWidgets = new LinkedList<>(oneToOneAssociationAttributeWidgets);
        associationAttributeWidgets.addAll(manyToOneAssociationAttributeWidgets);
        associationAttributeWidgets.addAll(oneToManyAssociationAttributeWidgets);
        associationAttributeWidgets.addAll(manyToManyAssociationAttributeWidgets);
        if (!associationAttributeWidgets.isEmpty()) {
            categories.put("Association", associationAttributeWidgets);
        }
        
        if (!getBeanTransientAttributeWidgets().isEmpty()) {
            categories.put("Transient", new LinkedList<>(getBeanTransientAttributeWidgets()));
        }
        return categories;
    }

}
