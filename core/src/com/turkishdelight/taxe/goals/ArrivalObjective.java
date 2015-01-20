package com.turkishdelight.taxe.goals;

import java.util.ArrayList;
import java.util.Random;

import com.turkishdelight.taxe.Player;
import com.turkishdelight.taxe.routing.AiSprite;

public class ArrivalObjective extends Objective {

	//This variable stores this Objective's target destination
	private String destination = "";
	
	public ArrivalObjective(int money, String goalText, String destination) {
		super(money, goalText);
		this.setDestination(destination);
	}
	
	//Override toString method
	public String toString()
	{
		return getGoalText() + destination;
	}
	
	public static Objective generate()
	{
		return new ArrivalObjective(200, "Transport a train to ", getRandomStation());
	}
	
	public static String getRandomStation()
	{
		String[] stations = {"London", "Paris", "Madrid", "Lisbon", "Rome", "Berlin", "Krakow", "Budapest", "Moscow"};
		int idx = new Random().nextInt(stations.length);
		String random = (stations[idx]);
		return random;
	}
	
	//Getter and setter methods for eventIndex and destination
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	//This method is the generic method that gets overridden in children to determine whether a player has completed this objective
	@Override
	public boolean fillsCompleteCriteria(Player pl, EventHandler eventLog)
	{
		ArrayList<AiSprite> playerPossessions = pl.aiSprites;
		for(int i = this.getEventIndex(); i < eventLog.getIndex(); i++)
		{
			Event e = eventLog.getEvent(i);
			System.out.println("Checking event " + e.Station + " vs. " + destination);
			//Check for the train in the playerPossessions to see if the player owns that train
			boolean playerTrain = false;
			for(AiSprite item : playerPossessions)
			{
				if(e.train.equals(item))
				{
					//We have found the train in the player's possessions
					playerTrain = true;
				}
			}
			if(playerTrain)
			{
				//We can now compare the target destination with the even't location. If it is a match, we can say this objective has been completed 
				if(e.Station.equals(destination))
				{
					//We have found a match
					return true;
				}
			}
			
		}
		return false;
	}
	

}