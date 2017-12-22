package interaction;

import controller.Console;
import generated.Types.Point;
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
		

	}

}
