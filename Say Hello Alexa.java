rule "Say Hello Alexa"
when
    frontdoor.state.toString changed from 'CLOSED' to 'OPEN'
then
    if(frontdoor.state.toString == 'OPEN' && frontdoor_motionsensor.state.toString == 'ON')
    {
        alexa_tts.sendCommand('Good Bye. See you later!')
    }
    else if(frontdoor.state.toString == 'OPEN' && frontdoor_motionsensor.state.toString == 'OFF')
    {
        alexa_tts.sendCommand('Welcome. Who is there?')
    }
end