package gui.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;

public class onGuiOpenEvent extends Event implements Cancellable{
	
	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;
	private Player player;
	private GUI gui;
	private Inventory inv;
	
	
	public onGuiOpenEvent(Player p, GUI gui, Inventory inv) {
		this.player = p;
		this.gui = gui;
		this.inv = inv;
		this.cancelled = false;
	}
	
	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
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

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
		 
	public static HandlerList getHandlerList() {
		return handlers;
	}

}
