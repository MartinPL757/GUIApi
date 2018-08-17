package gui.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class onGuiClose extends Event {
	
	private static final HandlerList handlers = new HandlerList();
	private Player player;
	private GUI gui;
	private Inventory inv;
	
	

	public onGuiClose(Player p, GUI gui) {
		this.player = p;
		this.gui = gui;
		this.inv = this.gui.getInv();
	}
	

	public GUI getGui() {
		return gui;
	}

	public Inventory getInv() {
		return inv;
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
		 
	public static HandlerList getHandlerList() {
		return handlers;
	}

}
