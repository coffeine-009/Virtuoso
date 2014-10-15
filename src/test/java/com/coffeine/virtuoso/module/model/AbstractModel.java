package com.coffeine.virtuoso.module.model;

import org.junit.BeforeClass;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Abstract base class for testing of validation
 *
 * @version 1.0
 */
public abstract class AbstractModel {

    protected static Validator validator;


    @BeforeClass
    public static void setUp() {
        // Set validator
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
