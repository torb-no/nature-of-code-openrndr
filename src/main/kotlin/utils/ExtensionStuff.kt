package utils

import org.openrndr.math.Vector2

// Fill in the gaps in stuff I feel
// is missing in OpenRNDR
// Will probably mostly be adding
// stuff I'm used to from Processing

fun Vector2.limit(d: Double) =
        if (length > d) normalized * d
        else this