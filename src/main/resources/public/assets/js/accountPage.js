
//Get user's teams and display them
populateTeams().then(r => console.log(r));
async function populateTeams(){
    let userId = document.getElementById("userId").value;
    console.log(userId)
    // let url = `localhost:8080/api/teamById?user_id=${userId}`;
    //
    // let res = await fetchData(url);
    // console.log(res);

    for(let i = 1; i < 4; i++){
        document.querySelector("#userTeams").innerHTML +=
            `<div class = "team" >
                    <div class = "teamName">
                        <strong> <a href = "/team">Team ${i} </a></strong>
                    </div>
                    <div class = "score">
                        Team Rating: 123
                    </div>
                    <div class = "hero">
                        Hero One
                    </div>
                    <div class = "hero">
                        Hero Two
                    </div>
                    <div class = "hero">
                        Hero Three
                    </div>
                    <div class = "hero">
                        Hero Four
                    </div>
                    <div class = "hero">
                        Hero Five
                    </div>
                </div>`;
    }

}

async function fetchData(url){
    let response = await fetch(url);
    let data = await response.json();
    console.log(data);
    return data;
}