Project 1 Unit 5
Tianqi Wen (tianqiw)
Tomcat 7.0
JRE 1.7

1. Servlet interacts with Client to get the list of available models:
ShowModelSerlet.java
showModel.jsp

2. Servlet interacts with Client to get the data for the list of available OptionSets:
GetOptionSetServlet.java
showOptionset.jsp

3. JSP shows the OptionSets and prints the selected choices with total vehicle cost:
showUserChoice.jsp

4. As in Unit 4, Server read model dynamically from the LinkedHashMap, so the data is dynamically displayed.

5. Interface applied between Servlet and Client:
public interface AutoServlet {
	public void openConnection();

	public void closeSession();

	public ArrayList<String> showModelList() throws Exception;

	public void getSelectedModel(String target) throws Exception;
	
	public ArrayList<String> getOpsetNames();
	
	public ArrayList<String> getOptionNames(String opsetName);
	
	public int getOptionPrice(String opsetName, String optionName);
	
	public int getBasePrice();
	
	public String getMake();
}

Servlets do not have direct access to client/server but need to interact with them through BuildClientCommand.java

Test cases:
1. No special case
screenshot:
test1_show.png
test1_choose.png
test1_price.png

2. Choose fields with spaces
(Cannot directly submit, replace spaces with placeholder)
test2_choose.png
test2_price.png

3. Access through multiple browser at the same time
test3_price.png

