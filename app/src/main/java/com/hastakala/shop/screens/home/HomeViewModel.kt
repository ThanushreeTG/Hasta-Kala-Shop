package com.hastakala.shop.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hastakala.shop.data.ColorStat
import com.hastakala.shop.data.InventoryItem
import com.hastakala.shop.data.SaleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

data class HomeUiState(
    val weeklyMode: Boolean = false,
    val totalIncome: Double = 0.0,
    val colorStats: List<ColorStat> = emptyList(),
    val filteredSaleCount: Int = 0,
    val lowStock: List<InventoryItem> = emptyList(),
)

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class HomeViewModel(
    private val repository: SaleRepository,
) : ViewModel() {

    private val weeklyFilter = MutableStateFlow(false)
    private val nowMillis = MutableStateFlow(System.currentTimeMillis())

    val uiState: StateFlow<HomeUiState> =
        combine(weeklyFilter, nowMillis) { weekly, now ->
            WeeklyWindow(weekly, now)
        }.flatMapLatest { window ->
            combine(
                repository.totalIncomeForFilter(window.weekly, window.now),
                repository.colorStatsForFilter(window.weekly, window.now),
                repository.salesForFilter(window.weekly, window.now),
                repository.getLowStock(),
            ) { income, colors, sales, lowStock ->
                HomeUiState(
                    weeklyMode = window.weekly,
                    totalIncome = income,
                    colorStats = colors.sortedByDescending { it.totalAmount },
                    filteredSaleCount = sales.size,
                    lowStock = lowStock,
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState(),
        )

    fun setWeeklyFilter(enabled: Boolean) {
        weeklyFilter.value = enabled
    }

    fun refreshTimeAnchor() {
        nowMillis.value = System.currentTimeMillis()
    }

    private data class WeeklyWindow(val weekly: Boolean, val now: Long)
}
