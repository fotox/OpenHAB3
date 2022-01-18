rule "Alexa Whats Up Today"
when
  whats_up_today changed from OFF to ON
then
  var String today = info_date.state.format("%1$td%1$tm")
  var String tomorrow = timestamp_tomorrow.state.toString
  var String trash_tomorrow = garbage_calendar_next_trash.state.format("%1$td%1$tm")
  var String freeday_tomorrow = next_freeday.state.format("%1$td%1$tm")
  var String hour = timestamp_hour.state.format("%1$tH")
  var String info_notice = "I have a current info for you."
  var String clock_out = "So feel free to turn off your alarm clock."
  var String congratulated = "Have you already congratulated?"
  var String pickup = "And do not forget tomorrow will be picked up"
  val Number volume = (alexa_tts.state as DecimalType).intValue
  var Number count = 0
  var String daytime = ""
  var String trash_typ = ""
  var String name = ""
  var String freeday = ""
  var String output = ""
  var int volume = 0
  var int voice_volume = 50

  if (tomorrow == trash_tomorrow){
    trash_typ = garbage_calendar_name.state.toString
    count = count + 1
  }

  if (birthday_calendar_name.state.toString != "UNDEF"){
    name = birthday_calendar_name.state.toString
    count = count + 2
  }

  if (tomorrow == freeday_tomorrow){
    freeday = next_freeday.state.toString
    count = count + 5
  }

  if (Float::parseFloat(hour) < 12){
    daytime = "Good morning"
  }
  else if (Float::parseFloat(hour) >= 12 && Float::parseFloat(hour) < 18){
    daytime = "Hello"
  }
  else {
    daytime = "Good evening"
  }

  if (count != 0){
    if (count == 1){
      output = ""+daytime+", "+info_notice+" Do not forget tomorrow will be picked up "+trash_typ+"."
    }
    if (count == 2){
      output = ""+daytime+", "+info_notice+" Today is "+name+" birthday. "+congratulated+""
    }
    if (count == 5){
      output = ""+daytime+", "+info_notice+" Tomorrow is "+freeday+". "+clock_out+""
    }
    if (count == 3){
      output = ""+daytime+", "+info_notice+" Today is "+name+" birthday. "+congratulated+" "+pickup+" "+trash_typ+"."
    }  
    if (count == 5){
      output = ""+daytime+", "+info_notice+" Today is "+name+" birthday. "+congratulated+" And tomorrow is "+freeday+". "+clock_out+""
    }
    if (count == 6){
      output = ""+daytime+", "+info_notice+" Tomorrow is "+freeday+". "+clock_out+" "+pickup+" "+trash_typ+"."
    }  
    if (count == 8){    
      output = ""+daytime+", "+info_notice+" Today is "+name+" birthday. "+congratulated+" And tomorrow is "+freeday+". "+clock_out+" "+pickup+" "+trash_typ+"."  
    }
  }
  else {
    output = ""+daytime+", your calendar will be empty."
  }

  alexa.tts_volume.sendCommand(3)
  alexa.tts.sendCommand(output)
  alexa.tts_volume.sendCommand(volume)

  if (whats_up_today.state.toString == "ON"){
    whats_up_today.sendCommand(OFF)
  }
end