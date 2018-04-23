<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="icon" href="img">
				 <link rel="shortcut icon" href="public/image/logo2.png">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">


        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384- rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Itim" rel="stylesheet">

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <title>Insert title here</title>
        <style>
            body{
                background-color: #D3D3D3;
                background-repeat: no-repeat;
                background-size: cover;
                font-family: 'Itim', cursive;
                font-size: 14px;
                    }
            form {
                background-color:cornflowerblue;
                padding: 10px 10px;
                height: 4.0in;
                width: 3.5in;
                box-shadow: 1px 1px 25px rgba(0, 0, 0, 0.35);
                border-radius: 10px;
                color: white;
            }
        </style>
    </head>
    <body>
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <center>
                <br><br><br><br><br><br>


                <form method="post" action="UploadServlet" enctype="multipart/form-data">

                    <div class="row">
                        <img src="public/img/logo.png" alt="logo" width="60" height="60"><h4>Upload PhoneNumbers(*csv)</h4>
                    </div>
                    <br><br>
                    <input type="file" name="file" class="form-control" >
                    <br>
                    <input type="submit" value="Upload" name="submit" class="btn btn-warning btn-lg ">
                </form>

            </center>
        </div>
        <div class="col-md-4"></div>
    </body>
</html>