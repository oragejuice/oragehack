package me.oragejuice.oragehack.client.api.render;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;

//TODO learn openGL
public class DrawHelper {
    private static void applyColor(Color color) {
        GL11.glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
    }

    public static void drawLine(float x1, float y1, float x2, float y2, Color color) {
        applyColor(color);
        drawLine(x1, y1, x2, y2);
    }

    private static void drawLine(float x1, float y1, float x2, float y2) {
        enabledGL2D();
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2d(x1, y1);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        disabledGL2D();
    }

    public static void drawRoundedRect(float x1, float y1, float x2, float y2, float radius, Color color) {
        applyColor(color);
        drawRoundedRect(x1, y1, x2, y2, radius);
    }

    private static void drawRoundedRect(float x1, float y1, float x2, float y2, float radius ) {
        enabledGL2D();
        drawLine(x1 + radius, y1, x2 - radius, y1);
        drawLine(x2, y1 + radius, x2, y2 - radius);
        drawLine(x2 - radius, y2, x1 + radius, y2);
        drawLine(x1, y2 - radius, x1, y1 + radius);

        drawArc(x2 - radius, y2 - radius, radius, 270, 360);
        drawArc(x1 + radius, y2 - radius, radius, 180, 270);
        drawArc(x1 + radius, y1 + radius, radius, 90,  180);
        drawArc(x2 - radius, y1 + radius, radius, 0,  90);
        disabledGL2D();
    }

    public static void drawFilledRect(float x1, float y1, float x2, float y2, Color color) {
        applyColor(color);
        drawFilledRect(x1, y1, x2, y2);
    }

    public static void drawRect(float x1, float y1, float x2, float y2, Color color) {
        applyColor(color);
        drawRect(x1, y1, x2, y2);
    }

    public static void drawRect(float x1, float y1, float x2, float y2) {
        enabledGL2D();
        drawLine(x1, y1, x2, y1);
        drawLine(x2, y1, x2, y2);
        drawLine(x2, y2, x1, y2);
        drawLine(x1, y2, x1, y1);
        disabledGL2D();
    }

    private static void drawFilledRect(float x1, float y1, float x2, float y2) {
        enabledGL2D();
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2d(x1, y1);
        GL11.glVertex2d(x1, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glVertex2d(x2, y1);
        GL11.glEnd();
        disabledGL2D();
    }

    public static void drawFilledRoundedRect(float x1, float y1, float x2, float y2, float radius, Color color) {
        applyColor(color);
        drawFilledRoundedRect(x1, y1, x2, y2, radius);
    }
    private static void drawFilledRoundedRect(float x1, float y1, float x2, float y2, float radius) {
        enabledGL2D();
        drawFilledRect(x1, y1 + radius, x1 + radius, y2 - radius);
        drawFilledRect(x2 - radius, y1 + radius, x2, y2 - radius);
        drawFilledRect(x1 + radius, y1, x2 - radius, y2);

        drawFilledArc(x2 - radius, y2 - radius, radius, 270, 360);
        drawFilledArc(x1 + radius, y2 - radius, radius, 180, 270);
        drawFilledArc(x1 + radius, y1 + radius, radius, 90,  180);
        drawFilledArc(x2 - radius, y1 + radius, radius, 0,  90);
        disabledGL2D();
    }

    public static void drawBorderedRoundedRect(float x1, float y1, float x2, float y2, float radius, Color color, Color borderColor) {
        drawFilledRoundedRect(x1, y1, x2, y2, radius, color);
        drawRoundedRect(x1, y1, x2, y2, radius, borderColor);
    }

    private static void drawFilledArc(float x, float y, float r, float start, float end) {
        enabledGL2D();
        GL11.glPushMatrix();
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glVertex2d(x, y);
        for(double k = start; k <= end; k += 1f) {
            GL11.glVertex2d(x+ r*Math.cos(Math.toRadians(k)), y - r * Math.sin(Math.toRadians(k)));
        }
        GL11.glEnd();
        GL11.glPopMatrix();
        disabledGL2D();
    }


    public static void drawArc(float x, float y, float r, float start, float end, Color color) {
        applyColor(color);
        drawArc(x, y, r, start, end);
    }

    private static void drawArc(float x, float y, float r, float start, float end) {
        enabledGL2D();
        GL11.glPushMatrix();
        GL11.glBegin(GL11.GL_LINE_STRIP);
        for(double k = start; k <= end; k += 1f) {
            GL11.glVertex2d(x+ r*Math.cos(Math.toRadians(k)), y - r * Math.sin(Math.toRadians(k)));
        }
        GL11.glEnd();
        GL11.glPopMatrix();
        disabledGL2D();
    }

/*    public static void drawWurst(float x1, float y1, float x2, float y2, float r) {
        drawArc(x1, y1, r, 90, 270);
    }*/

    public static void enabledGL2D() {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableCull();
    }

    public static void disabledGL2D() {
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableCull();
        //GlStateManager.color(1f, 1f, 1f,1f);
    }

}
