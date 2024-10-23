"use strict";

function init(){
    getSelector("#diagnose").addEventListener("click", openDiagnosis);
    getSelector("form").addEventListener("submit", openDiagnosis);
    getSelector("#history").addEventListener("click",openHistory);
}

function openDiagnosis(e) {
    document.querySelector(".errorHandler").innerText = "";
    e.preventDefault();
    const symptomsString = document.querySelector("#symptoms").value;
    const symptoms = symptomsString.split(",").map(symptom => symptom.trim());
    getDiagnosis(symptoms);
}

function openHistory(){
    setNewScreen("history.html");
}

function getDiagnosis(symptoms){
    post("aidoctor", {symptoms}, displayDiagnose, getDiagnoseFailureHandler);
}

async function displayDiagnose(response) {
    const json = await response.json();
        saveToSessionStorage("diagnose", json?.mostLikely);
        setNewScreen("diagnosis.html");

}

function getDiagnoseFailureHandler() {
    document.querySelector(".errorHandler").innerText = "Please fill in valid symptoms";
}




init();
