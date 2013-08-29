<?php
require('config.php');

$user_id = $_REQUEST['user_id'];
$category_id = $_REQUEST['category_id'];
$title = $_REQUEST['title'];
$content = $_REQUEST['content'];
$location = $_REQUEST['location'];
$captions = $_REQUEST['captions'];

$query = "INSERT INTO phpfox_story (title, content, content_parser, owner_type, user_id, story_date, location, time_stamp) VALUES ('$title', '$content', '$content', 'user', $user_id, UNIX_TIMESTAMP(now()), '$location', UNIX_TIMESTAMP(now()))";



$data = mysql_query($query);
if($data == '')
	$response[] = array('success'=>false, 'msg'=> 'Add Story failed!' );
else
{	


	$query = "SELECT story_id FROM phpfox_story WHERE user_id = $user_id ORDER BY story_id DESC";
	$data = mysql_query($query);
	$row = mysql_fetch_array( $data );
	$story_id = $row['story_id'];	
	$query = "INSERT INTO phpfox_story_category_data (story_id, category_id) VALUES ($story_id, $category_id)";
	mysql_query($query);

	$year = date("Y");
	$month = date("m");
	$dir = '../file/pic/photo/'.$year."/".$month."/";
	
	$count = 0 ;
	for ($i=0; $i<sizeOf($_FILES["image"]["name"]); $i++)
  	{	$count++;
		$dest = $dir.$_FILES["image"]["name"][$i];
		$tmp = $_FILES["image"]["tmp_name"][$i];
		$moved = move_uploaded_file($tmp , $dest);
		
		$destination = $year."/".$month."/".$_FILES["image"]["name"][$i];
		
		$query = "INSERT INTO phpfox_story_upload_photo (title, user_id, destination, time_stamp) VALUES ('mobile_image', $user_id, '$destination', UNIX_TIMESTAMP(now()) )";
		mysql_query($query);
		
		$query = "SELECT photo_id FROM phpfox_story_upload_photo WHERE user_id = $user_id AND title = 'mobile_image' ORDER BY photo_id DESC";
		$data = mysql_query($query);
		$row = mysql_fetch_array( $data );
		$photo_id = $row['photo_id'];

		$query = "INSERT INTO phpfox_story_item (story_id, item_id, item_type, caption) VALUES ($story_id, $photo_id, 'uploadphoto', 'sample caption' )";
		mysql_query($query);


		if ($i == 0 )
		{
			$query = "UPDATE phpfox_story SET type ='uploadphoto', photo_id = $photo_id WHERE story_id = $story_id";
			mysql_query($query);
		}
	}
	
	$videos = explode( "," , $_REQUEST['videos'] );
	for ($i=0; $i<sizeOf($videos); $i++)
  	{
		$count++;
		
		$query = "INSERT INTO phpfox_story_video (title, user_id, video_url) VALUES ('mobile_video', $user_id, '$videos[$i]' )";
		mysql_query($query);
		
		$query = "SELECT video_id FROM phpfox_story_video WHERE user_id = $user_id AND title = 'mobile_video' ORDER BY video_id DESC";
		$data = mysql_query($query);
		$row = mysql_fetch_array( $data );
		$video_id = $row['video_id'];

		$query = "INSERT INTO phpfox_story_item (story_id, item_id, item_type, caption) VALUES ($story_id, $video_id, 'attached_video', 'sample caption' )";
		mysql_query($query);



	}

	$response[] = array('success'=>true, 'story_id'=> $story_id,'media_count'=>$count , 'msg'=> 'Story Added Successfully!' );
	
}	
echo json_encode($response);

?>
