package me.oragejuice.oragehack.client.features.modules.render.brightness;

import me.oragejuice.eventbus.EventHandler;
import me.oragejuice.oragehack.client.api.IListener;
import me.oragejuice.oragehack.client.api.event.PlayerUpdateEvent;
import me.oragejuice.oragehack.client.api.feature.Categories;
import me.oragejuice.oragehack.client.api.feature.Feature;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class Brightness extends Feature {

    public Brightness() {
        super("brightness", Categories.RENDER, 10);
    }

    @EventHandler
    public void onPlayerUpdate(PlayerUpdateEvent event){
        mc.player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION.setPotionName("FullBright"),
                80950,
                1,
                false,
                false));
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.player.removePotionEffect(MobEffects.NIGHT_VISION);
    }

}
