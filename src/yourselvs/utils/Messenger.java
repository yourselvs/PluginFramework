package yourselvs.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.mythicacraft.voteroulette.utils.InteractiveMessageAPI.FormattedText;
import com.mythicacraft.voteroulette.utils.InteractiveMessageAPI.InteractiveMessage;
import com.mythicacraft.voteroulette.utils.InteractiveMessageAPI.InteractiveMessageElement;
import com.mythicacraft.voteroulette.utils.InteractiveMessageAPI.InteractiveMessageElement.ClickEvent;
import com.mythicacraft.voteroulette.utils.InteractiveMessageAPI.InteractiveMessageElement.HoverEvent;

public class Messenger {
	private String prefix;
	private String linkPrefix;
	private JavaPlugin plugin;
	private ChatColor highlight;
	
	public Messenger(JavaPlugin instance, String prefix, String linkPrefix, ChatColor highlight){
		this.plugin = instance;
		this.prefix = prefix;
		this.linkPrefix = prefix;
		this.highlight = highlight;
	}
	
	public void setPrefix(String prefix){
		this.prefix = prefix;
	}
	
	public void setLinkPrefix(String prefix){
		this.linkPrefix = prefix;
	}
	
	public void setHighlight(ChatColor highlight){
		this.highlight = highlight;
	}
	
	public void sendPlayerLog(Player player, String message){
		plugin.getLogger().info("Player \"" + player.getName() + "\" : " + message + "(UUID: " + player.getUniqueId().toString() + ")");
	}
	
	public void sendMessages(CommandSender player, List<String> msgs, String title){
		ArrayList<String> updateMessages = new ArrayList<String>();
		updateMessages.add(prefix + ChatColor.DARK_GRAY + "- - [ " + highlight + title + ChatColor.RESET + ChatColor.DARK_GRAY + "] - - - - - - - - - - - -");
		updateMessages.addAll(msgs);
		player.sendMessage(updateMessages.toArray(new String[msgs.size()]));
	}
	
	public void sendMessage(CommandSender player, String message){
		player.sendMessage(prefix + ChatColor.RESET + message + ChatColor.RESET);
	}
	
	public void sendServerMessage(String message){
		for(Player player : plugin.getServer().getOnlinePlayers())
			sendMessage(player, message);
	}	
	
	public void sendServerMessage(ArrayList<String> messages){
		for(Player player : plugin.getServer().getOnlinePlayers())
			sendMessages(player, messages, "");
	}
	
	public void sendClickMessage(CommandSender player, String line, String hoverMessage, String command){
		InteractiveMessage message = new InteractiveMessage(new InteractiveMessageElement(new FormattedText(linkPrefix + line), HoverEvent.SHOW_TEXT, new FormattedText(hoverMessage), ClickEvent.RUN_COMMAND, command));	
		message.sendTo(player);
	}
	
	public void sendClickMessage(CommandSender player, String line, String command){
		InteractiveMessage message = new InteractiveMessage(new InteractiveMessageElement(new FormattedText(linkPrefix + line), HoverEvent.NONE, new FormattedText(""), ClickEvent.RUN_COMMAND, command));	
		message.sendTo(player);
	}
	
	public void sendSuggestMessage(CommandSender player, String line, String hoverMessage, String command){
		InteractiveMessage message = new InteractiveMessage(new InteractiveMessageElement(new FormattedText(linkPrefix + line), HoverEvent.SHOW_TEXT, new FormattedText(hoverMessage), ClickEvent.SUGGEST_COMMAND, command));	
		message.sendTo(player);
	}

	public void sendSuggestMessage(CommandSender player, String line, String command){
		InteractiveMessage message = new InteractiveMessage(new InteractiveMessageElement(new FormattedText(linkPrefix + line), HoverEvent.NONE, new FormattedText(""), ClickEvent.SUGGEST_COMMAND, command));
		message.sendTo(player);
	}
}
