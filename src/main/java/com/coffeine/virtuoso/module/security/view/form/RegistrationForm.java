/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 11/18/15 10:36 PM
 */

package com.coffeine.virtuoso.module.security.view.form;

import com.coffeine.virtuoso.library.validator.anotation.InEnum;
import com.coffeine.virtuoso.module.security.model.entity.Roles;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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
    @InEnum( enumClass = Roles.class )//TODO: add exists validator
    protected List< String > roles;

    @NotNull
    @NotEmpty
    @Size( max = 5 )
    protected String locale;

    @NotNull
    protected LocalDate birthday;

    protected LocalDate deathDay;


    /// *** Methods     *** ///
    /**
     * Create an empty form.
     */
    public RegistrationForm() {
        //- Initialization -//
    }

    /**
     * Create form with required fields.
     *
     * @param username     Unique unsername of user
     * @param password     Password of user
     * @param firstName    First name of user
     * @param lastName     Last name of user
     * @param gender       True - man, false - woman
     * @param locale       User's locale
     * @param birthday     Birthday of user
     */
    public RegistrationForm(
        String username,
        String password,
        String firstName,
        String lastName,
        Boolean gender,
        List< String > roles,
        String locale,
        LocalDate birthday
    ) {
        //- Initialization -//
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.roles = roles;
        this.locale = locale;
        this.birthday = birthday;
    }


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

    /**
     * Get birthday.
     *
     * @return Date
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Get day of death
     *
     * @return Date
     */
    public LocalDate getDeathDay() {
        return deathDay;
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

    /**
     * Set birthday
     *
     * @param birthday
     */
    public void setBirthday( LocalDate birthday ) {
        this.birthday = birthday;
    }

    /**
     * Set day of death
     *
     * @param deathDay
     */
    public void setDeathDay( LocalDate deathDay ) {
        this.deathDay = deathDay;
    }
}
