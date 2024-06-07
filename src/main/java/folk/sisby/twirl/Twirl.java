package folk.sisby.twirl;

import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Twirl implements ModInitializer {
    public static final String ID = "twirl";
    public static final Logger LOGGER = LoggerFactory.getLogger(ID);
    public static final TagKey<Enchantment> TWIRLING = TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(ID, "twirling"));

    @Override
    public void onInitialize() {
        LOGGER.info("Ooh ooh! Do a handstand - No no wait, a twirl!");
    }
}
