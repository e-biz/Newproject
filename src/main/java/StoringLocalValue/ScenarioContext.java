package StoringLocalValue;


import java.util.HashMap;
import java.util.Map;


public class ScenarioContext {
	
	private static Map<String, String> sharedData = new HashMap<>();
	
	// Item1 = SVR1
	// Item2 = SVR2
	//REQNo1 = 1123
	//ReqNO2 = ABC

    public static void put(String key, String value) {
        sharedData.put(key, value);
    }

    public static String get(String key) {
        return sharedData.get(key);
    }


	

}

