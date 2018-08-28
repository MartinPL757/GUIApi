package gui.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class onGuiShiftClickFromBottomInventoryEvent extends Event implements Cancellable {
	
	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;
	private Player player;
	private GUI gui;
	private Inventory inv;
	private ItemStack i, cursor;
	private int slot;
	
	
	public ItemStack getI() {
		return i;
	}

	public ItemStack getCursor() {
		return cursor;
	}

	public onGuiShiftClickFromBottomInventoryEvent(Player p, GUI gui, ItemStack i, int slot, ItemStack cursor) {
		this.player = p;
		this.gui = gui;
		this.inv = this.gui.getInv();
		this.cancelled = false;
		this.i = i;
		this.slot = slot;
		this.cursor = cursor;
	}
	
	
	
	public int getSlot() {
		return slot;
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

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
		 
	public static HandlerList getHandlerList() {
		return handlers;
	}

}
