Tianqi Wen (tianqiw)

1. The database functionalities are implemented in the database package of CarConfigServer project

2. Interface AutoDB is applied to enable interaction between BuildAuto in adapter package and ImplDB in database package

3. Queries are read from text files using the IO method in Util.java

4. Database contains 3 tables
Models(model, make, price)
OptionSet(id, name, model)
Options(id, name, price, opsetid)

5. Test cases are in driver package Driver.java class of CarConfigServer project (you can still use client to test, but the cases are all written in server-side)

        BuildAuto build = new BuildAuto();
		
		// create db tables
		System.out.println("(1) Init db");
		build.initDB(CREATE_TABLE_FILE, DROP_TABLE_FILE);
		build.showModelDB(MODEL);
		
		// When a new Automobile is added to the LinkedHashMap
		System.out.println("(2) Add auto");
		build.buildAuto("configuration", ".txt");
		build.showModelDB(MODEL);
		
		// When an Automobile is updated in the LinkedHashMap
		System.out.println("(3) Update price and name");
		build.updateOptionSetName(MODEL, "Color", "Color Choices");
		build.updateOptionPrice(MODEL, "Transmission", "automatic", 500.f);
		build.showModelDB(MODEL);
		
		// When an Automobile is deleted from the LinkedHashMap
		System.out.println("(4) delete model");
		build.deleteAuto("Focus Wagon ZTW");
		build.showModelDB(MODEL);
