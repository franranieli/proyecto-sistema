$(document).ready(function() {
    //on ready
});

async function registerUsers() {

    let data = {};
    data.name = document.getElementById('txtName').value;
    data.lastName = document.getElementById('txtLastName').value;
    data.phone = document.getElementById('txtPhone').value;
    data.email = document.getElementById('txtEmail').value;
    data.password = document.getElementById('txtPassword').value;

    let repeatPassword = document.getElementById('txtRepeatPassword').value;

    if (repeatPassword != data.password){
        alert('The passwords are diferent');
        return ;
    }

    const request = await fetch('api/users', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });

    alert("The account has been created successfully")
    window.location.href = 'login.html'

}
