function toRegisterPage() {
    setNewScreen("subs.html");
}

function toMain() {
    setNewScreen("main.html");
}

function init(){
    getSelector(".register").addEventListener("click", toRegisterPage);
    getSelector(".existing").addEventListener("click", toMain);
    removeFromStorage("plan");
}

init();
