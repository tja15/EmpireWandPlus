package moe.myuuiii.empirewandplus.spellEffects;

import moe.myuuiii.empirewandplus.App;
import moe.myuuiii.empirewandplus.Data;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static moe.myuuiii.empirewandplus.Extensions.getNearbyEntities;
import static moe.myuuiii.empirewandplus.generators.FireworkGenerator.getFirework;

public class FirePulseSpellEffect {
    public static void Execute(Snowball s) {
        new BukkitRunnable() {
            public void run() {
                if (Data.firepulses.contains(s)) {
                    if (s.isDead()) {
                        // Executed when the entity is destroyed
                        final List<Entity> near = getNearbyEntities(2.0, s);
                        for (final Entity en : near)
                            if (en instanceof LivingEntity targetEntity)
                                targetEntity.damage(4);
                        this.cancel();
                    }

                    // Executed while the entity is alive
                    launchFirework(s);
                    s.getWorld().spawnParticle(Particle.FLAME, s.getLocation(), 50, 1, 1, 1, 0.1);
                }
            }
        }.runTaskTimer(App._app, 0L, 1L);
    }

    private static void launchFirework(Snowball s) {
        Firework fw = getFirework(s);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(244, 150, 0))
                .withFlicker()
                .build());
        fwMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.fromRGB(0, 0, 0))
                .build());
        fw.setFireworkMeta(fwMeta);
        fw.detonate();
    }
}
