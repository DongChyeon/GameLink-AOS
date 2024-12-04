import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.dongchyeon.feature.home.HomeRoute

const val homeRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions = androidx.navigation.navOptions {  }) {
    navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(
    showSnackBar: (String) -> Unit
) {
    composable(
        route = homeRoute
    ) {
        HomeRoute(
            showSnackBar = showSnackBar
        )
    }
}