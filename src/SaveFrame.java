
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class SaveFrame extends javax.swing.JDialog 
{
	private JTextField sword = new JTextField();
	private JTextPane sresult = new JTextPane();
	
	public SaveFrame() { }
 
	public SaveFrame( JFrame frame, JTextPane word ,JTextPane result)
	{
		
		super(frame,"save");
		this.setModal(true);
		this.setSize(500,150);
		this.setResizable(false);
		
//		this.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
//		居中;
		this.setLocationRelativeTo(frame);
		
		String word_text = word.getText();
		String result_text = result.getText();
		
		int x = result_text.indexOf("<strong>");
		int y = result_text.indexOf("</strong>");
		result_text = result_text.substring( x+("<strong>").length(), y);
		
		sword.setText(word_text);
		sresult.setContentType("text/html");
		sresult.setText("<strong>"+result_text+"</strong>");
		
		JButton confirm = new JButton("confirm");
		JButton cancel = new JButton("cancel");
		
		ActionListener listener = new SaveFrameListener();
		confirm.addActionListener(listener);
		cancel.addActionListener(listener);
	
//		布局;
		 GroupLayout groupLayout = new GroupLayout(getContentPane());
		 groupLayout.setHorizontalGroup(
		 	groupLayout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addContainerGap()
		 			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		 				.addComponent(sresult, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
		 				.addComponent(sword, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
		 				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
		 					.addComponent(cancel)
		 					.addPreferredGap(ComponentPlacement.RELATED)
		 					.addComponent(confirm)))
		 			.addContainerGap())
		 );
		 groupLayout.setVerticalGroup(
		 	groupLayout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addContainerGap()
		 			.addComponent(sword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addComponent(sresult, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
		 			.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		 			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
		 				.addComponent(confirm)
		 				.addComponent(cancel))
		 			.addContainerGap())
		 );
		 this.getContentPane().setLayout(groupLayout);
	}

	private class SaveFrameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae) 
		{
			if ( "cancel".equalsIgnoreCase ( ae.getActionCommand()) )
				SaveFrame.this.setVisible(false);
			if ( "confirm".equalsIgnoreCase ( ae.getActionCommand()) )
			{
				String key = sword.getText();
				String value = sresult.getText();
				
				if ( value.contains("<strong>") )
				{
					int index = value.indexOf("<strong>") + "<strong>".length();
					value = value.substring(index);
					if ( value.length()>150 )
						value = value.substring(0, 150)+"...";
				}	
					
				new PropertiesTool().writeProperties(Constant.words_path, key, value);
				SaveFrame.this.setVisible(false);
			}
		}
	
	}

}