/**
 * @copyright (c) 2015 by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */

package com.coffeine.virtuoso.module.user.controller;

import com.coffeine.virtuoso.module.user.model.entity.Composer;
import com.coffeine.virtuoso.module.user.model.service.ComposerService;
import com.coffeine.virtuoso.module.user.view.form.ComposerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.List;

/**
 * Controller for work with Composer
 *
 * @version 1.0
 */
@RestController
@RequestMapping( value = "/composer" )
public class ComposerController {

    /// *** Properties  *** ///
    @Autowired
    private ComposerService composerService;

    /// *** Methods     *** ///
    /**
     * Get list of composers
     *
     * @param page  Number of page
     * @param limit Count items per page
     *
     * @return List<Composer>
     */
    @GET
    @RequestMapping( value = "s", method = RequestMethod.GET )
    public List<Composer> listAction(
        @RequestParam( value = "page", required = false, defaultValue = "1" )
        int page,

        @RequestParam( value = "limit", required = false, defaultValue = "10" )
        int limit
    ) {
        return this.composerService.findAll(
            Math.max( page - 1, 0 ),
            limit
        );
    }

    /**
     * Action for create a new composer
     *
     * @param form  Data about composer
     * @param response      Use for set HTTP status
     *
     * @return Composer
     */
    @POST
    @RequestMapping( method = RequestMethod.POST )
    public Composer createAction(
        @RequestBody
        @Valid
        ComposerForm form,

        HttpServletResponse response
    ) {
        try {
            return this.composerService.create(
                new Composer(
                    form.getFirstName(),
                    form.getLastName(),
                    form.getFatherName(),
                    form.getLocale(),
                    form.getGender(),
                    form.getBirthday(),
                    form.getDeathDate()
                )
            );
        }
        catch ( DataIntegrityViolationException e ) {
            //- Warning, can not create duplicate -//
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );

            return null;
        }
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    public Composer readAction(
        @PathVariable( "id" )
        Long id,

        HttpServletRequest request,
        HttpServletResponse response
    ) {
        try {
            return this.composerService.find(
                id,
                request.getLocale().getLanguage() + "-" +
                    request.getLocale().getCountry()
            );
        }
        catch ( Exception e ) {
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );

            return null;
        }
    }
    //TODO
}
