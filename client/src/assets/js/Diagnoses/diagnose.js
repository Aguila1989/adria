"use strict";

function init() {
    const timeToLoad = 3000;
    setTimeout(buildPage,timeToLoad);
    getSelector("#back").addEventListener("click",goBack);
    getSelector("#history").addEventListener("click",goHistory);
}

function showDiagnosis() {
    const diagnosis = loadFromSessionStorage("diagnose");
    const $container = document.querySelector(".diagnosis");
    if(diagnosis){
        const baseSearchUrl = "https://en.wikipedia.org/w/index.php?fulltext=1&search=";
        const searchTerm = diagnosis.replace(" ", "%20");
        $container.innerHTML = `You most likely have <a href="${baseSearchUrl}${searchTerm}&title=Special%3ASearch&ns0=1" target="_blank">${diagnosis}</a>`;
    } else{
        $container.innerText = "Illness not found";
    }
}

function buildPage(){
    getSelector("#analyzing").classList.add("hidden");
    getSelector("#analyzing").classList.remove("loading");
    showDiagnosis();
}


function goHistory(){
    setNewScreen("history.html");
}









init();
