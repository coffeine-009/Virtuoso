/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.music.model.entity.StaffType;
import com.coffeine.virtuoso.music.model.service.StaffTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.springframework.util.Assert.notNull;

/**
 * Controller for work with staffs' types.
 *
 * @version 1.0
 */
@RestController
@RequestMapping( value = "/music/staffs/types" )
public class StaffTypeController {

    /// *** Properties  *** ///
    /**
     * Service for wirk with staffs' types.
     */
    @Autowired
    protected StaffTypeService staffTypeService;


    /// *** Methods     *** ///
    //- SECTION :: ACTION -//
    /**
     * Find all staff types per page.
     *
     * @param page  Number of page
     * @param limit Count of items per page
     *
     * @return List of staff types.
     */
    @RequestMapping( method = RequestMethod.GET )
    @ResponseStatus( HttpStatus.OK)
    @ResponseBody
    public List<StaffType> findAllAction(
        @RequestParam( value = "page", required = false, defaultValue = "1" )
        int page,

        @RequestParam( value = "limit", required = false, defaultValue = "10" )
        int limit
    ) {
        //- Search page -//
        return this.staffTypeService.findAll(
            Math.max( page - 1, 0),
            limit
        );
    }

    /**
     * Create a new staff type.
     *
     * @param staffType Type of staff for create
     * @param response  Used for set HTTP status
     *
     * @return Created type of staff
     */
    @RequestMapping( method = RequestMethod.POST )
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
        } catch ( DataIntegrityViolationException e ) {
            //- Failure. Can not to create staff type -//
            response.setStatus( HttpServletResponse.SC_FORBIDDEN );
        }

        return null;
    }

    /**
     * Find by id.
     *
     * @param id          Id of staff type.
     * @param response    Used for work with HTTP.
     *
     * @return Found staff type.
     */
    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    @ResponseBody
    public StaffType find(
        @PathVariable( value = "id")
        Long id,

        HttpServletResponse response
    ) {
        try {
            //- Search staff type -//
            StaffType staffType = this.staffTypeService.find( id );

            //- Check -//
            notNull( staffType );

            return staffType;
        } catch ( IllegalArgumentException e ) {
            //- Failure. Cannot find staff type -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }

        return null;
    }

    /**
     * Update staff type.
     *
     * @param id        ID of video type for update
     * @param staffType Data for update
     * @param response  Used for set HTTP status
     *
     * @return Updated type of staff
     */
    @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
    @ResponseBody
    public StaffType updateAction(
        @PathVariable( "id" )
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
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
            return null;
        }

        //- Try to create new type of video -//
        try {
            //- Update value  -//
            staffTypeOrigin.setTitle( staffType.getTitle() );
            staffTypeOrigin.setCode( staffType.getCode() );
            staffTypeOrigin.setDescription( staffType.getDescription() );

            //- Success. Return created staff type -//
            return this.staffTypeService.update( staffTypeOrigin );
        } catch ( DataIntegrityViolationException e ) {
            //- Failure. Can not to create staff type -//
            response.setStatus( HttpServletResponse.SC_FORBIDDEN );
        }

        return null;
    }

    /**
     * Delete staff type.
     *
     * @param id        ID of staff type for delete
     * @param response  Used for set HTTP status
     */
    @RequestMapping( value = "/{id}")
    @ResponseBody
    public void deleteAction(
        @PathVariable("id")
        Long id,

        HttpServletResponse response 
    ) {
        //- Try to delete staff type -//
        try {
            //- Success. Delete staff type -//
            this.staffTypeService.delete( id );
        } catch ( EmptyResultDataAccessException e ) {
            //- Not exists ID -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }
    }
}
