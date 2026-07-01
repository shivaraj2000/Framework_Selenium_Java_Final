package Data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {

        //Reading the file to String
        String jsonContenet=FileUtils.readFileToString
                (new File(System.getProperty
                        ("user.dir") + "\\src\\test\\java\\Data\\PurchaseOrder.json"),
                        StandardCharsets.UTF_8);

        //Converting String to List with Hashmap using Jackson Bind
        ObjectMapper ob= new ObjectMapper();
        List<HashMap<String,String>> data= ob.readValue(jsonContenet, new TypeReference<List<HashMap<String, String>>>()
        {});
        return data;

    }
}
