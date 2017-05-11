/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/29/16 11:27 PM
 */

package com.thecoffeine.virtuoso.music.controller;

import com.thecoffeine.virtuoso.music.model.entity.Collection;
import com.thecoffeine.virtuoso.music.model.service.CollectionService;
import com.thecoffeine.virtuoso.music.model.service.SongService;
import com.thecoffeine.virtuoso.music.view.form.CollectionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.springframework.util.Assert.notNull;

/**
 * Controller for working with Collection.
 *
 * @version 1.0
 */
@RestController
@RequestMapping( value = "/music/collections" )
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private SongService songService;


    /**
     * Get list of collections per page.
     *
     * @param page     Page number.
     * @param limit    Records per page.
     *
     * @return List of collections.
     */
    @RequestMapping( method = RequestMethod.GET )
    public Set<Collection> listAction(
        @RequestParam( value = "page", required = false, defaultValue = "1" )
        int page,

        @RequestParam( value = "limit", required = false, defaultValue = "10" )
        int limit
    ) {
        return this.collectionService.findAll(
            Math.max( page - 1, 0 ),
            limit
        );
    }

    /**
     * Create a new Collection.
     *
     * @param form        Form with input.
     * @param response    HTTP response.
     *
     * @return Created collection.
     */
    @RequestMapping( method = RequestMethod.POST )
    public Collection create(
        @RequestBody
        @Valid
            CollectionForm form,

        HttpServletResponse response
    ) {
        //- Try to create new collection -//
        try {
            //- Set HTTP status -//
            response.setStatus( HttpServletResponse.SC_CREATED );

            //- Success. Collection has created -//
            return this.collectionService.create(
                new Collection(
                    this.songService.findByIds( form.getSongsIds() ),
                    form.getTitle(),
                    form.getLocale()
                )
            );
        } catch ( DataIntegrityViolationException exception ) {
            //- Warning, can not create duplicate -//
            response.setStatus( HttpServletResponse.SC_CONFLICT );
        } catch ( Exception exception ) {
            //- Failure. Can not to create a composer -//
            response.setStatus( HttpServletResponse.SC_CONFLICT );
        }

        return null;
    }

    /**
     * Get collection by id.
     *
     * @param id          Id of collection.
     * @param response    HTTP response.
     *
     * @return Collection.
     */
    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    @ResponseBody
    public Collection read(
        @PathVariable( "id" )
        Long id,

        HttpServletResponse response
    ) {
        try {
            //- Search collection -//
            final Collection collection = this.collectionService.find( id );

            //- Check collection -//
            notNull( collection );

            return collection;
        } catch ( IllegalArgumentException e ) {
            //- Collection is not found -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }

        return null;
    }

    /**
     * Update collection.
     *
     * @param id          Id of collection.
     * @param form        Input.
     * @param response    HTTP response.
     *
     * @return Updated collection.
     */
    @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
    @ResponseBody
    public Collection update(
        @PathVariable( "id" )
        Long id,

        @RequestBody
        @Valid
        CollectionForm form,

        HttpServletResponse response
    ) {
        try {
            //- Search -//
            Collection collection = this.collectionService.find( id );

            //- Check -//
            notNull( collection );

            //- Update -//
            collection.setLocale( form.getLocale() );
            collection.setTitle( form.getTitle() );
            collection.setDescription( form.getDescription() );
            //TODO: copy songs

            return this.collectionService.update( collection );
        } catch ( IllegalArgumentException e ) {
            //- Cannot find -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        } catch ( DataIntegrityViolationException e ) {
            //- Cannot update -//
            response.setStatus( HttpServletResponse.SC_CONFLICT );
        }

        return null;
    }

    /**
     * Delete collection by ID.
     *
     * @param id        Id of collection.
     * @param response  Use for work with HTTP.
     */
    @Secured( "ADMINISTRATOR" )
    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
    @ResponseBody
    public void deleteAction(
        @PathVariable( value = "id" )
        Long id,

        HttpServletResponse response
    ) {
        try {
            this.collectionService.delete( id );
        } catch ( EmptyResultDataAccessException e ) {
            //- Failure. Cannot find this song -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }
    }
}
