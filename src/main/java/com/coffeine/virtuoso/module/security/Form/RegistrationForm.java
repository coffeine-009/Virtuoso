/// *** Security :: Form :: RegistrationForm    *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-07-25 15:26:32 :: 2014-07-28 16:20:03
     *
     * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.security.Form;

import com.coffeine.virtuoso.library.validator.anotation.InEnum;
import com.coffeine.virtuoso.module.security.model.entity.Roles;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.List;

/**
 * Form for registration of a new users
 *
 * @version 1.0
 */
public class RegistrationForm {

    /// *** Properties  *** ///
    @NotNull
    @NotEmpty
    @Email
    protected String username;

    @NotEmpty
    protected String password;

    @NotNull
    @NotEmpty
    @Size( max = 16, message = "{Size.registrationForm.firstName}" )
    protected String firstName;

    @NotNull
    @NotEmpty
    @Size( max = 16 )
    protected String lastName;

    @NotNull
    protected Boolean gender;

    @NotNull
    @NotEmpty
    @InEnum( enumClass = Roles.class )
    protected List< String > roles;

    @NotNull
    @NotEmpty
    @Size( max = 5 )
    protected String locale;

    protected Calendar birthday;

    protected Calendar deathday;


    /// *** Methods     *** ///
    //- SECTION :: GET -//
    /**
     * Get username
     *
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get password
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get first name
     *
     * @return String
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Get last name
     *
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get gender
     *
     * @return boolean
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * Get roles
     *
     * @return List<String>
     */
    public List < String > getRoles() {
        return roles;
    }

    /**
     * Get locale
     * @return String
     */
    public String getLocale() {
        return locale;
    }


    //- SECTION :: SET -//
    /**
     * Set username
     *
     * @param username
     */
    public void setUsername( String username ) {
        this.username = username;
    }

    /**
     * Set password
     *
     * @param password
     */
    public void setPassword( String password ) {
        this.password = password;
    }

    /**
     * Set first name
     *
     * @param firstName
     */
    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    /**
     * Set last name
     *
     * @param lastName
     */
    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    /**
     * Set getnder
     *
     * @param gender
     */
    public void setGender( Boolean gender ) {
        this.gender = gender;
    }

    /**
     * Set roles
     *
     * @param roles
     */
    public void setRole( List < String > roles ) {
        this.roles = roles;
    }

    /**
     * Set locale
     *
     * @param locale
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }
}
