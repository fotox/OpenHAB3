rule "Check Contacts"
when
    Member of gContact contacts changed
then
    var Number count = 0
    val messageOutput = "The following windows / doors are still open: "
    
    if (Window_1.state.toString == "OPEN") {
      messageOutput += "Window 1 "
      count = count + 1
    }
    if (Door_1.state.toString == "OPEN") {
      messageOutput += "Door 1 "
      count = count + 1
    }

    if (count > 0) {
      Alexa_TTS.sendCommand(messageOutput)
      CheckContactOutput.sendCommand("Not all closed")
    }
    else {
      Alexa_TTS.sendCommand("Everything is closed")
      CheckContactOutput.sendCommand("All closed")
    }

    CheckContact.sendCommand(OFF)
end
