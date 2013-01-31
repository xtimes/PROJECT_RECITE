import javax.swing.*;
import java.awt.event.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class SearchFrame extends javax.swing.JFrame implements ActionListener
{
	JLabel save = new JLabel();
	JLabel search = new JLabel();
	JTextPane word = new JTextPane();
	JTextPane result = new JTextPane();
	
	public SearchFrame(){ init();}
	
	private void init()
	{
		java.awt.Color color = new java.awt.Color(199,237,204);
		this.getContentPane().setBackground(color);
		
		this.setTitle("search");
		this.setBounds(0, 0, 399, 299);
//		居中;
		this.setLocationRelativeTo(null);
//		布局;
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		word.setBackground(color);
		result.setBackground(color);
		result.setEditable(false);
	
//		word.setContentType("text/html");
		result.setContentType("text/html");
//		System.out.println(result.getText());
		save.setText("<html><a href=blank>save</a></html>");
		search.setText("<html><a href=blank>search</a></html>");
		
		search.addMouseListener( new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent me) 
			{
				String input = word.getText();
				if(	input.trim().length()!=0 )
					result.setText(new JsonEntity().getResult(input));
				else
					word.setText("input a word");
			}
		});
		
		save.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) 
			{
				new SaveFrame(SearchFrame.this, word, result).setVisible(true);
			}
		});
		
//		输入完毕word后,enter键执行查询`按钮事件;
//		word.addKeyListener(new KeyAdapter() {
//			public void keyReleased(KeyEvent ke)
//			{
//				if(KeyEvent.VK_ENTER == ke.getKeyCode())
//				{
//					String input = word.getText();
//					if(	input.trim().length()!=0 )
//						result.setText(new JsonEntity().getResult(input));
//					else
//						word.setText("input a word");
//				} 
//			}
//		});
		
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(result, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(word, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(search)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(save)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(word, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(search, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(save, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(result, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
					.addContainerGap())
		);
		this.getContentPane().setLayout(groupLayout);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) 
	{
		new SearchFrame().setVisible(true);
	}
}
