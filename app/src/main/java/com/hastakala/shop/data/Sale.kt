package com.hastakala.shop.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sales")
data class Sale(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val item: String,
    val color: String,
    val price: Double,
    val timestamp: Long,
)
