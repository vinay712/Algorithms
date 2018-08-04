
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		String[] grid = new String[n];
		for (int grid_i = 0; grid_i < n; grid_i++) {
			grid[grid_i] = in.next();
		}
		int result = twoPluses(grid,n, m);
		System.out.println(result);
		in.close();
	}

	private static int twoPluses(String[] grid, int n, int m) {
		List<Plus> allPlus=new ArrayList<>();
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				char c=grid[i].charAt(j);
				if(c=='G'){
					addPlus(i,j,n,m,grid,allPlus);
				}
			}
		}
		int max=0;
		for(int i=0;i<allPlus.size();i++){
			for(int j=i+1;j<allPlus.size();j++){
				int area=allPlus.get(i).getArea()*allPlus.get(j).getArea();
				if(area>max && !allPlus.get(i).overlaps(allPlus.get(j))){
					max=area;
				}
			}
		}
		return max;
	}
	
	
	private static void addPlus(int r, int c, int n, int m, String[] grid, List<Plus> allPlus) {
		int length=0;
		do{
			allPlus.add(new Plus(r,c,length));
			length++;
			if(!(r-length>=0 && c-length>=0 && r+length<n && c+length<m)){
				break;
			}
			if(grid[r].charAt(c-length)=='B' ||grid[r].charAt(c+length)=='B' ||grid[r-length].charAt(c)=='B' ||grid[r+length].charAt(c)=='B' ){
				break;
			}
		}while(true);
	}


	private static class Plus{
		int row;
		int col;
		int length;
		public Plus(int row,int col,int length){
			this.row=row;
			this.col=col;
			this.length=length;
		}
		public int getArea(){
			return 4*(length)+1;
		}
		public String toString(){
			return "("+row+","+col+","+length+")";
		}
		public boolean overlaps(Plus obj){
			int diffRow=Math.abs(this.row-obj.row);
			int diffCol=Math.abs(this.col-obj.col);
			int maxlength=Math.max(this.length, obj.length);
			int minlength=Math.min(this.length, obj.length);
			int totalLen=this.length+obj.length+2;
			if((diffRow==0 && diffCol+1<totalLen) || (diffCol==0 && diffRow+1<totalLen) ){
				return true;
			}
			if((diffRow<=minlength && diffCol<=maxlength) || (diffCol<=minlength&&diffRow<=maxlength)){
				return true;
			}
			return false;
		}
	}
}
