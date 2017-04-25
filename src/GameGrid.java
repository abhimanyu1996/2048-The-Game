import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GameGrid extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int grid[][] = new int[4][4];
	
	boolean GameOver =false,GameWin = false;
	
	GameGrid(){
		rand2s();
	}
	
	private boolean Evaluate(){
		boolean t = true, f = false;
		
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(grid[i][j]==2048){
					GameWin = true;
					repaint();
					return true;
				}
			}
		}
		
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(grid[i][j]==0){
					t=false;
					break;
				}	
			}
		}
		
		if(t){
			for(int i=0;i<4;i++){
				for(int j=0;j<4;j++){
					if(i==0){
						if(grid[i][j]==grid[i+1][j]){
							f=true;
							break;
						}
					}
					else if(i==3){
						if(grid[i][j]==grid[i-1][j]){
							f=true;
							break;
						}
					}
					else{
						if(grid[i][j]==grid[i+1][j] || grid[i][j]==grid[i-1][j]){
							f=true;
							break;
						}
					}
					
					
					if(j==0){
						if(grid[i][j]==grid[i][j+1]){
							f=true;
							break;
						}
					}
					else if(j==3){
						if(grid[i][j]==grid[i][j-1]){
							f=true;
							break;
						}
					}
					else{
						if(grid[i][j]==grid[i][j+1] || grid[i][j]==grid[i][j-1]){
							f=true;
							break;
						}
					}
					
				}
			}
			
			if(!f)
				GameOver = true;
		}
		
		return false;
	}
	
	private void rand2s(){
		int x;
		int y;
		
		do{
			x = (int )(Math. random() * 4);
			y = (int )(Math. random() * 4);
		}while(grid[x][y]!=0);
		
		grid[x][y]=2;
		
	}
	
	public void dir(String z){
		
		if(!Evaluate())
			switch(z){
				case "down":
					downalgo();
					break;
				case "up":
					upalgo();
					break;
				case "right":
					rightalgo();
					break;
				case "left":
					leftalgo();
					break;
					
				default:
					break;
			}
		
		repaint();
	}
	
	private void downalgo(){
		boolean flag=false;
		
		for(int j=0;j<4;j++){
			if(downreplace(3,j)==true)
				flag=true;
		}
		
		if(flag==true)
			rand2s();
	}
	
	private boolean downreplace(int i,int j/*,boolean t*/){
		
		int x=i-1,temp;
		boolean flag = false;
		
		while(x>=0){
			if(grid[x][j]!=0){
				break;
			}
			x--;
		}
		
		while(x>=0){
			if(grid[i][j]==0){
				temp=grid[x][j];
				grid[x][j]=0;
				grid[i][j]=temp;
				x=x-1;
				flag=true;
			}
			else if(grid[i][j]==grid[x][j]){
				grid[i][j]*=2;
				grid[x][j]=0;
				i=i-1;
				x=x-1;
				flag=true;
			}
			else if(grid[i][j]!=grid[x][j]){
				
				if(x!=i-1)
					flag=true;
				
				temp = grid[x][j];
				grid[x][j]=0;
				grid[i-1][j] = temp;
				i=i-1;
				x=x-1;
			}
			
			while(x>=0){
				if(grid[x][j]!=0){
					break;
				}
				x--;
			}
		}
		
		return flag;
	}
	
	private void upalgo(){
		boolean flag = false;
		for(int j=0;j<4;j++){
			if(upreplace(0,j)==true)
				flag = true;
		}
		
		if(flag==true)
			rand2s();
	}
	
	private boolean upreplace(int i,int j){
		int x=i+1,temp;
		boolean flag = false;
		
		while(x<=3){
			if(grid[x][j]!=0){
				break;
			}
			x++;
		}
		
		while(x<=3){
			if(grid[i][j]==0){
				temp=grid[x][j];
				grid[x][j]=0;
				grid[i][j]=temp;
				x=x+1;
				flag = true;
			}
			else if(grid[i][j]==grid[x][j]){
				grid[i][j]*=2;
				grid[x][j]=0;
				i=i+1;
				x=x+1;
				flag = true;
			}
			else if(grid[i][j]!=grid[x][j]){
				
				if(x!=i+1)
					flag = true;
				
				temp = grid[x][j];
				grid[x][j]=0;
				grid[i+1][j] = temp;
				i=i+1;
				x=x+1;
			}
			
			while(x<=3){
				if(grid[x][j]!=0){
					break;
				}
				x++;
			}
		}
		
		return flag;
	}
	
	private void rightalgo(){
		boolean flag = false;
		for(int i=0;i<4;i++){
			if(rightreplace(i,3)==true)
				flag = true;
		}
		
		if(flag==true)
			rand2s();
	}
	
	private boolean rightreplace(int i,int j){
		int x=j-1,temp;
		boolean flag = false;
		
		while(x>=0){
			if(grid[i][x]!=0){
				break;
			}
			x--;
		}
		
		while(x>=0){
			if(grid[i][j]==0){
				temp=grid[i][x];
				grid[i][x]=0;
				grid[i][j]=temp;
				x=x-1;
				flag = true;
			}
			else if(grid[i][j]==grid[i][x]){
				grid[i][j]*=2;
				grid[i][x]=0;
				j=j-1;
				x=x-1;
				flag = true;
			}
			else if(grid[i][j]!=grid[i][x]){
				
				if(x!=j-1)
					flag = true;
				
				temp = grid[i][x];
				grid[i][x]=0;
				grid[i][j-1] = temp;
				j=j-1;
				x=x-1;
			}
			
			while(x>=0){
				if(grid[i][x]!=0){
					break;
				}
				x--;
			}
		}
		
		return flag;
	}
	
	private void leftalgo(){
		boolean flag = false;
		for(int i=0;i<4;i++){
			if(leftreplace(i,0)==true)
				flag = true;
		}
		
		if(flag==true)
			rand2s();
	}
	
	private boolean leftreplace(int i,int j){
		int x=j+1,temp;
		boolean flag = false;
		
		while(x<=3){
			if(grid[i][x]!=0){
				break;
			}
			x++;
		}
		
		while(x<=3){
			if(grid[i][j]==0){
				temp=grid[i][x];
				grid[i][x]=0;
				grid[i][j]=temp;
				x=x+1;
				flag = true;
			}
			else if(grid[i][j]==grid[i][x]){
				grid[i][j]*=2;
				grid[i][x]=0;
				j=j+1;
				x=x+1;
				flag = true;
			}
			else if(grid[i][j]!=grid[i][x]){
				
				if(x!=j+1)
					flag = true;
				
				temp = grid[i][x];
				grid[i][x]=0;
				grid[i][j+1] = temp;
				j=j+1;
				x=x+1;
			}
			
			while(x<=3){
				if(grid[i][x]!=0){
					break;
				}
				x++;
			}
		}
		
		return flag;
	}
	
	public void reset(){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				grid[i][j]=0;
			}
		}
		GameOver = false;
		GameWin = false;
		
		rand2s();
		repaint();
	}
	
	private Color boxColor(int x){
		Color c;
		
		switch(x){
			case 2: 
				c = new Color(255,255,102);
				break;
			case 4:
				c = new Color(255,255,70);
				break;
			case 8:
				c = new Color(255,255,40);
				break;
			case 16:
				c = new Color(255,255,10);
				break;
			case 32:
				c = new Color(255,240,0);
				break;
			case 64:
				c = new Color(255,200,0);
				break;
			case 128:
				c = new Color(255,160,0);
				break;
			case 256:
				c = new Color(255,120,0);
				break;
			case 512:
				c = new Color(255,80,0);
				break;
			case 1024:
				c = new Color(255,40,0);
				break;
			case 2048:
				c = new Color(255,0,0);
				break;
			
			default:
				c = Color.GRAY;
				break;
		}
		return c;
	}
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(0, 0, 365, 365);
		
		int y ,x;
		FontMetrics fm;
		Font myFont;
		
		myFont = new Font ("Courier New", 1, 20);
		g2d.setFont(myFont);
		fm = g2d.getFontMetrics();
		
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				g2d.setColor(boxColor(grid[j][i]));
				g2d.fillRoundRect(90*i+5, 90*j+5,85 ,85, 25, 25);
				
				if(grid[j][i] != 0){
		            x = (95 - fm.stringWidth(""+grid[j][i])) / 2;
		            y = ((95 - fm.getHeight()) / 2) + fm.getAscent();
					g2d.setColor(Color.BLACK);
					g2d.drawString(""+grid[j][i], 90*i+x,90*j+y);
				}
			}
		}
		
		if(GameWin == true){
			myFont = new Font ("Courier New", 1, 40);
			g2d.setFont(myFont);
			fm = g2d.getFontMetrics();
            x = (getWidth() - fm.stringWidth("You Won")) / 2;
            y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
            g2d.setColor(Color.BLUE);
            g2d.drawString("You Won",x,y);
            return;
		}
		
		if(GameOver == true){
			myFont = new Font ("Courier New", 1, 50);
			g2d.setFont(myFont);
			fm = g2d.getFontMetrics();
            x = (getWidth() - fm.stringWidth("Game Over")) / 2;
            y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
            g2d.setColor(Color.blue);
            g2d.drawString("Game Over",x,y);
            
		}
	}
}
