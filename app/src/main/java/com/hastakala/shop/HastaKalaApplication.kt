package com.hastakala.shop

import android.app.Application
import androidx.room.Room
import com.hastakala.shop.data.AppDatabase
import com.hastakala.shop.data.SaleRepository

class HastaKalaApplication : Application() {

    lateinit var repository: SaleRepository
        private set

    override fun onCreate() {
        super.onCreate()
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "hasta_kala.db",
        )
            .fallbackToDestructiveMigration()
            .addCallback(AppDatabase.CALLBACK)
            .build()
        repository = SaleRepository(
            saleDao = db.saleDao(),
            inventoryDao = db.inventoryDao(),
        )
    }
}
