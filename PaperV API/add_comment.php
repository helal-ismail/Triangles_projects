<?php
require('config.php');
$user_id = $_REQUEST['user_id'];
$story_id = $_REQUEST['story_id'];
$comment_text = $_REQUEST['comment'];

// === 1 - Get Story Owner ===
$query = "SELECT user_id FROM phpfox_story WHERE story_id = $story_id";
$data = mysql_query($query);
$row = mysql_fetch_array( $data );
$story_owner = $row['user_id'];

// === 2 - Insert comment ===
$query = "INSERT INTO phpfox_comment (type_id, item_id, user_id, owner_user_id, time_stamp) VALUES ('story', $story_id, $user_id, $story_owner, UNIX_TIMESTAMP(now()))";
$data = mysql_query($query);
if($data == '')
	$response[] = array('success'=>false, 'msg'=> 'Add comment failed!' );
else
{	
	$response[] = array('success'=>false, 'msg'=> 'Comment added successfully!' );
	echo $data;
			
}

?>
