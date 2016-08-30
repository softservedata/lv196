package tasks;

public class Combinations {
	
	public static void findTriple(int n){
		int x, y, z, nx, ny, nz;
		for( x = 0, nx = n; x * x <= nx; x++)
			 for( y = 0, ny = nx - x * x; y * y <= ny; y++)
			    for( z = 0, nz = ny - y * y; z * z <= nz; z++)
			       if(z * z == nz){
			    	   System.out.println("x = " + x + ", y = "+ y + ", z = " + z);
			       }
	}

	public static void main(String[] args) {

		findTriple(IOMethods.getInt());             
		
	}

}
