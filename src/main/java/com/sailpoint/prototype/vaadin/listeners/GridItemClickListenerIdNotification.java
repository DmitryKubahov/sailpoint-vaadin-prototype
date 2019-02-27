package com.sailpoint.prototype.vaadin.listeners;

import com.sailpoint.prototype.model.entity.IdentityIq;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.notification.Notification;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.Optional;

/**
 * Simple item click listener for grid with identities
 */
@Slf4j
public class GridItemClickListenerIdNotification implements ComponentEventListener<ItemClickEvent<IdentityIq>> {

    /**
     * Notification message pattern
     */
    private static final String NOTIFICATION_MASSAGE = "Identity id:{0}";

    @Override
    public void onComponentEvent(ItemClickEvent<IdentityIq> event) {
        log.debug("Click listener event");
        Notification.show(MessageFormat.format(NOTIFICATION_MASSAGE,
                Optional.ofNullable(event).map(ItemClickEvent::getItem).map(IdentityIq::getId).orElse(null)));
    }
}
