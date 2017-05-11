/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/29/16 10:39 PM
 */

package com.thecoffeine.virtuoso.music.model.entity;

import com.thecoffeine.virtuoso.module.model.AbstractModel;
import com.thecoffeine.virtuoso.music.model.persistence.mock.CollectionMock;
import org.junit.Test;

import java.util.Set;
import javax.validation.ConstraintViolation;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Collection entity.
 *
 * @see Collection
 */
public class CollectionTest extends AbstractModel {

    /**
     * Test field validation for entity field correct.
     */
    @Test
    public void testSongFieldsSuccess() {

        Set<ConstraintViolation<Collection>> constraintViolationSet;

        //- Successful-//
        //- Create entity-//
        Collection songSuccess = CollectionMock.find();
        //- Validate -//
        constraintViolationSet = validator.validate( songSuccess );

        assertEquals( 0, constraintViolationSet.size() );
    }

    //TODO: test constrains
}
