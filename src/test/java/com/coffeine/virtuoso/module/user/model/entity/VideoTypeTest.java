
package com.coffeine.virtuoso.module.user.model.entity;

import com.coffeine.virtuoso.module.model.AbstractModel;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.Assert.assertEquals;

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

        assertEquals(2, constraintViolationSet.size());
    }
}
