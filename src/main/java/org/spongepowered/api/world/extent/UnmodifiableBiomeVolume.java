/*
 * This file is part of SpongeAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.api.world.extent;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import org.spongepowered.api.util.DiscreteTransform3;
import org.spongepowered.api.util.PositionOutOfBoundsException;
import org.spongepowered.api.world.extent.worker.BiomeVolumeWorker;

/**
 * A volume containing biomes that can be accessed but not modified. The data may
 * be changed by other processes.
 *
 * @see BiomeVolume
 */
public interface UnmodifiableBiomeVolume extends BiomeVolume {

    /**
     * Returns a new volume that is the same or smaller than the current volume.
     * This does not copy the biomes, it only provides a new view of the
     * storage.
     *
     * @param newMin The new minimum coordinates in this volume
     * @param newMax The new maximum coordinates in this volume
     * @return The new volume with the new bounds
     * @throws PositionOutOfBoundsException If the new minimum and maximum are
     *         outside the current volume
     */
    @Override
    UnmodifiableBiomeVolume getBiomeView(Vector3i newMin, Vector3i newMax);

    /**
     * Returns a new volume that is viewed through some transformation. This does
     * not copy the biomes, it only provides a new view of the storage.
     *
     * @param transform The transformation to be applied
     * @return The new volume with the transform
     */
    @Override
    UnmodifiableBiomeVolume getBiomeView(DiscreteTransform3 transform);

    /**
     * Returns a new volume that is translated so that
     * {@link BiomeVolume#getBiomeMin()} returns {@link Vector2i#ZERO}. This does
     * not copy the biomes, it only provides a new view of the storage.
     *
     * @return The new volume with its minimum at zero
     */
    @Override
    default UnmodifiableBiomeVolume getRelativeBiomeView() {
        return getBiomeView(DiscreteTransform3.fromTranslation(getBiomeMin().negate()));
    }

    @Override
    default UnmodifiableBiomeVolume getUnmodifiableBiomeView() {
        return this;
    }

    @Override
    BiomeVolumeWorker<? extends UnmodifiableBiomeVolume> getBiomeWorker();

}
