package com.valkryst.VHQX;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class TestHelper {
    public static final String ORIGINAL_PATH = "/test-original.png";
    public static final String HQ2X_PATH = "/test-hq2x.png";
    public static final String HQ3X_PATH = "/test-hq3x.png";
    public static final String HQ4X_PATH = "/test-hq4x.png";

    public static BufferedImage loadImage(final String path) throws IOException {
        Objects.requireNonNull(path);

        if (path.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be blank.");
        }

        try (
            final InputStream inputStream = TestHelper.class.getResourceAsStream(path)
        ) {
            Objects.requireNonNull(inputStream);
            return ImageIO.read(inputStream);
        }
    }

    public static void comparePixels(final BufferedImage image1, final BufferedImage image2) {
        Objects.requireNonNull(image1);
        Objects.requireNonNull(image2);

        if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) {
            throw new IllegalArgumentException("Images must have the same dimensions.");
        }

        for (int y = 0; y < image1.getHeight(); y++) {
            for (int x = 0; x < image1.getWidth(); x++) {
                final int pixel1 = image1.getRGB(x, y);
                final int pixel2 = image2.getRGB(x, y);
                if (pixel1 != pixel2) {
                    throw new AssertionError("Pixel mismatch at (" + x + ", " + y + "): expected " + pixel1 + ", but got " + pixel2);
                }
            }
        }
    }

    public static void comparePixels(final BufferedImage image1, final int[] image2) {
        Objects.requireNonNull(image1);

        for (int y = 0; y < image1.getHeight(); y++) {
            for (int x = 0; x < image1.getWidth(); x++) {
                final int pixelIndex = y * image1.getWidth() + x;
                if (pixelIndex >= image2.length) {
                    throw new ArrayIndexOutOfBoundsException("Pixel index out of bounds: " + pixelIndex);
                }

                final int expectedPixel = image1.getRGB(x, y);
                final int actualPixel = image2[pixelIndex];
                if (expectedPixel != actualPixel) {
                    throw new AssertionError("Pixel mismatch at (" + x + ", " + y + "): expected " + expectedPixel + ", but got " + actualPixel);
                }
            }
        }
    }
}
