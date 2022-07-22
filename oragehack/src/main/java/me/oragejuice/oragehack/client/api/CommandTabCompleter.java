package me.oragejuice.oragehack.client.api;

import me.oragejuice.oragehack.Oragehack;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.TabCompleter;
import net.minecraft.util.math.BlockPos;


public class CommandTabCompleter extends TabCompleter {


    public CommandTabCompleter(GuiTextField textFieldIn, boolean hasTargetBlockIn) {
        super(textFieldIn, hasTargetBlockIn);
    }

    @Override
    public void complete(){

        super.complete();

        //if completion breaks the command and removes the prefix
        if(!textField.getText().startsWith("`")){
            Oragehack.LOGGER.info("removed prefix!");
            textField.setText("`" + textField.getText());
        }

    }



    @Override
    public BlockPos getTargetBlockPos() {
        return null;
    }
}
