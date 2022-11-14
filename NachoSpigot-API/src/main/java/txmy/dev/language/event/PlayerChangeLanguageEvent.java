package txmy.dev.language.event;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import txmy.dev.language.Language;

@Getter
public class PlayerChangeLanguageEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    private final Language oldLanguage, newLanguage;

    public PlayerChangeLanguageEvent(Player who, Language from, Language to) {
        super(who);

        this.oldLanguage = from;
        this.newLanguage = to;
    }

}
