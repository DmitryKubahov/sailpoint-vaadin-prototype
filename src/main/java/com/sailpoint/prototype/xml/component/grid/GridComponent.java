package com.sailpoint.prototype.xml.component.grid;

import com.sailpoint.prototype.xml.component.XmlComponent;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Grid component
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "grid", namespace = GridComponent.NAME_SPACE)
@XmlRootElement(name = "grid", namespace = GridComponent.NAME_SPACE)
public class GridComponent implements XmlComponent {

    /**
     * Name space for grid
     */
    public static final String NAME_SPACE = DEFAULT_XML_NAMESPACE + "/grid";

    /**
     * Click listener name
     */
    @XmlElement(name = "clickListenerClassName")
    public Class<?> clickListenerClassName;

    /**
     * Grid columns data
     */
    @XmlElementWrapper(name = "columns")
    @XmlElement(name = "column")
    private List<GridComponentColumn> columns;
}
