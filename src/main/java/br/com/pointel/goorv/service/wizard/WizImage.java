package br.com.pointel.goorv.service.wizard;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class WizImage {

    public static final List<String> IMAGE_EXTENSIONS = Arrays.asList(".png", ".jpg", ".jpeg", ".bmp", ".gif", ".tiff", ".tif");

    public static boolean isImage(File file) {
        if (!file.isFile()) {
            return false;
        }
        final var name = file.getName().toLowerCase();
        return IMAGE_EXTENSIONS.stream().anyMatch(ext -> name.endsWith(ext));
    }

    public static boolean isSame(BufferedImage imageA, BufferedImage imageB) {
        if (imageA.getWidth() != imageB.getWidth() || imageA.getHeight() != imageB.getHeight()) {
            return false;
        }
        var total = imageA.getWidth() * imageA.getHeight();
        var diffs = 0;
        for (int x = 0; x < imageA.getWidth(); x++) {
            for (int y = 0; y < imageA.getHeight(); y++) {
                if (imageA.getRGB(x, y) != imageB.getRGB(x, y)) {
                    diffs++;
                }
            }
        }
        var percent = (0.0 + diffs) / total;
        return percent < 0.05;
    }

    public static Dimension getScaledDimension(Dimension actual, Dimension boundary) {
        int original_width = actual.width;
        int original_height = actual.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;
        if (original_width > bound_width) {
            new_width = bound_width;
            new_height = (new_width * original_height) / original_width;
        }
        if (new_height > bound_height) {
            new_height = bound_height;
            new_width = (new_height * original_width) / original_height;
        }
        return new Dimension(new_width, new_height);
    }

}
