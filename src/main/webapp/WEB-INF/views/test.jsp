<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Player Rankings</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .ranking-table {
            width: 80%;
            max-width: 800px;
            background-color: #fff;
            border-collapse: collapse;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            border-radius: 8px;
        }
        .ranking-table thead {
            background-color: #4CAF50;
            color: #fff;
        }
        .ranking-table th, .ranking-table td {
            padding: 12px 15px;
            text-align: left;
        }
        .ranking-table tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .ranking-table tbody tr:hover {
            background-color: #e8f5e9;
        }
    </style>
</head>
<body>

    <table class="ranking-table">
        <thead>
            <tr>
                <th>Rank</th>
                <th>Player</th>
                <th>Country</th>
                <th>Points</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>Novak Djokovic</td>
                <td>Serbia</td>
                <td>11,120</td>
            </tr>
            <tr>
                <td>2</td>
                <td>Carlos Alcaraz</td>
                <td>Spain</td>
                <td>8,535</td>
            </tr>
            <tr>
                <td>3</td>
                <td>Daniil Medvedev</td>
                <td>Russia</td>
                <td>7,280</td>
            </tr>
            <tr>
                <td>4</td>
                <td>Stefanos Tsitsipas</td>
                <td>Greece</td>
                <td>5,755</td>
            </tr>
            <tr>
                <td>5</td>
                <td>Andrey Rublev</td>
                <td>Russia</td>
                <td>5,305</td>
            </tr>
        </tbody>
    </table>

</body>
</html>