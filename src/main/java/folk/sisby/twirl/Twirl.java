package folk.sisby.twirl;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.fabricmc.api.ModInitializer;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.UseAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Twirl implements ModInitializer {
    public static final String ID = "twirl";
    public static final Logger LOGGER = LoggerFactory.getLogger(ID);
    public static final TagKey<Enchantment> TWIRLING = TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(ID, "twirling"));
    public static final TagKey<Item> KEEP_USE = TagKey.of(RegistryKeys.ITEM, Identifier.of(ID, "keep_use"));
    public static final TagKey<Item> KEEP_TICK = TagKey.of(RegistryKeys.ITEM, Identifier.of(ID, "keep_tick"));
    public static final TagKey<Item> KEEP_FINISH = TagKey.of(RegistryKeys.ITEM, Identifier.of(ID, "keep_finish"));
    public static final TagKey<Item> KEEP_STOP = TagKey.of(RegistryKeys.ITEM, Identifier.of(ID, "keep_stop"));
    public static final TagKey<Item> KEEP_ACTION = TagKey.of(RegistryKeys.ITEM, Identifier.of(ID, "keep_action"));
    public static final TagKey<Item> ROTATE_Z = TagKey.of(RegistryKeys.ITEM, Identifier.of(ID, "rotate_z"));
    public static final float TWIRL_SPEED = 20.0f;
    public static final List<UseAction> STYLES = List.of(
        UseAction.SPYGLASS,
        UseAction.NONE,
        UseAction.SPEAR,
        UseAction.NONE,
        UseAction.CROSSBOW,
        UseAction.NONE,
        UseAction.BLOCK,
        UseAction.NONE,
        UseAction.TOOT_HORN,
        UseAction.NONE
    );

    public static int getLevelIn(ItemStack stack, TagKey<Enchantment> tag) {
        ItemEnchantmentsComponent itemEnchantmentsComponent = stack.getOrDefault(
            DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT
        );

        for(Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : itemEnchantmentsComponent.getEnchantmentEntries()) {
            if (entry.getKey().isIn(tag)) {
                return entry.getIntValue();
            }
        }

        return 0;
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Ooh ooh! Do a handstand - No no wait, a twirl!");
    }
}
