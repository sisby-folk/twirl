package folk.sisby.twirl;

import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
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
    public static final float TWIRL_SPEED = 30.0f;
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

    @Override
    public void onInitialize() {
        LOGGER.info("Ooh ooh! Do a handstand - No no wait, a twirl!");
    }
}
