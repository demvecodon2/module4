<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dictionary Lookup</title>
</head>
<body>
<h1>Dictionary Lookup</h1>

<form action="/dictionary" method="get">
    <label for="word">Enter word:</label>
    <input type="text" id="word" name="word" value="${word}">
    <input type="submit" value="Look up">
</form>

<h3>Result:</h3>
<p><strong>Word:</strong> ${word}</p>
<p><strong>Definition:</strong> ${definition}</p>
</body>
</html>
