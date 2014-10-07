/// *** User :: Model :: Entity :: Poet *** *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-04-22 20:40:51 :: 2014-04-22 20:44:12
     *
     * @address /Ukraine/Ivano-Frankivsk/Chornovola/104
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class for reflect table Poet from persistence layout
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

    @NotNull
    @Valid
    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn( name = "id_user" )
    protected User user;

    @JsonIgnore
    @NotNull
    @NotEmpty
    @Valid
    @OneToMany( mappedBy = "poet" )
    protected List < PoetLocale > data;

    @NotNull
    @NotEmpty
    @Length( max = 5 )
    @Column( name = "locale", length = 5)
    protected String locale;

    @Column( name = "gender" )
    protected Boolean gender;

    @Column( name = "birthday", columnDefinition = "TIMESTAMP NULL" )
    protected Calendar birthday;

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
    public Poet() {

    }

    /**
     * Construct for create new poet
     *
     * @param birthday
     * @param deathday
     */
    public Poet(
        String locale,
        Boolean gender,
        Calendar birthday,
        Calendar deathday
    ) {
        //- Initializaation -//
        this.locale = locale;
        this.gender = gender;
        this.birthday = birthday;
        this.deathday = deathday;
    }


    //- SECTION :: GET -//
    /**
     * Get ID of poet
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Get user-poet
     *
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * Get data for current locale
     *
     * @return
     */
    public List < PoetLocale > getData() {
        return data;
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
     * Set ID of poet
     *
     * @param id
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set user-poet
     *
     * @param user
     */
    public void setUser( User user ) {
        this.user = user;
    }

    /**
     * Set data of current locale
     *
     * @param data
     */
    public void setData( List < PoetLocale > data ) {
        this.data = data;
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
}
