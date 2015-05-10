/*
 * @copyright (c) 2015 by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */

package com.coffeine.virtuoso.module.user.view.form;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Form for create/update Composer
 *
 * @version 1.0
 */
public class ComposerForm {

    /// *** Inner classes   *** ///
    public class Data {

        /// *** Properties  *** ///
        /**
         * Locale
         */
        private String locale;

        /**
         * First name of composer
         */
        @NotBlank
        private String firstName;

        /**
         * Last name of composer
         */
        @NotBlank
        private String lastName;

        /**
         * Father's name if exists
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
     * ID of user if composer want to manage own songs
     */
    protected Long userId;

    /**
     * Gender: true - man, false - woman
     */
    @NotNull
    protected Boolean gender;

    /**
     * Code of country(Ex. UA, US, ... .)
     */
    @NotBlank
    @Length( min = 5, max = 5 )
    protected String locale;

    /**
     * Birthday date
     */
    @NotNull
    protected Date birthday;

    /**
     * Death date if composer is dead
     */
    protected Date deathDate;

    /**
     * Localized data
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

    public Date getBirthday() {
        return birthday;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public List < Data > getData() {
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

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public void setData( List < Data > data ) {
        this.data = data;
    }
}
