/* 
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-05-06 22:00:00 :: 2014-05-07 23:32:08
 *
 * @address /Ukraine/Ivano-Frankivsk/Chornovola/114/51
 */
var User = User || {};

$(function(){
    'use strict';

    new User.SongController();

    Backbone.history.start();
});
