<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <title>user service</title>
    </head>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

    <script type="text/javascript">
        var prefix = '/service';

        var RestGet = function() {
            $.ajax({
                type: 'GET',
                url: prefix + '/' + 2,
                dataType: 'json',
                async: true,
                success: function(result) {
                    alert('User id' + result._id + ' ' +
                    result._name + ' ' + result._surname);
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.status + ' ' + jqXHR.responseText);
                }
            });
        }

        var RestPut = function() {
                var JSONObject= {
                    '_name': 'REST',
                    '_surname': 'test'
                };

                $.ajax({
                    type: 'PUT',
                    url:  prefix,
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(JSONObject),
                    dataType: 'json',
                    async: true,
                    success: function(result) {
                        alert('User id' + result._id + ' ' +
                        result._name + ' ' + result._surname);
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        alert(jqXHR.status + ' ' + jqXHR.responseText);
                    }
                });
        }

        var RestDelete = function() {
                $.ajax({
                    type: 'DELETE',
                    url:  prefix + '/' + 5,
                    dataType: 'json',
                    async: true,
                    success: function() {
                        alert('Id ' + 5 + ' удален');
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        alert(jqXHR.status + ' ' + jqXHR.responseText);
                    }
                });
            }

    </script>

    <body>

        <h3>Это простой пример использования REST c помощью Ajax</h3>

        <button type="button" onclick="RestGet()">Метод GET</button>

        <button type="button" onclick="RestDelete()">Метод DELETE</button>
        <button type="button" onclick="RestPut()">Метод PUT</button>

    </body>

</html>