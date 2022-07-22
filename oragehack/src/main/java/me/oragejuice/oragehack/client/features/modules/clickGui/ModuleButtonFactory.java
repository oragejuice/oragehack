package me.oragejuice.oragehack.client.features.modules.clickGui;

import me.oragejuice.oragehack.client.api.feature.Feature;

public class ModuleButtonFactory {

    static int offset = 0;

    static int x = 0;

    static int y  = 0;

    static int width = 0;

    static int height = 0;

    public static void reset(){
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        offset = 0;
    }


    public static void setValues(int sx, int sy, int swidth, int sheight){
        x = sx;
        y = sy;
        width = swidth;
        height = sheight;
    }

    public static ModuleButton newModuleButton(Feature f){
        offset += height;
        return new ModuleButton(f, x, y + offset - height, width, height);
    }
}
