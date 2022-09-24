package com.example.composefun2.feature.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.unit.dp
import kotlin.math.floor

@Composable
fun ModifiersPage() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Some text",
                Modifier.padding(8.dp).background(Color.Gray).dottedBackground(12f, Color.Red)
            )

            Button(
                onClick = { /*TODO*/ },
                Modifier.background(Color.Black).dottedBackground(14f, Color.Green)
            ) {
                Text("I'm a button")
            }
        }
    }
}

fun Modifier.dottedBackground(r: Float, color: Color): Modifier {
    require(r > 0)

    return this.then(DottedBackground(r, color))
}

private class DottedBackground(
    private val r: Float,
    private val color: Color
) : DrawModifier {
    override fun ContentDrawScope.draw() {
        drawStar()

        drawContent()
    }

    private fun ContentDrawScope.drawStar() {
        val diameter = r * 2
        val rows = floor(size.height / diameter.coerceAtMost(size.height)).toInt() + 1
        val columns = floor(size.width / diameter.coerceAtMost(size.width)).toInt() + 1

        for (rowIndex in 0 until rows) {
            for (columnIndex in 0 until columns) {
                drawCircle(
                    color = color,
                    radius = r,
                    center = Offset(diameter * columnIndex + r, diameter * rowIndex + r)
                )
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
