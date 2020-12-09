package pinet.majortom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import pinet.majortom.ui.Controls.ControlsBody
import pinet.majortom.ui.Devices.DevicesBody
import pinet.majortom.ui.Music.MusicBody
import pinet.majortom.ui.Sensors.SensorsBody
import pinet.majortom.ui.Settings.SettingsBody

enum class MajorTomScreen (
    val icon: ImageVector,
    private val body: @Composable ((MajorTomScreen) -> Unit) -> Unit
) {
    Sensors(
        icon = Icons.Filled.SensorWindow,
        body = { onScreenChange -> SensorsBody(onScreenChange) }
    ),
    Control(
        icon = Icons.Filled.SettingsRemote,
        body = { onScreenChange -> ControlsBody(onScreenChange) }
    ),
    Music(
        icon = Icons.Filled.MusicNote,
        body = { onScreenChange -> MusicBody(onScreenChange) }
    ),
    Devices(
        icon = Icons.Filled.Devices,
        body = { onScreenChange -> DevicesBody(onScreenChange) }
    ),
    Settings(
        icon = Icons.Filled.Settings,
        body = { onScreenChange -> SettingsBody(onScreenChange) }
    );

    @Composable
    fun content(onScreenChange: (MajorTomScreen) -> Unit ) {
        body(onScreenChange)
    }
}