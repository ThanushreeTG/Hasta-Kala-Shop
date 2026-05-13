package com.hastakala.shop.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDao {

    @Query("SELECT * FROM inventory_items WHERE quantity <= 2 ORDER BY quantity ASC, item_name ASC")
    fun getLowStock(): Flow<List<InventoryItem>>

    @Query(
        """
        SELECT * FROM inventory_items
        WHERE item_name = :itemName AND color = :color
        LIMIT 1
        """,
    )
    suspend fun findByItemAndColor(itemName: String, color: String): InventoryItem?

    @Update
    suspend fun update(item: InventoryItem)
}
