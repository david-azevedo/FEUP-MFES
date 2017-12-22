package controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import interaction.MainMenu;

public class Console{
	
	private static HashMap<String, IConsoleCommand> commands = new HashMap<>();
	private static HashMap<String, String> explanations = new HashMap<>();

	// Creates a simple help command
	private static IConsoleCommand helpCMD = new IConsoleCommand() {
		@Override
		public boolean call(String[] s) {
			if(s.length == 1) {
				Println("Commands:");
				for(Map.Entry<String, String> obj : explanations.entrySet()) {
					Println(obj.getKey() + "\t" + obj.getValue());
				}
			}else if(s.length == 2) {
				if(explanations.containsKey(s[1])) {
					Println("Usage: " + s[1] + "\t" + explanations.get(s[1]));
				}
				
			}
			return true;
		}
	};
	
	// Deletes an existing command 
	public static void DeleteCMD(String cmd) {
		if(commands.containsKey(cmd)) {
			commands.remove(cmd);
			explanations.remove(cmd);
		}
	}
	
	// Initializes the help command
	static {
		Register("help", 
				"help or help <comand>  Shows the list of commands or a command's description", 
				helpCMD);
		Register("?", 
				"? or ? <comand>  Shows the list of commands or a command's description", 
				helpCMD);
	}
	
	// Register a command by the name of command with an explanation and a callback call
	public static void Register(String command, String explanation, IConsoleCommand call) {
		commands.put(command, call);
		explanations.put(command, explanation);
	}
	
	//Shorthand to print a line
	public static void Println(String l) {
		System.out.println(l);
	}
	
	//Executes a command
	public static void Execute(String cmd) {
		cmd = cmd.trim();
		String[] args = cmd.split(" ");
		
		if(args.length == 0)
			return;
		
		if(commands.containsKey(args[0])) {
			
			IConsoleCommand a = commands.get(args[0]);
			
			if(!a.call(args)) {
				helpCMD.call(new String[] {"help", args[0]});
			}
			
		}else {
			Println("Command Unknown");
		}
	}
	
	private static Boolean end = new Boolean(false);

	//START Function!
	public static void main(String[] args) {
		//Command to end the program
		MainMenu menu = new MainMenu();
		menu.SetupCommands();
		Console.Register("end", "ends the program", (String[] s) -> {end = true; return true;});
		
		Console.Println("Program Started");;
		
		Scanner s = new Scanner(System.in);
		
		while(!end.booleanValue()) {
			
			Console.Execute(s.nextLine());
			
		}
		Console.Println("Program Finished");
		
	}
}
