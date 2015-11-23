/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 11/21/15 7:20 PM
 */

package com.coffeine.virtuoso.module.security.view.form;

import com.coffeine.virtuoso.module.model.AbstractModel;
import org.hibernate.validator.constraints.Email;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test of RegistrationForm.
 * @see RegistrationForm
 *
 * @version 1.0
 */
@RunWith( Parameterized.class )
public class RegistrationFormTest extends AbstractModel {

    /// *** Properties  *** ///
    //- Assumptions -//
    /**
     * Username(e-mail) for register
     */
    private final String username;

    /**
     * Password
     */
    private final String password;

    /**
     * First name
     */
    private final String firstName;

    /**
     * Last name
     */
    private final String lastName;

    /**
     * Gender of user.
     * true - man, false - woman
     */
    private final Boolean gender;

    /**
     * Locale of user.
     * e.g. en-US
     */
    private final String locale;

    /**
     * Birthday
     */
    private final LocalDate birthday;

    /**
     * Day of death if it is known
     */
    private final LocalDate deathDay;

    /**
     * List of roles for assign to user
     */
    private final List<String> roles;

    //- Expected results -//
    /**
     * Count of constraints
     */
    private final int countConstraints;

    /**
     * List of properties that contains constraints
     */
    private final List<String> propertyNames;

    /**
     * List of constraint annotations that were involved
     */
    private final List<Class> annotationTypes;

    /**
     * List of messages that were generated
     */
    private final List<String> messages;


    /// *** Methods     *** ///
    /**
     * Constructor for create parametrized test.
     *
     * @param username           Username - e-mail
     * @param password           Password of user
     * @param firstName          First name
     * @param lastName           Last name
     * @param gender             Gender
     * @param locale             Locale(e.g. en-US)
     * @param roles              List of roles
     * @param birthday           Birthday
     * @param deathDay           Day of death if it is known
     * @param countConstraints   Count of constraints for this case
     * @param propertyNames      List of names of properties with constraint error
     * @param annotationTypes    Annotation type that generate this constraint
     * @param messages           List of messages that can be generated
     */
    public RegistrationFormTest(
        String username,
        String password,
        String firstName,
        String lastName,
        Boolean gender,
        String locale,
        LocalDate birthday,
        LocalDate deathDay,
        List< String > roles,
        int countConstraints,
        List< String > propertyNames,
        List< Class > annotationTypes,
        List< String > messages
    ) {
        //- Initialization -//
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.locale = locale;
        this.birthday = birthday;
        this.deathDay = deathDay;
        this.roles = roles;
        this.countConstraints = countConstraints;
        this.propertyNames = propertyNames;
        this.annotationTypes = annotationTypes;
        this.messages = messages;
    }


    /**
     * Parameters for each case.
     *
     * @return Collection of parameters
     */
    @Parameterized.Parameters
    public static Collection inputCases() {
        return Arrays.asList(
            new Object[][] {
                //- Success. Correct input -//
                {
                    "unit@test.com",
                    "te$t",
                    "Unit",
                    "Test",
                    false,
                    "en-US",
                    LocalDate.now(),
                    LocalDate.now().plusYears( 90 ),
                    new ArrayList< String >() {{
                        add( "STUDENT" );
                    }},
                    0,
                    new ArrayList< String >() {{
                        add( "username" );
                    }},
                    new ArrayList< Class >() {{
                        add( NotNull.class );
                    }},
                    new ArrayList< String >() {{
                        add( "may not be null" );
                    }}
                },
                //- Failure. incorrect input, bad email -//
                {
                    "unit#test.com",
                    "te$t",
                    "Unit",
                    "Test",
                    false,
                    "en-US",
                    LocalDate.now(),
                    LocalDate.now().plusYears( 90 ),
                    new ArrayList< String >() {{
                        add( "STUDENT" );
                    }},
                    1,
                    new ArrayList< String >() {{
                        add( "username" );
                    }},
                    new ArrayList< Class >() {{
                        add( Email.class );
                    }},
                    new ArrayList< String >() {{
                        add( "not a well-formed email address" );
                    }}
                }
            }
        );
    }


    /**
     * Test of input(filled form).
     */
    @Test
    public void testRequiredFieldsFailure() {
        //- Set of constraints after validation -//
        Set< ConstraintViolation< RegistrationForm > > constraintViolationSet;

        //- Evaluation -//
        RegistrationForm form = new RegistrationForm(
            this.username,
            this.password,
            this.firstName,
            this.lastName,
            this.gender,
            this.roles,
            this.locale,
            this.birthday,
            this.deathDay
        );

        //- Validate -//
        constraintViolationSet = validator.validate( form );

        //- Assertions -//
        assertEquals( this.countConstraints, constraintViolationSet.size() );
        for ( ConstraintViolation< RegistrationForm > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                this.propertyNames.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                this.annotationTypes.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue( this.messages.contains( constraintViolation.getMessage() ) );
        }
    }
}
