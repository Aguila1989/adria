"use strict";

const _URL = "https://project-2.ti.howest.be/2023-2024/group-05/api/users/1";

async function init() {
    const PLAN = await getUserPlan();
    displayButtons(PLAN);
}

async function getUserPlan() {
    const user = await fetch(_URL)
        .then(res => res.json());
    return user["subscriptionType"];
}


function setPlan(PLAN, NAV) {
    if (PLAN === "ResQ Pro") {
        displayPro(NAV);
        listenerPro();
    } else if (PLAN === "ResQ +") {
        displayPlus(NAV);
        listenerPlus();
    } else {
        displayBasic(NAV);
        listenerBasic();
    }
}

function displayButtons(PLAN) {
    const NAV = getSelector("nav");
    NAV.innerHTML = "";
    NAV.className = "";
    setPlan(PLAN, NAV);
}

function displayPro(NAV) {
    NAV.insertAdjacentHTML("beforeend",
        `<button id="stats">My Statistics</button>
                <button id="coach">AI Health Coach</button>
                <button id="travel">Space Travel Prep</button>
                <button id="achievements">Achievements</button>
                <button id="diagnosis">AI Diagnosis</button>
                <button id="wellness">Wellness</button>`);
    NAV.className = "buttonsPro";
}

function displayPlus(NAV) {
    NAV.insertAdjacentHTML("beforeend",
        `<button id="stats">My Statistics</button>
                <button id="coach">AI Health Coach</button>
                <button id="diagnosis">AI Diagnosis</button>
                <button id="wellness">Wellness</button>`);
    NAV.className = "buttonsPlus";
}

function displayBasic(NAV) {
    NAV.insertAdjacentHTML("beforeend",
        `<button id="stats">My Statistics</button>
                <button id="diagnosis">AI Diagnosis</button>`);
    NAV.className = "buttonsBasic";
}

function listenerPro() {
    addStatsListener();
    getSelector("#coach").addEventListener("click", openCoach);
    getSelector("#travel").addEventListener("click", openTravel);
    getSelector("#achievements").addEventListener("click", openAchievements);
    addDiagnosisListener();
    getSelector("#wellness").addEventListener("click", openWellness);
}

function listenerPlus() {
    addStatsListener();
    getSelector("#coach").addEventListener("click", openCoach);
    addDiagnosisListener();
    getSelector("#wellness").addEventListener("click", openWellness);
}

function listenerBasic() {
    addStatsListener();
    addDiagnosisListener();
}

function addDiagnosisListener() {
    getSelector("#diagnosis").addEventListener("click", openSymptoms);
}

function addStatsListener() {
    getSelector("#stats").addEventListener("click", openStats);
}

function openStats() {
    setNewScreen("data.html");
}

function openCoach() {
    setNewScreen("coach.html");
}

function openTravel() {
    setNewScreen("travel.html");
}

function openAchievements() {
    setNewScreen("achievements.html");
}

function openSymptoms() {
    setNewScreen("symptoms.html");
}

function openWellness() {
    setNewScreen("wellness.html");
}


init();
