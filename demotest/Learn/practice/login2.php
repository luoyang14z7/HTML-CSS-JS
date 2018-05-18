<?php

$con=mysqli_connect('127.0.0.1','root',"123456",'ying',3306);
$username=$_REQUEST["username"];
$password=$_REQUEST["password"];
$sql = mysqli_query($con,"select * from users where username='".$username."' and password = '".$password."';");
$row=mysqli_fetch_array($sql);
if ($row) {
    ?>
    <script >
        alert("123");
        window.location.href="index.html"
    </script>
<?php
}
else {
    ?>
    <script >
    window.location.href="ying.html"
        </script>
<?php
}
?>





