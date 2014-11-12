/*
 * @copyright (c) 2014, by Valentyn Namisnyk
 *
 * @author Valentyn Namisnyk <Valentun_Prodyser@ukr.net>
 */

package com.coffeine.virtuoso.module.user.model.entity;

import com.coffeine.virtuoso.module.model.AbstractModel;
import junit.framework.Assert;
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
 * Tests for Composer
 * @see com.coffeine.virtuoso.module.user.model.entity.Composer
 *
 * @version 1.0
 */
public class ComposerTest extends AbstractModel {

    /*
    * Test field validation for entity correct
    */
    @Test
    public void testComposerFieldSuccess() {

        Set < ConstraintViolation < Composer > > constraintViolationSet;

        //- Success -//
        //- Create entity-//
        Composer composerSuccess = new Composer(
            new ArrayList < ComposerLocale >() {{
                add(
                    new ComposerLocale(
                        "Test",
                        "Unit",
                        "Validation",
                        "en-US"
                    )
                );
            }},
            "uk-UA"
        );
        //- Validate -//
        constraintViolationSet = validator.validate(composerSuccess);

        assertEquals(0, constraintViolationSet.size());
    }

    /*
    * Test field validation for entity failure
    */
    @Test
    public void testComposerFieldFailure() {

        Set < ConstraintViolation < Composer > > constraintViolationSet;

        //-Failure-//
        //-Create entity-//
        Composer composerFailure = new Composer(
            null
        );

        //- Validate -//
        constraintViolationSet = validator.validate( composerFailure );

        assertEquals( 4, constraintViolationSet.size() );
        for ( ConstraintViolation < Composer > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add("data");
                    add("locale");
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList < Class >() {{
                    add( NotNull.class );
                    add( NotEmpty.class );
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "may not be null" );
                    add( "may not be empty" );
                }}.contains( constraintViolation.getMessage() )
            );
        }

        //- Failure: Incorrect length -//
        //- Create entity -//
        Composer composerFailureLength = new Composer(
            new ArrayList < ComposerLocale >() {{
                add(
                    new ComposerLocale(
                        "Test",
                        "Unit",
                        "Validation",
                        "en-US"
                    )
                );
            }},
            "123456"
        );

        //- Validate -//
        constraintViolationSet = validator.validate( composerFailureLength );

        assertEquals( 1, constraintViolationSet.size() );

        for ( ConstraintViolation < Composer > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "locale" );
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
                    add( "length must be between 0 and 5" );
                }}.contains( constraintViolation.getMessage() )
            );
        }
    }

    /*
    * Test field validation for entity failure( empty )
    */
    @Test
    public void testComposerFieldEmpty(){

        Set < ConstraintViolation < Composer > > constraintViolationSet;

        //- Failure: fields is empty-//
        //- Create entity -//
        Composer composerFailureEmpty = new Composer(
            new ArrayList < ComposerLocale >() {{
                add(
                    new ComposerLocale(
                        "Test",
                        "Unit",
                        "Validation",
                        "en-US"
                    )
                );
            }},
            ""
        );

        //- Validate -//
        constraintViolationSet = validator.validate( composerFailureEmpty );

        assertEquals( 1, constraintViolationSet.size() );

        for ( ConstraintViolation < Composer > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "locale" );
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
