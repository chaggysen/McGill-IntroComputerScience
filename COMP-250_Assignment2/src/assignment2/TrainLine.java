package assignment2;

import java.util.Arrays;
import java.util.Random;

public class TrainLine {

	private TrainStation leftTerminus;
	private TrainStation rightTerminus;
	private String lineName;
	private boolean goingRight;
	public TrainStation[] lineMap;
	public static Random rand;

	public TrainLine(TrainStation leftTerminus, TrainStation rightTerminus, String name, boolean goingRight) {
		this.leftTerminus = leftTerminus;
		this.rightTerminus = rightTerminus;
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;

		this.lineMap = this.getLineArray();
	}

	public TrainLine(TrainStation[] stationList, String name, boolean goingRight)
	/*
	 * Constructor for TrainStation input: stationList - An array of TrainStation
	 * containing the stations to be placed in the line name - Name of the line
	 * goingRight - boolean indicating the direction of travel
	 */
	{
		TrainStation leftT = stationList[0];
		TrainStation rightT = stationList[stationList.length - 1];

		stationList[0].setRight(stationList[stationList.length - 1]);
		stationList[stationList.length - 1].setLeft(stationList[0]);

		this.leftTerminus = stationList[0];
		this.rightTerminus = stationList[stationList.length - 1];
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;

		for (int i = 1; i < stationList.length - 1; i++) {
			this.addStation(stationList[i]);
		}

		this.lineMap = this.getLineArray();
	}

	public TrainLine(String[] stationNames, String name, boolean goingRight) {/*
									 * Constructor for TrainStation. input: stationNames - An array of String
									 * containing the name of the stations to be placed in the line name - Name of
									 * the line goingRight - boolean indicating the direction of travel
									 */
		TrainStation leftTerminus = new TrainStation(stationNames[0]);
		TrainStation rightTerminus = new TrainStation(stationNames[stationNames.length - 1]);

		leftTerminus.setRight(rightTerminus);
		rightTerminus.setLeft(leftTerminus);

		this.leftTerminus = leftTerminus;
		this.rightTerminus = rightTerminus;
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;
		for (int i = 1; i < stationNames.length - 1; i++) {
			this.addStation(new TrainStation(stationNames[i]));
		}

		this.lineMap = this.getLineArray();

	}

	// adds a station at the last position before the right terminus
	public void addStation(TrainStation stationToAdd) {
		TrainStation rTer = this.rightTerminus;
		TrainStation beforeTer = rTer.getLeft();
		rTer.setLeft(stationToAdd);
		stationToAdd.setRight(rTer);
		beforeTer.setRight(stationToAdd);
		stationToAdd.setLeft(beforeTer);

		stationToAdd.setTrainLine(this);

		this.lineMap = this.getLineArray();
	}

	public String getName() {
		return this.lineName;
	}

	public int getSize() {
		
		return lineMap.length;
		
	}

	public void reverseDirection() {
		this.goingRight = !this.goingRight;
	}

	// You can modify the header to this method to handle an exception. You cannot make any other change to the header.
	public TrainStation travelOneStation(TrainStation current, TrainStation previous) {
		
		try {
			TrainStation currentStation;
			currentStation = findStation(current.getName());
		}
		catch (Exception e) {
			throw new StationNotFoundException("Station not found");
		}
		if (current.hasConnection && current.getTransferStation().equals(previous) == true) {
			return getNext(current);
		}
		else if(current.hasConnection && current.getTransferStation().equals(previous) == false) {
			return current.getTransferStation();
		}
		else {
			return getNext(current);
		}
	}

	

	// You can modify the header to this method to handle an exception. You cannot make any other change to the header.
	public TrainStation getNext(TrainStation station){

		try {
			TrainStation currentStation;
			currentStation = findStation(station.getName());
		}
		catch(Exception e) {
			throw new StationNotFoundException("Station not found");
		}
		if (station.getLine().goingRight == true) {
			if (station.isRightTerminal()) {
				goingRight = false;
				return station.getLeft();
			}
			else return station.getRight();
			
		}   else {
			if (station.isLeftTerminal() == true) {
				goingRight = true;
				return station.getRight();
			}
			else return station.getLeft();
		}    
	}

	// You can modify the header to this method to handle an exception. You cannot make any other change to the header.
	public TrainStation findStation(String name) {
		
		for (int i = 0; i < lineMap.length; i++) {
			if (lineMap[i].getName().equals(name)) {
				return lineMap[i];
			}
		} throw new StationNotFoundException("Station not found");
	}

