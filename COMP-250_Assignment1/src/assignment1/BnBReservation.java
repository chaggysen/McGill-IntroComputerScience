package assignment1;

public class BnBReservation extends HotelReservation{
	
	public BnBReservation (String reservationName, Hotel reservationHotel, String roomType, int nbOfNight ) {
		super(reservationName, reservationHotel, roomType, nbOfNight);
	}
	
	
	public int getCost() {
		return super.getCost() + (super.getNumOfNights()) * 1000;
	}
}
