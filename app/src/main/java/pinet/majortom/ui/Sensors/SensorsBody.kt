package pinet.majortom.ui.Sensors

import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.unit.dp
import pinet.majortom.MajorTomScreen
import pinet.majortom.ui.DefaultSectionPadding

private val Distance = FloatPropKey("distance")

@Composable
fun SensorsBody(onScreenChange: (MajorTomScreen) -> Unit = {}) {
    ScrollableColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        SensorsCurrent(onScreenChange)
        Spacer(Modifier.preferredHeight(DefaultSectionPadding))
        SensorsGraphs(1, onScreenChange)
        Spacer(Modifier.preferredHeight(DefaultSectionPadding))
        SensorsGraphs(2, onScreenChange)
        Spacer(Modifier.preferredHeight(DefaultSectionPadding))
        SensorsList(onScreenChange)
    }
}

@Composable
fun SensorsCurrent(onScreenChange: (MajorTomScreen) -> Unit) {
    val sensors = listOf(
        Pair("Temp binnen", "25°C"),
        Pair("Temp buiten", "-4°C"),
        Pair("Temp binnen", "25°C"),
        Pair("Temp buiten", "-4°C"),
        Pair("Temp binnen", "25°C"),
        Pair("Temp buiten", "-4°C"),
    )

    Grid(sensors, 2) { item ->
        Column(
            modifier = Modifier.padding(vertical = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(item.first)
            Text(item.second, style=MaterialTheme.typography.h2)
        }
    }
}

@Composable
fun <T> Grid(
    items: List<T>,
    rowSize: Int = 1,
    itemContent: @Composable BoxScope.(T) -> Unit,
) {
    val rows = items.chunked(rowSize)

    Card(
        shape = RoundedCornerShape(DefaultSectionPadding)
    ) {
        Column(
            modifier = Modifier.padding(DefaultSectionPadding)
        ) {
            rows.forEach { row ->
                Row(
                    Modifier.fillMaxWidth()
                ) {
                    for ((index, item) in row.withIndex()) {
                        Box(
                            modifier = Modifier.fillMaxWidth(1f / (rowSize - index)),
                            contentAlignment = Alignment.Center
                        ) {
                            itemContent(item)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SensorsGraphs(field: Int = 1, onScreenChange: (MajorTomScreen) -> Unit) {
    ScrollableRow {
        for (i in 1..3) {
            Card(
                modifier = Modifier.padding(end = if(i==3) 0.dp else DefaultSectionPadding),
                shape = RoundedCornerShape(DefaultSectionPadding)
            ) {
                Column(
                    modifier = Modifier.padding(DefaultSectionPadding)
                ) {
                    Text(text = "Graph$i", style = MaterialTheme.typography.h2)
                    Surface(
                        modifier = Modifier.width(250.dp).height(150.dp)
                    ) {

                    }
                }
            }
        }
    }
}

@Composable
fun Graph(onScreenChange: (MajorTomScreen) -> Unit) {
    val linecolor = with(AmbientDensity.current) { Stroke(1.dp.toPx()) }
    val state = transition(
        definition = LinearTransition,
        initState = AnimatedGraphProgress.START,
        toState = AnimatedGraphProgress.END
    )

    Canvas(Modifier) {
        // Draw the axes
        // Draw axis labels
        // Animate the drawing of the graph data
    }
}

@Composable
fun SensorsList(onScreenChange: (MajorTomScreen) -> Unit) {
    Column() {
        for(i in 1..6) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical=10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Sensor$i", style=MaterialTheme.typography.h2)
                Text(modifier = Modifier.align(Alignment.CenterVertically), text = "H,T,P", color = Color.LightGray)
            }
        }
    }
}

private enum class AnimatedGraphProgress {START, END}

private val LinearTransition = transitionDefinition<AnimatedGraphProgress> {
    state(AnimatedGraphProgress.START) {
        this[Distance] = 0f
    }
    state(AnimatedGraphProgress.END) {
        this[Distance] = 100f
    }

    transition(fromState = AnimatedGraphProgress.START, toState = AnimatedGraphProgress.END) {
        Distance using tween(
            delayMillis = 500,
            durationMillis = 900,
            easing = LinearOutSlowInEasing
        )
    }
}