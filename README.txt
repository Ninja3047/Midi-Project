Christopher Brown & William Tan

Design changes: We refactored our views to not rely on the controller and
created a ModelObserver which the views use as a read only Model.

Model: The has two parts, the Model itself, and the ModelObserver.  The
ModelObserver follows the Observer design pattern and ensures that the views
only have read only access to the model.

Controller: The controller's job is to take input from the MouseHandler and the
KeyboardHandler and attach them to the view.  Those two classes are configured
in the controller and then added to the view.  The controller then responds to
the input from the mouse or keyboard and relays the info back to the model.
The Gui view is the only view that uses the controller, the other views don't
require a controller since they don't take any input.

View: The View interface has one method, display. This method displays the
given view. It gets the relevant information from the controller and then
"displays" the view according to how the specific view was implemented. Like
the controller, changing the implementation of a specific View will not require
any changes to the other parts of the program.  The view also does not have
access to any part of the controller and it only has read only access to the
model.

The model, controller, and view are completely separated from each other, so
changes to any of the parts are easy to make.
