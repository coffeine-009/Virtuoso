/**
 * @copyright 2014 (c), by Cofeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-05-27 22:06:21 ::
 *
 * @address /Ukraine/Ivano-Frankivsk
 */


/**
 * Notes editor and viewer
 */

/**
 * @constructor
 */
var NotesEditor = function(
    /*string*/  CanvasIdentificator,
    /*int*/     Width,
    /*int*/     Height
)// : void
{
    /// *** Properties  *** ///
    this.handle = document.getElementById( CanvasIdentificator );
    this.context = this.handle.getContext( "2d" );

    //- MVC -//
    this.controller = new ViewerController(
        this.context,
        Width,
        Height
    );
    this.controller.viewAction();
};

/// *** Methods     *** ///
NotesEditor.prototype.run = function()// : void
{

};
