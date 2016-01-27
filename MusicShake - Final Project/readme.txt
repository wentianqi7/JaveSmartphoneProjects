This is project2_unit2 by group bilibili
Student1: Tianqi Wen (tianqiw)
Student2: Mengyu Yang(mengyuy)
Student3: Xinyu Wang (xinyuwan)

The source codes are in the "Source Code" directory
Part A - Android App:
MusicShake.zip
Extract the zip file and the project can be directly imported into Android Studio.
Business Model can be seen at ./business_model_canvas_poster.pdf
Class Diagram can be seen at project2Unit2Diagram.png
Requirement Analysis can be seen at Project_Analysis.pdf.

The java source code can be found in ./app/src/
Notice: Make sure that there's /sdcard/MusicShake/Song/Music and /sdcard/MusicShake/Song/Cover directory on your device. Our program does not handle this case currently.

There are several packages:

ui:
	activities: Define seven activities:
		LoginActivity: Implement the login page
		MainActivity: Implement the main page of the app. From this page user can navigate to paly, profile, search and shake pages.
		PlayActivity: After a user select one music, the app will be directed to play page. On Play page, swipe to left you can go back to play list.
		SearchActivity: Shows the music played by nearby people recently. You can go back to play page by swiping to right.
		ShakeActivity: Implement the UI where the user shake his/her phone the get the people list who are shaking at the same time. You can go back to play page by swiping to right.
		ProfileActivity: Implemented the UI for user profile. User can go back to play page by swiping to right.
		MultiFragmentAcitivity: abstract class that every activity will extend from, defines three fragment - header, content, and footer

utils:
	ChangeActivity: Implemented intents to switch between two pages.
	GestureHandler: Implemented the swipe guesture.
	Logger: Implemented logging function.
	NavigateHelper: Implemented switching intents.
	progressDialogCreater: create the dialog for downloading process
	Utils: tools for io and conversion between classes

exception:
	CustomException: Implemented custom exception handlers, handle exceptions in user input, network connection, and etc that may crash the process.

model:
	ProfileRecord: profile record data
	SongRecord: song record data

entities:
	ProfileCache: temporarily store profile of the matched user in a shaking event
	ProfileLab: dealing with CRUD operations of profile
	SongCache: temporarily store song list in a shaking or searching event
	SongLab: dealing with CRUD operations of song

dblayout:
	Constants: Defined constant variables
	DBAdapter: execute queries on SQLite DB
	SongCRUD and ProfileCRUD: interface that declares CRUD operations on the models

Note that intents are setup in ChangeActivity.java and GestureHandler.java


Part B - Web Service
WebService.tar
The web service is a maven project. The pom.xml file is under the root directory. You will need install Maven 3 to install this project.
1. Use the following command to extract the file:
tar -xvf WebService.tar
2. Download necessary dependencies and compile the project:
mvn clean install
3. Start the server:
mvn exec:java

Web app path: /MusicShake
Interfaces provided:
/shake?longitude=&latitude=&songs=&profile=
/login?username=&password=
/search?pattern=