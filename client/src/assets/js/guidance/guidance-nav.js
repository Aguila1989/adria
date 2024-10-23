"use strict";

function init() {
    getSelector("#breathing").addEventListener("click", goBreathing);
    getSelector("#fleshWound").addEventListener("click", goFleshWound);
    getSelector("#impalement").addEventListener("click", goImpalement);
    getSelector("#heart").addEventListener("click", goHeart);
    getSelector("#deepWound").addEventListener("click", goDeepWound);
    getSelector("#fall").addEventListener("click", goFall);
}

function goBreathing() {
    setNewScreen("breathing.html");
}

function goFleshWound() {
    setNewScreen("wound.html");
}

function goImpalement() {
    setNewScreen("impalement.html");
}

function goHeart() {
    setNewScreen("heartAttack.html");
}

function goDeepWound() {
    setNewScreen("wound.html");
}

function goFall() {
    setNewScreen("fall.html");
}


init();
