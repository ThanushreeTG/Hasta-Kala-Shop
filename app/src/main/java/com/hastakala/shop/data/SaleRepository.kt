package com.hastakala.shop.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SaleRepository(
    private val saleDao: SaleDao,
    private val inventoryDao: InventoryDao,
) {

    companion object {
        const val WEEK_MS: Long = 7L * 24 * 60 * 60 * 1000
    }

    fun getAllSales(): Flow<List<Sale>> = saleDao.getAllSales()

    fun getLowStock(): Flow<List<InventoryItem>> = inventoryDao.getLowStock()

    fun salesForFilter(weekly: Boolean, nowMillis: Long): Flow<List<Sale>> {
        return if (weekly) {
            saleDao.getWeeklySales(nowMillis - WEEK_MS)
        } else {
            saleDao.getAllSales()
        }
    }

    fun colorStatsForFilter(weekly: Boolean, nowMillis: Long): Flow<List<ColorStat>> {
        return if (weekly) {
            saleDao.getColorStatsSince(nowMillis - WEEK_MS)
        } else {
            saleDao.getColorStats()
        }
    }

    fun totalIncomeForFilter(weekly: Boolean, nowMillis: Long): Flow<Double> {
        val source = if (weekly) {
            saleDao.getTotalIncomeSince(nowMillis - WEEK_MS)
        } else {
            saleDao.getTotalIncome()
        }
        return source.map { it ?: 0.0 }
    }

    suspend fun insertSale(sale: Sale) {
        saleDao.insertSale(sale)
        val row = inventoryDao.findByItemAndColor(sale.item.trim(), sale.color.trim())
        if (row != null && row.quantity > 0) {
            inventoryDao.update(row.copy(quantity = row.quantity - 1))
        }
    }
}
