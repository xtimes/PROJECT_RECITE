import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Map.Entry;


public class MainLabel implements ActionListener 
{
	Set entries = null;
	Iterator iterator =null;

//	初始化一个内容文字居中显示的JLabel;
	JLabel jlabel=new JLabel("load...",SwingConstants.CENTER);
    
    public MainLabel(){}
    
	public MainLabel(JWindow window,String words_path,String config_path)
	{	
//  读取words.properties;
		PropertiesTool pt = new PropertiesTool();
		
		Map words_map = pt.readProperties(words_path);
		entries = words_map.entrySet();
		iterator = entries.iterator( );
		
		String config_time = pt.readValue(config_path, "javax.swing.Timer");
		
		
//		jlabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		window.getContentPane().add(jlabel);
		window.getContentPane().setBackground(new java.awt.Color(199,237,204));
		window.setVisible(true);
//		设置循环任务;
		javax.swing.Timer timer = new javax.swing.Timer(Integer.parseInt(config_time), this);
		timer.setInitialDelay(0);
		timer.start();

	}
	
	public  void actionPerformed(ActionEvent ae) 
	{
			if(iterator.hasNext())
			{
				Map.Entry entry = (Entry) iterator.next();
				
				String text = "<html><font color=red size=+1>";
				text += entry.getKey();
				text += "</font>&nbsp;<font color=blue size=+1>";
				text += (String)entry.getValue();
				text += "</font></html>";
				
				jlabel.setText(text);
				
			}else{
				iterator = entries.iterator( );
			}
		
	}
	
	
	
//	转unicode;
	private String toUnicode(String s) 
	{  
		String s1 = "";
		for (int i = 0; i < s.length(); i++)	  
			s1 +="\\u" +  Integer.toHexString(s.charAt(i) & 0xffff);	
		return s1;
	}  

}
	

