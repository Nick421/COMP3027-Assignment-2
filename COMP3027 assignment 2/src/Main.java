import java.util.*;
public class Main {
	
	private static ArrayList<co_ordinates> findDiagonals(int low, int high, students[] students){
		int mid = low + (high-low)/2;
		ArrayList<co_ordinates> diagonalPoints = new ArrayList<co_ordinates>();
		 if(low == high){
			co_ordinates point1 = new co_ordinates(students[low].left, students[low].height);
			co_ordinates point2 = new co_ordinates(students[low].right, 0);
			
			diagonalPoints.add(point1);
			diagonalPoints.add(point2);
			return diagonalPoints;
		}else {
			ArrayList<co_ordinates> l1 = findDiagonals(low, mid, students);
			ArrayList<co_ordinates> l2 = findDiagonals(mid+1, high, students);
			return merge(l1,l2);
		}
	}
	
	private static ArrayList<co_ordinates> merge(ArrayList<co_ordinates> l1, ArrayList<co_ordinates> l2){
		ArrayList<co_ordinates> result = new ArrayList<co_ordinates>();
		int lastHeight1=0;
		int lastHeight2 =0;
		
		while(!(l1.isEmpty() || l2.isEmpty())){
			if( l1.get(0).x < l2.get(0).x ){
				int maxheight = l1.get(0).y;
				if (l1.get(0).y < lastHeight2){
					maxheight = lastHeight2;
				}
				
				lastHeight1 = l1.get(0).y;
				result.add(new co_ordinates(l1.get(0).x,maxheight));
				l1.remove(0);
				
			}else if (l1.get(0).x > l2.get(0).x){
				int maxheight = l2.get(0).y;
				if (l2.get(0).y < lastHeight1){
					maxheight = lastHeight1;
				}
				lastHeight2 = l2.get(0).y;
				result.add(new co_ordinates(l2.get(0).x,maxheight));
				l2.remove(0);
			}else{
				if (l1.get(0).y > l2.get(0).y){
					int maxheight =  l1.get(0).y;
					result.add( new co_ordinates(l1.get(0).x, maxheight) );
				}else{
					int maxheight = l2.get(0).y; 
					result.add( new co_ordinates(l1.get(0).x, maxheight) );
				}
			     
			    lastHeight1 = l1.get(0).y;  
			    lastHeight2 = l2.get(0).y; 
			     
			    l1.remove(0);
			    l2.remove(0);
			}
		}
		while(!l1.isEmpty()){
			   result.add( new co_ordinates(l1.get(0).x, l1.get(0).y) );
			   l1.remove(0);
			  }
		while(!l2.isEmpty()){
			   result.add( new co_ordinates(l2.get(0).x, l2.get(0).y) );
			   l2.remove(0);
			  }
			  
		//Remove Points falling at same height
		for (int i = 0; i < result.size(); i++) {
			   int j = i+1;
			   while(j < result.size() && result.get(i).y == result.get(j).y){
			    result.remove(j);
			   }
		}
		
		return result;
		
	}
	public static void main(String[] args)
	{
		// read the input data into an ArrayList
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		
		students[] slist = new students[n];
		int x = 0;
		for(int i = 1; i <= n; ++i)
		{
			int left = reader.nextInt();
			int right = reader.nextInt();
			int height = reader.nextInt();
			students s = new students(left, right, height);
			slist[x] = s;
			x++;
		}
		reader.close();
		List<co_ordinates> listH = findDiagonals(0,slist.length-1, slist);
		for (co_ordinates c : listH){
			System.out.println(c.x + " " + c.y);
		}
	}
}