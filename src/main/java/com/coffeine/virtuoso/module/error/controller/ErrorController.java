/// *** Error :: Controller :: Error    *** *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-07-25 15:21:04 :: 2014-07-25 17:22:29
     *
     * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.error.controller;

import com.coffeine.virtuoso.module.error.model.entity.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Controller for handle validation errors from others controllers
 *
 * @version 1.0
 */
@ControllerAdvice
public class ErrorController {

    /// *** Properties  *** ///
    @Autowired
    protected MessageSource messageSource;


    /// *** Methods     *** ///
    /**
     * Default constructor
     */
    public ErrorController() {

    }


    /**
     * Handle validation errors
     *
     * @param exeption
     * @return ValidationError
     */
    @ExceptionHandler( MethodArgumentNotValidException.class )
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    @ResponseBody
    public ValidationError processValidationError(
        MethodArgumentNotValidException exeption
    ) {
        return this.processFieldErrors(
            exeption.getBindingResult().getFieldErrors()
        );
    }


    //- SECTION :: HELPER -//
    /**
     * Helper for filling field's errors
     *
     * @param fieldErrors
     * @return ValidationError
     */
    private ValidationError processFieldErrors(
        List < FieldError > fieldErrors
    ) {
        //- Result -//
        ValidationError validationError = new ValidationError();

        for ( FieldError fieldError: fieldErrors ) {
            //- Add field's errors -//
            validationError.addFieldError(
                fieldError.getField(),
                this.resolveLocalizedErrorMessage( fieldError )
            );
        }

        return validationError;
    }

    /**
     * Helper for localization of error message
     *
     * @param fieldError
     * @return String
     */
    private String resolveLocalizedErrorMessage( FieldError fieldError ) {
        return messageSource.getMessage(
            fieldError,
            LocaleContextHolder.getLocale()
        );
    }
}
