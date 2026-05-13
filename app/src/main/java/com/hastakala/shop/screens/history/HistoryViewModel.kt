package com.hastakala.shop.screens.history

import androidx.lifecycle.ViewModel
import com.hastakala.shop.data.Sale
import com.hastakala.shop.data.SaleRepository
import kotlinx.coroutines.flow.Flow

class HistoryViewModel(
    private val repository: SaleRepository,
) : ViewModel() {

    fun salesFlow(): Flow<List<Sale>> = repository.getAllSales()
}
