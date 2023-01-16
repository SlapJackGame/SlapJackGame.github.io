package coms362.cards.utilities;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Based on code by Roland Illig, via stackexchange
 * https://codereview.stackexchange.com/questions/175332/splitting-url-query-string-to-key-value-pairs
 *
 */
public class QueryParams {
	
	private Map<String, String> params = new LinkedHashMap<>();
	
	public QueryParams (String query) {
        try {
            for (String param : query.split("&")) {
                String[] keyValue = param.split("=", 2);
                String key = URLDecoder.decode(keyValue[0], "UTF-8");
                String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], "UTF-8") : "";
                if (!key.isEmpty()) {
                    params.put(key, value);
                }
            }	           
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e); // Cannot happen with UTF-8 encoding.
        }
    }
	
	public String get(String key){
		return params.get(key) ;
	}
	
	public void put(String key, String value){
		params.put(key, value);
	}
	
	public int size(){
		return params.size();
	}
	
	public Map<String,String> toMap(){
		return params;
	}
	
}
