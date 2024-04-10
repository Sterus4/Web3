let graph = document.getElementById("graph");
let context = graph.getContext('2d');
context.font = "48px serif"
let localR = 100;
let axisLen = 140;
let arrowDx = 2;
let arrowDy = 6;
let traitLen = 6;
let pointRad = 5;
let badPointColor = "#DC143C";
let goodPointColor = "#32CD32";
let xField = document.getElementById("mainForm:xSpinner_input");
let yField = document.getElementById("mainForm:YInputText");
let submit = document.getElementById("mainForm:submit_button")
//TODO Изменение по слайдеру
let rText = document.getElementById("mainForm:rSliderText");
rText.addEventListener("input", redrawGraph);

graph.height = 300;
graph.width = 300;
let h = graph.height;
let w = graph.width;
context.translate(graph.width / 2, graph.height / 2);

function updateR(event, ui){
    rText.value = ui.value;
    redrawGraph();
}

function addPoint(event) {
    let r = rText.value;
    if (isNaN(r) || r <= 0){
        alert("Некорректное значение R")
        return;
    }
    let rect = graph.getBoundingClientRect();
    xField.value = (event.clientX - rect.left - graph.width / 2) * r / localR;
    yField.value = -(event.clientY - rect.top - graph.height / 2) * r / localR;
    submit.click();
}

graph.addEventListener("click", addPoint)

redrawGraph();

function drawGraph(){
    context.fillStyle = "#FFFFFF";
    context.strokeStyle = '#FFFFFF';
    context.clearRect(-w / 2, -h / 2, w, h);
    drawShapes();
    drawAxis();
    drawRTrait();
}


function drawShapes(){
    context.beginPath();
    context.fillStyle = "#EDD7B9";
    context.strokeStyle = '#EDD7B9';
    context.fillRect(0, 0, -localR, localR / 2);
    context.lineTo(- localR / 2, 0);
    context.lineTo(0, - localR / 2);
    context.lineTo(0, 0);
    context.fill();

    context.arc(0, 0, localR / 2, 0, Math.PI / 2);
    context.lineTo(0, 0);
    context.fill();
    context.lineWidth = 1;
    context.moveTo(0, 0);
    context.lineWidth = 2;
    context.closePath();
}


function drawAxis(){
    context.beginPath();
    context.fillStyle = "#A1845D";
    context.strokeStyle = "#A1845D";
    context.moveTo(0, axisLen);
    context.lineTo(0, -axisLen);
    context.moveTo(-axisLen, 0);
    context.lineTo(axisLen, 0);
    context.moveTo(0, -axisLen);
    context.lineTo(-arrowDx, -axisLen + arrowDy);
    context.moveTo(0, -axisLen);
    context.lineTo(arrowDx, -axisLen + arrowDy);

    context.moveTo(axisLen, 0);
    context.lineTo(axisLen - arrowDy, -arrowDx);
    context.moveTo(axisLen, 0);
    context.lineTo(axisLen - arrowDy, arrowDx);
    context.stroke();
    context.closePath();
}

function drawRTrait(){
    context.beginPath();
    context.fillStyle = "#A1845D";
    context.strokeStyle = "#A1845D";
    context.moveTo(localR / 2, traitLen / 2);
    context.lineTo(localR / 2, - traitLen / 2);
    context.moveTo(localR, traitLen / 2);
    context.lineTo(localR, - traitLen / 2);

    context.moveTo(-localR / 2, traitLen / 2);
    context.lineTo(-localR / 2, - traitLen / 2);
    context.moveTo(-localR, traitLen / 2);
    context.lineTo(-localR, - traitLen / 2);

    context.moveTo(-traitLen / 2, localR / 2);
    context.lineTo(traitLen / 2, localR / 2)
    context.moveTo(-traitLen / 2, localR);
    context.lineTo(traitLen / 2, localR)

    context.moveTo(-traitLen / 2, -localR / 2);
    context.lineTo(traitLen / 2, -localR / 2)
    context.moveTo(-traitLen / 2, -localR);
    context.lineTo(traitLen / 2, -localR)

    context.fillText("R/2", localR / 2, -traitLen / 2 - 3);
    context.fillText("-R/2", -localR / 2, -traitLen / 2 - 3);
    context.fillText("-R/2", traitLen / 2 + 1, localR / 2 - 2);
    context.fillText("R/2", traitLen / 2 + 1, -localR / 2 - 2);

    context.fillText("R", localR, -traitLen / 2 - 3);
    context.fillText("-R", -localR, -traitLen / 2 - 3);
    context.fillText("-R", traitLen / 2 + 1, localR - 2);
    context.fillText("R", traitLen / 2 + 1, -localR - 2);

    context.fillText("x", localR * 1.5 - 10, -traitLen / 2 - 3);
    context.fillText("y", traitLen / 2 + 1, -localR * 1.5 + 10);
    context.stroke();
    context.closePath();
}

