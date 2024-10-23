"use strict";

function init() {
    getSelector("span").innerHTML = getUserPlan();
    setTimeout(goMain,3000);
}

function getUserPlan() {
    return loadFromStorage("plan");
}

function goMain() {
    setNewScreen("main.html");
}

init();
