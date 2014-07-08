/**
 * Created by vitaliy on 5/28/14.
 */
/**
 * @constructor
 */
var ViewerView = function(
    Context,
    Width,
    Height
)// : void
{
    this.staffHelper = new StaffHelper(
        this.context = Context,
        this.width = Width,
        this.height = Height
    );
};

ViewerView.prototype.render = function()// : void
{
    this.staffHelper.render();
};
