package assignment1;

public abstract class Reservation {
	
	String name;
	
	public Reservation (String clientName) {
		
		this.name = clientName;
		
	}
	
	public final String reservationName () {
		
		return name;
		
	}
	
	public abstract int getCost();
		
	public abstract boolean equals(Object object);
	
	
}
