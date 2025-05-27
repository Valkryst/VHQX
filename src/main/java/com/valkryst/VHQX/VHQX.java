package com.valkryst.VHQX;

import java.awt.image.BufferedImage;
import java.util.Objects;

public class VHQX {
    /**
     * Scales the provided image by the specified scale factor using the HQX algorithm.
     *
     * @param image {@link BufferedImage} to scale.
     * @param scale Scale factor to apply to the image. Must be one of 2, 3, or 4.
     * @return A new {@link BufferedImage} that is a scaled version of the original image.
     */
    public BufferedImage scale(final BufferedImage image, final int scale) {
        Objects.requireNonNull(image);

        final int resultWidth = image.getWidth() * scale;
        final int resultHeight = image.getHeight() * scale;

        if (scale == 2) {
            final int[] result = new int[resultWidth * resultHeight];
            Hqx_2x.hq2x_32_rb(
                image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth()),
                result,
                image.getWidth(),
                image.getHeight()
            );

            return this.pixelsToImage(resultWidth, resultHeight, result);
        }

        if (scale == 3) {
            final int[] result = new int[resultWidth * resultHeight];
            Hqx_3x.hq3x_32_rb(
                image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth()),
                result,
                image.getWidth(),
                image.getHeight()
            );

            return this.pixelsToImage(resultWidth, resultHeight, result);
        }

        if (scale == 4) {
            final int[] result = new int[resultWidth * resultHeight];
            Hqx_4x.hq4x_32_rb(
                image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth()),
                result,
                image.getWidth(),
                image.getHeight()
            );

            return this.pixelsToImage(resultWidth, resultHeight, result);
        }

        throw new IllegalArgumentException("Unsupported Scale: " + scale);
    }

    /**
     * Converts an array of pixel data into a {@link BufferedImage}.
     *
     * @param width Width of the {@link BufferedImage} represented by the pixel data.
     * @param height Height of the {@link BufferedImage} represented by the pixel data.
     * @param pixels Array of pixel data, where each pixel is represented as an integer in ARGB format.
     * @return A {@link BufferedImage} created from the provided pixel data.
     */
    private BufferedImage pixelsToImage(final int width, final int height, final int[] pixels) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive integers.");
        }

        if (pixels == null || pixels.length != width * height) {
            throw new IllegalArgumentException("Pixels array cannot be null and must match specified width and height.");
        }

        final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        image.setRGB(0, 0, width, height, pixels, 0, width);
        return image;
    }
}
