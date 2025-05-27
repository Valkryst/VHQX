package com.valkryst.VHQX;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class VHQXTest {
    @Test
    public void cannotScaleWithNullImage() {
        final VHQX scaler = new VHQX();
        Assertions.assertThrows(NullPointerException.class, () -> scaler.scale(null, 2));
    }

    @Test
    public void cannotScaleWithInvalidScaleFactor() {
        final BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        final VHQX scaler = new VHQX();

        Assertions.assertThrows(IllegalArgumentException.class, () -> scaler.scale(image, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> scaler.scale(image, 5));
    }

    @Test
    public void canScaleBy2() throws IOException {
        final BufferedImage source = TestHelper.loadImage(TestHelper.ORIGINAL_PATH);
        final VHQX scaler = new VHQX();

        final BufferedImage scaledImage = scaler.scale(source, 2);
        final BufferedImage expectedResult = TestHelper.loadImage(TestHelper.HQ2X_PATH);
        TestHelper.comparePixels(expectedResult, scaledImage);
    }

    @Test
    public void canScaleBy3() throws IOException {
        final BufferedImage source = TestHelper.loadImage(TestHelper.ORIGINAL_PATH);
        final VHQX scaler = new VHQX();

        final BufferedImage scaledImage = scaler.scale(source, 3);
        final BufferedImage expectedResult = TestHelper.loadImage(TestHelper.HQ3X_PATH);
        TestHelper.comparePixels(expectedResult, scaledImage);
    }

    @Test
    public void canScaleBy4() throws IOException {
        final BufferedImage source = TestHelper.loadImage(TestHelper.ORIGINAL_PATH);
        final VHQX scaler = new VHQX();

        final BufferedImage scaledImage = scaler.scale(source, 4);
        final BufferedImage expectedResult = TestHelper.loadImage(TestHelper.HQ4X_PATH);
        TestHelper.comparePixels(expectedResult, scaledImage);
    }
}
