<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    $cat_name=$_POST["cat_name"];
    $cat_gender=$_POST["cat_gender"];
    $cat_type=$_POST["cat_type"];
    $cat_colour=$_POST["cat_colour"];
    $cat_food=$_POST["cat_food"];
    $id_user=$_POST["id_user"];
    
    require_once 'connect.php';

    $sql ="INSERT INTO  tb_list_cat(id_user,cat_name
    ,id_us)
    SELECT tb_user.id_user,
    '$cat_name','$cat_colour' 
    from tb_user where tb_user.id_user='$id_user';";
   
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