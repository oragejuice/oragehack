package me.oragejuice.oragehack;

import me.oragejuice.oragehack.argument.Argument;
import me.oragejuice.oragehack.argument.Arguments;
import me.oragejuice.oragehack.transform.SimpleTransformer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.commons.ClassRemapper;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.tools.obfuscation.mcp.ObfuscationServiceMCP;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SimpleTweaker implements ITweaker {

    public static final Logger LOGGER = LogManager.getLogger("oragehack");

    private static boolean setupTransformer = false;

    /**
     * The raw game launch arguments that are provided in {@link SimpleTweaker#acceptOptions(List, File, File, String)}
     */
    private List<String> args;

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        (this.args = new ArrayList<>()).addAll(args);
        addArg("gameDir", gameDir);
        addArg("assetsDir", assetsDir);
        addArg("version", profile);
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        LOGGER.info("Hello from tweaker :D");
        MixinBootstrap.init();

        // Find all of the other tweakers that are being loaded
        List<String> tweakClasses = (List<String>) Launch.blackboard.get("TweakClasses");

        // Default to NOTCH obfuscation context
        String obfuscation = ObfuscationServiceMCP.NOTCH;

        // If there are any tweak classes that are packaged under "net.minecraftforge.fml.common.launcher",
        // switch the obfuscation context in the mixin environment to SEARGE
        if (tweakClasses.stream().anyMatch(s -> s.contains("net.minecraftforge.fml.common.launcher"))) {
            obfuscation = ObfuscationServiceMCP.SEARGE;
            LOGGER.info("Discovered FML! Switching to SEARGE mappings.");
        }

        Mixins.addConfiguration("mixins.oragehack.json");

        MixinEnvironment.getDefaultEnvironment().setObfuscationContext(obfuscation);
        MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);
    }

    @Override
    public String getLaunchTarget() {
        return "net.minecraft.client.main.Main";
    }

    @Override
    @SuppressWarnings("unchecked")
    public String[] getLaunchArguments() {
        // Parse the arguments that we are able to pass to the game
        List<Argument> parsed = Arguments.parse(this.args);

        // Parse the arguments that are already being passed to the game
        List<Argument> existing = Arguments.parse((List<String>) Launch.blackboard.get("ArgumentList"));

        // Remove any arguments that conflict with existing ones
        parsed.removeIf(argument -> existing.stream().anyMatch(a -> a.conflicts(argument)));

        // Join back the filtered arguments and pass those to the game
        return Arguments.join(parsed).toArray(new String[0]);
    }

    private void addArg(String label, File file) {
        if (file != null)
            addArg(label, file.getAbsolutePath());
    }

    private void addArg(String label, String value) {
        if (!args.contains("--" + label) && value != null) {
            this.args.add("--" + label);
            this.args.add(value);
        }
    }

    private static void doInitialSetup(LaunchClassLoader classLoader) {
        if (!setupTransformer) {
            classLoader.addClassLoaderExclusion("me.oragejuice.oragehack.");
            classLoader.registerTransformer(SimpleTransformer.class.getName());
            setupTransformer = true;
        }
    }


}
