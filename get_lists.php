<?php

  // include db connect class
  require_once __DIR__ . '/db_connect.php';

  // connecting to db
  $db = new DB_CONNECT();

  // get all lists
  $result = mysql_query("SELECT descripcion, cod from Lista") or die(mysql_error());

  // check for empty result
  if (mysql_num_rows($result) > 0) {
  // looping through all results
  // lista node
  $response["lista"] = array();
  while ($row = mysql_fetch_array($result)) {
  // temp user array
  $product = array();
  $product["descripcion"] = $row["descripcion"];
  $product["cod"] = $row["cod"];

  // push single product into final response array
  array_push($response["lista"], $product);
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
?>