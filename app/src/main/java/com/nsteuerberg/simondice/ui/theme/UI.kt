@file:OptIn(ExperimentalMaterial3Api::class)

package com.nsteuerberg.simondice.ui.theme

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nsteuerberg.simondice.MyViewModel
import com.nsteuerberg.simondice.R

@Composable
fun InterfazUsuario(miViewModel: MyViewModel) {


    Column {
        ronda(miViewModel)
        botonesSimon()
        startSumaRonda(miViewModel)
    }
}

@Composable
fun ronda(miViewModel: MyViewModel){
    Row (modifier = Modifier.padding(300.dp,0.dp,0.dp,0.dp)){
        Text(
            text = stringResource(id = R.string.round)
        )
    }
    Row (modifier = Modifier
        .padding(315.dp, 0.dp, 0.dp, 0.dp)
        .height(25.dp)
    ){
        Text(
            text = "1",
            fontSize = 25.sp
            // text = "${miViewModel.getRound()}",
            //
            // fontSize = miViewModel.getTamanhoLetra().sp
        )
    }
}


@Composable
fun botonesSimon(){
    Row (modifier = Modifier.padding(0.dp,100.dp,0.dp,0.dp)){
        columnButtonSimon(color = Color.Cyan)
        columnButtonSimon(color = Color.Green)
    }
    Row (){
        columnButtonSimon(color = Color.Red)
        columnButtonSimon(color = Color.Yellow)
    }
}

@Composable
fun columnButtonSimon(color: Color){
    Column {
        Button(onClick = {
            /*TODO*/
        },
            shape = RectangleShape,
            modifier = Modifier
                .height(200.dp)
                .width(200.dp)
                .padding(50.dp, 50.dp)
            ,
            colors = ButtonDefaults.buttonColors(color)
        ){

        }
    }
}

@Composable
fun startSumaRonda(miViewModel: MyViewModel){
    Row {
        Column {
            Button(
                onClick = {
                    //miViewModel.changeEstado()
                },
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .padding(50.dp, 50.dp)
            ){
                Text(
                    text = "Inicio", textAlign = TextAlign.Center
                    //text = miViewModel.getEstadoRonda(), textAlign = TextAlign.Center
                )
            }
        }
        Column {
            Button(
                onClick = {
                    //miViewModel.elevateRound()
                },
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .padding(50.dp, 50.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.play_arrow),
                    contentDescription = stringResource(id = R.string.descripcionArrow)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimonDiceTheme {
        InterfazUsuario(miViewModel = MyViewModel())
    }
}