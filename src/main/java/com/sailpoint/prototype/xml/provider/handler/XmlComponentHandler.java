package com.sailpoint.prototype.xml.provider.handler;

import com.sailpoint.prototype.xml.component.XmlComponent;

/**
 * Handler for building components from xml
 */
public interface XmlComponentHandler<T> {

    /**
     * Check if handler can handle this component
     *
     * @param xmlComponent - xml component to handle
     * @return can handle
     */
    boolean canHandle(XmlComponent xmlComponent);

    /**
     * Build component from xml
     *
     * @param xmlComponent - xml component source
     * @return component from xml
     */
    T build(XmlComponent xmlComponent);
}
