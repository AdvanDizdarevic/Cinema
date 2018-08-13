package ba.fit.kino.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;


												// Zadatak funkcije je da radi query za url i header koji proslijedimo  u getResponseFromUrl()...

public class HttpManager {

	private InputStream inputStream; 			//  uzima response sa web servera stavlja u buffer stream
	private HttpClient httpClient;				//  to smo valjda mi?
	private HttpResponse httpResponse;		    // objekat odgovoran za resiving response od http requesta (http post)
	private HttpPost httpPost;					//
	private HttpEntity httpEntity;				// zadrzava headere i body od requesta  ???
	private StringBuilder result;				// zadrzava rezultat requesta u JSON string formatu te ga kasnije konvertuje u JSON object
	
	public HttpManager (){						// Konstruktor
		
		this.inputStream=null;
		this.httpClient=null;
		this.httpEntity=null;
		this.httpPost=null;
		this.result=null;
		this.httpResponse=null;
		
		
	}
	
	
														// Funkcija koja ceka odgovor od url-a
	public String getResponseFromUrl(String url, List<NameValuePair> params){
														//1 parametar - url koji nam treba
														//2 parametar - headeri???
		
			
			try {																				// konekcija sa web serverom
				this.httpClient= new DefaultHttpClient();										// predstavljace nas tj android uređaj
				this.httpPost= new HttpPost(url);												// Dodjeljumeo http postu url koji trazimo
				this.httpPost.setEntity(new UrlEncodedFormEntity(params));						// setujemo hedere . enkodiranje url-a ( npr da nema specijalnih karaktera, razmaka...)
				
				this.httpResponse= this.httpClient.execute(this.httpPost);						// za response
				this.httpEntity= this.httpResponse.getEntity();									// hvatanje headera i bodya od responsa
				this.inputStream=this.httpEntity.getContent();									// uzimanje body or responsa
				
			} catch (UnsupportedEncodingException e1) {																					
				Log.e("UnsupportedEncodingException",e1.getMessage());
				e1.printStackTrace();
			} catch (ClientProtocolException e1) {
				Log.e("ClientProtocolException",e1.getMessage());
				e1.printStackTrace();
			} catch (IllegalStateException e1) {
				Log.e("IllegalStateException",e1.getMessage());
				e1.printStackTrace();
			} catch (IOException e1) {
				Log.e("IOException",e1.getMessage());
				e1.printStackTrace();															// printanje greske
			}
			
		
		
		
		try {																																											// Inicijalizacij buffer readera, pretvaranje u string kojeg ce JSON object razumit																						
			
			BufferedReader reader= new BufferedReader(new InputStreamReader(this.inputStream, "UTF-8"),8);	// kreiranje bufferreader objecta koji prima imput stream koji ce bit enkodiran sa UTF-8(Dž,š,č...)
			
			this.result= new StringBuilder();                                  																							//
			
			String line=null;																																						//
			
			while ((line=reader.readLine())!=null) {																													// loop over buffer . sve dok ne dodjemo do kraja buffera
				this.result.append(line+"\n");																																// ubacivanje stringova u result
				
			}
			
			this.inputStream.close();																																			// brisanje iz memorije buffera
			
			return this.result.toString();																																	// vracamo rezultat u stringu
			
		} catch (Exception e) {
			Log.e("Error buffering",e.toString());																														// Ispis error-a u konzoli
		}
		return null;																																									// Ako je rezultat prazan vraca prazan buffer ili ti ga nulll nema nista da se vrati
	}
	
}
