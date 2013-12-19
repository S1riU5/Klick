package de.htw.beleg2;


public class Game {
/**
 * 
 */
	Board board;
	
	public Game(int height, int width, int cols){
		board = new Board(width, cols);
		fillBoard(cols);
	}
	
	private  void fillBoard(int colors){
		for (int i = 0; i < board.getWidth(); i++){
			for (int j = 0; j < board.getHeight(); j++){
				board.area[i][j] = randInt(1, colors);
			}
		}	
	}
	
	private int randInt( int low, int high ){
	
		return low + (int)(Math.random() * ((high - low) + 1));
	}
	
	
	
	public int getColor(int x, int y){
		if (board.getHeight() == 0 || board.getWidth() == 0)
				throw new IllegalStateException("Spielfeld noch nicht initialisiert!");
		else
			return  board.getValAt(x, y);
	}
	

	
	public class Board{
		private int [][] area;
		private int colors; // 0 = empty 
		
		public Board(int width, int height, int cols){
			area = new int[width][height];
			initField(cols);
		}
		
		public Board(int size, int cols) {
			area = new int[size][size];
			initField(cols);
		}
		
		public int getWidth(){
			return area.length;
		}
		
		public int getHeight(){
			return area[0].length;
		}
		
		private void initField(int cols){
			/*
			 * initialize the values of the fields
			 */
			colors = cols;
			for (int i = 0; i<getWidth(); i++){
				for(int j = 0; j<getWidth(); j++ ){
					area[i][j] = randInt(1, cols);
				}
			}
		}
		
		public int getValAt(int x, int y){
			return area[x][y];
		}
		
		public int howManyNeighbors(int x, int y){
			int value = 0;
			String[] nbrs = {"top", "right", "bottom", "left"};
			
			for (int i = 0; i < nbrs.length; i++){
				if (sameValue(x,y, nbrs[i]))
					value += 1;
			}
			return value;
		}
		
		private boolean sameValue(int x, int y, String direction){
			//if the earth a discb, be aware of the abyss!
			if (	(x == 0 				&& direction == "left")  || 
					(x == this.getWidth() 	&& direction == "right") ||
					(y == 0					&& direction == "bottom")||
					(y == this.getHeight()  && direction == "top"))
				return false;
			
			int valueHere = getValAt(x,y);
			int valueThere;
			
			switch (direction){
			//Are the relative coordinates right this way??
			case "top":
				valueThere = getValAt(x,y+1);
				break;
			case "right":
				valueThere = getValAt(x+1,y);
				break;
			case "bottom":
				valueThere = getValAt(x,y-1);
				break;
			default:
				valueThere = getValAt(x-1, y);			
			}
			//return true if equal
			return (valueHere == valueThere);
			
		}
	}
	
	
}
