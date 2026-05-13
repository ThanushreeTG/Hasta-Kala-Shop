package com.hastakala.shop.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory_items")
data class InventoryItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "item_name") val itemName: String,
    @ColumnInfo(name = "color") val color: String,
    @ColumnInfo(name = "quantity") val quantity: Int,
)
