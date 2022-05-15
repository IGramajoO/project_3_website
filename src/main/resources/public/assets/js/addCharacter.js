//Get user's teams and display them
populateChars().then(r => console.log(r));
async function populateChars(){
    // let userId = document.getElementById("userId").value;
    // console.log(userId)
    // let url = `localhost:8080/api/teamById?user_id=${userId}`;
    //
    // let res = await fetchData(url);
    // console.log(res);

    for(let i = 1; i < 6; i++){
        document.querySelector("#charTable").innerHTML +=
            `<tr>
                <th scope="row"> <img src="https://ftw.usatoday.com/wp-content/uploads/sites/90/2017/05/spongebob.jpg?w=1000&h=600&crop=1" alt="Spongebob"></th>
                  <td>Hawkeye</td>
                  <td class = "stats">
                        Strength: 100 | Intelligence: 50<br>
                        Speed: 200 | Durability: 50<br>
                        Power: -150 | Combat: 58
                  </td>
                  <td> <button class = "btn btn-success">Add To Team</button></td>
            </tr>`;
    }

}