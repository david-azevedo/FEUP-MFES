package interaction;

import org.overture.codegen.runtime.VDMSeq;

import controller.Console;
import generated.Global;
import generated.User;

public class MainMenu implements IActions {
	
	Global global = new Global();
	UserActions userActions;
	public static User user = null;
	
	
	@Override
	public void SetupCommands() {
		
		Console.Register("users.add", 
				"<name> to add an user to the database.",
				(String[] args) -> {
					try {
						VDMSeq s = global.listUsers();
						for(int i = 0; i < s.size(); i++)
							if(s.get(i).toString().equals(args[1]))
								throw new Exception();

						global.addUser(args[1]);
						Console.Println("Added New User");
					} catch (Exception e) {
						Console.Println("Error adding User! User already Exists");
					}
					return true;
				});

		Console.Register("users.list", "Shows the list of users!",
				(String[] args) -> {
					VDMSeq seq = global.listUsers();
					Console.Println("Users:");
					for(int i = 0; i < seq.size(); i++) {
						Console.Println("\t" + seq.get(i).toString());
					}
					return true;
				});

		Console.Register("users.clear", "Clear all users",
				(String[] args) -> {
					Console.Println("Deleting all users");
					global.clear();
					return true;
				});
		
		Console.Register("users.login", "<name> Access a user profile", 
				(String[] args) -> {
					
					user = global.getUser(args[1]);
					
					if(user == null) {
						Console.Println("User Don't Exists!");
					}
					else {
						userActions = new UserActions(user);
						userActions.SetupCommands();
						Console.Println("User Added New Commands Available");
					}
					return true;
				});
	}

	@Override
	public void ClearCommands() {
		
	}
	
	
}
