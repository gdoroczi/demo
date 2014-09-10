$(document).ready(function() {
	
    function createFoodList() {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/demo/foods"
        }).done(function(msg) {
            buildFoodList(msg);
        }).fail(function() {
            showErrorMsg("Error");
        });
    }

    function buildFoodList(foodList) {
        for (var k in foodList) {
            addFoodRowToTable(foodList[k]);
        }
    }

    function addFoodRowToTable(foodItem) {
        var row = "<tr><td>" + foodItem.id + "</td><td>" + foodItem.name + "</td><td><span data-id=\"" + foodItem.id + "\" class=\"btn btn-danger delete\">Delete</span></td></tr>";
        $("#foodList tbody").append(row);
    }

    function createNewFood() {
        var foodName = $("#newFood").val();

        if (foodName === '')
            alert('Food nam cannot be empty');
        else {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/demo/food",
                data: '{"name": "' + foodName + '"}',
                contentType: 'application/json'
            }).done(function(msg) {
                addFoodRowToTable({"id": msg, "name": foodName});
                showInfoMsg('Food was added');
                $("#newFood").val('');
            }).fail(function() {
                showErrorMsg("Error");
            });
        }
    }

    function deleteFood(id) {
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/demo/food/" + id
        }).done(function() {
            deleteFoodRowToTable(id);
            showInfoMsg('Food was deleted');
        }).fail(function() {
            showErrorMsg("Error");
        });
    }

    function showInfoMsg(msg) {
        showMessage(msg, 'alert-info');
    }

    function showErrorMsg(msg) {
        showMessage(msg, 'alert-danger');
    }

    function showMessage(msg, className) {
        var alertBox = $('.' + className);
        alertBox.html(msg);
        alertBox = alertBox.show();
        setTimeout(function() {
            alertBox.hide();
        }, 3000);
    }

    function deleteFoodRowToTable(id) {
        $('.delete[data-id=' + id + ']').parent().parent().remove();
    }

    $("#addButton").click(function() {
        createNewFood();
    });

    $(document).on('click', ".delete", function() {
        var id = $(this).data('id');
        deleteFood(id);
    });
    
    $('#newFood').keydown(function(event){
    	if (event.which == 13) {
    		createNewFood();
    	}
    });

    createFoodList();
    
});