package com.coffeine.virtuoso.module.user.Form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by vitaliy on 7/16/14. 22:48:22
 */
public class RegistrationForm {

    /// *** Properties  *** ///
    @NotNull
    @NotEmpty
    @Size( max = 16 )
    protected String firstName;

    @Size( max = 16 )
    protected String lastName;

    @Size( max = 32 )
    protected String middleName;

    protected Boolean gender;

    @NotNull
    @NotEmpty
    @Size( max = 5 )
    protected String locale;


    /// *** Methods     *** ///
    //- SECTION :: GET -//
    public String getFirstName() {
        return this.firstName;
    }

    //- SECTION :: SET -//
    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }
}
