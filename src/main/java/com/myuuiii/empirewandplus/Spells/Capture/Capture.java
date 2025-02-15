package com.myuuiii.empirewandplus.Spells.Capture;

import com.myuuiii.empirewandplus.Abstracts.Spell;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Capture extends Spell {
    @Override
    public int getReach() {
        return 8;
    }

    @Override
    public double getCloseRange() {
        return 3;
    }

    @Override
    public double getDamage() {
        return 0;
    }

    @Override
    public void forAllNearbyEntities(Entity entity, Location location, Player executingPlayer) {
        if (entity.equals(executingPlayer)) return;

        executingPlayer.addPassenger(entity);
    }

    @Override
    public void atExecutingLocation(Location loc, Player p) {
        p.getWorld().spawnParticle(Particle.REVERSE_PORTAL, loc, 250, 1, 1, 1, 0.1);
        p.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc, 100, 1, 1, 1, 0);

        p.getWorld().playSound(loc, Sound.ENTITY_PIG_SADDLE, 5, 0.85f);
        p.getWorld().playSound(loc, Sound.ENTITY_SHULKER_TELEPORT, 5, 1f);
    }
}
