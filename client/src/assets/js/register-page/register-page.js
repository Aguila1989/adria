const _URL = "https://project-2.ti.howest.be/2023-2024/group-05/api/users/1";

function init(){
    buildPlans();
    getSelector(".flex-container").addEventListener("click", reactToButton);
}

function reactToButton(e){
    const classList = e.target.classList;
    if (classList.contains('choose')) {
        selectArticle(e);
    } else if (classList.contains('monthly') || classList.contains('yearly')) {
        changeInterval(e);
    } else if(classList.contains('confirm')){
        confirmPlan();
    }
}

function changeInterval(e){
    resetClasses();
    const classList = e.target.classList;
    saveToStorage("interval", e.target.innerHTML);
    classList.remove("unselectedInterval");
    classList.add("selectedInterval");
    setText(e);
}

function setText(e){
    const id = loadFromStorage("plan");
    const plan = _PLANS[id];
    if(e.target.classList.contains("monthly")){
        addMonthlyPrices(plan);
    } else {
        addYearlyPrices(plan);
    }
}

function addMonthlyPrices(plan){
    addPrices(plan.monthlyPrice, "once a month");
}

function addYearlyPrices(plan){
    addPrices(plan.yearlyPrice, "yearly");
}

function addPrices(price, intervals){
    getSelector(".intervals").innerText = intervals;
    getSelector(".pay-amount").innerText = `${price} AdCoins`;
    getSelector(".due").innerText = `${price} AdCoins`;
}

function resetClasses(){
    const monthly = getSelector(".monthly");
    const yearly = getSelector(".yearly");
    monthly.classList.add("unselectedInterval");
    monthly.classList.remove("selectedInterval");
    yearly.classList.add("unselectedInterval");
    yearly.classList.remove("selectedInterval");

}

function selectArticle(e){
        const id = e.target.id;
        document.querySelectorAll("article").forEach(article => {
            if (article.getAttribute("id") !== id) {
                article.classList.toggle("hidden");
                buildPlanDetail(id);
            }else{
                article.classList.toggle("move");
                saveToStorage("plan", e.target.id);
            }
        });
        getSelector(".chosen-plan").classList.toggle("hidden");
        changeInnerTextButton(e);
}


function changeButtonWhenNotRegistered(button) {
    const buttonTexts = ["Choose", "Change Plan"];
    const choose = 0;
    const change = 1;
    if (button.innerHTML === buttonTexts[choose]) {
        button.innerHTML = buttonTexts[change];
    } else {
        button.innerHTML = buttonTexts[choose];
    }
}

async function changeInnerTextButton(e){
    const user = await fetch(_URL)
                .then(res => res.json());
    const button = e.target;
    if(user["subscriptionType"] === button.id ){
        button.innerText = "Current" + getIntervalString();
    }else {
        changeButtonWhenNotRegistered(button);
    }
}

function getData(planNr) {
    return JSON.stringify(
        {
            "type": "changeSubscription",
            "data": {
                "newSubscriptionType": planNr
            }
        });
}

async function confirmPlan(){
    const planNr = getplanNr();
    const data = getData(planNr);
    await fetch (_URL, {
        method: 'PUT',
        headers: {
            'Content-type': 'application/json;'
        },
        body: data
    });
    setNewScreen("thanks.html");
}

function getplanNr(){
    const plan = loadFromStorage("plan");
    if(plan === "ResQ"){
        return 1;
    }

    if (plan === "ResQ +"){
        return 2;
    }

    if(plan === "ResQ Pro"){
        return 3;
    }

    return  -1;
}

function buildTemplate($template, plan, id) {
    $template.setAttribute("id", plan.id);
    $template.querySelector(".intervals").innerText = "once a month";
    $template.querySelector(".pay-amount").innerText = `${plan.monthlyPrice} AdCoins`;
    $template.querySelector(".due").innerText = `${plan.monthlyPrice} AdCoins`;
    $template.querySelector(".confirm").setAttribute("id", id);
}

function buildPlanDetail(id){
    const $template = getTemplate("#plan-detail");
    const $container = getSelector(".chosen-plan");
    const plan = _PLANS[id];
    buildTemplate($template, plan, id);
    $container.innerHTML = $template.outerHTML;
}

function buildChooseButton($template, plan, user) {
    $template.querySelector("button").setAttribute("id", plan.id);
    if (user["subscriptionType"] && user["subscriptionType"] === plan.id) {
        $template.querySelector("button").innerText = "Current" + getIntervalString();
    } else {
        $template.querySelector("button").innerText = "Choose";
    }
}

function addInnerTextMonthlyPrice(plan, $template) {
    if (plan.monthlyPrice !== 0) {
        $template.querySelector("p").innerText = `${plan.monthlyPrice} AdCoins/month`;
    } else {
        $template.querySelector("p").innerText = "FREE";
    }
}

function buildSubscriptionDetails($template, plan) {
    $template.querySelector("h3").innerText = plan.title;
    $template.querySelector("ul").innerHTML = "";
    for (const feature of plan.features) {
        $template.querySelector("ul").insertAdjacentHTML("beforeend", `<li>${feature}</li>`);
    }
}

async function buildPlans() {
    const user = await getUser();
    const $template = getTemplate("#plan");
    const $container = getSelector(".plans");
    for (const planKey in _PLANS){
        const plan = _PLANS[planKey];
        $template.setAttribute("id", plan.id);
        buildSubscriptionDetails($template, plan);
        addInnerTextMonthlyPrice(plan, $template);
        buildChooseButton($template, plan, user);

        $container.insertAdjacentHTML("beforeend", $template.outerHTML);
    }
}
async function getUser(){
    return await fetch(_URL)
        .then(res => res.json());
}


function getIntervalString(){
    if(loadFromStorage("interval")) {
        return " - " + loadFromStorage("interval");
    }
    return "- Monthly";
}

init();
