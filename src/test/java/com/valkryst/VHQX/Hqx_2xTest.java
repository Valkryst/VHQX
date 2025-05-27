package com.valkryst.VHQX;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Hqx_2xTest {
    @Test
    public void ensureGeneratedImageMatchesOriginalResult() throws IOException {
        final BufferedImage source = TestHelper.loadImage(TestHelper.ORIGINAL_PATH);
        final BufferedImage expectedResult = TestHelper.loadImage(TestHelper.HQ2X_PATH);

        // The HQX_2x algorithm requires the destination image to be twice the size of the source image.
        final int resultHeight = source.getHeight() * 2;
        final int resultWidth = source.getWidth() * 2;
        final int[] result = new int[resultHeight * resultWidth];

        Hqx_2x.hq2x_32_rb(
            source.getRGB(0, 0, source.getWidth(), source.getHeight(), null, 0, source.getWidth()),
            result,
            source.getWidth(),
            source.getHeight()
        );

        TestHelper.comparePixels(expectedResult, result);
    }
}
