package com.hastakala.shop.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hastakala.shop.AppViewModelFactory
import com.hastakala.shop.screens.add.AddSaleScreen
import com.hastakala.shop.screens.add.AddSaleViewModel
import com.hastakala.shop.screens.analytics.AnalyticsScreen
import com.hastakala.shop.screens.analytics.AnalyticsViewModel
import com.hastakala.shop.screens.history.HistoryScreen
import com.hastakala.shop.screens.history.HistoryViewModel
import com.hastakala.shop.screens.home.HomeScreen
import com.hastakala.shop.screens.home.HomeViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    factory: AppViewModelFactory,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        modifier = modifier,
    ) {
        composable(Routes.HOME) {
            val vm: HomeViewModel = viewModel(factory = factory)
            HomeScreen(
                viewModel = vm,
                onNavigateAddSale = { navController.navigate(Routes.ADD) },
                onNavigateAnalytics = { navController.navigate(Routes.ANALYTICS) },
                onNavigateHistory = { navController.navigate(Routes.HISTORY) },
            )
        }
        composable(Routes.ADD) {
            val vm: AddSaleViewModel = viewModel(factory = factory)
            AddSaleScreen(
                viewModel = vm,
                onSaved = { navController.popBackStack() },
                onCancel = { navController.popBackStack() },
            )
        }
        composable(Routes.ANALYTICS) {
            val vm: AnalyticsViewModel = viewModel(factory = factory)
            AnalyticsScreen(
                viewModel = vm,
                onBack = { navController.popBackStack() },
            )
        }
        composable(Routes.HISTORY) {
            val vm: HistoryViewModel = viewModel(factory = factory)
            HistoryScreen(
                viewModel = vm,
                onBack = { navController.popBackStack() },
            )
        }
    }
}
