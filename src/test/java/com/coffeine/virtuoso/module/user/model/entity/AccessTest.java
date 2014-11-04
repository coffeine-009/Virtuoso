/*
 * @copyright (c) 2014, by Valentyn Namisnyk
 *
 * @author Valentyn Namisnyk <Valentun_Prodyser@ukr.net>
 */

package com.coffeine.virtuoso.module.user.model.entity;


import com.coffeine.virtuoso.module.model.AbstractModel;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Set;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Tests for Access
 * @see com.coffeine.virtuoso.module.user.model.entity.Access
 *
 * @version 1.0
 */
public class AccessTest extends AbstractModel {
    /*
    * Test field validation for entity correct
    */
    @Test
    public void testAccessFieldSuccess() {

        Set < ConstraintViolation < Access > > constraintViolationSet;

        //- Success -//
        //- Create entity-//
        Access accessSuccess = new Access(
            new User(
                //- Roles -//
                new ArrayList<Role>() {{
                    add(new Role("POET", "Poet"));
                }},
                //- Access -//
                new Access("MyP@$$w0rd"),
                //- Emails -//
                new Email("myemail@virtuoso.com"),
                "Tester",
                "Unit",
                "JUnit",
                "uk-UA"
            ),
            "MyP@$$w0rd"
        );
        //- Validate -//
        constraintViolationSet = validator.validate(accessSuccess);

        assertEquals(0, constraintViolationSet.size());
    }
    /*
    * Test field validation for entity failure
    */
    @Test
    public void testAccessFieldFailure() {

        Set < ConstraintViolation < Access > > constraintViolationSet;

        //- Failure: Incorrect user -//
        //- Create entity-//
        Access accessFailureUser = new Access(
            null,
            "London"
        );
        //- Validate emails user -//
        constraintViolationSet = validator.validate( accessFailureUser );

        assertEquals( 1, constraintViolationSet.size() );
        for ( ConstraintViolation < Access > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList<String>() {{
                    add("user");
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList<Class>() {{
                    add(NotNull.class);
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList<String>() {{
                    add("may not be null");
                }}.contains(constraintViolation.getMessage())
            );
        }
        //- Failure: Incorrect password -//
        //- Create entity-//
        Access emailFailurePassword = new Access(
            new User(
                //- Roles -//
                new ArrayList< Role >() {{
                    add( new Role( "POET", "Poet" ) );
                }},
                //- Access -//
                new Access( "MyP@$$w0rd" ),
                //- Emails -//
                new Email( "myemail@virtuoso.com" ),
                "Tester",
                "Unit",
                "JUnit",
                "uk-UA"
            ),
            null
        );
        //- Validate emails address -//
        constraintViolationSet = validator.validate( emailFailurePassword );

        assertEquals( 2, constraintViolationSet.size() );
        for( ConstraintViolation < Access > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList<String>() {{
                    add("password");
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList<Class>() {{
                    add(NotNull.class);
                    add(NotEmpty.class);
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList<String>() {{
                    add("may not be null");
                    add("may not be empty");
                }}.contains(constraintViolation.getMessage())
            );
        }
        //- Failure: Incorrect length -//
        //- Create entity -//
        Access accessFailureLength = new Access(
            new User(
                //- Roles -//
                new ArrayList< Role >() {{
                    add( new Role( "POET", "Poet" ) );
                }},
                //- Access -//
                new Access( "MyP@$$w0rd" ),
                //- Emails -//
                new Email( "myemail@virtuoso.com" ),
                "Tester",
                "Unit",
                "JUnit",
                "uk-UA"
            ),
            "123456789012345678901234567890123456789012345678901234567890" +
            "123456789012345678901234567890123456789012345678901234567890" +
            "123456789012345678901234567890123456789012345678901234567890" +
            "123456789012345678901234567890123456789012345678901234567890" +
            "12345678901234567"
        );

        //- Validate -//
        constraintViolationSet = validator.validate( accessFailureLength );

        assertEquals( 1, constraintViolationSet.size() );

        for ( ConstraintViolation < Access > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "password" );
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList < Class >() {{
                    add( Length.class );
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "length must be between 0 and 256" );
                }}.contains( constraintViolation.getMessage() )
            );
        }
    }
}
