package de.htw.beleg2;
import static java.lang.Math.random;


public class Game {
/**
 * class Game
 * 
 * Includes thge logical part of the game.
 * @include board   (class)
 */
	Board board;
	
	public Game(int height, int width, int cols){
		/**
		 * Game
		 * 
		 * Game-engine for the logical stuff of this game.
		 * 
		 * @param int height	Height of the board
		 * @param int width		Width of the board
		 * @param int cols		Amount of the Colors 
		 */
		board = new Board(height, width, cols);
	}
	
	private  void fillBoard(int colors){
		/**
		 * filloard
		 * 
		 * Fills the board with randomized values
		 * 
		 * @param int colors: Amount of colors
		 */
		for (int i = 0; i < board.getWidth(); i++){
			for (int j = 0; j < board.getHeight(); j++){
				board.area[i][j] = randInt(1, colors);
			}
		}	
	}
	
	private int randInt( int low, int high ){
		/**
		 * randInt
		 * 
		 * Simple Method to get randomized Integer inside 
		 * freely selectable range.
		 * 
		 * @param int low	lowest possible Value
		 * @param int high	highest possible Value
		 * @return int Randomized Integer
		 */
		return low + (int)(random() * ((high - low) + 1));
	}
	
	public int getColor(int x, int y){
		/**
		 * getColor
		 * 
		 * getter for the Colorvalue of a space of the board
		 * 
		 * @param int x		value of columns 
		 * @param int y		value of rows
		 * @returns int 	value refers to a color
		 */
		if (board.getHeight() == 0 || board.getWidth() == 0)
				throw new IllegalStateException(
						"Spielfeld noch nicht initialisiert!");
		else
			return  board.getValAt(x, y);
	}
	
	private void success( ){
		System.exit(0);
	}
	
	public class Board{
		/**
		 * Boardclass
		 */
		private int [][] area;
		
		 public Board(int height, int width, int cols){
			/**
			 * Board __init__
			 * 
			 * @param height 
			 * @param width
			 * @param cols
			 */
			System.out.printf("height: %s\t width: %s\tColors: %s\n", height, width, cols); // debug
			this.area = new int[height][width];
			//System.out.printf("%s\n", this.area[0].length); //debug
			initField(cols);
		}


		private void initField(int cols){
			/**
			 * initialize the values of the spaces
			 */
			for (int i = 0; i<getHeight(); i++){
				for(int j = 0; j<getWidth(); j++ ){
					area[i][j] = randInt(1, cols);
				}
			}
		}
		
		public int getValAt(int x, int y){
			//FIXEDME IndexOutOfBoundsException
			//TODO May the problem don't occur anymore, but keep it in sight
			// Maybe there is a Problem in parsing the chars t,b , l, r
			// to the actual direction..
			// Switched them yet but nothing changed.
			// but its the call from:
			// >> sameValue() <<
			
			//System.out.printf("x:\t%s\ty:\t%s", x, y); //debug
			return area[x][y];
		}
		
		public void deleteEqualNeighbors(int x, int y){
			/*
			 * deleteEqualNeighbors
			 * 
			 * 
			 */
			// Exit conditions first!
			if (valueOfEqualNeighbors(x,y) == 0){
				deleteElement(x,y);
				return;
			}
			// Speed Up! 
			//0 isn't part of the game, so you haven't to handle it.
			if (getValAt(x,y) == 0)
				return;
			/*
			 * Begin of the Algorithm.
			 * 
			 * 
			 */
			
			boolean left = false, right = false, top = false, bottom = false ;
			int currentValue = getValAt(x,y);
			
			if (sameValue(currentValue, x,y,'t')){
				top = true;
			}
			if (sameValue(currentValue, x,y,'b')){
				bottom = true;
			}
			if (sameValue(currentValue, x,y,'l')){
				left = true;
			}
			if (sameValue(currentValue, x,y,'r')){
				right = true;
			}
			/*
			 * 
			 */
			deleteElement(x,y);	
			/*
			 * 
			 */
			if (top){
				deleteEqualNeighbors(x-1  , y);
			}
			if (right){
				deleteEqualNeighbors(x , y+1);
			}
			if (bottom){
				deleteEqualNeighbors(x+1  , y);
			}
			if (left){
				deleteEqualNeighbors(x, y-1);
			}
		}

