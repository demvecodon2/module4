<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ứng Dụng Máy Tính</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Máy Tính Đơn Giản</h2>
    <form action="/calculate" method="post">
        <div class="form-group">
            <label for="num1">Số 1:</label>
            <input type="number" id="num1" name="num1" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="num2">Số 2:</label>
            <input type="number" id="num2" name="num2" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="operation">Chọn phép toán:</label>
            <select id="operation" name="operation" class="form-control">
                <option value="add">Cộng</option>
                <option value="subtract">Trừ</option>
                <option value="multiply">Nhân</option>
                <option value="divide">Chia</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Tính toán</button>
    </form>

    <div class="mt-4">
        <h4>Kết quả:</h4>
        <p>
            <strong>Phép toán:</strong> <span>${operation}</span><br>
            <strong>Số 1:</strong> <span>${num1}</span><br>
            <strong>Số 2:</strong> <span>${num2}</span><br>
            <strong>Kết quả:</strong> <span>${result}</span>
        </p>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
