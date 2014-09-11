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
        var row = "<tr>" +
        		"<td>" + foodItem.id + "</td>"+
        		"<td><span class=\"foodName\">" + foodItem.name + "</td>"+
        		"<td><span data-id=\"" + foodItem.id + "\" class=\"btn btn-danger delete\">Delete</span></td>"+
        		"</tr>";
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
    
    function updateFoodName(id, name) {
    	$.ajax({
            type: "PUT",
            url: "http://localhost:8080/demo/food/"+id,
            data: '{"name": "' + name + '"}',
            contentType: 'application/json'
        }).done(function(msg) {
            showInfoMsg('Food was updated');
        }).fail(function() {
            showErrorMsg("Error");
        });
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
    
    $(document).on('click', '.foodName', function () {
        var input = $('<input />', {'type': 'text', 'name': 'aname', 'class':'foodNameInput', 'value': $(this).html()});
        $(this).parent().append(input);
        $(this).remove();
        input.focus();
    });
    
    $(document).on('focusout', '.foodNameInput', function () {
        $(this).parent().append($('<span />', {'class':'foodName'}).html($(this).val()));
        var id = $(this).parent().prev().text();
        var name = $(this).val();
        $(this).remove();
        updateFoodName(id, name);
    });
    
    $(document).on('keyup', '.foodNameInput', function (event) {
    	if (event.which == 13) {
    		event.preventDefault();
    		event.stopPropagation();
    		$(this).blur();
    	}
    });
    
    createFoodList();
    
});