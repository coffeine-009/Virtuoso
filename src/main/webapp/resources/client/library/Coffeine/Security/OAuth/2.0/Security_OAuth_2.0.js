/*
 * @copyright (c) 2015, by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 9/04/14 10:40 AM :: 1/10/15 1:23 PM
 *
 * @address /Ukraine/Ivano-Frankivsk
 */

var Coffeine = Coffeine || {
    Security: {
        OAuth: {
            _2: {
                Provider : null,
                Token: null
            }
        }
    }
};

/**
 * Module: OAuth 2.0
 *
 * @version 1.0
 */
define(
    [
        'jquery',
        'underscore',
        'backbone'
    ],
    function($, _, Backbone) {

        /**
         * Constructor for create security
         *
         * @constructor
         *
         * @param AccessURL
         * @param ClientId
         * @param ClientSecret
         */
        var OAuth2 = function (
            /*string.url*/  AccessURL,
            /*string*/      ClientId,
            /*string*/      ClientSecret
        ) {
            /// *** Properties  *** ///
            //- Initialization -//
            this.accessURL = AccessURL;

            this.clientId = ClientId;
            this.clientSecret = ClientSecret;
        };

        /**
         * Authenticate a user
         *
         * @param Username          E-mail of user
         * @param Password          Password of user
         * @param CallBackSuccess   Function called after success authenticate
         * @param CallBackFailure   Function called after fail authenticate
         */
        OAuth2.prototype.Authenticate = function(
            /*string*/  Username,
            /*string*/  Password,
            /*function*/CallBackSuccess,
            /*function*/CallBackFailure
        )//: void
        {
            // Store a reference to the object
            var self = this;

            // Request a new access-token/refresh-token
            $.ajax({
                url: this.accessURL,
                type: 'POST',
                data: {
                    grant_type      : 'password',
                    scope           : 'read',
                    client_id       : this.clientId,
                    client_secret   : this.clientSecret,
                    username        : Username,
                    password        : Password
                },
                dataType: 'json',

                /**
                 * Success event, triggered on every successful
                 * authentication attempt.
                 *
                 * @param {object} response
                 */
                success: function (response) {

                    // Create a new token for store
                    var token = new Token(
                        response.access_token,
                        response.refresh_token,
                        response.expires_in,
                        response.scope
                    );

                    //- Call success function -//
                    CallBackSuccess(token);
                },

                /**
                 * Error event, triggered on every failed authentication attempt.
                 *
                 * @param {object} response
                 */
                error: function (response) {
                    // Call failure function
                    CallBackFailure(response);
                }
            });
        };


        /**
         * Token object
         *
         * @version 1.0
         */
        var Token = function(
            /*string*/  AccessToken,
            /*string*/  RefreshToken,
            /*Date*/    ExpiresIn,
            /*string*/  Scope
        ) {
            /// *** Properties  *** ///
            // Get current time
            var currentTime = new Date().getTime();

            /**
             * Access token
             *
             * @type {string}
             */
            this.accessToken = AccessToken;

            /**
             * Refresh token
             *
             * @type {string}
             */
            this.refreshToken = RefreshToken;

            /**
             * Access token expires in
             *
             * @type {Date}
             */
            this.expiresEnd = currentTime + ( parseInt( ExpiresIn ) * 1000 );

            /**
             * Scope for got token
             *
             * @type {string}
             */
            this.scope = Scope;
        };

        /// *** Methods     *** ///
        /**
         * Get access token
         *
         * @returns {string}
         */
        Token.prototype.getAccessToken = function()// : string
        {
            return this.accessToken;
        };

        /**
         * Get refresh token
         *
         * @returns {string}
         */
        Token.prototype.getRefreshToken = function()// : string
        {
            return this.refreshToken;
        };

        /**
         * Check expires end of token
         *
         * @returns {boolean} true - token is expired,
         *                    false - token is not expired
         */
        Token.prototype.isExpired = function()// : boolean
        {
            //- Get current time -//
            var time = new Date().getTime();

            return this.expiresEnd < time;
        };

        /**
         * Get scope for this token
         *
         * @returns {string}
         */
        Token.prototype.getScope = function()// : string
        {
            return this.scope;
        };


        // Export
        Coffeine.Security.OAuth._2.Provider = OAuth2;
        Coffeine.Security.OAuth._2.Token = Token;
    }
);
