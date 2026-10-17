package com.belajar.text_summarizer.features.summarizer.presentation.components

import androidx.annotation.IntRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.belajar.text_summarizer.R
import com.belajar.text_summarizer.core.designSystem.theme.Typography
import com.belajar.text_summarizer.core.designSystem.theme.mainText
import com.belajar.text_summarizer.core.designSystem.theme.purpleGrey
import com.belajar.text_summarizer.core.designSystem.theme.whiteBg
import kotlin.math.roundToInt

@Composable
fun SliderWord(){
    var sliderPosition by remember{ mutableStateOf(0f) }
    Column(

    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = stringResource(R.string.num_word),
                style = Typography.labelMedium,
                fontSize = 15.sp,
                color = mainText
            )
            Text(
                text = sliderPosition.toInt().toString(),
                style = Typography.labelMedium,
                fontSize = 15.sp,
                color = mainText
            )
        }
        Slider(
            value = sliderPosition,
            onValueChange = {sliderPosition = it.roundToInt().toFloat()},
            colors = SliderColors(
                thumbColor = Color.Red,
                activeTrackColor = Color.Cyan,
                activeTickColor = Color.Blue,
                inactiveTrackColor = Color.Black,
                inactiveTickColor = Color.White,
                disabledThumbColor = Color.Blue,
                disabledActiveTrackColor = Color.Blue,
                disabledActiveTickColor = Color.Blue,
                disabledInactiveTrackColor = Color.Blue,
                disabledInactiveTickColor = Color.Blue
            ),
            steps = 8,
            valueRange = 1f..10f
        )
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = "1",
                style = Typography.labelMedium,
                fontSize = 15.sp,
                color = mainText
            )
            Text(
                text = "10",
                style = Typography.labelMedium,
                fontSize = 15.sp,
                color = mainText
            )
        }
    }
}

@Preview
@Composable
fun SilderPreview(){
    SliderWord()
}