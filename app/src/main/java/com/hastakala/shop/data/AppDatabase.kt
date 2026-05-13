package com.hastakala.shop.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Sale::class, InventoryItem::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun saleDao(): SaleDao
    abstract fun inventoryDao(): InventoryDao

    companion object {
        val CALLBACK = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                db.execSQL(
                    """
                    INSERT INTO inventory_items (item_name, color, quantity) VALUES
                    ('Clay Pot', 'Terracotta', 2),
                    ('Wall Hanging', 'Indigo', 1),
                    ('Coaster Set', 'Green', 5),
                    ('Lamp Base', 'Sand', 2),
                    ('Jewelry Box', 'Walnut', 3)
                    """.trimIndent(),
                )
            }
        }
    }
}
