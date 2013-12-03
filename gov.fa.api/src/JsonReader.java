
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static List<ForeignAssistanceDataItem> readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		List<ForeignAssistanceDataItem> items = new ArrayList<ForeignAssistanceDataItem>();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			JSONArray recs = json.getJSONArray("JSONDataResult");
			try
			{
				// looping through fa.gov data
				for(int i = 0; i < recs.length(); i++){
					JSONObject c = recs.getJSONObject(i);
					//System.out.println(c.toString());
					ForeignAssistanceDataItem item = new ForeignAssistanceDataItem(c.getString("Status"), 
							c.getString("Category"), 
							c.getString("Amount"), 
							c.getString("BenefitingLocation"), 
							c.getString("AgencyName"), 
							c.getString("FiscalYear"), 
							c.getString("Sector"));
					items.add(item);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}	  
		} finally {
			is.close();
		}
		return items;
	}

	public static void main(String[] args) throws IOException, JSONException {

		List<ForeignAssistanceDataItem> items = readJsonFromUrl("https://fedweb.pwc.com/dashboardserviceapi/dashboardserviceapi.svc/json/GetData?type=spent&filter=country&option=ghana&year=2013");
		for(ForeignAssistanceDataItem item : items) {
			System.out.println("AgencyName: " + item.AgencyName());
		}
	}
}