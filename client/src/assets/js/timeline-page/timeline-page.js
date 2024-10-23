"use strict";

function init(){
    getSelector("button").addEventListener("click", toData);
    buildCharts();
}

function buildHeartChart(json) {
    const data = getData(json, "Heart Rate");
    const heartRates = Object.values(data);
    linechart(data, "heart Rate", "BPM", "#heart-rate", getMin(...heartRates), getMax(...heartRates));
}

function buildSaturationChart(json) {
    const data = getData(json, "SPO2");
    const satRates = Object.values(data);
    linechart(data, "Saturation", "Saturation", "#saturation", getMin(...satRates), getMax(...satRates));
}

function buildMuscleChart(json) {
    const data = getData(json, "Muscle Mass");
    const muscleRates = Object.values(data);
    linechart(data, "Muscle Rate", "Muscle Percentage", "#muscle-percentage", getMin(...muscleRates), getMax(...muscleRates));
}

function buildWaterChart(json) {
    const data = getData(json, "Body Water");
    const waterRates = Object.values(data);
    linechart(data, "Body Water", "Percentage", "#body-water", getMin(...waterRates), getMax(...waterRates));
}

function buildFatChart(json) {
    const data = getData(json, "Body Fat");
    const fatRates = Object.values(data);
    linechart(data, "Body Fat", "Percentage", "#fat-percentage", getMin(...fatRates), getMax(...fatRates));
}

function buildVisceralFatChart(json) {
    const data = getData(json,"Visceral Fat");
    const fatRates = Object.values(data);
    linechart(data, "Visceral Fat", "Rate", "#visceral-fat", getMin(...fatRates), getMax(...fatRates));
}

function buildBoneChart(json) {
    const data = getData(json, "Bone Mass");
    const boneRates = Object.values(data);
    linechart(data, "Bone Mass", "value", "#bone-mass", getMin(...boneRates), getMax(...boneRates));
}

async function buildCharts(){
    const data = await fetch("https://project-2.ti.howest.be/2023-2024/group-05/api/metrics/1")
        .then(res => res.json());
    buildHeartChart(data);
    buildSaturationChart(data);
    buildMuscleChart(data);
    buildWaterChart(data);
    buildFatChart(data);
    buildVisceralFatChart(data);
    buildBoneChart(data);
}

function toData(){
    setNewScreen("data.html");
}

function getData(data, key){
    const result = {};
    for (const dataKey in data) {
        result[dataKey] = data[dataKey][key];
    }
    return result;
}

function getMin(...data) {
    const padding = 0.95;
    return Math.min(...data) * padding;
}

function getMax(...data) {
    const padding = 1.05;
    return Math.max(...data) * padding;
}

function getY(max, min, yLabel) {
    return {
        max,
        min,
        title: {
            display: true,
            text: yLabel,
            font: {
                weight: "bold",
            }
        }
    };
}

function getX() {
    return {
        title: {
            display: true,
            text: "date",
            font: {
                weight: "bold",
            }
        }
    };
}

function buildTitle(labelValue) {
    return {
        display: true,
        text: labelValue,
        font: {
            size: 24
        }
    };
}

function setPlugins(labelValue) {
    return {
        legend: {
            labels: {
                boxHeight: 1
            }
        },
        title: buildTitle(labelValue)
    };
}

function getOptions(max, min, yLabel, labelValue) {
    return {
        maintainAspectRatio: false,
        pointRadius: 0,
        scales: {
            x: getX(),
            y: getY(max, min, yLabel)
        },
        plugins: setPlugins(labelValue)
    };
}

function buildData(graphData, labelValue) {
    return {
        labels: Object.keys(graphData).sort(),
        datasets: [
            {
                data: Object.values(graphData),
                label: labelValue,
                borderColor: "#4CAF50"
            }
        ]
    };
}

function linechart(graphData, labelValue, yLabel, location, min, max) {
    const ctx = document.querySelector(location).getContext('2d');
    const configuration = {
        type: 'line',
        data: buildData(graphData, labelValue),
        options: getOptions(max, min, yLabel, labelValue)
    };
    new Chart(ctx, configuration);
}

init();
