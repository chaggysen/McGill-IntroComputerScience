package assignment1;

public class Airport {
	
	private int xCoordinate;
	private int yCoordinate;
	private int airportFees;
	
	public Airport (int x, int y, int fees) {
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.airportFees = fees;
	}
	
	public int getFees () {
		return airportFees;
	}
	
	public static int getDistance (Airport airport_1, Airport airport_2) {
		
		double distance;
		double xDifference = ((double)airport_1.xCoordinate - (double)airport_2.xCoordinate) * ((double)airport_1.xCoordinate - (double)airport_2.xCoordinate);
		double yDifference = ((double)airport_1.yCoordinate - (double)airport_2.yCoordinate) * ((double)airport_1.yCoordinate - (double)airport_2.yCoordinate);
		
		distance = Math.sqrt(xDifference + yDifference);
		if (distance % (int)distance != 0)distance += 1;
		return (int)distance;
		
	}
	
}