		public int valueOfEqualNeighbors(int x, int y){
			/**
			 * valueOfEqualNeighbors
			 * 
			 * Get the amount of the equal Neighbors.
			 * @param int x		column
			 * @param int y		row
			 * @return int	number of equal Neighbors (possible: 0 - 4)
			 */			
			// Where is the big red button which will turn this off? 
			// Neighbors of deleted Areas may be not worth looking at
			if (getValAt(x,y) == 0){
				return 0;
			}
			int value = 0;
			
			char[] nbrs = {'t', 'r', 'b', 'l'};
			for (int i = 0; i < nbrs.length; i++){
				if (sameValue(getValAt(x,y),x,y, nbrs[i]))
					value += 1;
			}
			return value;
		}
		
		private boolean sameValue(int valueHere, int x, int y, char direction)
		throws IllegalStateException{
			/**
			 * sameValue
			 * 
			 * Compares two bordering spaces.
			 * 
			 * @param int x		column
			 * @param int y		row
			 * @param String direction 
			 * (possible: "t",  "r", "b", "l")
			 * @return boolean	are the two spaces equal
			 * @throws IllegalStateException	
			 * if direction is not valid
			 */	
	
			
			
			// If the earth a disc, be aware of the abyss!
			if (	(y == 0 					&& direction == 'l')|| 
					(y >= this.getWidth()-1 	&& direction == 'r')||
					(x == 0						&& direction == 't')||
					(x >= this.getHeight()-1  	&& direction == 'b'))
				return false;
			//System.out.printf("not at border\t"); //debug
			int valueThere;
			//System.out.printf("x: %s\t direction: %s\n", x, direction);
			switch (direction){
			case 'b':		
				//System.out.printf("/t/t%s", this.getHeight());//debug
				valueThere = getValAt(x+1  , y);
				break;
			case 'r':
				valueThere = getValAt(x, y+1);
				break;
			case 't':
				valueThere = getValAt(x-1  , y);
				break;
			default:
				valueThere = getValAt(x, y-1);
			}
			//return true if equal
			//System.out.printf("\t%s=%s => %s\n",valueHere, valueThere, valueHere == valueThere); //debug
			return (valueHere == valueThere);	
		}


		public void deleteElement(int x, int y){
			this.area[x][y] = 0;
		}
		
		private boolean boardEmpty(){
			/**
			 * empty
			 * 
			 * @return boolean if the whole Board empty.
			 */
			for (int i = 0; i<this.getWidth()-1; i++){
				for (int j = 0; j<this.getHeight()-1; j++){
					if (getValAt(i,j) != 0)
						return (false);
				}
			}
			return (true);
		}
		
		public void fillGaps(){
			boolean finished = false;
			int count = 0;
			do{
				int[]gapCursor = findGap(count);
				if (gapCursor[0] == -1 ){
					finished = true;
				}
				else{
					count += 1;
					if (!sameValue(0, gapCursor[1], gapCursor[0], 't')){
						fillGap(gapCursor);
						//count = 0;
					}
				}
				
			}while(!finished);
			
			for (int i = 1; i < this.getWidth(); i++){
				if (this.getValAt(this.getHeight()-1, i) 		== 0 && 
					this.getValAt(this.getHeight()-1, i-1) 	!= 0) {
					fillVerticalGap(i);
				}
			}
		}
		
		private int[] findGap(int count){
			/**
			 * findGap returns the position first gap
			 * 
			 * @return int[] x,y position of the first gap; 
			 * if it returns -1 -> no gap found
			 */
			int[] cursor = new int[2];
			// -1 errorcode
			cursor[0] = -1;
			cursor[1] = -1;
			// The first row we don't need.
			// If there are gaps they won't be filled
			for(int i = 1; i < this.getHeight(); i++){
				for (int j = 0; j < this.getWidth(); j++){
					//System.out.printf("findGap");
					// If Gap and top of position is not 
					if(this.getValAt(i, j) == 0 && !sameValue(0, i,j, 't')){
						if (count > 0){
							count -= 1;
							continue;
						}
						// yea! A gap is there. Will give you the position rapidly!
						cursor[0] = i;
						cursor[1] = j;
						return cursor;
					}
				}
			}
			//finally return the empty (-1) cursor.
			return cursor;	
		}
		
		private void fillGap(int[] cursor){
			int x = cursor[0];
			int y = cursor[1];			
			
			while (this.sameValue(0, x, y, 'b') && x < this.getHeight() ){
				x += 1;
			}
			for (int i = x; i > 0; i--){
				this.area[i][y] = getValAt(i-1, y);
			}
			this.area[0][y] = 0;
		}
		
		private void fillVerticalGap(int x){
			for (int i = 0; i<this.getHeight(); i++){
				for (int j = x; j > 0; j--){
					this.area[i][j] = this.area[i][j-1];
				}
				this.area[i][0] = 0;
			}
		}
		
		public int getHeight(){
			return area.length;
		}
		
		public int getWidth(){
			return area[0].length;
		}
		
		

	}
	
	
}
