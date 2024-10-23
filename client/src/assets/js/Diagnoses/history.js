"use strict";

const _LINK = "https://project-2.ti.howest.be/2023-2024/";

function init() {
    renderHistory();
    renderMedication();
    getSelector("#back").addEventListener("click", goBack);
}

function renderHistory() {
    fetch(_LINK + "group-05/api/users/1?includeMedicalHistory=true")
        .then(res => res.json())
        .then(json => {
            displayHistory(json);
        });
}

function renderMedication() {
    fetch(_LINK + "group-14/api/user/prescription/1")
        .then(res => res.json())
        .then(json => {
            displayMeds(json);
        });
}

function displayHistory(json) {
    const LIST = document.querySelector("#medical");
    const HISTORY = json.medicalHistory;

    LIST.innerHTML = "";
    LIST.insertAdjacentHTML("beforeend", `<li>BloodType: ${json.bloodType}</li>
                                                      <li>History:</li>`);
    for (const element of HISTORY) {
        LIST.insertAdjacentHTML("beforeend",
            `<li><article>
                <h3>${element.diagnosis.condition}</h3>
                <p>${element.date}</p>
                <p>${element.diagnosedBy}</p>
                </article></li>`);
    }
}

function displayMeds(json) {
    const LIST = document.querySelector("#medication");
    const MEDICATION = json.medicationSchedule;

    LIST.innerHTML = "";

    for (const element of MEDICATION) {
        LIST.insertAdjacentHTML("beforeend", `<article><h3>${element.medicationName}</h3><p>time: ${element.time}</p></article>`);
    }
}


init();
