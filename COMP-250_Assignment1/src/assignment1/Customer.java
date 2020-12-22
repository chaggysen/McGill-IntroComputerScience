package assignment1;

public class Customer {
	
	String customerName;
	int customerBalance;
	Basket customerBasket;
	
	public Customer (String customerName, int initialBalance) {
		customerBasket = new Basket();
		this.customerName = customerName;
		this.customerBalance = initialBalance;
	}
	
	public String getName() {
		return customerName;
	}
	
	public int getBalance() {
		return customerBalance;
	}
	
	public Basket getBasket() {
		return customerBasket;
	}
	
	public int addFunds(int fundAdded) {
		if (fundAdded >= 0) {
			customerBalance += fundAdded;
			return customerBalance;
		}
		throw new IllegalArgumentException("Sorry, the fund added is negative");
	}
	
	public int addToBasket(Reservation reservation) {
		if(reservation.reservationName().equals(customerName)) {
			customerBasket.add(reservation);
			return customerBasket.getNumOfReservations();
		}
		throw new IllegalArgumentException("Sorry, couldn't add reservation to the basket");
		
	}
	
	public int addToBasket(Hotel hotel, String roomType, int numberOfNight, boolean wantBreakfast) {
		HotelReservation newHotelReservation = new HotelReservation(customerName, hotel, roomType, numberOfNight);
		customerBasket.add(newHotelReservation);
		return customerBasket.getNumOfReservations();
	}
	
	public int addToBasket(Airport aiport1, Airport airport2) {
		FlightReservation newFlightReservation = new FlightReservation(customerName, aiport1, airport2);
		customerBasket.add(newFlightReservation);
		return customerBasket.getNumOfReservations();
	}
	
	public boolean removeFromBasket(Reservation reservation) {
		return customerBasket.remove(reservation);
	}
	
	public int checkOut() {
		if (customerBalance < customerBasket.getTotalCost()) {
			throw new IllegalArgumentException("Banlance is not enought to cover all expenses");
		}
		customerBalance -= customerBasket.getTotalCost();
		customerBasket.clear();
		return customerBalance;
	}

}
