import java.io.*;
import java.net.*;


public class HttpConnection 
{
	java.net.URL url;
	java.io.InputStream in;
	java.net.HttpURLConnection connection;
	String web = "http://dict.qq.com/dict?q=";
	
	public String httpRequest(String word)
	{
		// TODO String word,需要转义字符,如 空格= %20 ;
		try {
			url= new java.net.URL(web+word);
			connection= (java.net.HttpURLConnection)url.openConnection();
			
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:12.0) Gecko/20100101 Firefox/12.0");
			connection.connect();
			
			in= connection.getInputStream();
			BufferedReader buffer =  
					  
					new BufferedReader(new InputStreamReader(in , "utf8"));  
					  
					String result=buffer.readLine();  
					  
					if(result != null)
						return result;
			buffer.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "response error";
	}
}
