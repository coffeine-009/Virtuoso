/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

/// *** User :: Controller :: StaffType         *** *** *** *** *** *** *** ///

    //*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.music.model.entity.StaffType;
import com.coffeine.virtuoso.music.model.service.StaffTypeService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

/**
 *
 * @author Администратор
 */

@RestController
@RequestMapping( value = "/user/staff/type")
public class StaffTypeController {

    //    private final static

    /// *** Properties  *** ///
    protected StaffTypeService staffTypeService;


    /// *** Methods     *** ///
    //- SECTION :: ACTION -//
    /**
     * Find all staff types per page
     *
     * @param page  Number of page
     * @param limit Count of items per page
     * @return List < StaffType > List of staff types
     */
    @GET
    @RequestMapping( value = "list/{PAGE}/{LIMIT}")
    @ResponseStatus( HttpStatus.OK)
    @ResponseBody
    public List <StaffType> findAllAction(
        @PathVariable("PAGE")
        int page,

        @PathVariable("LIMIT")
        int limit) {
        //- Search page -//
        return this.staffTypeService.findAll(
            Math.max( page - 1, 0),
            limit
        );
    }

    /**
     * Create a new staff type
     *
     * @param staffType Type of staff for create
     * @param response  Used for set HTTP status
     * @return StaffType Created type of staff
     */
    @POST
    @RequestMapping( value = "/")
    @ResponseBody
    public StaffType createAction(
        @RequestBody
        @Valid
        StaffType staffType,

        HttpServletResponse response 
    ) {
        //- Try to create new type of staff -//
        try {
            //- Set HTTP status -//
            response.setStatus( HttpStatus.CREATED.value() );

            //- Success. Return created staff type -//
            return this.staffTypeService.create( staffType );
        }
        catch ( DataIntegrityViolationException e ) {
            //- Failure. Can not to create staff type -//
            response.setStatus( HttpStatus.FORBIDDEN.value() );
        }
        catch ( Exception e ) {
            //- Failure. Can not to create staff type -//
            response.setStatus( HttpStatus.FORBIDDEN.value() );
        }

        return null;
    }

    /**
     * Update staff type
     *
     * @param id        ID of video type for update
     * @param staffType Data for update
     * @param response  Used for set HTTP status
     * @return StaffType Updated type of staff
     */
    @PUT
    @RequestMapping( value = "/{ID}", method = RequestMethod.PUT )
    @ResponseBody
    public StaffType updateAction(
        @PathVariable( "ID" )
        Long id,

        @RequestBody
        @Valid
        StaffType staffType,

        HttpServletResponse response
    ) {
        //- Search staff type by ID -//
        StaffType staffTypeOrigin = this.staffTypeService.find( id );

        if ( staffTypeOrigin == null ) {
            //- Not exists ID of staff type -//
            response.setStatus( HttpStatus.NOT_FOUND.value() );
            return null;
        }

        //- Try to create new type of video -//
        try {
            //- Set HTTP status -//
            response.setStatus( HttpStatus.OK.value() );

            //- Update value  -//
            staffTypeOrigin.setTitle( staffType.getTitle() );
            staffTypeOrigin.setCode( staffType.getCode() );
            staffTypeOrigin.setDescription( staffType.getDescription() );

            //- Success. Return created staff type -//
            return this.staffTypeService.update( staffTypeOrigin );
        }
        catch ( DataIntegrityViolationException e ) {
            //- Failure. Can not to create staff type -//
            response.setStatus( HttpStatus.FORBIDDEN.value() );
        }
        catch ( Exception e ) {
            //- Failure. Can not to create staff type -//
            response.setStatus( HttpStatus.FORBIDDEN.value() );
        }

        return null;
    }

    /**
     * Delete staff type
     *
     * @param id        ID of staff type for delete
     * @param response  Used for set HTTP status
     */
    @DELETE
    @RequestMapping( value = "/{ID}")
    @ResponseBody
    public void deleteAction(
        @PathVariable("ID")
        Long id,

        HttpServletResponse response 
    ) {
        //- Try to delete staff type -//
        try {
            //- Set HTTP status -//
            response.setStatus(HttpStatus.OK.value());

            //- Success. Delete staff type -//
            this.staffTypeService.delete( id );
        }
        catch ( EmptyResultDataAccessException e ) {
            //- Not exists ID -//
            response.setStatus( HttpStatus.FORBIDDEN.value() );
        }
       catch ( Exception e ) {
           //- Not exists ID -//
           response.setStatus( HttpStatus.FORBIDDEN.value() );
       }
    }
}
