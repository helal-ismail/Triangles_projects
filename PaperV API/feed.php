<?php
require('config.php');

$page=$_REQUEST['page'];
if ($page == null)
 $page = 0;

$offset = $page * 20;
$limit = 20;


// Fetch Top 20 stories
$query = "select * from phpfox_story limit $offset, $limit "; 
$data = mysql_query($query);
$stories = array(); 
$index = 0;
while($row = mysql_fetch_array( $data )) 
{
     $customRow['story_id'] = $row['story_id'];
     $customRow['title'] = $row['title'];
     $customRow['total_comment'] = $row['total_comment'];
     $customRow['total_like'] = $row['total_like'];

     $photo_id = $row['default_cover'];
     //get Cover Photo
     $query_photo = "select * from phpfox_story_upload_photo where photo_id = '$photo_id'"; 
     $data_photo = mysql_query($query_photo);
     $photo = mysql_fetch_array( $data_photo);
     $photourl = "http://paperv.com/file/pic/photo/" . $photo['destination'];
     $customRow['photourl'] = $photourl;


     $customRow['user_id'] = $row['user_id'];
     $user_id = $customRow['user_id'];
     $query_user = "select * from phpfox_user where user_id = '$user_id'"; 
     $data_user = mysql_query($query_user);
     $user = mysql_fetch_array( $data_user);
     $user_image = "http://paperv.com/pic/user/" . $user['user_image'];
     //$customRow['user_image'] = $user_image;
     $customRow['user_fullname'] = $user['full_name'];


     $stories[$index] = $customRow;
     $index++;
}

$response[] = array('success'=>true, 'data'=>$stories);
echo json_encode($response);

?>

