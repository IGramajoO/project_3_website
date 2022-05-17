document.querySelector("#addCharBtn").addEventListener("mouseover", checkChars);
setTeam();
displayChars().then(r => console.log(r));

var heroNum;
function setTeam(){
    let teamID = document.getElementById("teamId").value;
    localStorage.setItem("teamId", teamID);
    console.log("CHECK: " + localStorage.getItem("teamId"));
}


 async function displayChars(){
    let heroIds = document.getElementsByClassName("charName");
    let pictures = document.getElementsByClassName("charPic");
    let url;
    let heroRes;
    console.log(heroIds);

    heroNum = heroIds.length;

    for(let i = 0; i < heroIds.length; i++){
        console.log(heroIds[i].attributes[1].nodeValue)
        url = `https://www.superheroapi.com/api.php/109324175078057/${heroIds[i].attributes[1].nodeValue}`;
        heroRes = await fetchData(url);
        console.log(heroRes.name)
        heroIds[i].innerHTML = heroRes.name;
        pictures[i].innerHTML = `<img src= "${heroRes.image.url}"  alt="${heroRes.name}">`;
    }
}

function checkChars(){
    if(heroNum >= 5){
        document.getElementById("errorMsg").innerHTML += `<span style="color: red" class = "text-center"> A maximum of five characters are allowed at a time </span>`;
        document.querySelector("#addCharBtn").disabled = true;
    }
    else{
        document.querySelector("#addCharBtn").disabled = false;
    }
}

async function fetchData(url){
    let response = await fetch(url);
    let data = await response.json();
    console.log(data);
    return data;
}