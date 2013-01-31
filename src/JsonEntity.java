import com.alibaba.fastjson.*;
 

public class JsonEntity 
{
	
	@SuppressWarnings("deprecation")
	String getResult(String word) 
	{
		StringBuffer s=new StringBuffer();
//		word转换url编码;
		word = java.net.URLEncoder.encode(word);
//		获取json字符串;
		String string = new HttpConnection().httpRequest(word);

		JSONObject json = JSON.parseObject(string);

		if (json.containsKey("local"))
		{
			JSONArray local = json.getJSONArray("local");
			JSONObject o=(JSONObject)local.get(0);
			JSONArray des = o.getJSONArray("des");
			for (Object x :des)
			{
				JSONObject ox=(JSONObject)x;
				s.append("<strong>"+ox.getString("p")+"&nbsp;"+ox.getString("d")+"</strong><br/>");
			}
			return s.toString();
		} else 
			return "<font color=red>no&nbsp;result;</font>";
	}
	
}