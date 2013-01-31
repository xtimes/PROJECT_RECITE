import java.io.*;
import java.awt.*;
import com.sun.awt.*;
import javax.swing.*;
import java.net.*;
import java.awt.event.*;


//在程序中依次设置以下几个参数：  
//设置窗口完全透明：AWTUtilities.setWindowOpaque(frame, false);   
//设置窗口无边缘：frame.setUndecorated(true);   
//设置窗口的ContentPane为要显示的Pane：frame.setContentPane(myPane);   
//在myPane中放置具体要显示的内容，也可以重载paint方法进行Java2D绘制。这些paint会直接发生在桌面背景上。 

@SuppressWarnings("serial")

public class MainFrame extends javax.swing.JWindow
{
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					JWindow frame =new MainFrame();
//					设置窗体透明度;
					AWTUtilities.setWindowOpacity(frame, 0.80F);
					new MainLabel(frame,Constant.words_path,Constant.config_path);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JPanel contentPane = new JPanel();      
	private JPopupMenu popupMenu = new JPopupMenu();
	
	public MainFrame() 
	{
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
//		设置JFrame窗口无边缘;
//		this.setUndecorated(true);  
		this.setBounds(938,117,378,30);
//      窗口置前;
        this.setAlwaysOnTop(true);		
    
        this.setContentPane(contentPane);
//      添加右键JPopupMenu菜单列;
        for( JMenuItem x: createMenuItems())
        	popupMenu.add(x);
        
        this.addMouseListener( mouseListener(this,popupMenu));
        this.addMouseMotionListener( mouseListener(this,popupMenu));
        
//	     是否支持显示托盘图标;       
	       if (java.awt.SystemTray.isSupported())
	       {  
	    	 try 
	    	 {      	        	
//	 			通过静态方法getSystemTray()得到系统托盘
	       		SystemTray tray = SystemTray.getSystemTray();
//	 			装载托盘图象
	    		URL url=this.getClass().getResource(Constant.tray_path);
	        	Image image = Toolkit.getDefaultToolkit().getImage(url);
	        	JXTrayIcon jxtray=new JXTrayIcon(image);
	        	jxtray.setToolTip("copyright cpp;");
	        	jxtray.setJPopupMenu(popupMenu);
				tray.add (jxtray);
				
	    	 } catch (AWTException e) {
					e.printStackTrace();
				}
	        }
	}
	
//	创建右键弹出菜单列表;
	private JMenuItem[] createMenuItems()
	{
		JMenuItem jmi_search = new JMenuItem("search");
		JMenuItem jmi_version = new JMenuItem("version");
		JMenuItem jmi_config = new JMenuItem("config");
		JMenuItem jmi_destroy = new JMenuItem("destroy");
//	设置菜单项监听事件;
		jmi_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new SearchFrame().setVisible(true);
			}
		});
		
		jmi_config.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
			}
		});
		
		jmi_version.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
			}
		});
		
		jmi_destroy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});
		return new JMenuItem[]{jmi_search,jmi_config,jmi_version,jmi_destroy};
	}
	
//	鼠标事件监听;
	private Point lastPoint = null;
	
	private MouseAdapter mouseListener(final Component component, final JPopupMenu popup)
	{		
		return new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
			       lastPoint = e.getLocationOnScreen();  
//			       System.out.println(lastPoint); 
			       
				if ( e.isPopupTrigger())
					popup.show(e.getComponent(), e.getX(), e.getY());
			}
			
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				if ( e.isPopupTrigger())
					popup.show(e.getComponent(), e.getX(), e.getY());
			}
			
			@Override
			public void mouseDragged(MouseEvent e) 
			{
//		        System.out.println(e);  
		        Point point = e.getLocationOnScreen(); 
		        
		        int offsetX = point.x - lastPoint.x;  
		        int offsetY = point.y - lastPoint.y;  
		          
		        Rectangle bounds = component.getBounds();  
		        bounds.x += offsetX;  
		        bounds.y += offsetY;  
		        component.setBounds(bounds);  
		        lastPoint = point;  
			}
		};
	}
	
//	资源文件路径;
	public String getResource(String path) throws IOException
	{  
//	返回读取指定资源的输入流  
		InputStream is=this.getClass().getResourceAsStream(path);   
		BufferedReader br=new BufferedReader(new InputStreamReader(is));  
		String s="";  
		while((s=br.readLine())!=null)  
		    System.out.println(s);
		return s;  
	}  

}