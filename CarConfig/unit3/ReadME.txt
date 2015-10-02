Project 1 Unit 3
Student Name: Tianqi Wen
Andrew ID: tianqiw

JRE version: 1.7
Project exported by Eclipse

Multi-threading is implemented in EditOptions only
Clarifications: I put all the synchronized blocks in EditOptions since we
should synchronize methods in EditOption class to isolate multi-threading and
synchronization

synchronized example
public void synUpdateOptionSetName(String model, String opsetName,
            String newName) {
    synchronized (autos) {
        autos.getAuto(model).updateOptionSetName(opsetName, newName);
    }
}
lock on autos object (linkedHashMap of Automobile) since simply make the method
synchronized will lock on EditOption object, but different edit threads has
different EditOption object and will be able to edit at the same time and cause
data corruption.

Interface:
SynEditAuto
public void synUpdateOptionSetName(String model, String opsetName,
		String newName);

public void synUpdateOptionPrice(String model, String opsetName,
        String optionName, float newPrice);

public void synDeleteOptionSet(String model, String opsetName);

public void synDeleteOption(String model, String opsetName,
        String optionName);

// non-synchronized methods for testing only
public void nonSynUpdateOptionPrice(String model, String opsetName,
        String optionName, float newPrice);

public void nonSynDeleteOption(String model, String opsetName,
        String optionName);
        
Test Cases:
Test 1: synchronized update and delete - no error
		either option not found option or update first and then delete

Test 2 (remove synchronization in Test 1): 
        non-synchronized update and delete
        the program will crash when the update method find the option 
        to update first but when it's ready to update, the option to 
        be updated is deleted by the delete thread
        
Test 3: synchronized delete on same option - no error
        The later delete will report option not found
   
Test 4 (remove synchronization in Test 3):
        If both threads fetch the option to delete first, the program will
		crash if the later thread tries to delete the option