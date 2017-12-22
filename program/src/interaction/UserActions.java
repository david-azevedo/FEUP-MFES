package interaction;

import java.awt.List;
import java.sql.Time;
import java.util.ArrayList;

import org.overture.codegen.runtime.VDMSeq;

import controller.Console;
import generated.User;

public class UserActions extends Actions{
	
	private User user = null;
	
	private WorkoutActions workout = null;
	
	public UserActions(User user) {
		this.user = user;
	}
	
	@Override
	public void SetupCommands() {
		
		Register("user.logout", "Logs the user out",
				(String[] args) -> 
				{
					ClearCommands();
					return true;
				});
		
		Register("user.friends.add", "<name> Adds a friend to a user",
			(String[] args) -> 
			{
				try {
					user.addFriend(args[1]);
					
					Console.Println("User " + args[1] + " added!");
				} catch (Exception e) {
					Console.Println("User " + args[1] + " not found!");
				}
				return true;
			});
		
		Register("user.friends.list", "Lists all the friends of an user",
				(String[] args) -> 
				{
					VDMSeq seq =  user.listFriends();
					Console.Println("Friends: " + seq.size());
					for(int i = 0; i < seq.size(); i++) {
						Console.Println("\t" + seq.get(i).toString());
					}
					return true;
				});
		
		
		Register("user.friends.remove", "Removes a user from the friendlist",
				(String[] args) -> 
				{
					VDMSeq seq =  user.listFriends();
					for(int i = 0; i < seq.size(); i++) 
						if(seq.get(i).toString().equals(args[1])) {
							user.removeFriend(args[1]);
							Console.Println("Succesfuly Removed: " + args[1]);
							return true;
						}
					Console.Println("User:" + args[1] + " Isn't in your friend list");
					return true;
				});
		
		Register("user.workout.start", "<name> Starts a workout with a given name",
				(String[] args) -> 
				{
					if(args.length != 2)
						return false;
					
					user.startWorkout(args[1], System.currentTimeMillis()/1000);
					workout = new WorkoutActions(user);
					workout.SetupCommands();
					Console.Println("Workout " + args[1] + " Started!");
					return true;
				});

				
		
	}
	
	@Override
	public void ClearCommands() {
		if(workout != null)
			workout.ClearCommands();
		super.ClearCommands();
	}

}
