package io.github.joudermin.mixin;


import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Window.class)
public class RenderHalfOnMacMixin {
    @Shadow
    private int framebufferWidth;

    @Shadow
    private int framebufferHeight;


    /**
     * Codes below are copied from
     * <a href="https://github.com/dima-dencep/rubidium-extra/blob/1.21/stable/src/main/java/me/flashyreese/mods/sodiumextra/mixin/reduce_resolution_on_mac/MixinWindow.java">...</a>
     * Original is licensed under Apache-2.0
     */
    @WrapOperation(at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwDefaultWindowHints()V"), method = "<init>", remap = false)
    private void onDefaultWindowHints(Operation<Void> original) {
        original.call();

        if (MinecraftClient.IS_SYSTEM_MAC) {
            GLFW.glfwWindowHint(GLFW.GLFW_COCOA_RETINA_FRAMEBUFFER, GLFW.GLFW_FALSE);
        }
    }

    /**
     * Codes below are copied from
     * <a href="https://github.com/dima-dencep/rubidium-extra/blob/1.21/stable/src/main/java/me/flashyreese/mods/sodiumextra/mixin/reduce_resolution_on_mac/MixinWindow.java">...</a>
     * Original is licensed under LGPL-3.0
     */
    @Inject(at = @At(value = "RETURN"), method = "updateFramebufferSize")
    private void afterUpdateFrameBufferSize(CallbackInfo ci) {
        if (MinecraftClient.IS_SYSTEM_MAC) {
            framebufferWidth /= 2;
            framebufferHeight /= 2;
        }
    }
}
