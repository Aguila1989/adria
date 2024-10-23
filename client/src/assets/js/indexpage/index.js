function goToLogin(){
    const twoSeconds = 2000;
    setTimeout(toLogin, twoSeconds);
}

function toLogin() {
    window.location.assign("./login-page.html");
}

goToLogin();
