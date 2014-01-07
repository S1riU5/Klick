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
		 * @param int width		WIdth of the board
		 * @param int cols		Amount of the Colors 
		 */
		board = new Board(width, cols);
		// Never build an empty board!
		fillBoard(cols);
	}
	
	private  void fillBoard(int colors){
		for (int i = 0; i < board.getWidth(); i++){
			for (int j = 0; j < board.getHeight(); j++){
				// Testcase for filling-method 
				if (j == 2 && i == 4)
					board.area[i][j] = 0;
				// Integer randomized value 
				// from 1 to the value of colors
				else
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
	
	public class Board{
		/**
		 * Boardclass
		 */
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
			setColors(cols);
			for (int i = 0; i<getWidth(); i++){
				for(int j = 0; j<getWidth(); j++ ){
					area[i][j] = randInt(1, cols);
				}
			}
		}
		
		public int getValAt(int x, int y){
			//FIXEDME IndexOutOfBoundsException
			//TODO May the problem don't occur anymore, but keep it in sight
			// Maybe there is a Problem in parsing the chars t, , l, r
			// to the actual direction..
			// Switched them yet but nothing changed.
			// but its the call from:
			// >> sameValue() <<
			//System.out.printf("x:\t%s\ty:\t%s", x, y); //debug
			return area[x][y];
		}
		
		public void deleteEqualNeighbors(int x, int y){
			
			//exit conditions first!
			if (valueOfEqualNeighbors(x,y) == 0){
				//System.out.printf("No Equal neighbors!\n"); //debug
				deleteElement(x,y);
				return;
			}
			if (getValAt(x,y) == 0)
				return;
			//TODO Recursive deleting of equal neighbors
			int currentValue = getValAt(x,y);
			deleteElement(x,y);
			// Delete here first 
			// if not philosophers don't eat the pasta.
			//TODO testing & debugging	
			if (sameValue(currentValue, x,y,'t')){
				System.out.printf("Mooving cursor to: top (%sx%s)\n", x, y-1);
				deleteEqualNeighbors(x  , y-1);
			}
			if (sameValue(currentValue, x,y,'r')){
				System.out.printf("Mooving cursor to: right(%sx%s)\n", x+1,y);
				deleteEqualNeighbors(x+1 , y);
			}
			if (sameValue(currentValue, x,y,'b')){
				System.out.printf("Mooving cursor to: bottom(%sx%s)\n" ,x, y+1);
				deleteEqualNeighbors(x  , y+1);
				}
			if (sameValue(currentValue, x,y,'l')){
				System.out.printf("Mooving cursor to: left(%sx%s)\n", x-1,y);
				deleteEqualNeighbors(x-1, y);
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
			// TODO Zerospaces shouldn't handled at all
			// Where is the big red button which will turn this off? 
			// Neighbors of deleted Areas may be not worth looking at
			if (getValAt(x,y) == 0)
				return 0;
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
	
			// Directions have to be very implicit!
			// not really necessary but does also no harm
			// and who knows what will happen 
			if (	direction != 'l' &&
					direction != 'r' &&
					direction != 'b' &&
					direction != 't')
				throw new IllegalStateException("Unknown direction");
	
			System.out.printf("%s\n" , direction); //debug
			// If the earth a disc, be aware of the abyss!
			if (	(x == 0 					&& direction == 'l')|| 
					(x >= this.getWidth()-1 	&& direction == 'r')||
					(y == 0						&& direction == 't')||
					(y >= this.getHeight()-1  	&& direction == 'b'))
				return false;
			//System.out.printf("not at border\n");
			int valueThere;
			
			switch (direction){
			//Are the relative coordinates right this way??
			case 'b':
				valueThere = getValAt(x  , y+1);
				break;
			case 'r':
				valueThere = getValAt(x+1, y);
				break;
			case 't':
				valueThere = getValAt(x  , y-1);
				break;
			default:
				valueThere = getValAt(x-1, y);
			}
			//return true if equal
			System.out.printf("%s=%s => %s\n"  //
					,valueHere, valueThere, valueHere == valueThere); 
			return (valueHere == valueThere);	
		}

		public int getColors() {
			return colors;
		}

		public void setColors(int colors) {
			this.colors = colors;
		}
		
		public void deleteElement(int x, int y){
			System.out.printf("deleting Element: %s; %s with Value: %s\n", 
					x,y,getValAt(x,y));
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
				System.out.printf("gap found at position %s x %s.\n", gapCursor[0], gapCursor[1]);
				if (gapCursor[0] == -1){
					finished = true;
				}
				else{
					count += 1;
					if (!sameValue(0, gapCursor[1], gapCursor[0], 't')){
						fillVerticalGap(gapCursor);
					}
				}
				
			}while(!finished);
		}
		
		private int[] findGap(int count){
			/**
			 * findGap returns the position first gap
			 * 
			 * @return int[] x,y position of the first gap; if it returns null -> no gap found
			 */
			int[] cursor = new int[2];
			cursor[0] = -1;
			cursor[1] = -1;
			// The first row we don't need.
			for(int i = 1; i < this.getWidth(); i++){
				for (int j = 0; j < this.getHeight(); j++){
					// If Gap and top of position is not 
					if(this.getValAt(i, j) == 0 && !sameValue(0, i,j, 't')){
						if (count > 0){
							count -= 1;
							continue;
						}
						// yea! A gap is there. Will give you the position rapidly!
						cursor[1] = i;
						cursor[0] = j;
						return cursor;
					}
				}
			}
			//finally return the empty cursor.
			return cursor;	
		}
		
		private void fillVerticalGap(int[] cursor){
			int valueAboveGap;
			
			if (sameValue(0, cursor[0]+1, cursor[1], 'b') &&
					cursor[1] < this.getHeight()){
				
				cursor[1] = cursor[1]+1;
				fillVerticalGap(cursor);
			}
			for (int i = cursor[1]; i > 0 ; i--){
				valueAboveGap = getValAt(i, cursor[0]);
				if ( valueAboveGap != 0){
					// Save value above gap at cursor-position
					this.area[cursor[0]][cursor[1]] = valueAboveGap;
					// Delete the value above Gap
					this.area[i][cursor[0]] = 0;
				}
			}
		}
		
		
		
		private void success( ){
			System.exit(0);
		}
	}
	
	
}
