package me.oragejuice.oragehack.client.features.modules.combat.autototem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface ItemPredicate {
    boolean compare(ItemStack item);
}
