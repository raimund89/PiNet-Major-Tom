package pinet.majortom.ui.Controls

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pinet.majortom.MajorTomScreen
import pinet.majortom.ui.DefaultSectionPadding

@Composable
fun ControlsBody(onScreenChange: (MajorTomScreen) -> Unit = {}) {
    ScrollableColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        // Some current values
        Spacer(Modifier.preferredHeight(DefaultSectionPadding))
        // Some graphs
        Spacer(Modifier.preferredHeight(DefaultSectionPadding))
        // List of sensors
    }
}