package com.example.pruebatecnicaliverpool.ui.flows.listhomeview

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pruebatecnicaliverpool.R
import com.example.pruebatecnicaliverpool.ui.flows.composables.DialogViewScreen
import com.example.pruebatecnicaliverpool.ui.flows.composables.LazyColumComposeView
import com.example.pruebatecnicaliverpool.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    var showDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        homeScreenViewModel.setPagingValues(Constants.TypeSort.DEFAULT.s)
    }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = { Text(stringResource(R.string.title)) },
            actions = {
                TextButton(onClick = {
                    showDialog = true
                }) {
                    Text(stringResource(R.string.sort), color = Color(0xFF2196F3), fontSize = 14.sp)
                }
            }
        )
    }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                TextButton(onClick = {}) {
                    Row {
                        Icon(Icons.Default.Search, "")
                        Text(stringResource(R.string.search))
                    }
                }
            }

            LazyColumComposeView(homeScreenViewModel)

            if (showDialog) {
                DialogViewScreen { action ->
                    when (action) {
                        Constants.TypeSort.DEFAULT,
                        Constants.TypeSort.LOWEST_PRICE,
                        Constants.TypeSort.HIGHER_PRICE -> homeScreenViewModel.setPagingValues(
                            action.s
                        )

                        Constants.TypeSort.CANCEL -> Unit
                    }

                    showDialog = false
                }
            }
        }
    }
}