<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
    $id_user=$_POST["id_user"];
	
    require_once 'connect.php';
    
    $stmt = $conn->prepare("SELECT tb_typecat.cat_type,tb_catfood.cat_food,SUM(amount_kg) as amont
    from tb_give_food join tb_typecat on tb_give_food.id_type_cat=tb_typecat.id_type_cat 
    join tb_catfood on tb_catfood.id_cat_food=tb_give_food.id_cat_food 
    JOIN tb_list_cat on tb_list_cat.id_type_cat=tb_give_food.id_type_cat 
    JOIN tb_user on tb_list_cat.id_user=tb_user.id_user 
    WHERE tb_user.id_user='$id_user'
    GROUP by cat_type,cat_food;");
	
	//executing the query 
    $stmt->execute();

	
	//binding results to the query 
    $stmt->bind_result($cat_type,$cat_food,$amount);

	$products = array(); 
	 
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
            
        // $temp['id_user'] = $id_list_cat;
        // $temp['id_user']=$id_typecat;
        // $temp['id_user']=$id_catfood;
        $temp['cat_food']=$cat_food;
        $temp['cat_type']=$cat_type;
        $temp['amount']=$amount;
        array_push($products, $temp);
        
      
	}
	
	//displaying the result in json format 
   

    echo json_encode($products);
}
     

    
 ?>