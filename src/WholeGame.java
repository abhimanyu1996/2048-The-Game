import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class WholeGame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	WholeGame(){
		super("2048 The Game");
		setLayout(new FlowLayout());
		GameGrid gg = new GameGrid();
		Dimension dm = new Dimension(365,365);
		gg.setMinimumSize(dm);
		gg.setMaximumSize(dm);
		gg.setPreferredSize(dm);
		add(gg);
		
		
		 KeyStroke stroke;
		    InputMap inputMap;
		 Action downaal = new AbstractAction() {
		      /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent a) {
		    	  gg.dir("down");
		      }
		};
		    
		stroke = KeyStroke.getKeyStroke("DOWN");
		inputMap = gg.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    inputMap.put(stroke, "downaal");
	    gg.getActionMap().put("downaal", downaal);
		
	    downaal = new AbstractAction() {
		      /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent a) {
		    	  gg.dir("up");
		     }
		};
		   
		stroke = KeyStroke.getKeyStroke("UP");
		inputMap = gg.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(stroke, "upaal");
	    gg.getActionMap().put("upaal", downaal);

	    

	    downaal = new AbstractAction() {
		      /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent a) {
		    	  gg.dir("right");
		    }
		};
		   
		stroke = KeyStroke.getKeyStroke("RIGHT");
		inputMap = gg.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(stroke, "rightaal");
	    gg.getActionMap().put("rightaal", downaal);

	    

	    downaal = new AbstractAction() {
		    
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent a) {
		    	  gg.dir("left");
		      }
		};
		   
		stroke = KeyStroke.getKeyStroke("LEFT");
		inputMap = gg.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(stroke, "leftaal");
	    gg.getActionMap().put("leftaal", downaal);

	
	
	    JButton reset = new JButton("Reset");
	    reset.addActionListener(
	    		new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						gg.reset();
					}
	    			
	    		}
	    		
	    		);
	    
	    add(reset);
	}
	
	public static void main(String arg[]){
		WholeGame wg = new WholeGame();
		
		wg.setSize(410,440);
		wg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wg.setVisible(true);
		wg.setResizable(false);
	}
	
}
