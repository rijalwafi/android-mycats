<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
    $id_user=$_POST["id_user"];
	
    require_once 'connect.php';
    
    $stmt = $conn->prepare("SELECT tb_typecat.cat_type,
    tb_cat_gender.cat_gender,count(cat_type) as number_of_cat 
    from tb_list_cat left outer join tb_typecat 
    on tb_list_cat.id_type_cat=tb_typecat.id_type_cat 
    join tb_cat_gender on tb_list_cat.id_cat_gender=tb_cat_gender.id_cat_gender 
    join tb_user on tb_user.id_user=tb_list_cat.id_user
    where tb_user.id_user='$id_user' GROUP BY cat_type
    ;");
	
	//executing the query 
    $stmt->execute();

	
	//binding results to the query 
    $stmt->bind_result($cat_type,$cat_gender,$number_of_cat);

	$products = array(); 
	 
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
            
        // $temp['id_user'] = $id_list_cat;
        // $temp['id_user']=$id_typecat;
        // $temp['id_user']=$id_catfood;
        $temp['cat_gender']=$cat_gender;
        $temp['cat_type']=$cat_type;
        $temp['number_of_cat']=$number_of_cat;
        array_push($products, $temp);
        
      
	}
	
	//displaying the result in json format 
   

    echo json_encode($products);
}
     

    
 ?>