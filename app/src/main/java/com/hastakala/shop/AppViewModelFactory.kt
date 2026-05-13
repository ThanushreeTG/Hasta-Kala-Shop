package com.hastakala.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hastakala.shop.data.SaleRepository
import com.hastakala.shop.screens.add.AddSaleViewModel
import com.hastakala.shop.screens.analytics.AnalyticsViewModel
import com.hastakala.shop.screens.history.HistoryViewModel
import com.hastakala.shop.screens.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class AppViewModelFactory(
    private val repository: SaleRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) ->
                HomeViewModel(repository) as T
            modelClass.isAssignableFrom(AddSaleViewModel::class.java) ->
                AddSaleViewModel(repository) as T
            modelClass.isAssignableFrom(AnalyticsViewModel::class.java) ->
                AnalyticsViewModel(repository) as T
            modelClass.isAssignableFrom(HistoryViewModel::class.java) ->
                HistoryViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel type: ${modelClass.name}")
        }
    }
}
