package ba.fit.kino.util;

import org.json.JSONObject;

import android.util.Log;

public class JSONParser {

	public static JSONObject parseJSONfromStringJSON (String jsonString){				// funkcija koja pretvara jsonString u JSONobject
		
		JSONObject jsonObject=null;														// kreiranje jsonObjekta
		
		Log.i("jsonString = ", jsonString);
		
		try {					
			jsonObject= new JSONObject(jsonString);									    // inicijalizacija JSONobjekta i pretvaranje prosledjenog stringa u JSON objekt
			
			
		} catch (Exception e) {
			Log.e("JSON parse error", " ne moze "+e.getMessage());					    // printanje errora u konzolu
			
		}
		
		return jsonObject;																// ukoliko je sve uspijesno vraca JSONobject
	}
	
}
