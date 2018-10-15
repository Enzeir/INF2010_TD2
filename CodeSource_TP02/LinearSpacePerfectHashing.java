import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType>
{
	static int p = 46337;

	QuadraticSpacePerfectHashing<AnyType>[] data;
	int a, b;

	LinearSpacePerfectHashing()
	{
		a=b=0; data = null;
	}

	LinearSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0)
		{
			// A completer
			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;

			// A completer
			return;
		}
		
		// A completer
		a = generator.nextInt(p);
		while(a == 0) 
		{
			a = generator.nextInt(p);
		}
		b = generator.nextInt(p);
		
		int m = array.size();			
		data = new QuadraticSpacePerfectHashing[m];	
		
		for(int i = 0; i < array.size(); i++)
		{
			int key = getKey(array.get(i));
			ArrayList<AnyType> al = new ArrayList<AnyType>();
			
			if(containsKey(key)) 
			{
				for( AnyType item : data[key].items)
				{
					if(item != null) 
					{
						al.add(item);
					}
				}	
			}
			
			al.add(array.get(i));
			data[key] = new QuadraticSpacePerfectHashing<AnyType>(al);
		}
	}

	public int Size()
	{
		if( data == null ) return 0;

		int size = 0;
		for(int i=0; i<data.length; ++i)
		{
			size += (data[i] == null ? 1 : data[i].Size());
		}
		return size;
	}

	public boolean containsKey(int key)
	{
		// A completer
		if(data[key] ==null)
		{
			return false;
		}
		return true;
	}
	
	public int getKey (AnyType x) {
		// A completer
		return ((a*x.hashCode()+b)%p)%data.length;
	}
	
	public boolean containsValue (AnyType x) {
		// A completer
		if(data[getKey(x)] != null && data[getKey(x)].containsValue(x)) {
			return true;
		}
		return false;
	}
	
	public void remove (AnyType x) {
		// A completer
		data[getKey(x)].remove(x);
	}

	public String toString () {
		String result = "";
		
		// A completer
		for(int i = 0; i < data.length; i++) 
		{
			if(data[i] != null) 
			{
				result += "[" + i  + "] -> " + data[i].toString() + "\n";
			}
			else {
				result += "[" + i  + "] -> \n";
			}
		}
		
		return result; 
	}

	public void makeEmpty () {
		// A completer
		
   	}
	
}
