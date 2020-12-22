package assignment1;

public class FlightReservation extends Reservation{
	
	private Airport departureAirport;
	private Airport arrivalAirport;
	
	public FlightReservation (String reservationName, Airport departure, Airport arrival) {
		
		super(reservationName);
		
		this.departureAirport = departure;
		this.arrivalAirport = arrival;
	
		if(Airport.getDistance(departure, arrival) == 0) {
		throw new IllegalArgumentException("Two inputs airports are the same");
		}
	}
	
	public int getCost() {
		
		double totalCost;
		double fuelCost;
		int airportFees;
		
		fuelCost = (Airport.getDistance(departureAirport, arrivalAirport)/167.52)*124;
		airportFees = departureAirport.getFees() + arrivalAirport.getFees();
		totalCost = fuelCost + airportFees +5375;
		
		if (totalCost % (int)totalCost !=0) totalCost += 1;
		return (int) totalCost;
	}
	
	public boolean equals (Object object) {
		if (object instanceof FlightReservation) {
		   FlightReservation reservation = (FlightReservation) object;
		   if (reservation.reservationName().equals(this.reservationName()) && reservation.departureAirport.equals(this.departureAirport) && reservation.arrivalAirport.equals(this.arrivalAirport)) return true ;
           
		} return false;
	}
}

