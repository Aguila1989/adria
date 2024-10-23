"use strict";

async function init(){
    const user = await fetch("https://project-2.ti.howest.be/2023-2024/group-05/api/users/1")
        .then(res => res.json());
    if(user["subscriptionType"] !== "ResQ") {
        getSelector("button").addEventListener("click", toTimeline);
        document.querySelectorAll(".notInResQ").forEach(stat => {
            stat.classList.remove("notInResQ");
        });
    }
    buildData();
}

function buildData(){
    const twoSeconds = 2000;
    const fiveSeconds = 5000;
    setInterval(changeHeartRate, twoSeconds);
    setInterval(changeSaturation, fiveSeconds);
}

function changeSaturation(){
    const basePercentage = 96;
    const variablePercentage = 4;
    const lowNumberWhereItCanNeverEnter = 50;
    const unreachableSaturation = 95.1;
    const graphLength = 10;
    const saturation = Math.ceil(Math.random() * variablePercentage) + basePercentage;
    const saturationDisplayed = lowNumberWhereItCanNeverEnter + ((saturation - unreachableSaturation) * graphLength);
    getSelector(".sat").innerText = `${saturation}%`;
    getSelector(".sat-chart").setAttribute("x", saturationDisplayed);
}

function changeHeartRate(){
    const baseBpm = 78;
    const variableBpm = 10;
    const scaleGraph = 2;
    const heartRate = Math.floor(Math.random() * variableBpm) + baseBpm;
    getSelector(".heart-rate").innerText = `${heartRate} bpm`;
    getSelector(".heart-chart").setAttribute("x", heartRate / scaleGraph);
}

function toTimeline(){
    setNewScreen("timeline-page.html");
}

init();
