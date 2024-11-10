// Call the dataTables jQuery plugin
$(document).ready(function() {
  addUsers();
  $('#users').DataTable();
  updateUserEmail();
});

function updateUserEmail(){
  document.getElementById('txt-email-user').outerHTML = localStorage.email;
}

async function addUsers() {

  const request = await fetch('api/users', {
    method: 'GET',
    headers: getHeaders()
  });
  const users = await request.json();

  console.log(users);



  let listHtml = '';
  for (let user of users){
    let buttonDelete = '<a href="#" onclick="deleteUser('+ user.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

    let userHtml = '<tr><td>'+user.id+'</td><td>'+user.name+' '+user.lastName+'</td><td>'+user.email+'</td><td>'+user.phone+'</td><td>' + buttonDelete + '</td></tr>';
    listHtml += userHtml;
  }

  document.querySelector('#users tbody').outerHTML = listHtml;

}

function getHeaders(){
  return {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    'Authorization': localStorage.token
  };
}

async function deleteUser(id) {
  if (!confirm('¿Desea eliminar este usuario?')){
    return;
  }
  const request = await fetch('api/users/' + id, {
    method: 'DELETE',
    headers: getHeaders()
  });

  location.reload()

}
