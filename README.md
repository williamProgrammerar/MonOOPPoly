# MonOOPPoly
The beginning of Chalmers Monopoly

Creators:

William Johnston, github: williamProgrammerar

Hampus Rhedinh, github: rhedinh

Jon Emilsson, github: JonEmilsson

Vilhem Hedquist, github: Hedquist



Running the program: 
To run the program you probably need to either configure your VM options or need to go through the maven-javafx plugin. 
This goes for the IntellJ version for the project, we are unsure if it works similairly in other enviroments. 
In IntellJ open the project and build. You can attempt the run the program but will probably run into an issue that "JavaFX runtime components are missing..."
Then head to the right hand menu where it says "Maven" or navigate in the top right bar "View -> Tool Window -> Maven"
Here go to "Plugins -> javafx -> javafx:run" this should work and the application should start. If it doesnt you might need to compile in Maven
Go: "Plugins -> compiler -> compiler:compile". and then run javafx:run again.

Some issues have been discovered when remotely connecting to the school computers. The application starts but
when pressing "start game" the program gets an error. We are unsure exactly what's causing this, it might be a user setting, firewall setting or the
fact that the operating system is linux. There might be similar issues, then you might have to configure the sdk manually, the version is 15.
