package com.nsteuerberg.simondice

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsteuerberg.simondice.ui.theme.ctxt
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * View Model of the game
 */
class MyViewModel: ViewModel(){
    /**
     * Generates a random number
     * @param max max number to generate
     * @return random number
     */
    fun generateRandomNumber(max:Int): Int {
        return (0..max-1).random()
    }

    /**
     * Resets the game
     */
    fun initGame(){
        resetRound()
        resetUserSecuence()
        resetBotSecuence()
        Data.state = State.START
    }

    /**
     * Resets the round
     */
    fun resetRound(){
        Data.round.value = 0
    }

    /**
     * Resets the user secuence
     */
    fun resetUserSecuence(){
        Data.UserSecuence.clear()
    }

    /**
     * Resets the bot secuence
     */
    fun resetBotSecuence(){
        Data.botSecuence.clear()
    }

    /**
     * Increase the bot secuence
     * shows the color secuence to the user
     */
    fun increaseShowBotSecuence(){
        Data.state = State.SEQUENCE
        Log.d("ESTADO",Data.state.toString())
        addBotSecuence()
        showSecuence()
    }

    /**
     * adds a number to the bot secuence
     */
    fun addBotSecuence(){
        Data.botSecuence.add(generateRandomNumber(4))
    }

    fun showSecuence() = runBlocking {
        showBotSequence()
    }

    /**
     * Darken the color
     * @param color color to darken
     * @param factor factor to darken
     * @return color darkened
     */
    fun darkenColor(color: Color, factor: Float): Color {
        val r = (color.red * (1 - factor)).coerceIn(0f, 1f)
        val g = (color.green * (1 - factor)).coerceIn(0f, 1f)
        val b = (color.blue * (1 - factor)).coerceIn(0f, 1f)
        return Color(r, g, b, color.alpha)
    }

    /**
     * Lighten the color
     * @param color color to lighten
     * @param factor factor to lighten
     * @return color lightened
     */
    fun lightenColor(color: Color, factor: Float): Color {
        val r = (color.red * 255 * (1 - factor) / 255 + factor).coerceIn(0f, 1f)
        val g = (color.green * 255 * (1 - factor) / 255 + factor).coerceIn(0f, 1f)
        val b = (color.blue * 255 * (1 - factor) / 255 + factor).coerceIn(0f, 1f)
        return Color(r, g, b, color.alpha)
    }

    /**
     * shows the bot secuence to the user
     */
    fun showBotSequence(){
        viewModelScope.launch {
            // we need to do the coroutines in the _viewModelScope.launch_
            for (colorIndex in Data.botSecuence) {
                Data.colorFlag = Data.colors[colorIndex].value
                Data.colorsMyColors[colorIndex].color.value = darkenColor(Data.colorFlag, 0.5f)
                Data.sounds[colorIndex].start()
                delay(500L)
                Data.colorsMyColors[colorIndex].color.value = Data.colorFlag
                delay(250L)
            }
            Data.state = State.WAITING
            Log.d("ESTADO",Data.state.toString())
        }
        Log.d("ESTADO",Data.botSecuence.toString())
    }
    fun showButtonPressed(color: MutableState<Color>){
        viewModelScope.launch {
            Data.state = State.INPUT
            Data.colorUserFlag = color.value
            color.value = darkenColor(Data.colorUserFlag, 0.5f)
            delay(250L)
            color.value = Data.colorUserFlag
            Data.state = State.WAITING
        }
    }

    /**
     * Increase the User secuence
     * @param color color introduced by the user
     */
    fun increaseUserSecuence(color: Int) {
        Data.state = State.INPUT
        Log.d("ESTADO",Data.state.toString())
        Data.UserSecuence.add(color)
        Data.state = State.WAITING
        Log.d("ESTADO",Data.state.toString())
    }


    /**
     * Checks if the user secuence is correct
     * if it is correct, increase the round and reset the user secuence
     * and if the round is greater than the record, update the record
     * if it is not correct, finish the game
     */
    fun checkSecuence(){
        Data.state = State.CHECKING
        Log.d("ESTADO",Data.state.toString())
        if (Data.UserSecuence == Data.botSecuence){
            Data.round.value ++
            if ((Data.round.value - 1) > Data.record.value){
                Data.record.value = Data.round.value - 1
            }
            Data.UserSecuence.clear()
            increaseShowBotSecuence()
        } else{
            Data.state = State.FINISH
            Toast.makeText(ctxt,"G A M E   O V E R",Toast.LENGTH_SHORT).show()
            Data.playStatus.value = "Start"
            initGame()
            Log.d("ESTADO",Data.state.toString())
        }
    }

    /**
     * Gets the round
     * @return round
     */
    fun getRound(): Int {
        return Data.round.value
    }

    /**
     * Gets the record
     * @return record
     */
    fun getRecord(): Int {
        return Data.record.value
    }

    /**
     * Change the play status
     */
    fun changePlayStatus(){
        if (Data.playStatus.value == "Start"){
            Data.playStatus.value = "Reset"
            Data.round.value ++
            increaseShowBotSecuence()
        } else{
            if (Data.state != State.SEQUENCE && Data.state != State.INPUT) {
                Data.playStatus.value = "Start"
                initGame()
            }
        }

    }

    /**
     * Gets the play status
     * @return play status
     */
    fun getPlayStatus(): String {
        return Data.playStatus.value
    }
}