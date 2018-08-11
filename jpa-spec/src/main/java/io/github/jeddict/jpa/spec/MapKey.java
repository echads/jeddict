//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.01.21 at 01:52:19 PM IST
//
package io.github.jeddict.jpa.spec;

import static io.github.jeddict.jcode.JPAConstants.MAP_KEY_FQN;
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

/**
 *
 *
 * @Target({METHOD, FIELD}) @Retention(RUNTIME) public @interface MapKey {
 * String name() default ""; }
 *
 *
 *
 * <p>
 * Java class for map-key complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="map-key">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "map-key")
public class MapKey implements JAREAnnotationLoader {

    @XmlAttribute
    protected String name;
    
    
    @Override
    public MapKey load(Element element, AnnotationMirror annotationMirror) {
        if (annotationMirror == null) {
            annotationMirror = JavaSourceParserUtil.findAnnotation(element, MAP_KEY_FQN);
        }
        MapKey mapKey = null;
        if (annotationMirror != null) {
            mapKey = this;
            mapKey.name = (String) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "name");
        }
        return mapKey;
    }

    public static MapKey load(MemberExplorer member) {
        MapKey mapKey = null;
        Optional<AnnotationExplorer> mapkeyOpt = member.getAnnotation(javax.persistence.MapKey.class);
        if (mapkeyOpt.isPresent()) {
            mapkeyOpt.get()
                    .getString("name").ifPresent(mapKey::setName);
        }
        return mapKey;
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

}
