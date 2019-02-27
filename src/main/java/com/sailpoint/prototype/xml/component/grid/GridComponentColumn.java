package com.sailpoint.prototype.xml.component.grid;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import static com.sailpoint.prototype.xml.component.XmlComponent.DEFAULT_XML_NAMESPACE;

/**
 * Class for grid column component
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = GridComponentColumn.NAME_SPACE)
public class GridComponentColumn {

    /**
     * Name space for grid columns
     */
    public static final String NAME_SPACE = DEFAULT_XML_NAMESPACE + "/grid/column";

    @XmlAttribute(name = "property")
    private String property;

    @XmlAttribute(name = "caption")
    private String caption;
}
