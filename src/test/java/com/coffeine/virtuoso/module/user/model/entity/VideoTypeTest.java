/**
 * @copyright (c) 2014, by Vitaliy Tsutsman
 *
 * @author Vitaliy Tsutsman <vtsutsman@softjourn.com>
 */

package com.coffeine.virtuoso.module.user.model.entity;

import com.coffeine.virtuoso.module.model.AbstractModel;
import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for VideoType
 * @see com.coffeine.virtuoso.module.user.model.entity.VideoType
 *
 * @version 1.0
 */
public class VideoTypeTest extends AbstractModel {

    /**
     * Test field validation for entity
     */
    @Test
    public void testVideoTypeFields() {

        Set < ConstraintViolation < VideoType > > constraintViolationSet;

        //- Success -//
        //- Create entity -//
        VideoType videoTypeSuccess = new VideoType(
            "POLKA",
            "Polka",
            "Ukrainian polka"
        );

        //- Validate -//
        constraintViolationSet = validator.validate( videoTypeSuccess );

        assertEquals(0, constraintViolationSet.size());


        //- Failure -//
        //- Create entity -//
        VideoType videoTypeFailure = new VideoType(
            null,
            "Polka",
            "Ukrainian polka"
        );

        //- Validate -//
        constraintViolationSet = validator.validate( videoTypeFailure );

        assertEquals( 2, constraintViolationSet.size() );
        for ( ConstraintViolation<VideoType> constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertEquals(
                "code",
                this.getPropertyName(
                    constraintViolation.getPropertyPath()
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
                new ArrayList < String >() {{
                    add( "may not be null" );
                    add( "may not be empty" );
                }}.contains( constraintViolation.getMessage() )
            );
        }
    }
}
