/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 11/30/15 11:14 PM
 */

package com.coffeine.virtuoso.security.view.form;

import com.coffeine.virtuoso.library.validator.anotation.Event;
import com.coffeine.virtuoso.library.validator.anotation.InEnum;
import com.coffeine.virtuoso.security.model.entity.Roles;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Form for registration of a new users.
 *
 * @version 1.0
 */
@Event( start = "birthday", end = "deathDate" )
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
    protected List<String> roles;

    @NotNull
    @NotEmpty
    @Size( max = 5 )
    protected String locale;

    @NotNull
    @JsonDeserialize( using = LocalDateDeserializer.class )
    protected LocalDate birthday;//FIXME: Add validation for date format

    @JsonDeserialize( using = LocalDateDeserializer.class )
    protected LocalDate deathDate;


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
     * @param username     Unique username of user
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
        List<String> roles,
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

    /**
     * Create form with all fields.
     *
     * @param username     Unique unsername of user
     * @param password     Password of user
     * @param firstName    First name of user
     * @param lastName     Last name of user
     * @param gender       True - man, false - woman
     * @param locale       User's locale
     * @param birthday     Birthday of user
     * @param deathDate     Birthday of death if it is known
     */
    public RegistrationForm(
        String username,
        String password,
        String firstName,
        String lastName,
        Boolean gender,
        List<String> roles,
        String locale,
        LocalDate birthday,
        LocalDate deathDate
    ) {
        //- Initialization -//
        this(
            username,
            password,
            firstName,
            lastName,
            gender,
            roles,
            locale,
            birthday
        );
        this.deathDate = deathDate;
    }

    //- SECTION :: GET -//
    /**
     * Get username.
     *
     * @return String   Username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get password.
     *
     * @return String Password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get first name.
     *
     * @return String First name.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Get last name.
     *
     * @return String Last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get gender.
     *
     * @return boolean true - man, false - woman.
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * Get roles.
     *
     * @return List of roles.
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * Get locale.
     *
     * @return String Locale code.
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Get birthday.
     *
     * @return Date Birthday.
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Get day of death.
     *
     * @return Date Day of death.
     */
    public LocalDate getDeathDate() {
        return deathDate;
    }


    //- SECTION :: SET -//
    /**
     * Set username.
     *
     * @param username    Username.
     */
    public void setUsername( String username ) {
        this.username = username;
    }

    /**
     * Set password.
     *
     * @param password    Password.
     */
    public void setPassword( String password ) {
        this.password = password;
    }

    /**
     * Set first name.
     *
     * @param firstName    First name.
     */
    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    /**
     * Set last name.
     *
     * @param lastName  Last name.
     */
    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    /**
     * Set gender.
     *
     * @param gender true - man, false - woman.
     */
    public void setGender( Boolean gender ) {
        this.gender = gender;
    }

    /**
     * Set roles.
     *
     * @param roles    List of roles.
     */
    public void setRole( List<String> roles ) {
        this.roles = roles;
    }

    /**
     * Set locale.
     *
     * @param locale    Locale code.
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }

    /**
     * Set birthday.
     *
     * @param birthday    Date of birthday.
     */
    public void setBirthday( LocalDate birthday ) {
        this.birthday = birthday;
    }

    /**
     * Set day of death.
     *
     * @param deathDate    Day of death.
     */
    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }
}
