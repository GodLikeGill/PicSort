package com.godlike.picsort.domain.hash

/** A single photo's identity + its computed hash, ready for clustering. */
data class HashedPhoto(
    val id: Long,       // e.g. MediaStore row id — whatever uniquely identifies the photo
    val hash: Long,
)

/** A group of photos considered duplicates/near-duplicates of each other. */
data class DuplicateCluster(
    val photoIds: List<Long>,
)

object DuplicateClusterer {

    /**
     * Groups photos whose hashes are within [threshold] Hamming distance
     * of *any other member already in the cluster*.
     *
     * This is essentially a simple graph-connected-components problem:
     * think of each photo as a node, and draw an edge between two photos
     * if their distance <= threshold. A "cluster" is a connected group of
     * nodes. We don't need a fancy graph library for this — a
     * union-find-free brute-force pass works fine at gallery scale
     * (thousands, not millions, of photos).
     */
    fun cluster(photos: List<HashedPhoto>, threshold: Int = 8): List<DuplicateCluster> {
        val visited = mutableSetOf<Long>()
        val clusters = mutableListOf<DuplicateCluster>()

        for (photo in photos) {
            if (photo.id in visited) continue // already placed in a cluster

            // Start a new cluster with this photo, then pull in anything
            // connected to it (possibly transitively) via BFS.
            val clusterIds = mutableListOf(photo.id)
            visited.add(photo.id)

            val queue = ArrayDeque<HashedPhoto>()
            queue.add(photo)

            while (queue.isNotEmpty()) {
                val current = queue.removeFirst()

                for (candidate in photos) {
                    if (candidate.id in visited) continue

                    val distance = current.hash hammingDistanceTo candidate.hash
                    if (distance <= threshold) {
                        visited.add(candidate.id)
                        clusterIds.add(candidate.id)
                        queue.add(candidate) // check its neighbors too
                    }
                }
            }

            // Only report it as a "duplicate cluster" if it actually has
            // more than one photo — a lone photo isn't a duplicate of anything.
            if (clusterIds.size > 1) {
                clusters.add(DuplicateCluster(clusterIds))
            }
        }

        return clusters
    }
}