package com.sailpoint.prototype.component.identity;

import com.sailpoint.prototype.component.MainView;
import com.sailpoint.prototype.xml.provider.XmlComponentProvider;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Identities page
 */
@Slf4j
@Route(layout = MainView.class)
public class Identities extends VerticalLayout {

    /**
     * Path to xml for identities
     */
    private static final String PATH_TO_XML = "/xml/identities/identities.xml";


    /**
     * Constructor where page is building
     *
     * @param xmlComponentProvider - provider for handling xml and build components
     */
    @Autowired
    public Identities(XmlComponentProvider<? extends Component> xmlComponentProvider) throws IOException {
        log.debug("Try to build identities page");
        log.debug("Set size full for it");
        setSizeFull();
        log.debug("Generate vaadin component from xml path:[{}]", PATH_TO_XML);
        add(xmlComponentProvider
                .build(IOUtils.toString(
                        getClass().getClassLoader().getResourceAsStream(PATH_TO_XML),
                        Charsets.UTF_8)));
    }

}
