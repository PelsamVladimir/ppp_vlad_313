$('document').ready(function () {


    $('.btn-group .btn-info').on('click', function (event) {

        event.preventDefault();

        alert('try to open modal')

        let button = $(this)
        let id = button.data('edId')
        let name = button.data('edName')
        let surname = button.data('edSurname')
        let email = button.data('edEmail')
        let password = button.data('edPassword')
        let age = button.data('edAge')
        let roles = button.data('edRoles')

        let myModal = $('#editModal')
        $('.modal-title').text('Edit user with ID = ' + id)
        $('#editId').val(id)
        $('#editName').val(name)
        $('#editSurname').val(surname)
        $('#editEmail').val(email)
        $('#editPassword').val(password)
        $('#editAge').val(age)
        $('#editRoles').val(roles)


        myModal.modal('show')
    });

    $('.table .btn-danger').on('click', function (event) {

        event.preventDefault();

        let delbutton = $(this)
        let id = delbutton.data('delId')
        let name = delbutton.data('delName')
        let surname = delbutton.data('delSurname')
        let email = delbutton.data('delEmail')
        let password = delbutton.data('delPassword')
        let age = delbutton.data('delAge')
        let roles = delbutton.data('delRoles')

        let delModal = $('#deleteModal')
        $('.modal-title').text('Delete user with ID = ' + id)
        $('#deleteId').val("" + id)
        $('#deleteName').val(name)
        $('#deleteSurname').val(surname)
        $('#deleteEmail').val(email)
        $('#deletePassword').val(password)
        $('#deleteAge').val(age)
        $('#deleteRoles').val(roles)


        delModal.modal('show')
    });


});