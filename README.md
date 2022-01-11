Contained in each folder: 
src/ all code
test/ all tests
res/ mosaic and downscale images, jar file

TA told us to specify that Windows would be better due to the usage of \ instead of / so filenames
would be messed up if ran on a macOS.

For this current version of our image processing program, we have representation for both
a single image, and a multi-layered image model, and we also have two views, one that supports
file scripting and interactive scripting, and one that is a GUI LIME view that allows the user
to interact with buttons, comboboxes, and menus. 

Regarding the model parts of our program that are complete, we have our single layer model, which 
allows the user to only insert one image, and can blur, sepia, greyscale, and sharpen the image.
We also have our multi-layer model, which handles multi-layered images, and allows the user to do
various operations such as adding a layer, removing a layer, setting a layer as the current layer,
setting a layer to visible and invisible, renaming a layer, blurring, sharpening, sepia, 
greyscale, mosaic, and downscaling the current layer.

Regarding the controller parts of our program that are complete, we have 3 different controllers,
one for the first implementation of the image processing application which was for a single image,
one for the multilayered model, and one for the new MyWindow view that we added for the GUI LIME 
view. The first controller named ImageProcessingControllerV1 simply loads an image to the model. 
The second controller allows for the user to do interactive scripting, as well as input a script
file. The last controller connects the MyWindow view and the multilayer model and acts as a 
messenger, receiving commands from the view to run on the model. 

Regarding the view parts of our program that are complete, we have two different views. One that
appends a message provided from the controller, which is used for scripting. The other view
which is the GUI LIME view which allows the user to press buttons to do certain operations, it
calls on the LIMEController to run operations on the multi-layer model. 

This version of our image processing application can be extended to add even more features in the
future.

A design change that we made from the previous homework was the implementation of mosaic and 
downscaling. We added those methods into the multi-layered model as they were just added operations,
and then we implemented the actual functionality in the Layer class and simply called on those 
methods from the multi layer model. We also added two methods to our MyUtil class which helped
with converting an IImage to an Image as well as getting a blank default image to display in our
GUI LIME view when there were no images already displayed. We implemented a new controller interface 
IViewListener, a class MyWindow, and new controller LIMEController. The IViewListener interface 
contained the operations used in the LIMEController to handle the emit operations from the MyWindow
view, and the controller calls certain operations on the model. The view displays a GUI for the
user to interact with.

Our program accepts 3 different command line inputs. One for reading a script file, one for 
interactive scripting, and one for the GUI. For the script file and interactive scripting, follow
the attached txt file named scriptCommands to see the syntax and available commands. To use the GUI,
follow the USEME txt file of how to use each operation. 

All the images in the res folder used to run the script, as well as used to demonstrate downscale
and mosaic are images belonging to Allison Li and Runyu Wang, and we allow the use of these images.