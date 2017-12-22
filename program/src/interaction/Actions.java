package interaction;

import java.util.ArrayList;

import controller.Console;
import controller.IConsoleCommand;

public class Actions{
	
	private ArrayList<String> commands = new ArrayList<>();
	
	
	protected void Register(String cmdName, String explanation, IConsoleCommand call) {
		commands.add(cmdName);
		Console.Register(cmdName, explanation, call);
	}
	
	
	public void SetupCommands() {
		
	}
	
	
	public void ClearCommands() {
		for(String s : commands) {
			Console.DeleteCMD(s);
		}
	}

}
