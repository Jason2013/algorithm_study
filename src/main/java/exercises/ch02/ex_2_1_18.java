// 代码来源：
// https://blog.csdn.net/qq_42440077/article/details/81671732

import edu.princeton.cs.algs4.StdDraw;
 
public class Insertion {
 
	public static void sort(Comparable[] a) {
		int N=a.length;
		for(int i=1;i<N;i++) {
			int min=i;
			for(int j=i;j>0&&less(a[j],a[j-1]);j--) {
				exch(a,j-1,j);
            min=j;		//获取受变动元素的下限	
			}	
			
			draw(a, i, min);//i绘出红色，i到min绘出黑色，其余绘灰色
			//延迟
			try   
			{   
			Thread.currentThread();
			Thread.sleep(200); 
			}   
			catch(Exception e){}  
			//清除画板
			clear();
		}
		draw(a,N,N);
	}
/**
 * 
 * @param a 排序数组 
 * @param ie 要插入的元素，以红色表示
 * @param min 参与比较的元素的最小值，以黑色表示
 */
	public static void draw(Comparable[] a,int e,int min) {
		int N=a.length;
		for(int i=0;i<N;i++) {
			double x=1.0*i/N;
        	double y=(double)a[i]/2.0;
        	double rw=0.5/N;
        	double rh=(double)a[i]/2.0;        	
			if(i==min) {	                        	//红色
				StdDraw.setPenColor(StdDraw.RED);
	        	StdDraw.filledRectangle(x, y, rw, rh);			
			}else if((i<e&&i>min)||i==e){               //黑色
				StdDraw.setPenColor(StdDraw.BLACK);
	        	StdDraw.filledRectangle(x, y, rw, rh);		
			}else {                                     //灰色
				StdDraw.setPenColor(StdDraw.GRAY);
	        	StdDraw.filledRectangle(x, y, rw, rh);
			}       	
        }
	}
	
		
	public static void clear() {
		StdDraw.clear();
	}
	
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w)<0;
	}
 
	private static void exch(Comparable[] a, int i, int j) {
		Comparable t=a[i];a[i]=a[j];a[j]=t;
	}
 
	private static void show(Comparable[] a) {
		for(int i = 0;i<a.length;i++)
			System.out.print(a[i]+" ");
		System.out.println();
	}
	private static boolean isSorted(Comparable[] a) {
		for(int i=0;i<a.length;i++) 
			if(less(a[i],a[i-1])) return false;
		return true;
	}
 
	public static void main(String[] args) {
		int N=50;
		Comparable[] a=new Comparable[N];
		for(int i=0;i<N;i++)
			a[i]=Math.random();
        sort(a);        
	}
}
