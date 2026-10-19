package com.belajar.text_summarizer.features.summarizer.presentation.input

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.belajar.text_summarizer.R
import com.belajar.text_summarizer.core.designSystem.theme.AppIcon
import com.belajar.text_summarizer.core.designSystem.theme.Typography
import com.belajar.text_summarizer.core.designSystem.theme.bricolageFamily
import com.belajar.text_summarizer.core.designSystem.theme.mainBg
import com.belajar.text_summarizer.core.designSystem.theme.mainText
import com.belajar.text_summarizer.core.designSystem.theme.purple
import com.belajar.text_summarizer.core.designSystem.theme.purpleGrey
import com.belajar.text_summarizer.core.designSystem.theme.secondText
import com.belajar.text_summarizer.core.designSystem.theme.sourceSerifFamily
import com.belajar.text_summarizer.core.designSystem.theme.whiteBg
import com.belajar.text_summarizer.core.di.RetrofitClient
import com.belajar.text_summarizer.features.summarizer.data.remote.dto.SummaryRequest
import com.belajar.text_summarizer.features.summarizer.presentation.SummarizerViewModel
import com.belajar.text_summarizer.features.summarizer.presentation.components.SliderWord
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun InputScreen(
    onNavigateToResult: (String, String) -> Unit,
    viewModel: SummarizerViewModel
){
    var inputText by remember { mutableStateOf("") }
    val clipboardManager = LocalClipboardManager.current
    val corutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }

    val charCount = inputText.length

    val wordCount = if (inputText.trim().isEmpty()) {
        0
    } else {
        inputText.trim().split("\\s+".toRegex()).size
    }

    Column (
        modifier = Modifier
            .background(mainBg)
            .padding(16.dp)
            .fillMaxSize()

    ){
        Column (
            modifier = Modifier
                .padding(top= 36.dp, bottom = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.header),
                color = mainText,
                style = Typography.headlineLarge
            )
            Spacer(modifier=Modifier.padding(4.dp))
            Text(
                text = stringResource(R.string.sub_heading),
                color = secondText,
                fontSize = 16.sp,
                style = Typography.labelMedium
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = whiteBg)
                .border(
                    border = BorderStroke(1.dp, color = secondText),
                    shape = RoundedCornerShape(20.dp)
                ),
        ){
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.text),
                    fontSize = 16.sp,
                    color = secondText,
                    style = Typography.labelMedium
                )
                BasicTextField(
                    value = inputText,
                    onValueChange = {inputText = it},
                    maxLines = 10,
                    textStyle = Typography.bodyMedium.copy(
                        fontSize = 16.sp,
                        color = mainText,
                        textAlign = TextAlign.Justify,
                        ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                )
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Button (
                        onClick = {
                            clipboardManager.getText()?.text?.let { pastedText ->
                                inputText += pastedText
                            }
                        },
                        colors = ButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = purple,
                            disabledContainerColor = purple,
                            disabledContentColor = purple
                        ),
                        border = BorderStroke(width = 1.dp,color = purple),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
//                            .width(120.dp)
                    ) {
                        Icon(
                            imageVector = AppIcon.paste,
                            contentDescription = null,
                            tint = purple,
                            modifier = Modifier
                                .size(15.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(R.string.paste),
                            color = purple,
                            fontSize = 15.sp,
                            style = Typography.labelMedium
                        )
                    }
                    Text(
                        text = "$charCount kata, $wordCount karakter",
                        style = Typography.labelMedium,
                        fontSize = 15.sp,
                        color = secondText
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(21.dp))
        SliderWord(sliderValue = viewModel.sliderValue,
            onValueChange = {viewModel.sliderValue = it})
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                if (inputText.isNotBlank() && !isLoading){
                    isLoading = true
                    corutineScope.launch {
                        try {
                            val request = SummaryRequest(inputText, viewModel.sliderValue)
                            val respon = RetrofitClient.instance.summarize(request)
                            isLoading = false
                            onNavigateToResult(inputText, respon.summary)
                        } catch (e: Exception){
                            isLoading = false
                            onNavigateToResult(inputText, "Error: ${e.message}")
                        }
                    }
                }
                      },
            colors = ButtonColors(
                containerColor = purple,
                contentColor = whiteBg,
                disabledContainerColor = purpleGrey,
                disabledContentColor = whiteBg
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = whiteBg,
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Icon(
                    imageVector = AppIcon.summary,
                    contentDescription = null,
                    tint = whiteBg,
                    modifier = Modifier
                        .size(15.dp)
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.summarize),
                    color = whiteBg,
                    fontSize = 16.sp,
                    style = Typography.labelMedium
                )
            }
        }
        Spacer(modifier = Modifier.height(21.dp))
    }
}


@Preview
@Composable
fun InputScreenPreview(){
    InputScreen(
        onNavigateToResult = { inputText, result -> },
        viewModel = viewModel()
    )
}