package com.godlike.picsort.domain.hash

import android.graphics.Bitmap
import androidx.core.graphics.get
import androidx.core.graphics.scale

/**
 * Generates a 64-bit "difference hash" (dHash) for a bitmap.
 *
 * The idea: shrink the image down to a tiny 9x8 grayscale grid, then encode
 * whether brightness *increases* moving left-to-right across each row.
 * That gradient pattern survives resizing/recompression/minor edits, so two
 * visually similar photos end up with nearly identical hashes — even if
 * their raw pixels differ.
 */

object PerceptualHasher {

    private const val HASH_WIDTH = 9  // 9 columns -> 8 pairwise comparisons per row
    private const val HASH_HEIGHT = 8 // 8 rows -> 64 bits total (8 x 8)

    fun hash(bitmap: Bitmap): Long {
        // Step 1: shrink to 9x8. This throws away fine detail on purpose —
        // we only care about the coarse brightness structure of the image.
        val small = bitmap.scale(HASH_WIDTH, HASH_HEIGHT)

        // Step 2: convert each pixel to a grayscale brightness value (0-255).
        val gray = Array(HASH_HEIGHT) { y ->
            IntArray(HASH_WIDTH) { x ->
                val pixel = small[x, y]
                luminance(pixel)
            }
        }

        // Step 3: walk each row left-to-right, comparing neighbors.
        // If the pixel gets brighter moving right, that's a 1 bit, else 0.
        var hash = 0L
        var bitIndex = 0
        for (y in 0 until HASH_HEIGHT) {
            for (x in 0 until HASH_WIDTH - 1) {
                val bit = if (gray[y][x] < gray[y][x + 1]) 1L else 0L
                hash = hash or (bit shl bitIndex)
                bitIndex++
            }
        }

        if (small !== bitmap) small.recycle()

        return hash
    }

    /** Standard luminance formula — weights green highest since the human eye
     *  is most sensitive to it, red next, blue least. */
    private fun luminance(pixel: Int): Int {
        val r = (pixel shr 16) and 0xFF
        val g = (pixel shr 8) and 0xFF
        val b = pixel and 0xFF
        return (0.299 * r + 0.587 * g + 0.114 * b).toInt()
    }
}