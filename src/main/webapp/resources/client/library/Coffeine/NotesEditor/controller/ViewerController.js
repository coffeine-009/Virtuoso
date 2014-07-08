/**
 * @copyright 2014 (c), by Vitaliy Tsutsman
 * 5/28/14.
 */
var ViewerController = function(
    Context,
    Width,
    Height
)
{
    this.view = new ViewerView(
        Context,
        Width,
        Height
    );
};

ViewerController.prototype.viewAction = function()// ; void
{
    this.view.render();
};
