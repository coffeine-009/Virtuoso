/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.entity;

import com.coffeine.virtuoso.main.model.serializer.JsonDateSerializer;
import com.coffeine.virtuoso.security.model.entity.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.time.LocalDate;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.util.Assert.notNull;

/**
 * Class for reflect table Poet from persistence layout.
 *
 * @version 1.0
 */
@JsonIgnoreProperties( ignoreUnknown = true )
@SuppressWarnings( "serial" )
@Entity
@Table(
    name = "poet",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {
                "id_user"
            }
        )
    }
)
public class Poet implements Serializable {

    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column( name = "id" )
    protected Long id;

    @JsonBackReference
    @NotNull
    @Valid
    @OneToOne
    @JoinColumn( name = "id_user" )
    protected User user;

    @NotNull
    @NotEmpty
    @Valid
    @JsonManagedReference
    @OneToMany(
        mappedBy = "poet",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    protected List<PoetLocale> data = new ArrayList<>();

    @NotNull
    @NotEmpty
    @Length( max = 5 )
    @Column( name = "locale", length = 5 )
    protected String locale;

    @Column( name = "gender" )
    protected Boolean gender;

    @JsonSerialize( using = JsonDateSerializer.class )
    @Column( name = "birthday", columnDefinition = "TIMESTAMP NULL" )
    protected LocalDate birthday;

    @JsonSerialize( using = JsonDateSerializer.class )
    @Column( name = "deathDate", columnDefinition = "TIMESTAMP NULL" )
    protected LocalDate deathDate;

    @Column(
        name = "creation",
        columnDefinition = " TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    protected Calendar creation;


    /// *** Methods     *** ///
    /**
     * Construct default.
     */
    public Poet() {

        //- Initialization -//
        this.data = new ArrayList<>();
    }

    /**
     * Construct for create new poet.
     *
     * @param birthday
     * @param deathDate
     */
    public Poet(
        String locale,
        Boolean gender,
        LocalDate birthday,
        LocalDate deathDate,
        List<PoetLocale> data
    ) {
        //- Check params -//
        notNull( data );

        //- Initialization -//
        this.locale = locale;
        this.gender = gender;
        this.birthday = birthday;
        this.deathDate = deathDate;
        this.data = data;

        for ( PoetLocale poetLocale: this.data ) {
            poetLocale.setPoet( this );
        }
    }

    /**
     * Constructor for create new poet.
     *
     * @param user
     * @param data
     * @param locale
     */
    public Poet(
        User user,
        List<PoetLocale> data,
        String locale
    ) {
        this();

        this.user = user;

        for( PoetLocale poetLocale: data ) {
            this.addPoetLocale( poetLocale );
        }
        this.locale = locale;
    }

    /**
     * Constructor for creating a new poet fully filled.
     *
     * @param user         User.
     * @param data         Localized data.
     * @param locale       Locale.
     * @param gender       Gender.
     * @param birthday     Birthday.
     * @param deathDate    Date of death.
     */
    public Poet(
        User user,
        List<PoetLocale> data,
        String locale,
        Boolean gender,
        LocalDate birthday,
        LocalDate deathDate
    ) {
        this.user = user;

        data.forEach( (localizedData) -> {
            localizedData.setPoet( this );
            this.data.add( localizedData );
        });
        this.locale = locale;
        this.gender = gender;
        this.birthday = birthday;
        this.deathDate = deathDate;
    }

    /**
     * Constructor for create new poet.
     *
     * @param user
     * @param locale
     */
    public Poet(
        User user,
        String locale
    ) {
        this.user = user;
        this.data = null;
        this.locale = locale;
    }
    //- SECTION :: GET -//
    /**
     * Get ID of poet.
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Get user-poet.
     *
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * Get data for current locale.
     *
     * @return
     */
    public List<PoetLocale> getData() {
        return data;
    }

    public String getLocale() {
        return locale;
    }

    public Boolean getGender() {
        return gender;
    }

    /**
     * Get date of birthday.
     *
     * @return Calendar
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Get date of death.
     *
     * @return Calendar
     */
    public LocalDate getDeathDate() {
        return deathDate;
    }

    /**
     * Get time of create record.
     *
     * @return Calendar
     */
    public Calendar getCreation() {
        return creation;
    }


    //- SECTION :: SET -//
    /**
     * Set ID of poet.
     *
     * @param id
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set user-poet.
     *
     * @param user
     */
    public void setUser( User user ) {
        this.user = user;
    }

    /**
     * Set data of current locale.
     *
     * @param data
     */
    public void setData( List<PoetLocale> data ) {
        this.data = data;
    }

    public void setLocale( String locale ) {
        this.locale = locale;
    }

    public void setGender( Boolean gender ) {
        this.gender = gender;
    }

    /**
     * Set date of birthday.
     *
     * @param birthday
     */
    public void setBirthday( LocalDate birthday ) {
        this.birthday = birthday;
    }

    /**
     * Set date of death.
     *
     * @param deathDate
     */
    public void setDeathDate( LocalDate deathDate ) {
        this.deathDate = deathDate;
    }

    /**
     * Add unique poet locale.
     *
     * @param poetLocale
     */
    public void addPoetLocale( PoetLocale poetLocale ) {
        //- Set composer-//
        poetLocale.setPoet(this);

        //- Add Composer locale-//
        if( !this.data.contains( poetLocale ) ) {
            this.data.add( poetLocale );
        }
    }
}
