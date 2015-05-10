/**
 * Created by vitaliy on 5/29/14.
 */
var StaffHelper = function(
    /**/Context,
    /**/Width,
    /**/Height
)
{
    this.context = Context;
    this.width = Width;

    //- Notnyi stan -//
    for ( var i = 0, y = 20; i < 5; i++, y += 10 ) {
        this.context.moveTo( 0, y );
        this.context.lineTo( this.width, y );
    }
};

StaffHelper.prototype.render = function()// : void
{
    this.context.stroke();
};
