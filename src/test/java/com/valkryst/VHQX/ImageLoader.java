package com.valkryst.VHQX;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ImageLoader {
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
            final InputStream inputStream = ImageLoader.class.getResourceAsStream(path)
        ) {
            Objects.requireNonNull(inputStream);
            return ImageIO.read(inputStream);
        }
    }
}