	public void sortLine() {
		
		TrainStation[] stations = getLineArray();
		
		for (int i = 0; i < stations.length; i++) {
			for (int j = 0; j < stations.length - i - 1; j++) {
				if (stations[j].getName().compareTo(stations[j + 1].getName()) > 0) {
					TrainStation tempStation = stations[j];
					stations[j] = stations[j + 1];
					stations[j + 1] = tempStation;
				}
			}
		}
		
		TrainStation current = null;
		this.leftTerminus = stations[0];
		this.leftTerminus.setRight(stations[1]);
		this.leftTerminus.setLeft(null);
		this.leftTerminus.setNonTerminal();
		this.leftTerminus.setLeftTerminal();
		for (int i = 1; i < stations.length - 1; i++) {
		 current = stations[i];
		 current.setRight(stations[i + 1]);
		 current.setLeft(stations[i - 1]);
		 current.setNonTerminal();
		}
		
		this.rightTerminus = stations[stations.length - 1];
		this.rightTerminus.setRight(null);
		this.rightTerminus.setLeft(stations[stations.length - 2]);
		this.rightTerminus.setNonTerminal();
		this.rightTerminus.setRightTerminal();
		
		lineMap = getLineArray();
	
	}
	
	// FIND THE LENGTH OF THE LINKED LIST
	private int getTrainLineLength (TrainStation leftTerminus) {
		
		TrainStation current = leftTerminus;
		int count = 0;
		while (current != null) {
			count ++;
			current = current.getRight();
		}
		return count;
	}

	public TrainStation[] getLineArray() {

		int trainLineLength = getTrainLineLength(leftTerminus);
		TrainStation [] lineArray  = new TrainStation [trainLineLength];
		int index = 0;
		TrainStation current = leftTerminus;
		
		while(current != null) {
			lineArray[index] = current;
			index ++;
			current = current.getRight();
		}
		return lineArray;
	}

	private TrainStation[] shuffleArray(TrainStation[] array) {
		Random rand = new Random();
		
		rand.setSeed(11); 

		for (int i = 0; i < array.length; i++) {
			int randomIndexToSwap = rand.nextInt(array.length);
			TrainStation temp = array[randomIndexToSwap];
			array[randomIndexToSwap] = array[i];
			array[i] = temp;
		}
		this.lineMap = array;
		return array;
	}

	public void shuffleLine() {

		// you are given a shuffled array of trainStations to start with
		TrainStation[] lineArray = this.getLineArray();
		TrainStation[] shuffledArray = shuffleArray(lineArray);
		
		TrainStation current = null;
		this.leftTerminus = shuffledArray[0];
		this.leftTerminus.setRight(shuffledArray[1]);
		this.leftTerminus.setLeft(null);
		this.leftTerminus.setNonTerminal();
		this.leftTerminus.setLeftTerminal();
		for (int i = 1; i < shuffledArray.length - 1; i++) {
		 current = shuffledArray[i];
		 current.setRight(shuffledArray[i + 1]);
		 current.setLeft(shuffledArray[i - 1]);
		 current.setNonTerminal();
		}
		
		this.rightTerminus = shuffledArray[shuffledArray.length - 1];
		this.rightTerminus.setRight(null);
		this.rightTerminus.setLeft(shuffledArray[shuffledArray.length - 2]);
		this.rightTerminus.setNonTerminal();
		this.rightTerminus.setRightTerminal();
		
	}

	public String toString() {
		TrainStation[] lineArr = this.getLineArray();
		String[] nameArr = new String[lineArr.length];
		for (int i = 0; i < lineArr.length; i++) {
			nameArr[i] = lineArr[i].getName();
		}
		return Arrays.deepToString(nameArr);
	}

	public boolean equals(TrainLine line2) {

		// check for equality of each station
		TrainStation current = this.leftTerminus;
		TrainStation curr2 = line2.leftTerminus;

		try {
			while (current != null) {
				if (!current.equals(curr2))
					return false;
				else {
					current = current.getRight();
					curr2 = curr2.getRight();
				}
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public TrainStation getLeftTerminus() {
		return this.leftTerminus;
	}

	public TrainStation getRightTerminus() {
		return this.rightTerminus;
	}
}

//Exception for when searching a line for a station and not finding any station of the right name.
class StationNotFoundException extends RuntimeException {
	String name;

	public StationNotFoundException(String n) {
		name = n;
	}

	public String toString() {
		return "StationNotFoundException[" + name + "]";
	}
}
