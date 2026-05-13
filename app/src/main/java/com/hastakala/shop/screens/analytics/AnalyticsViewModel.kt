package com.hastakala.shop.screens.analytics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hastakala.shop.data.ColorStat
import com.hastakala.shop.data.SaleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

data class AnalyticsUiState(
    val weeklyMode: Boolean = false,
    val colorStats: List<ColorStat> = emptyList(),
    val saleCount: Int = 0,
)

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class AnalyticsViewModel(
    private val repository: SaleRepository,
) : ViewModel() {

    private val weeklyFilter = MutableStateFlow(false)
    private val nowMillis = MutableStateFlow(System.currentTimeMillis())

    val uiState: StateFlow<AnalyticsUiState> =
        combine(weeklyFilter, nowMillis) { weekly, now ->
            Pair(weekly, now)
        }.flatMapLatest { (weekly, now) ->
            combine(
                repository.colorStatsForFilter(weekly, now),
                repository.salesForFilter(weekly, now),
            ) { colors, sales ->
                AnalyticsUiState(
                    weeklyMode = weekly,
                    colorStats = colors.sortedByDescending { it.totalAmount },
                    saleCount = sales.size,
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AnalyticsUiState(),
        )

    fun setWeeklyFilter(enabled: Boolean) {
        weeklyFilter.value = enabled
    }

    fun refreshTimeAnchor() {
        nowMillis.value = System.currentTimeMillis()
    }
}
