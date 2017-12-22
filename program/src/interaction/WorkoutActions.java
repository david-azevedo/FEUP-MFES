package interaction;

import java.util.ArrayList;

import controller.Console;
import generated.Types.Point;
import generated.Types.Route;
import generated.User;

public class WorkoutActions extends Actions{
	
	private User user = null;
	
	public WorkoutActions (User usr) {
		this.user = usr;
	}

	public void SetupCommands() {
		Console.Println("Workout Commands added!");

		Register("user.workout.finish", "<name> Finishes a current workout",
			(String[] args) -> 
			{
				user.finishCurrentWorkout(System.currentTimeMillis()/1000);
				Console.Println("Workout Finished");
				ClearCommands();
				return true;
			});
		
		Register("user.workout.cancel", "<name> Cancels a current workout",
			(String[] args) -> 
			{
				user.cancelCurrentWorkout();
				Console.Println("Workout Canceled");
				ClearCommands();
				return true;
			});

		Register("user.workout.GPS", "<lat> <lon> Adds a new GPS Point",
			(String[] args) -> 
			{
				try {
					user.updateByGPS(new Point( Double.parseDouble(args[1]) , Double.parseDouble(args[2])));
					Console.Println("GPS Updated Successfuly");
				} catch (Exception e) {
					Console.Println("Error Updating GPS");
					return false;
				}
				
				return true;
			});
		
		Register("user.workout.route", "Shows the current route",
			(String[] args) -> 
			{
				Route r = user.getCurrentRoute();
				Console.Println("Route: " + r.name + " Points:" + r.route.size());
				
				int maxX= Integer.MIN_VALUE;
				int minX= Integer.MAX_VALUE;
				int maxY= Integer.MIN_VALUE;
				int minY= Integer.MAX_VALUE;
				
				
				char[][] line = new char[20][20];
				
				for(int i = 0; i < line.length; i++) {
					for(int j = 0; j < line[i].length; j++) {
						line[i][j] = ' ';
					}
				}
				
				
				for(Object e : r.route) {
					Point p = (Point) e;
					
					maxX = Math.max((int)(double)p.lat, maxX);
					minX = Math.min((int)(double)p.lat, minX);
					maxY = Math.max((int)(double)p.lon, maxY);
					minY = Math.min((int)(double)p.lon, minY);
				}
				
				int index = 0;
				for(Object e : r.route) {
					Point p = (Point) e;
					int i = (int)((((int)(double)p.lat - minX)/ (float)(maxX - minX))*(line.length-1));
					int j = (int)((((int)(double)p.lon - minY)/ (float)(maxY - minY))*(line[0].length-1));
					line[i][j] = (char)('a' + index++);
				}
				
				Console.Println("lat("+ minX + ","+ maxX + ")  long(" + minY + "," + maxY +")"); 
				
				String side = "";
				for(int i = 0; i < line.length; i++) {
					side += "-";
				}
				
				Console.Println(side);
				for(int i = 0; i < line.length; i++) {
					String s ="|";
					for(int j = 0; j < line[i].length; j++) {
						s += line[i][j];
					}
					Console.Println(s + "|");
				}
				Console.Println(side);

				return true;
			});
		
		
		

	}

}
