package com.godlike.picsort.domain.hash

/**
 * Counts how many bits differ between two 64-bit hashes.
 *
 * Two dHashes from visually similar photos will differ in only a handful
 * of bits (usually < 10 out of 64). Wildly different photos will differ
 * in roughly half the bits (~32), since unrelated brightness gradients
 * are essentially random relative to each other.
 */

infix fun Long.hammingDistanceTo(other: Long): Int {
    // XOR sets a bit to 1 exactly where the two hashes disagree,
    // and 0 where they agree. So counting the 1-bits in the XOR
    // result gives us the number of differing bits.
    val xor = this xor other
    return java.lang.Long.bitCount(xor)
}