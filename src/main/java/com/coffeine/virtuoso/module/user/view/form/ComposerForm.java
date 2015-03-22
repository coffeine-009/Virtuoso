/*
 * @copyright (c) 2015 by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */

package com.coffeine.virtuoso.module.user.view.form;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * Form for create/update Composer
 *
 * @version 1.0
 */
public class ComposerForm {

    /// *** Properties  *** ///
    /**
     * ID of user if composer wont to manage own songs
     */
    protected Long userId;

    /**
     * First name of composer
     */
    @NotBlank
    protected String firstName;

    /**
     * Last name of composer
     */
    @NotBlank
    protected String lastName;

    /**
     * Father's name if exists
     */
    protected String fatherName;

    /**
     * Gender: true - man, false - woman
     */
    @NotNull
    protected Boolean gender;

    /**
     * Code of country(Ex. UA, US, ... .)
     */
    @NotBlank
    protected String country;

    /**
     * Code of language(Ex. uk, en, ... .)
     */
    @NotBlank
    protected String language;

    /**
     * Birthday date
     */
    @NotNull
    protected Date birthday;

    /**
     * Death date if composer is dead
     */
    protected Date deathDate;


    /// *** Methods     *** ///
    //- SECTION :: GET -//
    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public Boolean getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }

    public String getLocale() {
        return language + "-" + country;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Date getDeathDate() {
        return deathDate;
    }


    //- SECTION :: SET -//
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }
}
