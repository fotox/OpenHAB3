rule "Alarm system ON"
when
   Item Alarm_ON_OFF changed from OFF to ON
then
   val messageQuery0 = "Currently the following lights are / are on, should all lights be turned off?\n"
   val telegramAction = getActions("telegram","telegram:telegramBot:<ID>")
   telegramAction.sendTelegram("The alarm system is now active.")

   // Check lights
   if (Lampe1.state.toString != "0" || Lampe2.state.toString != "0") {
      messageQuery0 += "- lamp 1 and 2\n"
   }
   else if(Lampe3.state.toString != "0") {
      messageQuery0 += "- lamp 3\n"
   }
   else {
      messageQuery0 = 'All lights are off.'
   }

   if(messageQuery0 != 'All lights are off.') {
      telegramAction.sendTelegramQuery(messageQuery0, "Replay_Goodbye_Light", "Yes", "No")
   }   

   // Check contacts
   var Number count = 0
   val messageQuery1 = "Currently is / are still following windows / doors open!\n"

   if (Window1.state.toString == "OPEN") {
      messageQuery1 += "- Window 1\n"
      count = count + 1
   }
   if (Door1.state.toString == "OPEN") {
      messageQuery1 += "- Door 1\n")
      count = count + 1
   }

   if (count > 0) {
      messageQuery1 += "is / are still open")
      Alarm_ON_OFF.sendCommand(OFF) 
   }
   else {
      messageQuery1 = "All windows and doors are closed."
   }
   telegramAction.sendTelegram(messageQuery1)
end

rule "Alarm system OFF"
when
   Item Alarm_ON_OFF changed from ON to OFF
then
   val telegramAction = getActions("telegram","telegram:telegramBot:<ID>")
   telegramAction.sendTelegram("Welcome home.\nThe alarm system has been deactivated.")
end

rule "Goodbye Lights OFF"
when
    Item TelegramBot_ReplyId received update Replay_Goodbye_Light
then
    val telegramAction = getActions("telegram","telegram:telegramBot:<ID>")
    if (TelegramBot_LastMessage.state.toString == "YES") {
        gLights.sendCommand(OFF) 
        telegramAction.sendTelegramAnswer(TelegramBot_ReplyId.state.toString, "I turned off the lights.")
        TelegramBot_ReplyId.sendCommand("NULL")
    } 
    else {
        telegramAction.sendTelegramAnswer(TelegramBot_ReplyId.state.toString, "I leave the lights on.")   
        TelegramBot_ReplyId.sendCommand("NULL")
    }
end

rule "Break-in Movement"
when
    Member of gMovement received update ON
then
    val telegramAction = getActions("telegram","telegram:telegramBot:<ID>")
    val messageString2 = "!!! BREAK-IN NOTIFICATION !!!\n The motion detector:\n"

    if (Alarm_ON_OFF.state.toString == "ON") {
      if (MotionDetector1.state.toString == "OPEN") {
         messageString3 += "- Motion Detector 1\n"
      }
      if (MotionDetector2.state.toString == "OPEN") {
         messageString3 += "- Motion Detector 2\n"        
      }
      telegramAction.sendTelegram(messageString2)
    }
end

rule "Break-in Contacts"
when
    Member of gContact received update OPEN
then
    val telegramAction = getActions("telegram","telegram:telegramBot:<ID>")
    val messageString3 = "!!! BREAK-IN NOTIFICATION !!!\n The door/window contact:\n"

    if (Alarm_ON_OFF.state.toString == "ON") {
      if (DoorContact1.state.toString == "OPEN") {
         messageString3 += "- Doorcontact 1\n"
      }
      if (WindowContact1.state.toString == "OPEN") {
         messageString3 += "- Windowcontact 1\n"        
      }
      telegramAction.sendTelegram(messageString3)
    }
end