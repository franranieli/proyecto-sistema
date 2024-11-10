$(document).ready(function() {
    //on ready
});

async function logIn() {

    let data = {};
    data.email = document.getElementById('txtEmail').value;
    data.password = document.getElementById('txtPassword').value;


    const request = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });


    const response = await request.text();

    if (response != 'ERROR'){
        localStorage.email = data.email;
        localStorage.token = response;
        window.location.href = 'users.html'
    } else{
        alert("User and password are incorrect");
    }

}