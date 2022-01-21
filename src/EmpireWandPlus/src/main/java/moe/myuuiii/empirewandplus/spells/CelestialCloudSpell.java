package moe.myuuiii.empirewandplus.spells;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import moe.myuuiii.empirewandplus.Data;
import moe.myuuiii.empirewandplus.Spells;
import moe.myuuiii.empirewandplus.handlers.CloudHandler;

public class CelestialCloudSpell {
	//
	// Settings
	//
	// No settings for this spell

	public static void Execute(Location loc, Player p) {

		if (Data.celestialCloudUsers.contains(p.getUniqueId())) {
			p.sendMessage(Data.prefix + ChatColor.GRAY + "Deactivated " + Spells.CelestialCloud);
			if (!p.getGameMode().equals(GameMode.CREATIVE)) {
				p.setAllowFlight(false);
			}
			CloudHandler.DisableCloud(p.getUniqueId());
		} else {
			p.sendMessage(Data.prefix + ChatColor.GRAY + "Activated " + Spells.CelestialCloud);
			if (!p.getGameMode().equals(GameMode.CREATIVE)) {
				p.setAllowFlight(true);
			}
			Data.celestialCloudUsers.add(p.getUniqueId());
		}
	}
}
