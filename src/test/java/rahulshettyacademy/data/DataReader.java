package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {  //FileUtils need commons.io dependency
		
		//Step 1:Read JSON file to a Single String                                                                                                      //Standard String Encoding Type
		String jsonContent= FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json"),StandardCharsets.UTF_8);
		
		//Step 2:Convert String to HashMap using Jackson Databind dependency import itfrom mvn repository and add it to pom.xml
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){}); //Syntax Returns List<HashMap1,HashMap2>
		
		return data;//Returns List containing HashMaps
		
	}

}
