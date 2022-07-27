package me.oragejuice.oragehack.client.features.modules;

import me.oragejuice.eventbus.EventHandler;
import me.oragejuice.oragehack.client.api.event.Render3dEvent;
import me.oragejuice.oragehack.client.api.feature.Categories;
import me.oragejuice.oragehack.client.api.feature.Feature;
import me.oragejuice.oragehack.client.api.settings.GenericSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class Esp extends Feature {

    public GenericSetting<Boolean> storage = new GenericSetting<>("Storages", true);
    public GenericSetting<Float> outlineWidth = new GenericSetting<>("Outline Width", 1f);
    public GenericSetting<Boolean> item = new GenericSetting<>("Items", true);
    public GenericSetting<Boolean> player = new GenericSetting<>("Players", true);
    GenericSetting<Boolean> hostile = new GenericSetting<>("Hostile", true);
    GenericSetting<Boolean> passive = new GenericSetting<>("Passive", true);
    GenericSetting<Boolean> neutral = new GenericSetting<>("Neutral", true);
    GenericSetting<Boolean> crystals = new GenericSetting<>("Crystals", true);
    GenericSetting<Boolean> other = new GenericSetting<>("Other", false);
    GenericSetting<Boolean> hole = new GenericSetting<>("Holes", true);
    GenericSetting<Boolean> voidHoles = new GenericSetting<>("Void Holes", true);

    GenericSetting<Integer> distance = new GenericSetting<Integer>("Hole Distance", 10);

    GenericSetting<Boolean> textBackground = new GenericSetting<>("Text Background", true);

    GenericSetting<Boolean> potionFlag = new GenericSetting<>("Flags", true);

    GenericSetting<Integer> safeColor = new GenericSetting<>("Safe Holes", 0x00ff00);
    GenericSetting<Integer> semisafeColor = new GenericSetting<>("Semisafe Holes", 0x00ff33);
    GenericSetting<Integer> voidColor = new GenericSetting<>("Void Holes", 0xff0000);
    GenericSetting<Integer> chestColor = new GenericSetting<>("Chests", 0xffb600);
    GenericSetting<Integer> shulkerColor = new GenericSetting<>("Shulkers", 0xff00ae);
    GenericSetting<Integer> echestColor = new GenericSetting<>("Echests", 0x8300ff);
    GenericSetting<Integer> playerColor = new GenericSetting<>("Players", 0xff7700);
    GenericSetting<Integer> passiveColor = new GenericSetting<>("Passive", 0xa1ff00);
    GenericSetting<Integer> hostileColor = new GenericSetting<>("Hostile", 0xff2100);
    GenericSetting<Integer> neutralColor = new GenericSetting<>("Neutral", 0xeeff00);
    GenericSetting<Integer> itemColor = new GenericSetting<>("Items", 0x0048ff);
    GenericSetting<Integer> crystalColor = new GenericSetting<>("Crystals", 0xffffff);
    GenericSetting<Integer> miscColor = new GenericSetting<>("Misc", 0xe28902);

    private final ArrayList<AxisAlignedBB> items = new ArrayList<>();
    private final ArrayList<BlockPos> safe = new ArrayList<>();
    private final ArrayList<BlockPos> semisafe = new ArrayList<>();
    private final ArrayList<BlockPos> voids = new ArrayList<>();

    private final List<EntityLivingBase> entities = new ArrayList<>();
    private final ArrayList<AxisAlignedBB> chests = new ArrayList<>();
    private final ArrayList<AxisAlignedBB> trappedChests = new ArrayList<>();
    private final ArrayList<BlockPos> shulkers = new ArrayList<>();
    private final ArrayList<BlockPos> hoppers = new ArrayList<>();
    private final ArrayList<BlockPos> barrels = new ArrayList<>();
    private final ArrayList<BlockPos> enderChests = new ArrayList<>();

    public static Esp instance;

    public Esp() {
        super("ESP", Categories.RENDER);
        this.registerSettings(storage, outlineWidth,
                item, player, hostile, passive, neutral, crystals, other,
                hole, voidHoles, distance,
                textBackground, potionFlag,
                safeColor, semisafeColor, voidColor, chestColor, shulkerColor, echestColor, playerColor, passiveColor, hostileColor, neutralColor, itemColor, crystalColor, miscColor);
        instance = this;
    }

    @EventHandler
    public void onRenderWorld(Render3dEvent e) {


    }

    int timer = 0;
//    @EventTarget
//    public void onUpdate(EventUpdate ev) {
//
//        playerCards.clear();
//        if(cards.isToggle() && player.isToggle()) {
//            for (AbstractClientPlayerEntity worldPlayer : mc.world.getPlayers()) {
//                if(worldPlayer == mc.player)
//                    continue;
//
//                if(Antibots.instance.isToggle() && Antibots.instance.isBot(worldPlayer.getUuid())) {
//                    continue;
//                }
//
//                playerCards.put(worldPlayer, new EspCard(worldPlayer, burrowFlag.isToggle(), surroundFlag.isToggle(), potionFlag.isToggle(), idFlag.isToggle(), healthFlag.isToggle(), gamemodeFlag.isToggle(), pingFlag.isToggle(), showShulker.isToggle()));
//            }
//        }
//
//        safe.clear();
//        semisafe.clear();
//        voids.clear();
//        if(hole.isToggle()) {
//            if(hole.isToggle()) {
//                List<BlockPos> blockPosList = WorldUtils.getSphere(mc.player.getBlockPos(), distance.getCurrentValueInt());
//                for(BlockPos pos : blockPosList) {
//
//                    if (!mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR)) {
//                        continue;
//                    }
//
//                    if (!mc.world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(Blocks.AIR)) {
//                        continue;
//                    }
//
//                    if (!mc.world.getBlockState(pos.add(0, 2, 0)).getBlock().equals(Blocks.AIR)) {
//                        continue;
//                    }
//
//                    boolean safe = true;
//                    boolean semisafe = true;
//
//                    for(BlockPos b : new BlockPos[] {pos.down(), pos.north(), pos.east(), pos.south(), pos.west()}) {
//                        Block block = mc.world.getBlockState(b).getBlock();
//                        if(block != Blocks.BEDROCK) {
//                            safe = false;
//                            if (block != Blocks.OBSIDIAN && block != Blocks.NETHERITE_BLOCK && block != Blocks.CRYING_OBSIDIAN) {
//                                semisafe = false;
//                                break;
//                            }
//                        }
//                    }
//
//                    if(safe) {
//                        this.safe.add(pos);
//                    } else if (semisafe) {
//                        this.semisafe.add(pos);
//                    }
//                }
//            }
//        }
//
//        if(voidHoles.isToggle()) {
//            double y = mc.player.getY();
//            if(mc.world.getDimension().minY() != 0) {
//                if(y < -49) {
//                    for (int i = (int) (mc.player.getX() - 8); i < mc.player.getX() + 8; i++) {
//                        for (int j = (int) (mc.player.getZ() - 8); j < mc.player.getZ() + 8; j++) {
//                            BlockPos bp = new BlockPos(i, -64, j);
//                            if(mc.world.getBlockState(bp).getBlock() != Blocks.BEDROCK) {
//                                voids.add(bp);
//                            }
//                        }
//                    }
//                }
//            } else if (y < 15) {
//                for (int i = (int) (mc.player.getX() - 8); i < mc.player.getX() + 8; i++) {
//                    for (int j = (int) (mc.player.getZ() - 8); j < mc.player.getZ() + 8; j++) {
//                        BlockPos bp = new BlockPos(i, 0, j);
//                        if(mc.world.getBlockState(bp).getBlock() != Blocks.BEDROCK) {
//                            voids.add(bp);
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private AxisAlignedBB getAxisAlignedBBFromChest(ChestBlockEntity chestBE) {
//        BlockState state = chestBE.getCachedState();
//        if(!state.contains(ChestBlock.CHEST_TYPE))
//            return null;
//
//        ChestType chestType = state.get(ChestBlock.CHEST_TYPE);
//
//        // ignore other block in double chest
//        if(chestType == ChestType.LEFT)
//            return null;
//
//        BlockPos pos = chestBE.getPos();
//        if(!BlockUtils.canBeClicked(pos))
//            return null;
//
//        // larger AxisAlignedBB for double chest
//        if(chestType != ChestType.SINGLE) {
//            Direction d = ChestBlock.getFacing(state);
//            return new AxisAlignedBB(pos).stretch(d.getOffsetX(), d.getOffsetY(), d.getOffsetZ());
//        } else {
//            return new AxisAlignedBB(pos);
//        }
//    }
//
//    static final int fontOffset = -FontUtils.getFontHeight();
//
//    @EventTarget
//    private void onRender2D(EventRender2D event) {
//
//        MatrixStack matrices = new MatrixStack();
//        Camera camera = mc.getEntityRenderDispatcher().camera;
//        Vec3d camPos = camera.getPos();
//
//        final float linewidth = overlayLinewidth.getCurrentValueFloat();
//        final float innerOffset = linewidth / 3;
//        final float doubleOffset = innerOffset * 2;
//        for (PlayerEntity pec : playerCards.keySet()) {
//            if(!pec.shouldRender(camPos.x, camPos.y, camPos.z)) {
//                continue;
//            }
//
//            Vec3d pos = EntityUtils.getInterpolatedPos(pec, mc.getTickDelta());
//            drawOverlayAxisAlignedBB(matrices, pec, pos, linewidth, innerOffset, doubleOffset);
//        }
//    }
//
//    private void drawOverlayAxisAlignedBB(MatrixStack matrices, LivingEntity e, Vec3d interPos, float linewidth, float innerOffset, float doubleOffset) {
//
//        AxisAlignedBB bb = e.getBoundingAxisAlignedBB();
//        double halfX = bb.getXLength() / 2;
//        double halfZ = bb.getZLength() / 2;
//        final Vec3d[] corners = {
//                new Vec3d(interPos.x - halfX, interPos.y, interPos.z + halfZ),
//                new Vec3d(interPos.x + halfX, interPos.y, interPos.z - halfZ),
//                new Vec3d(interPos.x - halfX, interPos.y, interPos.z + halfZ),
//                new Vec3d(interPos.x + halfX, interPos.y, interPos.z + halfZ),
//
//                new Vec3d(interPos.x - halfX, interPos.y + bb.getYLength(), interPos.z - halfZ),
//                new Vec3d(interPos.x + halfX, interPos.y + bb.getYLength(), interPos.z - halfZ),
//                new Vec3d(interPos.x - halfX, interPos.y + bb.getYLength(), interPos.z + halfZ),
//                new Vec3d(interPos.x + halfX, interPos.y + bb.getYLength(), interPos.z + halfZ)
//        };
//
//        float x = Float.MAX_VALUE;
//        float y = Float.MAX_VALUE;
//        float x2 = Float.MIN_VALUE;
//        float y2 = Float.MIN_VALUE;
//
//        boolean visible = false;
//        for (Vec3d vec : corners) {
//            final ScreenPos projection = Render2D.instance.worldToScreen(vec.x, vec.y, vec.z);
//            if(projection.visible) {
//                visible = true;
//            }
//
//            x = Math.min(x, projection.x);
//            y = Math.min(y, projection.y);
//
//            x2 = Math.max(x2, projection.x);
//            y2 = Math.max(y2, projection.y);
//        }
//
//        float width = x2 - x;
//        float height = y2 - y;
//
//        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
//
//        if(drawBox.isToggle() && visible) {
//            Matrix4f matrix = matrices.peek().getPositionMatrix();
//            RenderSystem.enableBlend();
//            RenderSystem.disableTexture();
//            RenderSystem.defaultBlendFunc();
//
//            RenderSystem.setShader(GameRenderer::getPositionColorShader);
//            bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
//
//            Render2D.instance.drawRectVertices(bufferBuilder, matrix, x - linewidth, y - linewidth, x + width + linewidth, y, Color.BLACK); // top
//            Render2D.instance.drawRectVertices(bufferBuilder, matrix, x - linewidth, y, x, y + height, Color.BLACK); // left
//            Render2D.instance.drawRectVertices(bufferBuilder, matrix, x + width, y, x + width + linewidth, y + height, Color.BLACK); // right
//            Render2D.instance.drawRectVertices(bufferBuilder, matrix, x - linewidth, y + height, x + width + linewidth, y + height + linewidth, Color.BLACK); // bottom
//
//            // inner lines
//            Render2D.instance.drawRectVertices(bufferBuilder, matrix, x - doubleOffset, y - doubleOffset, x + width + doubleOffset, y - innerOffset, Color.RED); // top
//            Render2D.instance.drawRectVertices(bufferBuilder, matrix, x - doubleOffset, y - doubleOffset, x - innerOffset, y + height + innerOffset, Color.RED); // left
//            Render2D.instance.drawRectVertices(bufferBuilder, matrix, x + width + innerOffset, y - innerOffset, x + width + doubleOffset, y + height + innerOffset, Color.RED); // right
//            Render2D.instance.drawRectVertices(bufferBuilder, matrix, x - doubleOffset, y + height + innerOffset, x + width + doubleOffset, y + height + doubleOffset, Color.RED); // bottom
//
//            BufferRenderer.drawWithShader(bufferBuilder.end());
//            GL11.glEnable(GL11.GL_TEXTURE_2D);
////            RenderSystem.enableTexture();
//            GL11.glDisable(GL11.GL_BLEND);
////            RenderSystem.disableBlend();
//        }
//
//        matrices.push();
//        matrices.translate(x + width + linewidth * 2, y, 0);
//        matrices.scale(0.85f, 0.85f, 0.85f);
////        matrices.scale(1f/ Render2D.instance.getScaleFactor(), 1f/Render2D.instance.getScaleFactor(), 1f/Render2D.instance.getScaleFactor());
////        matrices.scale(Size.SCALEF, Size.SCALEF, Size.SCALEF);
//        EspCard card = playerCards.get((PlayerEntity) e);
//        float renderY = 0;
//
//        for (Map.Entry<String, Integer> se : card.getFlags().entrySet()) {
//            FontUtils.drawStringWithShadow(matrices, se.getKey(), 0, renderY, se.getValue());
//            renderY -= fontOffset;
//        }
//
//        matrices.pop();
//
//        ScreenPos top = new ScreenPos(x + width / 2, y - 3 - FontUtils.FONT_HEIGHT, true);
//        float renderX = (top.x - (card.items.length / 2f) * 16f);
//        renderY = top.y - 17;
//        for (int i = 0; i < card.items.length; i++) {
//            ItemStack renderItem = card.items[i];
//            Render2D.instance.renderGuiItemIcon(renderItem, renderX, renderY, 1);
//            Render2D.instance.renderGuiItemOverlay(matrices, mc.textRenderer, renderItem, renderX, renderY, 0);
//            if(renderItem.hasEnchantments() || (armorPercentFlag.isToggle() && renderItem.isDamageable())) {
//                float enchantmentY = -2;
//                matrices.push();
//                matrices.translate(renderX, renderY + fontOffset + 16.5, 500);
//                matrices.scale(0.6f, 0.6f, 0.6f);
////                matrices.scale(1f/ Render2D.instance.getScaleFactor(), 1f/Render2D.instance.getScaleFactor(), 1f/Render2D.instance.getScaleFactor());
////                matrices.scale(Size.SCALEF, Size.SCALEF, Size.SCALEF);
//                if(renderItem.hasEnchantments()) {
//                    if(card.names[i][0].equals("max")) {
//                        FontUtils.drawStringWithShadow(matrices, "max", 0, enchantmentY, Color.RED.value);
//                        enchantmentY += fontOffset - 1;
//                    } else {
//                        for (String ench : card.names[i]) {
//                            FontUtils.drawStringWithShadow(matrices, ench, 0, enchantmentY, -1);
//                            enchantmentY += fontOffset - 1;
//                        }
//                    }
//                }
//
//                if(renderItem.isDamageable()) {
//                    float percent = 1 - (float) renderItem.getDamage() / (float) renderItem.getMaxDamage();
//                    FontUtils.drawStringWithShadow(matrices, ((int) Math.floor(percent * 100)) + "%", 0, enchantmentY, renderItem.getItemBarColor());
//                }
//
//                matrices.pop();
//            }
//            renderX += 16;
//        }
//
//        if(card.shulker != null) {
////            x -= 15 * 9;
////            y -= 30;
//            x = (top.x - 4.5f * 16f); // TODO - align
//            y -= 79;
//
//            width = 15 * 9 + linewidth + 1;
//            height = 15 * 3 + linewidth + 1;
//
//            RenderSystem.enableBlend();
//            RenderSystem.disableTexture();
//            RenderSystem.defaultBlendFunc();
//            RenderSystem.setShader(GameRenderer::getPositionColorShader);
//            bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
//            Matrix4f matrix = matrices.peek().getPositionMatrix();
//
//            bufferBuilder.vertex(matrix, x, y + height, -2).color(0.22f, 0.22f, 0.22f, 0.3f).next();
//            bufferBuilder.vertex(matrix, x + width, y + height, -2).color(0.22f, 0.22f, 0.22f, 0.3f).next();
//            bufferBuilder.vertex(matrix, x + width, y, -2).color(0.22f, 0.22f, 0.22f, 0.3f).next();
//            bufferBuilder.vertex(matrix, x, y, -2).color(0.22f, 0.22f, 0.22f, 0.3f).next();
//
//            linewidth /= 2f;
//
//            Render2D.instance.drawRectVertices(bufferBuilder, matrix, x - linewidth, y - linewidth, x + width + linewidth, y, card.shulkerColor); // top
//            Render2D.instance.drawRectVertices(bufferBuilder, matrix, x - linewidth, y, x, y + height, card.shulkerColor); // left
//            Render2D.instance.drawRectVertices(bufferBuilder, matrix, x + width, y, x + width + linewidth, y + height, card.shulkerColor); // right
//            Render2D.instance.drawRectVertices(bufferBuilder, matrix, x - linewidth, y + height, x + width + linewidth, y + height + linewidth, card.shulkerColor); // bottom
//
//            BufferRenderer.drawWithShader(bufferBuilder.end());
//            RenderSystem.enableTexture();
//            RenderSystem.disableBlend();
//
//            x = (top.x - 4.5f * 16f); // TODO - align
//            ItemStack[] cs = card.shulker;
//            for (int i = 0; i < cs.length; i++) {
//                Render2D.instance.renderGuiItemIcon(cs[i], x, y, 1f);
//                Render2D.instance.renderGuiItemOverlay(matrices, mc.textRenderer, cs[i], x, y, 0);
//                if(i == 8 || i == 17) {
//                    y += 15;
//                    x -= 15 * 8;
//                } else {
//                    x += 15;
//                }
//            }
//        }
//
//        float fw = FontUtils.getStringWidth(card.nameplateText);
//        if(textBackground.isToggle()) {
//            Render2D.instance.drawRectWH(matrices, top.x -(fw / 2f), top.y, fw + 1, FontUtils.FONT_HEIGHT, textBackgroundColor.getColor());
//            FontUtils.drawStringWithShadow(matrices, card.nameplateText, top.x -(fw / 2f), top.y, -1);
//        }
//    }
//
//    @EventTarget
//    public void onRender3D(EventRender3D event) {
//
//        if(shader != null && uEffectTime != null) {
//            uEffectTime.set((float) (System.currentTimeMillis() - shaderStartTime));
//        }
//
//        Render3D.instance.setup();
//
//        Matrix4f matrix = Render3D.instance.getMatrices().peek().getPositionMatrix();
//        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
//
//        BlockPos camPos = Render3D.instance.getCameraBlockPos();
//        final int regionX = (camPos.getX() >> 9) * 512;
//        final int regionZ = (camPos.getZ() >> 9) * 512;
//
//        MatrixStack matrices = Render3D.instance.getMatrices();
//
//        if(this.hole.isToggle()) {
//
//            RenderSystem.setShaderColor(safeColor.getRed() / 255f, safeColor.getGreen() / 255f, safeColor.getBlue() / 255f,  safeColor.getAlpha() / 255f);
//            RenderSystem.setShader(GameRenderer::getPositionShader);
//            bufferBuilder.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION);
//            Color c = safeColor.getColor();
//            for (BlockPos AxisAlignedBB : safe) {
//                matrices.push();
//                matrices.translate(AxisAlignedBB.getX() -regionX, AxisAlignedBB.getY(), AxisAlignedBB.getZ() -regionZ);
//                drawHoleOutline(bufferBuilder, matrices.peek().getPositionMatrix());
//                matrices.pop();
//            }
//            BufferRenderer.drawWithShader(bufferBuilder.end());
//
//            RenderSystem.setShaderColor(semisafeColor.getRed() / 255f, semisafeColor.getGreen() / 255f, semisafeColor.getBlue() / 255f,  semisafeColor.getAlpha() / 255f);
//            RenderSystem.setShader(GameRenderer::getPositionShader);
//            bufferBuilder.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION);
//            for (BlockPos AxisAlignedBB : semisafe) {
//                matrices.push();
//                matrices.translate(AxisAlignedBB.getX() -regionX, AxisAlignedBB.getY(), AxisAlignedBB.getZ() -regionZ);
//                drawHoleOutline(bufferBuilder, matrices.peek().getPositionMatrix());
//                matrices.pop();
//            }
//            BufferRenderer.drawWithShader(bufferBuilder.end());
//
//            if(glow.isToggle()) {
//                updateHoleConstants();
//                RenderSystem.setShader(GameRenderer::getPositionColorShader);
//                bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
//                for (BlockPos AxisAlignedBB : safe) {
//                    matrices.push();
//                    matrices.translate(AxisAlignedBB.getX() -regionX, AxisAlignedBB.getY(), AxisAlignedBB.getZ() -regionZ);
//                    drawHoleQuads(bufferBuilder, matrices.peek().getPositionMatrix(), c, Vec3d.ofCenter(AxisAlignedBB).distanceTo(playerInterpos));
//                    matrices.pop();
//                }
//                BufferRenderer.drawWithShader(bufferBuilder.end());
//
//                RenderSystem.setShader(GameRenderer::getPositionColorShader);
//                bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
//                c = semisafeColor.getColor();
//                for (BlockPos AxisAlignedBB : semisafe) {
//                    matrices.push();
//                    matrices.translate(AxisAlignedBB.getX() -regionX, AxisAlignedBB.getY(), AxisAlignedBB.getZ() -regionZ);
//                    drawHoleQuads(bufferBuilder, matrices.peek().getPositionMatrix(), c, Vec3d.ofCenter(AxisAlignedBB).distanceTo(playerInterpos));
//                    matrices.pop();
//                }
//                BufferRenderer.drawWithShader(bufferBuilder.end());
//            }
//        }
//
//        if(voidHoles.isToggle()) {
//            RenderSystem.setShaderColor(voidColor.getRed() / 255f, voidColor.getGreen() / 255f, voidColor.getBlue() / 255f,  voidColor.getAlpha() / 255f);
//            RenderSystem.setShader(GameRenderer::getPositionShader);
//            bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);
//            for (BlockPos bb : voids) {
//                float f = bb.getX();
//                float f2 = bb.getX() + 1;
//                float y = bb.getY();
//                float f3 = bb.getZ();
//                float f4 = bb.getZ() + 1;
//                bufferBuilder.vertex(matrix, f, y, f3).next();
//                bufferBuilder.vertex(matrix, f, y, f4).next();
//                bufferBuilder.vertex(matrix, f2, y, f4).next();
//                bufferBuilder.vertex(matrix, f2, y, f3).next();
//            }
//            BufferRenderer.drawWithShader(bufferBuilder.end());
//        }
//
//        if(blockBreaking.isToggle()) {
//            for (BlockBreakingInfo value : mc.worldRenderer.blockBreakingInfos.values()) {
//                float stage;
//                if(FastBreak.instance.isToggle()) {
//                    stage = MathUtils.clamp((float) value.getStage() / (FastBreak.instance.damage.getCurrentValueFloat() * 9f), 0, 1);
//                } else {
//                    stage = MathUtils.clamp((float) value.getStage() / 9f, 0, 1);
//                }
//
//                stage /= 2;
//
//                Render3D.instance.drawAxisAlignedBB(new AxisAlignedBB(value.getPos().getX() + 0.5, value.getPos().getY() + 0.5, value.getPos().getZ() + 0.5, value.getPos().getX() + 0.5, value.getPos().getY() + 0.5, value.getPos().getZ() + 0.5).expand(stage, stage, stage), blockBreakingColor.getColor());
//            }
//        }
//
//        Render3D.instance.cleanup();
//    }
//
//    @Override
//    public void onEnable() {
//        MinecraftClient.getInstance().execute(new Runnable() {
//            @Override
//            public void run() {
//                load();
//                shaderAlpha.executeClickAction();
//                outlineWidth.executeClickAction();
//                doFade.executeClickAction();
//            }
//        });
//        while (shader == null) {
//            Thread.onSpinWait();
//        }
//        super.onEnable();
//    }
//
//    public ArrayList<AxisAlignedBB> getItems() {
//        return items;
//    }
//
    public Integer getColor(Entity e) {
        if(e == mc.player.getRidingEntity())
            return null;
        if(e instanceof EntityLivingBase) {
            if(e instanceof EntityPlayer) {
                if(!player.getValue())
                    return null;
                if(e == mc.player) {
                    return null;
                }
                return playerColor.getValue();
            }
//            EntityUtils.EntityAgression ag = EntityUtils.getEntityAgression(e);
//            if (ag == EntityUtils.EntityAgression.HOSTILE) {
//                if(!hostile.isToggle())
//                    return null;
//                return hostileColor.getColor();
//            } else if (ag == EntityUtils.EntityAgression.NEUTRAL) {
//                if(!neutral.isToggle())
//                    return null;
//                return neutralColor.getColor();
//            } else {
//                if(!passive.isToggle())
//                    return null;
//                return passiveColor.getColor();
//            }
            return passiveColor.getValue();
        } else if(e instanceof EntityItem) {
            if(item.getValue()) {
                return itemColor.getValue();
            }
            return null;
        } else if (e instanceof EntityEnderCrystal) {
            if(!crystals.getValue())
                return null;
            return crystalColor.getValue();
        }

        if(other.getValue()) {
            return miscColor.getValue();
        }

        return null;
    }
//
//    public Color getShaderColor(BlockEntity e) {
//        if(e instanceof EnderChestBlockEntity) {
//            return echestColor.getColor();
//        } else if (e instanceof ChestBlockEntity) {
//            if(e instanceof TrappedChestBlockEntity) {
//                return trappedChestColor.getColor();
//            }
//            return chestColor.getColor();
//        } else if (e instanceof ShulkerAxisAlignedBBBlockEntity) {
//            return shulkerColor.getColor();
//        } else if (e instanceof BarrelBlockEntity) {
//            return barrelColor.getColor();
//        }
//
//        return null;
//    }
//
//    @EventTarget
//    private void onRescale(EventViewportRescale e) {
//        load();
//        shaderAlpha.executeClickAction();
//        outlineWidth.executeClickAction();
//        doFade.executeClickAction();
//    }
//
//    public void load() {
//        try {
//            if (shader != null) {
//                shader.close();
//            }
//
//            Render3D.loadingShader(true);
//            shader = new ShaderEffect(mc.getTextureManager(), mc.getResourceManager(), mc.getFramebuffer(), new Identifier("hydra", "shaders/post/entity_sharp_outline.json"));
//            shader.setupDimensions(mc.getWindow().getFramebufferWidth(), mc.getWindow().getFramebufferHeight());
//            fbo = shader.getSecondaryTarget("main");
//            fbo.setClearColor(0, 0, 0, 0);
//            outlineVertexConsumerProvider = new OutlineVertexConsumerProvider(mc.getBufferBuilders().getEntityVertexConsumers());
//
//            genUniforms();
//
//            Render3D.loadingShader(false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void genUniforms() {
//        shaderStartTime = System.currentTimeMillis();
//        uEffectTime = getUniform("EffectTime");
//        uCenterAlpha = getUniform("CenterAlpha");
//        uOutlineWidth = getUniform("OutlineWidth");
//    }
//
//    private GlUniform getUniform(String name) {
//        if(shader == null)
//            return null;
//
//        for (int i = 0; i < shader.passes.size(); i++) {
//            GlUniform glUniform = shader.passes.get(i).getProgram().getUniformByName(name);
//            if (glUniform == null) {
//                continue;
//            }
//            return glUniform;
//        }
//
//        return null;
//    }
//
//    private Vec3d playerInterpos = Vec3d.ZERO;
//    private double holeStep;
//    private double holeRadiusMin;
//    private double holeRadiusMax;
//    private float maxHeight;
//    private int fadeFrom;
//
//    private void updateHoleConstants() {
//        holeStep = 3;
//        holeRadiusMax = distance.getCurrentValueInt() - 2;
//        holeRadiusMin = 2;
//        maxHeight = glowHeight.getCurrentValueFloat();
//        fadeFrom = glowAlpha.getCurrentValueInt();
//        playerInterpos = Render3D.instance.getPlayerInterpos();
//    }
//
//    public void drawHoleQuads(BufferBuilder bufferBuilder, Matrix4f matrix, Color c, double dist) {
//
//        float maxY = (float) (dist - holeRadiusMax);
//        maxY /= holeStep;
//
//        if(maxY > 0) {
//            maxY = (float) (dist - holeRadiusMax);
//            maxY /= holeStep;
//
////        float maxY = Math.max(1 - (float) ((dist - holeRadiusMin) / (holeRadiusMax - holeRadiusMin)), 1);
//            maxY = (float) Math.sqrt(1 - Math.pow(maxY - 1, 2));
//            maxY *= maxHeight;
//            maxY = maxHeight - maxY;
//        } else if(dist < holeRadiusMin) {
//            maxY = (float) (holeRadiusMin - dist); // ease down if near
//            maxY /= holeRadiusMin;
//
//            maxY *= 2;
//            maxY = maxHeight -maxY;
//        } else {
//            maxY = maxHeight;
//        }
//
//        bufferBuilder.vertex(matrix, 0, 0, 0).color(c.red, c.green, c.blue, fadeFrom).next();
//        bufferBuilder.vertex(matrix, 0, maxY, 0).color(c.red, c.green, c.blue, 0).next();
//        bufferBuilder.vertex(matrix, 1, maxY, 0).color(c.red, c.green, c.blue, 0).next();
//        bufferBuilder.vertex(matrix, 1, 0, 0).color(c.red, c.green, c.blue, fadeFrom).next();
//
//        bufferBuilder.vertex(matrix, 1, 0, 0).color(c.red, c.green, c.blue, fadeFrom).next();
//        bufferBuilder.vertex(matrix, 1, maxY, 0).color(c.red, c.green, c.blue, 0).next();
//        bufferBuilder.vertex(matrix, 0, maxY, 0).color(c.red, c.green, c.blue, 0).next();
//        bufferBuilder.vertex(matrix, 0, 0, 0).color(c.red, c.green, c.blue, fadeFrom).next();
//
//        bufferBuilder.vertex(matrix, 1, 0, 0).color(c.red, c.green, c.blue, fadeFrom).next();
//        bufferBuilder.vertex(matrix, 1, maxY, 0).color(c.red, c.green, c.blue, 0).next();
//        bufferBuilder.vertex(matrix, 1, maxY, 1).color(c.red, c.green, c.blue, 0).next();
//        bufferBuilder.vertex(matrix, 1, 0, 1).color(c.red, c.green, c.blue, fadeFrom).next();
//
//        bufferBuilder.vertex(matrix, 1, 0, 1).color(c.red, c.green, c.blue, fadeFrom).next();
//        bufferBuilder.vertex(matrix, 1, maxY, 1).color(c.red, c.green, c.blue, 0).next();
//        bufferBuilder.vertex(matrix, 1, maxY, 0).color(c.red, c.green, c.blue, 0).next();
//        bufferBuilder.vertex(matrix, 1, 0, 0).color(c.red, c.green, c.blue, fadeFrom).next();
//
//        bufferBuilder.vertex(matrix, 0, 0, 1).color(c.red, c.green, c.blue, fadeFrom).next();
//        bufferBuilder.vertex(matrix, 1, 0, 1).color(c.red, c.green, c.blue, fadeFrom).next();
//        bufferBuilder.vertex(matrix, 1, maxY, 1).color(c.red, c.green, c.blue, 0).next();
//        bufferBuilder.vertex(matrix, 0, maxY, 1).color(c.red, c.green, c.blue, 0).next();
//
//        bufferBuilder.vertex(matrix, 0, maxY, 1).color(c.red, c.green, c.blue, 0).next();
//        bufferBuilder.vertex(matrix, 1, maxY, 1).color(c.red, c.green, c.blue, 0).next();
//        bufferBuilder.vertex(matrix, 1, 0, 1).color(c.red, c.green, c.blue, fadeFrom).next();
//        bufferBuilder.vertex(matrix, 0, 0, 1).color(c.red, c.green, c.blue, fadeFrom).next();
//
//        bufferBuilder.vertex(matrix, 0, 0, 0).color(c.red, c.green, c.blue, fadeFrom).next();
//        bufferBuilder.vertex(matrix, 0, 0, 1).color(c.red, c.green, c.blue, fadeFrom).next();
//        bufferBuilder.vertex(matrix, 0, maxY, 1).color(c.red, c.green, c.blue, 0).next();
//        bufferBuilder.vertex(matrix, 0, maxY, 0).color(c.red, c.green, c.blue, 0).next();
//
//        bufferBuilder.vertex(matrix, 0, maxY, 0).color(c.red, c.green, c.blue, 0).next();
//        bufferBuilder.vertex(matrix, 0, maxY, 1).color(c.red, c.green, c.blue, 0).next();
//        bufferBuilder.vertex(matrix, 0, 0, 1).color(c.red, c.green, c.blue, fadeFrom).next();
//        bufferBuilder.vertex(matrix, 0, 0, 0).color(c.red, c.green, c.blue, fadeFrom).next();
//    }
//
//    private void drawHoleOutline(BufferBuilder bufferBuilder, Matrix4f matrix) {
//        bufferBuilder.vertex(matrix, 0, 0, 0).next();
//        bufferBuilder.vertex(matrix, 1, 0, 0).next();
//
//        bufferBuilder.vertex(matrix, 1, 0, 0).next();
//        bufferBuilder.vertex(matrix, 1, 0, 1).next();
//
//        bufferBuilder.vertex(matrix, 1, 0, 1).next();
//        bufferBuilder.vertex(matrix, 0, 0, 1).next();
//
//        bufferBuilder.vertex(matrix, 0, 0, 1).next();
//        bufferBuilder.vertex(matrix, 0, 0, 0).next();
//    }

}
