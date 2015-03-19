<?php

 if (isset($_POST['lista'])) {
    $obj= json_decode($_POST['lista']);

    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();


    $cod = $obj->cod[0];
   
    $descL = $obj->desc[0];

    if($cod == 0){
	//insertamos la linea
	$result = mysql_query('Insert into Lista (descripcion) values("'.$descL.'")') or die(mysql_error());

	$rs = mysql_query("SELECT MAX(cod) AS id FROM Lista");
	if ($row = mysql_fetch_row($rs)) {
	    $id = $row[0];
	}
    }else
	    $id = $cod;
	    
    if(isset($_POST['delete']) and $cod_item != 0)
    {
      $del = $_POST['delete'];
      for($i = 0; $i<sizeof($del->items[0]);$i++)
      {
	$cod_item_del = $obj->items[0][$i]->cod;
	$result =mysql_query('Delete from Item where cod_lista='.$id.' and cod_item ='.$cod_item_del.')') or die(mysql_error());
      }
      
    }

    $response["items"] = array();
    
    for($i = 0; $i<sizeof($obj->items[0]);$i++)
    {
      $items = array();
      
      $descItem = $obj->items[0][$i]->descripcion;

      $cantidad = $obj->items[0][$i]->cantidad;

      $check = $obj->items[0][$i]->check_item;
      
      $cod_item = $obj->items[0][$i]->cod;
      
      if($obj->items[0][$i]->cambiado and $cod_item != 0)
      {
	$rs = mysql_query("SELECT cod_item AS id FROM Item where cod_item=".$cod_item);
	if ($row = mysql_fetch_row($rs)) {
	  $result =mysql_query('Update Item set descripcion ="'.$descItem.'", cantidad ='.$cantidad.', check_item='.$check.' where cod_lista ='.$id.' and cod_item ='.$cod_item) or die(mysql_error());
	}
      }
       else if($obj->items[0][$i]->cambiado or $cod_item == 0)
      {
	$result =mysql_query('Insert into Item (cod_lista, descripcion, cantidad, check_item) values('.$id.', "'.$descItem.'", '.$cantidad.', '.$check.')') or die(mysql_error());
	$rs = mysql_query("SELECT MAX(cod_item) AS id FROM Item where cod_lista=".$id);
	if ($row = mysql_fetch_row($rs)) {
	    $cod_item = $row[0];
	}
      }
      $items['cod_item'] = $cod_item;
      $items['cod_lista'] = $id;
      $items['desc'] = $descItem;
      $items['cantidad'] = $cantidad;
      $items['check'] = $check;
	
      array_push($response["items"], $items);
    }
    
    // check if row deleted or not
    if (mysql_affected_rows() > 0) {
        // successfully updated
        $response["success"] = 1;
	$response["cod"] = $id;
        $response["message"] = "Lista creada";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No hay items nuevos";

        // echo no users JSON
        echo json_encode($response);
    }
	
}
 else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Faltan parametros";

    // echoing JSON response
    echo json_encode($response);
}
?>