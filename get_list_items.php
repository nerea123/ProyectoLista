<?php

  // include db connect class
  require_once __DIR__ . '/db_connect.php';

  // connecting to db
  $db = new DB_CONNECT();

  if(isset($_GET['cod']))
  {
    $res = json_decode($_GET['cod']);
    $cod = $res;
    $result = mysql_query("SELECT * from Item where cod_lista =".$cod) or die(mysql_error());

    // check for empty result
    if (mysql_num_rows($result) > 0) {
    // looping through all results
    // lista node
    $response["items"] = array();
    while ($row = mysql_fetch_array($result)) {
    // temp user array
    $product = array();
    $product["desc"] = $row["descripcion"];
    $product["cod"] = $row["cod_item"];
     $product["cantidad"] = $row["cantidad"];
    $product["check"] = $row["check_item"];

    // push single product into final response array
    array_push($response["items"], $product);
    }
    // success
    $response["success"] = 1;
    // echoing JSON response
    echo json_encode($response);
    } else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No products found";
    // echo no users JSON
    echo json_encode($response);
    }
  }else{
    $response["success"] = 0;
    $response["message"] = "Faltan parametros";
    // echo no users JSON
    echo json_encode($response);
}
 

  
?>