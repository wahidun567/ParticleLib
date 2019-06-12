/*
 *
 * Copyright (c) 2019 Xenondevs
 *
 * The MIT License (MIT)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package xyz.xenondevs.particle.data.color;

import xyz.xenondevs.particle.ParticleConstants;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.data.ParticleData;
import xyz.xenondevs.particle.utils.MathUtils;
import xyz.xenondevs.particle.utils.ReflectionUtils;

import java.awt.*;

/**
 * A implementation of the {@link ParticleColor}
 * class that supports normal RGB values.
 *
 * @author ByteZ
 * @since 10.06.2019
 */
public class RegularColor extends ParticleColor {

    /**
     * Initializes a new {@link ParticleData}
     * Object.
     *
     * @param effect the {@link ParticleEffect}
     *               that should be displayed.
     * @param color  the {@link Color} the
     *               particle should have.
     */
    public RegularColor(ParticleEffect effect, Color color) {
        super(effect, color.getRed(), color.getBlue(), color.getGreen());
    }

    /**
     * Initializes a new {@link ParticleData}
     * Object.
     *
     * @param effect the {@link ParticleEffect}
     *               that should be displayed.
     * @param red    the red value of the color.
     * @param green  the green value of the color.
     * @param blue   the blue value of the color.
     */
    public RegularColor(ParticleEffect effect, int red, int green, int blue) {
        super(effect, MathUtils.getMaxOrMin(red, 255, 0), MathUtils.getMaxOrMin(green, 255, 0), MathUtils.getMaxOrMin(blue, 255, 0));
    }

    /**
     * Gets the red value of the color.
     *
     * @return the red value.
     */
    @Override
    public float getRed() {
        return super.getRed() / 255f;
    }

    /**
     * Gets green red value of the color.
     *
     * @return the green value.
     */
    @Override
    public float getGreen() {
        return super.getGreen() / 255f;
    }

    /**
     * Gets the blue value of the color.
     *
     * @return the blue value.
     */
    @Override
    public float getBlue() {
        return super.getBlue() / 255f;
    }

    /**
     * Converts the current {@link ParticleData}
     * instance into nms data. If the current
     * minecraft version was released before 1.13
     * a int array should be returned. If the
     * version was released after 1.12 a nms
     * "ParticleParam" has to be returned.
     *
     * @return the nms data.
     */
    @Override
    public Object toNMSData() {
        if (getEffect() != ParticleEffect.REDSTONE || ReflectionUtils.MINECRAFT_VERSION < 13)
            return new int[0];
        try {
            return ParticleConstants.getParticleParamRedstoneConstructor().newInstance(getRed(), getGreen(), getBlue(), 1f);
        } catch (Exception ex) {
            return null;
        }
    }
}