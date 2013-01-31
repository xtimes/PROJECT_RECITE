import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

public class PropertiesTool 
{
//	转换字符;
	private String toUTF(String string) throws UnsupportedEncodingException
	{
		return new String ( string.getBytes("ISO-8859-1"),"utf8" ); 
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
// 	读取属性文件;
	public Map readProperties(String filePath) 
	{
		Map map = new HashMap<String, String>(); 
		Properties props = new Properties();
		
		try {
			InputStream in = this.getClass().getResourceAsStream(filePath);
			props.load(in);
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) 
			{
				String key = (String) en.nextElement();
				String value = props.getProperty(key);
				
				map.put( toUTF(key), toUTF(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	// 根据key读取value;
	public String readValue(String filePath, String key) 
	{
		Properties props = new Properties();
		try {
			InputStream in = this.getClass().getResourceAsStream(filePath);
			props.load(in);
			String value = props.getProperty(key);
//			System.out.println(key +"\t"+ value);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 写属性文件;
	public void writeProperties(String filePath, String parameterName,String parameterValue) 
	{
		Properties prop = new Properties();
		try {
			InputStream fis = this.getClass().getResourceAsStream(filePath);
			// 从输入流中读取属性列表(键和元素对);
			prop.load(fis);
			// 调用 Hashtable 的方法 put,使用 getProperty 方法提供并行性;
			// 强制要求为属性的键和值使用字符串,返回值是 Hashtable 调用 put 的结果;
			java.net.URI uri = this.getClass().getResource(filePath).toURI();
			FileOutputStream fos = new FileOutputStream(new File(uri)) ;
			prop.setProperty(parameterName, parameterValue);
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			prop.store(fos, "comments");
			
			fos.flush();
			fos.close();
			fis.close();
			
		} catch (IOException e) {
			System.err.println("Visit " + filePath + " for updating "
					+ parameterName + " value error");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
