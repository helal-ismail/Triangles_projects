<?php
require('config.php');
$owner_id = $_REQUEST['user_id'];
$page=$_REQUEST['page'];
if ($page == null)
 $page = 0;

$limit = 25;
$offset = $page * $limit;



// Fetch 50 stories
$query = "select * from phpfox_story WHERE user_id = $owner_id ORDER BY story_id DESC LIMIT $offset, $limit "; 
$data = mysql_query($query);
$stories = array(); 
$index = 0;
while($row = mysql_fetch_array( $data )) 
{
     $customRow['story_id'] = $row['story_id'];
     $customRow['title'] = $row['title'];
     $customRow['total_comment'] = $row['total_comment'];
     $customRow['total_like'] = $row['total_like'];
     $customRow['total_repost'] = $row['total_repost'];


     $photo_id = 0;
     $photo_row = $row['photo_id'];
     $photo_arr = explode(" ", $photo_row);
     $i = 0;
     while($i <sizeof($photo_arr)){
    	if($photo_arr[$i] != ''){
          $photo_id = $photo_arr[$i];
          break;
        } 
	$i = $i + 1;     
     }
   
     //get Cover Photo
     $query_photo = "select * from phpfox_story_upload_photo where photo_id = '$photo_id'"; 
     $data_photo = mysql_query($query_photo);
     $photo = mysql_fetch_array( $data_photo);
     if($photo['destination'] == '')
	     $photourl = '';
     else
     {
             $_photourl = "http://paperv.com/file/pic/photo/" . $photo['destination'];
             $photourl = str_replace('%s','',$_photourl,$count);    
     } 
     $customRow['photourl'] = $photourl;

     $stories[$index] = $customRow;
     $index++;
}

$response[] = array('success'=>true, 'data'=>$stories);
echo json_encode($response);

?>

