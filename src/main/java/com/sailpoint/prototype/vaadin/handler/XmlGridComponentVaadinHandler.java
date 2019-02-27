package com.sailpoint.prototype.vaadin.handler;

import com.sailpoint.prototype.data.JpaRepositoryProvider;
import com.sailpoint.prototype.model.entity.IdentityIq;
import com.sailpoint.prototype.model.repository.IdentityRepository;
import com.sailpoint.prototype.xml.component.XmlComponent;
import com.sailpoint.prototype.xml.component.grid.GridComponent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemClickEvent;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Handler for building VAADIN components from xml
 */
@Slf4j
@Component
public class XmlGridComponentVaadinHandler implements XmlComponentVaadinHandler<Grid> {

    /**
     * Identity repository
     */
    private final IdentityRepository identityRepository;

    /**
     * COnstructor with identity repoository
     *
     * @param identityRepository - identity repository
     */
    @Autowired
    public XmlGridComponentVaadinHandler(IdentityRepository identityRepository) {
        this.identityRepository = identityRepository;
    }

    /**
     * Check is xml component is grid component
     *
     * @param xmlComponent - xml component to handle
     * @return can build grid vaadin component
     */
    @Override
    public boolean canHandle(XmlComponent xmlComponent) {
        log.debug("GRID - is xml component can be handled");
        return xmlComponent instanceof GridComponent;
    }

    /**
     * Build grid vaadin component from source
     *
     * @param source - xml grid component
     * @return vaadin grid component
     */
    @Override
    public Grid build(@NonNull XmlComponent source) {
        log.debug("Build vaadin grid from source");

        log.trace("Source component:[{}]", source);
        log.debug("Cast source to GridComponent");
        GridComponent gridComponent = (GridComponent) source;

        log.debug("Create vaadin grid component for IDENTITY");
        Grid<IdentityIq> identityGrid = new Grid<>(IdentityIq.class, false);
        log.debug("Create vaadin grid component columns");
        gridComponent.getColumns().forEach(column -> {
            log.debug("Add column. Property:[{}], caption:[{}]", column.getProperty(), column.getCaption());
            identityGrid.addColumn(column.getProperty()).setHeader(column.getCaption());
        });

        addItemClickListener(identityGrid, gridComponent.getClickListenerClassName());

        identityGrid.setDataProvider(new JpaRepositoryProvider<>(identityRepository));

        log.debug("Set full size for grid");
        identityGrid.setSizeFull();

        log.debug("Building vaadin grid has been finished");
        return identityGrid;
    }

    /**
     * Try to initialize click listener and add it to grid component
     *
     * @param identityGrid           - grid target
     * @param clickListenerClassName - click listener class
     */
    private void addItemClickListener(Grid<IdentityIq> identityGrid, Class<?> clickListenerClassName) {
        log.debug("Try to add grid item click listener");
        if (clickListenerClassName == null) {
            log.debug("Click listener is null");
            return;
        }
        log.debug("Initialize click listener instance and set it to grid");
        try {
            identityGrid.addItemClickListener(
                    (ComponentEventListener<ItemClickEvent<IdentityIq>>) clickListenerClassName.newInstance());
        } catch (IllegalAccessException | InstantiationException | ClassCastException e) {
            log.warn("Can`t set click listener to grid. Click listener class:[{}], error:[{}]", clickListenerClassName,
                    e.getMessage());
        }
    }
}
