package com.godlike.picsort.domain.hash

import android.graphics.Bitmap
import android.graphics.Color
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PerceptualHasherTest {

    /** Builds a simple synthetic bitmap: a horizontal gradient from dark to light,
     *  optionally with a colored offset so we can create "similar but not identical" variants. */
    private fun gradientBitmap(width: Int, height: Int, brightnessOffset: Int = 0): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        for (y in 0 until height) {
            for (x in 0 until width) {
                val base = (x.toFloat() / width * 255).toInt()
                val value = (base + brightnessOffset).coerceIn(0, 255)
                bitmap.setPixel(x, y, Color.rgb(value, value, value))
            }
        }
        return bitmap
    }

    @Test
    fun identicalImages_produceZeroDistance() {
        val bitmap = gradientBitmap(100, 100)

        val hashA = PerceptualHasher.hash(bitmap)
        val hashB = PerceptualHasher.hash(bitmap)

        assertEquals(hashA, hashB)
        assertEquals(0, hashA hammingDistanceTo hashB)
    }

    @Test
    fun slightlyBrighterImage_isStillCloseMatch() {
        // Simulates the same photo re-saved/compressed with a minor exposure shift —
        // the gradient *shape* is the same, just shifted brighter.
        val original = gradientBitmap(100, 100)
        val brighter = gradientBitmap(100, 100, brightnessOffset = 10)

        val hashA = PerceptualHasher.hash(original)
        val hashB = PerceptualHasher.hash(brighter)

        val distance = hashA hammingDistanceTo hashB
        // Should be small — same structure, minor tonal shift.
        assertTrue("Expected small distance, got $distance", distance <= 8)
    }

    @Test
    fun unrelatedImages_produceLargeDistance() {
        val gradient = gradientBitmap(100, 100)
        val solidColor = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888).apply {
            eraseColor(Color.rgb(200, 50, 50)) // flat, unrelated image
        }

        val hashA = PerceptualHasher.hash(gradient)
        val hashB = PerceptualHasher.hash(solidColor)

        val distance = hashA hammingDistanceTo hashB
        assertTrue("Expected large distance, got $distance", distance > 15)
    }

    @Test
    fun clusterer_groupsSimilarPhotosTogether() {
        val original = gradientBitmap(100, 100)
        val brighter = gradientBitmap(100, 100, brightnessOffset = 10)
        val unrelated = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888).apply {
            eraseColor(Color.rgb(200, 50, 50))
        }

        val photos = listOf(
            HashedPhoto(id = 1, hash = PerceptualHasher.hash(original)),
            HashedPhoto(id = 2, hash = PerceptualHasher.hash(brighter)),
            HashedPhoto(id = 3, hash = PerceptualHasher.hash(unrelated)),
        )

        val clusters = DuplicateClusterer.cluster(photos, threshold = 8)

        // Photo 1 and 2 should cluster together; photo 3 stands alone
        // (and lone photos aren't reported as clusters at all).
        assertEquals(1, clusters.size)
        assertEquals(setOf(1L, 2L), clusters.first().photoIds.toSet())
    }
}