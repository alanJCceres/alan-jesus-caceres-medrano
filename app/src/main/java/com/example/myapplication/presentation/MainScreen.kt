package com.example.myapplication.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel= hiltViewModel()
) {
    val input1 by mainViewModel.input1.collectAsState()
    val input2 by mainViewModel.input2.collectAsState()
    val input3 by mainViewModel.input3.collectAsState()
    val errorMessage by mainViewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Información") },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(Color.DarkGray),
                contentAlignment = Alignment.Center
            ) {
                Column() {
                    Row(
                        modifier = Modifier
                            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                            .padding(15.dp),

                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(Color(0xFFecf2f7), shape = RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                text = "+")
                        }
                        Column() {
                            Text(
                                text = "Paso 1/4",

                            )
                            Text(text = "informacion")
                        }
                    }

                }
            }

            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {

                    Column(modifier = Modifier.weight(1f)) {
                        HorizontalInputField(
                            label = "Numero de celular:",
                            data = input1,
                            placeholder = "numero",
                            onValueChange = mainViewModel::onInput1Change,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        HorizontalInputField(
                            label = "Numero de carnet:",
                            data = input2,
                            placeholder = "carnet",
                            onValueChange = mainViewModel::onInput2Change,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            )

                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        HorizontalInputField(
                            label = "Complemento (opcional):",
                            data = input3,
                            placeholder = "Complemento",
                            onValueChange = mainViewModel::onInput3Change
                        )

                        errorMessage?.let { errorText ->
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = errorText,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    Button(
                        onClick = { mainViewModel.validateAndSubmit() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Siguiente", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}