//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.01.21 at 01:52:19 PM IST
//
package io.github.jeddict.jpa.spec;

import static io.github.jeddict.jcode.JPAConstants.COLUMN_FQN;
import io.github.jeddict.jpa.spec.extend.BaseElement;
import io.github.jeddict.jpa.spec.validator.column.ColumnValidator;
import io.github.jeddict.source.AnnotationExplorer;
import io.github.jeddict.source.JAREAnnotationLoader;
import io.github.jeddict.source.JavaSourceParserUtil;
import io.github.jeddict.source.MemberExplorer;
import java.util.Optional;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.eclipse.persistence.internal.jpa.metadata.columns.ColumnMetadata;

/**
 *
 *
 * @Target({METHOD, FIELD}) @Retention(RUNTIME) public @interface Column {
 * String name() default ""; boolean unique() default false; boolean nullable()
 * default true; boolean insertable() default true; boolean updatable() default
 * true; String columnDefinition() default ""; String table() default ""; int
 * length() default 255; int precision() default 0; // decimal precision int
 * scale() default 0; // decimal scale }
 *
 *
 *
 * <p>
 * Java class for column complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="column">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="unique" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="nullable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="insertable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="updatable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="column-definition" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="table" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="length" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="precision" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="scale" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "column")
@XmlJavaTypeAdapter(value = ColumnValidator.class)
public class Column extends BaseElement implements JAREAnnotationLoader {

    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute
    protected Boolean unique = false;
    @XmlAttribute
    protected Boolean nullable = true;
    @XmlAttribute
    protected Boolean insertable = true;
    @XmlAttribute
    protected Boolean updatable = true;
    @XmlAttribute(name = "column-definition")
    protected String columnDefinition;
    @XmlAttribute(name = "table")
    protected String table;
    @XmlAttribute(name = "length")
    protected Integer length;
    @XmlAttribute(name = "precision")
    protected Integer precision;
    @XmlAttribute(name = "scale")
    protected Integer scale;

    @Override
    @Deprecated
    public Column load(Element element, AnnotationMirror annotationMirror) {
        if (annotationMirror == null) {
            annotationMirror = JavaSourceParserUtil.findAnnotation(element, COLUMN_FQN);
        }
        Column column = null;
        if (annotationMirror != null) {
            column = this;
            column.name = (String) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "name");
            column.unique = (Boolean) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "unique");
            column.nullable = (Boolean) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "nullable");
            column.insertable = (Boolean) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "insertable");
            column.updatable = (Boolean) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "updatable");
            column.columnDefinition = (String) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "columnDefinition");
            column.table = (String) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "table");
            column.length = (Integer) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "length");
            column.precision = (Integer) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "precision");
            column.scale = (Integer) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "scale");
        }
        return column;
    }

    @Deprecated
    public Column load(Element element) {
        AnnotationMirror annotationMirror = JavaSourceParserUtil.findAnnotation(element, COLUMN_FQN);
        return load(element, annotationMirror);
    }

    public static Column load(MemberExplorer member) {
        Column column = null;
        Optional<AnnotationExplorer> annotationOpt = member.getAnnotation(javax.persistence.Column.class);
        if (annotationOpt.isPresent()) {
            column = load(annotationOpt.get());
        }
        return column;
    }

    public static Column load(AnnotationExplorer annotation) {
        Column column = new Column();
        annotation.getString("name").ifPresent(column::setName);
        annotation.getBoolean("unique").ifPresent(column::setUnique);
        annotation.getBoolean("nullable").ifPresent(column::setNullable);
        annotation.getBoolean("insertable").ifPresent(column::setInsertable);
        annotation.getBoolean("updatable").ifPresent(column::setUpdatable);
        annotation.getString("columnDefinition").ifPresent(column::setColumnDefinition);
        annotation.getString("table").ifPresent(column::setTable);
        annotation.getInt("length").ifPresent(column::setLength);
        annotation.getInt("precision").ifPresent(column::setPrecision);
        annotation.getInt("scale").ifPresent(column::setScale);
        return column;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the unique property.
     *
     * @return possible object is {@link Boolean }
     *
     */
    public Boolean getUnique() {
        if(unique == null){
            unique = false;
        }
        return unique;
    }

    /**
     * Sets the value of the unique property.
     *
     * @param value allowed object is {@link Boolean }
     *
     */
    public void setUnique(Boolean value) {
        this.unique = value;
    }

    /**
     * Gets the value of the nullable property.
     *
     * @return possible object is {@link Boolean }
     *
     */
    public Boolean getNullable() {
        if(nullable == null){
            nullable = true;
        }
        return nullable;
    }

    /**
     * Sets the value of the nullable property.
     *
     * @param value allowed object is {@link Boolean }
     *
     */
    public void setNullable(Boolean value) {
        this.nullable = value;
    }

    /**
     * Gets the value of the insertable property.
     *
     * @return possible object is {@link Boolean }
     *
     */
    public Boolean getInsertable() {
        if(insertable == null){
            insertable = true;
        }
        return insertable;
    }

    /**
     * Sets the value of the insertable property.
     *
     * @param value allowed object is {@link Boolean }
     *
     */
    public void setInsertable(Boolean value) {
        this.insertable = value;
    }

    /**
     * Gets the value of the updatable property.
     *
     * @return possible object is {@link Boolean }
     *
     */
    public Boolean getUpdatable() {
        if(updatable == null){
            updatable = true;
        }
        return updatable;
    }

    /**
     * Sets the value of the updatable property.
     *
     * @param value allowed object is {@link Boolean }
     *
     */
    public void setUpdatable(Boolean value) {
        this.updatable = value;
    }

    /**
     * Gets the value of the columnDefinition property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getColumnDefinition() {
        return columnDefinition;
    }

    /**
     * Sets the value of the columnDefinition property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setColumnDefinition(String value) {
        this.columnDefinition = value;
    }

    /**
     * Gets the value of the table property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getTable() {
        return table;
    }

    /**
     * Sets the value of the table property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setTable(String value) {
        this.table = value;
    }

    /**
     * Gets the value of the length property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setLength(Integer value) {
        this.length = value;
    }

    /**
     * Gets the value of the precision property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getPrecision() {
        return precision;
    }

    /**
     * Sets the value of the precision property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setPrecision(Integer value) {
        this.precision = value;
    }

    /**
     * Gets the value of the scale property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getScale() {
        return scale;
    }

    /**
     * Sets the value of the scale property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setScale(Integer value) {
        this.scale = value;
    }

    public ColumnMetadata getAccessor() {
        ColumnMetadata accessr = new ColumnMetadata();
        accessr.setName(name);
        accessr.setColumnDefinition(columnDefinition);
        accessr.setInsertable(insertable);
        accessr.setLength(length);
        accessr.setNullable(nullable);
        accessr.setPrecision(precision);
        accessr.setScale(scale);
        accessr.setTable(table);
        accessr.setUnique(unique);
        accessr.setUpdatable(updatable);

        return accessr;
    }
 
}
