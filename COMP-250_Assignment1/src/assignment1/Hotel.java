package assignment1;

public class Hotel {
	
	private String hotelName;
	Room hotelRooms [];
	
	public Hotel (String hotelName, Room hotelRooms[]) {
		this.hotelName = hotelName;
		Room newHotelRooms [] = new Room [hotelRooms.length];
		for (int i = 0; i < hotelRooms.length; i++) {
			newHotelRooms[i] = new Room (hotelRooms[i]);
		}
		
		this.hotelRooms = newHotelRooms;
	}
	
	
	public int reserveRoom (String type) {
		for (int i = 0; i < hotelRooms.length; i++) {
			if (hotelRooms[i].roomIsAvailable == true && hotelRooms[i].roomType.equals(type)) {
				hotelRooms[i].roomIsAvailable = false;
				return hotelRooms[i].roomPrice;			
			}
			
		}
		throw new IllegalArgumentException("This type of room is not available :( ");
	}
	
	public boolean cancelRoom (String type) {
		for (int j = 0; j < hotelRooms.length; j++) {
			if (hotelRooms[j].roomIsAvailable == false && hotelRooms[j].roomType.equals(type)) {
				hotelRooms[j].roomIsAvailable = true;
				return true;
			}
		} return false;
	}
}
 