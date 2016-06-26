Christopher Brown & William Tan

Model:
The has two parts.

Controller:
The controller's job is to take input from the MouseHandler and the
KeyboardHandler.  Those two classes are configured in the controller and then
added to the view.  The controller then responds to the input from the mouse
or keyboard and relays the info back to the model.

View:
The View interface has one method, display(). This method displays the given
view. It gets the relevant information from the controller and then
"displays" the view according to how the specific view was implemented. Like
the controller, changing the implementation of a specific View will not
require any changes to the other parts of the program.

The model, controller, and view are completely separated from each other, so
changes to any of the parts are easy to make.
