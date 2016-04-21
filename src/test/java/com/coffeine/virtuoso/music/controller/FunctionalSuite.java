/**
 * Copyright (c) 2016 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm&#64;gmail.com>
 *
 * @date 4/21/16 1:59 PM
 */

package com.coffeine.virtuoso.music.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Suite case for functional tests.
 */
@RunWith( Suite.class )
@Suite.SuiteClasses({
    FunctionalSongControllerTest.class, 
    FunctionalStaffControllerTest.class, 
    FunctionalStaffTypeControllerTest.class, 
    FunctionalStyleControllerTest.class
})
public class FunctionalSuite {

}
