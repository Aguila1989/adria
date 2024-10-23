"use strict";

function saveToStorage(key, value) {
    if (localStorage) {
        return localStorage.setItem(key,JSON.stringify(value));
    }
    return null;
}

function saveToSessionStorage(key, value) {
    if (sessionStorage) {
        return sessionStorage.setItem(key,JSON.stringify(value));
    }
    return null;
}

function loadFromSessionStorage(key){
    if(sessionStorage) {
        return JSON.parse(sessionStorage.getItem(key));
    }
    return null;
}

function loadFromStorage(key) {
    if (localStorage) {
        return JSON.parse(localStorage.getItem(key));
    }
    return null;
}



