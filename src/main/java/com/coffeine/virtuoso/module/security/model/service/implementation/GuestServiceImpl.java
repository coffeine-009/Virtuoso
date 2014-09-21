/// *** Security :: Model :: Service :: GuestService    *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-08-22 12:28:32 :: 2014-08-22 12:48:08
     *
     * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.security.model.service.implementation;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @version 1.0
 */
@Service
public class GuestServiceImpl implements ClientDetailsService {

    /// *** Properties  *** ///
    /**
     * Unique identificator of client
     */
    protected String clientId;

    /**
     * Secret key for access
     */
    protected String clientSecretKey;


    /// *** Methods     *** ///
    //- SECTION :: MAIN -//
    /**
     * Identify client by ID
     *
     * @param clientId
     * @return ClientDetails
     * @throws ClientRegistrationException
     */
    @Override
    public ClientDetails loadClientByClientId(
        String clientId
    ) throws ClientRegistrationException {
        //- Check ID of client -//
        if ( this.clientId.equals( clientId ) ) {

            BaseClientDetails clientDetails = new BaseClientDetails();
                clientDetails.setClientId( this.clientId );
                clientDetails.setClientSecret( this.clientSecretKey );
                clientDetails.setAuthorizedGrantTypes(
                    new ArrayList < String >() {
                        {
                            add( "password" );
                            add( "refreshToken" );
                            add( "credentials" );
                        }
                    }
                );

            return clientDetails;
        } else {
            throw new NoSuchClientException(
                String.format( "Client %s not found.", clientId )
            );
        }
    }


    //- SECTION :: GET -//
    /**
     * Get client ID
     *
     * @return String clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Get client secret key
     *
     * @return String clientSecretKey
     */
    public String getClientSecretKey() {
        return clientSecretKey;
    }


    //-  SECTION :: SET -//
    /**
     * Set client ID
     *
     * @param clientId Unique identificator of client
     */
    public void setClientId( String clientId ) {
        this.clientId = clientId;
    }

    /**
     * Set secret key of client for access
     *
     * @param clientSecretKey
     */
    public void setClientSecretKey( String clientSecretKey ) {
        this.clientSecretKey = clientSecretKey;
    }
}
