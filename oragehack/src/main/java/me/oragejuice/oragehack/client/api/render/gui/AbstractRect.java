package me.oragejuice.oragehack.client.api.render.gui;


import me.oragejuice.oragehack.client.Globals;

public class AbstractRect implements IGuiElement, Globals {

    protected int x;
    protected int y;
    protected int width;
    protected int height;


    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {
    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY, int state) {
    }

    @Override
    public void onMouseReleased(int mouseX, int mouseY, int state) {
    }

    @Override
    public void onKeyTyped(char typedChar, int keyCode) {
    }

    public boolean isInside(int mX, int mY){
        return (
                mX > this.x
                &&  mX < this.x + this.width
                && mY > this.y
                && mY < this.y + this.height
        );
    }

    public void updatePosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void updateSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
