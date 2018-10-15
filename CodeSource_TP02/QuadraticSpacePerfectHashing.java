import java.util.ArrayList;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> 
{
	static int p = 46337;

	int a, b;
	AnyType[] items;

	QuadraticSpacePerfectHashing()
	{
		a=b=0; items = null;
	}

	QuadraticSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public int Size()
	{
		if( items == null ) return 0;

		return items.length;
	}

	public boolean containsKey(int key)
	{
		// A completer
		if(items[key] == null)
		{
			return false;
		}
		return true;

	}

	public boolean containsValue(AnyType x )
	{
		// A completer
		if(items[getKey(x)] != null && items[getKey(x)].equals(x))
		{
			return true;
		}
		return false;

	}

	public void remove (AnyType x) {
		// A completer
		items[getKey(x)] = null;
	}

	public int getKey (AnyType x) {
		// A completer
		int hc = x.hashCode();
		int length = items.length;
		int key = ((a*hc+b)%p)%length;
		
		if(containsKey(key) && length >1) {
			int key2 = (x.hashCode() / length) + 1;
			while(containsKey(key)) {
				//key += key2;
				key += 1;
				key = key%length;
			}
		}
		
		return key;
		
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0)
		{
			// A completer
			//rien a faire?
			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;
			// A completer
			items = (AnyType[]) new Object[1];
			items[0] = array.get(0);
			return;
		}
		
		// A completer
		a = generator.nextInt(p);
		while(a == 0) 
		{
			a = generator.nextInt(p);
		}
		
		b = generator.nextInt(p);
		
		int m = array.size()*array.size();
		items = (AnyType[]) new Object[m];
		for(int i = 0; i < array.size(); i++)
		{
			int key = getKey(array.get(i));
			
			

			items[key]= array.get(i);
		}
	}

	
	
	public String toString () {
		String result = "";
		
		for( AnyType item : items)
		{
			if(item != null) 
			{
				result += "(" + getKey(item) + ", " + item + ") ";
			}
		}	
		return result; 
	}

	public void makeEmpty () {
		   // A completer
		 for(AnyType item : items) {
			 remove(item);
			 
			 
		 }
   	}
}
