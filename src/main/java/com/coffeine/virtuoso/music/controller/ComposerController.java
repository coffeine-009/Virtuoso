/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.music.model.entity.Composer;
import com.coffeine.virtuoso.music.model.entity.ComposerLocale;
import com.coffeine.virtuoso.music.model.service.ComposerService;
import com.coffeine.virtuoso.music.view.form.ComposerForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import static org.springframework.util.Assert.notNull;

/**
 * Controller for work with Composer.
 *
 * @version 1.0
 */
@RestController
@RequestMapping( value = "/music/composers" )
public class ComposerController {

    /// *** Properties  *** ///
    @Autowired
    private ComposerService composerService;


    /// *** Methods     *** ///
    /**
     * Get list of composers.
     *
     * @param page  Number of page
     * @param limit Count items per page
     *
     * @return List of Composer.
     */
    @GET
    @RequestMapping( method = RequestMethod.GET )
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
     * Action for create a new composer.
     *
     * @param form      Data about composer
     * @param response  Use for set HTTP status
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
        //- Try to create new composer -//
        try {
            //- Set HTTP status -//
            response.setStatus( HttpServletResponse.SC_CREATED );

            //- Prepare data for localization -//
            List<ComposerLocale> data = new ArrayList<>();
            form.getData().forEach( (dataLocalized) ->
                data.add(
                    new ComposerLocale(
                        dataLocalized.getFirstName(),
                        dataLocalized.getLastName(),
                        dataLocalized.getFatherName(),
                        dataLocalized.getLocale()
                    )
                )
            );

            //- Success. Composer has created -//
            return this.composerService.create(
                new Composer(
                    form.getLocale(),
                    form.getGender(),
                    form.getBirthday(),
                    form.getDeathDate(),
                    data
                )
            );
        } catch ( DataIntegrityViolationException exception ) {
            //- Warning, can not create duplicate -//
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
        } catch ( Exception exception ) {
            //- Failure. Can not to create a composer -//
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
        }

        return null;
    }

    /**
     * Action for searching a composer.
     *
     * @param id        Id of composer
     * @param response  Use for set HTTP status
     *
     * @return Composer
     */
    @GET
    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    public Composer readAction(
        @PathVariable( "id" )
        Long id,

        HttpServletResponse response
    ) {
        //- Try to find composer -//
        try {
            //- Success. Composer has found -//
            Composer composer = this.composerService.find( id );

            //- Check composer -//
            notNull( composer );
        }
        catch ( IllegalArgumentException e ) {
            //- Composer did not find -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }
        catch ( Exception e ) {
            //- Failure. Unknown error -//
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
        }

        return null;
    }

    /**
     * Action for update a new composer.
     *
     * @param form      Data about composer
     * @param response  Use for set HTTP status
     *
     * @return Composer
     */
    @PUT
    @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
    public Composer updateAction(
        @PathVariable( "id" )
        Long id,

        @RequestBody
        @Valid
        ComposerForm form,

        HttpServletResponse response
    ) {
        //- Try to update new composer -//
        try {
            //- Search  -//
            Composer composer = this.composerService.find( id );

            //- Check composer -//
            notNull( composer );

            //- Update data -//
            composer.setLocale( form.getLocale() );
            composer.setGender( form.getGender() );
            composer.setBirthday( form.getBirthday() );
            composer.setDeathDate( form.getDeathDate() );
            //TODO: finish

            //- Success. Composer has updated -//
            return this.composerService.update( composer );
        }
        catch ( IllegalArgumentException e ) {
            //- Warning. Composer has not found -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }
        catch ( DataIntegrityViolationException e ) {
            //- Warning, can not create duplicate -//
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
        }
        catch ( Exception e ) {
            //- Failure. Can not to create a composer -//
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
        }

        return null;
    }

    /**
     * Action for delete a composer.
     *
     * @param id        Id Id of composer
     * @param response  Use for set HTTP status
     */
    @DELETE
    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
    public void deleteAction(
        @PathVariable( "id" )
        Long id,

        HttpServletResponse response
    ) {
        //- Try to delete composer -//
        try {
            //- Success. Composer has deleted -//
            this.composerService.delete( id );
        }
        catch ( EmptyResultDataAccessException e ) {
            //- Warning. Composer has not found -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }
        catch ( Exception e ) {
            //- Failure. Unknown error -//
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
        }
    }
}
