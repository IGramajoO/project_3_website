setTeam()
function setTeam(){
    let teamID = document.getElementById("teamId").value;
    localStorage.setItem("teamId", teamID);
    console.log("CHECK: " + localStorage.getItem("teamId"));
}