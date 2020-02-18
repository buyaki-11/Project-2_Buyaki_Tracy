# Project-2 
Refactoring the ScalePlayer application code so that the layout of the window is stored in an FXML file and the style of the window is stored in a CSS file.

Authors: Buyaki Nyatichi, Tracy Cui

Design Overview:
We created a ScalePlayer.fxml file that handled the creation of the menubar, menu item and buttons. In the ScalePlayer.css file, we added background color to the "Play" and "Stop" buttons created in the fxml file. We also set the preferred height and width, and spacing. The ScalePlayer.java file handled the application logic without any of the GUI elements except the dialog box. By having these three files, we separated the application logic from the design making the code easier to maintain.

Inelegance:

Our “Play” and “Stop” buttons are visually distinct but only by virtue of color. We could make this more elegant by using icons. We did not have enough time to implement this. We also do not handle the case where a user enters invalid input, for example, an integer greater than 115 or a non-integer. We could have handled this case more gracefully rather than letting the program crash. We also did not have time to implement this. 

Collaboration:
We mostly worked separately but got together and proceeded with the best solution of the two and kept building on it. We again divided what was left between the two of us. This was mostly the css file and some issues that we ran into while compiling the fxml file. Working separately allowed us to gain a better understanding of the tools we were using which made it easier once we brought it back together.
