package de.htw.beleg2;

public class TopTen {
	private int [] highscore = {0,0,0,0,0,0,0,0,0,0};
	
	
	public void saveHighscore(int val){

		
		for (int i = 0; i < highscore.length; i++){
			if(highscore[i] == 0){
				highscore[i] = val;
				bubblesort();
				return;
			}
		}
		for (int i = highscore.length; i >= 0; i--){
			if(highscore[i] < val){
				highscore[i] = val;
				return;
			}
		}
		return;
		
	}
	
	public int[] getHighscore(){
		return highscore;
	}
	
	
	public void printHighscore(){
		for (int i = 0; i < highscore.length; i++){
			System.out.printf("[%02d]\t--  %05d  --\n", i+1, highscore[i]);
		}
	}
	
	private void bubblesort(){
		// worst bubblesort possible, but should work
		int tmp;
		for (int i = 0; i < highscore.length; i++){
			for (int j = 0; j < highscore.length-1; j++){
				if (highscore[j] < highscore[j+1]){
					tmp = highscore[j];
					highscore[j] = highscore[j+1];
					highscore[j+1] = tmp;
				}
			}
		}
	}
}
