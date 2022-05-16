
//Get user's teams and display them
populateTeams().then(r => console.log(r));
async function populateTeams(){
    // let userId = document.getElementById("userId").value;
    // console.log(userId)
    //TODO: Change this to look at session id once oauth works
    let url = `http://localhost:8080/api/teamById?user_id=1`;

    let heroRes = await fetchData(url);
    let res = await fetchData(url);
    // console.log(res);

    let num = 0;
    if(res.length === 0){
        document.querySelector("#userTeams").innerHTML += "No Teams Made Yet."
    }
    else{
        for(let i = 0; i < res.length; i++){

            document.querySelector("#userTeams").innerHTML +=
                `<div class = team${i} >
                    <div class = "teamName">
                        <strong> <a href = "/team">Team ${i+1} </a></strong>
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
                        console.log("HERE AT INDEX " + k);
                        // console.log(res[i].heroesList);
                        num = res[i].heroesList[k].id;
                        console.log("STILL AT INDEX " + k);
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

async function fetchData(url){
    let response = await fetch(url);
    let data = await response.json();
    // console.log(data);
    return data;
}