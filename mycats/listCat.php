<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
    $id_user=$_POST["id_user"];
	
    require_once 'connect.php';
    
    $stmt = $conn->prepare("SELECT tb_user.id_user, 
    tb_list_cat.cat_name,tb_cat_gender.cat_gender, 
    tb_typecat.cat_type,tb_cat_colour.cat_colour,tb_catfood.cat_food 
    from tb_user join tb_list_cat on tb_list_cat.id_user=tb_user.id_user 
    join tb_typecat on tb_list_cat.id_type_cat=tb_typecat.id_type_cat 
    join tb_catfood on tb_list_cat.id_cat_food=tb_catfood.id_cat_food
    join tb_cat_gender on tb_list_cat.id_cat_gender=tb_cat_gender.id_cat_gender 
    join tb_cat_colour on tb_list_cat.id_cat_colour=tb_cat_colour.id_cat_colour 
    WHERE tb_user.id_user='$id_user'
    ORDER by tb_list_cat.id_list_cat DESC;");
	
	//executing the query 
    $stmt->execute();

	
	//binding results to the query 
    $stmt->bind_result($id_user, $cat_name,$cat_gender,$cat_type,$cat_colour,$cat_food);

	$products = array(); 
	 
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
         $temp['id_user'] = $id_user; 
        // $temp['id_user'] = $id_list_cat;
        // $temp['id_user']=$id_typecat;
        // $temp['id_user']=$id_catfood;
        $temp['cat_name']=$cat_name;
        $temp['cat_gender']=$cat_gender;
        $temp['cat_type']=$cat_type;
        $temp['cat_colour']=$cat_colour;
        $temp['cat_food']=$cat_food;
         
        array_push($products, $temp);
      
	}
	
	//displaying the result in json format 
   

    echo json_encode($products);
}
     

    
 ?>