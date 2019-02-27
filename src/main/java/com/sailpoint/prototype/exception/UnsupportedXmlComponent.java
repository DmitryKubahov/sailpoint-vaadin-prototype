package com.sailpoint.prototype.exception;

import com.sailpoint.prototype.xml.component.XmlComponent;

import java.text.MessageFormat;

/**
 * Exception of unsupported xml component
 */
public class UnsupportedXmlComponent extends UnsupportedOperationException {

    /**
     * Main error message
     */
    private static final String ERROR_MESSAGE = "Can`t handle xml component. Xml component:[{0}], xml:[{1}]";

    /**
     * Default constructor
     *
     * @param xmlComponent - xml component
     * @param xml          - string xml source
     */
    public UnsupportedXmlComponent(XmlComponent xmlComponent, String xml) {
        super(MessageFormat.format(ERROR_MESSAGE, xmlComponent, xml));

    }
}
