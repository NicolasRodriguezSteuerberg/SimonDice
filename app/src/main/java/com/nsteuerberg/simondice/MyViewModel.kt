package com.nsteuerberg.simondice

/**
 * View Model of the game
 */
class MyViewModel {
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
    fun increaseShowBotSecuence() {
        Data.state = State.SEQUENCE
        addBotSecuence()
        showBotSecuence()
        Data.state = State.WAITING
    }

    /**
     * adds a number to the bot secuence
     */
    fun addBotSecuence(){
        Data.botSecuence.add(generateRandomNumber(4))
    }

    /**
     * shows the bot secuence to the user
     */
    fun showBotSecuence() {
        //TODO show color
    }

    /**
     * Increase the User secuence
     * @param color color introduced by the user
     */
    fun increaseUserSecuence(color: Int) {
        Data.state = State.INPUT
        Data.UserSecuence.add(color)
        Data.state = State.WAITING
    }


    /**
     * Checks if the user secuence is correct
     * if it is correct, increase the round and reset the user secuence
     * and if the round is greater than the record, update the record
     * if it is not correct, finish the game
     */
    fun checkSecuence(){
        Data.state = State.CHECKING
        if (Data.UserSecuence == Data.botSecuence){
            Data.round.value ++
            if (Data.round.value > Data.record.value){
                Data.record.value = Data.round.value
            }
            Data.UserSecuence.clear()
            Thread.sleep(1000)
            increaseShowBotSecuence()
        } else{
            Data.state = State.FINISH
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
            Data.round.value ++
            Data.playStatus.value = "Reset"
        } else{
            Data.playStatus.value = "Start"
            initGame()
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