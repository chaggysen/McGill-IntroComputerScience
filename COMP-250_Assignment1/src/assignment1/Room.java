package assignment1;

public class Room {
	
	public String roomType;
	public int roomPrice;
	public boolean roomIsAvailable;
	
	public Room (String type) {
		
		if (type.equalsIgnoreCase("double")) {
			
			this.roomType = type;
			this.roomPrice = 9000;
			this.roomIsAvailable = true;
			
		}else if (type.equalsIgnoreCase("queen")) {
			
			this.roomType = type;
			this.roomPrice = 11000;
			this.roomIsAvailable = true;
			
		}else if (type.equalsIgnoreCase("king")) {
			
			this.roomType = type;
			this.roomPrice = 15000;
			this.roomIsAvailable = true;
			
		}
		else throw new IllegalArgumentException("No room of such type can be created");
	}
	
	public Room(Room room) {
		this.roomType = room.roomType;
		this.roomPrice = room.roomPrice;
		this.roomIsAvailable = room.roomIsAvailable;
	}
	
	public String getType () {
		
		return roomType;
		
	}
	
	public int getPrice () {
		
		return roomPrice;
		
	}
	
	public void changeAvailability () {
		
		roomIsAvailable = !roomIsAvailable;
		
	}
	
	public static Room findAvailableRoom (Room availability [], String type) {
		
		for (int i = 0; i < availability.length; i++) {
			if (availability[i].roomType == type) return availability[i];
		}
		return null;
	}
	
	public static boolean makeRoomAvailable (Room availability [], String type) {
		
		for (int j = 0; j < availability.length; j++) {
			if(availability[j].roomIsAvailable == false) {
			availability[j].roomIsAvailable = true;
			return true;
			}
		} return false;
		
	}

}
