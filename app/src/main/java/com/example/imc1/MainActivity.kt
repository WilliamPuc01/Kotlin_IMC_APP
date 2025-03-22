package com.example.imc1

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imc1.ui.theme.IMC1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IMC1Theme {
                    imcApp()
                }
            }
        }
    }

@Composable
fun imcApp(){
    calcImc()
}

@Preview
@Composable
fun calcImc(){
    var peso by remember { mutableStateOf("")}
    var altura by remember { mutableStateOf("")}
    var imc by remember { mutableStateOf<Double?>(null)}



    Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(stringResource(R.string.title), fontSize = 20.sp)
        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier .fillMaxWidth() .padding(16.dp), horizontalArrangement = Arrangement.spacedBy(5.dp)){
            TextField(value = altura, onValueChange = {if (it.all { char -> char.isDigit() || char == '.' }) { altura = it }},
                label = {Text(stringResource(R.string.altura))},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), singleLine = true, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(10.dp))
            TextField(value = peso, onValueChange = {if (it.all { char -> char.isDigit() || char == '.' }) { peso = it }},
                label = {Text(stringResource(R.string.peso))},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), singleLine = true, modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            val alt = altura.toDoubleOrNull()
            val pes = peso.toDoubleOrNull()
            if(alt != null && pes != null && alt>0 && pes>0){
                imc = CalcIMC(alt, pes)
            }
        }) {
            Text(stringResource(R.string.button))
        }
        Spacer(modifier = Modifier.height(10.dp))
        imc?.let{
            Text("Resultado: %.2f.".format(imc), fontSize = 15.sp, color = Color.Red)
        }
    }
}

fun CalcIMC(altura: Double, peso: Double) = peso / (altura * altura)