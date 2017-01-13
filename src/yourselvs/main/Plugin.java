package yourselvs.main;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import yourselvs.main.CommandProcessor.Cmd;
import yourselvs.utils.DateFormatter;
import yourselvs.utils.Messenger;

//TODO highlight classes youve already joined in /classes

public class Plugin extends JavaPlugin 
{
	public static final String version = "0.1";
	public static final String pluginName = "Plugin";
	public static final String prefix = "PLUGIN";
	
	private final static Object commandLock = new Object();
	
	private CommandProcessor commandProcessor;
	private DateFormatter formatter;
	private Messenger messenger;
	
	private String normalPrefix = "[" + ChatColor.BLUE + ChatColor.BOLD + prefix + ChatColor.RESET + "] ";
	private String linkPrefix = ChatColor.AQUA + "[" + ChatColor.BLUE + ChatColor.BOLD + prefix + ChatColor.RESET + ChatColor.AQUA + "]" + ChatColor.RESET + " ";
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
    	
    	formatter = new DateFormatter();
    	messenger = new Messenger(this, normalPrefix, linkPrefix, ChatColor.YELLOW);

    	commandProcessor = new CommandProcessor(this);
	}
	
	public Messenger getMessenger() {return messenger;}
	public DateFormatter getFormatter() {return formatter;}
	public CommandProcessor getCommandProcessor() {return commandProcessor;}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Thread t = new Thread(new Runnable() {
	        public void run(){
	        	synchronized(commandLock) {
	        		commandProcessor.parseCommand(new Cmd(sender, command, label, args));
	        	}
	        }
	    });
		
		t.setName(pluginName + " Command Processor");
		t.start();
		
		return true;
	}
}