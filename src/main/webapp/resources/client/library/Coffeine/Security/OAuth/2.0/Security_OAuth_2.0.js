/**
 * @copyright 2014 (c), by Vitaliy Tsutsman
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-09-04 10:40:47 ::
 */

define(
    [
    ],
    function() {
        /**
         * Constructor for create security
         *
         * @constructor
         */
        var OAuth2 = function (
            /*string.url*/  AccessURL
        )
        {
            /// *** Constants   *** ///
            this.STORRAGE_KEY = "__oauth2";

            /// *** Properties  *** ///
            //- Initialization -//
            this.accessURL = AccessURL;

            this.clientId;
            this.clientSecret;

            this.grantType;
            this.scope;
        };
    }
);
