function setNewScreen(href) {
    window.location.href = href;
}

function getTemplate(selector){
    return getSelector(`${selector}`).content.firstElementChild.cloneNode(true);
}

function getSelector(selector){
    return document.querySelector(`${selector}`);
}

function goBack(){
    window.history.back();
}
