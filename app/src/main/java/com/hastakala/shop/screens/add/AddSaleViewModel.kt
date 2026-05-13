package com.hastakala.shop.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hastakala.shop.data.Sale
import com.hastakala.shop.data.SaleRepository
import kotlinx.coroutines.launch

class AddSaleViewModel(
    private val repository: SaleRepository,
) : ViewModel() {

    fun saveSale(
        item: String,
        color: String,
        priceText: String,
        onResult: (String?) -> Unit,
    ) {
        val trimmedItem = item.trim()
        val trimmedColor = color.trim()
        if (trimmedItem.isEmpty() || trimmedColor.isEmpty()) {
            onResult("Please enter item and color.")
            return
        }

        val price = priceText.trim().toDoubleOrNull()
        if (price == null || price <= 0) {
            onResult("Enter a valid price greater than zero.")
            return
        }

        viewModelScope.launch {
            repository.insertSale(
                Sale(
                    item = trimmedItem,
                    color = trimmedColor,
                    price = price,
                    timestamp = System.currentTimeMillis(),
                ),
            )
            onResult(null)
        }
    }
}
