package me.oragejuice.oragehack.tweak;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.tools.obfuscation.mcp.ObfuscationServiceMCP;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Tweaker implements ITweaker {

    public static final Logger LOGGER = LogManager.getLogger("OrageTweaker");

    private final List<String> args = new ArrayList<>();

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        this.args.addAll(args);

        if (gameDir != null) {
            this.args.add("--gameDir");
            this.args.add(gameDir.getAbsolutePath());
        }

        if (assetsDir != null) {
            this.args.add("--assetsDir");
            this.args.add(assetsDir.getAbsolutePath());
        }

        if (profile != null) {
            this.args.add("--version");
            this.args.add(profile);
        }    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        LOGGER.info("oragehack loading...");
        MixinBootstrap.init();

        List<String> tweakClasses = (List<String>) Launch.blackboard.get("TweakClasses");
        String obfuscation = ObfuscationServiceMCP.SEARGE;

        Mixins.addConfiguration("mixins.oragehack.json");

        MixinEnvironment.getDefaultEnvironment().setObfuscationContext(obfuscation);
        MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);
    }

    @Override
    public String getLaunchTarget() {
        return "net.minecraft.client.main.Main";
    }

    @Override
    public String[] getLaunchArguments() {
        return ((List<?>) Launch.blackboard.get("ArgumentList")).isEmpty() ? this.args.toArray(new String[0]) : new String[0];
    }
}
