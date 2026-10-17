package com.belajar.text_summarizer.features.summarizer.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.belajar.text_summarizer.R
import com.belajar.text_summarizer.core.designSystem.theme.AppIcon
import com.belajar.text_summarizer.core.designSystem.theme.Typography
import com.belajar.text_summarizer.core.designSystem.theme.mainText

@Composable
fun AppBarBase(
    onClick: () -> Unit
){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
    ){
        IconButton(
            onClick = onClick
        ) {
            Icon(
                imageVector = AppIcon.arrowBack,
                contentDescription = null
            )
        }
        Text(
            text = stringResource(R.string.summary_result),
            style = Typography.labelMedium,
            fontSize = 24.sp,
            color = mainText
        )
    }
}