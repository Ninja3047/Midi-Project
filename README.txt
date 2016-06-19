Christopher Brown & William Tan

Model:
The model was changed from the first assignment. We decided to use
composition to combine the best aspects of both models. We used composition
to easily combine the two models easily. We also added more functionality
to the MusicNote class. For example having fields for instruments and volume.
We also added a Utilities class to the MusicNote class to avoid creating
unnecessary objects when looping over pitches and octaves to compare them
with other notes.

Controller:
The controller's only job is to communicate between the model and views.
Because of this, the controller calls only methods from the model interface.
The controller also tells a given view to start. Changing anything in the
model or controller besides the interface signatures will never break the
controller because it does not care about implementation.

View:
The View interface has one method display(). This method displays the given
model. It gets the relevant information from the controller and then
"displays" the view according to how the specific view was implemented.  Like
the controller, changing the implementation of a specific View will not
require any changes to the controller.

The model, controller, and view are completely separated from each other, so
changes to any of the parts are easy to make.