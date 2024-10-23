"use strict";
const _SLIDER = getSelector("#emergencySlider");

function init() {
    getSelector("#emergencySlider").addEventListener("mouseup", alert);
    getSelector("#logo").addEventListener("click", toMain);
    getSelector("#guidance").addEventListener("click",openGuidance);
    if(!window.location.pathname.includes("guidance")){
        getSelector("#settings").addEventListener("click",openSettings);
    }
}


function alert() {

    const maxValueSlider = 10000000000000;

    if (_SLIDER.value < maxValueSlider) {
        _SLIDER.value = 0;
    } else {
        changeSliderMessage();
        window.location.href = "../group-05/map.html";
        setTimeout(resetSlider, 1500);
    }

}

function resetSlider() {
    _SLIDER.value = 0;
    const LABEL = getSelector("#emergencyLabel");
    LABEL.innerHTML = "";
    LABEL.insertAdjacentHTML("beforeend", "! ALERT EMERGENCIES !");
}

function changeSliderMessage() {
    const LABEL = getSelector("#emergencyLabel");
    LABEL.innerHTML = "";
    LABEL.insertAdjacentHTML("beforeend", "! ALERT SENT !");
}

function toMain() {
    if (window.location.pathname.includes("guidance")) {
        window.location.href = "../main.html";
    } else {
        window.location.href = "main.html";
    }
}

function openSettings() {
    window.location.href = "settings.html";
}

function openGuidance(){
    if (window.location.pathname.includes("guidance")) {
        window.location.href = "guidance.html";
    } else {
    window.location.href = "guidance/guidance.html";
    }
}

init();
