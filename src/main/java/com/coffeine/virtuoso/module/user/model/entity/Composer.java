/// *** User :: Model :: Entity :: Composer *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-04-16 22:04:59 :: 2014-04-17 23:48:05
     *
     * @address /Ukraine/Ivano-Frankivsk/Chornovola/104
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class for reflect table Composer from persistence layout
 *
 * @version 1.0
 */
@JsonIgnoreProperties( ignoreUnknown = true )
@SuppressWarnings( "serial" )
@Entity
@Table(
    name = "composer"
)
public class Composer implements Serializable {

    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column( name = "id" )
    protected Long id;

    @Valid
    @ManyToOne
    @JoinColumn( name = "id_user" )
    protected User user;

    @JsonIgnore
    @NotNull
    @NotEmpty
    @Valid
    @OneToMany(
        mappedBy = "composer",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    protected List < ComposerLocale > data;

    @NotNull
    @NotEmpty
    @Length( max = 5 )
    @Column( name = "locale", length = 5 )
    protected String locale;

    @Column( name = "gender" )
    protected Boolean gender;

    @Column( name = "birthday", columnDefinition = "TIMESTAMP NULL" )
    protected Calendar birthday;

    //TODO: add validation
    @Column( name = "deathday", columnDefinition = "TIMESTAMP NULL" )
    protected Calendar deathday;

    @Column(
        name = "creation",
        columnDefinition = " TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    protected Calendar creation;


    /// *** Methods     *** ///
    /**
     * Construct default
     */
    public Composer(
    ) {
        //- Initialization -//
        this.data = new ArrayList < ComposerLocale >();
    }

    /**
     * Construct for create new composer
     *
     * @param birthday
     * @param deathday
     */
    public Composer(
        String locale,
        Boolean gender,
        Calendar birthday,
        Calendar deathday
    ) {
        //- Initialisation -//
        this.locale = locale;
        this.gender = gender;
        this.birthday = birthday;
        this.deathday = deathday;
    }

    /**
     * Constructor for create new composer
     *
     * @param data
     * @param locale
     */
    public Composer(
        List < ComposerLocale > data,
        String locale
    ) {
        this();

        //- Set locales -//
        for ( ComposerLocale composerLocale : data ) {
            this.addComposerLocale( composerLocale );
        }
        this.locale = locale;
    }

    public Composer(
        String locale
    ) {
        this.data = null;
        this.locale = locale;
    }
    //- SECTION :: GET -//
    /**
     * Get ID of composer
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Get user-composer
     *
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * Get composer's data
     *
     * @return List<ComposerLocale>
     */
    public List < ComposerLocale > getData() {
        return data;
    }

    /**
     * Get locale of this composer
     *
     * @return String
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Get gender of this composer
     *
     * @return Boolean
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * Get date of birthday
     *
     * @return Calendar
     */
    public Calendar getBirthday() {
        return birthday;
    }

    /**
     * Get date of death
     *
     * @return Calendar
     */
    public Calendar getDeathday() {
        return deathday;
    }

    /**
     * Get time of create record
     *
     * @return Calendar
     */
    public Calendar getCreation() {
        return creation;
    }


    //- SECTION :: SET -//
    /**
     * Set ID of composer
     *
     * @param id
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set user-composer
     *
     * @param user
     */
    public void setUser( User user ) {
        this.user = user;
    }

    /**
     * Set composer's data
     *
     * @param data
     */
    public void setData( List < ComposerLocale > data ) {
        this.data = data;
    }

    /**
     * Set locale of this composer
     *
     * @param locale
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }

    /**
     * Set gender of this composer
     *
     * @param gender
     */
    public void setGender( Boolean gender ) {
        this.gender = gender;
    }

    /**
     * Set date of birthday
     *
     * @param birthday
     */
    public void setBirthday( Calendar birthday ) {
        this.birthday = birthday;
    }

    /**
     * Set date of death
     *
     * @param deathday
     */
    public void setDeathday( Calendar deathday ) {
        this.deathday = deathday;
    }

    /**
     * Add unique composer locale
     *
     * @param composerLocale
     */
    public void addComposerLocale( ComposerLocale composerLocale) {
        //- Set composer-//
        composerLocale.setComposer( this );

        //- Add Composer locale-//
        if( !this.data.contains( composerLocale ) ) {
            this.data.add( composerLocale );
        }
    }
}
