package com.hastakala.shop.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SaleDao {

    @Insert
    suspend fun insertSale(sale: Sale)

    @Query("SELECT * FROM sales ORDER BY timestamp DESC")
    fun getAllSales(): Flow<List<Sale>>

    /**
     * @param sinceMillis Start of the rolling window (e.g. now - 7 days).
     */
    @Query("SELECT * FROM sales WHERE timestamp >= :sinceMillis ORDER BY timestamp DESC")
    fun getWeeklySales(sinceMillis: Long): Flow<List<Sale>>

    @Query(
        """
        SELECT color AS color, SUM(price) AS totalAmount, COUNT(*) AS saleCount
        FROM sales
        GROUP BY color
        """,
    )
    fun getColorStats(): Flow<List<ColorStat>>

    @Query("SELECT SUM(price) FROM sales")
    fun getTotalIncome(): Flow<Double?>

    @Query(
        """
        SELECT color AS color, SUM(price) AS totalAmount, COUNT(*) AS saleCount
        FROM sales
        WHERE timestamp >= :sinceMillis
        GROUP BY color
        """,
    )
    fun getColorStatsSince(sinceMillis: Long): Flow<List<ColorStat>>

    @Query("SELECT SUM(price) FROM sales WHERE timestamp >= :sinceMillis")
    fun getTotalIncomeSince(sinceMillis: Long): Flow<Double?>
}
