/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/27/16 11:02 PM
 */

package com.coffeine.virtuoso.music.view.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * Abstract class for authors.
 *
 * @version 1.0
 */
public abstract class AbstractAuthor {

    /// *** Inner classes   *** ///
    public static class Data {

        /// *** Properties  *** ///
        /**
         * Locale.
         */
        private String locale;

        /**
         * First name of composer.
         */
        @NotBlank
        private String firstName;

        /**
         * Last name of composer.
         */
        @NotBlank
        private String lastName;

        /**
         * Father's name if exists.
         */
        private String fatherName;


        /// *** Methods     *** ///
        //- SECTION :: GET -//
        public String getLocale() {
            return locale;
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


        //- SECTION :: SET -//
        public void setLocale( String locale ) {
            this.locale = locale;
        }

        public void setFirstName( String firstName ) {
            this.firstName = firstName;
        }

        public void setLastName( String lastName ) {
            this.lastName = lastName;
        }

        public void setFatherName( String fatherName ) {
            this.fatherName = fatherName;
        }
    }

    /// *** Properties  *** ///
    /**
     * ID of user if composer want to manage own songs.
     */
    protected Long userId;

    /**
     * Gender: true - man, false - woman.
     */
    @NotNull
    protected Boolean gender;

    /**
     * Code of country(Ex. UA, US, ... .).
     */
    @NotBlank
    @Length( min = 5, max = 5 )
    protected String locale;

    /**
     * Birthday date.
     */
    @NotNull
    protected LocalDate birthday;

    /**
     * Death date if composer is dead.
     */
    protected LocalDate deathDate;

    /**
     * Localized data.
     */
    protected List<Data> data = new ArrayList<>();


    /// *** Methods     *** ///
    //- SECTION :: GET -//
    public Long getUserId() {
        return userId;
    }

    public Boolean getGender() {
        return gender;
    }

    public String getLocale() {
        return this.locale;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public List<Data> getData() {
        return data;
    }

    //- SECTION :: SET -//
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public void setLocale( String locale ) {
        this.locale = locale;
    }

    public void setBirthday( LocalDate birthday ) {
        this.birthday = birthday;
    }

    public void setDeathDate( LocalDate deathDate ) {
        this.deathDate = deathDate;
    }

    public void setData( List<Data> data ) {
        this.data = data;
    }
}
