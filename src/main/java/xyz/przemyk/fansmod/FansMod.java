package xyz.przemyk.fansmod;

import net.minecraft.state.IntegerProperty;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import xyz.przemyk.fansmod.blocks.*;

@Mod(FansMod.MODID)
public class FansMod {
    public static final String MODID = "fansmod";

    public FansMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("fansmod-common.toml"));
        ConfigurableFanBlock.LEVEL = IntegerProperty.create("level", 0, Config.CONFIGURABLE_FAN_MAX_RANGE.get());

        Registration.init();
    }
}
