package mc.subsilence.empirewandplus.spellEffects;

import java.util.List;

import org.bukkit.Particle;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;

import mc.subsilence.empirewandplus.App;
import mc.subsilence.empirewandplus.Data;

public class LightningSpellEffect {

	//
	// Settings
	//
	private static double _closeRange = 10;
	private static double _damage = 10;
	private static int _explosionSize = 10;

	public static void Execute(Snowball s) {
		new BukkitRunnable() {
			public void run() {
				if (Data.lightningBolts.contains(s)) {
					if (s.isDead()) {
						// Executed when the entity is destroyed
						s.getWorld().strikeLightning(s.getLocation());
						s.getWorld().createExplosion(s.getLocation(), _explosionSize);
						final List<Entity> near = (List<Entity>) s.getLocation().getWorld().getEntities();
						for (final Entity en : near) {
							if (en.getLocation().distance(s.getLocation()) <= _closeRange && en instanceof Damageable) {
								((Damageable) en).damage(_damage);
							}
						}
						this.cancel();
					}

					// Executed while the entity is alive
					s.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, s.getLocation(), 250, 0.1, 0.1, 0.1, 0.1);
					s.getWorld().spawnParticle(Particle.CLOUD, s.getLocation(), 125, 0.1, 0.1, 0.1, 0.2);
				}
			}
		}.runTaskTimer(App._app, 0L, 1L);

	}
}
