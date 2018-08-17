package gui.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class onGuiChangeEvent extends Event implements Cancellable {
	
	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;
	private Player player;
	private GUI previousGui, currentGui;
	
	

	public GUI getPreviousGui() {
		return previousGui;
	}

	public GUI getCurrentGui() {
		return currentGui;
	}

	public onGuiChangeEvent(Player p, GUI prev, GUI now) {
		this.player = p;
		this.cancelled = false;
		this.previousGui = prev;
		this.currentGui = now;
	}
	
	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
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
