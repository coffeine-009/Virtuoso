/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 11/21/15 7:20 PM
 */

package com.coffeine.virtuoso.module.security.view.form;

import com.coffeine.virtuoso.module.model.AbstractModel;
import com.coffeine.virtuoso.module.security.model.entity.Roles;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Test of RegistrationForm.
 * @see RegistrationForm
 *
 * @version 1.0
 */
public class RegistrationFormTest extends AbstractModel {

    /**
     * Test of filling required fields in success expected case.
     */
    @Test
    public void testRequiredFieldsSuccess() {
        //- Assumptions -//
        final String USERNAME = "Unit@test.com";
        final String PASSWORD = "Te$t";
        final String FIRST_NAME = "Unit";
        final String LAST_NAME = "Test";
        final Boolean GENDER = true;
        final List< String > ROLES = new ArrayList< String >() {{
            add( Roles.STUDENT.name() );
        }};
        final String LOCALE = "en-US";
        final LocalDate BIRTHDAY = LocalDate.now();

        //- Set of constraints after validation -//
        Set< ConstraintViolation< RegistrationForm > > constraintViolationSet;

        //- Create entity-//
        RegistrationForm registrationForm = new RegistrationForm(
            USERNAME,
            PASSWORD,
            FIRST_NAME,
            LAST_NAME,
            GENDER,
            ROLES,
            LOCALE,
            BIRTHDAY
        );

        //- Validate -//
        constraintViolationSet = validator.validate( registrationForm );

        //- Assertions -//
        assertEquals( 0, constraintViolationSet.size() );
    }
}
