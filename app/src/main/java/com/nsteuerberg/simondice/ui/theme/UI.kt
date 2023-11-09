@file:OptIn(ExperimentalMaterial3Api::class)

package com.nsteuerberg.simondice.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.nsteuerberg.simondice.Data
import com.nsteuerberg.simondice.MyViewModel
import com.nsteuerberg.simondice.R

@Composable
fun UserInterface(miViewModel: MyViewModel) {
    Column {
        round(miViewModel)
        botonesSimon(miViewModel)
        startIncreaseRound(miViewModel)
    }
}

@Composable
fun round(myViewModel: MyViewModel){
    Column {
        // Row with the text "Record" and "Round"
        Row {
            Text(
                modifier = Modifier.padding(20.dp,0.dp,0.dp,0.dp),
                text = stringResource(id = R.string.record)
            )
            Text(
                modifier = Modifier.padding(240.dp,0.dp,0.dp,0.dp),
                text = stringResource(id = R.string.round)
            )
        }
        // Row with the record and the round
        Row {
            Text(
                modifier = Modifier.padding(20.dp,0.dp,0.dp,0.dp),
                text = "${myViewModel.getRecord()}",
                fontSize = 25.sp
            )
            Text(
                text = "${myViewModel.getRound()}",
                modifier = Modifier.padding(if(myViewModel.getRound()<10) 295.dp else 290.dp,0.dp,0.dp,0.dp),
                fontSize = 25.sp
            )
        }

    }
}


@Composable
fun botonesSimon(myViewModel: MyViewModel){
    Row (modifier = Modifier.padding(0.dp,100.dp,0.dp,0.dp)){
        columnButtonSimon(color = Color.Cyan,myViewModel)
        columnButtonSimon(color = Color.Green, myViewModel)
    }
    Row (){
        columnButtonSimon(color = Color.Red, myViewModel)
        columnButtonSimon(color = Color.Yellow, myViewModel)
    }
}

@Composable
fun columnButtonSimon(color: Color, myViewModel: MyViewModel){
    Column {
        Button(onClick = {
            myViewModel.increaseUserSecuence(Data.colors.indexOf(color))
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
fun startIncreaseRound(miViewModel: MyViewModel){
    Row {
        Column {
            Button(
                onClick = {
                    miViewModel.changePlayStatus()
                },
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .padding(50.dp, 50.dp)
            ){
                Text(
                    text = miViewModel.getPlayStatus(), textAlign = TextAlign.Center
                )
            }
        }
        Column {
            Button(
                onClick = {
                    if (miViewModel.getPlayStatus().equals("Start")){
                        //nothing
                    } else {
                        miViewModel.checkSecuence()
                    }
                },
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .padding(50.dp, 50.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.play_arrow),
                    contentDescription = stringResource(id = R.string.arrowDescription)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimonDiceTheme {
        UserInterface(miViewModel = MyViewModel())
    }
}