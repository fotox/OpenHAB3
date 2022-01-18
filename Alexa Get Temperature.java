rule "alexa_get_temperature"
when
  Item change_room was updated
then
  var Number room1 = (kitchen_temperature.state as QuantityType<Number>).doubleValue
  var Number room2_workstation = (office_workstation_temperature.state as QuantityType<Number>).doubleValue
  var Number room2_window1 = (office_window1_temperature.state as QuantityType<Number>).doubleValue
  var Number room2_window2 = (office_window2_temperature.state as QuantityType<Number>).doubleValue

  var Number room1_all = room1
  var Number room2_all = (room2_workstation + room2_window1 + room2_window2)/3
  var Number all_rooms = (room1_all + room2_all)/2

  if (change_room.state.toString == '1'){
    var String temp_string = String.format("%.0f", room1_all) 
    Alexa_TTS.sendCommand("Currently it is"+temp_string+" degrees in the kitchen.")
  }
  else if (change_room.state.toString == '2'){
    var String temp_string = String.format("%.0f", room2_all) 
    Alexa_TTS.sendCommand("Currently it is"+temp_string+" degrees in the office.")
  }
  else if (change_room.state.toString == '3'){  
    var String temp_string = String.format("%.0f", all_rooms) 
    Alexa_TTS.sendCommand("Currently it is"+temp_string+" degrees in the house.")
  }  
  
  if (change_room.state.toString != '0'){
    change_room.sendCommand(0)
  }
  else{
    // Reset on zero
  }
end