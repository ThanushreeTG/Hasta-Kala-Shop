package com.hastakala.shop.data

/**
 * Room projection for color-wise aggregates (not a persisted entity).
 */
data class ColorStat(
    val color: String,
    val totalAmount: Double,
    val saleCount: Int,
)
