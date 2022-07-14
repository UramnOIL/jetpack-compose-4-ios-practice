package com.uramnoil.practice.common

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun App() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val infiniteTransition = rememberInfiniteTransition()
        // 色を時間で変化させるための値
        // 0.0〜360.0を10秒でループ
        val base by infiniteTransition.animateFloat(
            0f,
            360f,
            animationSpec = infiniteRepeatable(animation = tween(10000))
        )

        // 画面に敷き詰められる色(Box)の数
        val times = 30

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            // 虹の表示
            repeat(times) {
                // 色を決める
                val hue = (base + it) % 360

                // 色の表示
                Box(
                    modifier = Modifier
                        .background(Color.hsl(hue, 1f, 0.5f))
                        .fillMaxWidth(1f)
                        .weight(1f / times)
                )
            }
        }
    }
}
