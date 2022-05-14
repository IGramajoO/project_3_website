
//Use this to get the data for team page
async function createTeams(){
    console.log("HERE");
    let newName = document.querySelector("#newWishlistName").value;
    // let url = `https://intense-springs-54966.herokuapp.com/api/addList?username=${username}&listName=${newName}`;

    const requestOptions = {
        method: 'PUT',
        redirect: 'follow'
    };

    let res;
    await fetch(url, requestOptions)
        .then(response => response.text())
        .then(result => {
            res = result;
            console.log(res);
        })
        .then(response => console.log(response))
        .catch(error => console.log('error', error));

    // document.querySelector("#newWishlistName").value = "";

    // window.location.reload();

}