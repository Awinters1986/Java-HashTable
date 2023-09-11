import java.io.BufferedReader;
import java.io.FileReader;

/***********************************
Date:		April 13th, 2020
***********************************/

public class UAHashTable {
	
	protected static final int INITIAL_SIZE = 27;
	protected UACourse[] T;
	protected int hashMapSize;
	protected int collisionCount;
	private UACourse[] A = new UACourse[1100];
	
	/*
	 * Constructors
	 */
	
	public UAHashTable(int size) {
		T = new UACourse[size];
	}
	
	public UAHashTable() {
		this(INITIAL_SIZE);
	}
	
	/*
	 * Methods to implement in each class:  insert() and search()
	 */
	
	public void readFile(String filename) {
		String line;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			while ( (line = br.readLine()) != null) {
				String[] tokens = line.split( "," );
				
				
				insert(new UACourse(Integer.parseInt(tokens[0]), tokens[1], tokens[2]));
				
			}
			
			br.close();
		}
		catch(Exception e) {
			System.out.println("Exception: " + e);
		}
	}
	
	public void insert(UACourse k) {
		int attempt = 0;
		int i = 0;
		
		do {
			int j = k.hashCode(attempt);
			if( A[j] == null ) {
				A[j] = k;
				k.setHc(j);
				attempt++;
				hashMapSize++;
			 } else {
				i++;
				attempt++;
				collisionCount++;
			}
			
		} while ( i < A.length );	
	}
	
	public UACourse search(int id) {
		int attempt = 0;
		int j = 0;
		int i = 0;
		UACourse a = new UACourse();
		
		a.setId(id);
		
		do {
			j = a.hashCode(attempt);
			
			if(A[j].getId() == a.getId()) {
				return a; 
			} else {
				i++;
				attempt++;
			}
				
			return a;
			
		} while (i < A.length | A[j] == null);
	} 
	
	public int size() {
		return this.size();
	}
	
	// This should return the number of times a collision occurred.
	
	public int getCollisionCount() {
		return this.collisionCount;
	}
	
	/*
	 * Runtime code
	 */
	
	public static void main(String[] args) {
		String file = "records.txt";
		
		UAHashTable h = new UAHashTable();
		
		h.readFile(file);
		
	}
	
	
	/*
	 * Class that will be used to store in the hash map implementation.
	 */
	
	public static class UACourse {
		public int hc;
		public int id;
		private String firstName;
		private String lastName;
		
		public String courseName;
		public String courseInstructor;
		
		/*public UACourse(int id, String courseName, String courseInstructor ) {
			this.firstName = firstName; 
			this.lastName = lastName;
		}*/
		
		public UACourse() {
			
		}
		
		public UACourse(int hash, int id, String firstName, String lastName) {
			this.hc = hash;
			this.id = id;
			this.firstName = firstName; 
			this.lastName = lastName;
		}
		
		public UACourse(int id, String firstName, String lastName ) {
		this.id = id;
		this.firstName = firstName; 
		this.lastName = lastName;
		}
		
		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		public int getHc() {
			return hc;
		}

		public void setHc(int hc) {
			this.hc = hc;
		}
		
		/*public String toString() {
			return String.format("Hash Code: %15d      Course Info: %30s %n", this.hashCode(), this.courseName + ", " + this.courseInstructor);
		} */
		
		public String toString() {
			return String.format("Hash Code: %15d      Course Info: %30s %n", this.hc, this.firstName + ", " + this.lastName);
		}
		
		// Be sure to update this method!
		public int hashCode(int a) {
			int hash = 0;
			hash = ((id + a) % INITIAL_SIZE);
			return hash;
		}
		
		/*
		 * Specific implementations of hash maps
		 */
		
		public int linear(int a) {
			int linear = 0;
			linear = ((id + a) % INITIAL_SIZE);
			return linear;
		}
		
		public int quad(int a) {
			int quad = 0;
			quad = (int) Math.round(((id + a * 1 + Math.pow(a, 2) * 3) % INITIAL_SIZE));
			return quad;
		}
		
		public int doubleHash(int a) {
			int dbl = 0;
			dbl = (((id + a) * (3 + (id % INITIAL_SIZE))) % INITIAL_SIZE);
			return dbl;
		}
	
	}
}

