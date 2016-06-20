Christopher Brown & William Tan

Model:
The model was changed from the first assignment. We found out that storing the
notes in only a list can be extremely inefficient when we want to get
information about the list of notes, so we decided to change the data structure
from just a list to William Tan's composition data structure. A composition
contains 2 sorted maps, the first one being a map from beat to list of notes,
and the second one being a map from note to list of beats it is at. We decided
to use a sorted map because it allows us to get the first and last note, and
beat very easily. A fast run time is required to get information about the
composition so the view does not have to wait very long to get the information.
We also added more functionality to the MusicNote class. For example having
fields for instruments and volume. We also added a static Utilities class to
the MusicNote class to avoid creating unnecessary objects when looping over
pitches and octaves to compare them with other notes.

Controller:
The controller's only job is to communicate between the model and views.
Because of this, the controller calls only methods from the model interface.
The controller also tells a given view to start. Changing anything in the
model or view besides the interface signatures will never break the
controller because it does not care about implementation details.

View:
The View interface has one method, display(). This method displays the given
view. It gets the relevant information from the controller and then
"displays" the view according to how the specific view was implemented. Like
the controller, changing the implementation of a specific View will not
require any changes to the other parts of the program.

The model, controller, and view are completely separated from each other, so
changes to any of the parts are easy to make.
