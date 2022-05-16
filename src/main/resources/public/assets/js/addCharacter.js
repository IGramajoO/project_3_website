//Get user's teams and display them
populateChars().then(r => console.log(r));
async function populateChars(){
    let teamId = localStorage.getItem("teamId");
    let username = document.getElementById("username").value;
    // console.log(userId)
    let url = `https://www.superheroapi.com/api.php/109324175078057/3/powerstats`;
    //
    let res = await fetchData(url);
    console.log(res);
    let num = 1;
    for(let i = 1; i < 6; i++){
        num = Math.floor(Math.random() * (731 - 1) + 1);
        console.log(num);
        url = `https://www.superheroapi.com/api.php/109324175078057/${num}`;
        res = await fetchData(url);
        console.log(res);
        document.querySelector("#charTable").innerHTML +=
            `<tr>
                <th scope="row"> <img src="${res.image.url}" alt="Hero Picture"></th>
                  <td>${res.name}</td>
                  <td class = "stats">
                        Strength: ${res.powerstats.strength} | Intelligence: ${res.powerstats.intelligence}<br>
                        Speed: ${res.powerstats.speed} | Durability: ${res.powerstats.durability}<br>
                        Power: ${res.powerstats.power} | Combat: ${res.powerstats.combat}
                  </td>
                  <td> 
                    <form action = "/addHeroToTeam?username=${username}&teamId=${teamId}&heroId=${res.id}" method="POST">
                        <input type = "hidden" value = "${username}" name = "username" id = "username">
                        <input type = "hidden" value = "${teamId}" name = "teamId" id = "teamId">
                        <input type = "hidden" value = "${res.id}" name = "heroId">
                        <button class = "btn btn-success">Add To Team</button>
                    </form>
                  </td>
            </tr>`;
    }

    // alert(username + " " + teamId);

}

async function fetchData(url){
    let response = await fetch(url);
    let data = await response.json();
    // console.log(data);
    return data;
}