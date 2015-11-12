Project 1 Unit 4
Student Name: Tianqi Wen
Andrew ID: tianqiw

JRE 1.7, Project exported by Eclipse

1. Exposure to LinkedHashMap object is done only through interfaces
    Server can only access LinkedHashMap object through AutoServer interface which is implemented by both 
    BuildCarModelOptions and BuildAuto
    
2. Client and Server use Object Streams only
    Both client and server only use ObjectInputStream and ObjectOutputStream for communication
    
3. Server is not storing any information about class configuration
    When client receives the chosen model from server and configuration is made due to user input, the
    configured Automobile will not be sent back to server nor save in client-side LinkedHashMap. Instead,
    the configuration information is printed to the screen at client-side
   
4. DefaultSocketClient class is used in Server.java and RequestHanlder.java (proxy to handle multiple client
    connection concurrently) at server-side in server package
    
5. DefaultSocketClient class is used in Client.java at client-side in client package

6. Test:
    Two .props file available at client-side, named Ford.props and BMW.props
    Enter the valid command in client console to carry out the test.
    
    show
        show the list of models available at server-side
        
    send filename
        send the Properties file to server
        e.g. send Ford.props
        
    select modelname
        select target model
        e.g. select Focus Wagon ZTW
        server will return the respective Automobile
        when client receive the Automobile, it will print its information on the screen and allow
        user to type choices one by one
        e.g. 
        automatic   (available choice)
        manual      (available choice)
        Please choose one option for Transmission
        automatic   (user choice)
        
        after configuration for all optionsets are made
        the choices of user will be shown on the client console
        e.g.
        Brakes/Traction Control: Standard
        Color: Pitch Black Clearcoat
        Transmission: automatic
        Side Impact Air Bags: present
        Power Moonroof: not present
    
    quit
        client send a CLOSE message to the server and server will respond with a confirmation message
        then closeSession will be called and the client is closed too