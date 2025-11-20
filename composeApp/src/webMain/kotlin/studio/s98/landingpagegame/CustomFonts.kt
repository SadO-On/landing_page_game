package studio.s98.landingpagegame

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import landingpagegame.composeapp.generated.resources.Res
import landingpagegame.composeapp.generated.resources.Tajawal_Bold
import landingpagegame.composeapp.generated.resources.Tajawal_Regular
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.FontResource

@Composable
fun TajwalTypography(): Typography {
    val tajawalFont = FontFamily(
        Font(Res.font.Tajawal_Regular, FontWeight.Normal),
        Font(Res.font.Tajawal_Bold, FontWeight.Bold),
    )

    return with(MaterialTheme.typography) {
        copy(
            displayLarge = displayLarge.copy(fontFamily = tajawalFont, fontWeight = FontWeight.Bold),
            displayMedium = displayMedium.copy(fontFamily = tajawalFont, fontWeight = FontWeight.Bold),
            displaySmall = displaySmall.copy(fontFamily = tajawalFont, fontWeight = FontWeight.Bold),
            headlineLarge = headlineLarge.copy(fontFamily = tajawalFont, fontWeight = FontWeight.Bold),
            headlineMedium = headlineMedium.copy(fontFamily = tajawalFont, fontWeight = FontWeight.Bold),
            headlineSmall = headlineSmall.copy(fontFamily = tajawalFont, fontWeight = FontWeight.Bold),
            titleLarge = titleLarge.copy(fontFamily = tajawalFont, fontWeight = FontWeight.Bold),
            titleMedium = titleMedium.copy(fontFamily = tajawalFont, fontWeight = FontWeight.Bold),
            titleSmall = titleSmall.copy(fontFamily = tajawalFont, fontWeight = FontWeight.Bold),
            labelLarge = labelLarge.copy(fontFamily = tajawalFont, fontWeight = FontWeight.Normal),
            labelMedium = labelMedium.copy(fontFamily = tajawalFont, fontWeight = FontWeight.Normal),
            labelSmall = labelSmall.copy(fontFamily = tajawalFont, fontWeight = FontWeight.Normal),
            bodyLarge = bodyLarge.copy(fontFamily = tajawalFont, fontWeight = FontWeight.Normal),
            bodyMedium = bodyMedium.copy(fontFamily = tajawalFont, fontWeight = FontWeight.Normal),
            bodySmall = bodySmall.copy(fontFamily = tajawalFont, fontWeight = FontWeight.Normal),
        )
    }
}