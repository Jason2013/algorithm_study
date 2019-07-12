import java.util.Iterator;

public class MyObj<T> implements Iterable<T> {
	
	private int begin;
	private int end;
	private T val;
	
	public MyObj(int b, int e, T val) {
		begin = b;
		end = e;
		this.val = val;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("MyObj");
		
		MyObj<String> objStr = new MyObj<String>(1, 5, "Hello");
		for (String s: objStr) {
			System.out.println("s=" + s);
		}
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new MyObjIterator<T>(this);
	}
	
	private static class MyObjIterator<T> implements Iterator<T> {
		private int cur;
		private MyObj<T> o;
		MyObjIterator(MyObj<T> obj) {
			cur = obj.begin;
			o = obj;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return cur <= o.end;
		}

		@Override
		public T next() {
			cur++;
			return o.val;
		}
		
	}

}
