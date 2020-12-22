package assignment1;

public class Basket {
	
	private Reservation reservationList [];
	private Reservation Products [];
	
	public Basket() {
	reservationList = new Reservation[0];
	}
	
	public Reservation[] getProducts() {

		Products = reservationList;
		return Products;
		
	}
	
	public int add (Reservation reservation) {
		
		Reservation newReservationList[] = new Reservation[reservationList.length + 1];
		for (int i = 0; i < reservationList.length; i++) {
			newReservationList[i] = reservationList[i];
		}
		newReservationList[newReservationList.length - 1] = reservation;
		reservationList = newReservationList;
		return reservationList.length;
	}
	
	public boolean remove (Reservation reservation) {
		
		Reservation[] newReservationList = new Reservation[reservationList.length - 1];
		int index = -1;
		
		for (int i = 0; i < reservationList.length; i++) {
			if (reservationList[i].equals(reservation)) {
				index = i;
			}
		}
		if (index == -1) return false;
		for (int i = 0; i < index; i++) {
			newReservationList[i] = reservationList[i];
		}
		for (int j = index + 1; j < reservationList.length; j++) {
			newReservationList[j - 1] = reservationList[j];
		}
		reservationList = newReservationList;
		return true;
		
	}
	
	public void clear() {
		
		Reservation[] clearedReservationList = new Reservation [0];
		for (int i = 0; i < reservationList.length; i++) {
			reservationList[i] = null;
		}
		reservationList = clearedReservationList;
		
	}
	
	public int getNumOfReservations() {
		return reservationList.length;
	}
	
	public int getTotalCost() {
		int totalCost = 0;
		for (int i = 0; i < reservationList.length; i++) {
			totalCost += reservationList[i].getCost();
		}
		return totalCost;
	}

		
} 
	
	

