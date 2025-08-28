package xyz.Desyncd.harder.client.mixin;

import com.google.common.base.Strings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;
import java.util.Objects;

@Mixin(DebugHud.class)
public abstract class DebugMixin {
    @Shadow
    protected abstract void drawLeftText(DrawContext context);

    /**
     * @author Desyncfy
     * @reason No more debug screen >:0
     */
    @Overwrite
    public void render(DrawContext context) {
        this.drawText(context, List.of("Nope"), true);
    }

    @Unique
    private void drawText(DrawContext context, List<String> text, boolean left) {
        Objects.requireNonNull(MinecraftClient.getInstance().textRenderer);
        int i = 9;

        int j;
        String string;
        int k;
        int l;
        int m;
        for(j = 0; j < text.size(); ++j) {
            string = (String)text.get(j);
            if (!Strings.isNullOrEmpty(string)) {
                k = MinecraftClient.getInstance().textRenderer.getWidth(string);
                l = left ? 2 : context.getScaledWindowWidth() - 2 - k;
                m = 2 + i * j;
                context.fill(l - 1, m - 1, l + k + 1, m + i - 1, -1873784752);
            }
        }

        for(j = 0; j < text.size(); ++j) {
            string = (String)text.get(j);
            if (!Strings.isNullOrEmpty(string)) {
                k = MinecraftClient.getInstance().textRenderer.getWidth(string);
                l = left ? 2 : context.getScaledWindowWidth() - 2 - k;
                m = 2 + i * j;
                context.drawText(MinecraftClient.getInstance().textRenderer, string, l, m, -2039584, false);
            }
        }

    }
}
