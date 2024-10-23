"use strict";
async function init() {
    const PLAN = await getUserPlan();
    displayButtons(PLAN);
}

async function getUserPlan() {
   const user =  await fetch("https://project-2.ti.howest.be/2023-2024/group-05/api/users/1")
        .then(res => res.json());
    return user["subscriptionType"];
}

function buildCustomizeAndSubscriptionButton(NAV) {
    NAV.insertAdjacentHTML("beforeend",
        `<button id="customize">Customize</button>
                  <button id="sub">Subscription</button>`);
    NAV.className = "buttonsPro";
    listenerPro();
}

function buildSubscriptionButton(NAV) {
    NAV.insertAdjacentHTML("beforeend",
        `<button id="sub">subscription</button>`);
    NAV.className = "buttonsBasic";
    listenerBasic();
}

function displayButtons(PLAN) {
    const NAV = getSelector("nav");
    NAV.innerHTML = "";
    NAV.className = "";
    if (PLAN === "ResQ Pro") {
        buildCustomizeAndSubscriptionButton(NAV);
    }else {
        buildSubscriptionButton(NAV);
    }
}

function listenerPro() {
    getSelector("#customize").addEventListener("click",openCustomize);
    getSelector("#sub").addEventListener("click",openSubs);

}


function listenerBasic() {
    getSelector("#sub").addEventListener("click",openSubs);
}

function openCustomize() {
    setNewScreen("customize.html");
}

function openSubs() {
     setNewScreen("subs.html");
}

init();
