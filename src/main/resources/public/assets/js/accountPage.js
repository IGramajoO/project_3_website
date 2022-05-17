document.querySelector("#createTeamBtn").addEventListener("mouseover", checkTeams);
//Get user's teams and display them
populateTeams().then(r => console.log(r));

var teamSize;

async function populateTeams(){
    let username = document.getElementById("username").value;
    // console.log(userId)
    //TODO: Change this to not be localhost
    let url = `http://localhost:8080/api/teamByUsername?username=${username}`;

    let heroRes = await fetchData(url);
    let res = await fetchData(url);

    let num = 0;
    teamSize = res.length;

    if(res.length === 0){
        document.querySelector("#userTeams").innerHTML += "No Teams Made Yet."
    }
    else{
        for(let i = 0; i < res.length; i++){

            document.querySelector("#userTeams").innerHTML +=
                `<div class = team${i} >
                    <div class = "teamName">
                        <strong> <a href = "/team?team_id=${res[i].id}">Team ${i+1} </a></strong>
                    </div>
                    <div class = "score">
                        Team Rating: 123
                    </div>`;
                    if(res[i].heroesList.length === 0){
                        console.log("EMPTY");
                        document.querySelector(`.team${i}`).innerHTML += "No Characters Yet"
                        continue;
                    }
                    for(let k = 0; k < res[i].heroesList.length; k++){
                        num = res[i].heroesList[k].id;
                        url = `https://www.superheroapi.com/api.php/109324175078057/${num}`;
                        heroRes = await fetchData(url);

                        document.querySelector(`.team${i}`).innerHTML +=
                            `<div class = "hero">
                            ${heroRes.name}
                        </div>`;
                    }

            document.querySelector("#userTeams").innerHTML += `</div>`;
        }
    }
}

function checkTeams(){
    if(teamSize >= 3){
        document.getElementById("errorMsg").innerHTML += `<span style="color: red" class = "text-center"> A maximum of three teams are allowed at a time </span>`;
        document.querySelector("#createTeamBtn").disabled = true;
    }
    else{
        document.querySelector("#createTeamBtn").disabled = false;
    }
}

async function fetchData(url){
    let response = await fetch(url);
    let data = await response.json();
    console.log(data);
    return data;
}