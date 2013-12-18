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
	}
	
	
}
