package me.oragejuice.oragehack.client.api.render;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class Color {

    public int red;
    public int green;
    public int blue;
    public int alpha;
    public int value;

    private static final double FACTOR = 0.7;

    // frequently used colors
    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0,  0, 255);
    public static final Color YELLOW = new Color(255,  255, 0);
    public static final Color ORANGE = new Color(229, 109, 4);
    public static final Color DARKER_YELLOW = new Color(255,  255, 0).darker().darker();
    public static final Color WHITE = new Color(255, 255, 255, 255);
    public static final Color BLACK = new Color(0, 0, 0, 255);
    public static final Color HYDRA_PURPLE = new Color(0xFF7a00ff);
    public static final Color GUI_TOP = new Color(0xFF092db0).withAlpha(200);
    public static final Color HYDRA_BLUE = new Color(0xFF092db0);
    public static final Color HYDRA_BLUE_ALPHA = HYDRA_BLUE.getTransparent();
    public static final Color HYDRA_PURPLE_ALPHA = HYDRA_PURPLE.getTransparent();
    public static final Color TEXT_GRAY = new Color(0xFF999999);
    public static final Color TEXT_BRIGHTER = TEXT_GRAY.brighter();
    public static final Color BACKGROUND = new Color(22, 22, 22, 86);
    public static final Color GUI_BACKGROUND = new Color(22, 22, 22, 255);
    public static final Color GUI_HIGHLIGHT = new Color(111, 111, 111, 255);
    public static final Color HOVER_BACKGROUND = new Color(44, 44, 44, 112);
    public static final Color HOVER_BACKGROUND_BRIGHTER = new Color(44, 44, 44, 112).brighter();
    public static final Color DARKER_BACKGROUND = new Color(11, 11, 11, 112);
    public static final Color DARKER_BACKGROUND_TRANSPARENT = new Color(11, 11, 11, 0);
    public static final Color DARKER_BACKGROUND_SOLID = new Color(11, 11, 11, 255);
    public static final Color DARKER_HOVER = new Color(33, 33, 33, 137);
    public static final Color ALPHA = new Color(0, 0, 0, 0);

    /**
     * Used internally, before this class I stored colors as a vec3f, so this prevented lots of refactoring.
     * Don't use this
     * @param vec In vec
     */
    @Deprecated
    public Color(Vec3d vec) {
        this.red = (int) (vec.x * 255);
        this.green = (int) (vec.y * 255);
        this.blue = (int) (vec.z * 255);
        this.alpha = 255;
        updateInt();
    }

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = 255;
        updateInt();
    }

    public Color(float red, float green, float blue) {
        this.red = MathHelper.clamp((int) (red * 255), 0, 255);
        this.green = MathHelper.clamp((int) (green * 255), 0, 255);
        this.blue = MathHelper.clamp((int) (blue * 255), 0, 255);
        this.alpha = 255;
        updateInt();
    }

    public Color(int red, int green, int blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
        updateInt();
    }

    public Color(float red, float green, float blue, float alpha) {
        this.red = MathHelper.clamp((int) (red * 255), 0, 255);
        this.green = MathHelper.clamp((int) (green * 255), 0, 255);
        this.blue = MathHelper.clamp((int) (blue * 255), 0, 255);
        this.alpha = MathHelper.clamp((int) (alpha * 255), 0, 255);
        updateInt();
    }

    /**
     * Please provide readable rgb values if practical
     * @param argb Packed argb int
     */
    @Deprecated
    public Color(int argb) {
        value = 0xff000000 | argb;
        this.alpha = (value >> 24) & 0xff;
        this.red = (value >> 16) & 0xFF;
        this.green = (value >> 8) & 0xFF;
        this.blue = (value) & 0xFF;

//        System.out.println(TEXT_GRAY.toString());
    }

    private void updateInt() {
        value = ((alpha & 0xFF) << 24) |
                ((red & 0xFF) << 16) |
                ((green & 0xFF) << 8)  |
                ((blue & 0xFF));
    }

    /**
     *
     * @return Packed int color. Used for some minecraft rendering functions, most notably text rendering
     */
    public int getInt() {
        return value;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
        updateInt();
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
        updateInt();
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
        updateInt();
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
        updateInt();
    }

    public void set(int r, int g, int b, int a) {
        this.red = r;
        this.green = g;
        this.blue = b;
        this.alpha = a;
        updateInt();
    }

    public void set(int r, int g, int b) {
        this.red = r;
        this.green = g;
        this.blue = b;
        updateInt();
    }

    /**
     * Please provide readable rgba values instead
     * @param value
     */
    @Deprecated
    public void set(int value) {
//        value = 0xff000000 | argb;
        this.alpha = (value >> 24) & 0xff;
        this.red = (value >> 16) & 0xFF;
        this.green = (value >> 8) & 0xFF;
        this.blue = (value) & 0xFF;
    }

    public static Color getHSBColor(float h, float s, float b) {
        return new Color(getHSB(h, s, b));
    }

    public static int getHSB(float h, float s, float b) {
        return HSBtoRGB(h, s, b);
    }

    public static int HSBtoRGB(float hue, float saturation, float brightness) {
        int r = 0, g = 0, b = 0;
        if (saturation == 0) {
            r = g = b = (int) (brightness * 255.0f + 0.5f);
        } else {
            float h = (hue - (float)Math.floor(hue)) * 6.0f;
            float f = h - (float)java.lang.Math.floor(h);
            float p = brightness * (1.0f - saturation);
            float q = brightness * (1.0f - saturation * f);
            float t = brightness * (1.0f - (saturation * (1.0f - f)));
            switch ((int) h) {
                case 0: {
                    r = (int) (brightness * 255.0f + 0.5f);
                    g = (int) (t * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                    break;
                }
                case 1: {
                    r = (int) (q * 255.0f + 0.5f);
                    g = (int) (brightness * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                    break;
                }
                case 2: {
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (brightness * 255.0f + 0.5f);
                    b = (int) (t * 255.0f + 0.5f);
                    break;
                }
                case 3: {
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (q * 255.0f + 0.5f);
                    b = (int) (brightness * 255.0f + 0.5f);
                    break;
                }
                case 4: {
                    r = (int) (t * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (brightness * 255.0f + 0.5f);
                    break;
                }
                case 5: {
                    r = (int) (brightness * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (q * 255.0f + 0.5f);
                    break;
                }
            }
        }
        return 0xff000000 | (r << 16) | (g << 8) | (b);
    }

    public static float[] RGBtoHSB(int r, int g, int b, float[] hsbvals) {
        float hue, saturation, brightness;
        if (hsbvals == null) {
            hsbvals = new float[3];
        }
        int cmax = Math.max(r, g);
        if (b > cmax) cmax = b;
        int cmin = Math.min(r, g);
        if (b < cmin) cmin = b;

        brightness = ((float) cmax) / 255.0f;
        if (cmax != 0)
            saturation = ((float) (cmax - cmin)) / ((float) cmax);
        else
            saturation = 0;
        if (saturation == 0)
            hue = 0;
        else {
            float redc = ((float) (cmax - r)) / ((float) (cmax - cmin));
            float greenc = ((float) (cmax - g)) / ((float) (cmax - cmin));
            float bluec = ((float) (cmax - b)) / ((float) (cmax - cmin));
            if (r == cmax)
                hue = bluec - greenc;
            else if (g == cmax)
                hue = 2.0f + redc - bluec;
            else
                hue = 4.0f + greenc - redc;
            hue = hue / 6.0f;
            if (hue < 0)
                hue = hue + 1.0f;
        }
        hsbvals[0] = hue;
        hsbvals[1] = saturation;
        hsbvals[2] = brightness;
        return hsbvals;
    }

    public Color getTransparent() {
        return new Color(this.red, this.green, this.blue, 0);
    }

    public Color darker() {
        return new Color(Math.max((int)(getRed() * FACTOR), 0),
                Math.max((int)(getGreen() * FACTOR), 0),
                Math.max((int)(getBlue() * FACTOR), 0),
                getAlpha());
    }

    public Color brighter() {

        /* From 2D group:
         * 1. black.brighter() should return grey
         * 2. applying brighter to blue will always return blue, brighter
         * 3. non pure color (non zero rgb) will eventually return white
         */
        int i = (int)(1.0 / (1.0-FACTOR));
        if (red == 0 && green == 0 && blue == 0) {
            return new Color(i, i, i, alpha);
        }

        if (red > 0 && red < i) red = i;
        if (green > 0 && green < i) green = i;
        if (blue > 0 && blue < i) blue = i;

        return new Color(
                Math.min((int)(red / FACTOR), 255),
                Math.min((int)(green / FACTOR), 255),
                Math.min((int)(blue / FACTOR), 255),
                alpha
        );
    }

    public Color withAlpha(int a) {
        return new Color(this.red, this.green, this.blue, a);
    }

    public Color copy() {
        return new Color(this.red, this.green, this.blue, this.alpha);
    }

    @Override
    public String toString() {
        return getInt() + "";
    }

}
