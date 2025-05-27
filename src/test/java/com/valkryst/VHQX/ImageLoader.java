package com.valkryst.VHQX;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageLoader {
    public static final String ORIGINAL_PATH = "/test-original.png";
    public static final String HQ2X_PATH = "/test-hq2x.png";
    public static final String HQ3X_PATH = "/test-hq3x.png";
    public static final String HQ4X_PATH = "/test-hq4x.png";

    public static BufferedImage loadImage(final String path) throws IOException {
        Objects.requireNonNull(path);

        if (path.isBlank()) {
            throw new IllegalArgumentException("Filename cannot be blank.");
        }

        try (
            final var inputStream = ImageLoader.class.getResourceAsStream(path)
        ) {
            Objects.requireNonNull(inputStream);
            return ImageIO.read(inputStream);
        }
    }

    public static BufferedImage pixelsToImage(final int width, final int height, final int[] pixels) {
        Objects.requireNonNull(pixels, "Pixels array cannot be null.");

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive integers.");
        }

        if (pixels.length != width * height) {
            throw new IllegalArgumentException("Pixels array length does not match specified width and height.");
        }

        final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        image.setRGB(0, 0, width, height, pixels, 0, width);
        return image;
    }
}
