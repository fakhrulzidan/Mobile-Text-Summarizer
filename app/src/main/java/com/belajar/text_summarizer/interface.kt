package com.belajar.text_summarizer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.belajar.text_summarizer.network.RetrofitClient
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SummarizerScreenPreview() {
    var inputText by remember { mutableStateOf("") }
    var summarizedText by remember { mutableStateOf("") }
    var showTranslation by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val brush = remember {
        Brush.linearGradient(
            colors = listOf(Color.Red, Color.Yellow, Color.Green, Color.Blue, Color.Magenta)
        )
    }

    Scaffold (
        containerColor = colorResource(R.color.dark),
        topBar = {
            Surface (
                color = colorResource(R.color.dark)
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        "Text Summarizer",
                        color = colorResource(R.color.text),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,)
                }
            }
        }
    ){ paddingValues ->
        Surface (
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            color = colorResource(R.color.main)

        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 34.dp)
            ) {
                Box (
                    modifier = Modifier
                        .background(color = colorResource(R.color.white),
                            shape = RoundedCornerShape(10.dp)
                        )
                ){
                    OutlinedTextField(
                        value = inputText,
                        onValueChange = {
                            inputText = it
                            showTranslation = false
                        },
                        maxLines = 6,
                        singleLine = false,
                        textStyle = TextStyle(brush = brush, fontSize = 16.sp),
                        placeholder = { Text("Enter your text here") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }

                Button (
                    onClick = {
                        coroutineScope.launch{
                            try {
                                val request = SummaryRequest(inputText, 3)
                                val respon = RetrofitClient.instance.summarize(request)
                                summarizedText = respon.summary
                                showTranslation = true
                            } catch (e:Exception){
                                summarizedText="Error: ${e.message}"
                                showTranslation=true
                            }
                        }
                    },
                    shape = CircleShape,
                    modifier = Modifier
                        .size(150.dp)
                        .offset(y = -30.dp)

                ) {
                    Text("Summarize",
                        color = Color.White,
                        fontSize =18.sp,
                        fontWeight = FontWeight.Bold)
                }

                if(showTranslation){
                    Box (
                        modifier = Modifier
                            .background(color = colorResource(R.color.white),
                                shape = RoundedCornerShape(10.dp)
                            )
                    ){
                        OutlinedTextField(
                            value = summarizedText,
                            onValueChange = {
                                summarizedText = it
                            },
                            maxLines = 6,
                            singleLine = false,
                            textStyle = TextStyle(brush = brush, fontSize = 16.sp),
                             modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SummarizerScreenPreviewLight() {
    SummarizerScreenPreview()
}


//            // Input Box
//            Card(
//                elevation = CardDefaults.cardElevation(4.dp),
//                shape = RoundedCornerShape(12.dp),
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        Text("English", style = MaterialTheme.typography.labelLarge, modifier = Modifier.weight(1f))
//                        Icon(imageVector = Icons.Default.Close, contentDescription = "Clear")
//                    }
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    OutlinedTextField(
//                        value = inputText,
//                        onValueChange = {
//                            inputText = it
//                            showTranslation = false // Reset translation on input change
//                        },
//                        placeholder = { Text("Enter text here...") },
//                        modifier = Modifier.fillMaxWidth()
//                    )
//
//                    Spacer(modifier = Modifier.height(12.dp))
//
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//                        IconButton(onClick = { /* mic placeholder */ }) {
////                            Icon(imageVector = Icons.Default.Mic, contentDescription = "Mic")
//                        }
//
//                        Button(
//                            onClick = {
//                                showTranslation = true
//                            },
//                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6600)),
//                            shape = RoundedCornerShape(24.dp)
//                        ) {
//                            Text("Translate", color = Color.White)
//                        }
//                    }
//                }
//            }
//
//            // Translation Box (Only show if Translate pressed)
//            if (showTranslation) {
//                Spacer(modifier = Modifier.height(24.dp))
//
//                Card(
//                    elevation = CardDefaults.cardElevation(4.dp),
//                    shape = RoundedCornerShape(12.dp),
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Column(modifier = Modifier.padding(16.dp)) {
//                        Row(verticalAlignment = Alignment.CenterVertically) {
//                            Text("Spanish", style = MaterialTheme.typography.labelLarge, modifier = Modifier.weight(1f))
////                            Icon(imageVector = Icons.Default.VolumeUp, contentDescription = "Speaker")
//                        }
//
//                        Spacer(modifier = Modifier.height(8.dp))
//
//                        Text("¿Hola como estas?", style = MaterialTheme.typography.bodyLarge)
//
//                        Spacer(modifier = Modifier.height(12.dp))
//
//                        Row(
//                            horizontalArrangement = Arrangement.End,
//                            modifier = Modifier.fillMaxWidth()
//                        ) {
//                            IconButton(onClick = { /* copy */ }) {
////                                Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "Copy")
//                            }
//                            IconButton(onClick = { /* share */ }) {
////                                Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
//                            }
//                        }
//                    }
//                }
//            }

//            Column(
//                modifier = Modifier
//                    .padding(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ){
//                Text("This app uses an extractive summarization technique to create a summary of your text. \n\n" +
//                        "Paste your text below, choose the desired summary length, and click 'Summarize'.",
//                    color = colorResource(R.color.text)
//                )
//                Spacer(modifier = Modifier.padding(vertical = 8.dp))
//                TextField(
//                    value = inputText,
//                    onValueChange = {
//                        inputText = it
//                    },
//                    label = { Text("Paste your text here") },
//                    modifier = Modifier
//                        .fillMaxWidth()
//
//                )
//            }