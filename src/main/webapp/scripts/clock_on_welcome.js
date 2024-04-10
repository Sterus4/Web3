let clockUpdateTime = 9000;

setInterval(setClock, clockUpdateTime)

const hourHand = document.getElementById('data-hour-hand')
const minuteHand = document.getElementById('data-minute-hand')
const secondHand = document.getElementById('data-second-hand')

function setClock() {
    const currentDate = new Date()
    const secondsRatio = currentDate.getSeconds() / 60
    const minutesRatio = (secondsRatio + currentDate.getMinutes()) / 60
    const hoursRatio = (minutesRatio + currentDate.getHours()) / 12
    setRotation(secondHand, secondsRatio)
    setRotation(minuteHand, minutesRatio)
    setRotation(hourHand, hoursRatio)
}

function setRotation(element, rotationRatio) {
    element.style.setProperty('--rotation', rotationRatio * 360)
}

let clock = document.getElementById("clock-name");
setTime();
function setTime(){
    let currentTime = new Date();
    clock.innerHTML = `Текущее время: ${currentTime.toLocaleString()}`;
}

setInterval(setTime, clockUpdateTime);
setClock();