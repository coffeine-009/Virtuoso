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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.springframework.util.Assert.notNull;

/**
 * Class for reflect table Composer from persistence layout
 *
 * @version 1.0
 */
@JsonIgnoreProperties( ignoreUnknown = true )
@SuppressWarnings( "serial" )
@Entity
@Table(
    name = "composer",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {
                "id_user"
            }
        )
    }
)
public class Composer implements Serializable {

    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column( name = "id" )
    protected Long id;

    @JsonIgnore
    @Valid
    @OneToOne
    @JoinColumn( name = "id_user" )
    protected User user;

//    @NotNull
//    @NotEmpty
//    @Size( min = 1 )
//    @Valid
    @OneToMany(
        mappedBy = "composer",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    protected List < ComposerLocale > data;

    @Transient
    protected String firstName;

    @Transient
    protected String lastName;

    @Transient
    protected String fatherName;

    /**
     * Original locale of composer
     */
    @NotNull
    @NotEmpty
    @Length( max = 5 )
    @Column( name = "locale", length = 5 )
    protected String locale;

    @Column( name = "gender" )
    protected Boolean gender;

    @Column( name = "birthday", columnDefinition = "DATE" )
    protected LocalDate birthday;

    //TODO: add validation
    @Column( name = "deathDate", columnDefinition = "DATE" )
    protected LocalDate deathDate;

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
        this.data = new ArrayList<>();
    }

    /**
     * Construct for create new composer
     *
     * @param birthday
     * @param deathDate
     */
    public Composer(
        String locale,
        Boolean gender,
        LocalDate birthday,
        LocalDate deathDate
    ) {
        //- Initialisation -//
        this.locale = locale;
        this.gender = gender;
        this.birthday = birthday;
        this.deathDate = deathDate;
    }

    /**
     * Construct for create new composer
     *
     */
    public Composer(
        final String locale,
        Boolean gender,
        LocalDate birthday,
        LocalDate deathDate,
        List < ComposerLocale > data
    ) {
        //- Check params -//
        notNull( data );

        //- Initialisation -//
        this.locale = locale;
        this.gender = gender;
        this.birthday = birthday;
        this.deathDate = deathDate;
        this.data = data;

        for ( ComposerLocale composerLocale : this.data ) {
            composerLocale.setComposer( this );
        }
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
     * Get first name in original locale
     *
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get last name in origin locale
     *
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get father name in origin locale
     *
     * @return String
     */
    public String getFatherName() {
        return fatherName;
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
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Get date of death
     *
     * @return Calendar
     */
    public LocalDate getDeathDate() {
        return deathDate;
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
    public void setBirthday( LocalDate birthday ) {
        this.birthday = birthday;
    }

    /**
     * Set date of death
     *
     * @param deathDate
     */
    public void setDeathDate( LocalDate deathDate ) {
        this.deathDate = deathDate;
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


    //- SECTION :: HELPER -//
    /**
     * Populate not stored data
     */
    @PostLoad
    private void postRead() {
        //- Search origin data about composer -//
        for (ComposerLocale composerLocale : this.data ) {
            //- Check origin locale -//
            if (this.locale.equals( composerLocale.getLocale() )) {
                //- Populate data -//
                this.firstName = composerLocale.getFirstName();
                this.lastName = composerLocale.getLastName();
                this.fatherName = composerLocale.getMiddleName();
            }
        }
    }
}
