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
 * Tests for Email
 * @see com.coffeine.virtuoso.module.user.model.entity.Email
 *
 * @version 1.0
 */
public class EmailTest extends AbstractModel {
        /*
     * Test field validation for entity correct
     */
    @Test
    public void testEmailFieldSuccess() {

        Set < ConstraintViolation < Email > > constraintViolationSet;

        //- Success -//
        //- Create entity-//
        Email emailSuccess = new Email(
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
            "London"
        );
        //- Validate -//
        constraintViolationSet = validator.validate( emailSuccess );

        assertEquals( 0, constraintViolationSet.size() );
    }

    /*
    * Test field validation for entity failure
    */
    @Test
    public void testEmailFieldFailure() {

        Set < ConstraintViolation < Email > > constraintViolationSet;

        //- Failure: Incorrect user -//
        //- Create entity-//
        Email emailFailureUser = new Email(
            null,
            "London"
        );
        //- Validate emails user -//
        constraintViolationSet = validator.validate( emailFailureUser );

        assertEquals( 1, constraintViolationSet.size() );
        for ( ConstraintViolation < Email > constraintViolation : constraintViolationSet ) {
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
        //- Failure: Incorrect address -//
        //- Create entity-//
        Email emailFailureAddress = new Email(
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
        constraintViolationSet = validator.validate( emailFailureAddress );

        assertEquals( 2, constraintViolationSet.size() );
        for( ConstraintViolation < Email > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList<String>() {{
                    add("address");
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
        Email emailFailureLength = new Email(
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
            "1234567890123456789012345678901234567890" +
            "12345678901234567890123456789012345678901"
        );

        //- Validate -//
        constraintViolationSet = validator.validate( emailFailureLength );

        assertEquals( 1, constraintViolationSet.size() );

        for ( ConstraintViolation < Email > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "address" );
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
                    add( "length must be between 0 and 80" );
                }}.contains( constraintViolation.getMessage() )
            );
        }
    }

    /*
    * Test field validation for entity failure( empty )
    */
    @Test
    public void testEmailFieldEmpty() {

    Set < ConstraintViolation < Email > > constraintViolationSet;
        //- Failure: fields is empty-//
        //- Create entity -//
        Email emailFailureEmpty = new Email(
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
            ""
        );

        //- Validate -//
        constraintViolationSet = validator.validate( emailFailureEmpty );

        assertEquals( 1, constraintViolationSet.size() );

        for ( ConstraintViolation < Email > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "address" );
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList < Class >() {{
                    add( NotEmpty.class );
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "may not be empty" );
                }}.contains( constraintViolation.getMessage() )
            );
        }

    }
}
