<?php
require('config.php');
$user_id = $_REQUEST['user_id'];
$target_id = $_REQUEST['target_id'];

$query = "DELETE FROM phpfox_follow WHERE user_id = $target_id AND follower_id = $user_id";
$result = mysql_query($query);

$response[] = array('success'=>true, 'msg'=>'User unfollowed successfully!');
echo json_encode($response);

?>