function drawOnRTrait(rValue){
    context.beginPath();
    context.moveTo(localR / 2, traitLen / 2);
    context.lineTo(localR / 2, - traitLen / 2);
    context.moveTo(localR, traitLen / 2);
    context.lineTo(localR, - traitLen / 2);

    context.moveTo(-localR / 2, traitLen / 2);
    context.lineTo(-localR / 2, - traitLen / 2);
    context.moveTo(-localR, traitLen / 2);
    context.lineTo(-localR, - traitLen / 2);

    context.moveTo(-traitLen / 2, localR / 2);
    context.lineTo(traitLen / 2, localR / 2)
    context.moveTo(-traitLen / 2, localR);
    context.lineTo(traitLen / 2, localR)

    context.moveTo(-traitLen / 2, -localR / 2);
    context.lineTo(traitLen / 2, -localR / 2)
    context.moveTo(-traitLen / 2, -localR);
    context.lineTo(traitLen / 2, -localR);
    context.stroke();
    context.fillText((rValue / 2).toString(), localR / 2, -traitLen / 2 - 3);
    context.fillText((-rValue / 2).toString(), -localR / 2, -traitLen / 2 - 3);
    context.fillText((-rValue / 2).toString(), traitLen / 2 + 1, localR / 2 - 2);
    context.fillText((rValue / 2).toString(), traitLen / 2 + 1, -localR / 2 - 2);

    context.fillText(rValue.toString(), localR, -traitLen / 2 - 3);
    context.fillText(-rValue.toString(), -localR, -traitLen / 2 - 3);
    context.fillText(-rValue.toString(), traitLen / 2 + 1, localR - 2);
    context.fillText(rValue.toString(), traitLen / 2 + 1, -localR - 2);

    context.fillText("x", localR * 1.5 - 10, -traitLen / 2 - 3);
    context.fillText("y", traitLen / 2 + 1, -localR * 1.5 + 10);
}

function redrawPoints(){
    let table = document.getElementById("resultTable");
    let rows = table.rows.length;
    for (i = 1; i < rows; i++){
        let ceils = table.rows.item(i).cells;
        let localX = parseFloat(ceils.item(0).innerHTML);
        let localY = parseFloat(ceils.item(1).innerHTML);
        let localR1 = parseFloat(ceils.item(2).innerHTML);
        if (localR1 == rText.value) {
            if (ceils.item(3).innerHTML == "true"){
                drawPoint(localX * localR / localR1, -localY * localR / localR1, goodPointColor);
            } else {
                drawPoint(localX * localR / localR1, -localY * localR / localR1, badPointColor);
            }
        }
    }
}

function drawPoint(x, y, color){
    context.beginPath()
    context.arc(x, y, pointRad, 0,2 * Math.PI);
    context.fillStyle = color;
    context.fill();
    context.closePath();
}
function redrawGraph(){
    if (isNaN(rText.value) || rText.value <= 0){
        drawGraph();
        return;
    }
    context.fillStyle = "#FFFFFF";
    context.strokeStyle = '#FFFFFF';
    context.clearRect(-w / 2, -h / 2, w, h);
    drawShapes();
    drawAxis();
    drawOnRTrait(rText.value);
    redrawPoints();
}