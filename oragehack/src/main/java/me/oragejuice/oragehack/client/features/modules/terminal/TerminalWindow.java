package me.oragejuice.oragehack.client.features.modules.terminal;


import me.oragejuice.oragehack.Oragehack;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class TerminalWindow extends AbstractWindow {

    //#2d2d2d
    Color background = new Color(0x2d2d2d);
    Color borderColor = new Color(0x797977);
    final int BORDER = 6;
    boolean dragging;
    int diffX;
    int diffY;

    public TerminalWindow(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {

        if(dragging){
            this.x = mouseX + diffX;
            this.y = mouseY + diffY;
        }
        //the outer box (will show as border)
        drawRect(x,y,x+w,y+h, borderColor.getRGB());

        //inner box (for text rendering)
        // -8 to allow room for text box
        drawRect(x+BORDER, y+BORDER, x+w-BORDER, y+h-BORDER-12, background.getRGB());

        drawRect(x+BORDER+1, y+h+12, x+w-BORDER-1, y+h-1, background.getRGB());
        Minecraft.getMinecraft().fontRenderer.drawString("text", x+BORDER, y+h-BORDER, Color.WHITE.getRGB());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if(isInBorder(mouseX, mouseY)){
            Oragehack.LOGGER.info("Clicked in border");
            diffX = this.x - mouseX;
            diffY = this.y - mouseY;
            dragging = true;

        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        dragging = false;
    }

    private boolean isInBorder(int mX, int mY){
        return isInside(mX, mY) && !isInside(mX, mY, x+BORDER, y+BORDER, w-BORDER, h-BORDER);
    }

}
