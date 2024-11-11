<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calculator</title>
</head>
<body>
<h1>Calculator</h1>
<form action="/calculate" method="post">
    <label for="num1">Number 1: </label>
    <input type="number" id="num1" name="num1" required>
    <br>
    <label for="num2">Number 2: </label>
    <input type="number" id="num2" name="num2" required>
    <br>
    <input type="submit" value="Calculate">
</form>

<p>
    <strong>Result: </strong>
    <span>${sum != null ? sum : 'Please enter numbers and submit to calculate'}</span>
</p>
</body>
</html>
