package assignment1;

public class HotelReservation extends Reservation{
	
	private Hotel hotel;
	private String reserveRoomType;
	private int numberNight;
	private int roomPrice;
	
	public HotelReservation (String reservationName, Hotel reservationHotel, String roomType, int nbOfNight) {
		
		super(reservationName);
		this.roomPrice = reservationHotel.reserveRoom(roomType);
		this.numberNight = nbOfNight;
		this.reserveRoomType = roomType;
		this.hotel = reservationHotel;
	}
	
	public int getNumOfNights() {
		return numberNight;
	}
	
	public int getCost() {
		return (roomPrice * numberNight);
	}
	
	public boolean equals (Object object) {
		if (object instanceof HotelReservation) {
			HotelReservation reservation = (HotelReservation) object;
			if (reservation.reservationName().equals(this.reservationName()) && reservation.hotel.equals(this.hotel) && reservation.reserveRoomType.equals(this.reserveRoomType) && reservation.numberNight == this.numberNight && reservation.getCost() == this.getCost()) {
				return true;
			}
			
		} return false;
	}

}
 