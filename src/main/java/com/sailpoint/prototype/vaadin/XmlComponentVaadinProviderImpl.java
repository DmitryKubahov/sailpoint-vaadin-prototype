package com.sailpoint.prototype.vaadin;

import com.sailpoint.prototype.exception.UnsupportedXmlComponent;
import com.sailpoint.prototype.xml.component.XmlComponent;
import com.sailpoint.prototype.xml.provider.XmlComponentProvider;
import com.sailpoint.prototype.xml.provider.handler.XmlComponentHandler;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.List;

/**
 * Provider for hadling components and build vaadin components
 */
@Slf4j
@Component
public class XmlComponentVaadinProviderImpl<T extends com.vaadin.flow.component.Component>
        implements XmlComponentProvider<T> {

    /**
     * Xml component marshaller
     */
    private final Jaxb2Marshaller marshaller;

    /**
     * All available handlers
     */
    private final List<XmlComponentHandler<T>> handlers;

    /**
     * Constructor with parameters
     *
     * @param marshaller - xml component marshaller
     * @param handlers   - vaadin xml handlers
     */
    @Autowired(required = false)
    public XmlComponentVaadinProviderImpl(Jaxb2Marshaller marshaller, List<XmlComponentHandler<T>> handlers) {
        log.debug("Init xml component provider");

        log.debug("Setting xml component marshaller");
        this.marshaller = marshaller;

        log.debug("Setting handlers");
        this.handlers = handlers;

        log.debug("Init xml component provider finished");
    }

    /**
     * Build component from xml source
     *
     * @param xml - xml string source
     * @return component after handling
     */
    @Override
    public T build(@NonNull String xml) {
        log.debug("Try to handle xml component");
        log.trace("Xml component:[{}]", xml);
        XmlComponent xmlComponent;
        try {
            log.debug("Try to unmarshal xml");
            xmlComponent = (XmlComponent) marshaller.unmarshal(new StreamSource(new StringReader(xml)));
            log.trace("After unmarshalling xml component:[{}]", xmlComponent);
            for (XmlComponentHandler<T> handler : handlers) {
                log.debug("Check if handler:[{}] can handle xml component:[{}]", handler, xmlComponent);
                if (handler.canHandle(xmlComponent)) {
                    log.debug("Handler:[{}] can handle xml component:[{}]", handler, xmlComponent);
                    log.debug("Try to handle component");
                    return handler.build(xmlComponent);
                }
                log.debug("Handler:[{}] can`t handle xml component:[{}]", handler, xmlComponent);
            }
        } catch (Throwable ex) {
            log.error("Error while handling xml:[{}]", xml, ex);
            throw ex;
        }
        UnsupportedXmlComponent unsupportedXmlComponent = new UnsupportedXmlComponent(xmlComponent, xml);
        log.error("Can`t handle xml component", unsupportedXmlComponent);
        throw unsupportedXmlComponent;
    }
}
