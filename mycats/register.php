<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
    $username=$_POST["username"];
    $password=$_POST["password"];
    $address=$_POST["address"];
    $password=password_hash($password,PASSWORD_DEFAULT);
    require_once 'connect.php';

    $sql="INSERT INTO tb_user(username,password,address)
    VALUES('$username','$password','$address')";

    if(mysqli_query($conn,$sql)){
        $result["success"]="1";
        $result["message"]="success";
        echo json_encode($result);
        mysqli_close($conn);
    }else{
        $result["success"]="0";
        $result["message"]="Error";
        echo json_encode($result);
        mysqli_close($conn);

    }
}
?>