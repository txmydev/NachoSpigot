package txmy.dev.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
@Getter
public class AnvilPreRepairEvent extends Event {

    @Getter
    private static final HandlerList handlerList = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    private InventoryView view;
    private Inventory inventory;
    private ItemStack stack;
    @Setter
    private int cost;


}
