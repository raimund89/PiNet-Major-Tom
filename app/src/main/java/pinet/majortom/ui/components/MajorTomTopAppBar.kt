package pinet.majortom.ui.components

import androidx.compose.animation.animate
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRippleIndication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import pinet.majortom.MajorTomScreen

@Composable
fun MajorTomTopAppBar(
    allScreens: List<MajorTomScreen>,
    onTabSelected: (MajorTomScreen) -> Unit,
    currentScreen: MajorTomScreen
) {
    Surface(
        Modifier.preferredHeight(TabHeight).fillMaxWidth()
    ) {
        Row {
            allScreens.forEach { screen ->
                MajorTomTab(
                    text = screen.name.toUpperCase(),
                    icon = screen.icon,
                    onSelected = { onTabSelected(screen) },
                    selected = currentScreen == screen
                )
            }
        }
    }
}

@Composable
private fun MajorTomTab(
    text: String,
    icon: ImageVector,
    onSelected: () -> Unit,
    selected: Boolean
) {
    val color = MaterialTheme.colors.onSurface
    val durationMillis = if(selected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration

    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }

    val tabTintColor = animate(
        target = if(selected) color else color.copy(alpha = InactiveTapOpacity),
        animSpec = animSpec
    )

    Row(
        modifier = Modifier
            .padding(16.dp)
            .animateContentSize()
            .preferredHeight(TabHeight)
            .selectable(
                selected = selected,
                onClick = onSelected,
                indication = rememberRippleIndication(bounded = false)
            )
    ) {
        Icon(imageVector = icon, tint = tabTintColor)
        if(selected) {
            Spacer(Modifier.preferredWidth(12.dp))
            Text(text, color = tabTintColor)
        }
    }
}

private val TabHeight = 56.dp
private const val InactiveTapOpacity = 0.60f
private const val TabFadeInAnimationDelay = 100
private const val TabFadeInAnimationDuration = 150
private const val TabFadeOutAnimationDuration = 100