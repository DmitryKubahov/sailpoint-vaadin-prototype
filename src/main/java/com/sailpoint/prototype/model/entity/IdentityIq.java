package com.sailpoint.prototype.model.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Iq identity entity
 */
@Data
@Entity
@Table(name = "spt_identity", schema = "identityiq")
public class IdentityIq implements SailPointEntity {

    /**
     * Identifier for entity
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * First name
     */
    @Column(name = "firstname")
    private String firstName;

    /**
     * Last name
     */
    @Column(name = "lastname")
    private String lastName;

    /**
     * Display name
     */
    @Column(name = "display_name")
    private String displayName;

    /**
     * Email
     */
    @Column(name = "email")
    private String email;

    /**
     * Manager for current identity
     */
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager")
    private IdentityIq manager;

}
