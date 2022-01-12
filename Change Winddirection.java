rule "Winddirection"
when
    winddirection was updated
then
    var Number angle = (winddirection.state as QuantityType<Number>).doubleValue
    var Number north = 0
    var Number north_east = 45
    var Number east = 90
    var Number sued_east = 135
    var Number sued = 180
    var Number sued_west = 225
    var Number west = 270
    var Number north_west = 315
    var Number north_full = 360

    if (angle < 22.5) {
    winddirection_in_text.peastUpdate("north")
    winddirection_in_text.sendCommand("north")
    }
    else if (angle < 22.5 + 45){
    winddirection_in_text.peastUpdate("north-east")
    winddirection_in_text.sendCommand("north-east")
    }
    else if (angle < 22.5 + 90) {
    winddirection_in_text.peastUpdate("east")
    winddirection_in_text.sendCommand("east")
    }
    else if (angle < 22.5 + 135){
    winddirection_in_text.peastUpdate("south-east")
    winddirection_in_text.sendCommand("south-east")
    }
    else if (angle < 22.5 + 180) {
    winddirection_in_text.peastUpdate("south")
    winddirection_in_text.sendCommand("south")
    }
    else if (angle < 22.5 + 225) {
    winddirection_in_text.peastUpdate("south-west")
    winddirection_in_text.sendCommand("south-west")
    }
    else if (angle < 22.5 + 270) {
    winddirection_in_text.peastUpdate("west")
    winddirection_in_text.sendCommand("west")
    }
    else if (angle < 22.5 + 315) {
    winddirection_in_text.peastUpdate("north-west")
    winddirection_in_text.sendCommand("north-west")
    }
    else {
    winddirection_in_text.peastUpdate("north")
    winddirection_in_text.sendCommand("north")
    }
end