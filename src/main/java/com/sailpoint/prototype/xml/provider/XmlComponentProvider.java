package com.sailpoint.prototype.xml.provider;

/**
 * Xml component provider for xml component handlers
 */
public interface XmlComponentProvider<T> {

    /**
     * Build component from xml
     *
     * @param xml - xml source
     * @return component from xml
     */
    T build(String xml);
}
