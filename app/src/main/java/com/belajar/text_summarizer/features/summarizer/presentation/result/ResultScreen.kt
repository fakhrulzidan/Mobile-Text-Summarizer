package com.belajar.text_summarizer.features.summarizer.presentation.result

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.belajar.text_summarizer.R
import com.belajar.text_summarizer.core.designSystem.theme.AppIcon
import com.belajar.text_summarizer.core.designSystem.theme.Typography
import com.belajar.text_summarizer.core.designSystem.theme.mainBg
import com.belajar.text_summarizer.core.designSystem.theme.mainText
import com.belajar.text_summarizer.core.designSystem.theme.purple
import com.belajar.text_summarizer.core.designSystem.theme.purpleGrey
import com.belajar.text_summarizer.core.designSystem.theme.secondText
import com.belajar.text_summarizer.core.designSystem.theme.whiteBg
import com.belajar.text_summarizer.features.summarizer.presentation.components.AppBarBase

@Composable
fun ResultScreen(
    onBackClick: () -> Unit,
    originalText: String,
    summaryText: String,
){
    val originalWordCount = if (originalText.trim().isEmpty()) 0 else originalText.trim().split("\\s+".toRegex()).size
    val summaryWordCount = if (summaryText.trim().isEmpty()) 0 else summaryText.trim().split("\\s+".toRegex()).size

//    var summarizedText by remember { mutableStateOf("") }
//    var inputText by remember { mutableStateOf("") }
    val clipboardManager = LocalClipboardManager.current
    var editableSummaryText by remember { mutableStateOf(summaryText) }


    val realSentenceCount = if (originalText.trim().isEmpty()) 0 else{
        originalText.split(Regex("[.!?]+")).filter { it.isNotBlank() }.size
    }
    val summarizeSentenceCount = if (summaryText.trim().isEmpty()) 0 else {
        summaryText.split(Regex("[.!?]+")).filter { it.isNotBlank() }.size
    }

    val percentageReduce = if (originalWordCount == 0) 0.0 else{
        ((originalWordCount-summaryWordCount).toDouble()/summaryWordCount)*100
    }
    val formattedPercentage = String.format(java.util.Locale("id", "ID"), "%.1f", percentageReduce)

    val originalProgress = 1f
    val summaryProgress = if (originalWordCount == 0) 0f else{
        summaryWordCount.toFloat()/originalWordCount.toFloat()
    }

    Scaffold (
        containerColor = mainBg,
        topBar = {
            AppBarBase (
                onClick = onBackClick
            )
        }
    ){ paddingValues ->
        Surface (
            color = mainBg,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ){
            Column (
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            ){
                Column (
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Text(
                        text = "Teks jadi $formattedPercentage% lebih pendek",
                        style = Typography.labelMedium,
                        color = secondText,
                        fontSize = 21.sp
                    )
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Text(
                            text = "Asli",
                            style = Typography.labelMedium,
                            fontSize = 14.sp,
                            color = secondText,
                            modifier = Modifier.width(70.dp)
                        )
                        LinearProgressIndicator(
                            progress = {originalProgress},
                            modifier = Modifier
                                .weight(1f)
                                .height(10.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            color = purpleGrey,
                            trackColor = whiteBg
                        )
                        Text(
                            text = "$originalWordCount kata",
                            style = Typography.labelMedium,
                            fontSize = 14.sp,
                            color = mainText
                        )
                    }
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Text(
                            text = "Ringkasan",
                            style = Typography.labelMedium,
                            fontSize = 14.sp,
                            color = secondText,
                            modifier = Modifier.width(70.dp)
                        )
                        LinearProgressIndicator(
                            progress = {summaryProgress},
                            modifier = Modifier
                                .weight(1f)
                                .height(10.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            color = purple,
                            trackColor = whiteBg
                        )
                        Text(
                            text = "$summaryWordCount kata",
                            style = Typography.labelMedium,
                            fontSize = 14.sp,
                            color = mainText
                        )
                    }
                    Text(
                        text = "5 kalimat jadi 2 kalimat",
                        style = Typography.labelMedium,
                        fontSize = 14.sp,
                        color = secondText
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = whiteBg)
                        .border(
                            border = BorderStroke(1.dp, color = secondText),
                            shape = RoundedCornerShape(20.dp)),
                ){
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        BasicTextField(
                            value = editableSummaryText,
                            onValueChange = { editableSummaryText = it },
                            maxLines = 10,
                            textStyle = Typography.bodyMedium.copy(
                                fontSize = 16.sp,
                                color = mainText,
                                textAlign = TextAlign.Justify,
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                        )
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Button (
                                onClick = {
                                    clipboardManager.setText(AnnotatedString(editableSummaryText))
                                },
                                colors = ButtonColors(
                                    containerColor = purple,
                                    contentColor = whiteBg,
                                    disabledContainerColor = purpleGrey,
                                    disabledContentColor = whiteBg
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .width(120.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.copy),
                                    fontSize = 15.sp,
                                    style = Typography.labelMedium
                                )
                            }
                        }
                    }
                }
                Text(
                    text = "Teks asli, kalimat terpilih disorot",
                    style = Typography.labelMedium,
                    color = secondText,
                    fontSize = 18.sp
                )
                Text(
                    text = originalText,
                    style = Typography.bodyMedium,
                    color = mainText,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun ResultScreenPreview(){
    ResultScreen(
        onBackClick = {},
        originalText = "Alurnya begini: OpenCode mengirim request, lalu 9Router memprosesnya sebelum meneruskan ke provider, dan memproses jawabannya sebelum dikembalikan. Skill dan plugin berbeda karena keduanya dipasang di sisi CLI (Claude Code, OpenCode, dll.), sedangkan Token Saver bekerja di sisi proxy. Jadi berlaku untuk semua CLI yang lewat 9Router tanpa mengubah apa pun di CLI-nya.",
        summaryText = "Alurnya begini: OpenCode mengirim request, lalu 9Router memprosesnya sebelum meneruskan ke provider, dan memproses jawabannya sebelum dikembalikan. Skill dan plugin berbeda karena keduanya dipasang di sisi CLI (Claude Code, OpenCode, dll.), sedangkan Token Saver bekerja di sisi proxy."
    )
}