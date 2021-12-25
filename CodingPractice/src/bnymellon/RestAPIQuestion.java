package bnymellon;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;



public class RestAPIQuestion {

    /*
     * Complete the 'getArticleTitles' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING author as parameter.
     * 
     * URL for cut and paste: 
     * https://jsonmock.hackerrank.com/api/articles?author=<authorName>&page=<num>
     *
     */

	public static void main(String[] args) {
		getArticleTitles("olalonde");
	}
	
	
    public static List<String> getArticleTitles(String author) {
        List<String> titles =  new ArrayList<String>();
        try {
        	
        	StringBuffer output = getJsonResponse(author, 1);
            
            Gson gson  = new Gson();
            
            JsonObject jsonObject = gson.fromJson(output.toString(),JsonObject.class);
            int pages = jsonObject.getAsJsonObject().get("total_pages").getAsInt();
            int counter = 1;
            
            while(counter <= pages){
            	
            	output = getJsonResponse(author, counter);
            	JsonArray arr = jsonObject.getAsJsonArray("data");
            	
            	for (JsonElement e : arr) {
                    JsonElement title = e.getAsJsonObject().get("title");
                    JsonElement sTitle = e.getAsJsonObject().get("story_title");
                    
                    if(title != null){
                        titles.add(title.getAsString());
                    }else if(title == null && sTitle != null){
                        titles.add(sTitle.getAsString());    
                    }
                    
                }
            
            	counter++;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println(titles);
        
        return titles;
    }

	private static StringBuffer getJsonResponse(String author, int page)
			throws MalformedURLException, IOException, ProtocolException {
		String targetURL = "https://jsonmock.hackerrank.com/api/articles?author=" + author + "&page="+ page;
      
      
		URL url = new URL(targetURL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
   
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		String line;
		StringBuffer output = new StringBuffer();
		
		while ((line = bufferedReader.readLine()) != null) {
		    output.append(line);
		}
		bufferedReader.close();
		return output;
	}

}